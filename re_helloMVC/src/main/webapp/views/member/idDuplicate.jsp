<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.web.member.model.vo.Member" %>
<c:set var="path" value="${pageContext.request.contextPath }"/>
<%-- <%
	Member m=(Member)request.getAttribute("result"); 
%> --%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디중복확인</title>
<style>
	div#checkId-container{
		text-align:center;
		padding-top:50px;
	}
	span#duplicated{
		color:red;
		font-weight:bolder;
	}
</style>
</head>
<body>
	<div id="checkId-container">
	<c:if test="${empty result }">
		[<span>${userId}</span>]는 사용가능합니다.	
		<br><br>
		<button type="button">닫기</button>
	</c:if>
	<c:if test="${not empty result }">
		[<span id="duplicated">${userId}</span>]는 사용중입니다.
		<br><br>
		<!-- 아이디 재입력창 구성 -->
		<form action="${path}/member/idDuplicate.do" method="get">
			<input type="text" name="userId" id="userId">
			<input type="submit" value="중복검사" >
		</form>
	</c:if>
	</div>
	<script>
		const btn=document.querySelector("button[type=button]");
		btn.addEventListener("click",e=>{
			opener.document.querySelector("#userId_").value=${userId};
			close();
		});
	</script>
</body>
</html>



