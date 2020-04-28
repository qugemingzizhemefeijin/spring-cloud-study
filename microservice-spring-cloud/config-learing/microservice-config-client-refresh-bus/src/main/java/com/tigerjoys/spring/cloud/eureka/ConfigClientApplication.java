package com.tigerjoys.spring.cloud.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ConfigClientApplication {

	public static void main(String[] args) {
		//ContextIdApplicationContextInitializer
		SpringApplication.run(ConfigClientApplication.class, args);
	}

}
