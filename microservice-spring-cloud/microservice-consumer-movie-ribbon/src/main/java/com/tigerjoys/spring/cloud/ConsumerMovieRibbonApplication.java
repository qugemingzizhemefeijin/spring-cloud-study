package com.tigerjoys.spring.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.client.RestTemplate;

import com.tigerjoys.spring.cloud.configure.ExcludeFromComponentScan;
import com.tigerjoys.spring.cloud.configure.TestConfiguration;

@SpringBootApplication
//这里的名称一定要Eureka注册的名称，ribbon默认是轮询
@RibbonClient(name="microservice-provider-user",configuration=TestConfiguration.class)
@ComponentScan(
	excludeFilters = {@ComponentScan.Filter(type=FilterType.ANNOTATION,value=ExcludeFromComponentScan.class)}
)
//ribbon 配置 最好是用配置文件配置，第二就是java代码配置
public class ConsumerMovieRibbonApplication {
	
	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	public static void main(String[] args) {
		SpringApplication.run(ConsumerMovieRibbonApplication.class, args);
	}
}
