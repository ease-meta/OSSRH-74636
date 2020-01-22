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

public class TestListener {

    public static void main(String[] args) {
        Person person = new Person();
        person.registerListener(new MyPersonListener());
        person.run();
        person.eat();
    }
}

//实现监听器接口中的方法
class MyPersonListener implements PersonListener {

    @Override
    public void dorun(Even even) {
        //拿到事件源
        Person person = even.getPerson();
        System.out.println("人在跑之前执行的动作");
    }

    @Override
    public void doeat(Even even) {
        System.out.println("人在吃之前执行的动作");
    }

}