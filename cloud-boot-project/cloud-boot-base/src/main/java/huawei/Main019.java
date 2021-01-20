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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Leijian
 * @date 2020/2/13
 */
public class Main019 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while (sc.hasNext()) {
			String str = sc.nextLine();
			if (isLengthQualified(str) && isContentQualified(str) && !hasDuplicatedString(str))
				System.out.println("OK");
			else
				System.out.println("NG");
		}
		sc.close();
	}

	public static boolean isLengthQualified(String s) {
		return s.length() > 8;
	}

	public static boolean isContentQualified(String s) {
		int count = 0;
		String[] str = {"[a-z]", "[A-Z]", "[0-9]", "[^a-zA-Z0-9]"};
		for (int i = 0; i < str.length; i++) {
			Pattern p = Pattern.compile(str[i]);
			Matcher m = p.matcher(s);
			if (m.find())
				count++;
		}
		return count >= 3;

	}

	public static boolean hasDuplicatedString(String s) {
		for (int i = 0; i < s.length() - 3; i++) {
			if (s.substring(i + 3).contains(s.substring(i, i + 3)))
				return true;
		}
		return false;
	}
}
