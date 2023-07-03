<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- JSTL을 적용하기 위해서 반드시 페이지에 지시자로 taglib를 선언해야한다. -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- textnode가 없어서 전부 속성 값으로 설정해야한다. -->
	<h3><c:out value="우와 코어 태그로 출력"/></h3>
	<%-- ${"우와 코어 태그로 출력" } == <%="우와 코어 태그로 출력" %> ==<c:out value="우와 코어 태그로 출력"/> --%>
	
	<h2>set / out 태그 이용하기</h2>
	<p>c:out 태그 : value 속성에 있는 값을 page에 출력할 때 사용하는 태그</p>
	<p>c:set 태그 : 내장 객체 영역에 데이터를 key:value 형식으로 저장할 때 사용하는 태그</p>
	
	<h4>c:set 태그 속성</h4>
	<ul>
		<li>var : key값(변수명)</li>
		<li>value : key에 저장될 값 /  EL 표현식, 리터럴 사용</li>
		<li>scope : 변수가 선언될 내장 객체 지정, request, session, application</li>
	</ul>
	<h4>c:out 태그 속성</h4>
	<ul>
		<li>value : 출력될 데이터, EL 표현식, 리터럴 사용</li>
		<li>default : 출력될 데이터가 없을 때, 대체로 출력하는 기본 값</li>
		<li>escapeXml : value속성에 태그 형식으로 작성했을 때 태그로 해석할지 여부를 선택</li>
	</ul>
	
	<h3>변수 선언하기</h3>
	<c:set var="comment" value="점심 맛있게 먹었나요"/>
	<p>${comment }</p>
	<c:set var="path" value="${pageContext.request.contextPath }"/>
	<p>절대 경로 : ${path }</p>
	
	<c:set var="test" value="requestData" scope="request"/>
	<c:set var="test" value="sessionData" scope="session"/>
	<c:set var="test" value="applicationData" scope="application"/>
	<p>${test }</p>
	<p>${sessionScope.test}</p>
	<p>${applicationScope.test }</p>
	
	
	<h3>c:out 태그로 데이터 출력하기</h3>
	<p><c:out value="점심은 뭐 드셨나요?"/></p>
	<p><c:out value="${path }"/></p> <!-- 보안상 이렇게 사용하는 것이 좋다 -->
	<p>${path }</p>
	<c:set value="<script>location.assign('http://www.naver.com');</script>" var="testData"/>
	<%-- <div>${testData }</div> --%><!-- 새로고침 하면 바로 location.assign 되어버리기 때문에 위험에 노출될 수 있다. -->
	<div><c:out value="${testData }"/></div><!-- script 안의 구문이 문자열로 출력됨  -->
	<div><c:out value="${testData }" escapeXml="true"/></div><!-- Xml을 이용해서 script를 해석 여부를 선택할 수 있다. -> true로 설정하면 실행되지 않음 -->
	
	<h4>default 값을 지정해서 값이 없을 때 출력될 값을 지정할 수 있다.</h4>
	<p>${test11==null?"없음":test11 }</p><!-- 삼항 연산자로 설정할 수 있다. -->
	<c:set value="있는 값" var="test11"/>
	<p><c:out value="${test11 }" default="없음"/></p>
	
	<h3>c:url 태그</h3>
	<p>링크되는 주소 값 데이터를 저장하는 태그 == c:set / c:set과의 차이 점 : 파라미터 값을 설정할 수 있다.</p>
	<c:url var="searchlunch" value="http://search.naver.com/search.naver">
		<c:param name="query" value="점심"/>
	</c:url>
	<a href="${searchlunch }">검색</a>
	<a href="<c:out value='${searchlunch }'/>">검색</a>
	
</body>
</html>
















