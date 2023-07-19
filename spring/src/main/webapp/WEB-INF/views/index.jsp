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