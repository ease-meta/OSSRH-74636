package com.open.cloud.common.utils;

import com.open.cloud.common.utils.spring.SpringApplicationContext;
import com.sun.istack.internal.NotNull;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Locale;

/**
 * @author Leijian
 */
public class MessageUtils {
    public static String message(@NotNull String message, Object... args) {
        String msg = "";
        try {
            Locale locale = LocaleContextHolder.getLocale();
            MessageSource messageSource = SpringApplicationContext.getBean(MessageSource.class);
            msg = StringUtils.defaultString(messageSource.getMessage(message, args, locale),message);
        } catch (Exception e) {
            return message;
        	//log.error("parse message error! ", e);
        }
        return msg;
    }
}