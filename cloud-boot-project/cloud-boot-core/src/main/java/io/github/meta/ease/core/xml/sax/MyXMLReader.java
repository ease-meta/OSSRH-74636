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
package io.github.meta.ease.core.xml.sax;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParserFactory;

public class MyXMLReader extends DefaultHandler {
    java.util.Stack tags = new java.util.Stack();

    public MyXMLReader() {
        super();
    }

    public static void main(String args[]) {
        long lasting = System.currentTimeMillis();
        try {
            SAXParserFactory sf = SAXParserFactory.newInstance();
            XMLReader sp = sf.newSAXParser().getXMLReader();
            MyXMLReader reader = new MyXMLReader();
            sp.setContentHandler(reader);
            sp.parse(new InputSource(
                    "D:\\IdeaProjects\\open-cloud-platform\\cloud-boot-project\\cloud-boot-core\\src\\test\\data_10k.xml"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("运行时间：" + (System.currentTimeMillis() - lasting) + "毫秒");
    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
        String tag = (String) tags.peek();
        if (tag.equals("NO")) {
            System.out.print("车牌号码：" + new String(ch, start, length));
        }
        if (tag.equals("ADDR")) {
            System.out.println("地址:" + new String(ch, start, length));
        }
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attrs) {
        tags.push(qName);
    }
}
