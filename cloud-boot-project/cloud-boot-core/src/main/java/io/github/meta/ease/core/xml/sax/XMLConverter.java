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
import io.github.meta.ease.core.xml.FormatHashMap;
import io.github.meta.ease.core.xml.XMLCommonData;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import javax.xml.parsers.SAXParserFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/*<sarray target="BODY.AUTH_ARRAY" source="Document.AdminInfo.AuthrtyInf" >
//data.get("Document.AdminInfo.AuthrtyInf").get(0).get("InitPtcpt")
<item target='InitPtcpt' source="InitPtcpt"/>
//data.get("Document.AdminInfo.AuthrtyInf").get(0).get("BizAuthInfo.SndBizAuthrtyInf.PrmsnInf")
<sarray target="SndBizAuthrtyInf" source="BizAuthInfo.SndBizAuthrtyInf.PrmsnInf" >
<item target='mt' source="MT"/>\

</sarray>
</sarray>
</sarray>*/

public class XMLConverter {
    public static void main(String[] args) {
        XMLConverter xmlConverter = new XMLConverter();
        CommonData commonData = new XMLCommonData();
        xmlConverter
                .load(
                        commonData,
                        new InputSource(
                                "D:\\IdeaProjects\\open-cloud-platform\\cloud-boot-project\\cloud-boot-core\\src\\test\\data_10k.xml"));
        System.out.println(commonData);

        Map data = new FormatHashMap();
        commonDatatoMap(commonData, data);
    }

    public static void commonDatatoMap(CommonData root, Map data) {
        CommonData realroot = root.getChild(0);
        Map documentData = new FormatHashMap();
        data.put(realroot.getName(), documentData);
        for (int i = 0; i < realroot.childCount(); i++) {

            CommonData commonData = realroot.getChild(i);

            addChild(commonData, documentData);
        }
    }

    private static void addChild(CommonData commonData, Map data) {

        int childCount = commonData.childCount();

        if (childCount != 0) {

            if (isArray(commonData)) {

                if (data.get(commonData.getName()) == null) {

                    List array = new ArrayList();

                    data.put(commonData.getName(), array);

                    addArray(commonData, array);
                } else {
                    addArray(commonData, (List) data.get(commonData.getName()));
                }
            } else {

                Map cd = new FormatHashMap();

                data.put(commonData.getName(), cd);

                for (int i = 0; i < childCount; i++) {

                    addChild(commonData.getChild(i), cd);
                }
            }
        } else {

            if (isArray(commonData)) {

                if (data.containsKey(commonData.getName())) {

                    List<Map> arr = (List<Map>) data.get(commonData.getName());
                    Map rec = new FormatHashMap();
                    rec.put(commonData.getName(),
                            commonData.getValue() == null ? "" : commonData.getValue());
                    arr.add(rec);

                } else {

                    List<Map> arr = new ArrayList<>();
                    Map rec = new FormatHashMap();
                    rec.put(commonData.getName(),
                            commonData.getValue() == null ? "" : commonData.getValue());
                    arr.add(rec);

                    data.put(commonData.getName(), arr);

                }

            } else if (commonData.attributeNames().hasNext()) {
                Map amountData = new FormatHashMap();

                //amountData.put("CCY", commonData.getAttribute(ISOConstants.ATTRIBUTE_CCY));
                //amountData.put("AMT", commonData.getValue() == null ? "" : commonData.getValue());

                data.put(commonData.getName(), amountData);
            } else {
                data.put(commonData.getName(),
                        commonData.getValue() == null ? "" : commonData.getValue());
            }
        }

    }

    private static void addArray(CommonData commonData, List<Map> array) {

        Map cd = new FormatHashMap();

        for (int i = 0; i < commonData.childCount(); i++) {

            addChild(commonData.getChild(i), cd);
        }
        array.add(cd);

    }

    private static boolean isArray(CommonData tag) {

        String array = "AuthrtyInf;PrmsnInf;MT;";

        String tagName = tag.getName();

        if (array.contains(".")) {

            String parName = tag.getParent() == null ? "" : tag.getParent().getName();

            return array.contains(parName + "." + tagName + ";");

        }

        return array.contains(tagName + ";");
    }

    private static SAXParserFactory factory = SAXParserFactory.newInstance();
    private XMLReader parser;
    private SaxHandler handler;

    public void load(CommonData root, InputSource in) {
        this.handler.setRoot(root);
        try {
            this.parser.parse(in);
        } catch (Exception var6) {
            System.out.println(var6);
            /* BaseException se = new BaseException("parse_xml_error");
             se.addScene("msg", "Parse xml error!");
             se.addScene("exception", var6);
             throw se;*/
        }
    }

    public XMLConverter() {
        try {
            this.parser = factory.newSAXParser().getXMLReader();
            this.handler = new SaxHandler();
            this.parser.setDTDHandler(this.handler);
            this.parser.setEntityResolver(this.handler);
            this.parser.setErrorHandler(this.handler);
            this.parser.setContentHandler(this.handler);
        } catch (Exception var3) {

        }
    }

    static {
        factory.setValidating(false);
    }
}
