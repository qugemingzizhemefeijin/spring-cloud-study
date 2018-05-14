package com.tigerjoys.spring.cloud.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tigerjoys.spring.config.FeignConfiguration2;

/**
 * 此是测试UserFeignClient的自定义配置是否会影响到其他的Feign的配置
 * 此类访问eureka还使用了帐号和密码的方式
 * @author dell
 *
 */
@FeignClient(name="xxx" , url="http://localhost:8761/" , configuration=FeignConfiguration2.class)
public interface FeignClient2 {
	
	@RequestMapping("/eureka/apps/{serviceName}")
	public String findEurekaByServiceName(@PathVariable("serviceName") String serviceName);

}
