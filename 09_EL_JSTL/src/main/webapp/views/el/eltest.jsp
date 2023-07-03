<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.el.model.vo.Snack, java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>el 표현식 활용하기</title>
</head>
<body>
	<h3>EL 표현식 활용하기</h3>
	<p>
		EL <%--${}--%>로 공용 객체에 저장된 데이터를 페이지에 출력
		servlet에서 request, session에 setAttribute() 메소드로 저장한 데이터를 자동으로 탐색해서 key 값 기준으로 가져온다.
		getter 메소드를 호출하지 않고 필드 명을 작성하면 자동으로 EL이 getter를 호출해서 데이터를 출력해준다. (형변환을 안 해도 됨)
	</p>
	
	<h2>1. 리터럴 값 출력하기</h2>
	<p><%="오랜만에 수업 너무 재밌다" %></p>
	<p>${"우와 신기하다"}</p>
	<p>나이는 ${19 }</p><!-- 특정한 리터럴 값을 넣어도 무방함 잘 안 쓸뿐... -->
	
	<h2>2. 내장 객체(공용 객체)에 저장된 데이터 출력하기</h2>
	<p>request, session, application(ServletContext)에 저장된 데이터를 출력</p>
	<%
		request.setAttribute("name","유병승");
		session.setAttribute("age",19);
		application.setAttribute("test","기본 데이터");
		String email="yoo@yoo.com"; /* 일반적인 지역변수는 el이 가져올 수 없다. */
		request.setAttribute("email",email);
	%>
	<h4>${name }</h4>
	<h4>${age }</h4>
	<h4>${test }</h4>
	<h4>${email }</h4>
	
	
	<h2>3. EL표현식에서 연산자 활용하기</h2>
	<%
		request.setAttribute("su",19);
		request.setAttribute("su2",30);
		request.setAttribute("su3",30);
		request.setAttribute("testData","admin");
	%>
	<h3>3-1. 산술 연산 처리하기</h3>
	<h4>+ 연산 : ${su+su2 }</h4>
	<h4>- 연산 : ${su-su2 }</h4>
	<h4>* 연산 : ${su*su2 }</h4>
	<h4>/ 연산 : ${su/su2 }</h4>
	<h4>% 연산 : ${su%su2 }</h4>
	<h4>복합 산술 연산 : ${(su%su2*2)/(3+100)*su3 }</h4>
	
	<h3>3-2. 비교 연산 처리하기</h3>
	<h4>대소비교</h4>
		<p>&lt; : ${su < su2 }  ${su lt su2 }</p><!-- 부등호 표시가 아닌 문자로 비교 가능  -->
		<p>&gt; : ${su > su2 }  ${su gt su2 }</p>
		<p>&le; : ${su <= su2 } ${su le su2 }</p><!-- 부등호 표시가 아닌 문자로 비교 가능  -->
		<p>&ge; : ${su >= su2 } ${su ge su2 }</p>
		<p>범위 논리 연산 : ${su<20&&20<su2}</p>
		
	<h4>동등비교</h4>
	<p>== : ${su == su2 } ${su eq su2 }</p>
	<p>== : ${testData=="admin"} ${testData eq "admin" }</p><!-- 문자열 비교도 equals 쓰지 않고 바로 == 연산으로 비교할 수 있다. -->
	<p>!= : ${su!=su2} ${su ne su2}</p>
	<p>!= : ${testData!="userId"} ${testData ne "userId"}</p>
	<p>논리연산 : ${testData== "admin" && su2>19}</p>
	
	<h4>null 값 확인</h4>
	<p>== : ${ testb==null} ${testData==null}</p>
	
	<h4>3-3. 삼항연산자 활용하기</h4>
	<p>${su>10?"10보다 크다":"10보다 작다" }</p>
	<input type="checkbox" ${su>10?"checked":"" }>check
	<input type="checkbox" ${su>20?"checked":"" }>non-check
	
	
	<h4>메소드 호출하기</h4>
	<p>${testData.length() }</p>
	<p>${testData.charAt(0) }</p>
	<p>${testData.contains("a")?"있다":"없다" }</p>
	<p>${testData.substring(2) }</p>
	
	<h4>3-4. 논리 연산</h4>
	<p>&&, ||, and, or </p>
	<p>${snacks.get(0).type eq "chocolate" and snacks.get(0).price >5200 }</p>
	<p>${snacks.get(0).type eq "chocolate" or snacks.get(0).price >5200 }</p>
	
	<h3>4. 저장된 객체 탐색하기</h3>
	<p>request, session, application에 저장된 객체 데이터 가져오기</p>
	<%
		Snack s= Snack.builder().type("chocolate").name("m&m").price(1000).weight(50.0).build();
		Snack s2= Snack.builder().type("candy").name("chupachups").price(300).weight(10.0).build();
		Snack s3= Snack.builder().type("jelly").name("haribo").price(2000).weight(60.0).build();
		request.setAttribute("s", s);
		request.setAttribute("s2", s2);
		request.setAttribute("s3", s3);
		request.setAttribute("snacks",List.of(s,s2,s3));
		request.setAttribute("map",Map.of("s",s,"s2",s2));
		
	%>
	<p>저장된 객체는 key 값으로 불러와서 처리가 가능하다. 필드의 값을 불러올 때는 getter를 호출하지 않고 필드 명으로 불러온다</p>
	<p>저장된 s 불러오기 : ${s }</p><!-- toString overide 했기 때문에 이렇게 출력됨 -->
	<p>저장된 s의 필드 값 불러오기 : ${s.type } ${s.name } ${s.price }</p><!--getter를 호출하기 때문에 naming 규칙에 반하는 코드를 입력하면 NoSuchMethod error 발생함 -->
	<!-- vo객체에서 필드 명에서 pName, cName 같이 두번째 문자에 대문자를 넣으면 el 구문에서 인식하지 못할 수 있다.   -->
	<p>저장된 s2의 필드 값 불러오기 : ${s2.type } ${s2.name } ${s2.price*2 }</p>
	
	<h3>collection으로 저장된 객체 출력하기</h3>
	<p>list : ${snacks }</p>
	<p>list 직접 접근해서 값 가져오기 : ${snacks.get(0).type} ${snacks.get(0).name}</p>
	<p>list 직접 접근해서 값 가져오기 : ${snacks.get(1).type} ${snacks.get(1).name}</p>
	
	<p>map : ${map } ${map.s.price}  ${map.s2.price*100}</p>
	
	<h3>collection의 빈 값 확인하기</h3>
	<!-- 기존과 같이 size(), length(), isEmpty() 다 가능하지만 el 표현식 가능 -->
	<p>${snacks.isEmpty()}</p>
	<p>${empty snacks} ${not empty snacks}  ${snacks.size()>0}</p>
	
	
	<h3>5. servlet와 연동해서 date 처리하기</h3>
	<h4>
		<a href="${pageContext.request.contextPath }/dataTest.do">
			데이터처리실습
		</a>
	</h4>
	
	<h3>parameter 값을 el로 출력하기</h3>
	<form action="${pageContext.request.contextPath }/dataTest.do">
		<input type="text" name="userId">
		<input type="password" name="password">
		<input type="checkbox" name="hobby" value="운동">운동
		<input type="checkbox" name="hobby" value="독서">독서
		<input type="checkbox" name="hobby" value="코딩">코딩
		<input type="checkbox" name="hobby" value="영화">영화
		<input type="checkbox" name="hobby" value="등산">등산
		<input type="submit" value="제출">
	</form>
	
</body>
</html>
































