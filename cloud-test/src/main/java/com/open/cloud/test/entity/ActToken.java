package com.open.cloud.test.entity;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@ToString
public class ActToken implements java.io.Serializable {

    private static final long serialVersionUID = 127593201694027397L;

    private String uuid;
    private String token;
    private LocalDateTime time;

}
