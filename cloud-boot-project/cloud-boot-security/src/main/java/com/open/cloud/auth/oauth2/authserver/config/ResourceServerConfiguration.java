package com.open.cloud.auth.oauth2.authserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.web.access.AccessDeniedHandler;

/**
 * Created by guankai on 2018/7/23.
 */
@Configuration
@EnableResourceServer
@SuppressWarnings("all")
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

	@Bean
	public AccessDeniedHandler getAccessDeniedHandler() {
		return new CustomAccessDeniedHandler();
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
				.csrf().disable()
				.exceptionHandling()
				.accessDeniedHandler(getAccessDeniedHandler())
				.authenticationEntryPoint(new AuthExceptionEntryPoint())
//                .authenticationEntryPoint((request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED))

				.and()
				.authorizeRequests()
				.antMatchers(
						"/actuator/**",
						"/oauth/token",
						"/*.html",
						"/webjars/**",
						"/swagger-resources/**",
						"/v2/**",
						"/dict/**",
						"/sso/**",
						"/sso/loginDataCenter",
						"/verify/code",
						"/manage/matchDataCenter",
						"/manage/matchDataCenterList",
						"/manage/getDataCenterList").permitAll()
				.anyRequest().authenticated()
				.and()
				.httpBasic();
	}

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.authenticationEntryPoint(new AuthExceptionEntryPoint());
	}
}
