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
package observer;

/**
 * @author shadow
 * @Date 2016年8月12日下午8:02:58
 * @Fun 监听器 是观察者模式的一种实现
 * 一些需要监听的业务接口上添加 监听器，调用监听器的相应方法，实现监听。
 **/
public class User {
    public void register(IRegisterListener register) {
        /**
         * do ... register
         */
        System.out.println("正在注册中....");
        //注册后
        register.onRegistered();
    }

    public void login(ILoginListener login) {
        /**
         * do ... login
         */
        System.out.println("正在登录中....");
        //登录后
        login.onLogined();
    }
}
