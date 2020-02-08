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

/* 文件名 : Salary.java */
public class Salary extends Employee {
	private double salary; //Annual salary

	public Salary(String name, String address, int number, double
			salary) {
		super(name, address, number);
		System.out.println("子类");
		setSalary(salary);
	}

	public void mailCheck() {
		System.out.println("Within mailCheck of Salary class ");
		System.out.println("Mailing check to " + getName()
				+ " with salary " + salary);
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double newSalary) {
		if (newSalary >= 0.0) {
			salary = newSalary;
		}
	}

	public double computePay() {
		System.out.println("Computing salary pay for " + getName());
		return salary / 52;
	}
}