package com.bs.spring.demo.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.bs.spring.demo.model.dto.Demo;
import com.bs.spring.demo.service.DemoService;

@Controller
public class DemoController {
	
	private Logger logger=LoggerFactory.getLogger(DemoController.class);
	
	@Autowired
	private DemoService service;
	
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
//		System.out.println(req);
//		System.out.println(res); log4j에 의해서 debug를 logger로 출력할 수 있다.
		logger.debug("request : {}",req); // 자동으로 String으로 변경해서 출력할 수 있다. 
		logger.debug("response: {}",res);
		String devName=req.getParameter("devName");
		int devAge=Integer.parseInt(req.getParameter("devAge"));
		String devGender= req.getParameter("devGender");
		String devEmail=req.getParameter("devEmail");
		String[] devLang= req.getParameterValues("devLang");
		logger.debug("demo : {}",devName+" "+devAge+" "+devGender+" "+devEmail);
		//System.out.println(devName+" "+devAge+" "+devGender+" "+devEmail);
		for(String l: devLang) {
			logger.debug(l);
			//System.out.println(l);
			// 내가 확인해야 하는 값이면 info로 수정해서 사용할 수 있다.
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
	
	// 1:1로 매칭해서 데이터 받기
	// 매핑 메소드의 매개변수에 파라미터로 전송되는 이름과 동일한 이름의 변수를 선언
	// 매개변수의 타입은 사용할 타입으로 설정한다. (변경 가능해야 한다)
	// 		주의 !) 기본형 타입의 매개변수를 설정할 때, 매개변수로 사용한 변수는 모두 작성되어야 함 (참조형은 null값으로 넘어온다)
	@RequestMapping("demo/demo2.do")
	public String demo2(String devName, int devAge, String devGender, String devEmail, String[] devLang, /* double weight, */ Model model) {
		//System.out.println(devName+" "+devAge+" "+devGender+" "+devEmail+" "+Arrays.toString(devLang)); // 넘어온 값 확인
		
		// 페이지에 생성한 데이터를 전송하려면 request, session, servletContext 사용했는데 
		// Model : spring에서 데이터 전송하는 (저장하는) 객체를 제공  
		// model에 데이터 저장하기 -> model.addAttribute("key",value); <String,Object>
		
		Demo d=Demo.builder()
				.devName(devName)
				.devAge(devAge)
				.devGender(devGender)
				.devEmail(devEmail)
				.devLang(devLang)
				.build();
		
		model.addAttribute("demo", d); // setAttribute와 같음
		
		return "demo/demoResult";
	}
	
	// @RequsetParam : 파라미터 데이터를 받을 때, 기본 옵션을 선택할 수 있다.
	// value : 필드명으로 일치 시켜줌
	// defaultValue : 값이 안 넘어왔을 때 기본 설정값을 줄 수 있다.
	// required : 필수값 확인 (false: 생략해버림)
	@RequestMapping("/demo/demo3.do")
	public String usingRequestParam(@RequestParam(value="devName") String name, @RequestParam(value="devAge", defaultValue="10")int age, @RequestParam(value="devGender")String gender, @RequestParam(value="devEmail", required=true)String devEmail, String[] devLang, Model model) {
		// 매개변수의 이름과 파라미터의 변수명이 달라도 괜찮다. 
		Demo d=Demo.builder()
				.devName(name)
				.devAge(age)
				.devGender(gender)
				.devEmail(devEmail)
				.devLang(devLang)
				.build();
		model.addAttribute("demo", d);
		
		return "demo/demoResult";
	}
	
	// dto를 매개변수로 해서 파라미터 값을 받을 때, field이름이랑 parameter 이름이 같아야 값을 dto에 저장할 수 있다
	// dto로 값을 가져올 때는 자료형에 주의해야한다 (java.util.Date로 가져오면 값을 parsing하지 못할 수 있다)
	// dto에서 has a 관계로 되어 있으면 값을 가져올 수 없다
	
	// Dto/ Vo 객체로 직접 parameter 값 받기
	// 매개변수로 전달된 parameter이름과 동일한 필드명을 갖는 객체를 선언한다.
	// 		주의 !) 클래스 타입 Date를 전달받을 때는 (java.sql.Date를 선언하는 편이 낫다 -> 호환성이 더 좋음) 
	@RequestMapping("demo/demo4.do")
	public String commandMapping(Demo demo, Model m) {
		System.out.println(demo);
		m.addAttribute("demo",demo);
		return "demo/demoResult";
	}
	
	// @RequestParam : Map으로 parameter 데이터 받아오기 
	// 단일값만 가져올 수 있다 / 배열에 대한 값을 다 가져오지 못하고 date는 string으로 가져오기 때문에 parsing해서 사용해야한다.
	// -> 따로 값을 매개변수로 받아서 map에 추가할 수 있다.
	@RequestMapping("demo/demo5.do")
	public String mapMapping(@RequestParam Map<String,Object> param, String[] devLang, Model m) {
		System.out.println(param);
		param.put("devLang", devLang);
		m.addAttribute("demo",param);
		return "demo/demoResult";
	}
	
	// 파라미터로 넘어오는 데이터 외의 기타 데이터 가져오기 -> String 값으로 가져올 수 있다.
	// Cookie, header, Session 등의 정보 가져오기(index.jsp에서 테스트로 설정함)
	// Cookie : @CookieValue(value="key") required 옵션 있음 -> String data 
	// Header : @RequestHeader(value="헤더이름") -> String header
	// Session : @SessionAttribute(value="sessionKey값") -> String id
	@RequestMapping("/demo/demo6.do")
	public String extraData(@CookieValue(value="testData", required=false, defaultValue="rest-time")String data, @RequestHeader(value="user-agent") String userAgent, @SessionAttribute(value="sessionId") String sessionId, @RequestHeader(value="Referer") String referer) {
		System.out.println("cookie : "+data+" header : "+userAgent+" session : "+sessionId+" referer : "+referer);
		return "index";
	}
	
	// ModelAndView 객체를 이용해서 반환하기 -> 일반적으로 String으로 반환하는 것을 지향
	@RequestMapping("/demo/demo7.do")
	public ModelAndView modelAndViewReturn(Demo d,ModelAndView mv) {
		// ModealAndView : view 설정과 Model 설정을 같이 할 수 있는 객체
		// view : setViewName() 메소드를 이용해서 저장
		// data : addObject("key",value) 메소드 이용해서 저장
		
		mv.addObject("demo",d);
		mv.setViewName("demo/demoResult");
		
		//mv.getModel(); model의 내용을 다시 가져올 수 있다.
		return mv;
	}
	
	// 자료형에 대한 반환하기 -> 데이터만 응답할 때 사용한다. -> jackson라이브러리를 이용해서 처리한다
	// restfull메소드를 구현했을 때 사용한다.
	// @ResponseBody 메소드에 선언부에 선언한다. (return값 있는 곳에 선언해도 무방)
	// viewResolver로 가면 일치하는 값이 없음/ viewResolver로 이동하는 것이 아니라 body로 전달한다
	@RequestMapping("/demo/demo8.do")
	@ResponseBody
	public String dataReturn(){
		return "유병승 최주영 조장흠 솔 조윤진"; // list 파싱 못 했었음,,, 그리고 이 반환 값도 encoding 해야함 
	}
	
	// Request 요청 메소드(get, post)를 필터링하기
	// @RequestMapping(value="url주소", method=RequestMethod.GET || RequestMethod.POST)
//	@RequestMapping(value = "/demo/demo9.do", method = RequestMethod.POST ) 
	//logException(AbstractHandlerExceptionResolver.java:208) - Resolved [org.springframework.web.HttpRequestMethodNotSupportedException: Request method 'POST' not supported
	// method방식을 GET으로 고정시켜놨는데, demo.jsp에서 post방식으로 form을 전달하기 때문에 에러 발생한다.
//	public String methodCheck(Demo d, Model m) {
//		m.addAttribute("demo",d);
//		return "demo/demoResult";
//	}
	
	// 간편하게 사용할 수 있게 Mapping 어노테이션 지원 
//	@GetMapping 
//	@PostMapping
//	@DeleteMapping
//	@PutMapping
	@PostMapping("/demo/demo9.do") 
	public String methodCheck(Demo d, Model m) {
		m.addAttribute("demo",d);
		return "demo/demoResult";
	}
	
	// mapping 주소를 설정할 때 { }를 사용할 수 있다.(rest 방식으로 설계할 때 많이 사용)
	// "/board/boardview?no=1" -> "/board/1" method = GET
	// "/board/" method = GET
	@GetMapping("/demo/{no}")
	public String searchDemo(@PathVariable(value = "no") int no) {
		System.out.println(no);
		return "demo/demoResult";
	}
	
	@RequestMapping(value="/demo/insertDemo.do", method=RequestMethod.POST)
	public String insertDemo(Demo demo,Model m) {
		System.out.println(demo);
		int result=service.insertDemo(demo);
		System.out.println(result);
		m.addAttribute("msg",result>0?"등록 성공":"등록 실패");
		m.addAttribute("loc","/demo/demo.do");
		//return "demo/demo"; 
		// default로 request.getRequestDispatcher()로 이동되는데 
		// sendRedirect로 변경하는 방법
	
		// prefix redirect : 요청할 주소 -> 매핑주소(주소값 주면서 재요청 하기 때문에 내부에서 직접 jsp 호출할 수 없으므로 매핑 주소를 적어야한다.)
		//return "redirect:/";
		return "common/msg";
	}
	
	@RequestMapping("/demo/selectDemoAll.do")
	public String selectDemoAll(Model m) {
		List<Demo> list=service.selectDemoAll();
		System.out.println(list);
		m.addAttribute("demo",list);
		return "demo/demoList";
	}
	
	@RequestMapping("/demo/selectDemo.do")
	public String selectDemo(Model m, int devNo) {
		System.out.println(devNo);
		Demo d=service.selectDemo(devNo);
		m.addAttribute("demo",d);
		return "demo/demoResult";
	}
	
}














