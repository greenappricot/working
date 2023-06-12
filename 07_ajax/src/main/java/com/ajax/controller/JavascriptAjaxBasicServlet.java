package com.ajax.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class JavascriptAjaxBasicServlet
 */
@WebServlet("/js/ajax.do")
public class JavascriptAjaxBasicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JavascriptAjaxBasicServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("ajax 서비스 실행!");
//		response.getWriter().append("Served at: ").append(request.getContextPath()); // 기본 생성되는 메소드
		
		// ajax로 클라이언트가 입력한 데이터 가져오기(input) 
		String param= request.getParameter("param");
		System.out.println(param);
		
		// 원하는 데이터 ajax로 백엔드로 보내기
		response.setContentType("text/csv; charset=utf-8");
		PrintWriter out= response.getWriter();
		out.print("데이터 요청 처리");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
		request.setCharacterEncoding("UTF-8");
		System.out.println("post 방식 요청 응답하기");
		
		String paramData=request.getParameter("param");
		System.out.println(paramData);
		
		// delay 주기 
		// 4초 뒤에 응답하기
		new Thread(()->{
			try {
				Thread.sleep(4000);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}).run();
		
//		response.setContentType("text/html; charset=utf-8");
//		PrintWriter out= response.getWriter();
//		out.print("<h2>요청에 대한 응답</h2>");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		out.print("<h2>post 요청에 대한 응답</h2>");
		
	}

}
