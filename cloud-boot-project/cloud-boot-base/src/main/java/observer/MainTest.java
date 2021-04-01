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
 * @Date 2016年8月12日下午7:39:54
 * @Fun 观察者(Observer)模式，行为型模式<br/>
 * 观察者模式定义了一种一对多的依赖关系，让多个观察者对象同时观察某一个目标对象。<br/>
 * 这个目标对象在状态上发生变化时，会通知所有观察者对象，让它们能够自动更新自己目标对象中需要添加、移除、通知 观察者的接口。<br/>
 **/
public class MainTest {
    public static void main(String[] args) {
        /**
         * 使用Java自带的Observer接口和Observable类
         */
        UpdateObserver observer = new UpdateObserver();
        UpdateObservable observable = new UpdateObservable(observer);
        observable.setData(3);
        observable.setData(9);
        System.out.println();
        System.out.println();

        /**
         * 自定义的观察者模型
         */
        IWathedSubject watched = new UpdateWatchedSubject();
        watched.add(new UpdateWatcher());
        watched.add(new UpdateWatcher());
        watched.add(new UpdateWatcher());
        watched.update();
        System.out.println();

        /**
         * 子模式-监听器
         */
        User user = new User();
        user.register(new IRegisterListener() {

            @Override
            public void onRegistered() {
                // TODO Auto-generated method stub
                System.out.println("监听到注册后...");
            }
        });

        user.login(new ILoginListener() {

            @Override
            public void onLogined() {
                // TODO Auto-generated method stub
                System.out.println("监听到登录后...");
            }
        });
    }
}
/**
 * 推荐博客：http://blog.csdn.net/ai92/article/details/375691
 */
