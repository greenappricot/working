<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>action tag</title>
</head>
<body>
	<h2>액션태그 활용하기</h2>
	<p>jsp 페이지에서 java 코드를 html 태그 방식으로 작성할 수 있게 해주는 태그</p>
	<ul>
		<li>표준 액션 태그 : 기본  jsp에서 제공하는 태그</li>
		<li>커스텀 액션 태그(JSTL) : 별도 jar 파일로 제공하는 태그 * jar 파일을 추가해야 사용 가능하다</li>
	</ul>
	<h3>표준 액션 태그 이용하기</h3>
	<p>태그를 작성할 때 jsp prefix를 사용한다. 반드시 닫기 태그 명시해야한다.</p>
	<%-- ex) <jsp:태그명></jsp:태그명> --%>
	<h3>jsp:include 태그 활용하기</h3>
	<p>다른 jsp 페이지를 불러와서 출력해주는 태그</p><%--  <%@ include %>태그와 비슷한 기능 --%>
	<p>jsp:include page="불러올 페이지 경로"</p>
	<a href="<%=request.getContextPath() %>/views/includeTest.jsp">jsp:include Test</a>
	
	<h3>커스텀 액션 태그 -> JSTL 이용하기</h3>
	<h4>EL 표현식 활용하기</h4>
	<p>java 코드로 생성된 데이터를 페이지에 출력해주는 표현식 </p>
	<%-- <p>${} -> 공용 객체(request, session, Application) 저장된 데이터를 불러와서 출력함</p> --%>
	<h3><a href="<%=request.getContextPath()%>/views/el/eltest.jsp">el 활용하기</a></h3>
</body>
</html>