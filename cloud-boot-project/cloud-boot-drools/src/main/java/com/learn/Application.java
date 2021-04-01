package com.learn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * Description: .
 *
 * @author : ys.
 * @date :
 */
@SpringBootApplication(scanBasePackages = "com.learn")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


}
