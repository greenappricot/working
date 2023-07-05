<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- <%
	String msg=(String)request.getAttribute("msg");
	String loc=(String)request.getAttribute("loc");
	String script=(String)request.getAttribute("script");
%> --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>시스템메세지</title>
</head>
<body>
	<script>
		<%--		<%=script!=null?script:""%>
		location.replace("<%=request.getContextPath()%><%=loc%>"); --%>
		alert('${msg}');
		${empty script?"":script}
		location.replace('${path}${loc}');
	</script>
</body>
</html>






