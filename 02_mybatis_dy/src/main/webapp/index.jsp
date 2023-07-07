<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="path" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>사원 조회</title>
</head>
<body>
	<h3>전체 사원 조회</h3>
	<h4><a href="${path }/selectAllEmployee.do">조회</a></h4>
	<h2>DATA 연관관계설정하기</h2>
	<h3><a href="${path }/emp/association.do">join으로 객체 가져오기 </a></h3>
	<h2>전체 부서 조회하기</h2>
	<h3><a href="${path }/selectDeptAll.do">전체부서 조회하기</a></h3>
	<h2>다른 환경 접속하기</h2>
	<h3><a href="${path }/member.do">회원 가져오기</a></h3>
	
	<h2>게시글가져오기</h2>
	<h3><a href="${path }/board.do?no=2">게시글, 댓글, 작성자이름, 이메일 기져오기</a></h3>
</body>
</html>