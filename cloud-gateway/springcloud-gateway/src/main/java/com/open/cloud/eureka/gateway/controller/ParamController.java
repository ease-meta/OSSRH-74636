package com.open.cloud.eureka.gateway.controller;

import com.alibaba.fastjson.JSON;
import com.open.cloud.eureka.gateway.common.ResultDO;
import com.open.cloud.eureka.gateway.exception.CommonException;
import com.open.cloud.eureka.gateway.model.HeadersInfo;
import com.open.cloud.eureka.gateway.model.ParamInformation;
import com.open.cloud.eureka.gateway.model.ParamListInformation;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping({"/gateway/param"})
public class ParamController {
	@Value("${json.predicate.path:null}")
	String predicatePath;
	@Value("${json.filter.path:null}")
	String filterPath;
	@Value("${json.resolver.path:null}")
	String resolverPath;
	@Value("${json.headers.path:null}")
	String headersPath;
	List<ParamListInformation> predicateList;
	List<ParamListInformation> filterList;
	List<ParamListInformation> resolverList;
	List<HeadersInfo> headerList;

	@GetMapping({"/getParams/{name}"})
	public ResponseEntity<?> getParams(@PathVariable final String name) {
		List<ParamListInformation> list = null;
		try {
			switch (name) {
				case "predicate": {
					list = Optional.ofNullable(predicateList).orElse(JSON.parseArray(FileUtils.readFileToString(new File(predicatePath), "UTF-8"), (Class) ParamListInformation.class));
					break;
				}
				case "filter": {
					list = Optional.ofNullable(filterList).orElse(JSON.parseArray(FileUtils.readFileToString(new File(filterPath), "UTF-8"), (Class) ParamListInformation.class));
					break;
				}
				case "resolver": {
					list = Optional.ofNullable(resolverList).orElse(JSON.parseArray(FileUtils.readFileToString(new File(resolverPath), "UTF-8"), (Class) ParamListInformation.class));
					break;
				}
				case "headers": {
					final List<HeadersInfo> headers = Optional.ofNullable(headerList).orElse(JSON.parseArray(FileUtils.readFileToString(new File(headersPath), "UTF-8"), (Class) HeadersInfo.class));
					return (ResponseEntity<?>) ResponseEntity.ok((Object) new ResultDO().setCode("0000").setMessage("Success").setData(headers));
				}
				default: {
					list = new ArrayList<ParamListInformation>();
					break;
				}
			}
		} catch (IOException e) {
			throw new CommonException().setCode("1006");
		}
		return (ResponseEntity<?>) ResponseEntity.ok((Object) new ResultDO().setCode("0000").setMessage("读取参数成功").setData(list));
	}

	public ParamListInformation getFilterByName(final FilterDefinition filter) {
		final String path = filterPath;
		try {
			final List<ParamListInformation> list = Optional.ofNullable(filterList).orElse(JSON.parseArray(FileUtils.readFileToString(new File(path), "UTF-8"), (Class) ParamListInformation.class));
			for (final ParamListInformation param : list) {
				if (param.getName().equals(filter.getName())) {
					final List<ParamInformation> args = param.getArgs();
					for (final ParamInformation temp : args) {
						if (filter.getArgs().containsKey(temp.getName())) {
							temp.setValue(filter.getArgs().get(temp.getName()));
						}
					}
					return param;
				}
			}
			return null;
		} catch (IOException e) {
			throw new CommonException().setCode("1006");
		}
	}

	public ParamListInformation getPredicateByName(final PredicateDefinition predicate) {
		try {
			final List<ParamListInformation> list = Optional.ofNullable(predicateList).orElse(JSON.parseArray(FileUtils.readFileToString(new File(predicatePath), "UTF-8"), (Class) ParamListInformation.class));
			for (final ParamListInformation param : list) {
				if (param.getName().equals(predicate.getName())) {
					final List<ParamInformation> args = param.getArgs();
					for (final ParamInformation temp : args) {
						if (predicate.getArgs().containsKey(temp.getName())) {
							temp.setValue(predicate.getArgs().get(temp.getName()));
						}
					}
					return param;
				}
			}
			return null;
		} catch (IOException e) {
			throw new CommonException().setCode("1006");
		}
	}
}
