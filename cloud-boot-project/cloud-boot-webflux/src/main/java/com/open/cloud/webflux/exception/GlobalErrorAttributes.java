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
package com.open.cloud.webflux.exception;

import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.server.ResponseStatusException;

import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class GlobalErrorAttributes extends DefaultErrorAttributes {
	@Override
	public Map<String, Object> getErrorAttributes(ServerRequest request, boolean includeStackTrace) {
		Throwable error = getError(request);
		if (error instanceof ResponseStatusException) {
			ResponseStatusException responseStatusException = (ResponseStatusException) error;
			Map<String, Object> errorAttributes = new LinkedHashMap<>();
			errorAttributes.put("code", responseStatusException.getStatus().value());
			errorAttributes.put("msg", responseStatusException.getMessage());
			errorAttributes.put("data", "");
			return errorAttributes;
		} else {
			Map<String, Object> errorAttributes = super.getErrorAttributes(request,
					includeStackTrace);
			errorAttributes.put("code", errorAttributes.getOrDefault("status", 404));
			errorAttributes.put("msg", error.getMessage());
			errorAttributes.put("data", "");
			return errorAttributes;
		}
	}
}
