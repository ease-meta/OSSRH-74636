package io.github.meta.ease.web.autoconfigure;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import io.github.meta.ease.common.enums.WebFilterOrderEnum;
import io.github.meta.ease.common.util.json.JsonUtils;
import io.github.meta.ease.web.CometConsumerMessageConvert;
import io.github.meta.ease.web.ResponseAdvice;
import io.github.meta.ease.web.apilog.core.filter.ApiAccessLogFilter;
import io.github.meta.ease.web.apilog.core.service.ApiAccessLogFrameworkService;
import io.github.meta.ease.web.apilog.core.service.ApiErrorLogFrameworkService;
import io.github.meta.ease.web.config.WebProperties;
import io.github.meta.ease.web.config.XssProperties;
import io.github.meta.ease.web.core.filter.CacheRequestBodyFilter;
import io.github.meta.ease.web.core.filter.DemoFilter;
import io.github.meta.ease.web.core.filter.XssFilter;
import io.github.meta.ease.web.core.handler.GlobalExceptionHandler;
import io.github.meta.ease.web.core.handler.GlobalResponseBodyHandler;
import io.github.meta.ease.web.jackson.core.databind.LocalDateTimeDeserializer;
import io.github.meta.ease.web.jackson.core.databind.LocalDateTimeSerializer;
import io.github.meta.ease.web.util.WebFrameworkUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import javax.servlet.Filter;
import java.time.LocalDateTime;

/**
 * <p>文件名称:  WebMvcCoreAutoConfiguration</p>
 * <p>描述:     </p>
 * <p>创建时间:  2022/6/3</p>
 *
 * @author Abu
 * @version 22.0.1
 * @since 22.0.1
 */
@Configuration(proxyBeanMethods = false)
@ImportAutoConfiguration({WebMvcCoreAutoConfiguration.ApiLogAutoConfiguration.class, WebMvcCoreAutoConfiguration.JacksonAutoConfiguration.class, WebMvcCoreAutoConfiguration.WebAutoConfiguration.class})
@Slf4j
public class WebMvcCoreAutoConfiguration {

    @Bean
    public ResponseAdvice responseAdvice() {
        return new ResponseAdvice();
    }

    @Bean
    public CometConsumerMessageConvert cometConsumerMessageConvert() {
        return new CometConsumerMessageConvert();
    }

    @Configuration
    @AutoConfigureAfter(WebAutoConfiguration.class)
    class ApiLogAutoConfiguration {

        /**
         * 创建 ApiAccessLogFilter Bean，记录 API 请求日志
         */
        @Bean
        public FilterRegistrationBean<ApiAccessLogFilter> apiAccessLogFilter(WebProperties webProperties,
                                                                             @Value("${spring.application.name}") String applicationName,
                                                                             ApiAccessLogFrameworkService apiAccessLogFrameworkService) {
            ApiAccessLogFilter filter = new ApiAccessLogFilter(webProperties, applicationName, apiAccessLogFrameworkService);
            return createFilterBean(filter, WebFilterOrderEnum.API_ACCESS_LOG_FILTER);
        }

        private <T extends Filter> FilterRegistrationBean<T> createFilterBean(T filter, Integer order) {
            FilterRegistrationBean<T> bean = new FilterRegistrationBean<>(filter);
            bean.setOrder(order);
            return bean;
        }

    }


    @Configuration
    public class JacksonAutoConfiguration {

        @Bean
        public BeanPostProcessor objectMapperBeanPostProcessor() {
            return new BeanPostProcessor() {
                @Override
                public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                    if (!(bean instanceof ObjectMapper)) {
                        return bean;
                    }
                    ObjectMapper objectMapper = (ObjectMapper) bean;
                    SimpleModule simpleModule = new SimpleModule();
                    /*
                     * 1. 新增Long类型序列化规则，数值超过2^53-1，在JS会出现精度丢失问题，因此Long自动序列化为字符串类型
                     * 2. 新增LocalDateTime序列化、反序列化规则
                     */
                    simpleModule
//                .addSerializer(Long.class, ToStringSerializer.instance)
//                    .addSerializer(Long.TYPE, ToStringSerializer.instance)
                            .addSerializer(LocalDateTime.class, LocalDateTimeSerializer.INSTANCE)
                            .addDeserializer(LocalDateTime.class, LocalDateTimeDeserializer.INSTANCE);

                    objectMapper.registerModules(simpleModule);

                    JsonUtils.init(objectMapper);
                    log.info("初始化 jackson 自动配置");
                    return bean;
                }
            };
        }

    }

    @Configuration
    @EnableConfigurationProperties({WebProperties.class, XssProperties.class})
    public class WebAutoConfiguration implements WebMvcConfigurer {

        @Resource
        private WebProperties webProperties;
        /**
         * 应用名
         */
        @Value("${spring.application.name}")
        private String applicationName;

        @Override
        public void configurePathMatch(PathMatchConfigurer configurer) {
            configurePathMatch(configurer, webProperties.getAdminApi());
            configurePathMatch(configurer, webProperties.getAppApi());
        }

        /**
         * 设置 API 前缀，仅仅匹配 controller 包下的
         *
         * @param configurer 配置
         * @param api        API 配置
         */
        private void configurePathMatch(PathMatchConfigurer configurer, WebProperties.Api api) {
            AntPathMatcher antPathMatcher = new AntPathMatcher(".");
            configurer.addPathPrefix(api.getPrefix(), clazz -> clazz.isAnnotationPresent(RestController.class)
                    && antPathMatcher.match(api.getController(), clazz.getPackage().getName())); // 仅仅匹配 controller 包
        }

        @Bean
        public GlobalExceptionHandler globalExceptionHandler(ApiErrorLogFrameworkService ApiErrorLogFrameworkService) {
            return new GlobalExceptionHandler(applicationName, ApiErrorLogFrameworkService);
        }

        @Bean
        public GlobalResponseBodyHandler globalResponseBodyHandler() {
            return new GlobalResponseBodyHandler();
        }

        @Bean
        @SuppressWarnings("InstantiationOfUtilityClass")
        public WebFrameworkUtils webFrameworkUtils(WebProperties webProperties) {
            // 由于 WebFrameworkUtils 需要使用到 webProperties 属性，所以注册为一个 Bean
            return new WebFrameworkUtils(webProperties);
        }

        // ========== Filter 相关 ==========

        /**
         * 创建 CorsFilter Bean，解决跨域问题
         */
        @Bean
        public FilterRegistrationBean<CorsFilter> corsFilterBean() {
            // 创建 CorsConfiguration 对象
            CorsConfiguration config = new CorsConfiguration();
            config.setAllowCredentials(true);
            config.addAllowedOriginPattern("*"); // 设置访问源地址
            config.addAllowedHeader("*"); // 设置访问源请求头
            config.addAllowedMethod("*"); // 设置访问源请求方法
            // 创建 UrlBasedCorsConfigurationSource 对象
            UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
            source.registerCorsConfiguration("/**", config); // 对接口配置跨域设置
            return createFilterBean(new CorsFilter(source), WebFilterOrderEnum.CORS_FILTER);
        }

        /**
         * 创建 RequestBodyCacheFilter Bean，可重复读取请求内容
         */
        @Bean
        public FilterRegistrationBean<CacheRequestBodyFilter> requestBodyCacheFilter() {
            return createFilterBean(new CacheRequestBodyFilter(), WebFilterOrderEnum.REQUEST_BODY_CACHE_FILTER);
        }

        /**
         * 创建 XssFilter Bean，解决 Xss 安全问题
         */
        @Bean
        public FilterRegistrationBean<XssFilter> xssFilter(XssProperties properties, PathMatcher pathMatcher) {
            return createFilterBean(new XssFilter(properties, pathMatcher), WebFilterOrderEnum.XSS_FILTER);
        }

        /**
         * 创建 DemoFilter Bean，演示模式
         */
        @Bean
        @ConditionalOnProperty(value = "yudao.demo", havingValue = "true")
        public FilterRegistrationBean<DemoFilter> demoFilter() {
            return createFilterBean(new DemoFilter(), WebFilterOrderEnum.DEMO_FILTER);
        }

        private <T extends Filter> FilterRegistrationBean<T> createFilterBean(T filter, Integer order) {
            FilterRegistrationBean<T> bean = new FilterRegistrationBean<>(filter);
            bean.setOrder(order);
            return bean;
        }

    }

}
