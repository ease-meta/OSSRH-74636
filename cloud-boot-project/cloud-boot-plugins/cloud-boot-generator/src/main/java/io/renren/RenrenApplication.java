package io.renren;

import io.renren.service.SysGeneratorService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@MapperScan("io.renren.dao")
public class RenrenApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext configurableApplicationContext = SpringApplication.run(RenrenApplication.class, args);

		SysGeneratorService sysGeneratorService = configurableApplicationContext.getBean(SysGeneratorService.class);
		//生成ext扩展以及存在的时候是否进行覆盖操作
		sysGeneratorService.generatorCode(new String[]{"workflow"});
	}
}
