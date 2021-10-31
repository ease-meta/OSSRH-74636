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
package io.github.meta.ease.logging;

import io.github.meta.ease.core.util.NewInstanceServiceLoader;

import java.util.Collection;

/**
 * 日志服务.enable=true
 * <p>
 * #级别定义
 * 日志服务.业务日志.日志级别定义[0].id=1
 * 日志服务.业务日志.日志级别定义[0].类型=error
 * <p>
 * 日志服务.业务日志.日志级别定义[1].id=2
 * 日志服务.业务日志.日志级别定义[1].类型=error,profile
 * <p>
 * 日志服务.业务日志.日志级别定义[2].id=3
 * 日志服务.业务日志.日志级别定义[2].类型=parm,method,debug,warn,info,error,profile,sql
 * <p>
 * 日志服务.业务日志.日志级别定义[3].id=4
 * 日志服务.业务日志.日志级别定义[3].类型=info
 * <p>
 * 日志服务.业务日志.日志级别定义[4].id=5
 * 日志服务.业务日志.日志级别定义[4].类型=info,profile
 * <p>
 * 日志服务.业务日志.日志级别定义[5].id=6
 * 日志服务.业务日志.日志级别定义[5].类型=info,method
 * <p>
 * 日志服务.业务日志.日志级别定义[6].id=7
 * 日志服务.业务日志.日志级别定义[6].类型=info,method,profile
 * <p>
 * 日志服务.业务日志.日志级别定义[7].id=8
 * 日志服务.业务日志.日志级别定义[7].类型=parm,method,debug,info,sql
 * <p>
 * 日志服务.业务日志.日志级别定义[8].id=9
 * 日志服务.业务日志.日志级别定义[8].类型=parm,method,debug,info,profile,sql
 *
 * @author Leijian
 * @date 2020 /11/
 */
public class BizLoggerFactory {

    private static ILogFactory defaultLogFactory = null;

    private static Collection<ILogFactory> extensions = NewInstanceServiceLoader
            .newServiceInstances(ILogFactory.class);

    static {
        NewInstanceServiceLoader.register(ILogFactory.class);
        extensions = NewInstanceServiceLoader.newServiceInstances(ILogFactory.class);
    }

    static {
        init();
    }

    private BizLoggerFactory() {

    }

    private static void init() {
        if (defaultLogFactory == null) {
            try {
                defaultLogFactory = extensions.stream().findFirst().orElseThrow(() -> new IllegalArgumentException("Did not find type ILogFactory!"));
            } catch (Exception e) {
                //defaultLogFactory = new JdkLogFactory();
            }
        }
    }

    /**
     * Gets logger.
     *
     * @param clazz the clazz
     * @return the logger
     */
    public static BizLogger getLogger(Class<?> clazz) {
        return defaultLogFactory.getLog(clazz);
    }

    /**
     * Gets logger.
     *
     * @param name the name
     * @return the logger
     */
    public static BizLogger getLogger(String name) {
        return defaultLogFactory.getLog(name);
    }
}
