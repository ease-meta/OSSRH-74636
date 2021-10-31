package com.open.cloud.web;

import com.open.cloud.domain.api.BaseResponse;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

public class CometConsumerMessageConvert extends MappingJackson2HttpMessageConverter {
    @Override
    public boolean supports(Class aClass) {
        return BaseResponse.class.equals(aClass);
    }
}