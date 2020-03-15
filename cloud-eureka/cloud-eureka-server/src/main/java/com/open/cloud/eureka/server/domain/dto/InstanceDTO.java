package com.open.cloud.eureka.server.domain.dto;

import com.netflix.appinfo.InstanceInfo;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@ToString
@NoArgsConstructor
@Accessors(chain = true)
public class InstanceDTO {

    private String[] availabilityZones;
    private InstanceInfo instanceInfo;

}