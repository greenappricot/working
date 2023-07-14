package com.bs.spring.common.interceptor;

import java.lang.reflect.Method;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.bs.spring.demo.controller.DemoController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoggerInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) // response, request를 매개변수로 받아오기 때문에 다양한 정보를 확인할 수 있다.
			throws Exception { 
		// 반환형이 boolean형으로, true를 반환하면 mapping method가 실행, false 반환하면 mapping method 실행하지 않는다.
		log.debug("----- interceptor prehandle 실행 -----");
		log.debug(request.getRequestURI());
		// 요청하는 정보를 확인할 수 있다.
		Map param=request.getParameterMap();
		for(Object key : param.keySet()) {
			System.out.println(key);
		}
		log.debug("--------------------------------------");
//		response.sendRedirect(request.getContextPath());
		// handler => 실행되는 controller 클래스, 실행되는 메소드를 확인할 수 있다.
		HandlerMethod hm=(HandlerMethod)handler;
		log.debug("{}",hm.getBean()); // Democontroller 클래스에 대한 정보 출력
		DemoController demo= (DemoController)hm.getBean();
		// 다른 메소드를 실행시키거나 전환 시킬 수 있다.
		
		log.debug("{}",hm.getMethod()); // DemoController.demo() 출력됨
		Method m=hm.getMethod();
//		m.invoke(m, null);
		
		return true; 
		
		// interceptor가 차단해서 controller가 실행되는 것을 막음 
		// servlet-context.xml에서 interceptor 등록 / interceptor 적용될 경로 등록한다 -> /demo/*로 등록했고, return false로 반환값을 두면 demo로 시작되는 경로는 모두 차단됐다
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// 메소드가 다 실행이 끝나고 난 뒤에 실행시킨다 
		log.debug("----- interceptor posthandle 실행 -----");
		log.debug("{}",modelAndView.getViewName());
		Map modelData = modelAndView.getModel();
		log.debug("{}",modelData);
		log.debug("---------------------------------------");log.debug("---------------------------------------");
	}
	
	// 직접적으로 발생시키면 spring에서 해결하기 때문에 view단에서 exception 발생 안 하면 null
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception{
		log.debug("----- interceptor afterCompleteion 실행 -----");
		log.debug("요청주소 {}",request.getRequestURI());
		log.debug("에러메세지 {} :", ex!=null?ex.getMessage():" 응답 성공");
		log.debug("-----------------------------------------------------");
	}
	
}
