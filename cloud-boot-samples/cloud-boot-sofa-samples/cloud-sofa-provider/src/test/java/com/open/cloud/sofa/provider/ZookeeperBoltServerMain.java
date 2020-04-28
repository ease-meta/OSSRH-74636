package com.open.cloud.sofa.provider;

import com.alipay.sofa.rpc.common.RpcConstants;
import com.alipay.sofa.rpc.config.ProviderConfig;
import com.alipay.sofa.rpc.config.RegistryConfig;
import com.alipay.sofa.rpc.config.ServerConfig;
import com.alipay.sofa.rpc.context.RpcRuntimeContext;
import com.alipay.sofa.rpc.log.Logger;
import com.alipay.sofa.rpc.log.LoggerFactory;
import com.open.cloud.sofa.api.HelloService;

/**
 * <p></p>
 * <p>
 *
 *
 * @author <a href=mailto:zhanggeng.zg@antfin.com>GengZhang</a>
 */
public class ZookeeperBoltServerMain {

    /**
     * slf4j Logger for this class
     */
    private final static Logger LOGGER = LoggerFactory.getLogger(ZookeeperBoltServerMain.class);

    public static void main(String[] args) {

        /**
         * 运行需要pom.xml里增加依赖 
         <dependency>
             <groupId>org.apache.curator</groupId>
             <artifactId>curator-recipes</artifactId>
             <scope>test</scope>
         </dependency>
         */
        //RegistryConfig registryConfig = new RegistryConfig()
        //   .setProtocol(RpcConstants.REGISTRY_PROTOCOL_ZK)
        //    .setAddress("122.51.108.224:2181");
        RegistryConfig registryConfig = new RegistryConfig()
               .setProtocol("nacos")
                .setAddress("122.51.108.224:8848/public");

        ServerConfig serverConfig = new ServerConfig()
            .setPort(22104).
             setProtocol("bolt")
            .setDaemon(false);

        ProviderConfig<HelloService> providerConfig = new ProviderConfig<HelloService>()
            .setInterfaceId(HelloService.class.getName())
            .setRef(new HelloServiceImpl())
            .setServer(serverConfig)
            .setRegistry(registryConfig);



        providerConfig.export();

        LOGGER.warn("started at pid {}", RpcRuntimeContext.PID);
    }

}
