package com.open.cloud;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

/**
 * @author leijian
 * @version 1.0
 * @date 2019/6/4 21:25
 **/
@Slf4j
public class Reader implements ItemReader<String> {
    private String[] messages = {"javainuse.com",
            "Welcome to Spring Batch Example",
            "We use H2 Database for this example"};

    private int count = 0;

    @Override
    public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        if (count < messages.length) {
            return messages[count++];
        } else {
            count = 0;
        }
        return null;
    }
}
