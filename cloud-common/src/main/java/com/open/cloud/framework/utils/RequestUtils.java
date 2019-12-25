package com.open.cloud.framework.utils;

import com.moc.branch.workflow.common.enums.HTTPMethod;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.StreamUtils;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * @author leijian
 * @date 2019年1月13日
 */
@SuppressWarnings("all")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
public class RequestUtils {

    /**
     * 判断请求方式GET
     *
     * @param request
     * @return
     */
    public static boolean isGet(HttpServletRequest request) {
        return HTTPMethod.GET.toString().equalsIgnoreCase(request.getMethod());
    }

    /**
     * 判断请求方式POST
     *
     * @param request
     * @return
     */
    public static boolean isPost(HttpServletRequest request) {
        return HTTPMethod.POST.toString().equalsIgnoreCase(request.getMethod());
    }

    /**
     * 判断请求方式PUT
     *
     * @param request
     * @return
     */
    public static boolean isPut(HttpServletRequest request) {
        return HTTPMethod.PUT.toString().equalsIgnoreCase(request.getMethod());
    }

    /**
     * 判断请求方式DELETE
     *
     * @param request
     * @return
     */
    public static boolean isDelete(HttpServletRequest request) {
        return HTTPMethod.DELETE.toString().equalsIgnoreCase(request.getMethod());
    }

    /**
     * 判断请求方式PATCH
     *
     * @param request
     * @return
     */
    public static boolean isPatch(HttpServletRequest request) {
        return HTTPMethod.PATCH.toString().equalsIgnoreCase(request.getMethod());
    }

    /**
     * 判断请求方式TRACE
     *
     * @param request
     * @return
     */
    public static boolean isTrace(HttpServletRequest request) {
        return HTTPMethod.TRACE.toString().equalsIgnoreCase(request.getMethod());
    }

    /**
     * 判断请求方式HEAD
     *
     * @param request
     * @return
     */
    public static boolean isHead(HttpServletRequest request) {
        return HTTPMethod.HEAD.toString().equalsIgnoreCase(request.getMethod());
    }

    /**
     * 判断请求方式OPTIONS
     *
     * @param request
     * @return
     */
    public static boolean isOptions(HttpServletRequest request) {
        return HTTPMethod.OPTIONS.toString().equalsIgnoreCase(request.getMethod());
    }

    /**
     * 获取请求
     *
     * @param request
     * @return
     */
    public static String getRequestBody(HttpServletRequest request) {
        String requestBody = null;
        if (isContainBody(request)) {
            try {
                ServletInputStream inputStream = null;
                inputStream = request.getInputStream();
                if (Objects.nonNull(inputStream)) {
                    StringWriter writer = new StringWriter();
                    IOUtils.copy(inputStream, writer, StandardCharsets.UTF_8.name());
                    requestBody = writer.toString();
                }
            } catch (IOException ignored) {
            }
        }
        return requestBody;
    }

    /**
     * 获取请求
     *
     * @param request
     * @return
     */
    public static byte[] getByteBody(HttpServletRequest request) {
        byte[] body = new byte[0];
        try {
            body = StreamUtils.copyToByteArray(request.getInputStream());
        } catch (IOException e) {
            log.error("Error: Get RequestBody byte[] fail," + e);
        }
        return body;
    }

    /**
     * 是否包含请求体
     *
     * @param request
     * @return
     */
    public static boolean isContainBody(HttpServletRequest request) {
        return isPost(request) || isPut(request) || isPatch(request);
    }

    public static String getClientIP(HttpServletRequest request, String... otherHeaderNames) {
        String[] headers = {"X-Forwarded-For", "X-Real-IP", "Proxy-Client-IP", "WL-Proxy-Client-IP", "HTTP_CLIENT_IP", "HTTP_X_FORWARDED_FOR"};
        return getClientIPByHeader(request, headers);
    }

    public static String getClientIPByHeader(HttpServletRequest request, String... headerNames) {
        String ip;
        for (String header : headerNames) {
            ip = request.getHeader(header);
            if (false == isUnknow(ip)) {
                return getMultistageReverseProxyIp(ip);
            }
        }

        ip = request.getRemoteAddr();
        return getMultistageReverseProxyIp(ip);
    }

    private static boolean isUnknow(String checkString) {
        return StringUtils.isBlank(checkString) || "unknown".equalsIgnoreCase(checkString);
    }

    private static String getMultistageReverseProxyIp(String ip) {
        // 多级反向代理检测
        if (ip != null && ip.indexOf(",") > 0) {
            final String[] ips = ip.trim().split(",");
            for (String subIp : ips) {
                if (false == isUnknow(subIp)) {
                    ip = subIp;
                    break;
                }
            }
        }
        return ip;
    }
}
