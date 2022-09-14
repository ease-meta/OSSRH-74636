package io.github.meta.ease.bpm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 只是为了单元测试@ComponentScan与@SpringBootApplication（@ComponentScan区别）
 * 参考文章 https://www.yuque.com/u1198581/stqqzn/agbnwg，测试过程需要删除activiti的相关依赖
 */
//@SpringBootApplication(scanBasePackages = {"io.github.meta.ease.bpm"})
@SpringBootApplication
@ComponentScan(basePackages = {"io.github.meta.ease.bpm"})
public class QuickstartApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuickstartApplication.class, args);
    }
}
