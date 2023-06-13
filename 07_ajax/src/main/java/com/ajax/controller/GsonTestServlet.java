package com.ajax.controller;

import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.web.admin.service.AdminService;
import com.web.member.model.vo.Member;

/**
 * Servlet implementation class GsonTestServlet
 */
@WebServlet("/gsonTest.do")
public class GsonTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GsonTestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Member> list= new AdminService().selectMemberByKeyword("userId","a",1,30);
		Member m=list.get(0);
		
		// Gson 라이브러리 이용해서 json parsing하기
		// 1. Gson 클래스 생성하기
		Gson gson= new Gson();
		// 2. Gson에서 제공하는 parsing하는 메소드 사용하기
		// toJson(json으로 변경할 객체[,OutputStream]); // outputStream으로 변경한 객체 보내고 끝난다
		response.setContentType("application/json; charset=UTF-8");
		//gson.toJson(m,response.getWriter()); // 첫번째 멤버변수를 key값으로 쓴다.
		gson.toJson(list,response.getWriter()); // list 보낼 때는 바로 list 보내도 됨 ㅠㅠ
		//gson.fromJson(String, null)
		
		
		// fetch로 보낸 데이터 확인하기
//		Enumeration<String> names=request.getParameterNames();
//		while(names.hasMoreElements()) {
//			System.out.println(names.nextElement());
//		}
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
















