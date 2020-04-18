package com.open.cloud.dubbo.provider;

import com.open.cloud.business.api.DemoService;
import org.apache.dubbo.config.annotation.Service;

/**
 * @author Leijian
 * @date 2020/4/18
 */
@Service
@org.springframework.stereotype.Service
public class DemoServiceImpl implements DemoService {
	@Override
	public String sayHello(String message) {
		return message;
	}
}
