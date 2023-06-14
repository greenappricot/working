<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>json으로 데이터 가져오기</title>
<script src="http://code.jquery.com/jquery-3.7.0.min.js"></script>
</head>
<body>
	<h2>json을 이용해서 데이터 통신하기</h2>
	<p>server와 client가 데이터를 주고 받을 때 문자열을 js object 표현 방식으로 처리할 수 있다.</p>
	<!-- [a, b, c,] : 배열 / {k:v, k:v, ...} :객체 -->
	<ul>
		<li>java에서 생성한 객체(vo, Collection들, ...)를 자동으로 변환해주는 라이브러리를 사용한다.</li>
		<!-- json simple -->
		<li>jsonsimple.jar -> JSONObject Class, JSONArray Class의 멤버변수로 주는 메소드를 이용해서 처리한다.</li>
		<li>gson.jar -> Gson Class 이용</li>
		<li>jackson.jar -> ObjectMapper 클래스 이용 (* Spring에서 기본 사용한다.)</li>
	</ul>
	<button id="jsonBtn">jsonSimple</button>
	<button id="gsonBtn">Gson Test</button>
	<button id="jsonParse">parsing test</button>
	<script>
		// json simple 라이브러리 이용해서 데이터 가져오기
		$("#jsonBtn").click(e=>{
			$.get("<%=request.getContextPath()%>/basicJson.do",function(data){
				console.log(data);
				/* console.log(data.userId);
				console.log(data["age"]);
				console.log(data.height+20);
				if(data.flag){
					alert("실행");
				} */
			});
		});
		
		// gson 라이브러리 이용해서 데이터 가져오기
		<%-- $("#gsonBtn").click(e=>{
			$.get("<%=request.getContextPath()%>/gsonTest.do",data=>{
				console.log(data);
			});
		}); --%>
		
		// url주소에 get 방식으로 querystring으로 보내면 파싱하지 못해서 post 방식으로 보내야한다.
		// stringify() <->
		$("#gsonBtn").click(e=>{
			$.post("<%=request.getContextPath()%>/gsonTest.do",
					// member클래스의 멤버변수랑 이름 똑같이 불러와야함 
					{data:JSON.stringify({
						userId:"bsyoo",
						password:"1234",
						userName:"유병승",
						gender:"M",
						age:19,
						email:"teacherdev09@gmail.com",
						phone:"01012",
						address:"경기도시흥시",
						enrollDate:"20230614"
					})},
					data=>{
				
			});
		});
		
		
		
		// fetch 함수 이용해서 parsing해서 데이터 가져오기 
		$("#jsonParse").click(e=>{
			fetch("<%=request.getContextPath()%>/gsonTest.do",
				{ // ajax처럼 속성에 객체를 줄 수 있다.
				method:"post",
				// js에서 작성한 객체를 자바로 문자열로 바꿔서 string화해서 전송한다.
				body:
					JSON.stringify({
					"userId":"bsyoo",
					"password":"1234",
					"age":19,
					"userName":"유병승",
					"gender":"M",
					"email":"teacherdev09@gmail.com"
					})
				}).then(response=>response.json()).then(data=>{
					console.log(data);
					})
		});
	</script>
</body>
</html>









