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
package command;

/**
 * @author shadow
 * @Date 2016年8月7日下午8:48:11
 * @Fun  命令(Command)模式	 行为模式	请求与执行  分离
 * 		 可以多个命令接口的实现类，隐藏真实的被调用方法。
 **/
public class MainTest {
    public static void main(String[] args) {
        //真正的执行者
        Receiver receiver = new Receiver();
        //用于的隔离的命令
        ICommand command = new ConcreteCommand(receiver);
        //调用者
        Invoker invoker = new Invoker(command);
        invoker.invoke();
    }
}
/**
 * 参考博客：http://www.cnblogs.com/zhenyulu/articles/69858.html
 * */
