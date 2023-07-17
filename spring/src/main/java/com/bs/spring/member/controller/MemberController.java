package com.bs.spring.member.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.bs.spring.member.model.dto.Member;
import com.bs.spring.member.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/member") // mapping method를 구현할 때 반복해서 사용하는 url pattern을 메소드 선언부에 선언해서 사용할 수 있다.
@SessionAttributes({"loginMember"}) // sessionAttribute로 선언할 수 있다.
@Slf4j // lombok library 있을 때만 사용할 수 있는 어노테이션 -> logger 추가됨
public class MemberController {
	
	@Autowired
	private MemberService service;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@RequestMapping("/enrollMember.do")
	public String enrollMember(@ModelAttribute("member") Member m) { // model에 연결해준다.
		// 페이지 전환
		// 매개변수가 아니라 지역 변수로 어노테이션 선언해서 연결해도 된다.
		return "member/enrollMember";
	}
	
	@RequestMapping(value="/insertMember.do", method=RequestMethod.POST)
	public String insertMember(@Validated Member m, BindingResult isResult, Model msg) {
		
		// Validated 처리 된 객체를 BindingResult에 넘겨준다
		if(isResult.hasErrors()) {
			// 에러가 나면 다시 입력 화면으로 보낸다.
			return "member/enrollMember";
		}
		
		// 패스워드 암호화 처리하기
		String oriPwd=m.getPassword();
		log.debug(oriPwd);
		String encodePwd=passwordEncoder.encode(oriPwd);
		log.debug(encodePwd);
		m.setPassword(encodePwd);
		
		int result=service.insertMember(m);
		msg.addAttribute("msg",result>0?"등록 성공":"등록 실패");
		msg.addAttribute("loc",result>0?"/":"/member/enrollMember.do");
		return "common/msg";
	}
	
	@RequestMapping("/selectMemberAll.do")
	public String selectMemberAll(Model m) {
		List<Member> list = service.selectMemberAll();
		m.addAttribute("members",list);
		return "member/memberList";
	}
	
	@RequestMapping(value="/login.do", method=RequestMethod.POST)
	public String loginMember(@RequestParam Map<String,String> param, Model model /*, HttpSession session*/) {
		
		Member member=service.selectMemberById(param);
		
		// matches() : 암호화처리 한 비밀번호를 비교하기 위해서 BCryptPasswordEncoder 객체가 제공하는 메소드를 이용해야 한다.
		//passwordEncoder.matches(param.get("password"), member.getPassword());
		
		if(member!=null&&passwordEncoder.matches(param.get("password"), member.getPassword())) { // Map에서 generic타입 선언 되어 있지 않으면 map에서 가져오는 값에 String 형변환 해야 함
//		if(member!=null&&member.getPassword().equals(param.get("password"))) { 암호화 전 메소드 처리
			//session.setAttribute("loginMember",member);
			
			// Session없이 Model을 이용한 로그인처리하기
			model.addAttribute("loginMember",member);
		}else {
			model.addAttribute("msg","로그인 실패");
			model.addAttribute("loc","/");
			return "common/msg";
		}
		return "redirect:/";
	}
//	@RequestMapping("/logoutMember.do")
//	public String logoutMember(HttpSession session) {
//		if(session!=null) session.invalidate();
//		return "redirect:/";
//	}
	
	@RequestMapping("/logoutMember.do")
	public String logoutMember(SessionStatus status) {
		// SessionAttribute 어노테이션을 이용해서 등록한 loginMember 삭제하기
		// SessionStatus 객체를 이용해서 삭제한다.
		
		//if(1==1) throw new IllegalArgumentException("잘못된 접근입니다."); // afterThrowing aspect 확인을 위한 강제 exception 생성
		
		if(!status.isComplete()) { // complete : 세션 만료
			status.setComplete(); // 강제 세션 만료 
		}
		
		return "redirect:/";
	}
	
	@RequestMapping("/mypage.do")
	public String selectMemberById(HttpSession session, Map<String, String> param) {
		Member m=(Member)session.getAttribute("loginMember");
		param.put("userId", m.getUserId());
		param.put("password", m.getPassword());
		Member member=service.selectMemberById(param);
		return "member/mypage";
	}
	
}
