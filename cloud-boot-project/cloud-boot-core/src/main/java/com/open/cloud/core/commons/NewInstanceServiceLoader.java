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
package com.open.cloud.core.commons;

import lombok.SneakyThrows;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.ServiceLoader;

/**
 * The type New instance service loader.
 *
 * @author Leijian
 * @date 2020 /3/26
 */
public final class NewInstanceServiceLoader {

	private static final Map<Class, Collection<Class<?>>> SERVICE_MAP = new HashMap<>();

	private NewInstanceServiceLoader() {

	}

	/**
	 * Register.
	 *
	 * @param <T> the type parameter
	 * @param service the service
	 */
	public static <T> void register(final Class<T> service) {
		for (T each : ServiceLoader.load(service)) {
			registerServiceClass(service, each);
		}
	}

	private static <T> void registerServiceClass(final Class<T> service, final T instance) {
		Collection<Class<?>> serviceClasses = SERVICE_MAP.get(service);
		if (null == serviceClasses) {
			serviceClasses = new LinkedHashSet<>();
		}
		serviceClasses.add(instance.getClass());
		SERVICE_MAP.put(service, serviceClasses);
	}

	/**
	 * New service instances collection.
	 *
	 * @param <T> the type parameter
	 * @param service the service
	 * @return the collection
	 */
	@SneakyThrows
	@SuppressWarnings("unchecked")
	public static <T> Collection<T> newServiceInstances(final Class<T> service) {
		Collection<T> result = new LinkedList<>();
		if (null == SERVICE_MAP.get(service)) {
			return result;
		}
		for (Class<?> each : SERVICE_MAP.get(service)) {
			result.add((T) each.newInstance());
		}
		return result;
	}
}
