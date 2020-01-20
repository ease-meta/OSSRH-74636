package com.open.cloud.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;

/**
 * @author Leijian
 */
@Slf4j
public class LogUtils {

	public static boolean isDebugEnabled() {
		return PropertyUtils.getSystemProperty("log.debug.enabled", true);
	}

	public static boolean isInfoEnabled() {
		return PropertyUtils.getSystemProperty("log.info.enabled", true);
	}

	public static boolean isErrorEnabled() {
		return PropertyUtils.getSystemProperty("log.error.enabled", true);
	}

	public static boolean isWarnEnabled() {
		return PropertyUtils.getSystemProperty("log.warn.enabled", true);
	}

	public static void info(Class cls, String project, String msg) {
		if (isInfoEnabled() && log.isInfoEnabled()) {
			org.slf4j.Logger log = LoggerFactory.getLogger(cls.getName());
			log.info("[" + project + "]" + msg);
		}
	}

	public static void debug(Class cls, String project, String msg) {
		if (isDebugEnabled() && log.isDebugEnabled()) {
			org.slf4j.Logger log = LoggerFactory.getLogger(cls.getName());
			log.debug("[" + project + "]" + msg);
		}
	}

	public static void error(Class cls, String project, String msg, Throwable exp) {
		if (isErrorEnabled() && log.isErrorEnabled()) {
			org.slf4j.Logger log = LoggerFactory.getLogger(cls.getName());
			log.error("[" + project + "]" + msg, exp);
		}
	}

	public static void warn(Class cls, String project, String msg) {
		if (isWarnEnabled() && log.isWarnEnabled()) {
			org.slf4j.Logger log = LoggerFactory.getLogger(cls.getName());
			log.warn("[" + project + "]" + msg);
		}
	}

	public static void warn(Class cls, String project, String msg, Throwable exp) {
		if (isWarnEnabled() && log.isWarnEnabled()) {
			org.slf4j.Logger log = LoggerFactory.getLogger(cls.getName());
			log.warn("[" + project + "]" + msg, exp);
		}
	}


	public static void info(Class cls, String msg) {
		if (isInfoEnabled() && log.isInfoEnabled()) {
			org.slf4j.Logger log = LoggerFactory.getLogger(cls.getName());
			log.info(msg);
		}
	}

	public static void debug(Class cls, String msg) {
		if (isDebugEnabled() && log.isDebugEnabled()) {
			org.slf4j.Logger log = LoggerFactory.getLogger(cls.getName());
			log.debug(msg);
		}
	}

	public static void error(Class cls, String msg, Throwable exp) {
		if (isErrorEnabled() && log.isErrorEnabled()) {
			org.slf4j.Logger log = LoggerFactory.getLogger(cls.getName());
			log.error(msg, exp);
		}
	}

	public static void warn(Class cls, String msg) {
		if (isWarnEnabled() && log.isWarnEnabled()) {
			org.slf4j.Logger log = LoggerFactory.getLogger(cls.getName());
			log.warn(msg);
		}
	}

	public static void warn(Class cls, String msg, Throwable exp) {
		if (isWarnEnabled() && log.isWarnEnabled()) {
			org.slf4j.Logger log = LoggerFactory.getLogger(cls.getName());
			log.warn(msg, exp);
		}
	}
}
