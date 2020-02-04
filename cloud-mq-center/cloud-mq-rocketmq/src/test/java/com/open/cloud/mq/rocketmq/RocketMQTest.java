package com.open.cloud.mq.rocketmq;

import com.open.cloud.common.serialize.JsonSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Leijian
 * @date 2020/2/3
 */
//@RunWith(SpringRunner.class)
//@SpringBootTest
@SpringBootApplication
@RestController
public class RocketMQTest {

	@Autowired(required = false)
	private RocketMQProducerProvider rocketMQProducerProvider;

	//@Test
	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(RocketMQTest.class, args);
//		RocketMQConsumerProvider consumerProvider = context.getBean(RocketMQConsumerProvider.class);
//		consumerProvider.subscribe("csx-bsf-demo-test-consumer-01", "csx-bsf-demo-test", new String[]{"aaaa", "ddd"}, (msg) -> {
//			System.out.println(new JsonSerializer().serialize(msg));
//		}, String.class);
	}

	int i = 0;

	@GetMapping("/sendMessage")
	public void sendMessage() {
		i = i + 1;
		rocketMQProducerProvider.sendMessage("csx-bsf-demo-test", "aaaa", "测试" + i);

	}
}
