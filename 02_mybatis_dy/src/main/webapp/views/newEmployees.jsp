<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>객체 조회 결과 가져오기</title>
</head>
<body>
	<c:if test="${not empty employees }">
		<table class="table">
			<tr>
					<th>사원 아이디</th>
					<th>이름</th>
					<th>주민등록번호</th>
					<th>이메일</th>
					<th>전화번호</th>
					<th>부서명</th>
					<th>직책코드</th>
					<th>급여 레벨</th>
					<th>급여</th>
					<th>보너스</th>
					<th>매니저 아이디</th>
					<th>입사일</th>
					<th>퇴사일</th>
					<th>퇴사여부</th>
				</tr>
			<c:forEach var="e" items="${employees }">
				<tr>
					<td>${e.empId }</td>
					<td>${e.empName }</td>
					<td>${e.empNo }</td>
					<td>${e.email }</td>
					<td>${e.phone }</td>
					<td>
						<ul>
							<li>${e.dept.deptId}</li>
							<li>${e.dept.deptTitle}</li>
							<li>${e.dept.locationId}</li>
						</ul>
					</td>
					<td>${e.jobCode }</td>
					<td>${e.salary }</td>
					<td>${e.bonus }</td>
					<td>${e.salLevel }</td>
					<td>${e.managerId }</td>
					<td><fmt:formatDate value="${e.hireDate}" type="date" pattern="yyyy-MM-dd"/></td>
					<td><fmt:formatDate value="${e.entDate}" type="date" pattern="yyyy-MM-dd"/></td>
					<td>${e.entYn }</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
</body>
</html>