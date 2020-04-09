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
import com.dcits.galaxy.dubbo.rpc.cluster.router.GrayRule;
import com.dcits.galaxy.dubbo.rpc.cluster.router.ServRule;
import com.dcits.galaxy.dubbo.rpc.cluster.router.redis.JedisPoolUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
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
            Map<String, Map<String, String>> grayRouteRulesMap = new HashMap<>();
            Object[] args = invocation.getArguments();
            //暂时先用json解析。后续转为spel解析
            if (args == null) {
                return defualtLoadBalance.select(invokers, url, invocation);
            }
            jedis = JedisPoolUtil.getJedisPoolInstance().getResource();
            int length = invokers.size(); // 总个数
            boolean greey = false;
            List<Invoker<T>> newInvokers = new ArrayList<>();
            for (int i = 0; i < length; i++) {
                if (invokers.get(i).isAvailable()) {
                    Field field = invokers.get(i).getClass()
                        .getDeclaredField(Dubbo_Invoker_ProviderUrl_Field_Key);
                    field.setAccessible(true);
                    URL providerUrl = (URL) field.get(invokers.get(i));
                    Set<String> set = jedis.keys("Greey:" + providerUrl.getHost() + "*");
                    if (set.size() > 0) {
                        greey = true;
                        newInvokers.add(invokers.get(i));
                    }
                }
            }
            //灰度接口校验
            if (args.length == 3 && greey
                && (jedis.exists(url.getServiceInterface()) || jedis.exists("ALL"))) {
                //接口名：灰度条件--->应用
                ServRule servRule = JSON.parseObject(jedis.get(url.getServiceInterface()),
                    ServRule.class);
                String applicationId = servRule.getSerMtdEnm();
                String bizzKey = servRule.getRouterColCdn();
                String bizzKeyValue = servRule.getRouterCondVal();
                String applicationType = servRule.getAppSerClsnm();
                Integer integer = servRule.getRouterArgsPos();
                String key = "";
                if (0 == integer) {
                    key = "head";
                }
                if (1 == integer) {
                    key = "body";
                }
                if (key.trim().length() == 0) {
                    return defualtLoadBalance.select(invokers, url, invocation);
                }
                JSONObject jsonObject = (JSON.parseArray(JSON.toJSONString(args[2])))
                    .getJSONObject(0).getJSONObject("key");
                if (jsonObject != null && null != bizzKeyValue && null != bizzKey
                    && bizzKeyValue.equals(jsonObject.getString(bizzKey))) {
                    return defualtLoadBalance.select(newInvokers,
                    //this.getGrayInvoker(invokers, applicationId, applicationType, bizzKey),
                        url, invocation);
                }
                return defualtLoadBalance.select(invokers, url, invocation);
            } else {
                return defualtLoadBalance.select(invokers, url, invocation);
            }
        } catch (Exception e) {
            logger.error("{},{}", e.getMessage(), e);
            if (e instanceof JedisConnectionException) {
                return defualtLoadBalance.select(invokers, url, invocation);
            }
            throw new RpcException("RPC执行异常");
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
