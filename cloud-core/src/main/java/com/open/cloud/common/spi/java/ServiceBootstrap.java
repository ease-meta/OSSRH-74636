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
package com.open.cloud.common.spi.java;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.ServiceLoader;

/**
 * @author Leijian
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ServiceBootstrap {

    private static final Map<Class, Collection> SERVICE_MAP = new HashMap<>();

    private static final Object                 lock        = new Object();

    private static <T> void register(final Class<T> service) {
        if (!SERVICE_MAP.containsKey(service)) {
            synchronized (lock) {
                if (SERVICE_MAP.containsKey(service)) {
                    return;
                }
            }
        }
        for (T each : ServiceLoader.load(service)) {
            registerServiceClass(service, each);
        }
    }

    @SuppressWarnings("unchecked")
    private static <T> void registerServiceClass(final Class<T> service, final T instance) {
        Collection<T> serviceClasses = SERVICE_MAP.get(service);
        if (null == serviceClasses) {
            serviceClasses = new LinkedHashSet<>();
        }
        serviceClasses.add(instance);
        SERVICE_MAP.put(service, serviceClasses);
    }

    @SneakyThrows
    @SuppressWarnings("unchecked")
    private static <T> Collection<T> loadAll(final Class<T> service) {
        register(service);
        Collection<T> result = new LinkedList<>();
        if (null == SERVICE_MAP.get(service)) {
            return result;
        } else {
            return SERVICE_MAP.get(service);
        }

    }

    public static <T> T loadFirst(final Class<T> service) {
        Iterator<T> iterator = loadAll(service).iterator();
        if (!iterator.hasNext()) {
            throw new IllegalStateException(
                String
                    .format(
                        "No implementation defined in /META-INF/services/%s, please check whether the file exists and has the right implementation class!",
                        service.getName()));
        }
        return iterator.next();
    }
}
