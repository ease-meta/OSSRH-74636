package com.open.cloud.workflow.framework.serviceImpl;

import com.open.cloud.workflow.common.utils.MD5Util;
import com.open.cloud.workflow.framework.annotation.Resources;
import com.open.cloud.workflow.framework.entity.Resource;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ScanMappings {

	@Autowired
	private ResourceServiceImpl resourceService;

	@Autowired
	private RequestMappingHandlerMapping handlerMapping;

	private String[] emptyArray = new String[] { "" };

	@Value("${api.version}")
	private String apiVersion;

	/**
	 * 扫描资源插入数据库
	 */
	@PostConstruct
	public void doScan() {
		resourceService.saveOrUpdateBatch(handlerMapping.getHandlerMethods().values().stream().map(this::getResources)
		.flatMap(Collection::stream).collect(Collectors.toList()));
		//handlerMapping.getHandlerMethods().values().stream().forEach(this::getResources);
	}

	/**
	 * 获取Resource
	 *
	 * @param handlerMethod
	 * @return
	 */
	public List<Resource> getResources(HandlerMethod handlerMethod) {
		Resources res = handlerMethod.getMethodAnnotation(Resources.class);
		if (Objects.isNull(res)) {
			return Collections.emptyList();
		}
		RequestMapping requestMappingAnnotation = handlerMethod.getBeanType().getAnnotation(RequestMapping.class);
		RequestMapping methodMappingAnnotation = handlerMethod.getMethodAnnotation(RequestMapping.class);
		if (Objects.isNull(requestMappingAnnotation) && Objects.isNull(methodMappingAnnotation)) {
			return Collections.emptyList();
		}
		String[] requestMappings = Objects.nonNull(requestMappingAnnotation) ? requestMappingAnnotation.value()
				: emptyArray;
		String[] methodMappings = Objects.nonNull(methodMappingAnnotation) ? methodMappingAnnotation.path()
				: emptyArray;
		RequestMethod[] method = Objects.nonNull(methodMappingAnnotation) ? methodMappingAnnotation.method()
				: new RequestMethod[0];
		requestMappings = ArrayUtils.isEmpty(requestMappings) ? emptyArray : requestMappings;
		methodMappings = ArrayUtils.isEmpty(methodMappings) ? emptyArray : methodMappings;
		Set<String> mappings = new HashSet<>(1);
		for (String reqMapping : requestMappings) {
			for (String methodMapping : methodMappings) {
				mappings.add(reqMapping + methodMapping);
			}
		}
		List<Resource> resources = new ArrayList<>(1);
		for (RequestMethod requestMethod : method) {
			for (String mapping : mappings) {
				// 接口描述
				mapping = StringUtils.replaceIgnoreCase(mapping, "${api.version}", apiVersion);
				if(!StringUtils.startsWith(mapping, "/")) {
					mapping="/"+mapping;
				}
				Resource resource = new Resource();
				resource.setResourceName(StringUtils.defaultIfBlank(res.description(), "未命名资源路径"));
				resource.setMapping(mapping);
				resource.setMethod(requestMethod.name());
				resource.setAuthType(String.valueOf(res.auth().getValue()));
				resource.setPerm(resourceService.getResourcePermTag(requestMethod.name(), mapping));
				resource.setId(MD5Util.computeMD5(resource.getPerm()));
				resource.setUpdateTime(LocalDateTime.now());
				resources.add(resource);
				//resourceService.insert(resource);
			}
		}
		return resources;
	}

}
