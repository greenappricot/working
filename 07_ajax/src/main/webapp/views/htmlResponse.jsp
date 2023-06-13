<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- <!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ajax response jsp</title>
</head>
<body>

</body>
</html> -->
<!-- 응답할 위치에 전체 페이지가 들어가는 경우가 아니라면, html등 위의 코드를 제외하고, 필요한 코드만 작성해도 된다. -->
<%@ page import="java.util.List, com.ajax.model.dto.Actor" %>
<%
	List<Actor> actors= (List<Actor>)request.getAttribute("actors");
%>
<table id="t">
	<tr>
		<th>이름</th>
		<th>전화번호</th>
		<th>프로필</th>
	</tr>
	<%if(actors.isEmpty()) {%>
		<tr>
			<td colspan="3">조회된 배우가 없습니다.</td>
		</tr>
	<%}else{ 
		for(Actor a:actors){ %>
		<tr>
			<td><%=a.getName()%></td>
			<td><%=a.getPhone()%></td>
			<td><img src="<%=request.getContextPath()%>/images/<%=a.getProfile()%>" width="100"></td>
		</tr>
		<%} 
	}%>
</table>
<style>
	#t{margin-top:2rem;}
</style>
