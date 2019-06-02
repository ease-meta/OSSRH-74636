package com.open.cloud.uid.provide;


import com.open.cloud.uid.factory.SnowflakeUidGeneratorFactory;

@Deprecated
public class DefaultUidGeneratorService {

    public Long getKey() {
        return SnowflakeUidGeneratorFactory.getInstance().getKey();
    }

}
