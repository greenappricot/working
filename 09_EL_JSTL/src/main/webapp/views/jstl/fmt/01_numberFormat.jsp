<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>fmt</title>
</head>
<body>
	<h2>페이지에 숫자 표현하기</h2>
	<c:set var="numtest" value="123456012"/>
	<c:set var="numtest1" value="19883000"/>
	<c:set var="numtest2" value="1"/>
	<c:set var="numtest3" value="1234.567"/>
	
	<p>일반 숫자 출력 : ${numtest }</p>
	<p>기본 fmt 태그를 이용해서 출력 : <fmt:formatNumber value="${numtest }"/>원</p>
	<p>숫자 단위 쉼표를 처리하는 속성 : groupingUsed="true/false"</p>
	<p>true : <fmt:formatNumber value="${numtest }" groupingUsed="true"/></p>
	<p>false : <fmt:formatNumber value="${numtest }" groupingUsed="false"/></p><!-- default 값이 true  / 그냥 출력하는 것과 똑같이 출력할 수 있음 -->
	
	<h3>숫자를 화폐로 표시하기</h3><!-- locale 기준으로 화폐 통화가 출력됨 -->
	<p>type 속성을 currency로 설정</p>
	<p>원화로 표시 : <fmt:formatNumber value="${numtest1 }" type="currency"/></p>
	<p>원하는 통화 기호 표시 : <fmt:formatNumber value="${numtest1 }" type="currency" currencySymbol="^.~"/></p>
	<%-- <fmt:setLocale value="fr_FR"/> 
	<p>프랑스 통화 기호 표시 : <fmt:formatNumber value="${numtest1 }" type="currency"/></p>--%>
	<p>현재 로케일 확인 : ${pageContext.request.locale }</p>
	
	<h3>퍼센트 표시하기</h3>
	<p>소수점으로 표시 1 -> 100%, 0 -> 0% </p>
	<p>numtest2 : ${numtest2 }</p>
	<p>퍼센트 : <fmt:formatNumber value="${numtest2 }" type="percent"></fmt:formatNumber></p>
	<p>퍼센트 : <fmt:formatNumber value="0.5" type="percent"></fmt:formatNumber></p>
	<p>퍼센트 : <fmt:formatNumber value="0.25" type="percent"></fmt:formatNumber></p>
	
	<h3>패턴으로 숫자 표시하기 : 자릿수에 맞춰서 특정 문구를 출력할 수 있다.</h3>
	<p>
		0 : 지정한 자리에 수가 없으면 0으로 표시<br>
		# : 지정한 자리에 수가 없으면 생략(표시 x)
	</p><!-- oracle fm999,999,999 생각남 -->
	<p>0 : ${numtest3 } -> <fmt:formatNumber value="${numtest3 }" pattern="000,000,000"/></p>
	<p># : ${numtest3 } -> <fmt:formatNumber value="${numtest3 }" pattern="###,###,###"/></p>
	<p>소수점 0 : ${numtest3 } ->  <fmt:formatNumber value="${numtest3 }" pattern="000,000.000000"/></p><!-- dollar표기할 때 패턴사용가능 -->
	<p>소수점 # : ${numtest3 } ->  <fmt:formatNumber value="${numtest3 }" pattern="###,###.#######"/></p>
	<p>소수점 #, 0 혼용 : ${numtest3 } ->  <fmt:formatNumber value="${numtest3 }" pattern="###,###.000000"/></p>
	
	<h3>소수점 자리 수 설정하기</h3>
	<p>
		minFractionDigits : 최소 소수점 자리<br>
		maxFractionDigits : 최대 소수점 자리
	</p>
	<h3>
		<fmt:formatNumber value="123.1" minFractionDigits="2"/><!-- 123.10 -->
		<fmt:formatNumber value="123.123" minFractionDigits="2"/><!-- 123.123 -->
		
		<fmt:formatNumber value="123.126" maxFractionDigits="2"/><!-- 123.13 반올림처리함 -->
		<fmt:formatNumber value="123.1" maxFractionDigits="2"/><!-- 123.1 -->
		<fmt:formatNumber value="123.12613546515" maxFractionDigits="2"/><!-- 123.13 반올림처리함 -->
	</h3>
</body>
</html>