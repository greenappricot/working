<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>date format</title>
</head>
<body>
	<h2>날짜 데이터 처리</h2>
	<c:set var="today" value="<%=new Date() %>"/>
	<h3><c:out value="${today }"/></h3>
	<ul>
		<li>type : date, time, both 값을 설정<br>
			date : 날짜만 출력 / 년, 월, 일 출력<br>
			time : 시간만 출력 / 시, 분, 초 출력<br>
			both : 날짜와 시간을 모두 출력/ 년, 월, 일, 시, 분, 초
		</li> 
		<li>dateStyle : 날짜를 출력하는 방식(default, short, long, full)</li>
		<li>timeStyle: 시간을 출력하는 방식(medium, short, long, full)</li>
	</ul>
	<h3>date : <fmt:formatDate value="${today }" type="date"/></h3>
	<h3>time : <fmt:formatDate value="${today }" type="time"/></h3>
	<h3>both : <fmt:formatDate value="${today }" type="both"/></h3>
	
	<h2>기본 스타일을 이용해서 날짜 형식 변경하기</h2>
	<h3>dateStyle(default) : <fmt:formatDate value="${today }" type="date" dateStyle="default"/></h3>
	<h3>dateStyle(short) : <fmt:formatDate value="${today }" type="date" dateStyle="short"/></h3>
	<h3>dateStyle(long) : <fmt:formatDate value="${today }" type="date" dateStyle="long"/></h3>
	<h3>dateStyle(full) : <fmt:formatDate value="${today }" type="date" dateStyle="full"/></h3>
	
	<h2>기본 스타일을 이용해서 시간 형식 변경하기</h2>
	<h3>timeStyle(default) : <fmt:formatDate value="${today }" type="time" timeStyle="medium"/></h3>
	<h3>timeStyle(short) : <fmt:formatDate value="${today }" type="time" timeStyle="short"/></h3>
	<h3>timeStyle(long) : <fmt:formatDate value="${today }" type="time" timeStyle="long"/></h3>
	<h3>timeStyle(full) : <fmt:formatDate value="${today }" type="time" timeStyle="full"/></h3>
	
	<h2>시간, 날짜 스타일을 이용해서 형식 변경하기</h2>
	<h3>both/ dateStyle, timeStyle 따로 적용 : <fmt:formatDate value="${today }" type="both" dateStyle="full" timeStyle="short"/></h3>
	
	<h2>pattern 속성 : 패턴으로 스타일 커스터마이징하기</h2>
	<p>
		yy(년도) MM(월) dd(일) hh(시간) mm(분) ss(초) SSS
	</p>
	<h3><fmt:formatDate value="${today }" type="date" pattern="yyyy/MM/dd"/></h3>
	<h3><fmt:formatDate value="${today }" type="time" pattern="hh:mm:ss"/></h3>
	<h3><fmt:formatDate value="${today }" type="both" pattern="yyyy-MM-dd (E) hh:mm:ss"/></h3>
	
	<h2>시간 기준 설정해서 출력하기</h2>
	<h3>
		<fmt:timeZone value="GMT">
			<fmt:formatDate value="${today }" type="time"/>
		</fmt:timeZone>
		<fmt:timeZone value="GMT+9">
			<fmt:formatDate value="${today }" type="time"/>
		</fmt:timeZone>
	</h3>
	
	<h2>로케일 설정을 바꾸면 나라에 맞는 형식으로 날짜를 출력</h2>
	<h3>
		<fmt:formatDate value="${today }" type="both" dateStyle="full" timeStyle="full"/>
	</h3>
	<h3>
		<fmt:setLocale value="fr_FR"/>
		<fmt:formatDate value="${today }" type="both" dateStyle="full" timeStyle="full"/>
	</h3>
</body>
</html>














