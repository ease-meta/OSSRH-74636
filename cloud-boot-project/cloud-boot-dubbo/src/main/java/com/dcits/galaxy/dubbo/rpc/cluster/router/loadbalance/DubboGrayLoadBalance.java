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
package com.dcits.galaxy.dubbo.rpc.cluster.router.loadbalance;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.ExtensionLoader;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.RpcException;
import com.alibaba.dubbo.rpc.cluster.LoadBalance;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dcits.galaxy.dubbo.rpc.cluster.router.ServRule;
import com.dcits.galaxy.dubbo.rpc.cluster.router.redis.JedisPoolUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import static com.alibaba.dubbo.common.Constants.APPLICATION_KEY;
import static com.alibaba.dubbo.common.Constants.DEFAULT_LOADBALANCE;
import static com.dcits.galaxy.dubbo.rpc.cluster.router.Constant.Dubbo_Invoker_ProviderUrl_Field_Key;

/**
 * @author Leijian
 * @date 2020/4/7 9:23
 */
public class DubboGrayLoadBalance implements LoadBalance {

    public static final String  NAME   = "randomgray";

    private static final Logger logger = LoggerFactory.getLogger(DubboGrayLoadBalance.class);

    public DubboGrayLoadBalance() {
        logger.info("DubboGrayLoadBalance Init!");
    }

    @Override
    public <T> Invoker<T> select(List<Invoker<T>> invokers, URL url, Invocation invocation)
                                                                                           throws RpcException {
        Jedis jedis = null;
        LoadBalance defualtLoadBalance = ExtensionLoader.getExtensionLoader(LoadBalance.class)
            .getExtension(DEFAULT_LOADBALANCE);
        try {
            logger.info("use loadbalance {}", NAME);

            Field field = invokers.get(0).getClass()
                .getDeclaredField(Dubbo_Invoker_ProviderUrl_Field_Key);
            field.setAccessible(true);
            URL providerUrl = (URL) field.get(invokers.get(0));
            logger.info("providerUrl{}", providerUrl);
            String messagep = providerUrl.getPath();
            String application = providerUrl.getParameter(APPLICATION_KEY);
            logger.info("messagep{}", messagep);
            logger.info("application{}", application);
            Object[] args = invocation.getArguments();
            //暂时先用json解析。后续转为spel解析
            jedis = JedisPoolUtil.getJedisPoolInstance().getResource();
            int length = invokers.size(); // 总个数

            List<Invoker<T>> newInvokers = new ArrayList<>();
            //先匹配接口，在匹配地址
            boolean greey = false;
            ServRule servRule = null;
            //精确匹配
            if (jedis.exists(messagep) || jedis.exists("All") || jedis.exists("ALL")
                || jedis.exists("all") || jedis.exists("*")) {
                if (jedis.exists(messagep)) {
                    servRule = JSON.parseObject(jedis.get(messagep), ServRule.class);
                }
                if (servRule == null && jedis.exists("*")) {
                    servRule = JSON.parseObject(jedis.get(messagep), ServRule.class);
                }
                if (servRule == null && jedis.exists("all")) {
                    servRule = JSON.parseObject(jedis.get(messagep), ServRule.class);
                }
                if (servRule == null && jedis.exists("All")) {
                    servRule = JSON.parseObject(jedis.get("All"), ServRule.class);
                }
                if (servRule == null && jedis.exists("ALL")) {
                    servRule = JSON.parseObject(jedis.get("ALL"), ServRule.class);
                }
                if (servRule != null) {
                    greey = true;
                }
                if (!application.equalsIgnoreCase(servRule.getSerMtdEnm())) {
                    greey = false;
                }
                logger.info("servRule{}", servRule);
                logger.info("greey{}", greey);
            }
            //ALL匹配
            //规则再与应用ID匹配
            if (greey && servRule != null) {
                for (int j = 0; j < length; j++) {
                    if (invokers.get(j).isAvailable()) {
                        Set<String> set = jedis.keys("Greey:" + providerUrl.getHost() + "*");
                        logger.info("greey{}", set.size());
                        if (set.size() > 0) {
                            String bizzKey = servRule.getRouterColCdn();
                            String bizzKeyValue = servRule.getRouterCondVal();
                            String routerCondOper = servRule.getRouterCondOper();
                            Integer integer = servRule.getRouterArgsPos();
                            String keyLocl = "";
                            if (0 == integer) {
                                keyLocl = "sysHead";
                            }
                            if (1 == integer) {
                                keyLocl = "body";
                            }
                            JSONObject jsonObject = (JSON.parseArray(JSON.toJSONString(args[2])))
                                .getJSONObject(0).getJSONObject(keyLocl);
                            if (jsonObject != null && null != bizzKeyValue && null != bizzKey) {
                                String v = jsonObject.getString(bizzKey);
                                String[] strings = bizzKeyValue.split(",");
                                logger.info("v{}", v);
                                logger.info("strings{}", strings);
                                for (int i = 0; i < strings.length; i++) {
                                    if ("=".equalsIgnoreCase(routerCondOper)
                                        && strings[i].equals(v)) {
                                        newInvokers.add(invokers.get(j));
                                    }
                                    if ("!=".equalsIgnoreCase(routerCondOper)
                                        && !strings[i].equals(v)) {
                                        newInvokers.add(invokers.get(j));
                                    }
                                    //in包含
                                    if ("in".equalsIgnoreCase(routerCondOper) && null != v
                                        && v.contains(strings[i])) {
                                        newInvokers.add(invokers.get(j));
                                    }
                                    //前缀
                                    if ("pre".equalsIgnoreCase(routerCondOper) && null != v
                                        && v.startsWith(strings[i])) {
                                        newInvokers.add(invokers.get(j));
                                    }
                                }
                            }
                        }
                    }
                }
            }
            logger.info("greey{}", greey);
            if (greey) {
                logger.info("newInvokers{}", newInvokers.size());
                return defualtLoadBalance.select(newInvokers, url, invocation);
            } else {
                logger.info("invokers{}", invokers.size());
                return defualtLoadBalance.select(invokers, url, invocation);
            }
        } catch (Exception e) {
            logger.error("{},{}", e.getMessage(), e);
            return defualtLoadBalance.select(invokers, url, invocation);
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
    }

    private <T> List<Invoker<T>> getGrayInvoker(List<Invoker<T>> invokers, String applicationId,
                                                String applicationType, String bizzKey) {
        URL providerUrl;
        Iterator<Invoker<T>> iterator = invokers.iterator();
        List<Invoker<T>> newInvokers = new ArrayList<>();
        while (iterator.hasNext()) {
            Invoker<T> invoker = iterator.next();
            try {
                Field field = invoker.getClass().getDeclaredField(
                    Dubbo_Invoker_ProviderUrl_Field_Key);
                field.setAccessible(true);
                providerUrl = (URL) field.get(invoker);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                logger.error("获取providerUrl错误");
                newInvokers.add(invoker);
                continue;
            }
            if (applicationId.equals(providerUrl.getParameter(APPLICATION_KEY))) {
                newInvokers.add(invoker);
            }
        }
        if (newInvokers.isEmpty()) {
            throw new RpcException("当前无可用服务:applicationType-" + applicationType);
        }
        return newInvokers;
    }
}
