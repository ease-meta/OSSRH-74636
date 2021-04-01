package com.learn.service;

import com.learn.util.KieSessionType;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.StatelessKieSession;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Description: .
 *
 * @author : ys.
 * @date :
 **/
@Service
public class KieContainerService {

    private KieContainer kieContainer;
    private Map<String, StatelessKieSession> kieSessionMap = new HashMap<>();

    public void initKieContainer() {
        if (this.kieContainer == null) {
            KieServices kieServices = KieServices.Factory.get();
            kieContainer = kieServices.getKieClasspathContainer();
            initilize();
        }
    }

    private void initilize() {
        for (KieSessionType type : KieSessionType.values()) {
            if (type != KieSessionType.OTHER) {
                kieSessionMap.put(type.desc(), kieContainer.newStatelessKieSession(type.desc()));
            }
        }
    }

    public void updateKieSession(StatelessKieSession session, KieSessionType type) {
        kieSessionMap.put(type.desc(), session);
    }

    public StatelessKieSession getKieSession(KieSessionType type) {
        initKieContainer();
        return kieSessionMap.get(type.desc());
    }

}
