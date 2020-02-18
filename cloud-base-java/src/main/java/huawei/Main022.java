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

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author Leijian
 * @date 2020/2/13
 */
public class Main022 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            Map<Character, Integer> map = new LinkedHashMap<>();
            char[] arr = scanner.nextLine().toCharArray();
            for (int i = 0; i < arr.length; i++) {
                if (map.containsKey(arr[i]))
                    map.put(arr[i], map.get(arr[i]) + 1);
                else
                    map.put(arr[i], 1);
            }
            Collection<Integer> ci = map.values();
            int index = Collections.min(ci);
            StringBuilder sb = new StringBuilder("");
            for (char c : arr) {
                if (map.get(c) != index)
                    sb.append(c);
            }
            System.out.println(sb);
        }
    }
}
