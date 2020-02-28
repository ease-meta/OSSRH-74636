package com.open.cloud.sloars;

import com.open.cloud.sloars.aop.Chinese;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author Leijian
 * @date 2020/2/28
 */
@SpringBootApplication
public class SloarsApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext configurableApplicationContext = SpringApplication.run(SloarsApplication.class, args);
		Chinese chinese = configurableApplicationContext.getBean(Chinese.class);
		chinese.sayHello("马冬梅");
		System.out.println(chinese.getClass());
	}
}
