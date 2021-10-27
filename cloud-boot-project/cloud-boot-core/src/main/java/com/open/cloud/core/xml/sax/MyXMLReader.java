package com.open.cloud.core.xml.sax;

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
            sp.parse(new InputSource("D:\\IdeaProjects\\open-cloud-platform\\cloud-boot-project\\cloud-boot-core\\src\\test\\data_10k.xml"));
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
