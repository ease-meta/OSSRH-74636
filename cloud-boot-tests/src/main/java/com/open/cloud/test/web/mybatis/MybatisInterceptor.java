/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.open.cloud.test.web.mybatis;

import com.github.pagehelper.Dialect;
import com.github.pagehelper.PageException;
import com.github.pagehelper.PageInterceptor;
import com.github.pagehelper.util.ExecutorUtil;
import com.github.pagehelper.util.StringUtil;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.*;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author miemie
 * @since 3.4.0
 */
@SuppressWarnings({"rawtypes"})
@Intercepts({
        @Signature(type = StatementHandler.class, method = "prepare", args = {
                Connection.class,
                Integer.class}),
        @Signature(type = StatementHandler.class, method = "getBoundSql", args = {}),
        @Signature(type = Executor.class, method = "update", args = {MappedStatement.class,
                Object.class}),
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class,
                Object.class,
                RowBounds.class,
                ResultHandler.class}),
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class,
                Object.class,
                RowBounds.class,
                ResultHandler.class,
                CacheKey.class,
                BoundSql.class}),})
public class MybatisInterceptor extends PageInterceptor {

    private String default_dialect_class = "com.open.cloud.test.web.mybatis.MybatisPageHelper";

    private String countSuffix = "_COUNT";

    private volatile Dialect dialect;

    private static final List<ResultMapping> EMPTY_RESULTMAPPING = new ArrayList<ResultMapping>(0);

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object target = invocation.getTarget();
        Object[] args = invocation.getArgs();
        if (target instanceof Executor) {
            Executor executor = (Executor) invocation.getTarget();
            boolean isUpdate = args.length == 2;
            MappedStatement ms = (MappedStatement) args[0];
            Object parameter = args[1];
            if (!isUpdate && ms.getSqlCommandType() == SqlCommandType.SELECT) {
                return super.intercept(invocation);
            } else if (isUpdate) {
                if (ms.getId().contains("ByPage")) {
                    if (!dialect.skip(ms, parameter, null)) {
                        //判断是否需要进行 count 查询
                        if (dialect.beforeCount(ms, parameter, null)) {
                            //查询总数
                            BoundSql boundSql = ms.getBoundSql(parameter);
                            Long count = count(executor, ms, parameter, null, null, boundSql);
                            //处理查询总数，返回 true 时继续分页查询，false 时直接返回
                            if (!dialect.afterCount(count, parameter, null)) {
                                //当查询总数为 0 时，直接返回空的结果
                                return dialect.afterPage(new ArrayList(), parameter, null);
                            }
                        }

                    }

                }
            }
        } else {
            // StatementHandler
            final StatementHandler sh = (StatementHandler) target;
            // 目前只有StatementHandler.getBoundSql方法args才为null

        }
        return invocation.proceed();
    }

    private Long count(Executor executor, MappedStatement ms, Object parameter,
                       RowBounds rowBounds, ResultHandler resultHandler, BoundSql boundSql)
            throws SQLException {
        String countMsId = ms.getId() + countSuffix;
        Long count;
        //先判断是否存在手写的 count 查询
        MappedStatement countMs = ExecutorUtil.getExistedMappedStatement(ms.getConfiguration(),
                countMsId);
        if (countMs != null) {
            count = ExecutorUtil.executeManualCount(executor, countMs, parameter, boundSql,
                    resultHandler);
        } else {
            if (msCountMap != null) {
                countMs = msCountMap.get(countMsId);
            }
            //自动创建
            if (countMs == null) {
                //根据当前的 ms 创建一个返回值为 Long 类型的 ms
                countMs = newCountMappedStatement(ms, countMsId);
                if (msCountMap != null) {
                    msCountMap.put(countMsId, countMs);
                }
            }
            count = ExecutorUtil.executeAutoCount(this.dialect, executor, countMs, parameter,
                    boundSql, rowBounds, resultHandler);
        }
        return count;
    }

    public static MappedStatement newCountMappedStatement(MappedStatement ms, String newMsId) {
        MappedStatement.Builder builder = new MappedStatement.Builder(ms.getConfiguration(),
                newMsId, ms.getSqlSource(), SqlCommandType.SELECT);
        builder.resource(ms.getResource());
        builder.fetchSize(ms.getFetchSize());
        builder.statementType(ms.getStatementType());
        builder.keyGenerator(ms.getKeyGenerator());
        if (ms.getKeyProperties() != null && ms.getKeyProperties().length != 0) {
            StringBuilder keyProperties = new StringBuilder();
            for (String keyProperty : ms.getKeyProperties()) {
                keyProperties.append(keyProperty).append(",");
            }
            keyProperties.delete(keyProperties.length() - 1, keyProperties.length());
            builder.keyProperty(keyProperties.toString());
        }
        builder.timeout(ms.getTimeout());
        builder.parameterMap(ms.getParameterMap());
        //count查询返回值int
        List<ResultMap> resultMaps = new ArrayList<ResultMap>();
        ResultMap resultMap = new ResultMap.Builder(ms.getConfiguration(), ms.getId(), Long.class,
                EMPTY_RESULTMAPPING).build();
        resultMaps.add(resultMap);
        builder.resultMaps(resultMaps);
        builder.resultSetType(ms.getResultSetType());
        builder.cache(ms.getCache());
        builder.flushCacheRequired(ms.isFlushCacheRequired());
        builder.useCache(ms.isUseCache());

        return builder.build();
    }

    @Override
    public Object plugin(Object target) {
        if (target instanceof Executor || target instanceof StatementHandler) {
            return Plugin.wrap(target, this);
        }
        return target;
    }

    /**
     * 使用内部规则,拿分页插件举个栗子:
     * <p>
     * - key: "@page" ,value: "com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor"
     * - key: "page:limit" ,value: "100"
     * <p>
     * 解读1: key 以 "@" 开头定义了这是一个需要组装的 `InnerInterceptor`, 以 "page" 结尾表示别名
     * value 是 `InnerInterceptor` 的具体的 class 全名
     * 解读2: key 以上面定义的 "别名 + ':'" 开头指这个 `value` 是定义的该 `InnerInterceptor` 属性需要设置的值
     * <p>
     * 如果这个 `InnerInterceptor` 不需要配置属性也要加别名
     */
    @Override
    public void setProperties(Properties properties) {

        String dialectClass = properties.getProperty("dialect");
        if (StringUtil.isEmpty(dialectClass)) {
            dialectClass = default_dialect_class;
        }
        try {
            Class<?> aClass = Class.forName(dialectClass);
            dialect = (Dialect) aClass.newInstance();
        } catch (Exception e) {
            throw new PageException(e);
        }
        dialect.setProperties(properties);

        super.setProperties(properties);
    }

}
