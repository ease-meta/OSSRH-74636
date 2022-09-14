package io.github.meta.ease.cache.hazelcast.interceptor;

import com.hazelcast.core.DistributedObject;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import com.hazelcast.spring.cache.HazelcastCache;
import io.github.meta.ease.cache.ExpireCacheManager;
import io.github.meta.ease.cache.autoconfigure.ExpireProperties;
import io.github.meta.ease.cache.autoconfigure.ExpireProperties.ExpireConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.Cache;

import java.time.Duration;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static org.springframework.util.Assert.isTrue;

/**
 * <p>文件名称:  ExpireHazelcastCacheManager</p>
 * <p>描述:     </p>
 * <p>创建时间:  2022/8/12</p>
 *
 * @author Abu
 * @version 22.0.1
 * @since 22.0.1
 */
@Slf4j
public class ExpireHazelcastCacheManager implements ExpireCacheManager {

    public static final String CACHE_PROP = "hazelcast.spring.cache.prop";

    private final ConcurrentMap<String, Cache> caches = new ConcurrentHashMap<String, Cache>();

    private HazelcastInstance hazelcastInstance;

    /**
     * Default cache value retrieval timeout. Apply to all caches.
     * Can be overridden setting a cache specific value to readTimeoutMap.
     */
    private long defaultReadTimeout;

    /**
     * Holds cache specific value retrieval timeouts. Override defaultReadTimeout for specified caches.
     */
    private Map<String, Long> readTimeoutMap = new HashMap<String, Long>();

    private Map<String, ExpireConfig> expireProperties = new HashMap<>();

    private ExpireHazelcastCacheManager() {
    }

    public ExpireHazelcastCacheManager(HazelcastInstance hazelcastInstance, ExpireProperties expireProperties) {
        this.hazelcastInstance = hazelcastInstance;
        this.expireProperties = expireProperties.getExpireConfig().
                stream()
                .filter(distinctByKey(ExpireConfig::getName))
                .collect(
                        Collectors.toMap(ExpireConfig::getName,
                                expireConfig -> expireConfig));

    }

    private static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    @Override
    public Cache getCache(String name) {
        Cache cache = caches.get(name);
        if (cache == null) {
            IMap<Object, Object> map = hazelcastInstance.getMap(name);
            if (this.expireProperties.containsKey(name)) {
                ExpireConfig expireConfig = this.expireProperties.get(name);
                cache = new ExpireHazelcastCache(map, parseCacheExpire(expireConfig));
            } else {
                cache = new ExpireHazelcastCache(map);
            }

            long cacheTimeout = calculateCacheReadTimeout(name);
            ((HazelcastCache) cache).setReadTimeout(cacheTimeout);
            Cache currentCache = caches.putIfAbsent(name, cache);
            if (currentCache != null) {
                cache = currentCache;
            }
        }
        return cache;
    }

    private long parseCacheExpire(ExpireConfig expireConfig) {
        log.trace("CacheExpire ttl:{}, CacheExpire unit:{}", expireConfig.getTtl(), expireConfig.getUnit());
        return Duration.ofMillis(expireConfig.getUnit().toMillis(expireConfig.getTtl())).toMillis();

    }

    @Override
    public Collection<String> getCacheNames() {
        Set<String> cacheNames = new HashSet<String>();
        Collection<DistributedObject> distributedObjects = hazelcastInstance.getDistributedObjects();
        for (DistributedObject distributedObject : distributedObjects) {
            if (distributedObject instanceof IMap) {
                IMap<?, ?> map = (IMap) distributedObject;
                cacheNames.add(map.getName());
            }
        }
        return cacheNames;
    }

    private long calculateCacheReadTimeout(String name) {
        Long timeout = getReadTimeoutMap().get(name);
        return timeout == null ? defaultReadTimeout : timeout;
    }

    public HazelcastInstance getHazelcastInstance() {
        return hazelcastInstance;
    }

    public void setHazelcastInstance(HazelcastInstance hazelcastInstance) {
        this.hazelcastInstance = hazelcastInstance;
    }

    private void parseOptions(String options) {
        if (null == options || options.trim().isEmpty()) {
            return;
        }
        for (String option : options.split(",")) {
            parseOption(option);
        }
    }

    private void parseOption(String option) {
        String[] keyValue = option.split("=");
        isTrue(keyValue.length != 0, "blank key-value pair");
        isTrue(keyValue.length <= 2, String.format("key-value pair %s with more than one equals sign", option));

        String key = keyValue[0].trim();
        String value = (keyValue.length == 1) ? null : keyValue[1].trim();

        isTrue(value != null && !value.isEmpty(), String.format("value for %s must not be null or empty", key));

        if ("defaultReadTimeout".equals(key)) {
            defaultReadTimeout = Long.parseLong(value);
        } else {
            readTimeoutMap.put(key, Long.parseLong(value));
        }
    }

    /**
     * Return default cache value retrieval timeout in milliseconds.
     */
    public long getDefaultReadTimeout() {
        return defaultReadTimeout;
    }

    /**
     * Set default cache value retrieval timeout. Applies to all caches, if not defined a cache specific timeout.
     *
     * @param defaultReadTimeout default cache retrieval timeout in milliseconds. 0 or negative values disable timeout.
     */
    public void setDefaultReadTimeout(long defaultReadTimeout) {
        this.defaultReadTimeout = defaultReadTimeout;
    }

    /**
     * Return cache-specific value retrieval timeouts. Map keys are cache names, values are cache retrieval timeouts in
     * milliseconds.
     */
    public Map<String, Long> getReadTimeoutMap() {
        return readTimeoutMap;
    }

    /**
     * Set the cache ead-timeout params
     *
     * @param options cache read-timeout params, auto-wired by Spring automatically by getting value of
     *                hazelcast.spring.cache.prop parameter
     */
    @Autowired
    public void setCacheOptions(@Value("${" + CACHE_PROP + ":}") String options) {
        parseOptions(options);
    }


}
