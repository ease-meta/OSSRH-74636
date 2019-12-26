package com.open.cloud.eureka.gateway.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.open.cloud.eureka.gateway.model.HeadersInfo;
import com.open.cloud.eureka.gateway.model.SystemRequest;
import com.open.cloud.eureka.gateway.repository.HeadersConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.server.ServerWebExchange;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

@Component
public class RequestBodyUtil {
	private static HeadersConfigRepository headersConfigRepository;

	@Autowired
	public RequestBodyUtil(final HeadersConfigRepository headersConfigRepository) {
		RequestBodyUtil.headersConfigRepository = headersConfigRepository;
	}

	public static HashMap getSystemHeader(final ServerWebExchange exchange) {
		final HashMap<String, Object> resultMap = new HashMap<String, Object>();
		final SystemRequest systemRequest = (SystemRequest) JSONObject.parseObject((String) exchange.getAttribute("requestBody"), (Class) SystemRequest.class);
		if (null != systemRequest && null != systemRequest.getSysHead()) {
			final HashMap headers = (HashMap) JSONObject.parseObject(JSON.toJSONString((Object) systemRequest.getSysHead()), (Class) HashMap.class);
			final List<HeadersInfo> headerList = RequestBodyUtil.headersConfigRepository.getHeaders();
			if (!CollectionUtils.isEmpty((Collection) headerList)) {
				headerList.forEach(headersInfo -> resultMap.put(headersInfo.getName(), headers.get(headersInfo.getName())));
			}
		}
		return resultMap;
	}
}
