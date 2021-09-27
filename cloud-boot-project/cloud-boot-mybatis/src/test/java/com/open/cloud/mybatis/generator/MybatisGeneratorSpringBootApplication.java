package com.open.cloud.mybatis.generator;

import com.baomidou.mybatisplus.autoconfigure.MybatisPlusAutoConfiguration;
import com.open.cloud.mybatis.generator.annotion.EnableGenerator;
import com.open.cloud.mybatis.generator.service.CodeGeneratorService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author leijian
 * @version 1.0
 * @date 2021/9/25 21:14
 */
@SpringBootApplication(exclude = MybatisPlusAutoConfiguration.class)
@EnableGenerator
@MapperScan("com.open.cloud.mybatis.generator.mapper")
public class MybatisGeneratorSpringBootApplication {

    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext configurableApplicationContext = new SpringApplication(MybatisGeneratorSpringBootApplication.class).run(args);

        CodeGeneratorService codeGeneratorService = configurableApplicationContext.getBean(CodeGeneratorService.class);
        codeGeneratorService.createCode(MybatisGeneratorSpringBootApplication.class);
    }

}
