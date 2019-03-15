package com.open.cloud.workflow.framework.drools.rules.rule1;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

/**
 * @Classname: HelloDrools
 * @Description:
 * @Author: leijian
 * @Date: 2019/3/13
 * @Version: 1.0
 */
public class HelloDrools {
    public static void main(String[] args) {
        KieServices kieServices = KieServices.Factory.get();
//        KieContainer kieContainer = kieServices.newKieClasspathContainer();
        KieContainer kieContainer = kieServices.getKieClasspathContainer();//kmodule.xml
        KieSession kieSession = kieContainer.newKieSession("helloWorldSession");
        //加入数据
        Message message = new Message();
        message.setId("123");
        message.setName("haha");
        kieSession.insert(message);
        //执行规则
        int i = kieSession.fireAllRules();//fire:火
        System.out.println("========" + i);
        kieSession.dispose();//处置，处理
    }

}
