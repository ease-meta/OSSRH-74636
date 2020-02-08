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
 */
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
