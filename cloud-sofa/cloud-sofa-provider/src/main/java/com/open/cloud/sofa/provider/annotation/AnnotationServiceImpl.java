package com.open.cloud.sofa.provider.annotation;

import com.open.cloud.business.api.annotation.AnnotationService;

public class AnnotationServiceImpl implements AnnotationService {
	@Override
	public String sayAnnotation(String stirng) {
		return stirng;
	}
}