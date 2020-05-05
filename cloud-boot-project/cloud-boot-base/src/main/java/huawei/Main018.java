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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Leijian
 * @date 2020/2/13
 */
public class Main018 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String messageLine = null;
        //使用有序的LinkedHashMap存储信息
        Map<String, Integer> map = new LinkedHashMap<String, Integer>();
        //循环读入数据
        while ((messageLine = bufferedReader.readLine()) != null) {
            String[] error_Message = messageLine.split(" ");
            String error = error_Message[0];
            String line_no = error_Message[1];
            //取文件名
            String file_Name = error.substring(error.lastIndexOf("\\") + 1);
            //处理长度超过16的情况
            if (file_Name.length() > 16) {
                file_Name = file_Name.substring(file_Name.length() - 16);
            }

            //将错误信息添加到map中
            String error_Name = file_Name + " " + line_no;
            if (map.containsKey(error_Name)) {
                map.put(error_Name, map.get(error_Name) + 1);
            } else {
                map.put(error_Name, 1);
            }
        }

        //输出错误信息,最多8条(后八条)
        int count = 0;
        for (String key : map.keySet()) {
            count++;
            if (count > (map.size() - 8))
                System.out.println(key + " " + map.get(key));
        }
    }
}
