package com.tigerjoys.spring.cloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.tigerjoys.spring.cloud.client.UserFeignClient;
import com.tigerjoys.spring.cloud.entity.User;

@RestController
public class MovieController {
	
	@Autowired
	private UserFeignClient userFeignClient;
	
	@GetMapping("/movie/{id}")
	public User findById(@PathVariable Long id) {
		return userFeignClient.fingById(id);
	}
	
	@GetMapping("/test")
	public User testPost(User user) {
		return this.userFeignClient.postUser(user);
	}
	
	//该请求不会成功，只要参数是复杂对象，即使指定了是Get方法，Feign依然会以POST方法进行发送请求。[好像服务端改成Post接收也请求不成功]
	@GetMapping("/get-user")
	public User testUser(User user) {
		return this.userFeignClient.getUser(user);
	}

}
