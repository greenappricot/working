package com.bs.spring;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller // spring에서 Controller 역할을 하는 클래스에 선언한다. -> spring bean으로 등록된다.
public class MainController {
	
	// log를 출력하기 위한 Logger 가져오기
	private static final Logger logger= LoggerFactory.getLogger(MainController.class);
	
	
	// @Controller로 등록된 클래스는 클라이언트가 요청한 서비스를 진행하는 메소드(Mapping method)를 선언한다. 
	// 매핑 메소드는 요청 url 주소와 연결한다.
	// - getMapping, postMapping, ... (rest할 때 다시)
	
	// @RequestMapping("url") 어노테이션을 매핑메소드 선언부에 선언한다.
	// Controller에 선언된 메소드는 (view를 선택해서 출력 시킬 때) 일반적으로 String 자료형을 반환하게 설정한다.
	
	@RequestMapping("/")
	// DemoController에서 test를 위해 매개변수에 servletRequest, servletResponse, Session 넣기
	public String main(HttpServletRequest req, HttpServletResponse res, HttpSession session){
		// 메소드가 반환하는 값은 viewResolver Bean이 처리한다.
		// servlet-context.xml에서 등록된 InternalResourceResolver Bean은 반환된 문자열에 
		// 객체에 설정된 prefix, suffix를 붙여서 내부에서 화면 출력 파일을 찾는다.
//		<beans:property name="prefix" value="/WEB-INF/views/" />
//		<beans:property name="suffix" value=".jsp" />
		// prefix/return/suffix => "/WEB-INF/views/index.jsp"
		// RequestDistpatcher("/WEB-INF/views/리턴값.jsp").forward();
		
		
		//  DemoController에서 test를 위해 Cookie 추가
		Cookie c= new Cookie("testData","cookieData");
		c.setMaxAge(60*60*24);
		res.addCookie(c);
		
		session.setAttribute("sessionId", "admin");
		
		
		// log4j를 이용해서 log 출력하기
		// slf4j에서 제공하는 Logger 인터페이스 구현한 클래스를 이용한다. -> 통상 필드로 사용한다.
		// LoggerFactory 클래스에 static 메소드인 getLogger(logger 가져오는 클래스 지정); 사용한다.
		
		// log를 출력할 때는 Logger가 제공하는 메소드 이용한다. -> level에 따라 출력하는 것들이 달라진다.
		// 		1. debug() : 개발 시 사용하는 로그를 출력한다. 
		//		2. info() : 프로그램이 실행하는 정보를 출력한다.
		//		3. warn() : 잘못 사용 했을 때 출력한다. (프로그램 멈출 정도는 아님)
		//		4. error() : Exception이나 실행이 불가능한 기능에 대한 로그를 출력한다.
		//		* 메소드의 매개변수는 기본적으로 String 값만 가능하다. 객체나 다른 데이터를 출력하려면 ("{}", 출력할 변수)
		
		// log level (선택된 레벨의 상위 범위까지 전부 포함해서 출력된다) 
		// debug -> info -> warn -> error -> fatal 
		
		// log 출력하기
		logger.debug("debug 내용 출력하기");
		logger.info("info 내용 출력하기");
		logger.warn("warn 내용 출력하기");
		logger.error("error 내용 출력하기");
		
		
		return "index";
	}
	
	
}
