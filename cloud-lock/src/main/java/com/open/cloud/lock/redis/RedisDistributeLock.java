package com.open.cloud.lock.redis;

import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author Leijian
 * @date 2020/2/19
 */
public class RedisDistributeLock implements Lock {
	//锁名称
	public static final String LOCK_PREFIX = "redis_lock";
	//加锁失效时间，毫秒
	public static final int LOCK_EXPIRE = 300; // ms

	RedisTemplate redisTemplate;

	String key;

	@Override
	public void lock() {
		this.tryLock();
	}

	@Override
	public void lockInterruptibly() throws InterruptedException {
		this.lock();
	}

	@Override
	public boolean tryLock() {
		String lock = LOCK_PREFIX + key;
		return (Boolean) redisTemplate.execute((RedisCallback) connection -> {
			long expireAt = System.currentTimeMillis() + LOCK_EXPIRE + 1;
			Boolean acquire = connection.setNX(lock.getBytes(), String.valueOf(expireAt).getBytes());
			if (acquire) {
				return true;
			} else {
				byte[] value = connection.get(lock.getBytes());
				if (Objects.nonNull(value) && value.length > 0) {
					long expireTime = Long.parseLong(new String(value));
					// 如果锁已经过期
					if (expireTime < System.currentTimeMillis()) {
						// 重新加锁，防止死锁
						byte[] oldValue = connection.getSet(lock.getBytes(), String.valueOf(System.currentTimeMillis() + LOCK_EXPIRE + 1).getBytes());
						return Long.parseLong(new String(oldValue)) < System.currentTimeMillis();
					}
				}
			}
			return false;
		});
	}

	@Override
	public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
		return false;
	}

	@Override
	public void unlock() {
		redisTemplate.delete(key);
	}

	@Override
	public Condition newCondition() {
		return null;
	}
}
