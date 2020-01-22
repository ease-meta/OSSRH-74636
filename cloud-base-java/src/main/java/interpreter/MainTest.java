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
package interpreter;

/**
 * @author shadow
 * @Date 2016年8月7日下午9:02:19
 * @Fun	 解释器(Interpreter)模式
 * 		给定一门语言，定义它的文法的一种表示，并定义一个解释器，该解释器使用该表示来解释语言中句子。		行为型模式
 * 		应用场合，如编译器，正则表达式，语言规范。。。
 * 		解释器模式在实际的系统开发中使用的非常少，因为它会引起效率、性能以及维护等问题。
 **/
public class MainTest {
    public static void main(String[] args) {
        Context context = new Context();
        context.setData("一段XML数据");
        new XmlDomInterpreter().interpreter(context);
        new XmlDomInterpreter().interpreter(context);
    }
}
/**
 * 推荐博客：http://blog.csdn.net/zhengzhb/article/details/7666020
 */
