package com.bs.spring.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

import com.bs.spring.member.model.dto.Member;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {@Override
	
	// 실행 전에 login filtering 하는 것은 preHandle만 사용해도딘다.
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session=request.getSession();
		Member loginMember=(Member)session.getAttribute("loginMember");
		if(loginMember==null) {
			// 로그인 실패
			request.setAttribute("msg", "로그인 후 이용할 수 있는 서비스 입니다 :<");
			request.setAttribute("loc", "/");
			request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp").forward(request, response);
			return false;
		}
		return true;
	}
	
}
