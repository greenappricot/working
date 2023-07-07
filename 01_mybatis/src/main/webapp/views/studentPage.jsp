<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>페이징처리</title>
</head>
<body>
	<h2>학생 조회 결과</h2>
	<table class="table">
		<tr>
			<th>NO</th>
			<th>이름</th>
			<th>전화번호</th>
			<th>이메일</th>
			<th>주소</th>
			<th>등록일</th>
		</tr>
		<c:if test="${not empty students }">
			<c:forEach items="${students }" var="s">
			<tr>
				<td><c:out value="${s.studentNo }"/></td>
				<td><c:out value="${s.studentName }"/></td>
				<td><c:out value="${s.studentTel }"/></td>
				<td><c:out value="${s.studentEmail}"/></td>
				<td><c:out value="${s.studentAddress }"/></td>
				<td><c:out value="${s.reg_date }"/></td>
			</tr>
			</c:forEach>
		</c:if>
	</table>
	<div>
		${pageBar}
	</div>
</body>
</html>