package com.open.cloud.mybatis.generator.annotion;

import com.open.cloud.mybatis.generator.entity.GeneratorProperties;
import com.open.cloud.mybatis.generator.service.CodeGeneratorService;
import org.springframework.context.annotation.Import;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import({GeneratorProperties.class, CodeGeneratorService.class})
@Documented
@Inherited
public @interface EnableGenerator {

}
