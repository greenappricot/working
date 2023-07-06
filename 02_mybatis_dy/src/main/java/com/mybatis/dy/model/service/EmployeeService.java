package com.mybatis.dy.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.mybatis.dy.common.SessionTemplate;
import com.mybatis.dy.model.dao.EmployeeDao;
import com.mybatis.dy.model.vo.Employee;

public class EmployeeService {
	private EmployeeDao dao= new EmployeeDao();
	public List<Employee> selectAllEmployee(int cPage,int numPerpage){
		SqlSession session= SessionTemplate.getSession();
		List<Employee> list=dao.selectAllEmployee(session, cPage,numPerpage);
		session.close();
		return list;
	}
	
	public int selectAllEmployeeCount() {
		SqlSession session=SessionTemplate.getSession();
		int count=dao.selectAllEmployeeCount(session);
		session.close();
		return count;
	}
}
