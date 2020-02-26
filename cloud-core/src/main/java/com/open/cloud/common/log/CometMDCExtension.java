package com.open.cloud.common.log;


import org.slf4j.MDC;

import java.util.HashMap;

/**
 * @author Leijian
 * @version 1.0
 */
public class CometMDCExtension implements MDCExtension {

    @Override
    public void logStartedMDC(HashMap<String, String> hashMap) {
        if (null != hashMap && hashMap.size() > 0) {
            for (String key : hashMap.keySet()) {
                MDC.put(key, hashMap.get(key));
            }
        }
    }

    @Override
    public void logStoppedMDC(HashMap<String, String> hashMap) {
        if (null != hashMap && hashMap.size() > 0) {
            for (String key : hashMap.keySet()) {
                MDC.remove(key);
            }
        }
    }

    @Override
    public String supportName() {
        return MDCConstans.COMET.getMDCCode();
    }
}
