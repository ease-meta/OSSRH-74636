package com.open.cloud.eureka.gateway.util;

import com.open.cloud.eureka.gateway.exception.CommonException;
import com.open.cloud.eureka.gateway.model.ParamInformation;
import com.open.cloud.eureka.gateway.resolver.FieldCheck;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

@Component
public class LimiterFieldCheckUtil implements ApplicationContextAware {
	private static ApplicationContext applicationContext;

	public void setApplicationContext(final ApplicationContext applicationContext) throws BeansException {
		LimiterFieldCheckUtil.applicationContext = applicationContext;
	}

	public static void fieldCheck(final String name, final List<ParamInformation> params) {
		if (StringUtils.isEmpty(name)) {
			throw new CommonException().setCode("1015").setMsg("resolver not found");
		}
		final String beanName = name.substring(name.lastIndexOf("@") + 1, name.length() - 1);
		FieldCheck fieldCheck;
		try {
			fieldCheck = LimiterFieldCheckUtil.applicationContext.getBean(beanName, FieldCheck.class);
		} catch (BeansException e) {
			throw new CommonException().setCode("1015").setMsg("resolver not found");
		}
		if (!Objects.isNull(fieldCheck)) {
			fieldCheck.fieldCheck(params);
			return;
		}
		throw new CommonException().setCode("1015").setMsg("resolver not found");
	}

	public static boolean isInteger(final String value) {
		return !StringUtils.isEmpty(value) && PatternUtil.IS_INTEGER.matcher(value).matches();
	}
}
