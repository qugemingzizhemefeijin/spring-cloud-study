package com.tigerjoys.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Contract;
import feign.Logger;

@Configuration
public class FeignConfiguration {
	
	@Bean
    public Contract feignContract() {
        return new feign.Contract.Default();
    }
	
	@Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    /*@Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
        return new BasicAuthRequestInterceptor("user", "password");
    }*/

}
