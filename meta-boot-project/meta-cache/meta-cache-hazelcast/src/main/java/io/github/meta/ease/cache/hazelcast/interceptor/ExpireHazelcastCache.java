package io.github.meta.ease.cache.hazelcast.interceptor;

import com.hazelcast.map.IMap;
import com.hazelcast.spring.cache.HazelcastCache;
import io.github.meta.ease.cache.ExpireCache;

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

    public ExpireHazelcastCache(IMap<Object, Object> map) {
        super(map);
    }

    @Override
    public void put(Object key, Object value) {
        super.put(key, value);
    }

}
