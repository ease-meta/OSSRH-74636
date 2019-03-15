package com.open.cloud.workflow.framework.idgenerator;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Classname: KeyGenerateFactory
 * @Description: 业务流水号生成
 * @Author: leijian
 * @Date: 2019/1/21
 * @Version: 1.0Environment
 */
public class KeyGenerateFactory {
    private static final KeyGenerateFactory inst = new KeyGenerateFactory();
    private Map<String, com.open.cloud.workflow.framework.idgenerator.TimeSequenceGenerator> keys = new ConcurrentHashMap<>();

    private KeyGenerateFactory() {

    }

    public static KeyGenerateFactory getInstance() {
        return inst;
    }

    public synchronized String getKey(String name) {
        com.open.cloud.workflow.framework.idgenerator.TimeSequenceGenerator key = keys.get(name);
        if (key == null) {
            key = new com.open.cloud.workflow.framework.idgenerator.TimeSequenceGenerator(name);
            keys.put(name, key);
        }
        return key.nextId();
    }

    public synchronized String getKey() {
        return getKey("default");
    }
}

