package com.open.cloud.core.xml.sax;

import com.open.cloud.core.xml.CommonData;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

public class SaxHandler
        extends DefaultHandler {
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

    public void endElement(String uri, String localname, String qName)
            throws SAXException {
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

    public void error(SAXParseException e)
            throws SAXException {
        printException(e);
    }

    public void fatalError(SAXParseException e)
            throws SAXException {
        printException(e);
    }

    public void warning(SAXParseException e)
            throws SAXException {
        printException(e);
    }

    private void printException(SAXParseException e) {
        System.out.println(e);

        System.out.println("Line:" + e.getLineNumber());

        System.out.println("Column:" + e.getColumnNumber());
    }
}
