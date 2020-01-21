package com.open.cloud.common.spi.java;

/**
 * @author Leijian
 */
public interface BaseSPI {

	int HIGHEST_PRECEDENCE = Integer.MIN_VALUE;

	int LOWEST_PRECEDENCE = Integer.MAX_VALUE;

	int getOrder();

	String supportName();
}
