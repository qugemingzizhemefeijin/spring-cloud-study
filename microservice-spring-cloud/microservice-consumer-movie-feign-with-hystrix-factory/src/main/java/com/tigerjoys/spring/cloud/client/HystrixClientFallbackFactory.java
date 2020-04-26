package com.tigerjoys.spring.cloud.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.tigerjoys.spring.cloud.entity.User;

import feign.hystrix.FallbackFactory;

@Component
public class HystrixClientFallbackFactory implements FallbackFactory<UserFeignClient>{
	
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());

	@Override
	public UserFeignClient create(Throwable cause) {
		LOGGER.info("fallback:reason was: {}", cause.getMessage());
		
		return new UserFeignClientWithFactory() {
			
			@Override
			public User fingById(Long id) {
				User user = new User();
				user.setId(-1L);
				
				return user;
			}
		};
	}

}
