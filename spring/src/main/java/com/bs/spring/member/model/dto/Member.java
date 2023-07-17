package com.bs.spring.member.model.dto;

import java.sql.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Member {
	
	@NotEmpty(message = "아이디는 반드시 입력하세요")
	@Size(min=4, message = "아이디는 4글자 이상 입력하세요")
	private String userId;
	@Pattern(regexp = "(?=.*?[a-z])(?=.*?[A-Z])(?=.*?[~!@#$%^&*()])[a-zA-Z~!@#$%^*()]{8,}", message = "비밀번호는 영문 대, 소문자와 특수기호 !@#$%^*()를 포함하고 8글자 이상 작성하세요") // 정규표현식으로 패턴 등록
	private String password;
	private String userName;
	private String gender;
	@Min(value=14,message = "14살 이상 가입할 수 있습니다")@Max(150)
	private int age;
	@Email
	private String email;
	@NotEmpty(message = "전화번호는 반드시 입력하세요")
	private String phone;
	private String address;
	private String[] hobby;
	private Date enrolldate;
}
