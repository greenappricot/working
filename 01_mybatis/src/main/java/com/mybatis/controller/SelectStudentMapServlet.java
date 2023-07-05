package com.mybatis.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mybatis.model.service.StudentService;

/**
 * Servlet implementation class SelectStudentMapServlet
 */
@WebServlet("/student/selectStudentMap")
public class SelectStudentMapServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectStudentMapServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int no=Integer.parseInt(request.getParameter("no"));
		// Map으로 반환 받기
		Map data=new StudentService().selectStudentMap(no); //<String, Object>이나 <Object, Object>로 제네릭 선언할 수 있다.
		// 자동으로 컬럼명을 key값을 기준으로 해서, value를 가져온다 {K=V, K=V, ...}
		// 컬럼명을 key값으로 
		System.out.println(data);
		request.setAttribute("s", data);
		request.getRequestDispatcher("/views/student.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
