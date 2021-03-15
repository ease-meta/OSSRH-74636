package com.open.cloud.jsf;

import com.open.cloud.jsf.api.IHelloService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Leijian
 * @date 2021/3/14 0:11
 * @since
 */
public class ClientMain {
	private final static Logger LOGGER = LoggerFactory.getLogger(ClientMain.class);

	public static void main(String[] args) {
		ClassPathXmlApplicationContext appContext = new ClassPathXmlApplicationContext("classpath:jsf-consumer.xml");
		IHelloService service = (IHelloService) appContext.getBean("helloService");
		LOGGER.info("得到调用端代理：{}", service);

		try {
			String result = service.echoStr("jsf");
			LOGGER.info("response msg from server :{}", result);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
	}
}

