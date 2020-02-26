package com.open.cloud.common.log;


import java.util.HashMap;

/**
 * @author Leijian
 * @version 1.0
 */
public interface MDCExtension {

    void logStartedMDC(HashMap<String, String> hashMap);

    void logStoppedMDC(HashMap<String, String> hashMap);

    String supportName();
}
