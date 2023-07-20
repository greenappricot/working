package com.bs.spring.member.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bs.spring.member.model.dto.Member;
import com.bs.spring.member.service.MemberService;

@RestController // Controller 역할을 하지만 Rest방식을 따른다 선언(Response+Controller)
@RequestMapping("/member")
public class RestMemberController {
	
	private MemberService service;
	
	public RestMemberController(MemberService service) {
		this.service=service;
	}
	
	@GetMapping
//	public List<Member> selectMemberAll(){
	public ResponseEntity<List<Member>> selectMemberAll(){
		List<Member> members = service.selectMemberAll();
		// ResponseEntity<List<Member>> response= new ResponseEntity<List<Member>>(members,HttpStatus.BAD_REQUEST);
		// builder 패턴 제공함
		ResponseEntity<List<Member>> response = ResponseEntity.ok(members);
		return response;
	}
	
	@GetMapping("/{id}")
	public Member selectMemberById(@PathVariable("id") String id){
		return service.selectMemberById(Map.of("userId",id));
	}
	
	
	@PostMapping
	public int insertMember(@RequestBody Member m) {
		return service.insertMember(m);
	}
	
	/*
	 * @PutMapping 
	 * public int updateMember(@RequestBody Member m) { return
	 * service.updateMember(m); }
	 * 
	 * @DeleteMapping("/{id}")
	 * public int deleteMember(@PathVarialbe("id") Member m) { return
	 * service.deleteMember(); }
	 */
	
	// 특정 게시글에 댓글 가져오기
	// /board/{no}/comment/{commentNo}
	// ResponseeEntity 객체를 이용해서 응답하기
	
	
}
