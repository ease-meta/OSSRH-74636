package com.open.cloud;

import com.alipay.hessian.generic.model.GenericObject;
import com.alipay.sofa.rpc.api.GenericService;
import com.alipay.sofa.rpc.boot.runtime.param.BoltBindingParam;
import com.alipay.sofa.runtime.api.client.ReferenceClient;
import com.alipay.sofa.runtime.api.client.param.BindingParam;
import com.alipay.sofa.runtime.api.client.param.ReferenceParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * SOFABootWebSpringApplication
 */
@SpringBootApplication
@Slf4j
public class SOFABootGateWaySpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(SOFABootGateWaySpringApplication.class, args);
    }

    @Configuration
    @RestController
    class Demo {

        /*@SofaReference(binding = @SofaReferenceBinding(bindingType = "bolt"))
        IRbAcctTransfer iRbAcctTransfer;
        */


        /*@GetMapping("/test")
        @ResponseBody
        public HeadOut getNext(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
            RbAcctTransferIn rbAcctTransferIn = new RbAcctTransferIn();
            rbAcctTransferIn.setBaseAcctNo("GenericService");
            HeadOut headOut = iRbAcctTransfer.rbAcctTransfer(rbAcctTransferIn);
            log.info("{}", headOut);
            return headOut;
        }*/

        @Autowired
        ClientFactoryBean clientFactory;
        @GetMapping("/test")
        @ResponseBody
        public boolean getNext(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {


            ReferenceClient referenceClient = clientFactory.getClientFactory().getClient(ReferenceClient.class);
            ReferenceParam<GenericService> referenceParam = new ReferenceParam<GenericService>();
            referenceParam.setInterfaceType(GenericService.class);

            BindingParam refBindingParam = new BoltBindingParam();
            ((BoltBindingParam) refBindingParam).setGenericInterface("com.open.cloud.api.IRbAcctTransfer");
            referenceParam.setBindingParam(refBindingParam);

            GenericObject genericObject = new GenericObject(
                    "com.open.cloud.api.model.RbAcctTransferIn");
            genericObject.putField("reference", "123456");
            genericObject.putField("acctSystem", "555");


            Object o2 = referenceClient.reference(referenceParam).$genericInvoke("rbAcctTransfer",
                    new String[]{"com.open.cloud.api.model.RbAcctTransferIn"},
                    new Object[]{genericObject});
            log.info("{}",o2);

            return true;
        }
    }
}
