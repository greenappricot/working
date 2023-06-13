package com.ajax.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.web.admin.service.AdminService;
import com.web.member.model.vo.Member;

/**
 * Servlet implementation class BasicJsonServlet
 */
@WebServlet("/basicJson.do")
public class JsonSimpleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JsonSimpleServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Member> list= new AdminService().selectMemberByKeyword("userId", "admin", 1, 30);
		Member m=list.get(0);
		System.out.println(m);
		
		// ajax에서 csv로 보내기 위해서 형변환 했던 작업을 하지 않고 jsonSimple library를 이용해서 바로 객체로 전달할 수 있다. 
		// jsonsimple 라이브러리 이용해서 객체 전송하기
		// 단일 객체 전송 할 때 : JSONObject 클래스 이용
		// 다수 객체 전송 (List, Colletction, ...) : JSONArray 클래스 이용
		
		// 1. jsonsimple 라이브러리가 제공하는 JSONObject 객체 생성하기
		JSONObject jo=new JSONObject();
		// 2. JSONObject가 제공하는 멤버 메소드를 이용해서 전송할 데이터를 저장한다.
			// put() 메소드 : key:value 형식으로 저장한다.
		jo.put("userId", m.getUserId());
		jo.put("userName", m.getUserName());
		jo.put("age", m.getAge());
		jo.put("height", 180.5);
		jo.put("flag", true);
		
		// 3. 생성된 JSONObject를 Stream을 통해서 전송할 타입 지정 한 후, 전송한다.
		//response.setContentType("application/json; charset=UTF-8"); // json 라이브러리를 통해 전송하기 때문에 타입을 지정해줘야한다.
		//response.getWriter().print(jo);
		
		// 1-1. 다수의 데이터를 JSONArray 클래스를 이용해서 전달하기 
		List<Member> lists= new AdminService().selectMemberByKeyword("userId", "a", 1, 30);
		JSONArray joa= new JSONArray();
		// add() 메소드 이용해서  list 방식으로 JSONObject를 저장할 수 있다. 
		for(Member m1: lists) {
			JSONObject j=new JSONObject();
			j.put("userId", m1.getUserId());
			j.put("userName", m1.getUserName());
			j.put ("age", m1.getAge());
			j.put("gender", String.valueOf(m1.getGender())); // js에서 character 자료형 없어서 parsing하지 못하므로 String으로 변환해줘야한다.
			j.put("phone", m1.getPhone());
			j.put("height", 180.5);
			j.put("flag", true);
			joa.add(j);
		}
		
		// 전달하기
		response.setContentType("application/json; charset=UTF-8"); // json 라이브러리를 통해 전송하기 때문에 타입을 지정해줘야한다.
		response.getWriter().print(joa);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
