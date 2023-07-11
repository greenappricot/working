package com.bs.spring.demo.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bs.spring.demo.model.dto.Demo;

@Controller
public class DemoController {
	
	@RequestMapping("/demo/demo.do")
	public String demo() {
		return "demo/demo";
	}
	
	// 매핑 메소드 선언하기
	// 선언할 수 있는 반환형, 매개 변수 알아보기
	// 1. 반환형 
	// 		1) String : ViewResolver에 의해서 view 화면을 출력해준다. (기본적으로 가장 많이 사용 / rest 방식으로 가면 String 사용 안함)
	//		2) void : HttpServletResponse객체로 직접 응답 메세지를 작성할 때 사용 한다. (do get()과 유사함)
	//				  파일 업로드, 다운로드에 사용
	//		3) ModelAndView : (Spring이 제공하는 객체 반환) 화면에 전달할 데이터와 view 내용을 저장하는 객체 (Model : request와 생명 주기가 같음) / 사용 지양하는 추세
	//		4) 클래스 타입 : 일반 객체로, json으로 데이터 반환할 때, Restful하게 서버를 구성했을 때 많이 사용한다.
	//					* ResponseEntity<클래스타입> : 응답 객체
	
	// 2. 매개변수로 선언할 수 있는 타입 
	//		1) HttpServletRequest : servlet처럼 사용 가능
	//		2) HttpServletResponse : servlet처럼 사용 가능 
	//		3) HttpSession : session값 가져와서 대입해준다.
	//		4) java.util.Locale : 서버의 로케일 정보를 저장한 객체 
	//		5) InputStream/Reader : 파일 읽어올 때 사용하는 stream
	//		6) OutputStream/Writer : 파일 보낼 때 사용하는 stream
	//		7) 기본 자료형 변수 : 클라이언트가 보낸 parameter 데이터랑 선언한 변수 이름이 동일하면 자동으로 매핑해준다. (request.getParameter("name"))
	//					선언 이름과 파라미터 이름이 다를 경우 @RequestParam 어노테이션으로 연결해준다. (연결 뿐만 아니라 parameter에 대한 추가 설정을 할 수 있는 속성을 갖는다)
	// 		@RequestParam : 매핑, 기본 값 설정, 필수 여부 설정
	
	//		8) 클래스 변수 : Command라고 한다. parameter 데이터를 필드에 넣어서 객체를 전달한다.
	//					* parameter 이름과 필드 명이 같은 데이터를 대입해준다.
	//		9) java.util.Map : @RequestParam 어노테이션과 같이 사용한다. parameter 값을 Map으로 저장한다. (단일 값에서만 가능함)
	
	//		10) @RequestHeader(name값)와 기본 자료형을 작성하면 header의 정보를 받을 수 있다. (ex, refrer)
	//		11) @CookieValue(name값)와 기본 자료형을 작성하면 Cookie에 저장된 값을 받을 수 있다
	//		12) Model : request와 비슷하게 데이터를 key:value 형식으로 저장할 수 있는 객체 
	//		13) ModelAndView : model과 view를 동시에 저장하는 객체 (반환형에도 사용할 수 있고 매개변수로도 사용할 수 있다) 

	//		메소드 어노테이션
	// 		@ResponseBody : Rest 방식으로 클래스를 json으로 전송할 때 선언한다.(data가 json으로)
	//		@RequestBody : json 방식으로 전송된 parameter를 클래스로 받을 때 선언한다.
	
	//		restful하게 구성하면 자주 사용함(@GetMapping, @PostMapping, @DeleteMapping)
	//		@GetMapping : get 메소드로 요청했을 때 연결
	//		@PostMapping : post 메소드로 요청했을 때 연결
	//		@DeleteMapping : delete (ajax나 patch로 요청할 때 method 방식을 정할 수 있음, push, pull, .... )
	
	// 서블릿 방식으로 매핑메소드 이용하기
	@RequestMapping("demo/demo1.do")
	public void demo1(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println(req);
		System.out.println(res);
		String devName=req.getParameter("devName");
		int devAge=Integer.parseInt(req.getParameter("devAge"));
		String devGender= req.getParameter("devGender");
		String devEmail=req.getParameter("devEmail");
		String[] devLang= req.getParameterValues("devLang");
		System.out.println(devName+" "+devAge+" "+devGender+" "+devEmail);
		for(String l: devLang) {
			System.out.println(l);
		}
		
		Demo d=Demo.builder()
				.devName(devName)
				.devAge(devAge)
				.devEmail(devEmail)
				.devGender(devGender)
				.devLang(devLang)
				.build();
		
		req.setAttribute("demo", d);
		req.getRequestDispatcher("/WEB-INF/views/demo/demoResult.jsp").forward(req,res);
		
//		res.setContentType("text/html;charset=utf-8");
//		PrintWriter out=res.getWriter();
//		out.print("<h2>"+devName+" "+devAge+" "+devGender+" "+devEmail+"</h2>");
	}
	
}














