package com.open.cloud.core.flow.web;

import com.open.cloud.core.domain.BaseResponse;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

public class CometConsumerMessageConvert extends MappingJackson2HttpMessageConverter {
    @Override
    public boolean supports(Class aClass) {
        return BaseResponse.class.equals(aClass);
    }
}