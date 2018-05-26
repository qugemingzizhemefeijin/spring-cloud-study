package com.tigerjoys.spring.cloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.tigerjoys.spring.cloud.entity.User;

/**
 * 添加 commandProperties = execution.isolation.strategy 有两种选择
 * THREAD - 代表执行的时候是在隔离线程中，也就是无法保持线程上线问，这个是hystrix推荐和默认的选项
 * SEMAPHORE - 代表执行的时候可以保持在一个线程上下文
 * 官方建议正常情况下不要配置，等抛出异常了再配置。
 * @author dell
 *
 */
@RestController
public class MovieController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@GetMapping("/movie/{id}")
	@HystrixCommand(fallbackMethod = "findByIdFallback" , 
		commandProperties = {
			@HystrixProperty(name="execution.isolation.strategy", value="SEMAPHORE")
		}
	)
	public User findById(@PathVariable Long id) {
		return this.restTemplate.getForObject("http://microservice-provider-user/simple/" + id, User.class);
	}
	
	public User findByIdFallback(Long id) {
		User user = new User();
		user.setId(0L);
		return user;
	}
	
}
