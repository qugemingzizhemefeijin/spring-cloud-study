package com.tigerjoys.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import feign.Feign;
import feign.auth.BasicAuthRequestInterceptor;

@Configuration
public class FeignConfiguration2 {
	
	@Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
        return new BasicAuthRequestInterceptor("user", "password");
    }
	
	/**
	 * 禁用单个Feign的hystrix功能加上此段即可
	 * 
	 * Feign.Builder 默认是: HystrixFeign.Builder，所以如果向禁用单个的，只要覆盖掉即可
	 * 
	 * @return Feign.Builder
	 */
	@Bean
	@Scope("prototype")
	public Feign.Builder feignBuilder() {
		return Feign.builder();
	}

}
