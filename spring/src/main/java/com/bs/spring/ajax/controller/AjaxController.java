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
		return mservice.selectMemberAll();
	}
	
	// ajax로 보낸 데이터 받기
	@PostMapping("/insertData.do")
	@ResponseBody
	public Member insertData(@RequestBody Member m) { // @RequestBody 선언 : json으로 받은 데이터를 spring이 알아서 parsing해준다
		log.info("{}", m);
		return m;
	}
	
}










