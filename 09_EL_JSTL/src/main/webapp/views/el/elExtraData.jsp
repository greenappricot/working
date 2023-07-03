<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>el에서 추가 데이터 출력하기</title>
</head>
<body>
	<h2>context에 대한 정보</h2>
	<h3>${pageContext.request.contextPath}</h3>
	<h3>${pageContext.request.requestURI}</h3>
	<h3>${pageContext.request.requestURL}</h3>
	
	<h2>Cookie 정보 출력하기</h2>
	<h3>cookie : ${cookie }</h3>
	<h3>cookie : ${cookie.JSESSIONID.name}</h3><h3>cookie : ${cookie.JSESSIONID.value}</h3>
	
	<h2>Header 정보 출력하기</h2>
	<p>header 안의 정보가 "-"가 들어가있는 key값이 많기 때문에 [] 안의 키 값을 넣어서 값을 불러올 수 있다 "-"가 없는 값은 그냥 . 찍고 불러올 수 있음</p>
	<h3>Header : ${header}</h3>
	<h3>${header["user-agent"] }</h3>
	<h3>${header.host }</h3>
	
	
</body>
</html>