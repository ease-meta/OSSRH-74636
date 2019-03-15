package com.open.cloud.workflow.framework.config;

import com.open.cloud.workflow.common.exception.BusinessException;
import com.open.cloud.workflow.common.responses.Responses;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.ibatis.binding.BindingException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * @Classname: ExceptionHandle
 * @Description:
 * @Author: leijian
 * @Date: 2019/1/17
 * @Version: 1.0
 */

@RestControllerAdvice
@Slf4j
public class ExceptionHandle extends BaseController<Void> {

    @ExceptionHandler(value = NoHandlerFoundException.class)
    @ResponseBody
    public Responses<Void> NoHandlerFoundException(Exception e) {
        log.error("异常信息:", e);
        return responses(404, "参数异常[" + ExceptionUtils.getMessage(e) + "]");
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    public Responses<Void> MethodArgumentNotValidException(Exception e) {
        log.error("异常信息:", e);
        return responses(401, "参数异常[" + ExceptionUtils.getMessage(e) + "]");
    }

    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    @ResponseBody
    public Responses<Void> HttpMessageNotReadableException(Exception e) {
        log.error("异常信息:", e);
        return responses(401, "参数异常[" + ExceptionUtils.getMessage(e) + "]");
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    @ResponseBody
    public Responses<Void> IllegalArgumentException(Exception e) {
        log.error("异常信息:", e);
        return responses(401, "参数异常[" + ExceptionUtils.getMessage(e) + "]");
    }

    @ExceptionHandler(value = BindingException.class)
    @ResponseBody
    public Responses<Void> BindingException(Exception e) {
        log.error("异常信息:", e);
        return responses(700, "数据库操作异常[" + ExceptionUtils.getMessage(e) + "]");
    }

    @ExceptionHandler(value = BusinessException.class)
    @ResponseBody
    public Responses<Void> BusinessException(Exception e) {
        log.error("异常信息:", e);
        return responses(666, "[" + ExceptionUtils.getMessage(e) + "]");
    }


    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Responses<Void> handleException(Exception e) {
        log.error("异常信息:", e);
        return responses(999, "系统异常[" + ExceptionUtils.getMessage(e) + "]");
    }
}
