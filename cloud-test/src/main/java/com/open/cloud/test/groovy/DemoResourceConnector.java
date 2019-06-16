package com.open.cloud.test.groovy;

import groovy.util.ResourceConnector;
import groovy.util.ResourceException;

import java.net.URLConnection;

/**
 * @author leijian
 * @version 1.0
 * @date 2019/6/16 9:56
 **/
public class DemoResourceConnector implements ResourceConnector {
    @Override
    public URLConnection getResourceConnection(String name) throws ResourceException {
        return null;
    }
}
