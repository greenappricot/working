package com.bs.helloboot.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.bs.helloboot.common.mapper.MemberMapper;
import com.bs.helloboot.dto.Member;

@Repository
public class MemberDaoImpl implements MemberDao {
	
	// mapper.xml에 연결하지 않고 mapper 클래스에 연결하기
	private MemberMapper mapper;
	
	public MemberDaoImpl(MemberMapper mapper) {
		this.mapper= mapper;
	}
	
	@Override
	public List<Member> selectMemberAll(SqlSession session) {
		//return session.selectList("member.selectMemberAll");
		return mapper.selectMemberAll();
	}
	
	@Override
	public int insertMember(SqlSession session, Member m) {
		return session.insert("member.insertMember",m);
	}
	
	@Override
	public Member selectMemberById(String userId) {
		return mapper.selectMemberById(userId);
	}
	
	@Override
	public List<Member> selectMemberByName(Map<String, Object> param) {
		return mapper.selectMemberByWhere(param);
	}
}
