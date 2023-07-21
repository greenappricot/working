package com.bs.helloboot.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bs.helloboot.service.MemberService;


@Controller
@RequestMapping("/member")
public class MemberController {
	
	private MemberService service;
	
	public MemberController(MemberService service) {
		this.service=service;
	}
	
//	@GetMapping("/")
//	public String index() {
//		return "index";
//	}
//	configuraton class에서 연결해서 지워도 가능
	
	@GetMapping("/memberAll")
	public String selectMemberAll(Model m){
		m.addAttribute("members", service.selectMemberAll());
		return "member/memberList";
	}
	
	
}
