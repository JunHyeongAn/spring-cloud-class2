package com.example.zuulservice.filter;


import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ZuulLogginFilter extends ZuulFilter {
	@Override
	public boolean shouldFilter() {
		// TODO Auto-generated method stub
		// 필터로 사용하겠다.
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		// 사용자의 요청이 들어올때 마다 실행
		log.info("******************* printing logs: ");
		
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		log.info("******************* " + request.getRequestURI());
		
		return null;
	}

	@Override
	public String filterType() {
		// TODO Auto-generated method stub
		// 전후 결정 pre 니까 전에 실행
		return "pre";
	}

	@Override
	public int filterOrder() {
		// TODO Auto-generated method stub
		// 우선순위
		return 1;
	}
	
}
