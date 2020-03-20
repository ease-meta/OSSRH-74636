package com.open.cloud.sofa.provider.annotation;

import com.alipay.sofa.runtime.api.annotation.SofaService;
import com.alipay.sofa.runtime.api.annotation.SofaServiceBinding;
import com.open.cloud.business.api.annotation.AnnotationService;
import org.springframework.stereotype.Component;

@SofaService(interfaceType = AnnotationService.class, bindings = {@SofaServiceBinding(bindingType = "bolt")})
@Component
public class AnnotationServiceImpl implements AnnotationService {
	@Override
	public String sayAnnotation(String stirng) {
		return stirng;
	}
}