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
package state;

/**
 * @author shadow
 * @Date 2016年8月12日下午8:42:27
 * @Fun 状态(State)模式  行为模式
 * 既改变对象的状态，又改变对象的行为，在运行期 根据状态改变行为。
 **/
public class MainTest {
    /**
     * 本例的状态值只有两个，由状态类自身控制，也可以把状态值的控制，交由客户端来设置。
     */
    public static void main(String[] args) {
        WindowContext context = new WindowContext(new WindowState("窗口"));
        context.switchState();
        context.switchState();
        context.switchState();
    }
}
/**
 * 推荐博客：http://www.cnblogs.com/java-my-life/archive/2012/06/08/2538146.html
 */
