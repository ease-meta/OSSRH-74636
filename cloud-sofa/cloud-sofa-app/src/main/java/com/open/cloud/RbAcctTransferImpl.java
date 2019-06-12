package com.open.cloud;

import com.alipay.sofa.runtime.api.annotation.SofaService;
import com.alipay.sofa.runtime.api.annotation.SofaServiceBinding;
import com.open.cloud.api.IRbAcctTransfer;
import com.open.cloud.api.model.RbAcctTransferIn;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@SofaService(bindings = {@SofaServiceBinding(bindingType = "bolt")})
public class RbAcctTransferImpl implements IRbAcctTransfer {


    @Override
    public boolean rbAcctTransfer(RbAcctTransferIn in) {
        log.info("{}", in);
        return true;
    }
}
