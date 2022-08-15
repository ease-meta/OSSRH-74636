package io.github.meta.ease.cache.autoconfigure;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.spring.cache.HazelcastCacheManager;
import io.github.meta.ease.cache.hazelcast.interceptor.ExpireHazelcastSimpleCachingConfigurerSupport;
import io.github.meta.ease.cache.beans.factory.config.CacheInterceptorBeanPostProcessor;
import io.github.meta.ease.cache.hazelcast.interceptor.ExpireCacheResolver;
import io.github.meta.ease.cache.hazelcast.interceptor.SimpleCachingConfigurerSupport;
import io.github.meta.ease.cache.hazelcast.interceptor.SimpleKeyGenerator;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.hazelcast.HazelcastAutoConfiguration;
import org.springframework.cache.CacheManager;
import org.springframework.cache.interceptor.CacheAspectSupport;
import org.springframework.cache.interceptor.CacheResolver;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * <p>文件名称:  CacheAutoConfiguration</p>
 * <p>描述:     </p>
 * <p>创建时间:  2022/8/12</p>
 *
 * @author Abu
 * @version 22.0.1
 * @since 22.0.1
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnClass(CacheManager.class)
@ConditionalOnBean(CacheAspectSupport.class)
@Import({CacheAutoConfiguration.ExpireHazelcastSimpleCachingConfigurer.class})
public class CacheAutoConfiguration {

    @Bean
    public CacheInterceptorBeanPostProcessor cacheInterceptorBeanPostProcessor() {
        return new CacheInterceptorBeanPostProcessor();
    }

    @Configuration(proxyBeanMethods = false)
    @ConditionalOnClass({HazelcastInstance.class, HazelcastCacheManager.class})
    @AutoConfigureAfter(HazelcastAutoConfiguration.class)
    static class ExpireHazelcastSimpleCachingConfigurer {

        @Bean
        SimpleCachingConfigurerSupport configurerSupport(@Autowired CacheManager cacheManager, @Autowired CacheResolver cacheResolver, ObjectProvider<KeyGenerator> keyGenerator) {
            return new ExpireHazelcastSimpleCachingConfigurerSupport(cacheManager, cacheResolver, keyGenerator);
        }

        @Bean
        ExpireCacheResolver cacheResolver(@Autowired CacheManager cacheManager) {
            return new ExpireCacheResolver(cacheManager);
        }

        @Bean
        SimpleKeyGenerator keyGenerator() {
            return new SimpleKeyGenerator();
        }

    }
}
