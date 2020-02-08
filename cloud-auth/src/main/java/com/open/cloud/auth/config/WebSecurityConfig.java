/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.open.cloud.auth.config;

import com.open.cloud.auth.filter.CustomJSONLoginFilter;
import com.open.cloud.auth.handler.CustomAccessDeniedHandler;
import com.open.cloud.auth.handler.CustomAuthenticationEntryPoint;
import com.open.cloud.auth.handler.CustomAuthenticationFailureHandler;
import com.open.cloud.auth.handler.CustomAuthenticationSuccessHandler;
import com.open.cloud.auth.handler.CustomLogoutSuccessHandler;
import com.open.cloud.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author Leijian
 * @date   2020/2/8
 */
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	private UserService userService;

	@Autowired
	public void setUserService(UserService userService){
		this.userService = userService;
	}

	/**
	 * 匹配 "/register" 路径，不需要权限即可访问
	 * 匹配 "/user" 及其以下所有路径，都需要 "USER" 权限
	 * 登录地址为 "/login"，登录成功返回响应状态码
	 * 退出登录的地址为 "/logout"，退出成功返回响应状态码
	 * 禁用 CSRF
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.authorizeRequests()
				.antMatchers("/", "/register").permitAll()
				.antMatchers("/user/**").hasAuthority("USER")
				.antMatchers("/admin/**").hasAuthority("ADMIN")
				.and()
				.logout().logoutUrl("/logout")
				.logoutSuccessHandler(new CustomLogoutSuccessHandler())
				.clearAuthentication(true).permitAll()
				.and()
				.exceptionHandling().authenticationEntryPoint(new CustomAuthenticationEntryPoint())
				.accessDeniedHandler(new CustomAccessDeniedHandler())
				.and()
				.csrf().disable();

		http.addFilterAt(customJSONLoginFilter(), UsernamePasswordAuthenticationFilter.class);
	}

	/**
	 * 自定义认证过滤器
	 */
	private CustomJSONLoginFilter customJSONLoginFilter() {
		CustomJSONLoginFilter customJSONLoginFilter = new CustomJSONLoginFilter("/login", userService);
		customJSONLoginFilter.setAuthenticationFailureHandler(new CustomAuthenticationFailureHandler());
		customJSONLoginFilter.setAuthenticationSuccessHandler(new CustomAuthenticationSuccessHandler());
		return customJSONLoginFilter;
	}

}
