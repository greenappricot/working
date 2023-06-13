package com.ajax.controller;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.admin.service.AdminService;
import com.web.member.model.vo.Member;

/**
 * Servlet implementation class SearchMemberServlet
 */
@WebServlet("/searchMember.do")
public class SearchMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchMemberServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Member> members=new AdminService().selectMemberAll(1,100);
//		members.stream().forEach(System.out::println);
		
		// jsp로 보내서 html을 불러오는 방식으로 함 
//		request.setAttribute("members", members);
//		request.getRequestDispatcher("/views/memberTable.jsp").forward(request, response);
		
		
		// csv방식으로 데이터를 보내는 방식
		// 1. Member를 toString override 한다
		// 2. 받아온 member List를 parsing한다.
		String resultData=members.stream().map(e->e.toString()).collect(Collectors.joining("\n")); // stream으로 간단하게 사용할 수 있다.
		// abcd,김준호,M,25,abcd@naver.com,01012345678,서울시 관악구,[운동, 등산, 독서],2018-00-01
		// bcde,서지와,F,25,bcde@naver.com,01012345678,서울시 강북구,[운동, 등산, 독서],2018-00-02
		// 와 같이 각각의 member가 ,로 parsing할 수 있고/ 전체 데이터가 \n으로 파싱할 수 있다 
		System.out.println(resultData);
		response.setContentType("text/csv; charset=UTF-8");
		response.getWriter().print(resultData);
		
		
		int cPage,numPerpage;
		try {
			cPage=Integer.parseInt(request.getParameter("cPage"));
		}catch(NumberFormatException e) {
			cPage=1;
		}
		try {
			numPerpage=Integer.parseInt(request.getParameter("numPerpage"));
		}catch(NumberFormatException e) {
			numPerpage=10;
		}
		String pageBar="";
		int totalData=new AdminService().selectMemberCount();
		int totalPage=(int)Math.ceil((double)totalData/numPerpage);
		int pageBarSize=5;
		
		int pageNo=((cPage-1)/pageBarSize)*pageBarSize+1;
		int pageEnd=pageNo+pageBarSize-1;
		
		if(pageNo==1) {
			pageBar+="<span>[이전]</span>";
		}else {
			pageBar+="<a href='"+request.getRequestURI()+"?cPage="+(pageNo-1)+"'>[이전]</a>";
		}
		while (!(pageNo > pageEnd || pageNo > totalPage)) {
			if (pageNo == cPage) {
				pageBar += "<span>" + pageNo + "</span>";
			} else {
				pageBar += "<a href='" + request.getRequestURI() + "?cPage=" + pageNo + "'>" + pageNo + "</a>";
			}
			pageNo++;

		}

		if (pageNo > totalPage) {
			pageBar += "<span>[다음]</span>";
		} else {
			pageBar += "<a href='" + request.getRequestURI() + "?cPage=" + pageNo + "'>[다음]</a>";
		}
		
		//request.setAttribute("pageBar",pageBar);
		//request.getRequestDispatcher("/").forward(request, response);
		//response.getWriter().print(pageBar);
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
