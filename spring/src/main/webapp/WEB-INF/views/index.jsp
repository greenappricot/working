<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param name="title" value="메인화면"/>
</jsp:include>
<section id="content">
	<h2>Hello Spring</h2>
	<h3>ajax 통신하기</h3>
	<h4><button class="btn btn-outline-primary" onclick="basicAjax();">기본 ajax 처리</button></h4>
	<h4><button class="btn btn-outline-success" onclick="convertAjax();">json 받기</button></h4>
	<h4><button class="btn btn-outline-warning" onclick="basic2();">jsp 받아오기</button></h4>
	<h4><button class="btn btn-outline-danger" onclick="selectMemberAll();">전체 회원 가져오기</button></h4>
	<h4><button class="btn btn-outline-dark" onclick="insertData();">데이터 저장하기</button></h4>
	<div id="ajaxContainer"></div><!-- table 형식으로 전체 회원 정보 출력하기 -->
	<script>
		
		// 프론트에서 데이터 전송해서 저장하기
		const insertData=()=>{
		/* $.post("${pageContext.request.contextPath}/ajax/insertData.do",
					{userId:"test1", password:"test", userName:"테스트",age:19, gender:"M"},
					data=>{
						console.log(data);
					}); */
			const data = {userId:"test1", password:"test", userName:"테스트",age:19, gender:"M"};
			/* $.ajax({
				url : "${pageContext.request.contextPath}/ajax/insertData.do",
				type : "post",
				data : JSON.stringify(data), // 객체를 json 형식으로 변환 
				contentType : "application/json;charset=utf-8", // 요청을 보내는 타입 설정
				success : data =>{
					console.log(data);
				}
			}) */
			
			// javascript가 fetch 함수를 제공함
			// jquery와 달리 다른 라이브러리가 없어도 사용할 수 있는 fetch 함수를 제공함 
			// callback 함수로 promise / then 구절 사용 
			// fetch("url-address",{요청에 대한 옵션(K:V 형태의 객체)}) // HttpRequest를 요청하고 HttpResponse를 반환함
			//	.then(response=>{response.json()}) // 응답 내용을 parsing할 수 있다. (중간매개체로 활용할 수 있다. / error 처리할 수 있음) -> 데이터를 받아서 json방식으로 parsing한다.
			//	.then(data=>{처리로직}) // success 함수 
			
			// get방식으로 보낼 경우 method설정을 생략하고 바로 then절을 사용할 수 있다.
			/* fetch("${pageContext.request.contextPath}/ajax/selectMemberAll")
				.then(response=>{
					console.log(response); // response 객체가 반환되므로 response body에 포함된 속성들을 제어할 수 있다.
					if(!response.ok) throw new Error("요청 실패"); // 새로운 에러를 exception을 발생시킬 수 있다.
					return response.json(); // return값으로 다음 then(success 구문에 해당하는)에서  실행한다. 
					// controller의 responsebody에서 전달한 객체를 json으로 parsing해서 return한다
				}).then(data=>{
					console.log(data);
				}).catch(e=>{
					alert(e);
			}); */
			
			
			/* fetch("${pageContext.request.contextPath}/ajax/selectMemberAll",{
				method:"get" // post 방식
				// headers:{} contentType설정 또는 header에 보낼 값을 정할 수 있다.
				// body : {} JSON.stringify(객체) 전달할 객체를 JSON형식으로 변환해서 전송할 수 있다. 
			}).then(response=>{
				console.log(response); // response 객체가 반환되므로 response body에 포함된 속성들을 제어할 수 있다.
				if(!response.ok) throw new Error("요청 실패"); // 새로운 에러를 exception을 발생시킬 수 있다.
				return response.json(); // return값으로 다음 then(success 구문에 해당하는)에서  실행한다. 
				// controller의 responsebody에서 전달한 객체를 json으로 parsing해서 return한다
			}).then(data=>{
				console.log(data);
			}).catch(e=>{
				alert(e);
			}); */
			
			fetch("${pageContext.request.contextPath}/ajax/insertData.do",{
				method:"post",
				headers:{
					"Content-type" : "application/json"
				}, body : JSON.stringify(data) 
			}).then(response=>{
				if(!response.ok) new Error("입력실패");
				// return response.json(); 서버가 json으로 응답했을 때 
				// 서버가 일반 문자를 반환했을 때 ? text()메소드 사용 / response.text() 
						
			}).then(data=>{
				console.log(data)
			}).catch(e=>{
				// 다른 페이지로 전환하는 등 exception 발생했을 때 처리 가능
			});
			
			
			
		}
		
		// 기본 방식으로 전송
		const basicAjax=()=>{
			$.get('${pageContext.request.contextPath}/ajax/basicTest.do',(data)=>{
				console.log(data);
				$("#ajaxContainer").html("<h2>"+data+"</h2>");
			})
		}
		// json으로 받아오기
		const convertAjax=()=>{
			$.get("${pageContext.request.contextPath}/ajax/converter",(data)=>{
				console.log(data);
			})
		}
		
		// ajax로 jsp 가져오기
		const basic2=()=>{
			$.get("${pageContext.request.contextPath}/ajax/basic2",data=>{
				console.log(data);
				$("#ajaxContainer").html(data);
			})
		}


		// 전체 회원 가져오기
		const selectMemberAll=()=>{
			$.get("${pageContext.request.contextPath}/ajax/selectMemberAll",data=>{
				console.log(data);
				console.log(data[0]);
				console.log(typeof data[0].enrolldate);
				date = new Date(data[0].enrolldate);
				console.log(date);
				const tbl= $("<table class='table'>").css("width","100%");
				//tbl.html("<tr><th>아이디</th><th>이름</th><th>성별</th><th>나이</th><th>이메일</th><th>전화번호</th><th>주소</th><th>취미</th></tr>");
				// 헤더에 들어갈 내용을 배열로 만들어서 뿌릴 수 있다.
				const header=["아이디","이름","성별","나이","이메일","전화번호","주소","취미","가입일"];
				const headerTr= $("<tr>");
				header.forEach(e=>{
					const th=$("<th>").text(e);
					headerTr.append(th);
				})
				tbl.append(headerTr);
				
				data.forEach(e=>{
					const tr=$("<tr>");
					tr.append($("<td>").text(e.userId));
					tr.append($("<td>").text(e.userName));
					tr.append($("<td>").text(e.gender));
					tr.append($("<td>").text(e.age));
					tr.append($("<td>").text(e.email));
					tr.append($("<td>").text(e.phone));
					tr.append($("<td>").text(e.address));
					tr.append($("<td>").text(e.hobby));
					//const hobby = $("<td>").html(e.hobby.toString());
					//toLocaleDateString("로케일") 메소드를 사용하면 new Date 한 객체를 쉽게 파싱할 수 있다.
					tr.append($("<td>").text(new Date(e.enrolldate).toLocaleDateString("ko-KO")));
					tbl.append(tr);
				})
				$("#ajaxContainer").html(tbl);
			})
		}
		
		
		
		
		
		
		
		
	</script>
</section>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>



















