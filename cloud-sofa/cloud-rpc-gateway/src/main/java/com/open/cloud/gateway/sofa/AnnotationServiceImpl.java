package com.open.cloud.gateway.sofa;

import com.alipay.sofa.runtime.api.annotation.SofaReference;
import com.alipay.sofa.runtime.api.annotation.SofaReferenceBinding;
import com.open.cloud.business.api.annotation.AnnotationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class AnnotationServiceImpl {

	@SofaReference(jvmFirst=false,binding=@SofaReferenceBinding(bindingType = "bolt"))
	AnnotationService annotationService;

	@PostMapping("sayAnnotation")
	public String sayAnnotation(String stirng) {
		return annotationService.sayAnnotation(stirng);
	}
}