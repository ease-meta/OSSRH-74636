package com.open.cloud.eureka.gateway.resolver;

import com.open.cloud.eureka.gateway.model.ParamInformation;

import java.util.List;

public interface FieldCheck {
	void fieldCheck(final List<ParamInformation> params);
}