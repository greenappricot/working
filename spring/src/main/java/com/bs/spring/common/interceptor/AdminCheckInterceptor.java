package com.bs.spring.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

import com.bs.spring.member.model.dto.Member;

public class AdminCheckInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session=request.getSession();
		Member loginMember=(Member)session.getAttribute("loginMember");
		
		if(loginMember==null || !loginMember.getUserId().equals("admin")) {
			// loginMember가 없거나 admin 아닐 때
			request.setAttribute("msg", "관리자 계정만 이용할 수 있는 서비스 입니다 :<");
			request.setAttribute("loc", "/");
			request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp").forward(request, response);
			return false;
		}
		return true;
	}
	
}
