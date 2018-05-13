package com.tigerjoys.spring.cloud.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.tigerjoys.spring.cloud.entity.User;

@FeignClient("microservice-provider-user")
public interface UserFeignClient {
	
	/**
	 * 现在的版本使用Feign，已经可以用GetMapping，@PathVariable还得加value。
	 * http://edu.51cto.com/center/course/lesson/index?id=133800 作者在讲的时候还不能用@GetMapping
	 * @param id - Long
	 * @return User
	 */
	@GetMapping("/simple/{id}")
	public User fingById(@PathVariable("id") Long id);
	
	@PostMapping("/user")
	public User postUser(@RequestBody User user);
	
	/**
	 * feign get方法的请求为对象的时候，不能请求微服物的get接口
	 * "org.springframework.web.HttpRequestMethodNotSupportedException","message":"Request method 'POST' not supported","path":"/test-user"
	 * 
	 * 如果非要get传递参数的话，可以使用@RequestParam注解来将参数逐个传递。
	 * @param user - User
	 * @return User
	 */
	@GetMapping("/test-user")
	public User getUser(User user);

}
