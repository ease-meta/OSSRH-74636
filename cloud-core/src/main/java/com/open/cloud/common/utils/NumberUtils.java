package com.open.cloud.common.utils;

import java.math.BigDecimal;

/**
 * @author Leijian
 */
public class NumberUtils {
	public static double scale(Number number, int scale) {
		BigDecimal bg = new BigDecimal(number.doubleValue());
		return bg.setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
}