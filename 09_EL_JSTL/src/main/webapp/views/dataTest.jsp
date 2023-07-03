<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>서버에서 전송한 데이터 출력하기</h2>
	<h4>${snacks.get(0).type } >${snacks.get(0).name }</h4>
	<h4>${snacks.get(1).type } >${snacks.get(1).name }</h4>
	<h4>${snacks.get(2).type } >${snacks.get(2).name }</h4>
	<h4>${snacks.get(3).type } >${snacks.get(3).name }</h4>
	
	
	<h2>내장 객체에 중복 키 값으로 저장된 데이터 가져오기</h2>
	<p>EL에서 내장 객체의 데이터를 가져올 때 작은 범위부터 큰 범위로 탐색한다</p>
	<p> request -> session -> servletContext </p>
	<h4>${snack }</h4>
	
	<h4>EL이 제공하는 내장 객체를 이용해서 중복된 키 값이 있을 때 특정 영역에서 데이터 불러올 수 있다. </h4>
	<p>requestScope : request 영역에서만 key값 검색</p>
	<p>sessionScope : session 영역에서만 key값 검색</p>
	<p>applicationScope : servletContext 영역에서만 key값 검색</p>
	<h4>${requestScope.snack }</h4>
	<h4>${sessionScope.snack }</h4>
	<h4>${applicationScope.snack }</h4>
	<h4>${requestScope.snacks.get(0).name }</h4>
	
	<h2>parameter 값 출력하기</h2>
	<p>param : 내장 객체에서 먼저 찾기 때문에 parameter 값을 찾으려면 별도의 el 객체를 이용해서 출력해야한다. </p>
	<h3>${param.userId } ${param.password }</h3>
	
	<p>paramValues : parameter 값이 다수인 경우 배열로 값을 가져오기 위해 사용한다.</p>
	<h3>${paramValues.hobby[0] }</h3>
	<h3>${paramValues.hobby[1] }</h3>
	<h3>${paramValues.hobby[2] }</h3>
	
	
	
	
</body>
</html>











