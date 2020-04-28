package com.tigerjoys.spring.cloud.eureka;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope//当配置发生修改的时候，会被特殊处理，比如刷新配置内容等等。
public class ConfigClientController {
	
	@Value("${profile}")
	private String profile;
	
	@GetMapping("/profile")
	public String getProfile() {
		return profile;
	}

}
