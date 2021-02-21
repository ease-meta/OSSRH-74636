package org.apache.kafka;

import lombok.Data;

import java.util.Date;

@Data
public class Message {
    private String id;
    private String message;
    private Date sendTime;
    // getter setter 略  
}