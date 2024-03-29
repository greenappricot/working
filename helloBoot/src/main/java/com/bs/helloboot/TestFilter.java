package com.bs.helloboot;


import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
@WebFilter("/*")
@Slf4j
@Component
public class TestFilter implements Filter{
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		log.debug("필터 적용 test");
		chain.doFilter(request, response);
	}
	
}



