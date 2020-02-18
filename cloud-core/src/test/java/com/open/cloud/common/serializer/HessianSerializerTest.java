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
package com.open.cloud.common.serializer;

import org.junit.jupiter.api.Test;

import java.io.IOException;

class HessianSerializerTest {

    @Test
    void name() {
    }

    @Test
    void serialize() throws IOException {
        HessianSerializer hessianSerializer = new HessianSerializer();
        Person person1 = new Person("张三", 23, false);
        byte[] bytes = hessianSerializer.serialize(person1);
        System.out.println((Person) hessianSerializer.deserialize(bytes, null));
    }

    @Test
    void deserialize() {
    }
}