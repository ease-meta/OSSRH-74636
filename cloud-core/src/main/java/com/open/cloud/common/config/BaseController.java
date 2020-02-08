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
/**
 * <p>Title: BaseController.java</p>
 * <p>Description: </p>
 *
 * @author leijian
 * @date 2019年1月10日
 * @version 1.0
 */
package com.open.cloud.common.config;

import com.open.cloud.common.base.Response;
import com.open.cloud.common.base.Ret;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author leijian
 * @date 2019年1月10日
 */
public class BaseController<T> {


    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true));
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));

    }

    private static Ret extracted(HttpStatus code) {
        return extracted(code.value(), HttpStatus.valueOf(code.value()).getReasonPhrase());
    }

    private static Ret extracted(int code, String message) {
        return Ret.builder().retCode(code).retMsg(message).retTime(LocalDateTime.now()).build();
    }

    @SuppressWarnings("hiding")
	protected <T> Response<T> response(HttpStatus code, T object) {
        return Response.<T>builder().ret(extracted(code)).result(object).build();

    }

    protected Ret ret(HttpStatus code) {
        return extracted(code);

    }

    public Response<Void> response(HttpStatus code) {
        return Response.<Void>builder().ret(extracted(code)).build();
    }

    public Response<Void> response(int code, String message) {
        return Response.<Void>builder().ret(extracted(code, message)).build();
    }
}
