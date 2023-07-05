<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="path" value="${pageContext.request.contextPath }"/>
<%@ include file="/views/common/header.jsp"%>
<section id=enroll-container>
		<h2>회원 정보 수정</h2>
		<form id="memberFrm" method="post" >
			<table>
				<tr>
					<th>아이디</th>
					<td>
						<input type="text" name="userId" id="userId_" value="${infoMember.userId}" readonly>
					</td>
				</tr>
				<!-- <tr>
					<th>패스워드</th>
					<td>
						<input type="password" name="password" id="password_">
					</td>
				</tr>
				<tr>
					<th>패스워드확인</th>
					<td>	
						<input type="password" id="password_2"><br>
					</td>
				</tr>   -->
				<tr>
					<th>이름</th>
					<td>	
					<input type="text"  name="userName" id="userName"  value="${infoMember.userName}" required><br>
					</td>
				</tr>
				<tr>
					<th>나이</th>
					<td>	
					<input type="number" name="age" id="age" value="${infoMember.age}"><br>
					</td>
				</tr> 
				<tr>
					<th>이메일</th>
					<td>	
						<input type="email" placeholder="abc@xyz.com" name="email" id="email" value="${infoMember.email}"><br>
					</td>
				</tr>
				<tr>
					<th>휴대폰</th>
					<td>	
						<input type="tel" placeholder="(-없이)01012345678" name="phone" id="phone" maxlength="11" value="${infomember.phone}"><br>
					</td>
				</tr>
				<tr>
					<th>주소</th>
					<td>	
						<input type="text" placeholder="" name="address" id="address" value="${infoMember.address}"><br>
					</td>
				</tr>
				<tr>
					<th>성별 </th>
					<td>
						<input type="radio" name="gender" id="gender0" value="M"  ${String.valueOf(infoMember.gender)=="M"?"checked":""}>
						<label for="gender0">남</label>
						<input type="radio" name="gender" id="gender1" value="F" ${String.valueOf(infoMember.gender)=="F"?"checked":""}>
						<label for="gender1">여</label>				
					</td>
				</tr>
				<%-- <tr>
					<th>취미 </th>
					<td>
						<input type="checkbox" name="hobby" id="hobby0" value="운동" ${infoMember.hobby.contains("운동")?'checked':''}><label for="hobby0">운동</label>
						<input type="checkbox" name="hobby" id="hobby1" value="등산" ${infoMember.hobby.contains("등산")?'checked':''}><label for="hobby1">등산</label>
						<input type="checkbox" name="hobby" id="hobby2" value="독서" ${infoMember.hobby.contains("독서")?'checked':''}><label for="hobby2">독서</label><br />
						<input type="checkbox" name="hobby" id="hobby3" value="게임" ${infoMember.hobby.contains("게임")?'checked':''}><label for="hobby3">게임</label>
						<input type="checkbox" name="hobby" id="hobby4" value="여행" ${infoMember.hobby.contains("여행")?'checked':''}><label for="hobby4">여행</label><br />
						

					</td>
				</tr> --%>
			</table>
			<input type="button" onclick="fn_updatePassword();" value="비밀번호수정"/>
			<input type="button" onclick="fn_updateMember();" value="정보수정"/>
			<input type="button" value="탈퇴"/>
		</form>
	</section>
	<script>
		const fn_updatePassword=()=>{
			//새창으로 패스워드 변경하자.
			open(${path}+"/member/passwordUpdate.do?userId="+${loginMember.userId},
					"_blank","width=400,height=210");
		}
		const fn_updateMember=()=>{
			//form전송하기
			$("#memberFrm").attr("action",${path}+"/member/updateEndMemeber.do").submit();
		}
	</script>
	
	

<%@ include file="/views/common/footer.jsp"%>