package com.open.cloud.mapstruct;

import lombok.Data;

@Data
public class Target {

    private String id;

    private Integer num;

    private Integer count;

    private SubTarget subTarget;
}