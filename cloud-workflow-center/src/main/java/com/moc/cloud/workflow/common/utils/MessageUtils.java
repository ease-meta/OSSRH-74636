package com.moc.cloud.workflow.common.utils;

import com.moc.cloud.workflow.framework.config.SpringApplicationConfiguration;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import javax.validation.constraints.NotNull;
import java.util.Locale;


/**
 * @Classname: MessageUtils
 * @Description:
 * @Author: leijian
 * @Date: 2019/1/23
 * @Version: 1.0
 */
public class MessageUtils {
    public static String message(@NotNull String message, Object... args) {
        String msg = "";
        try {
            Locale locale = LocaleContextHolder.getLocale();
            MessageSource messageSource = SpringApplicationConfiguration.getBean(MessageSource.class);
            msg = StringUtils.defaultString(messageSource.getMessage(message, args, locale),message);
        } catch (Exception e) {
            return message;
        	//log.error("parse message error! ", e);
        }
        return msg;
    }
}
