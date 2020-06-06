package com.open.cloud.cache.test;


import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.junit.Assert;

import java.time.Duration;

public class CaffeineCacheConfigurationTest {
	Cache<String, String> cache;
	@org.junit.Before
	public void setUp() throws Exception {
		cache = Caffeine.newBuilder().maximumSize(2).recordStats().initialCapacity(2).expireAfterAccess(Duration.ofDays(7)).build();
	}

	@org.junit.After
	public void tearDown() throws Exception {
	}

	@org.junit.Test
	public void testCache(){
		cache.put("1","1");
		cache.put("2","2");
		cache.put("3","3");
		cache.put("4","4");
		Assert.assertEquals("1",cache.getIfPresent("1"));
		Assert.assertNull(cache.getIfPresent("1111"));
	}
}