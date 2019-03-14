package com.moc.cloud.workflow.framework.shiro;

import com.moc.cloud.workflow.framework.serviceImpl.ResourceServiceImpl;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.util.UrlPathHelper;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Leijian
 * @date 2019年1月28
 */
@ConditionalOnProperty(name = "cloud.workflow.shiro.enabled", havingValue = "true")
@Configuration
@AutoConfigureAfter
public class ShiroAutoConfiguration {

    /**
     *
     * @param realm
     * @return
     */
    @Bean
    public SecurityManager securityManager(@Autowired JWTRealm realm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        DefaultSubjectDAO subjectDAO = (DefaultSubjectDAO) securityManager.getSubjectDAO();
        DefaultSessionStorageEvaluator evaluator = (DefaultSessionStorageEvaluator) subjectDAO.getSessionStorageEvaluator();
        evaluator.setSessionStorageEnabled(false);
        securityManager.setSubjectDAO(subjectDAO);
        securityManager.setRealm(realm);
        return securityManager;
    }

    /**
     *
     * @param securityManager
     * @param resourceService
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilter(@Autowired SecurityManager securityManager, @Autowired ResourceServiceImpl resourceService) {
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        shiroFilter.setSecurityManager(securityManager);
        Map<String, Filter> filters = new HashMap<String, Filter>();
        JWTFilter value = new JWTFilter();
        value.setAuthzScheme("Bearer");
        value.setUrlPathHelper(new UrlPathHelper());
        value.setResourceService(resourceService);
        value.setPathMatcher(new AntPathMatcher());
        filters.put("jwt", value);
        shiroFilter.setFilters(filters);
        Map<String, String> filterMap = new LinkedHashMap<>();
        filterMap.put("/**/**.*", "anon");
        filterMap.put("/v2/api-docs", "anon");
        filterMap.put("/swagger-resources/**", "anon");
        filterMap.put("/swagger-ui.html", "anon");
        filterMap.put("/webjars/**", "anon");
        filterMap.put("/v2/**", "anon");
        filterMap.put("/swagger-resources/**", "anon");
        //druid过滤
        filterMap.put("/druid/**", "anon");
        //JWTFilter
        filterMap.put("/", "anon");
        filterMap.put("/index.html", "anon");
        filterMap.put("/actuator/**","anon");

        filterMap.put("/**", "jwt");
        shiroFilter.setFilterChainDefinitionMap(filterMap);
        return shiroFilter;
    }
}
