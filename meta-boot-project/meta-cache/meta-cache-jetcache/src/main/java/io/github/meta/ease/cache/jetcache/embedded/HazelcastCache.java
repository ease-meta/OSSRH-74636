package io.github.meta.ease.cache.jetcache.embedded;

import com.alicp.jetcache.embedded.AbstractEmbeddedCache;
import com.alicp.jetcache.embedded.EmbeddedCacheConfig;
import com.alicp.jetcache.embedded.InnerMap;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;

import java.util.Collection;
import java.util.Map;

/**
 * <p>文件名称:  HazelcastCache</p>
 * <p>描述:     </p>
 * <p>创建时间:  2022/8/15</p>
 *
 * @author Abu
 * @version 22.0.1
 * @since 22.0.1
 */
public class HazelcastCache<K, V> extends AbstractEmbeddedCache<K, V> {
    private HazelcastInstance hazelcastInstance;

    private String cacheArea;

    public HazelcastCache(EmbeddedCacheConfig<K, V> config, HazelcastInstance hazelcastInstance, String cacheArea) {
        super(config);
        this.hazelcastInstance = hazelcastInstance;
        this.cacheArea = cacheArea;
    }

    @Override
    protected InnerMap createAreaCache() {
        return new InnerMap() {
            @Override
            public Object getValue(Object key) {
                return null;
            }

            @Override
            public Map getAllValues(Collection keys) {
                return null;
            }

            @Override
            public void putValue(Object key, Object value) {
                IMap<Object, Object> map = hazelcastInstance.getMap(cacheArea);
                map.set(key,value);
            }

            @Override
            public void putAllValues(Map map) {

            }

            @Override
            public boolean removeValue(Object key) {
                return false;
            }

            @Override
            public boolean putIfAbsentValue(Object key, Object value) {
                return false;
            }

            @Override
            public void removeAllValues(Collection keys) {

            }
        };
    }

    @Override
    public <T> T unwrap(Class<T> clazz) {
        return null;
    }
}
