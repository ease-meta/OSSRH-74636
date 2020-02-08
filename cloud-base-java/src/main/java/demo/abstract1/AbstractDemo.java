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
package demo.abstract1;

/* 文件名 : AbstractDemo.java */
public class AbstractDemo {
	public static void main(String[] args) {
		/* 以下是不允许的，会引发错误 */
		//Employee e = new Employee("George W.", "Houston, TX", 43);

		System.out.println("\n Call mailCheck using Employee reference--");
		//e.mailCheck();


		Salary s = new Salary("Mohd Mohtashim", "Ambehta, UP", 3, 3600.00);
		Employee e = new Salary("John Adams", "Boston, MA", 2, 2400.00);

		System.out.println("Call mailCheck using Salary reference --");
		s.mailCheck();

		System.out.println("\n Call mailCheck using Employee reference--");
		e.mailCheck();
	}
}
