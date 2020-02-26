package com.open.cloud.common.log;


import com.open.cloud.common.utils.TracerUtils;
import org.slf4j.MDC;

import java.util.HashMap;

/**
 * @author Leijian
 * @version 1.0
 */
public class BatchMDCExtension implements MDCExtension {

    @Override
    public void logStartedMDC(HashMap<String, String> hashMap) {
        MDC.put(MDCConstans.BATCHTRACEID.getMDCCode(), TracerUtils.generate());
        MDC.put(MDCConstans.TRANMODE.getMDCCode(), MDCConstans.BATCH.getMDCCode());
        if (null != hashMap && hashMap.size() > 0) {
            for (String key : hashMap.keySet()) {
                MDC.put(key, hashMap.get(key));
            }
        }
    }

    @Override
    public void logStoppedMDC(HashMap<String, String> hashMap) {
        MDC.remove(MDCConstans.BATCHTRACEID.getMDCCode());
        MDC.remove(MDCConstans.TRANMODE.getMDCCode());
        if (null != hashMap && hashMap.size() > 0) {
            for (String key : hashMap.keySet()) {
                MDC.remove(key);
            }
        }
    }

    @Override
    public String supportName() {
        return MDCConstans.BATCH.getMDCCode();
    }
}
