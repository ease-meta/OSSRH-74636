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
package com.dcits.galaxy.dubbo.rpc.cluster.router.redis;

import com.alibaba.dubbo.common.utils.ConfigUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisPoolUtil {
    private JedisPoolUtil() {
    }

    public static volatile JedisPool jedisPool = null;

    public static JedisPool getJedisPoolInstance() {
        if (null == jedisPool) {
            synchronized (JedisPoolUtil.class) {
                if (null == jedisPool) {
                    JedisPoolConfig poolConfig = new JedisPoolConfig();
                    poolConfig.setTestOnBorrow(true);
                    String host = ConfigUtils.getProperty("redis.host");
                    int port = Integer.parseInt(ConfigUtils.getProperty("redis.port", "6379")
                        .trim());
                    jedisPool = new JedisPool(poolConfig, host, port);
                }
            }
        }
        return jedisPool;
    }

    public static void release(Jedis jedis) {
        if (null != jedis) {
            jedis.close();
        }
    }
}