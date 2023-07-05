<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath }"/>
<%@ include file="/views/common/header.jsp" %>
<section id="content">
	<h2 align="center" style="margin-top:200px">
		안녕하세요,MVC입니다.
	</h2>
	<div id="mbAllC">
		<button id="memberAll">전체 회원 조회</button>
		<br> <input type="text" id="id">
		<button>아이디 조회</button>
	</div>
	<div id="memberList"></div>
	<style>
		#mbAllC {
			width: 100%;
			text-align: center;
		}
		
		#memberAll, #id {
			margin: 10px auto;
		}
		
		#id {
			margin-right: 5px;
		}
		
		div#memberList>table {
			width: 100%;
			margin: 0 auto;
			border: 1px solid black;
			border-collapse: collapse;
			clear: both;
		}
		
		div#memberList>table th, div#memberList>table td {
			border: 1px solid;
			padding: 5px 0;
			text-align: center;
		}
</style>
</section>
<script>
	
		// 아이디로 회원 조회
		$("#id").next().click(e=>{
			const id=$("#id").val();
			if(id!=""){
				$.ajax({
					url:${path}+"/ajax/searchMember.do",
					data:{"userId":id},
					success:data=>{
						//console.log(data);
						const $table=$("<table>");
						const $header="<tr><th>아이디</th><th>이름</th><th>성별</th><th>나이</th><th>주소</th><th>이메일</th><th>전화번호</th><th>취미</th><th>가입일</th></tr>";
						$table.html($header);
						for(let i=0;i<data.length;i++){
							const $tr=$("<tr>");
							const member=data[i];
							console.log(member);
								const $id=$("<td>").text(member.userId);
								const $name=$("<td>").text(member.userName);
								const $gender=$("<td>").text(member.gender);
								const $age=$("<td>").text(member.age);
								const $add=$("<td>").text(member.address);
								const $email=$("<td>").text(member.email);
								const $phone=$("<td>").text(member.phone);
								const $hobby=$("<td>").text(member.hobby);
								const $date=$("<td>").text(member.enrollDate);
								$tr.append($id).append($name).append($gender).append($age).append($add).append($email).append($phone).append($hobby).append($date); 
								$table.append($tr);
						}
						$("#memberList").append($table);
					}
				})
			}else {
				alert("없음")
			}
		})
	
		// 전체 회원 조회
		$("#memberAll").click(e=>{
			$.get(${path}+"/ajax/memberAll.do",data=>{
				//console.log(data);
				const $table=$("<table>");
				const $header="<tr><th>아이디</th><th>이름</th><th>성별</th><th>나이</th><th>주소</th><th>이메일</th><th>전화번호</th><th>취미</th><th>가입일</th></tr>";
				$table.html($header);
				console.log(data.length);
				for(let i=0;i<data.length;i++){
					const $tr=$("<tr>");
					//console.log(e);
					//console.log(data[i]);
					const member=data[i];
					//console.log(data[i].userId);
						const $id=$("<td>").text(member.userId);
						const $name=$("<td>").text(member.userName);
						const $gender=$("<td>").text(member.gender);
						const $age=$("<td>").text(member.age);
						const $add=$("<td>").text(member.address);
						const $email=$("<td>").text(member.email);
						const $phone=$("<td>").text(member.phone);
						const $hobby=$("<td>").text(member.hobby);
						const $date=$("<td>").text(member.enrollDate);
						$tr.append($id).append($name).append($gender).append($age).append($add).append($email).append($phone).append($hobby).append($date); 
						$table.append($tr);
				}
				
				$("#memberList").append($table);
			})
		});

	</script>
<%@ include file="/views/common/footer.jsp" %>		
