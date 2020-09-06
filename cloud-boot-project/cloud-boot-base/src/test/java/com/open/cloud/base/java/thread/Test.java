package com.open.cloud.base.java.thread;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Leijian
 * @date 2020/8/21
 */
public class Test {

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@ToString
	class Result {
		/**
		 * 在线人数
		 */
		Integer onlineUser;

		/**
		 * 注册人数
		 */
		Integer registered;

		/**
		 * 订单总额
		 */
		BigDecimal orderAmount;

		/**
		 * 支出总额
		 */
		BigDecimal outlayAmount;
	}

	@org.junit.Test
	public void collect() {
		System.out.println("数据汇总开始");
		long startTime = System.currentTimeMillis();
		Integer onlineUser = queryOnlineUser();
		Integer registered = queryRegistered();
		BigDecimal orderAmount = queryOrderAmount();
		BigDecimal outlayAmount = queryOutlayAmount();
		Result result = new Result(onlineUser, registered, orderAmount, outlayAmount);
		long endTime = System.currentTimeMillis();
		System.out.println("获取汇总数据结束，result = " + result);
		System.out.println("总耗时 = " + (endTime - startTime) + "毫秒");
	}

	@org.junit.Test
	public void collecta() {
		System.out.println("数据汇总开始");
		long startTime = System.currentTimeMillis();
		Result result = new Result();
		List<Runnable> taskList = new ArrayList<Runnable>() {
			{
				add(() -> result.setOnlineUser(queryOnlineUser()));
				add(() -> result.setRegistered(queryRegistered()));
				add(() -> result.setOrderAmount(queryOrderAmount()));
				add(() -> result.setOutlayAmount(queryOutlayAmount()));
			}
		};
		taskList.parallelStream().forEach(v -> v.run());
		long endTime = System.currentTimeMillis();
		System.out.println("获取汇总数据结束，result = " + result);
		System.out.println("总耗时 = " + (endTime - startTime) + "毫秒");
	}

	public Integer queryOnlineUser() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("查询在线人数 耗时2秒");
		return 10;
	}

	public Integer queryRegistered() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("查询注册人数 耗时2秒");
		return 10086;
	}

	public BigDecimal queryOrderAmount() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("查询订单总额 耗时3秒");
		return BigDecimal.valueOf(2000);
	}

	public BigDecimal queryOutlayAmount() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("查询支出总额 耗时3秒");
		return BigDecimal.valueOf(1000);
	}
}