package com.open.cloud.eureka.gateway.repository;

import com.alibaba.fastjson.JSON;
import com.open.cloud.eureka.gateway.model.HeadersInfo;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class HeadersConfigRepository
{
    private static final Logger log;
    @Value("${json.headers.path:null}")
    String headersPath;
    private List<HeadersInfo> headers;
    
    public HeadersConfigRepository() {
        this.headers = new ArrayList<HeadersInfo>();
    }
    
    @PostConstruct
    private void init() {
        try {
            this.headers = (List<HeadersInfo>)JSON.parseArray(FileUtils.readFileToString(new File(this.headersPath), "UTF-8"), (Class)HeadersInfo.class);
        }
        catch (IOException e) {
            HeadersConfigRepository.log.error("headers 文件解析失败,{}", (Throwable)e);
        }
    }
    
    public List<HeadersInfo> getHeaders() {
        return this.headers;
    }
    
    static {
        log = LoggerFactory.getLogger((Class)HeadersConfigRepository.class);
    }
}
