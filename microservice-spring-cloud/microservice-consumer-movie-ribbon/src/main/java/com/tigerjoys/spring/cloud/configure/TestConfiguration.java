package com.tigerjoys.spring.cloud.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;

/**
 * 如果将此类防止到Application的同目录或者子包下，就不能针对某个应用来单独做ribbon规则配置了。
 * 比如microservice-provider-user随机访问，microservice-provider-user2轮询访问
 * ConsumerMovieRibbonApplication中的@RibbonClient注解失效了
 * 这个Configuration的配置变成全整个应用共享的配置了
 * 
 * 解决办法先写一个注解类，然后在启动类加上@ComponentScan
 * @ComponentScan(
 *	excludeFilters = {@ComponentScan.Filter(type=FilterType.ANNOTATION,value=ExcludeFromComponentScan.class)}
 * )
 * 
 * 此类上加上 @ExcludeFromComponentScan注解
 * @author dell
 *
 */
@Configuration
@ExcludeFromComponentScan
public class TestConfiguration {
	
	@Bean
	public IRule ribbonRule() {
		return new RandomRule();
	}

}
