package com.open.cloud;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.servlet.ServletException;

/**
 * SOFABootWebSpringApplication
 */
@SpringBootApplication
@Slf4j
public class SOFABootGateWaySpringApplication {

    public static void main(String[] args) throws ServletException {
        SpringApplication.run(SOFABootGateWaySpringApplication.class, args);

        UndertowServer undertowServer = new UndertowServer();
        undertowServer.start();
    }
/*
    @Configuration
    @RestController
    class Demo {*/
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

/*        @Autowired
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

    }*/
}
