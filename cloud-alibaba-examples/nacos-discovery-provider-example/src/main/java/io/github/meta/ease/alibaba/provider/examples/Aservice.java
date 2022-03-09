package io.github.meta.ease.alibaba.provider.examples;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author leijian
 * @version 1.0
 * @date 2022/3/2 17:00
 */
@Component
public class Aservice {

    @Autowired
    private Bservice bservice;
}
