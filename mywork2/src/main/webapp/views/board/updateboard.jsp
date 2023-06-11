<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/views/common/header.jsp"%>
<%@ page import="java.util.List,com.web.board.model.vo.Board" %>
<%
	Board b=(Board)request.getAttribute("board");
%>
<style>
	section#board-container
	{
		width:600px;
		margin:2% auto;
		text-align:center;
	}
	section#board-container h2
	{
		margin:10px 0;
		text-align:center;
	}
	table#tbl-board
	{
		width:500px;
		margin:2% auto;
		border:1px solid black;
		border-collapse:collapse;
	}
	table#tbl-board th
	{
		width:125px;
		border:1px solid;
		padding:5px 0;
		text-align:center;
	}
	table#tbl-board td
	{
		border:1px solid;
		padding:5px 0 5px 10px;
		text-align:left;
	}
	table#tbl-board td input, textarea{
		border: none;
	}
</style>
<script>
</script>
<section id='board-container'>
	<h2>게시판 수정</h2>
	<form action='' >
		<table id='tbl-board'>
			<%if(b!=null){ %>
				<tr>
					<th>제목</th>
					<td><input type="text" name="boardTitle" value="<%=b.getBoardTitle()%>" size="55"></td>
				</tr>
				<tr>
					<th>작성자</th>
					<td><input type="text" name="boardWriter" value="<%=b.getBoardWriter()%>"></td>
				</tr>
				<tr>
					<th>첨부파일</th>
					<td><input type="text" name="file" value="<%=b.getBoardOriginalFilename()%>"></td>
				</tr>
				<tr>
					<th>내용</th>
					<td><textarea cols="40" rows="5" name="boardContent" value="<%=b.getBoardContent()%>"></textarea></td>
				</tr>
				<tr>
					<th colspan="2">
						<button name="submit">수정 하기</button>
					</th>
				</tr>
			<%} %>
		</table>
	</form>
</section>
<%@ include file="/views/common/footer.jsp"%>