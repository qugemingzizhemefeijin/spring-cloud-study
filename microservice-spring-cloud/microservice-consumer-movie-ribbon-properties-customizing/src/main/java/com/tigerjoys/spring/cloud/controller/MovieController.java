package com.tigerjoys.spring.cloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.tigerjoys.spring.cloud.entity.User;

@RestController
public class MovieController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private LoadBalancerClient loadBalancerClient;
	
	@GetMapping("/movie/{id}")
	public User findById(@PathVariable Long id) {
		ServiceInstance serviceInstance = this.loadBalancerClient.choose("microservice-provider-user");
		System.out.println("===:"+serviceInstance.getHost()+":"+serviceInstance.getServiceId()+":"+serviceInstance.getPort());
		
		return this.restTemplate.getForObject("http://microservice-provider-user/simple/" + id, User.class);
	}
	
	@GetMapping("/test")
	public String test() {
		//随机获取
		ServiceInstance serviceInstance = this.loadBalancerClient.choose("microservice-provider-user");
		System.out.println("111:"+serviceInstance.getHost()+":"+serviceInstance.getServiceId()+":"+serviceInstance.getPort());
		
		//轮询获取
		ServiceInstance serviceInstance2 = this.loadBalancerClient.choose("microservice-provider-user2");
		System.out.println("111:"+serviceInstance2.getHost()+":"+serviceInstance2.getServiceId()+":"+serviceInstance2.getPort());
		
		System.out.println("==========================================");
		
		return "1";
	}

}
