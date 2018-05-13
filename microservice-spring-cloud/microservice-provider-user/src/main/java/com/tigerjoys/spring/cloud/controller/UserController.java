package com.tigerjoys.spring.cloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.tigerjoys.spring.cloud.entity.User;
import com.tigerjoys.spring.cloud.repository.UserRepository;

@RestController
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private EurekaClient eurekaClient;
	
	@Autowired
	private DiscoveryClient discoveryClient;
	
	@GetMapping("/simple/{id}")
	public User findById(@PathVariable Long id) {
		return this.userRepository.findOne(id);
	}
	
	/**
	 * 如何使用EurekaClient来进行一些操作，如从Eureka中获取服务的主页等
	 * @return String
	 */
	@GetMapping("/eureka-instance")
	public String serviceURL() {
		InstanceInfo instance = this.eurekaClient.getNextServerFromEureka("MICROSERVICE-PROVIDER-USER", false);
		return instance.getHomePageUrl();
	}
	
	/**
	 * 获得本机的服务信息
	 * @return ServiceInstance
	 */
	@GetMapping("/instance-info")
	public ServiceInstance instanceInfo() {
		ServiceInstance instance = this.discoveryClient.getLocalServiceInstance();
		return instance;
	}
	
	@PostMapping("/user")
	public User postUser(@RequestBody User user) {
		return user;
	}
	
	@GetMapping("/test-user")
	public User getUser(User user) {
		return user;
	}

}
