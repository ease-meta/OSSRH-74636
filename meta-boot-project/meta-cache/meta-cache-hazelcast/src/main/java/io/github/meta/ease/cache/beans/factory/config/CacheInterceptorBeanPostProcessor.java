package io.github.meta.ease.cache.beans.factory.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.cache.interceptor.CacheInterceptor;

/**
 * <p>文件名称:  CacheBeanPostProcessor</p>
 * <p>描述:     </p>
 * <p>创建时间:  2022/8/15</p>
 *
 * @author Abu
 * @version 22.0.1
 * @since 22.0.1
 */
public class CacheInterceptorBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof CacheInterceptor) {
            return new IntegrationCacheInterceptor((CacheInterceptor) bean);
        }
        return bean;
    }
}
