package com.web.member.model.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Member {
	private String userId;
	private String password;
	private String userName;
	private char gender;
	private int age;
	private String email;
	private String phone;
	private String address;
	private String[] hobby;
	private Date enrollDate;
}

//CREATE TABLE MEMBER(
//		  USERID VARCHAR2(15) PRIMARY KEY
//		 ,PASSWORD VARCHAR2(15) NOT NULL
//		 ,USERNAME  VARCHAR2(20) NOT NULL
//		 ,GENDER CHAR(1) CHECK (GENDER IN ('M','F'))
//		 ,AGE NUMBER
//		 ,EMAIL VARCHAR2(30)
//		 ,PHONE CHAR(11)  NOT NULL
//		 ,ADDRESS VARCHAR2(100)
//		 ,HOBBY VARCHAR2(50)
//		 ,ENROLLDATE DATE DEFAULT SYSDATE
//		);