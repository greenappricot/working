<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>boot main page</title>
<body>
	<h2>나의 첫 boot 화면</h2>
	<h3><a href="${pageContext.request.contextPath }/member/memberAll">전체 회원 조회</a></h3>
	<form action="${pageContext.request.contextPath }/fileUpload" method="post" enctype="multipart/form-data"><!-- application.yml에 multipart/form  resolver 등록 -->
		<input type="file" name="upFile"/>
		<input type="file" name="upFile"/>
		<input type="file" name="upFile"/>
		<input type="submit" value="파일 저장"/>
	</form>
	<form action="${pageContext.request.contextPath}/datatest" method="post">
		<input type="text" name="data"/>
		<input type="submit" value="전송"/>
	</form>
	<!-- 아이디로 조회 -->
	<form action="${pageContext.request.contextPath}/memberId" method="post">
		<input type="text" name="userId"/>
		<input type="submit" value="아이디 검색"/>
	</form>
	
	<form action="${pageContext.request.contextPath}/membername" method="get">
		<input type="text" name="name"/>
		<input type="submit" value="이름으로 검색"/>
	</form>
	
	<button onclick="openChatting();">채팅하기</button>
	<script>
		function openChatting(){
			open("/chattingpage","_blank","width=500, height=600");
		}
	</script>
</body>
</html>