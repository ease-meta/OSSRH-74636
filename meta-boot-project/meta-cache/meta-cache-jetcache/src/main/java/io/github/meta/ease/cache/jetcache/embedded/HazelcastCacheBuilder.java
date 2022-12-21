package io.github.meta.ease.cache.jetcache.embedded;

import com.alicp.jetcache.embedded.EmbeddedCacheBuilder;
import com.alicp.jetcache.embedded.EmbeddedCacheConfig;
import com.hazelcast.core.HazelcastInstance;

/**
 * <p>文件名称:  HazelcastCacheBuilder</p>
 * <p>描述:     </p>
 * <p>创建时间:  2022/8/16</p>
 *
 * @author Abu
 * @version 22.0.1
 * @since 22.0.1
 */
public class HazelcastCacheBuilder<T extends EmbeddedCacheBuilder<T>> extends EmbeddedCacheBuilder<T> {

    public static class HazelcastCacheBuilderImpl extends HazelcastCacheBuilder<HazelcastCacheBuilderImpl> {

        public HazelcastCacheBuilderImpl(HazelcastInstance hazelcastInstance, String cacheArea) {
            super(hazelcastInstance, cacheArea);
        }
    }

    public static HazelcastCacheBuilderImpl createHazelcastCacheBuilder(HazelcastInstance hazelcastInstance,String cacheArea) {
        return new HazelcastCacheBuilderImpl(hazelcastInstance,cacheArea);
    }
    protected HazelcastCacheBuilder(HazelcastInstance hazelcastInstance, String cacheArea) {
        buildFunc((c) -> new HazelcastEmbeddedCache((EmbeddedCacheConfig) c, hazelcastInstance,cacheArea));
    }
}