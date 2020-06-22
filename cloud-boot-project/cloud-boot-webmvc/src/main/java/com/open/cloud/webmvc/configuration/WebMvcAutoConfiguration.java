package com.open.cloud.webmvc.configuration;

import com.open.cloud.webmvc.filter.WebMvcFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import javax.servlet.Filter;

@Configuration
public class WebMvcAutoConfiguration {

  @Bean
  @Order(-1)
  public Filter webMvcFilter() {
    return new WebMvcFilter();
  }

}