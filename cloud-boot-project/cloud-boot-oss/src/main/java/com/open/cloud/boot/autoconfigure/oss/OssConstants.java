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
package com.open.cloud.boot.autoconfigure.oss;

/**
 * The type Oss constants.
 */
public final class OssConstants {

	/**
	 * Prefix of OSSConfigurationProperties.
	 */
	public static final String PREFIX = "com.open.oss";

	/**
	 * Enable OSS.
	 */
	public static final String ENABLED = PREFIX + ".enabled";

	/**
	 * OSS ThreadPool bean name.
	 */
	public static final String OSS_TASK_EXECUTOR_BEAN_NAME = "ossTaskExecutor";

	private OssConstants() {
		throw new AssertionError("Must not instantiate constant utility class");
	}

}