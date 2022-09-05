package io.github.meta.ease.cache.jetcache.embedded;

import com.alicp.jetcache.CacheBuilder;
import com.alicp.jetcache.autoconfigure.ConfigTree;
import com.alicp.jetcache.autoconfigure.EmbeddedCacheAutoInit;
import com.alicp.jetcache.autoconfigure.JetCacheCondition;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

/**
 * <p>文件名称:  HazelcastJetcacheAutoConfiguration</p>
 * <p>描述:     </p>
 * <p>创建时间:  2022/8/16</p>
 *
 * @author Abu
 * @version 22.0.1
 * @since 22.0.1
 */
@Component
@Conditional(HazelcastJetcacheAutoConfiguration.HazelcastCondition.class)
@ConditionalOnClass(HazelcastInstance.class)
public class HazelcastJetcacheAutoConfiguration extends EmbeddedCacheAutoInit {
   private final HazelcastInstance hazelcastInstance ;

    public HazelcastJetcacheAutoConfiguration(ObjectProvider<HazelcastInstance> hazelcastInstance) {
        super("hazelcast");
        this.hazelcastInstance = hazelcastInstance.getIfUnique();
    }

    /**
     * 初始化 CacheBuilder
     * @param ct
     * @param cacheAreaWithPrefix
     * @return
     */
    @Override
    protected CacheBuilder initCache(ConfigTree ct, String cacheAreaWithPrefix) {
        String[] split = StringUtils.split(cacheAreaWithPrefix, ".");
        Assert.isTrue(split.length==2,"The cache configuration information is abnormal, the prefix should be local. or remote.");
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