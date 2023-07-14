package com.bs.spring.memo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bs.spring.memo.model.dto.Memo;
import com.bs.spring.memo.service.MemoService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
//@RequestMapping("/memo")
public class MemoController {
	
	private MemoService service;
	
	@Autowired // 필드에 하기보다는 생성자나 setter에 어노테이션 선언한다.
	public MemoController(MemoService service) {
		this.service=service; // ver4 이상 가능하다
	}
	
	@RequestMapping("/memo/selectMemoAll.do")
	public String memo(Model m) {
		List<Memo> list=service.selectMemoAll();
		
		log.debug("{}",list);
		m.addAttribute("memo",list);
		return "/memo/memoList";
	}
	
	@RequestMapping(value="/memo/insertMemo.do")
	public String insertMemo(Memo m, Model model) {
		int result= service.insertMemo(m);
		String msg, loc;
		if(result>0) {
			msg="등록 성공";
			loc="/";
		}else {
			msg="등록 실패";
			loc="/memo/memo.do";
		}
		model.addAttribute("msg",msg);
		model.addAttribute("loc",loc);
		return "/common/msg";
	}
	
	@RequestMapping("/memo/deleteMemo.do")
	public String deleteMemo(int memoNo, Model model) {
		int result=service.deleteMemo(memoNo);
		String msg, loc;
		if(result>0) {
			msg="등록 성공";
			loc="/";
		}else {
			msg="등록 실패";
			loc="/memo/memo.do";
		}
		model.addAttribute("msg",msg);
		model.addAttribute("loc",loc);
		return "/common/msg";
	}
	
	
}	
