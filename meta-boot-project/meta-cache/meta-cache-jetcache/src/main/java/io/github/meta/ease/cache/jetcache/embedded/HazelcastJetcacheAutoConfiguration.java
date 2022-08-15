package io.github.meta.ease.cache.jetcache.embedded;

import com.alicp.jetcache.CacheBuilder;
import com.alicp.jetcache.autoconfigure.ConfigTree;
import com.alicp.jetcache.autoconfigure.EmbeddedCacheAutoInit;
import com.alicp.jetcache.autoconfigure.JetCacheCondition;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

/**
 * <p>文件名称:  HazelcastJetcacheAutoConfiguration</p>
 * <p>描述:     </p>
 * <p>创建时间:  2022/8/15</p>
 *
 * @author Abu
 * @version 22.0.1
 * @since 22.0.1
 */
@Component
@Conditional(HazelcastJetcacheAutoConfiguration.HazelcastCondition.class)
public class HazelcastJetcacheAutoConfiguration extends EmbeddedCacheAutoInit {
    @Autowired
    HazelcastInstance hazelcastInstance;

    public HazelcastJetcacheAutoConfiguration() {
        super("hazelcast");
    }

    @Override
    protected CacheBuilder initCache(ConfigTree ct, String cacheAreaWithPrefix) {
        String[] split = StringUtils.split(cacheAreaWithPrefix, ".");
        Assert.isTrue(split.length==2,"缓存配置信息异常");
        HazelcastCacheBuilder builder = HazelcastCacheBuilder.createHazelcastCacheBuilder(hazelcastInstance,split[0]);
        parseGeneralConfig(builder, ct);
        return builder;
    }

    public static class HazelcastCondition extends JetCacheCondition {
        public HazelcastCondition() {
            super("hazelcast");
        }
    }
}