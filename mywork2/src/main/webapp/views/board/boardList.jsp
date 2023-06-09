<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/views/common/header.jsp"%>
<%@ page import="java.util.List, com.web.board.model.vo.Board" %>
<%
	List<Board> boards= (List)request.getAttribute("board");
%>
<style>
	section#board-container{width:600px; margin:2% auto; text-align:center;}
	section#board-container h2{margin:10px 0;}
	table#tbl-board{width:100%; margin:0 auto; border:1px solid black; border-collapse:collapse; clear:both; }
	table#tbl-board th, table#tbl-board td {border:1px solid; padding: 5px 0; text-align:center;} 
	/*글쓰기버튼*/
	input#btn-add{float:right; margin: 0 0 15px;}
	/*페이지바*/
	div#pageBar{margin-top:10px; text-align:center;}
	div#pageBar a,div#pageBar span {
		padding: 0 10px;
	}
	div#pageBar span{font-weight:bold;}
	* a{color:black; text-decoration:none;}
	section#board-container>div:first-of-type{
    	display:flex;
    	justify-content:end;
    	margin: 10px auto;
    }
	</style>
	<section id="board-container">
		<h2>게시판 </h2>
		<div>
			<%if(loginMember!=null){ %>
				<button onclick="location.assign('<%=request.getContextPath()%>/board/insertBoard.do')">글쓰기</button>
				<%-- <button onclick="fn_insertBoard('<%=loginMember.getUserId()%>')">글쓰기</button> --%>
			<%} %>
		</div>
		<table id="tbl-board">
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
				<th>첨부파일</th>
				<th>조회수</th>
			</tr>
			<%if(boards!=null){ 
				for(Board b:boards){%>
					<tr>
						<td><%=b.getBoardNo() %></td>
						<td>
							<a href="<%=request.getContextPath()%>/board/boardView.do?boardNo=<%=b.getBoardNo()%>"><%=b.getBoardTitle() %></a>
						</td>
						<td><%=b.getBoardWriter() %></td>
						<td><%=b.getBoardDate() %></td>
						<td>
							<%if(b.getBoardOriginalFilename()!=null) {%>
								<img src="<%=request.getContextPath()%>/images/file.png">
								<%=b.getBoardOriginalFilename() %>
							<%} %>
						</td>
						<td><%=b.getBoardReadCount() %></td>
					</tr>
				<%}
			}%>
		</table>

		<div id="pageBar">
			<%=request.getAttribute("pageBar") %>
		</div>
	<script>
		const fn_insertBoard=(id)=>{
			if(id!=null) {
				location.assign('<%=request.getContextPath()%>/board/insertBoard.do');
			}else{
				alert("로그인 후 이용할 수 있습니다.");
			}
		}
	</script>
</section>
<%@ include file="/views/common/footer.jsp"%>