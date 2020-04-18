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
package bridge;

/**
 * @author shadow
 * @Date 2016年8月4日下午7:47:27
 * @Fun 桥接（Bridge）模式，是结构性模式
 * <p>
 * 将抽象化与现实化脱藕，使得二者可以独立的变化，也就是说将他们之间的强关联变成弱关联，
 * 也就是指在一个软件系统的抽象化和实现化之间使用组合/聚合关系，而不是继承关系，从而使两者可以独立的改变。
 **/
public class MainTest {
	public static void main(String[] args) {
		BaseCellphone cellphone = new CellPhone();
		cellphone.setShell(new CellphoneShell());
		cellphone.mapping();
	}
}
/**
 * @Date 2016年8月20日19:07:14
 * <p>
 * 优点：1.分离接口极其实现。
 * 2.提高可扩充性。
 * 3.实现细节对客户透明。
 * 参考博客：http://blog.csdn.net/jason0539/article/details/22568865
 */

