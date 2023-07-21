package com.bs.helloboot.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bs.helloboot.dto.Member;
import com.bs.helloboot.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@CrossOrigin(origins = "http://localhost:8889")// configuration class에서 일괄적으로 설정하지 않고 해당 컨트롤러에 cors 설정할 수 있다.
public class BasicController {
	
	@Value("${linux.url}") // yml에 설정한 linux 가져오기
	private String url;
	
	@Value("${linux.baseDir}")
	private String baseDir;
	
	private MemberService service;
	public BasicController(MemberService service) {
		this.service= service;
	}
	
	@PostMapping("/fileUpload")
	public String fileUpload(MultipartFile[] upFile) {
//		log.debug(upFile.getOriginalFilename());
//		log.debug("{}", upFile.getSize());
		for(MultipartFile f: upFile) {
			log.debug("{}", f.getOriginalFilename());
		}
		
		
//		upFile.transferTo(new File(path+fileName));
		return "redirect:/";
	}
	
	@GetMapping("/values")
	public String valueCheck() {
		log.debug(url);
		log.debug(baseDir);
		return "redirect:/";
	}
	
	@PostMapping("/datatest")
	public String dataTest(String data) {
		log.debug(data);
		return "redirect:/";
	}
	
	@PostMapping("/memberId")
	@ResponseBody
	public Member selectMemberById(String userId) {
		return service.selectMemberById(userId);
	}
	
	@GetMapping("/membername")
	@ResponseBody
	public List<Member> selectMemberByName(String name){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("userName", name);
		return service.selectMemberByName(param);
	}
	
}














