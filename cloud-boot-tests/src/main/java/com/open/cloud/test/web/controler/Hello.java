package com.open.cloud.test.web.controler;

import com.open.cloud.test.web.module.Ease14006001In;
import com.open.cloud.test.web.module.Ease14006001Out;
import org.springframework.stereotype.Component;

/**
 * @author leijian
 * @version 1.0
 * @date 2021/10/16 13:10
 */
@Component
public class Hello {

    public Ease14006001Out sys(String str) {
        System.out.println(str);
        return new Ease14006001Out();
    }

    public Ease14006001Out sys(Ease14006001In ease14006001In) {
        return new Ease14006001Out();
    }
}
