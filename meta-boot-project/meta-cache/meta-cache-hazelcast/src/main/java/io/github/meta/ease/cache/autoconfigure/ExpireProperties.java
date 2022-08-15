package io.github.meta.ease.cache.autoconfigure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * <p>文件名称:  ExpireConfig</p>
 * <p>描述:     </p>
 * <p>创建时间:  2022/8/15</p>
 *
 * @author Abu
 * @version 22.0.1
 * @since 22.0.1
 */
@Data
@ConfigurationProperties(prefix = "ease.cache")
public class ExpireProperties {

    @NestedConfigurationProperty
    private List<ExpireConfig> expireConfig = new LinkedList<>();

    @Data
    public static class ExpireConfig {

        private String name;

        private long ttl = 60L;

        private TimeUnit unit = TimeUnit.SECONDS;

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            ExpireConfig that = (ExpireConfig) o;
            return name.equals(that.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name);
        }
    }


}
