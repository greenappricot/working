package com.ajax.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ajax.model.dto.Actor;

/**
 * Servlet implementation class HtmlTestServlet
 */
@WebServlet("/ajax/htmlTest.do")
public class HtmlTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HtmlTestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// getRequestDispatcher로 응답 보내듯이 보낼 수 있다.
		List<Actor> actors= List.of(
				Actor.builder().name("박보검").phone("01012341234").profile("parkBogum.jpg").build(),
				Actor.builder().name("Julia Roberts").phone("01012341234").profile("juliaRoberts.jpg").build(),
				Actor.builder().name("matt Damon").phone("01012341234").profile("mattDamon.jpg").build());
		
		request.setAttribute("actors", actors);
		request.getRequestDispatcher("/views/htmlResponse.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
