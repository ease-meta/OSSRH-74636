package io.github.meta.ease.cache.jetcache.embedded;

import com.alicp.jetcache.embedded.AbstractEmbeddedCache;
import com.alicp.jetcache.embedded.EmbeddedCacheConfig;
import com.alicp.jetcache.embedded.InnerMap;
import com.hazelcast.cache.HazelcastExpiryPolicy;
import com.hazelcast.cache.ICache;
import com.hazelcast.cache.impl.HazelcastServerCachingProvider;
import com.hazelcast.config.CacheConfig;
import com.hazelcast.config.EvictionConfig;
import com.hazelcast.core.HazelcastInstance;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.math.NumberUtils;

import javax.cache.Caching;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.hazelcast.cache.HazelcastCachingProvider.propertiesByInstanceItself;

/**
 * <p>文件名称:  HazelcastEmbeddedCache</p>
 * <p>描述:     </p>
 * <p>创建时间:  2022/8/16</p>
 *
 * @author Abu
 * @version 22.0.1
 * @since 22.0.1
 */
@Slf4j
public class HazelcastEmbeddedCache<K, V> extends AbstractEmbeddedCache<K, V> {
    private ICache<Object, Object> unwrappedCache;

    private HazelcastExpiryPolicy expiryPolicy;

    public HazelcastEmbeddedCache(EmbeddedCacheConfig<K, V> config, HazelcastInstance instance, String cacheArea) {
        super(config);
        EvictionConfig evictionConfig = new EvictionConfig();
        evictionConfig.setSize(config.getLimit());

        CacheConfig<Object, Object> cacheConfig = new CacheConfig<>(cacheArea);
        cacheConfig.setEvictionConfig(evictionConfig);
        cacheConfig.setStatisticsEnabled(true);
        cacheConfig.setManagementEnabled(true);

        long createMillis = config.getExpireAfterWriteInMillis();
        long accessMillis = config.getExpireAfterAccessInMillis();
        if (NumberUtils.compare(createMillis,0)!=1){
            createMillis = Long.MAX_VALUE;
        }
        if (NumberUtils.compare(accessMillis,0)!=1){
            accessMillis = Long.MAX_VALUE;
        }
        expiryPolicy = new HazelcastExpiryPolicy(createMillis, accessMillis,createMillis);

        unwrappedCache = Caching.getCachingProvider(HazelcastServerCachingProvider.class.getName())
                .getCacheManager(null, null, propertiesByInstanceItself(instance))
                .createCache(cacheArea, cacheConfig).unwrap(ICache.class);

    }

    @Override
    protected InnerMap createAreaCache() {
        return new InnerMap() {
            @Override
            public Object getValue(Object key) {
                if (key == null) {
                    return null;
                }
                return unwrappedCache.get(key, expiryPolicy);

            }

            @Override
            public Map getAllValues(Collection keys) {
                return unwrappedCache.getAll((Set<Object>) keys.stream().collect(Collectors.toSet()));
            }

            @Override
            public void putValue(Object key, Object value) {
                if (key != null) {
                    unwrappedCache.put(key, value, expiryPolicy);
                }
            }

            @Override
            public void putAllValues(Map map) {
                unwrappedCache.putAll(map, expiryPolicy);
            }

            @Override
            public boolean removeValue(Object key) {
                return unwrappedCache.remove(key,expiryPolicy);
            }

            @Override
            public boolean putIfAbsentValue(Object key, Object value) {
                return unwrappedCache.putIfAbsent(key, value, expiryPolicy);
            }

            @Override
            public void removeAllValues(Collection keys) {
                unwrappedCache.removeAll((Set<Object>) keys.stream().collect(Collectors.toSet()));
            }
        };

    }

    @Override
    public <T> T unwrap(Class<T> clazz) {
        if (clazz.equals(ICache.class)) {
            return (T) unwrappedCache;
        }
        throw new IllegalArgumentException(clazz.getName());
    }
}
