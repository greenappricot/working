package com.mybatis.dy.model.service;

import java.util.List;
import java.util.Map;

import com.mybatis.dy.model.vo.Board;
import com.mybatis.dy.model.vo.Department;
import com.mybatis.dy.model.vo.Employee;

public interface EmpService {
	List<Employee> selectAllEmployee(int cPage,int numPerpage);
	int selectAllEmployeeCount();
	List<Employee> searchEmp(Map<String, Object> param);
	 List<Department> selectAllDept();
	List<Board> boardList(int no);
}
