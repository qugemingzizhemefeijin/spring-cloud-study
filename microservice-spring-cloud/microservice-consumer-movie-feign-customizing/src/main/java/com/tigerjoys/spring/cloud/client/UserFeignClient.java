package com.tigerjoys.spring.cloud.client;

import org.springframework.cloud.netflix.feign.FeignClient;

import com.tigerjoys.spring.cloud.entity.User;
import com.tigerjoys.spring.config.FeignConfiguration;

import feign.Param;
import feign.RequestLine;

@FeignClient(name = "microservice-provider-user" , configuration=FeignConfiguration.class)
public interface UserFeignClient {
	
	@RequestLine("GET /simple/{id}")
	public User findById(@Param("id") Long id);

}
