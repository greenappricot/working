<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ajax 통신 활용하기</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
	<p>ajax : 비동기식으로 서버와 통신하는 기술</p>
	<h2>javascript로 ajax 통신 구현하기</h2>
	<p>
		XMLHttpRequest 객체 : javascript로 비동기 통신 구현하려면 js가 제공하는 객체를 이용한다.<br>
		1. XMLHttpRequest 객체 생성하기
		2. 필요한 속성에 대한 설정하기
			- 요청할 서버 주소, 요청 방식 등 설정
			- 요청이 끝나고 실행할 함수 설정(callback 함수) // 비동기식 통신이기 때문에 알아서 background에서 움직이기 때문에 callback함수가 반드시 필요하다.
		3. send() : 요청 보내는 함수 실행하기 
		// 기본적으로 어떤 것을 변경하기 위해서 ajax통신을 하기 때문에 변경 사항이 반영되는 곳을 지정해야한다.
	</p>
	<h3>js로 요청 보내기</h3>
	<input id="data" type="text">
	<button id="jsSendBtn">js로 ajax 통신</button>
	<button id="postBtn">js로 post방식 보내기</button>
	<div id="result"></div>
	
	<script>
		// 요청 방식 post로 보내기 
		const postBtn= document.getElementById("postBtn");
		postBtn.addEventListener("click",e=>{
			const request= new XMLHttpRequest();
			request.open("post","<%=request.getContextPath()%>/js/ajax.do");
			
			request.onreadystatechange=()=>{
				if(request.readyState==4){
					if(request.status==200){
						document.getElementById("result").innerHTML='<div class="spinner-border text-primary">'+request.responseText+'</div>';
						
					}
				}
			}//
			
			// post 방식으로 요청 보낼 때, header 설정을 추가해야한다.
			// content-type 속성 값 설정하기
			request.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
			
			// post 방식으로 데이터를 전송할 때는 send 함수의 매개변수로 데이터를 전송한다.
			const val=document.getElementById("data").value;
			request.send("param="+val+"&data=asdf"); // 다수의 값을 보낼 때는 jsp와 같이 &활용해서 보낸다
			document.getElementById("result").innerHTML="<h3>로딩중....</h3>";
		});
		
		
		
		const sendBtn=document.getElementById("jsSendBtn");
		sendBtn.addEventListener("click",e=>{
			// 클라이언트에게 입력받은 값 전송 하기
			const paramData=document.getElementById("data").value;
			
			// click 하면 server에 요청보냄
			// 1. new 연산자를 이용해서 XMLHttpRequest 객체 생성한다.
			const request= new XMLHttpRequest(); 
			console.log(request);
			
			// 2. 필수 속성에 대한 설정
			// open("요청 방식(method)","요청할 주소()"); : 함수 호출
			// 절대 경로를 이용해서 주소 설정하기
			request.open("get","<%=request.getContextPath()%>/js/ajax.do?param="+paramData);
			
			
			// 3. 요청이 끝나고 응답이 왔을 때 실행할 함수 지정 *** 주의
			// XMLHttpRequest 객체의 onreadystatechange 속성을 이용해서 이벤트 함수를 등록한다.
			// onreadystatechange 속성 : ajax 통신에 대한 상태 관리를 한다. 요청 상태가 변경될 때마다 실행되는 함수를 등록한다.
			// 변경되는 요청 상태는 readystate 속성에 저장된다. -> 0 ~ 4 사이의 값을 가진다.
			// * 4 : 응답 완료된 상태
			
			// status : 응답 코드 저장하는 속성 (default 0) -> 404(서비스 주소 찾을 수 없을 때), 500(서버 에러), 200(정상 통신 완료)
			
			// 매개변수 없는 함수 등록
			request.onreadystatechange=()=>{
				console.log(request.readyState);
				console.log(request.status);
				if(request.readyState==4){ 	// 4 : 응답 완료된 상태
					let msg="";
					switch(request.status){
						case 200 : msg="정상적으로 통신을 완료함"; break; 
						case 404 : msg="요청한 서비스가 없습니다 ;("; break;
					}
					document.getElementById("result").innerHTML=msg+" ";
					document.getElementById("result").innerHTML+=request.responseText;
					
					// 서버가 보낸 데이터 가져오기
					// XMLHttpRequest 객체의 responseText 속성 : 서버가 응답한 데이터를 문자열로 가져온다. 
					console.log(request.responseText);
				}
			}
			
			// 요청 보내기
			request.send();
			
			
		});
	</script>
</body>
</html>