package com.open.cloud.leaf.server.service;

import com.open.cloud.leaf.IDGen;
import com.open.cloud.leaf.core.common.PropertyFactory;
import com.open.cloud.leaf.core.common.ZeroIDGen;
import com.open.cloud.leaf.redis.RedisIDGenImpl;
import com.open.cloud.leaf.server.Constants;
import com.open.cloud.leaf.server.exception.InitException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Properties;

/**
 * @author Leijian
 * @date 2020/2/17
 */
public class RedisService {
	private Logger logger = LoggerFactory.getLogger(RedisService.class);

	private IDGen idGen;

	private RedisTemplate redisTemplate;

	public RedisService(RedisTemplate redisTemplate) throws InitException {
		Properties properties = PropertyFactory.getProperties();
		boolean flag = Boolean.parseBoolean(properties.getProperty(Constants.LEAF_REDIS_ENABLE, "true"));
		if (flag) {
			this.redisTemplate = redisTemplate;
			idGen = new RedisIDGenImpl(redisTemplate);
			if (idGen.init()) {
				logger.info("Redis Service Init Successfully");
			} else {
				throw new InitException("Snowflake Service Init Fail");
			}
		} else {
			idGen = new ZeroIDGen();
			logger.info("Zero ID Gen Service Init Successfully");
		}
	}
}
