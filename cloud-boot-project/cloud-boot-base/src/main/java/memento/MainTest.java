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
package memento;

/**
 * @author shadow
 * @Date 2016年8月8日下午8:26:30
 * @Fun 备忘录(Memento)模式   行为模式
 * 流程：Memento用于保存 数据状态
 * Originator用于加载数据，建立Memento对象，及通过Memento恢复原始数据
 **/
public class MainTest {
    public static void main(String[] args) {
        //创建数据对象
        DataState state = new DataState();
        state.setAction("copy a character");
        //
        Originator originator = new Originator();
        System.out.println("创建原始数据");
        originator.setState(state);
        Memento memento = originator.getMemento();
        System.out.println(memento.getState());

        System.out.println("创建备忘录对象，保存原始数据状态");
        originator.setState(new DataState());

        System.out.println("创建了一个新数据");
        originator.setState(new DataState());

        System.out.println("创建新数据后：" + originator.getState().getAction());

        /**
         * memento 需要保存在某地，需要时取出，以恢复它内部所保存的数据
         */
        System.out.println("创建新数据后，恢复原数据");
        originator.setMemento(memento);
        System.out.println(originator.getState().getAction());
    }
}

/**
 * 推荐博客：http://blog.csdn.net/zhengzhb/article/details/7697549
 */
