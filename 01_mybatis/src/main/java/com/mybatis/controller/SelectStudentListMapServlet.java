package com.mybatis.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mybatis.model.service.StudentService;

/**
 * Servlet implementation class SelectStudentListMapServlet
 */
@WebServlet("/student/selectStudentListMap")
public class SelectStudentListMapServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectStudentListMapServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		// Vo없이 Map을 이용해서 불러올 수 있다 -> 장점 : vo는 고정적인 tbl과 연결해서 사용하는 것에 비해 유연하게 사용할 수 있다 / 단점 : 변경 이력을 남기기 어렵다
		List<Map> data=new StudentService().selectStudentListMap();
		//System.out.println(data);
		//data.stream().forEach(System.out::println);
		request.setAttribute("students", data);
		request.getRequestDispatcher("/views/student.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
