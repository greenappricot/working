<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
 <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<c:set var="path" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>사원 조회</title>
</head>
<body>
	<h3>사원 조회 결과</h3>
	<form action="${path}/searchEmp.do" method="post">
		<table class="table">
			<tr>
				<td>
					<select name="type">
						<option value="emp_id">사원번호</option>
						<option value="emp_name">사원이름</option>
						<option value="email">이메일</option>
						<option value="phone">전화번호</option>
					</select>
				</td>
				<td>
					<input type="text" name="keyword"/>
				</td>
			</tr>
			<tr>
				<td>성별</td>
				<td>
					<label><input type="radio" name="gender" value="M">M</label>
					<label><input type="radio" name="gender" value="F">F</label>
				</td>
			</tr>
			<tr>
				<td>급여</td>
				<td>
					<input type="number" name="salary" step="500000" min="1200000">
					<label><input type="radio" name="salFlag" value="ge">이상</label>
					<label><input type="radio" name="salFlag" value="le">이하</label>
				</td>
			</tr>
			<tr>
				<td>부서</td>
				<td>
					<label><input type="checkbox" name="deptCode" value="D1">인사관리부</label>
					<label><input type="checkbox" name="deptCode" value="D2">회계관리부</label>
					<label><input type="checkbox" name="deptCode" value="D3">마케팅부</label>
					<label><input type="checkbox" name="deptCode" value="D4">국내영업부</label>
					<label><input type="checkbox" name="deptCode" value="D5">해외영업1부</label>
					<label><input type="checkbox" name="deptCode" value="D6">해외영업2부</label>
					<label><input type="checkbox" name="deptCode" value="D7">해외영업3부</label>
					<label><input type="checkbox" name="deptCode" value="D8">기술지원부</label>
					<label><input type="checkbox" name="deptCode" value="D9">총무부</label>
				</td>
			</tr>
			<tr>
				<td>직책</td>
				<td>
					<label><input type="checkbox" name="jobCode" value="J1">대표</label>
					<label><input type="checkbox" name="jobCode" value="J2">부사장</label>
					<label><input type="checkbox" name="jobCode" value="J3">부장</label>
					<label><input type="checkbox" name="jobCode" value="J4">차장</label>
					<label><input type="checkbox" name="jobCode" value="J5">과장</label>
					<label><input type="checkbox" name="jobCode" value="J6">대리</label>
					<label><input type="checkbox" name="jobCode" value="J7">사원</label>
				</td>
			</tr>
			<tr>
				<td>입사 일</td>
				<td>
					<input type="date" name="hireDate">
					<label><input type="radio" name="hireFlag" value="ge">이후</label>
					<label><input type="radio" name="hireFlag" value="le">이전</label>
				</td>
			</tr>
			<!-- <tr>
				<td>매니저 아이디</td>
				<td>
					<label><input type="checkbox" name="managerId" value=""></label>
				</td>
			</tr> -->
			<tr>
				<td>퇴사 여부</td>
				<td>
					<label><input type="radio" name="entYn" value="Y">Y</label>
					<label><input type="radio" name="entYn" value="N">N</label>
				</td>
			</tr>
			<tr>
				<td>
					<input type="submit" value="검색">
				</td>
			</tr>
		</table>
	</form>
	<c:choose>
		<c:when test="${empty employee }">
			<h3>조회된 사원이 없습니다</h3>
		</c:when>
		<c:otherwise>
			<table class="table">
				<tr>
					<th>사원 아이디</th>
					<th>이름</th>
					<th>주민등록번호</th>
					<th>이메일</th>
					<th>전화번호</th>
					<th>부서코드</th>
					<th>직책코드</th>
					<th>급여 레벨</th>
					<th>급여</th>
					<th>보너스</th>
					<th>매니저 아이디</th>
					<th>입사일</th>
					<th>퇴사일</th>
					<th>퇴사여부</th>
					<th>성별</th>
				</tr>
				<c:forEach items="${employee }" var="e">
					<tr>
						<td><c:out value="${e.empId }"/></td>
						<td><c:out value="${e.empName }"/></td>
						<td><c:out value="${e.empNo}"/></td>
						<td><c:out value="${e.email }"/></td>
						<td><c:out value="${e.phone }"/></td>
						<td><c:out value="${e.deptCode}"/></td>
						<%-- <td><c:out value="${e.deptTitle}"/></td> --%>
						<td><c:out value="${e.jobCode }"/></td>
						<td><c:out value="${e.salLevel}"/></td>
						<td><fmt:formatNumber type="currency" value="${e.salary}"/>원</td>
						<td><fmt:formatNumber type="percent" value="${e.bonus }"/></td>
						<td><c:out value="${e.managerId}"/></td>
						<td><fmt:formatDate value="${e.hireDate}" type="date" pattern="yyyy-MM-dd"/></td>
						<td><fmt:formatDate value="${e.entDate}" type="date" pattern="yyyy-MM-dd"/></td>
						<td><c:out value="${e.entYn}"/></td>
						<td>${e.gender=='M'?'남':'여' }</td>
					</tr>
				</c:forEach>
			</table>
		</c:otherwise>
	</c:choose>
	<div>
		${pageBar }
	</div>
</body>
</html>