/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.open.cloud.nacos.gateway.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * 路由实体类
 */
public class RouteEntity {

	//路由id
	private String id;

	//路由断言集合
	private List<PredicateEntity> predicates = new ArrayList<>();

	//路由过滤器集合
	private List<FilterEntity> filters = new ArrayList<>();

	//路由转发的目标uri
	private String uri;

	//路由执行的顺序
	private int order = 0;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<PredicateEntity> getPredicates() {
		return predicates;
	}

	public void setPredicates(List<PredicateEntity> predicates) {
		this.predicates = predicates;
	}

	public List<FilterEntity> getFilters() {
		return filters;
	}

	public void setFilters(List<FilterEntity> filters) {
		this.filters = filters;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}
}