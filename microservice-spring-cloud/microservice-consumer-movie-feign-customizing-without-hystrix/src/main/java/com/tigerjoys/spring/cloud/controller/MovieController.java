package com.tigerjoys.spring.cloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.tigerjoys.spring.cloud.client.FeignClient2;
import com.tigerjoys.spring.cloud.client.UserFeignClient;
import com.tigerjoys.spring.cloud.entity.User;

@RestController
public class MovieController {
	
	@Autowired
	private UserFeignClient userFeignClient;
	
	@Autowired
	private FeignClient2 feignClient2;
	
	@GetMapping("/movie/{id}")
	public User findById(@PathVariable Long id) {
		return this.userFeignClient.findById(id);
	}
	
	@GetMapping("/{serviceName}")
	public String findEurekaByServiceName(@PathVariable String serviceName) {
		return this.feignClient2.findEurekaByServiceName(serviceName);
	}

}
