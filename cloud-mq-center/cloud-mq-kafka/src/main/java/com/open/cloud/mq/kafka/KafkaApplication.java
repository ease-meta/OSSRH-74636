package com.open.cloud.mq.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@SpringBootApplication
@RestController
//http://localhost:8080/send/kl
public class KafkaApplication {

	private final Logger logger = LoggerFactory.getLogger(KafkaApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(KafkaApplication.class, args);
	}

	@Autowired
	private KafkaTemplate<Object, Object> template;

	@GetMapping("/send/{input}")
	public void sendFoo(@PathVariable String input) {
		ListenableFuture<SendResult<Object, Object>> future = this.template.send("topic_input", input);
		try {
			future.get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 事务消息
	 *
	 * @param input
	 */
	@GetMapping("/kafkatx/{input}")
	@Transactional(rollbackFor= RuntimeException.class)
	public void kafkaTx(@PathVariable String input) {
		template.executeInTransaction(t -> {
			t.send("topic_id", "ka");
			if ("error".equals(input)) {
				throw new RuntimeException("error");
			}
			t.send("topic_id", "ka1");
			return true;
		});
	}


	//异步获取发送结果
	public void sendFooAsy() {
		template.send("", "").addCallback(new ListenableFutureCallback<SendResult<Object, Object>>() {
			@Override
			public void onFailure(Throwable ex) {

			}

			@Override
			public void onSuccess(SendResult<Object, Object> result) {

			}
		});
	}

	@KafkaListener(id = "webGroup", topics = "topic_input")
	public void listen(String input) {
		logger.info("input value: {}", input);
	}
}