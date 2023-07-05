package com.web.admin.dao;

import org.apache.ibatis.session.SqlSession;

public class AdminDao2 {
	
	public int selectMemberCount(SqlSession session) {
		return session.selectOne("admin.selectMemberCount");
	}
}
