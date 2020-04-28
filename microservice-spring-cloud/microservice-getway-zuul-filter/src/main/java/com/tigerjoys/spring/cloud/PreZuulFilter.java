package com.tigerjoys.spring.cloud;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

public class PreZuulFilter extends ZuulFilter {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PreZuulFilter.class);

	@Override
	public Object run() {
		HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
		
		String host = request.getRemoteHost();
		
		LOGGER.info("请求的host:{}", host);
		
		return null;
	}

	@Override
	public boolean shouldFilter() {
		return true;//是否使用此过滤器
	}

	@Override
	public int filterOrder() {
		return 1;//过滤器执行顺序，数字越大越靠后
	}

	@Override
	public String filterType() {
		return "pre";
	}

}
