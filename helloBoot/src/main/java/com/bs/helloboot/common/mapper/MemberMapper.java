package com.bs.helloboot.common.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import com.bs.helloboot.dto.Member;

@Mapper
public interface MemberMapper {
	// interface 방식으로 method가 mapper가 된다.
	
	@Select("SELECT * FROM MEMBER")
	List<Member> selectMemberAll();
	
	@Select("SELECT * FROM MEMBER WHERE USERID=#{id}")
	Member selectMemberById(String id);
	
	//@Select(value="SELECT * FROM MEMBER WHERE USERNAME LIKE '%'||#{name}||'%'")
	// MemberSelectBuilder 클래스에서 구현한 메소드를 가져옴3
	
	@SelectProvider(type=MemberSelectBuilder.class, method="selectMemberByWhere")
	List<Member> selectMemberByWhere(Map<String, Object> param);
}
