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
package com.open.cloud.core.xml.jdom;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

import java.io.File;
import java.util.List;

public class MyXMLReader {
    public static void main(String arge[]) {
        long lasting = System.currentTimeMillis();
        try {
            SAXBuilder builder = new SAXBuilder();
            Document doc = builder.build(new File("src/test/data_10k.xml"));
            Element foo = doc.getRootElement();
            List allChildren = foo.getChildren();
            for (int i = 0; i < allChildren.size(); i++) {
                System.out.print("车牌号码:" + ((Element) allChildren.get(i)).getChild("NO").getText());
                System.out.println("车主地址:"
                        + ((Element) allChildren.get(i)).getChild("ADDR").getText());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
