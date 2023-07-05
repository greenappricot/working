<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.web.member.model.vo.Member" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%
	//Member loginMember=(Member)session.getAttribute("loginMember");
	/* Cookie[] cookies=request.getCookies();
	String saveId=null;
	if(cookies!=null){
		for(Cookie c : cookies){
			if(c.getName().equals("saveId")){
				saveId=c.getValue();
				break;
			}
		}
	} */
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>HelloMVC</title>
<c:set var="path" value="${pageContext.request.contextPath }"/>
<link rel="stylesheet" type="text/css" href="${path }/css/style.css"/>
<script src="${path }/js/jquery-3.7.0.min.js"></script>
</head>
<body>
	<div id="container">
		<header>
			<h1>HelloMVC</h1>
			<div class="login-container">
			<c:if test="${empty cookie.saveId.value || empty loginMember}">
				<form id="loginFrm" action="${path}/login.do"	method="post" onsubmit="return fn_validation();">
					<table>
						<tr>
							<td>
								<input type="text" name="userId" id="userId" placeholder="아이디" value="${not empty cookie.saveId.value?cookie.saveId.value:''}">
							</td>
						</tr>
						<tr>
							<td>
								<input type="password" name="password" id="password" placeholder="패스워드">
							</td>
							<td>
								<input type="submit" value="로그인">
							</td>
						</tr>
						<tr>
							<td>
								<input type="checkbox" name="saveId" id="saveId" ${not empty cookie.saveId.value?'checked':''}>
								<label for="saveId">아이디저장</label>
								<input type="button" value="회원가입" onclick="location.assign('${path}/member/enrollMember.do')">
							</td>
						</tr>
					</table>
				</form>
				</c:if>
				<c:if test="${not empty loginMember}">
					<table id="logged-in">
						<tr>
							<td colspan="2">
								${loginMember.userId}님, 환영합니다. :)
							</td>
						</tr>
						<tr>
							<td>
								<input type="button" onclick="location.assign('${path}/member/memberView.do?userId=${loginMember.userId}')" value="내 정보보기">
							</td>
							<td>
								<input type="button" value="로그아웃"
								onclick="location.replace('${path}/logout.do')">
							</td>
						</tr>
					</table>
				</c:if>
			</div>
			<nav>
				<ul class="main-nav">
					<li class="home"><a href="${path}">Home</a></li>
					<li id="notice">
						<a href="${path}/notice/noticeList.do">공지사항</a>
					</li>
					<li id="board"><a href="${path}/board/boardList.do">게시판</a></li>
					<c:if test="${not empty loginMember and loginMember.userId=='admin' }">
						<li id="memberManage"><a href="${path}/admin/memberList.do">회원관리</a></li>
					</c:if>
				</ul>
			</nav>
		</header>
		<script>
			const fn_validation=()=>{
				const userId=$("#userId").val();
				if(userId.length<4){
					alert('아이디는 4글자이상 입니다.');
					$("#userId").val("");
					$("#userId").focus();
					return false;
				}
			}
		</script>
		
		
		
		
		
		