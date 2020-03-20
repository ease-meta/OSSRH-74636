package com.open.cloud.eureka.web;

import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.stereotype.Controller;


class TestRestControllerTest {

	public static void main(String[] args) {
		Controller controller = AnnotatedElementUtils.getMergedAnnotation(TestRestController.class,Controller.class);
		boolean flage = AnnotatedElementUtils.hasAnnotation(TestRestController.class, Controller.class);
		System.out.println(flage);
		//MergedAnnotations mergedAnnotations = AnnotatedElementUtils.findAnnotations(TestRestController.class);
	}
}