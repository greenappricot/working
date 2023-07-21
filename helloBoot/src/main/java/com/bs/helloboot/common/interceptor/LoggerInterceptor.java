package com.bs.helloboot.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class LoggerInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		//preHandle : 특정 메소드 실행 전에 실행하는 인터셉터 
		log.debug("==================== 실행 전 ====================");
		log.debug(request.getRequestURI());
		log.debug("=================================================");
		return true;
	}
	
}
