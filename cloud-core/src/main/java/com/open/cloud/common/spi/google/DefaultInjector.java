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
package com.open.cloud.common.spi.google;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.open.cloud.common.exception.BaseException;

/**
 * @author Leijian
 */
public class DefaultInjector implements Injector {

    private com.google.inject.Injector m_injector;

    public DefaultInjector() {
        try {
            m_injector = Guice.createInjector(new CloudModule());
        } catch (Throwable ex) {
            throw new BaseException(ex);
        }
    }

    @Override
    public <T> T getInstance(Class<T> clazz) {
        try {
            return m_injector.getInstance(clazz);
        } catch (Throwable ex) {
            throw new BaseException(String.format("Unable to load instance for %s!",
                clazz.getName()), ex);
        }
    }

    @Override
    public <T> T getInstance(Class<T> clazz, String name) {
        //Guice does not support get instance by type and name
        return null;
    }

    private static class CloudModule extends AbstractModule {
        @Override
        protected void configure() {

        }
    }
}
