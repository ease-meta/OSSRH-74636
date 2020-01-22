/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
            msg = StringUtils.defaultString(messageSource.getMessage(message, args, locale),
                message);
        } catch (Exception e) {
            return message;
            //log.error("parse message error! ", e);
        }
        return msg;
    }
}