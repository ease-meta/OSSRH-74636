package com.open.cloud.mapstruct;

import lombok.Data;

@Data
public class Address {

    private String street;
    private int zipCode;
    private int houseNo;
    private String description;

}