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
 * 开发一个坐标计算工具， A表示向左移动，D表示向右移动，W表示向上移动，S表示向下移动。从（0,0）点开始移动，从输入字符串里面读取一些坐标，并将最终输入结果输出到输出文件里面。
 *
 * @author Leijian
 * @date 2020/2/12
 */
public class Main016 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		while (scan.hasNextLine()) {
			int x = 0;
			int y = 0;
			String[] tokens = scan.nextLine().split(";");
			for (int i = 0; i < tokens.length; i++) {
				try {
					int b = Integer.parseInt(tokens[i].substring(1, tokens[i].length()));
					if (tokens[i].charAt(0) == 'A') {
						x -= b;
					}
					if (tokens[i].charAt(0) == 'W') {
						y += b;
					}
					if (tokens[i].charAt(0) == 'S') {
						y -= b;
					}
					if (tokens[i].charAt(0) == 'D') {
						x += b;
					}
				} catch (Exception e) {
					continue;
				}
			}
			System.out.println(x + "," + y);

		}
	}
}
