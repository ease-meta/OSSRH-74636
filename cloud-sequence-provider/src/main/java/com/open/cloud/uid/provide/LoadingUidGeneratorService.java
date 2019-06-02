package com.open.cloud.uid.provide;

import com.open.cloud.uid.factory.LoadingUidGeneratorFactory;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author leijian
 * @version 1.0
 * @date 2019/4/26 10:09
 **/
@Deprecated
public class LoadingUidGeneratorService {

    public Long getKeyBybiztag(@PathVariable(required = false) String biztag) {
        return LoadingUidGeneratorFactory.getInstance().getKey(biztag);
    }


}
