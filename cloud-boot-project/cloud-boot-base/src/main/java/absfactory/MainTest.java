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
package absfactory;

/**
 * @author shadow
 * @Date 2016年8月18日下午7:08:24
 * @Fun 抽象工厂模式：为创建一组相关或者相互依赖的对象提供一个接口，而且无需指定他们的具体类。 创建型设计模式<br/>
 * 流程：抽象工厂，实体工厂，生产产品<br/>
 * 抽象产品，实体产品，实现产品功能<br/>
 * 缺点：当需要添加一个产品（由抽象和实体构成），工厂都需要修改。<br/>
 **/
public class MainTest {
    public static void main(String[] args) {
        IFactory factory = new Factory();
        IProduct1 product1 = factory.createProduct1A();
        product1.show();

        IProduct1 product12 = factory.createProduct1B();
        product12.show();

        IProduct2 product2 = factory.createProduct2A();
        product2.show();

        IProduct2 product22 = factory.createProduct2B();
        product22.show();
    }
}
/**
 * 参考博客：http://blog.csdn.net/zhengzhb/article/details/7359385
 * <p>
 * 适用情况：
 * 1.系统中有多个产品族，而系统一次只可能消费其中一族产品。
 * 2.同属于同一个产品族的产品以其使用。
 * <p>
 * 适用情况：
 * 1.系统中有多个产品族，而系统一次只可能消费其中一族产品。
 * 2.同属于同一个产品族的产品以其使用。
 * <p>
 * 适用情况：
 * 1.系统中有多个产品族，而系统一次只可能消费其中一族产品。
 * 2.同属于同一个产品族的产品以其使用。
 **/
/**
 * 适用情况：
 *  1.系统中有多个产品族，而系统一次只可能消费其中一族产品。
 *  2.同属于同一个产品族的产品以其使用。
 * **/
