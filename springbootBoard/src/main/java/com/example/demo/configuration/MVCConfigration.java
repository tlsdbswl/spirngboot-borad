package com.example.demo.configuration;

import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.demo.interceptor.LoggerInterceptor;

public class MVCConfigration implements WebMvcConfigurer{

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LoggerInterceptor()).excludePathPatterns("/css/**", "/fonts/**", "/plugin/**", "/scripts/**");
	}
}
