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
package facade;

/**
 * @author shadow
 * @Date 2016年8月4日下午9:49:14
 * @Fun  外观(Facade)模式
 * 		 简单的说就是降低了类与类之间的耦合度，使用一个Facade类来持有原有类的引用。它使用的频率其实非常的高，跟静态代理在实现上有些类似，
 * 		不同的是，外观模式中可以持有多个实体对象的引用，进行组合实现业务功能。
 **/
public class MainTest {
    public static void main(String[] args) {
        /**
         * 如果不使用外观模式，那么在Actor和Scene可能至少一方需要持有对方的引用
         * 当需要添加新的具体功能类时，只需要在Facade中添加一个引用，在相应的周期函数中使用即可。
         */
        Facade facade = new Facade();
        facade.startGame();
        System.out.println("-------");
        facade.endGame();
    }
}

/**
 * 推荐博客：http://blog.csdn.net/hguisu/article/details/7533759
 * */
