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
package io.github.meta.ease.core.xml.dom;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class MyXMLReader {
    public static void main(String arge[]) {
        long lasting = System.currentTimeMillis();
        try {
            File f = new File("src/test/data_10k.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(f);
            NodeList nl = doc.getElementsByTagName("VALUE");
            for (int i = 0; i < nl.getLength(); i++) {
                System.out.print("车牌号码:"
                        + doc.getElementsByTagName("NO").item(i).getFirstChild()
                        .getNodeValue());
                System.out.println("车主地址:"
                        + doc.getElementsByTagName("ADDR").item(i).getFirstChild()
                        .getNodeValue());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
