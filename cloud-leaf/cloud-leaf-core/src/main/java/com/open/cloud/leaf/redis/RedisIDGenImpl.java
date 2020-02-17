package com.open.cloud.leaf.redis;

import com.open.cloud.leaf.IDGen;
import com.open.cloud.leaf.core.common.Result;
import com.open.cloud.leaf.core.common.Status;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;

import java.util.HashMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author Leijian
 * @date 2020/2/17
 */
public class RedisIDGenImpl implements IDGen {

	private RedisTemplate redisTemplate;

	private HashMap<String, RedisAtomicLong> hashMap = new HashMap<>();

	private ReadWriteLock lock = new ReentrantReadWriteLock();

	public RedisIDGenImpl(RedisTemplate redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	@Override
	public Result get(String key) {
		//先加读锁，后写如果有写锁，则读锁等待写锁释放资源
		try {
			lock.readLock().lock();
			if (hashMap.containsKey(key)) {
				long id = hashMap.get(key).getAndIncrement();
				return new Result(id, Status.SUCCESS);
			}
		} finally {
			lock.readLock().unlock();
		}

		try {
			lock.writeLock().lock();
			if (!hashMap.containsKey(key)) {
				RedisAtomicLong redisAtomicLong = new RedisAtomicLong(key, redisTemplate.getConnectionFactory());
				hashMap.put(key, redisAtomicLong);
			}
			long id = hashMap.get(key).getAndIncrement();
			return new Result(id, Status.SUCCESS);
		} finally {
			lock.writeLock().unlock();
		}
	}

	@Override
	public boolean init() {
		return true;
	}
}
