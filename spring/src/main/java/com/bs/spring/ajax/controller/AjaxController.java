package com.bs.spring.ajax.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bs.spring.board.model.dto.Board;
import com.bs.spring.common.exception.AuthenticationException;
import com.bs.spring.member.model.dto.Member;
import com.bs.spring.member.service.MemberService;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/ajax")
@Slf4j
public class AjaxController {
	
	@Autowired
	private MemberService mservice;
	
	
	@GetMapping("/basicTest.do")
	public void basic(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
//		기본 방식으로 전달 
//		res.setContentType("text/csv;charset=utf-8");
//		res.getWriter().write("test");
		
		
		// mapper를 이용해서 객체를 json 방식으로 전달하기
		Board b= Board.builder().boardTitle("제목").boardContent("내용").build();
		ObjectMapper mapper= new ObjectMapper(); // jackson library에서 제공하는 ObjectMapper를 사용할 수 있다. 
		res.setContentType("application/json;charset=utf-8");
		res.getWriter().write(mapper.writeValueAsString(b)); // board 객체를 json으로 변환해서 보낸다.
		
	}
	
	// 리턴 값에 반환할 객체를 선언할 수 있다.
//	@ResponseBody -> json으로 반환할 수 있게 처리
	@GetMapping("/converter")
	@ResponseBody
	public Board convertTest() {
		return Board.builder().boardTitle("spring json").boardContent("hahaha").build();
	}
	
	@GetMapping("/idCheck")
	@ResponseBody
	public Member idCheck(String id) {
		log.debug("{}",id);
		return mservice.selectMemberById(Map.of("userId",id,"password",""));
	}
	
	@PostMapping("/idDuplicate")
	public @ResponseBody Member idDuplicate(@RequestParam Map param) {
		log.info("{}",param);
		return mservice.selectMemberById(param);
	}
	
	// 일반적으로 jsp를 반환하는 메소드 
	@GetMapping("/basic2")
	public String basic2() {
		return "demo/demo";
	}
	
	// 전체 회원 리스트 조회
	@GetMapping("/selectMemberAll")
	@ResponseBody
	public List<Member> selectMemberAll(){
		if(1==1) throw new AuthenticationException("권한에러발생");
		return mservice.selectMemberAll();
	}
	
	// ajax로 보낸 데이터 받기
	@PostMapping("/insertData.do")
	@ResponseBody
	public Member insertData(@RequestBody Member m) { // @RequestBody 선언 : json으로 받은 데이터를 spring이 알아서 parsing해준다
		log.info("{}", m);
		return m;
	}
	
	// REST API, RESTful -> view단과 backend 분리 (session, cookie 관리 안함 / stateless) 
	// 웹에서 url 설계 할 때 독립적으로 움직이게 하기 위해 만든 구조 (-> 주소, 메소드를 알아보기 쉽게 간단하게 쓰자)
	// URL을 설정할 때 간단하게 서비스를 식별할 수 있는 값으로 설정하자
	// URL 주소를 설정할 때 행위에 대한 표현을 제외하고 resource에 대한 정보만 갖기 -> method를 보고 행위 결정하기

	// method (web통신에서의)
	// GET : Data를 조회하는 서비스
	// POST : Data를 저장하는 서비스 
	// PUT : Data를 수정하는 서비스
	// DELETE : Data를 삭제하는 서비스
	
	// url주소를 설정할 때는 명사로 작성한다.
	// ex) 회원을 관리하는 서비스
	// GET localhost:9090/spirng/member  -> 전체 회원 조회
	// GET localhost:9090/spring/member/{id}admin||1 -> 아이디로 회원 조회
	// POST localhost:9090/spring/member -> 회원 추가
	// PUT localhost:9090/spring/member -> 회원 수정
	// DELETE localhost:9090/spring/member -> 회원 삭제
	
}










