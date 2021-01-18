package com.open.cloud.boot.autoconfigure.oss;

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