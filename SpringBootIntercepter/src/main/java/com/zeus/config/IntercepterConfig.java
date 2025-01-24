package com.zeus.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.zeus.common.intercepter.AccessLoggingInterceptor;

//자동으로 환경설정 해주는 애너테이션
//아래 요청사항을 설정하기 위함.
@Configuration
public class IntercepterConfig implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {

		// "/login", "/board", "/api/**" 링크를 인식 후 가로챈 뒤 LoginController에 전달
		registry.addInterceptor(new AccessLoggingInterceptor()).addPathPatterns("/**").excludePathPatterns("/resources/**");

		WebMvcConfigurer.super.addInterceptors(registry);
	}

}
