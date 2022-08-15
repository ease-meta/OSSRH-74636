package io.github.meta.ease.cache.autoconfigure;

import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import lombok.SneakyThrows;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.hazelcast.HazelcastProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

/**
 * <p>文件名称:  HazelcastAutoConfiguration</p>
 * <p>描述:     </p>
 * <p>创建时间:  2022/8/11</p>
 *
 * @author Abu
 * @version 22.0.1
 * @since 22.0.1
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnClass(HazelcastInstance.class)
@AutoConfigureBefore(name = "org.springframework.boot.autoconfigure.hazelcast.HazelcastAutoConfiguration")
public class HazelcastAutoConfiguration {

    @SneakyThrows
    @Bean
    Config config(HazelcastProperties hazelcastProperties) {
        Config config = new Config();
        if (!ObjectUtils.isEmpty(hazelcastProperties.getConfig())){
            config.setConfigurationUrl(hazelcastProperties.resolveConfigLocation().getURL());
            return config;
        }
        return config;
    }

    @Bean
    HazelcastInstance hazelcastInstance(Config config) {
        return getHazelcastInstance(config);
    }

    private static HazelcastInstance getHazelcastInstance(Config config) {
        if (StringUtils.hasText(config.getInstanceName())) {
            return Hazelcast.getOrCreateHazelcastInstance(config);
        }
        return Hazelcast.newHazelcastInstance(config);
    }
}
