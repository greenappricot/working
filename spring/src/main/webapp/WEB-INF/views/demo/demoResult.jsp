<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param name="title" value="demoResult"/>
</jsp:include>
<style>
	table#tbl-dev{
		margin:0 auto;
		width:50%;
	}
</style>
<section id="content">
	<table class="table" id="tbl-dev">
		<c:if test="${not empty demo }">
		<tr>
			<th scope="col">이름</th>
			<td>${demo.devName }</td>
		<tr>
		<tr>
			<th>나이</th>
			<td>${demo.devAge }</td>
		</tr>
		<tr>
			<th>이메일</th>
			<td>${demo.devEmail }</td>
		</tr>
		<tr>
			<th>성별</th>
			<td>${demo.devGender }</td>
		</tr>
		<tr>
			<th>개발가능언어</th>
			<c:forEach items="${demo.devLang}" var="d">
				<td>
					<c:out value="${d}"/>
				</td>
			</c:forEach>
		</tr>
		</c:if>
		<c:if test="${empty demo }">
			<tr>
				<td>결과가 없습니다</td>
			</tr>
		</c:if>
	</table>
</section>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>