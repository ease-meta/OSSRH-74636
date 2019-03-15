package com.open.cloud.workflow.framework.shiro;

import com.open.cloud.workflow.common.enums.AuthTypeEnum;
import com.open.cloud.workflow.common.responses.Responses;
import com.open.cloud.workflow.common.responses.Ret;
import com.open.cloud.workflow.common.utils.JacksonUtils;
import com.open.cloud.workflow.framework.entity.Resource;
import com.open.cloud.workflow.framework.serviceImpl.ResourceServiceImpl;
import com.google.common.base.Throwables;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.util.MimeTypeUtils;
import org.springframework.util.PathMatcher;
import org.springframework.web.util.UrlPathHelper;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author leijian
 * @date 2019年1月26日
 */
@Slf4j
public class JWTFilter extends BasicHttpAuthenticationFilter {

    private PathMatcher pathMatcher;

    private ResourceServiceImpl resourceService;

    private UrlPathHelper urlPathHelper;

    @Override
    protected AuthenticationToken createToken(ServletRequest servletRequest, ServletResponse servletResponse) {
        // 获取请求token
        String token = getToken(WebUtils.toHttp(servletRequest));
        if (StringUtils.isBlank(token)) {
            return null;
        }
        return StringUtils.isBlank(token) ? null : new JWTToken(token);
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        HttpServletRequest httpRequest = WebUtils.toHttp(request);
        HttpServletResponse httpResponse = WebUtils.toHttp(response);

        String token = getToken(httpRequest);
        String method = httpRequest.getMethod();
        String requestUri = urlPathHelper.getOriginatingRequestUri(httpRequest);
        log.info("请求方法[{}],请求路径[{}]", method, requestUri);
        List<Resource> resources = resourceService.getResourceByMethod(method);
        Optional<Resource> optional = resources.stream().filter(match(method, requestUri)).findFirst();
        if (optional.isPresent()) {
            log.info("开始鉴权{}",optional.get());
        } else {
            log.info("鉴权不通过");
            httpResponse.setStatus(HttpStatus.NOT_FOUND.value());
            return false;
        }
        //申请token时有可能会带上token信息
        if (Objects.isNull(token)||requestUri.contains("token")) {
            // 无需登录的开放式接口
            List<Resource> openPerms = resources.stream().filter(re -> (String.valueOf(AuthTypeEnum.OPEN.getValue())).equals(re.getAuthType())).collect(Collectors.toList());
            boolean flag = anyMatch(openPerms, method, requestUri);
            if (flag) {
                log.info("鉴权通过");
            } else {
                httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                log.info("鉴权不通过");
            }
            return flag;
        }
        if (isLoginRequest(request, response)) {
            if (executeLogin(request, response)) {
                String uid = JWTUtils.getUid(token);
                log.info("鉴权通过,用户标识码{}",uid);
                return true;
            } else {
                log.info("鉴权不通过");
                httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return false;
            }
        }
        return false;

    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) {
        HttpServletResponse httpResponse = WebUtils.toHttp(response);
        log.info("http返回的状态头{}", httpResponse.getStatus());
        switch (httpResponse.getStatus()) {
            case HttpServletResponse.SC_OK:
                return true;
            default:
                return sendFail(request, response);
        }
    }

    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        log.info("操作异常");
        return false;
    }

    /**
     */
    protected boolean sendFail(ServletRequest request, ServletResponse response) {
        HttpServletResponse httpResponse = WebUtils.toHttp(response);
        response.setContentType(MimeTypeUtils.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        int status = httpResponse.getStatus();
        try (PrintWriter writer = response.getWriter()) {
            Responses<Void> responses = responses(status, HttpStatus.valueOf(status).getReasonPhrase());
            writer.print(JacksonUtils.toJson(responses));
            writer.flush();
        } catch (IOException e) {
            log.warn("Error: Response printJson faild, stackTrace: {}", Throwables.getStackTraceAsString(e));
        }
        return false;
    }

    public Responses<Void> responses(int code, String message) {
        return Responses.<Void>builder().ret(extracted(code, message)).build();
    }

    private static Ret extracted(int code, String message) {
        return Ret.builder().retCode(code).retMsg(message).retTime(LocalDateTime.now()).build();
    }

    /**
     * 获取请求的token
     */
    protected String getToken(HttpServletRequest request) {
        String token = request.getHeader(AUTHORIZATION_HEADER);
        return StringUtils.isBlank(token) ? null : token.replaceFirst("Bearer ", "");
    }

    /**
     * 是否任意匹配权限URL
     *
     * @param perms
     * @return
     */
    protected boolean anyMatch(Collection<Resource> perms, String method, String requestUri) {
        return perms.stream().anyMatch(match(method, requestUri));
    }

    /**
     * 匹配请求方法与路径
     *
     * @param method
     * @param requestUri
     * @return
     */
    private Predicate<Resource> match(String method, String requestUri) {
       return new Predicate<Resource>() {
            @Override
            public boolean test(final Resource resource) {
                return resource.getMethod().equalsIgnoreCase(method) && pathMatcher.match(resource.getMapping(), requestUri);
            }
        };
        //return resource -> resource.getMethod().equalsIgnoreCase(method) && pathMatcher.match(resource.getMapping(), requestUri);
    }

    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) {
        try {
            return super.executeLogin(request, response);
        } catch (Exception ignored) {
        }
        return false;
    }

    public void setPathMatcher(PathMatcher pathMatcher) {
        this.pathMatcher = pathMatcher;
    }

    public void setUrlPathHelper(UrlPathHelper urlPathHelper) {
        this.urlPathHelper = urlPathHelper;
    }

    public void setResourceService(ResourceServiceImpl resourceService) {
        this.resourceService = resourceService;

    }

}