package com.open.cloud.sloars.aop;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AfterReturningAdviceTest {
	// 所有方法的执行作为切入点
	@AfterReturning(returning = "rvt",
			pointcut = "execution(* com.open.cloud.sloars.aop.*.*(..))")
	public void log(Object rvt) {
		System.out.println("获取目标方法返回值 :" + rvt);
		System.out.println("模拟记录日志功能 ...");
	}
}