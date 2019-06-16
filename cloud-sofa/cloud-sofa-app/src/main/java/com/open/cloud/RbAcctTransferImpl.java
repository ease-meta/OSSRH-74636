package com.open.cloud;

import com.alipay.sofa.runtime.api.annotation.SofaService;
import com.alipay.sofa.runtime.api.annotation.SofaServiceBinding;
import com.open.cloud.api.IRbAcctTransfer;
import com.open.cloud.api.model.HeadOut;
import com.open.cloud.api.model.RbAcctTransferIn;
import com.open.cloud.api.model.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@SofaService(bindings = {@SofaServiceBinding(bindingType = "bolt")})
public class RbAcctTransferImpl implements IRbAcctTransfer {


    @Override
    public HeadOut rbAcctTransfer(RbAcctTransferIn in) {
        log.info("{}", in);
        Result ret = new Result("123", "456");
        String retStatus = "OK";
        HeadOut headOut = new HeadOut(retStatus, ret);
        return headOut;
    }
}
