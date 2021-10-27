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
            for (int i = 0; i < allChildren.size();
                 i++) {
                System.out.print("车牌号码:" + ((Element) allChildren.get(i)).getChild("NO").getText());
                System.out.println("车主地址:" + ((Element) allChildren.get(i)).getChild("ADDR").getText());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
