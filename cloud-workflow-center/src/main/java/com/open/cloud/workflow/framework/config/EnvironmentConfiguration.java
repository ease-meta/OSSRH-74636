/**
 * <p>Title: SpringbootEnvironmentAware.java</p>
 * <p>Description: </p>
 *
 * @author leijian
 * @date 2019年1月25日
 * @version 1.0
 */
package com.open.cloud.workflow.framework.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author leijian
 * @date 2019年1月25日
 */
@Component
@Order(value = Ordered.HIGHEST_PRECEDENCE)
@Slf4j
public class EnvironmentConfiguration {


    @Value("${system.sequence.nodes}")
    private String wordId;

    @Value("${spring.config.location}")
    private String systemPath;

    @Value("${system.sequence.nodes}")
    private String expire;

    public  String getWordId(){
        return this.wordId;
    }

    public String getSystemPath() {
        log.info("转换前路径{}", this.systemPath);
        String pathToUse = StringUtils.removeStartIgnoreCase(this.systemPath, "classpath:");
        pathToUse = StringUtils.removeStartIgnoreCase(pathToUse, "file:");
        pathToUse = pathToUse.replaceAll("[/\\\\]{1,}", "/").trim();
        log.info("转换后路径{}", pathToUse);
        return pathToUse;
    }


}
