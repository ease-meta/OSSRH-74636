package com.open.cloud.mq.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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
		this.template.send("topic_input", input);
	}

	@KafkaListener(id = "webGroup", topics = "topic_input")
	public void listen(String input) {
		logger.info("input value: {}", input);
	}
}