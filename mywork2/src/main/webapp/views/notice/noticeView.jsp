<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.web.notice.model.vo.Notice" %>    
<%
	Notice n=(Notice)request.getAttribute("notice");
%>    
<%@ include file="/views/common/header.jsp"%>
<section id="notice-container">
		<h2>공지사항 상세화면</h2>
        <table id="tbl-notice">
        <tr>
            <th>제 목</th>
            <td><%=n.getNoticeTitle() %></td>
        </tr>
        <tr>
            <th>작성자</th>
            <td><%=n.getNoticeWriter() %></td>
        </tr>
        <tr>
            <th>첨부파일</th>
            <td>
           		<%if(n.getFilePath()!=null){ %>
           			<div id="download-container" onclick="fileDownload('<%=n.getFilePath() %>');">
	           			<img src="<%=request.getContextPath()%>/images/file.png" width="20"><span><%=n.getFilePath() %></span>
           			</div>
           		<%} %>
            </td>
        </tr>
        <tr>
            <th>내 용</th>
            <td><%=n.getNoticeContent() %></td>
        </tr>
        <%if(loginMember!=null&&
        	(loginMember.getUserId().equals("admin")||
        	loginMember.getUserId().equals(n.getNoticeWriter()))){ %>
	        <tr>
	            <th colspan="2">
	                <input type="button" value="수정하기" onclick="">
	                <input type="button" value="삭제하기" onclick="">
	            </th>
	        </tr>
        <%} %>
    </table>
    <script>
    	const fileDownload=(filename)=>{
    		// alert("파일 다운로드");
    		// 다운로드 요청 보내기 -> file에 대한 정보를 location으로 보낸다. -> 매개변수로 파일명을 받는다.
			location.assign("<%=request.getContextPath()%>/fileDownload.do?name="+filename); 		
    	}
    </script>
</section>
<style>
    section#notice-container{width:600px; margin:3% auto; text-align:center;}
    section#notice-container h2{margin:10px 0;}
    table#tbl-notice{width:500px; margin:2% auto; border:1px solid black; border-collapse:collapse; clear:both; }
    table#tbl-notice th {width: 125px; border:1px solid; padding: 5px 0; text-align:center;} 
    table#tbl-notice td {border:1px solid; padding: 5px 0 5px 10px; text-align:left;}
    div#download-container {
    	cursor:pointer;
    }
</style>
<%@ include file="/views/common/footer.jsp"%>