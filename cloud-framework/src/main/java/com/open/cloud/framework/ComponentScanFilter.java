package com.open.cloud.framework;


import org.springframework.core.io.Resource;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

import java.io.IOException;

public class ComponentScanFilter implements TypeFilter {

	@Override
	public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
		//获取当前类注解的信息
		AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();
		for (String s : annotationMetadata.getAnnotationTypes()) {
			System.out.println("当前正在被扫描的类注解类型" + s);
		}
		//获取当前正在扫描类的信息
		ClassMetadata classMetadata = metadataReader.getClassMetadata();
		System.out.println("当前正在被扫描的类的类名" + classMetadata.getClassName());
		//获取当前类的资源信息（类存放的路径...）
		Resource resource = metadataReader.getResource();
		System.out.println("当前正在被扫描的类存放的地址" + resource.getURL());
		return true;
	}
}