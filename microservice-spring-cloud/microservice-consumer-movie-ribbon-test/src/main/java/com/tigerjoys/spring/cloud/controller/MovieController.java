package com.tigerjoys.spring.cloud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.google.common.collect.Lists;
import com.tigerjoys.spring.cloud.entity.User;

@RestController
public class MovieController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private LoadBalancerClient loadBalancerClient;
	
	@GetMapping("/movie/{id}")
	public User findById(@PathVariable Long id) {
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
	
	/**
	 * 如果远程服务返回的是一个List<User>这种，在使用restTemplate直接是拿不到List<User>对象的。
	 * 使用RestTemplate时，想要获取一个List时，应该用数组，而不应该直接用List.
	 * @return
	 */
	@GetMapping("/list-all")
	public List<User> listAll(){
		//错误写法
		/*List<User> userList = this.restTemplate.getForObject("http://microservice-provider-user/list-all", List.class);
		for(User user : userList) {
			System.out.println(user.getId());
		}*/
		
		//正确写法
		User[] userArray = this.restTemplate.getForObject("http://microservice-provider-user/list-all", User[].class);
		for(User user : userArray) {
			System.out.println(user.getId());
		}
		
		return Lists.newArrayList(userArray);
	}

}
