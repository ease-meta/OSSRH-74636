package com.open.cloud.business.api;

public interface StorageService {
	/**
	 * 扣除存储数量
	 */
	void deduct(String commodityCode, int count);
}
