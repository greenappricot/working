<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.List,com.web.board.model.vo.Board" %>
<c:set var="path" value="${pageContext.request.contextPath }"/>  
<%-- <%
	List<Board> boards=(List<Board>)request.getAttribute("boards");
%>   --%>  
<%@ include file="/views/common/header.jsp"%>
<style>
	section#board-container{width:600px; margin:0 auto; text-align:center;}
	section#board-container h2{margin:10px 0;}
	table#tbl-board{width:100%; margin:0 auto; border:1px solid black; border-collapse:collapse; clear:both; }
	table#tbl-board th, table#tbl-board td {border:1px solid; padding: 5px 0; text-align:center;} 
	/*글쓰기버튼*/
	input#btn-add{float:right; margin: 0 0 15px;}
	/*페이지바*/
	div#pageBar{margin-top:10px; text-align:center; background-color:rgba(0, 188, 212, 0.3);}
	div#pageBar span.cPage{color: #0066ff;}
</style>
	<section id="board-container">
		<h2>게시판 </h2>
		<table id="tbl-board">
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
				<th>첨부파일</th>
				<th>조회수</th>
			</tr>
			<c:if test="${empty boards }">
				<tr>
					<td colspan="6">조회된 데이터가 없습니다</td>
				</tr>
			</c:if>
			<c:if test="${not empty boards }">
				<c:forEach items="${boards }" var="b">
					<tr>
						<td>${b.boardNo}</td>
						<td>
							<a href="${path }/board/boardView.do?no=${b.boardNo}">
								${b.boardTitle }
							</a>
						</td>
						<td>${b.boardWriter}</td>
						<td>${b.boardDate}</td>
						<td>
							<c:if test="${not empty b.boardOriginalFilename }">
								<img src="${path}/images/file.png"
								width="20">
							</c:if>
						</td>
						<td>${b.boardReadCount}</td>
					</tr>
				</c:forEach>
			</c:if>
		</table>

		<div id="pageBar">
			${pageBar}
		</div>
	</section>
<%@ include file="/views/common/footer.jsp"%>