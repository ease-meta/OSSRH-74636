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
package com.leijian.moc;

import absfactory.IFactory;
import absfactory.IProduct1;
import absfactory.IProduct2;

public class Factory implements IFactory {

    @Override
    public SubClass1 createSubClass1() {
        return new SubClass1();
    }

    @Override
    public SubClass2 createSubClass2() {
        return new SubClass2();
    }

    @Override
    public IProduct1 createProduct1A() {
        return null;
    }

    @Override
    public IProduct1 createProduct1B() {
        return null;
    }

    @Override
    public IProduct2 createProduct2A() {
        return null;
    }

    @Override
    public IProduct2 createProduct2B() {
        return null;
    }
}
