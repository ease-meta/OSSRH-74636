package com.open.cloud.sofa.provider;

import com.open.cloud.business.api.EchoService;
import com.open.cloud.core.runtime.api.annotation.ProviderService;
import org.springframework.stereotype.Service;

/**
 * @author Leijian
 * @date 2020/5/6
 */
@Service
@ProviderService
public class EchoServiceImpl implements EchoService {
	@Override
	public String echo(String message) {
		return message;
	}
}
