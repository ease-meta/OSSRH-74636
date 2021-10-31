package com.open.cloud.test.web.controler;

import com.open.cloud.flow.base.FlowExecutor;
import com.open.cloud.test.web.api.IEase14006001;
import com.open.cloud.test.web.module.Ease14006001In;
import com.open.cloud.test.web.module.Ease14006001Out;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author leijian
 * @version 1.0
 * @date 2021/10/1 18:10
 */
@RestController
@Service
public class Ease14006001 implements IEase14006001 {

    @Override
    @PostMapping("/ease/14006001/")
    public Ease14006001Out runService(@RequestBody Ease14006001In in) {

        return FlowExecutor.execute2Resp(in);
    }
}
