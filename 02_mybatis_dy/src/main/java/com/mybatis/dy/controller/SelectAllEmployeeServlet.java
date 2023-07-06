package com.mybatis.dy.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mybatis.dy.model.service.EmpService;
import com.mybatis.dy.model.service.EmpServiceImpl;
import com.mybatis.dy.model.vo.Employee;

/**
 * Servlet implementation class SelectAllEmployeeServlet
 */
@WebServlet("/selectAllEmployee.do")
public class SelectAllEmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    private EmpService service; // 인터페이스에 연결
    
    public SelectAllEmployeeServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		service=new EmpServiceImpl();
		
		int cPage,numPerpage;
		try {
			cPage=Integer.parseInt(request.getParameter("cPage"));
		}catch(Exception e) {
			cPage=1;
		}
		try {
			numPerpage=Integer.parseInt(request.getParameter("numPerpage"));
		}catch(Exception e) {
			numPerpage=5;
		}
		int totalData=service.selectAllEmployeeCount();
		
		int totalPage=(int)Math.ceil((double)totalData/numPerpage);
		int pageBarSize=5;
		int pageNo=((cPage-1)/pageBarSize)*pageBarSize+1;
		int pageEnd=pageNo+pageBarSize-1;
		
		String pageBar="<ul class='pagination justify-content pagination-sm'>";
	
		if(pageNo==1) {
			/*
			 * pageBar+="<li class='page-item disabled'>";
			 * pageBar+="<a class='page-link' href='#'>이전</a>"; pageBar+="</li>";
			 */
			// 15버전 이상에서 위의 구문을 아래처럼 작성 가능 (고정 값일 때)
			pageBar+="""
					<li class='page-item disabled'>
						<a class='page-link' href='#'>이전</a>
					<li class=''>
					</li>
					""";
		}else {
			pageBar+="<li class='page-item'>";
			pageBar+="<a class='page-link' href='"+request.getRequestURI()+"?cPage="+(pageNo-1)+"'>이전</a>";
			pageBar+="</li>";
		}
		while(!(pageNo>pageEnd||pageNo>totalPage)) {
			if(cPage==pageNo) {
				pageBar+="<li class='page-item disabled'>";
				pageBar+="<a class='page-link' href='#'>"+pageNo+"</a>";
				pageBar+="</li>";
			}else {
				pageBar+="<li class='page-item'>";
				pageBar+="<a class='page-link' href='"+request.getRequestURI()
						+"?cPage="+(pageNo)+"'>"+pageNo+"</a>";
				pageBar+="</li>";
			}
			pageNo++;
		}
		
		if(pageNo>totalPage) {
			pageBar+="""
					<li class='page-item disabled'>
						<a class='page-link' href='#'>다음</a>
					<li class=''>
					</li>
					""";
		}else {
			pageBar+="<li class='page-item'>";
			pageBar+="<a class='page-link' href='"+request.getRequestURI()+"?cPage="+(pageNo)+"'>다음</a>";
			pageBar+="</li>";
		}
		pageBar+="</ul>";
		request.setAttribute("pageBar", pageBar);
		
//		List<Employee> list= new EmployeeService().selectAllEmployee(cPage,numPerpage);
		List<Employee> list=service.selectAllEmployee(cPage,numPerpage);
		// 직접 연결 해서 의존성 높이기 보다 모듈처럼 따로 독립적으로 움직일 수 있게 변경-> 중간 매개체 인터페이스 넣음
		
		System.out.println(list);
		request.setAttribute("employee", list);
		request.getRequestDispatcher("/views/employee.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
