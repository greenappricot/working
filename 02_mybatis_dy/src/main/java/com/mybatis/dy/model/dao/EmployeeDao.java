package com.mybatis.dy.model.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import com.mybatis.dy.model.vo.Employee;

public class EmployeeDao {
	public List<Employee> selectAllEmployee(SqlSession session, int cPage, int numPerpage){
		RowBounds rb= new RowBounds((cPage-1)*numPerpage,numPerpage);
		return session.selectList("employee.selectAllEmployee",null, rb);
	}
	public int selectAllEmployeeCount(SqlSession session) {
		return session.selectOne("employee.selectAllEmployeeCount");
	}
}
