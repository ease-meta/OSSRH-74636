package com.open.cloud.webssh.model;

import org.mapstruct.InheritConfiguration;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mappings;

import java.util.List;


public interface BaseConvert<SOURCE, TARGET> {
    @Mappings({})
    @InheritConfiguration
    TARGET to(SOURCE var1);

    @InheritConfiguration
    List<TARGET> to(List<SOURCE> var1);

    @InheritInverseConfiguration
    SOURCE from(TARGET var1);

    @InheritInverseConfiguration
    List<SOURCE> from(List<TARGET> var1);
}
