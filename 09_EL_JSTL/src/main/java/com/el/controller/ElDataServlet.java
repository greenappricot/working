package com.el.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.el.model.vo.Snack;

/**
 * Servlet implementation class ElDataServlet
 */
@WebServlet("/dataTest.do")
public class ElDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ElDataServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Snack s= Snack.builder().type("chocolate").name("m&m").price(1000).weight(50.0).build();
		Snack s2= Snack.builder().type("candy").name("chupachups").price(300).weight(10.0).build();
		Snack s3= Snack.builder().type("jelly").name("haribo").price(2000).weight(60.0).build();
		Snack s4= Snack.builder().type("icecream").name("m&m").price(1000).weight(50.0).build();
		List<Snack> list= List.of(s,s2,s3,s4);
		request.setAttribute("snacks", list);
		
		request.setAttribute("snack", "request과자");
		HttpSession session= request.getSession();
		session.setAttribute("snack", "맛있는 과자");
		ServletContext context= request.getServletContext();
		context.setAttribute("snack", "context영역의 과자");
		 
		request.getRequestDispatcher("/views/dataTest.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
