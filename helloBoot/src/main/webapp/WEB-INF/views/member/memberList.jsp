<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>boot main page</title>
<body>
	<h3>전체 회원 조회</h3>
	<c:if test="${not empty members }">
		<table>
			<tr>
				<th>아이디</th>
				<th>이름</th>
				<th>나이</th>
				<th>성별</th>
				<th>이메일</th>
			</tr>
			<c:forEach var="m" items="${members }">
				<tr>
					<td>${m.userId }</td>
					<td>${m.userName }</td>
					<td>${m.age }</td>
					<td>${m.gender }</td>
					<td>${m.email }</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
</body>
</html>