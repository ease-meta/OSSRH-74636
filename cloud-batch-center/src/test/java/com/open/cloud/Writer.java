package com.open.cloud;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

/**
 * @author leijian
 * @version 1.0
 * @date 2019/6/4 21:27
 **/
@Slf4j
public class Writer implements ItemWriter<String> {
    @Override
    public void write(List<? extends String> items) throws Exception {
        for (String msg : items) {
            log.info("Writing the data " + msg);
        }
    }
}
