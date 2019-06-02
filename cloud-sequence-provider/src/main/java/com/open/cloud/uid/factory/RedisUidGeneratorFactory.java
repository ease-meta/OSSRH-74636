package com.open.cloud.uid.factory;


import com.open.cloud.uid.api.UidGenerator;
import com.open.cloud.uid.impl.RedisUidGenerator;
import com.open.cloud.uid.proxy.UidGeneratorProxy;

/**
 * @author leijian
 * @version 1.0
 * @date 2019/4/3 13:00
 **/
@Deprecated
public class RedisUidGeneratorFactory extends UidGeneratorFactory {

    private static RedisUidGeneratorFactory inst = null;

    private RedisUidGeneratorFactory() {
        UidGenerator uidGenerator = getUidGenerator(RedisUidGenerator.class);
        this.uidGeneratorProxy = new UidGeneratorProxy(uidGenerator);
    }


    public static RedisUidGeneratorFactory getInstance() {
        if (inst == null) {
            synchronized (RedisUidGeneratorFactory.class) {
                if (inst == null) {
                    inst = new RedisUidGeneratorFactory();
                }
            }
        }
        return inst;
    }


}

