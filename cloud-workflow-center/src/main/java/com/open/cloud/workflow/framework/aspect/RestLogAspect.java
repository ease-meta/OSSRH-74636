package com.open.cloud.workflow.framework.aspect;

import com.alibaba.fastjson.JSON;
import com.open.cloud.workflow.common.utils.RequestUtils;
import com.open.cloud.workflow.framework.entity.SysLog;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Objects;

/**
 * @author leijian
 * @date 2019年1月7日
 */
@Aspect
@Component
@Slf4j
public class RestLogAspect {

    // @Pointcut("execution( *
    // com.nizi..coder.*.*(..))")//两个..代表所有子目录，最后括号里的两个..代表所有参数
    @Pointcut("execution(* com.open.cloud.workflow.framework.controller..*.*(..))")
    public void logPointCut() {
    }

    @Before("logPointCut()")
    public void doBefore(JoinPoint joinPoint) {
        // 接收到请求，记录请求内容
        SysLog sysLog = new SysLog();
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 记录下请求内容
        log.info("请求地址 : " + request.getRequestURI());
        log.info("HTTP METHOD : " + request.getMethod());
        // 获取真实的ip地址
        log.info("IP : " + RequestUtils.getClientIP(request));
        log.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        log.info("参数 : " + Arrays.toString(joinPoint.getArgs()));
        sysLog.setIp(RequestUtils.getClientIP(request));
        sysLog.setType(request.getMethod().toUpperCase());
        sysLog.setCreateTime(LocalDateTime.now());
        sysLog.setUrl(request.getRequestURL().toString());
        sysLog.setText(getRequestBody(request));
        Object[] obj = joinPoint.getArgs();
        StringBuffer buffer = new StringBuffer();
        if (obj != null) {
            for (int i = 0; i < obj.length; i++) {
                buffer.append("[参数" + (i + 1) + ":");
                Object o = obj[i];
                if (o instanceof Model) {
                    continue;
                }
                String parameter;
                try {
                    parameter = JSON.toJSONString(o);
                } catch (Exception e) {
                    continue;
                }
                buffer.append(parameter);
                buffer.append("]");
            }
        }
        sysLog.setParam(buffer.toString());
    }

    /**
     *
     * @param ret
     */
    @AfterReturning(returning = "ret", pointcut = "logPointCut()") // returning的值和doAfterReturning的参数名一致
    public void doAfterReturning(Object ret) {
        // 处理完请求，返回内容(返回值太复杂时，打印的是物理存储空间的地址)
        log.debug("返回值 : " + ret);
    }

    @Around("logPointCut()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object ob = pjp.proceed();// ob 为方法的返回值
        log.info("耗时{},返回值{}" + (System.currentTimeMillis() - startTime),ob);
        return ob;
    }

    /**
     *
     * @param request
     * @return
     */
    private static String getRequestBody(HttpServletRequest request) {
        String requestBody = null;
        if (isContainBody(request)) {
            try {
                ServletInputStream inputStream = request.getInputStream();
                if (Objects.nonNull(inputStream)) {
                    StringWriter writer = new StringWriter();
                    IOUtils.copy(inputStream, writer, StandardCharsets.UTF_8.name());
                    requestBody = writer.toString();
                }
            } catch (IOException e) {
                log.error(e.getMessage(),e);
            }
        }
        return requestBody;
    }


    private static boolean isContainBody(HttpServletRequest request) {
        return RequestUtils.isPost(request) || RequestUtils.isPut(request) || RequestUtils.isPatch(request);
    }
}
