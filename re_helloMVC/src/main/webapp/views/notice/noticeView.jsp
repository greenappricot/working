<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath }"/>
<%@ page import="com.web.notice.model.vo.Notice" %>    
<%-- <%
	Notice n=(Notice)request.getAttribute("notice");
%>    --%> 
<%@ include file="/views/common/header.jsp"%>
<section id="notice-container">
		<h2>공지사항 상세화면</h2>
        <table id="tbl-notice">
        <tr>
            <th>제 목</th>
            <td>${notice.noticeTitle}</td>
        </tr>
        <tr>
            <th>작성자</th>
            <td>${notice.noticeWriter}</td>
        </tr>
        <tr>
            <th>첨부파일</th>
            <td>
            	<c:if test="${not empty notice.filePath }">
           		<div class="dowonload-container" onclick="fileDownload('${notice.filePath}');">	
           			<img src="${path}/images/file.png"
           			width="20"><span>${notice.filePath}</span>
           		</div>
           		</c:if>
            </td>
        </tr>
        <tr>
            <th>내 용</th>
            <td>${notice.noticeContent}</td>
        </tr>
        	<c:if test="${not empty loginMember &&(loginMember.userId=='admin' || loginMember.userId==notice.noticeWriter)}">
	        <tr>
	            <th colspan="2">
	                <input type="button" value="수정하기" onclick="">
	                <input type="button" value="삭제하기" onclick="">
	            </th>
	        </tr>
        </c:if>
    </table>
	<script>
		const fileDownload=(filename)=>{
			location.assign(${path}+"/fileDownload.do?name="+filename);
		}
	</script>
</section>

    <style>
    div.dowonload-container{cursor:pointer;}
    section#notice-container{width:600px; margin:0 auto; text-align:center;}
    section#notice-container h2{margin:10px 0;}
    table#tbl-notice{width:500px; margin:0 auto; border:1px solid black; border-collapse:collapse; clear:both; }
    table#tbl-notice th {width: 125px; border:1px solid; padding: 5px 0; text-align:center;} 
    table#tbl-notice td {border:1px solid; padding: 5px 0 5px 10px; text-align:left;}
    </style>
<%@ include file="/views/common/footer.jsp"%>