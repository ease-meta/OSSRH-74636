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
package com.open.cloud.common.spi.java.guice;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class InjectorFactory {

    public static Injector getInjector() {
        return InjectorFactoryHolder.injector;
    }

    private static class InjectorFactoryHolder {

        private static Injector injector;

        static {
            injector = Guice.createInjector(new CloudModule());
        }

    }
}
