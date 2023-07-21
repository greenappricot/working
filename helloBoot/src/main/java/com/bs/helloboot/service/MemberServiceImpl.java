package com.bs.helloboot.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import com.bs.helloboot.dao.MemberDao;
import com.bs.helloboot.dto.Member;
@Service
public class MemberServiceImpl implements MemberService {
	
	private MemberDao dao;
	private SqlSession session;
	
	public MemberServiceImpl(MemberDao dao, SqlSession session) {
		this.dao=dao;
		this.session=session;
	}
	
	@Override
	public List<Member> selectMemberAll() {
		return dao.selectMemberAll(session);
	}

	@Override
	public int insertMember(Member m) {
		return dao.insertMember(session, m);
	}
	
	@Override
	public Member selectMemberById(String userId) {
		return dao.selectMemberById(userId); // mapper에서 구현해주기 때문에 session을 보내지 않아도 된다.
	}
	
	@Override
	public List<Member> selectMemberByName(Map<String, Object> param) {
		return dao.selectMemberByName(param);
	}

}
