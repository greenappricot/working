package com.ajax.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class JqueryBasicServlet
 */
@WebServlet("/jquery/ajax.do")
public class JqueryBasicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JqueryBasicServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub\
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		String name=request.getParameter("name");
		int age=Integer.parseInt(request.getParameter("age"));
		System.out.println(name+" "+age);
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		out.print(name);
		out.print(age);
		out.print(" 사용자가 get 방식으로 보낸 데이터");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
		request.setCharacterEncoding("UTF-8");
		String name=request.getParameter("name");
		int age=Integer.parseInt(request.getParameter("age"));
		System.out.println("post 방식으로 요청");
		System.out.println(name+" "+age);
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out=response.getWriter();
		out.print(name);
		out.print(age);
		out.print(" 사용자가 post 방식으로 보낸 데이터");
		
		
	}

}
