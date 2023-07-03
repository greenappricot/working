<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>function</title>
</head>
<body>
	<c:set var="data" value="How are you? I'm fine"/>
	<h3><c:out value="${fn:toUpperCase(data)}"/></h3>
	<h3><c:out value="${fn:toLowerCase(data)}"/></h3>
	<h3><c:out value="${fn:replace(data,'fine','bad')}"/></h3>
	<h3><c:out value="${fn:contains(data,'fine')?'좋아':'슬퍼'}"/></h3>
</body>
</html>