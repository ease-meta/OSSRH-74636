package io.github.meta.ease.cache.hazelcast.interceptor;

import com.hazelcast.map.IMap;
import com.hazelcast.spring.cache.HazelcastCache;
import io.github.meta.ease.cache.ExpireCache;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.concurrent.TimeUnit;

/**
 * <p>文件名称:  ExpireHazelcastCache</p>
 * <p>描述:     重写Cache，覆盖PUT方法,新增过期时间 </p>
 * <p>创建时间:  2022/8/12</p>
 *
 * @author Abu
 * @version 22.0.1
 * @since 22.0.1
 */
public class ExpireHazelcastCache extends HazelcastCache implements ExpireCache {
    @Setter
    @Getter
    private long ttl;

    public ExpireHazelcastCache(IMap<Object, Object> map) {
        super(map);
    }

    public ExpireHazelcastCache(IMap<Object, Object> map, long ttl) {
        super(map);
        this.ttl = ttl;
    }

    @Override
    public void put(Object key, Object value) {
        if (key != null) {
            if (NumberUtils.compare(getTtl(), 0) == 1) {
                super.getNativeCache().set(key, toStoreValue(value), getTtl(), TimeUnit.MILLISECONDS);
            }
            super.put(key, value);
        }
    }

}
