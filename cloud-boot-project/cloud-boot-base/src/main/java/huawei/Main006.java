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

import java.util.Scanner;

/**
 * 功能:输入一个正整数，按照从小到大的顺序输出它的所有质数的因子（如180的质数因子为2 2 3 3 5 ）
 * <p>
 * 最后一个数后面也要有空格
 *
 * @author Leijian
 * @date 2020/2/11
 */
public class Main006 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		while (scanner.hasNextLine()) {
			String str = scanner.nextLine();
			long num = Long.parseLong(str);
			System.out.println(getResult(num));
		}
	}

	public static String getResult(long ulDataInput) {
		StringBuilder stringBuilder = new StringBuilder();
		int index = 2;
		while (index <= ulDataInput) {
			if (ulDataInput % index == 0) {
				if (index == ulDataInput) {
					stringBuilder.append(index).append(" ");
					break;
				} else {
					stringBuilder.append(index).append(" ");
					ulDataInput = ulDataInput / index;
				}
			} else {
				index++;
			}
		}
		return stringBuilder.toString();
	}
}
