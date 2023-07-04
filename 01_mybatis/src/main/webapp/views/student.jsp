<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>학생 조회 결과</title>
</head>
<body>
	<h2>학생 정보</h2>
	<c:if test="${count!=null}">
		<h3>전체 학생 수 : <c:out value="${count }"/></h3>
	</c:if>
	<c:if test="${s!=null}">
		<ul>
			<li>학생 이름 : <c:out value="${s.studentName }"/></li>
			<li>학생 전화번호 : <c:out value="${s.studentTel }"/></li>
			<li>학생 이메일 : <c:out value="${s.studentEmail }"/></li>
			<li>학생 주소 : <c:out value="${s.studentAddress }"/></li>
			<li>등록일 : <c:out value="${s.reg_date }"/></li>
		</ul>
	</c:if>
	<c:if test="${students.size()>0}">
	<%-- <c:if test="${not empty students}"> --%>
		<table>
			<tr>
				<th>번호</th>
				<th>이름</th>
				<th>전화번호</th>
				<th>이메일</th>
				<th>주소</th>
				<th>등록일</th>
			</tr>
			<c:forEach var="s" items="${students}" varStatus="vs">
				<tr>
					<td><c:out value="${s.studentName }"/></td>
					<td><c:out value="${s.studentTel }"/></td>
					<td><c:out value="${s.studentEmail }"/></td>
					<td><c:out value="${s.studentAddress }"/></td>
					<td><fmt:formatDate value="${s.reg_date }"/></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
</body>
</html>