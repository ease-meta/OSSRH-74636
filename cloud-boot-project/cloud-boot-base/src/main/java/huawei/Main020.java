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
public class Main020 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String str = sc.nextLine();
            String ss = "";
            int i = 0;
            for (i = 0; i < str.length(); i++) {
                char ch = str.charAt(i);
                if (ch >= '0' && ch <= '9')
                    ss = ss + ch;
                if (ch >= 'a' && ch <= 'z') {
                    if (ch >= 'a' && ch <= 'c')
                        ss = ss + "2";
                    if (ch >= 'd' && ch <= 'f')
                        ss = ss + "3";
                    if (ch >= 'g' && ch <= 'i')
                        ss = ss + "4";
                    if (ch >= 'j' && ch <= 'l')
                        ss = ss + "5";
                    if (ch >= 'm' && ch <= 'o')
                        ss = ss + "6";
                    if (ch >= 'p' && ch <= 's')
                        ss = ss + "7";
                    if (ch >= 't' && ch <= 'v')
                        ss = ss + "8";
                    if (ch >= 'w' && ch <= 'z')
                        ss = ss + "9";
                    if (ch == '1')
                        ss = ss + "1";
                    if (ch == '0')
                        ss = ss + "0";
                }
                if (ch >= 'A' && ch <= 'Z') {
                    char cc = Character.toLowerCase(ch);
                    if (cc == 'z')
                        ss = ss + "a";
                    else {
                        ss = ss + (char) (Integer.valueOf(cc) + 1);
                    }
                }
            }
            System.out.println(ss);
        }
        sc.close();
    }
}
