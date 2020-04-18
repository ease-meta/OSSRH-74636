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
package huawei;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * 给定n个字符串，请对n个字符串按照字典序排列。
 *
 * @author Leijian
 * @date 2020/2/12
 */
public class Main013 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int num = Integer.parseInt(scanner.nextLine());
		LinkedList linkedList = new LinkedList();
		for (int i = 0; i < num; i++) {
			linkedList.add(scanner.nextLine());
		}
		linkedList.stream().sorted().forEach(v -> System.out.println(v));
	}
}
