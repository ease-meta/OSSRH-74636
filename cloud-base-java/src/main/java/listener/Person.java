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
package listener;

//事件源
public class Person {
    //1.1首先定义一个私有的、空的监听器对象，用来接收传递进来的事件监听器(相当于一个全局变量)
    private PersonListener listener;

    //1.2将传递进来的事件监听器付给1.1中的listener
    public void registerListener(PersonListener personListener) {
        this.listener = personListener;
    }

    //1.3判断listener是否为null，如果不为空，则执行监听器中的方法,否则照常调用
    public void run() {
        if (listener != null) {
            Even even = new Even(this);
            this.listener.dorun(even);
        }
        System.out.println("人具有跑的方法");
    }

    //1.4和1.3一个道理
    public void eat() {
        if (listener != null) {
            Even even = new Even(this);
            this.listener.doeat(even);
        }
        System.out.println("人具有吃的方法");
    }
}