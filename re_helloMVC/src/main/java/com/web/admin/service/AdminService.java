package com.web.admin.service;

import org.apache.ibatis.session.SqlSession;

import com.web.admin.dao.AdminDao2;
import com.web.common.SessionTemplate;
public class AdminService {
	
	private AdminDao2 dao=new AdminDao2();
	
// 	paging 처리는 나중에 해결하겠음...! 
//	public List<Member> selectMemberAll(int cPage,int numPerpage){
//		SqlSession session=SessionTemplate.getSession();
//		List<Member> result=dao.selectMemberAll(session,cPage,numPerpage);
//		close(conn);
//		return result;
//	}
	public int selectMemberCount() {
		SqlSession session=SessionTemplate.getSession();
		int result=dao.selectMemberCount(session);
		session.close();
		return result;
	}
	
	
//	public List<Member> selectMemberByKeyword(
//			String type, String keyword, int cPage,int numPerpage){
//		Connection conn=getConnection();
//		List<Member> members=dao.selectMemberByKeyword(
//				conn,type,keyword,cPage,numPerpage);
//		close(conn);
//		return members;
//	}
	
//	public int selectMemberByKeywordCount(String type, String keyword) {
//		Connection conn=getConnection();
//		int count=dao.selectMemberByKeywordCount(conn,type,keyword);
//		close(conn);
//		return count;
//	}
}






