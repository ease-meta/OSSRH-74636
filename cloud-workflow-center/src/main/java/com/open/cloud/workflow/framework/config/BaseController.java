/**
 * <p>Title: BaseController.java</p>
 * <p>Description: </p>
 *
 * @author leijian
 * @date 2019年1月10日
 * @version 1.0
 */
package com.open.cloud.workflow.framework.config;

import com.open.cloud.workflow.common.responses.Responses;
import com.open.cloud.workflow.common.responses.Ret;
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
	protected <T> Responses<T> responses(HttpStatus code, T object) {
        return Responses.<T>builder().ret(extracted(code)).result(object).build();

    }

    protected Ret ret(HttpStatus code) {
        return extracted(code);

    }

    public Responses<Void> responses(HttpStatus code) {
        return Responses.<Void>builder().ret(extracted(code)).build();
    }

    public Responses<Void> responses(int code, String message) {
        return Responses.<Void>builder().ret(extracted(code, message)).build();
    }
}
