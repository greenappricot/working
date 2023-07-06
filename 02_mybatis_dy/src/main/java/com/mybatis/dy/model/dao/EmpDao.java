package com.mybatis.dy.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.mybatis.dy.model.vo.Employee;

public interface EmpDao {
	 List<Employee> selectAllEmployee(SqlSession session, int cPage, int numPerpage);
	 int selectAllEmployeeCount(SqlSession session);
	 List<Employee> searchEmp(SqlSession session, Map<String, Object> param);
}
