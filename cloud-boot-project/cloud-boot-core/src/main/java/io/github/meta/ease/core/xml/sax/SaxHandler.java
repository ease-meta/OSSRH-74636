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

import io.github.meta.ease.core.xml.CommonData;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

public class SaxHandler extends DefaultHandler {
    private CommonData root;
    private CommonData current;

    public void setRoot(CommonData root) {
        this.root = root;

        this.current = root;
    }

    public void characters(char[] value, int start, int length) {
        if (length > 0) {
            String s = new String(value, start, length);
            if (null != this.current.getValue()) {
                this.current.setValue(this.current.getValue() + s);
            } else {
                this.current.setValue(s);
            }
        }
    }

    public void endElement(String uri, String localname, String qName) throws SAXException {
        this.current = this.current.getParent();
    }

    public void startElement(String uri, String localname, String qName, Attributes attr)
            throws SAXException {
        this.current = this.current.addChild(qName);

        int i = 0;
        for (int len = attr.getLength(); i < len; i++) {
            this.current.setAttribute(attr.getQName(i), attr.getValue(i));
        }
    }

    public void error(SAXParseException e) throws SAXException {
        printException(e);
    }

    public void fatalError(SAXParseException e) throws SAXException {
        printException(e);
    }

    public void warning(SAXParseException e) throws SAXException {
        printException(e);
    }

    private void printException(SAXParseException e) {
        System.out.println(e);

        System.out.println("Line:" + e.getLineNumber());

        System.out.println("Column:" + e.getColumnNumber());
    }
}
