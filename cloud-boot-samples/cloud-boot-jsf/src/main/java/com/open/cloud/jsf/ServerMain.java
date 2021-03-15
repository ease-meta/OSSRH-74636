package com.open.cloud.jsf;

import com.jd.jsf.gd.config.ProviderConfig;
import com.jd.jsf.gd.config.ServerConfig;
import com.jd.jsf.gd.registry.Provider;
import com.jd.jsf.gd.registry.file.FileRegistryHelper;
import com.jd.jsf.gd.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ServerMain {
	private final static Logger LOGGER = LoggerFactory.getLogger(ServerMain.class);

	public static void main(String[] args) {
		System.setProperty("unitTestMode", "true");
		LOGGER.info("服务端启动ing！");
		ClassPathXmlApplicationContext server = new ClassPathXmlApplicationContext("classpath:jsf-provider.xml");
		LOGGER.info("服务端启动完成！");

		ProviderConfig providerConfig = (ProviderConfig) server.getBean("helloService");
		/*providerConfig.setId("helloService");
		providerConfig.setInterfaceId("com.open.cloud.jsf.api.IHelloService");
		providerConfig.setAlias("CHANGE");
		providerConfig.setDelay(0);
		providerConfig.setServer(server.getBean(ServerConfig.class));
		providerConfig.setRegistry(server.getBean(RegistryConfig.class));
		providerConfig.setRef(server.getBean("helloServiceImpl"));
		providerConfig.export();*/
		Map<String, List<Provider>> map = new ConcurrentHashMap<>();
		List<Provider> providerList = new ArrayList<>();
		Provider provider = new Provider();
		provider.setAlias(providerConfig.getAlias());
		provider.setInterfaceId(providerConfig.getIfaceId());
		ServerConfig serverConfig = (ServerConfig) providerConfig.getServer().get(0);
		provider.setIp(serverConfig.getHost());
		provider.setPort(serverConfig.getPort());
		providerList.add(provider);
		String key = StringUtils.trimToEmpty(providerConfig.getInterfaceId()) + "@" + StringUtils.trimToEmpty(providerConfig.getAlias()) + "@" + StringUtils.trimToEmpty("jsf");
		map.put(key, providerList);
		FileRegistryHelper.backup("/app/dcits/jsf/", map);
		while (true) {

		}
		/*ClassPathXmlApplicationContext appContext = new ClassPathXmlApplicationContext("classpath:jsf-consumer.xml");
		IHelloService service = (IHelloService) appContext.getBean("helloService");
		LOGGER.info("得到调用端代理：{}", service);

		try {
			String result = service.echoStr("jsf");
			LOGGER.info("response msg from server :{}", result);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}*/
	}
}