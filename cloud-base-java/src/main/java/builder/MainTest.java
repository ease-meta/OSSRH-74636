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
package builder;

import absfactory.MazeFactory;

/**
 * 建造者(Builder)模式是创建型模式，创建了一个对外隐藏创建过程的产品，使用组合的方式，由指挥者(Director)来决定建造的流程。
 * 
 * 建造者模式：将各种产品集中起来进行管理，用来创建复合对象。
 * 
 * 将一个复杂对象的构造与它的表示分离，使同样的构建过程可以创建不同的表示，这样的设计模式被称为建造者模式。
 *	
 * @author shadow E-mail:zyydqpi@163.com
 * @Date 2016年8月20日下午7:16:44
 * @version 1.0
 * @since 
 **/
public class MainTest {
    public static void main(String[] args) {
        MazeFactory factory = new MazeFactory();
        MazeBuilder builder = new StandardMazeBuilder(factory);
        Director director = new Director(builder);
        director.construct();
        System.out.println(builder.currentMaze.getRoomCount());
    }
}

/**
 * 参考博客：http://blog.csdn.net/jason0539/article/details/44992733
 * */
