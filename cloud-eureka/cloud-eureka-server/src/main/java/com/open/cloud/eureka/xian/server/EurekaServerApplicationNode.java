package com.open.cloud.eureka.xian.server;

import cn.hutool.core.net.NetUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.core.env.Environment;

@Slf4j
@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApplicationNode {
	public static void main(String[] args) {
		Environment env = new SpringApplicationBuilder(EurekaServerApplicationNode.class).run(args).getEnvironment();
		log.info(
				"\n----------------------------------------------------------\n\t"
						+ "Application '{}' is running! Access URLs:\n\t" + "Local: \t\thttp://127.0.0.1:{}\n\t"
						+ "External: \thttp://{}:{}\n----------------------------------------------------------",
				env.getProperty("spring.application.name"), env.getProperty("server.port"),
				NetUtil.getLocalhostStr(), env.getProperty("server.port"));

		String configServerStatus = env.getProperty("configserver.status");
		log.info(
				"\n----------------------------------------------------------\n\t"
						+ "Config Server: \t{}\n----------------------------------------------------------",
				configServerStatus == null ? "Not found or not setup for this application" : configServerStatus);
	}
}