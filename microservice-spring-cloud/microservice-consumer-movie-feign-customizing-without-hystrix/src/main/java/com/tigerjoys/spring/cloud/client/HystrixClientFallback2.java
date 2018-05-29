package com.tigerjoys.spring.cloud.client;

import org.springframework.stereotype.Component;

@Component
public class HystrixClientFallback2 implements FeignClient2 {

	@Override
	public String findEurekaByServiceName(String serviceName) {
		return "haha";
	}
	

}
