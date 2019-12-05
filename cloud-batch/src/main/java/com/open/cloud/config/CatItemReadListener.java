package com.open.cloud.config;

import org.springframework.batch.core.ItemReadListener;

public class CatItemReadListener implements ItemReadListener {
	@Override
	public void beforeRead() {
		System.out.println("Read执行之前");
	}

	@Override
	public void afterRead(Object item) {
		System.out.println("Read执行之后");
	}

	@Override
	public void onReadError(Exception ex) {
		System.out.println("Read执行之错");
	}
}
