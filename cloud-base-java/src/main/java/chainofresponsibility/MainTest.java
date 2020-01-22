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
package chainofresponsibility;

/**
 * @author shadow
 * @Date 2016年8月7日下午8:06:44
 * @Fun  责任链(Chain of Responsibility)模式	行为模式
 * 		 将责任一级一级传递给后继者，直到某一个后继者处理了行为表现在：责任的传递，需要是一个链一个。
 **/
public class MainTest {
    public static void main(String[] args) {
        //权限过滤
        IFilter filter1 = new ConcreteFilter("permission-filter");

        //后缀过滤
        IFilter filter2 = new ConcreteFilter("suffix-filter");

        //风格过滤
        IFilter filter3 = new ConcreteFilter("style-filter");

        //美女过滤
        IFilter filter4 = new ConcreteFilter("beauty-filter");
        filter1.setSuccessor(filter2);
        filter2.setSuccessor(filter3);
        filter3.setSuccessor(filter4);
        System.out.println("----以下是每一个处理者（包括后继者）都处理了，顺序也是一级一级的传递----");
        filter1.handleFilter();

        System.out.println("---以下是交由最后一个后继者处理----");
        filter1.handleFilter2();
    }
}
