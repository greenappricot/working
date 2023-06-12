<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>jQuery Ajax</title>
<script src="http://code.jquery.com/jquery-3.7.0.min.js"></script>
</head>
<body>
	<h2>jQuery가 제공하는 함수 이용하기</h2>
	<ol>
		<li>$.ajax({element}) : 요청에 대한 상세 설정 할 수 있다. ()header, 요청 내용 등)</li>
		<li>$.get("",(data)=>{}) : 기본 get 방식으로 요청 처리 - 간편 함수(매개변수 2개만 보내면 됨, 요청 주소, callback 함수)</li>
		<li>$.post("",{(parameterData)},(data)=>{}) : 기본 post 방식으로 요청 처리 -> 간편 함수</li>
	</ol>
	<h2>$.ajax() 함수 활용하기</h2>
	<p>
		요청에 대해 설정한 객체를 매개 변수로 전달한다.
		$.ajax() 함수 정해져 있는 key값을 사용한다.
			- url : 요청 주소를 설정 -> String<br>
			- [type] : 요청 방식 설정(get, post) - String / 생략 가능, default : get;<br>
			- [data] : 서버에 요청할 때 전송할 데이터 설정 -> Object({key:value, k:v, k:v, ...}) 생략 가능<br>
			- [dataType] : 응답 데이터 타입에 대한 설정 -> String (json, html, text, ...)<br>
			- success : 응답 완료되고 실행되는 callback 함수로, status가 200일 때(정상 통신 완료) 실행하는 함수 -> (data)=>{}<br>
			- [error] : 응답 완료되고 실행되는 callback 함수로, status가 200이 아닐 때 실행하는 함수 -> (e,r,m)=>{}<br>
			- [complete] : 결과에 상관 없이 응답이 완료되면 무조건 실행되는 함수 -> ()=>{}<br>
	</p>
	<button id="btn">기본 $.ajax이용하기</button>
	<button id="btnGet">기본 $.get이용하기</button>
	<button id="btnPost">기본 $.post이용하기</button>
	<div id="container"></div>
	<script>
		// jquery가 제공하는 $.get() 함수 이용하기 
		$("#btnGet").click(e=>{
			$.get("<%=request.getContextPath()%>/jquery/ajax.do?name=최주영&age=27",data=>{
				console.log(data);
				$("<h4>").text(data).css("color","blue").appendTo($("#container"));
			});
		});
		
		// jquery가 제공하는 $.post() 함수 이용하기 
		$("#btnPost").click(e=>{
			$.post("<%=request.getContextPath()%>/jquery/ajax.do",
					{name:"김재훈",age:29},
					data=>{
						$("<h1>").text(data).css("color","cornflowerblue").appendTo($("#container"));
			});
		});
		
		// jquery가 제공하는 $.ajax() 함수 이용하기
		$("#btn").click(e=>{
			// const request={
		    //}
		    //$.ajax(request); -> 이렇게 처리해도 된다
			$.ajax({
				url:"<%=request.getContextPath()%>/jquery/ajax.do",
				//type:"get",
				type:"post",
				data:{
					name:"유병승",
					age:19
				}, // get 방식으로 보내면 f12 network - header를 확인하면 queryString으로 key:value 형식으로 데이터가 넘어가는 것을 알 수 있다.
				
				// callback 함수로, 특정 시점에 시작된다
				// status의 결과에 따라 success, error 중 하나가 실행된다.
				success:(data)=>{ 
					// responseText에 저장된 값을 data에 대입한다.
					// console.log(data);
					$("<h3>").text(data).css("color","magenta").appendTo($("#container"));
				},
				error: (e,m)=>{
					// console.log(e); // element
					// console.log(m);
					if(e.status==404) alert("요청한 페이지가 없습니다.");
				},
				complete:()=>{
					alert("서버와 통신 끝"); // success나 error가 끝난 뒤에 무조건 complete가 실행 된다
				}
			}); 
		});
	</script>
	
	<h2>서버에 저장되어 있는 문자 파일 가져오기</h2>
	<button id="btnFile">test.txt 가져오기</button>
	<div id="result"></div>
	<script>
	$("#btnFile").click(e=>{
		$.get("<%=request.getContextPath()%>/test/test.txt",
				data=>{
					const arr=data.split("\n");
					arr.forEach(e=>{
						$("#result").append($("<p>").text(e));
					});
					const person=arr[arr.length-1];
					const persons=person.split("\\n");
					const table=$("<table>");
					persons.forEach(e=>{
						const tr=$("<tr>");
						const p=e.split(",");
						p.forEach(d=>{
							tr.append($("<td>").text(d).css("border","1px solid black"));
						});
						table.append(tr);
					});
					$("#result").append(table);
				});
	<%-- 	$.ajax({
			url:"<%=request.getContextPath()%>/test/test.txt",
			dataType:"text",
			success:data=>{
				console.log(data);
				const arr=data.split("\n");
				console.log(arr);
				arr.forEach(e=>{
					$("#result2").append($("<p>").text(e));
				});
				const person=arr[arr.length-1];
				console.log(person);
				const persons=person.split("\\n");
				console.log(persons);
				const table=$("<table>");
				persons.forEach(e=>{
					const tr=$("<tr>");
					const p=e.split(",");
					p.forEach(d=>{
						tr.append($("<td>").text(d).css("border","1px solid black"));
					});
					table.append(tr);
				});
				$("#result2").append(table);
			}
		}); --%>
	});
	</script>
	
	<h2>서버에서 전송하는 CSV방식의 데이터 처리하기</h2>
	<p>
		CSV(Comma-Separated Values) : 문자열을 데이터별로 구분할 수 있게 만들어놓은 것으로, MIME 형식은 text/csv이다.
									  , \n, $ 등으로 구분할 수 있는 문자열
									  ex) 유병승,19,남,경기도시흥$최솔,29,여,경기도안양시$
	</p>
	<button id="btnCsv">csv 데이터 가져오기</button>
	<div id="csvContainer"></div>
	<script>
		$("#btnCsv").click(e=>{
			$.ajax({
				url:"<%=request.getContextPath()%>/ajax/csvData.do",
				dataType:"text",
				success :data=>{
					console.log(data);
					const actors=data.split("\n");
					console.log(actors);
					// data 공유하기 위해서 json (data:data로 처리)
					const $table=$("<table>");
					const $header="<tr><th>이름</th><th>전화번호</th><th>프로필</th></tr>";
					$table.html($header);
					actors.forEach(a=>{
						const $tr=$("<tr>");
						const actor=a.split(",");
						const $name=$("<td>").text(actor[0]);
						const $phone=$("<td>").text(actor[1]);
						const $profile=$("<td>").append($("<img>").attr({
							"src":'<%=request.getContextPath()%>/images/'+actor[2],
							"width":"100px",
							"height":"100px"
						}));
						$tr.append($name).append($phone).append($profile);
						$table.append($tr);
					});
					$("#csvContainer").html($table);
				}
				//else{
				//	$("#csvContainer").html("<h4>조회된 데이터가 없습니다.</h4>");
				//}
			});
		});
	</script>
	
	
</body>
</html>








