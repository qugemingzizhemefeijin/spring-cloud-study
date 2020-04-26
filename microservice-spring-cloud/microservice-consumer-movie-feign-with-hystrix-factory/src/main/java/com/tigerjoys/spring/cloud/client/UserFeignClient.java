package com.tigerjoys.spring.cloud.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tigerjoys.spring.cloud.entity.User;

@FeignClient(name="microservice-provider-user",/*fallback=HystrixClientFallback.class, */fallbackFactory = HystrixClientFallbackFactory.class)
public interface UserFeignClient {
	
	@RequestMapping(method = RequestMethod.GET, value = "/simple/{id}")
	public User fingById(@PathVariable("id") Long id);

}
