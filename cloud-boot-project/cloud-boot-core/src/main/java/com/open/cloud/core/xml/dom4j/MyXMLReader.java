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
package com.open.cloud.core.xml.dom4j;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.Iterator;

public class MyXMLReader {
    public static void main(String arge[]) {
        long lasting = System.currentTimeMillis();
        try {
            File f = new File("src/test/data_10k.xml");
            SAXReader reader = new SAXReader();
            Document doc = reader.read(f);
            Element root = doc.getRootElement();
            Element foo;
            for (Iterator i = root.elementIterator("VALUE"); i.hasNext(); ) {
                foo = (Element) i.next();
                System.out.print("车牌号码:" + foo.elementText("NO"));
                System.out.println("车主地址:" + foo.elementText("ADDR"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}