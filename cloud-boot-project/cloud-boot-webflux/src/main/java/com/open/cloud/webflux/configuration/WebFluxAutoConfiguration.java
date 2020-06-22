package com.open.cloud.webflux.configuration;

import com.open.cloud.webflux.filter.WebfluxFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * @author Leijian
 * @date 2020/6/22
 */
@Configuration
public class WebFluxAutoConfiguration {

	@Bean
	@Order(-1)
	public WebfluxFilter webMvcFilter() {
		return new WebfluxFilter();
	}
}
