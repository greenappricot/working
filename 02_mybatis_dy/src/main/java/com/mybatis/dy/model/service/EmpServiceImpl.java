package com.mybatis.dy.model.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.mybatis.dy.common.SessionTemplate;
import com.mybatis.dy.model.dao.EmpDao;
import com.mybatis.dy.model.dao.EmpDaoImpl;
import com.mybatis.dy.model.vo.Board;
import com.mybatis.dy.model.vo.Department;
import com.mybatis.dy.model.vo.Employee;

public class EmpServiceImpl implements EmpService {
	
	private EmpDao dao= new EmpDaoImpl();
	
	@Override
	public List<Employee> selectAllEmployee(int cPage,int numPerpage) {
		SqlSession session= SessionTemplate.getSession();
		List<Employee> list=dao.selectAllEmployee(session, cPage,numPerpage);
		session.close();
		return list;
	}

	@Override
	public int selectAllEmployeeCount() {
		SqlSession session=SessionTemplate.getSession();
		int count=dao.selectAllEmployeeCount(session);
		session.close();
		return count;
	}
	
	@Override
	public List<Employee> searchEmp(Map<String, Object> param) {
		SqlSession session= SessionTemplate.getSession();
		List<Employee> list=dao.searchEmp(session, param);
		session.close();
		return list;
	}
	@Override
	public List<Department> selectAllDept() {
		SqlSession session=SessionTemplate.getSession();
		List<Department> list=dao.selectAllDept(session);
		session.close();
		return list;
	}
	
	@Override
	public List<Board> boardList(int no) {
		SqlSession session= SessionTemplate.getWebSession();
		List<Board> list=dao.boardList(session, no);
		session.close();
		return list;
	}

}
