package com.open.cloud.es.util;

public class TimeUtil {
	public static void timeSleep(long sleep) {
		try {
			Thread.sleep(sleep);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
