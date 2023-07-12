package com.bs.spring;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller // spring에서 Controller 역할을 하는 클래스에 선언한다. -> spring bean으로 등록된다.
public class MainController {
	
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
		
		
		return "index";
	}
	
	
}
