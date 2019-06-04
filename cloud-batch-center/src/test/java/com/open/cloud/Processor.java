package com.open.cloud;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;

/**
 * @author leijian
 * @version 1.0
 * @date 2019/6/4 21:26
 **/
@Slf4j
public class Processor implements ItemProcessor<String, String> {
    @Override
    public String process(String item) throws Exception {
        return item.toUpperCase();
    }
}
