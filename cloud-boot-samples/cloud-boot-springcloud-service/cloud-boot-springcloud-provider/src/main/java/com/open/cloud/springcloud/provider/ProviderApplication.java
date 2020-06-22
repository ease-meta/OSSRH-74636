package com.open.cloud.springcloud.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EurekaClientAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiaojing
 */
@EnableDiscoveryClient
@SpringBootApplication(exclude = {EurekaClientAutoConfiguration.class})
public class ProviderApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProviderApplication.class, args);
	}

	@RestController
	class EchoController {

		@PostMapping("/")
		public ResponseEntity index() {
			return new ResponseEntity("index error", HttpStatus.INTERNAL_SERVER_ERROR);
		}

		@PostMapping("/test")
		public ResponseEntity test() {
			return new ResponseEntity("error", HttpStatus.INTERNAL_SERVER_ERROR);
		}

		@PostMapping("/sleep")
		public String sleep() {
			try {
				Thread.sleep(1000L);
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
			return "ok";
		}

		@PostMapping("/echo/{string}")
		public String echo(@PathVariable String string) {
			return "hello Nacos Discovery " + string;
		}

		@PostMapping("/divide")
		public String divide(@RequestParam Integer a, @RequestParam Integer b) {
			return String.valueOf(a / b);
		}

	}

}
