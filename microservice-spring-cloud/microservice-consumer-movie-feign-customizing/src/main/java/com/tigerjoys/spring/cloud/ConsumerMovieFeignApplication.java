package com.tigerjoys.spring.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 * 此项目用的是Feign的注解，上一个项目用的是SpringMVC注解
 * @author dell
 *
 */
@SpringBootApplication
@EnableFeignClients
public class ConsumerMovieFeignApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(ConsumerMovieFeignApplication.class, args);
	}
}
