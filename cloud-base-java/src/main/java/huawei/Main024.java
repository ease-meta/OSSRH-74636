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
 * @author Leijian
 * @date 2020/2/13
 */
public class Main024 {

    public static void main(String[] args) {
        char c1 = 'a';
        System.out.printf((int) c1 + "");
        Scanner s = new Scanner(System.in);
        while (s.hasNextLine()) {
            StringBuffer builder = new StringBuffer();
            String str = s.nextLine();
            for (int i = 0; i < 26; i++) {
                char c = (char) (i + 'A');
                for (int j = 0; j < str.length(); j++) {
                    char sc = str.charAt(j);
                    if (c == sc || c == sc - 32) {
                        builder.append(sc);
                    }
                }
            }
            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);
                if (!(c >= 'a' && c <= 'z') && !(c >= 'A' && c <= 'Z')) {
                    builder.insert(i, c);
                }
            }
            System.out.println(builder.toString());
        }

    }
}
