package com.open.cloud.config;

import org.springframework.batch.core.ItemWriteListener;

import java.util.List;

public class CatItemWriteListener implements ItemWriteListener {
	@Override
	public void beforeWrite(List items) {
		System.out.println("Read执行之前");
	}

	@Override
	public void afterWrite(List items) {
		System.out.println("Read执行之前");
	}

	@Override
	public void onWriteError(Exception exception, List items) {
		System.out.println("Read执行之前");
	}
}
