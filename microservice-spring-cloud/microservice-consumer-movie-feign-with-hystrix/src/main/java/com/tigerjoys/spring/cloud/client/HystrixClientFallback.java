package com.tigerjoys.spring.cloud.client;

import org.springframework.stereotype.Component;

import com.tigerjoys.spring.cloud.entity.User;

@Component
public class HystrixClientFallback implements UserFeignClient {
	
	@Override
	public User fingById(Long id) {
		User user = new User();
		user.setId(0L);
		return user;
	}

}
