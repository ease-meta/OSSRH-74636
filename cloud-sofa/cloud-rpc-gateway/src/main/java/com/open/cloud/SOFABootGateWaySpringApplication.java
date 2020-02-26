/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *//*

package com.open.cloud;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.servlet.ServletException;

*/
/**
 * SOFABootWebSpringApplication
 *//*

@SpringBootApplication
@Slf4j
public class SOFABootGateWaySpringApplication {

    public static void main(String[] args) throws ServletException {
        SpringApplication.run(SOFABootGateWaySpringApplication.class, args);

        UndertowServer undertowServer = new UndertowServer();
        undertowServer.start();
    }
    */
/*
        @Configuration
        @RestController
        class Demo {*//*

    */
/*@SofaReference(binding = @SofaReferenceBinding(bindingType = "bolt"))
    IRbAcctTransfer iRbAcctTransfer;
    *//*


    */
/*@GetMapping("/test")
    @ResponseBody
    public HeadOut getNext(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        RbAcctTransferIn rbAcctTransferIn = new RbAcctTransferIn();
        rbAcctTransferIn.setBaseAcctNo("GenericService");
        HeadOut headOut = iRbAcctTransfer.rbAcctTransfer(rbAcctTransferIn);
        log.info("{}", headOut);
        return headOut;
    }*//*


    */
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

        }*//*

}
*/
