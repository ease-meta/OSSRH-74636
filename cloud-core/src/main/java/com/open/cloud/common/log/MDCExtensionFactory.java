package com.open.cloud.common.log;

import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.ServiceLoader;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author Leijian
 * @version 1.0
 */
public class MDCExtensionFactory {

    private static ConcurrentHashMap<String, MDCExtension> extensions = new ConcurrentHashMap<>();

    static {
        for (MDCExtension spanExtension : ServiceLoader.load(MDCExtension.class)) {
            String supportName = spanExtension.supportName();
            if (StringUtils.isNotEmpty(supportName)) {
                extensions.put(supportName, spanExtension);
            }
        }
    }

    public static void logStartedSpan(String supportName, HashMap<String, String> hashMap) {
        if ((!extensions.isEmpty()) && StringUtils.isNotEmpty(supportName) && extensions.containsKey(supportName)) {
            extensions.get(supportName).logStartedMDC(hashMap);
        }
    }

    public static void logStoppedSpan(String supportName, HashMap<String, String> hashMap) {
        if ((!extensions.isEmpty()) && StringUtils.isNotEmpty(supportName) && extensions.containsKey(supportName)) {
            extensions.get(supportName).logStoppedMDC(hashMap);
        }
    }
}
