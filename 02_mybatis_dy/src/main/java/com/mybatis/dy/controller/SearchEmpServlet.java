package com.mybatis.dy.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mybatis.dy.model.service.EmpService;
import com.mybatis.dy.model.service.EmpServiceImpl;
import com.mybatis.dy.model.vo.Employee;

/**
 * Servlet implementation class SearchByEmpNameServlet
 */
@WebServlet("/searchEmp.do")
public class SearchEmpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   private EmpService service;
	
    public SearchEmpServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		service=new EmpServiceImpl();
		
		request.setCharacterEncoding("utf-8");
		
		Map<String, Object> param=new HashMap<String, Object>();
		
		String type=request.getParameter("type");
		String keyword=request.getParameter("keyword");
		String gender=request.getParameter("gender");
		//int salary=Integer.parseInt(request.getParameter("salary"));
		String salFlag=request.getParameter("salFlag");
				
		
		param.put("type", type);
		param.put("keyword", keyword);
		param.put("gender",gender);
		param.put("salary", Integer.parseInt(request.getParameter("salary").equals("")?"0":request.getParameter("salary")));
		param.put("salFlag", salFlag);
		param.put("deptCodes", request.getParameterValues("deptCode"));
		param.put("jobCodes", request.getParameterValues("jobCode"));
		param.put("hireDate", request.getParameter("hireDate"));
		param.put("hireFlag", request.getParameter("hireFlag"));
		param.put("entYn", request.getParameter("entYn"));
		
		List<Employee> list=service.searchEmp(param);
		System.out.println(list);
		request.setAttribute("employee", list);
		request.getRequestDispatcher("/views/employee.jsp").forward(request,response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
