package com.open.cloud.common.log;

import org.slf4j.MDC;

import java.util.HashMap;


/**
 * @author Leijian
 * @version 1.0
 */
public class TimerMDCExtension implements MDCExtension {

    @Override
    public void logStartedMDC(HashMap<String, String> hashMap) {
        MDC.put(MDCConstans.TRANMODE.getMDCCode(), MDCConstans.TIMER.getMDCCode());
    }

    @Override
    public void logStoppedMDC(HashMap<String, String> hashMap) {
        MDC.remove(MDCConstans.TRANMODE.getMDCCode());
    }

    @Override
    public String supportName() {
        return MDCConstans.TIMER.getMDCCode();
    }
}
