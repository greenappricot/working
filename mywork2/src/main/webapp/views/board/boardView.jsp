<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/views/common/header.jsp"%>
<%@ page import="com.web.board.model.vo.Board, java.util.List, com.web.board.model.vo.BoardComment" %>
<%
	Board b=(Board)request.getAttribute("board");
	List<BoardComment> comments=(List)request.getAttribute("comment");
%>
<style>
    section#board-container{width:600px; margin:2% auto; text-align:center;}
    section#board-container h2{margin:10px 0;}
    table#tbl-board{width:500px; margin:0 auto; border:1px solid black; border-collapse:collapse; clear:both; }
    table#tbl-board th {width: 125px; border:1px solid; padding: 5px 0; text-align:center;} 
    table#tbl-board td {border:1px solid; padding: 5px 0 5px 10px; text-align:left;}
    div#comment-container{margin:2% auto;}
    div#comment-container button#btn-insert{width:60px;height:50px; color:white;
    background-color:#B89B88;position:relative;top:-10px;}
        /*댓글테이블*/
    table#tbl-comment{width:580px; margin:0 auto; border-collapse:collapse; clear:both; } 
    table#tbl-comment tr td{border-bottom:1px solid; border-top:1px solid; padding:5px; text-align:left; line-height:120%;}
    table#tbl-comment tr td:first-of-type{padding: 5px 5px 5px 50px;}
    table#tbl-comment tr td:last-of-type {text-align:right; width: 100px;}
    table#tbl-comment button.btn-reply{display:none;}
    table#tbl-comment button.btn-delete{display:none;}
    table#tbl-comment tr:hover {background:lightgray;}
    table#tbl-comment tr:hover button.btn-reply{display:inline;}
    table#tbl-comment tr:hover button.btn-delete{display:inline;}
    table#tbl-comment tr.level2 {color:gray; font-size: 14px;}
    table#tbl-comment sub.comment-writer {color:navy; font-size:14px}
    table#tbl-comment sub.comment-date {color:tomato; font-size:10px}
    table#tbl-comment tr.level2 td:first-of-type{padding-left:100px;}
    table#tbl-comment tr.level2 sub.comment-writer {color:#8e8eff; font-size:14px}
    table#tbl-comment tr.level2 sub.comment-date {color:#ff9c8a; font-size:10px}
    /*답글관련*/
    table#tbl-comment textarea{margin: 4px 0 0 0;}
    table#tbl-comment button.btn-insert2{width:60px; height:23px; color:white; background:#3300ff; position:relative; top:-5px; left:10px;}
</style>
<section id="board-container">
		<h2>게시판</h2>
		<table id="tbl-board">
			<tr>
				<th>글번호</th>
				<td><%=b.getBoardNo()%></td>
			</tr>
			<tr>
				<th>제 목</th>
				<td><%=b.getBoardTitle() %></td>
			</tr>
			<tr>
				<th>작성자</th>
				<td><%=b.getBoardWriter() %></td>
			</tr>
			<tr>
				<th>조회수</th>
				<td><%=b.getBoardReadCount() %></td>
			</tr>
			<tr>
				<th>첨부파일</th>
				<td>
				<%if(b.getBoardOriginalFilename()!=null){ %>
				 	<a href="<%=request.getContextPath() %>/board/fileDownload.do?filename=<%=b.getBoardOriginalFilename()%>">
				 		<img src="<%=request.getContextPath()%>/images/file.png"><%=b.getBoardOriginalFilename()%>
				 	</a>
				 <%} %>
				</td>
			</tr>
			<tr>
				<th>내 용</th>
				<td><%=b.getBoardContent() %></td>
			</tr>
			<%--글작성자/관리자인경우 수정삭제 가능 --%>
			<%if(loginMember!=null&&
			(loginMember.getUserId().equals("admin")
			||loginMember.getUserId().equals(b.getBoardWriter()))) {%>
			<%-- <%if(loginMember!=null&&(loginMember.getUserId().equals("admin")||loginMember.getUserId().equals(b.getBoardWriter()))) {%> --%>
				<tr>
					<th colspan="2">
						<button name="update" onclick="<%=request.getContextPath()%>/board/updateBoard.do?boardNo=<%=b.getBoardNo()%>">수정하기</button>
						<button name="delete">삭제하기</button>
					</th>
				</tr>
			<%} %>
		</table> 
		<div id="comment-container">
			<div class="comment-editor">
				<form action="<%=request.getContextPath() %>/board/insertComment.do" method="post">
					<textarea name="boardCommentContent" cols="55" rows="2"></textarea>
					<input type="hidden" name="boardRef" value="<%=b.getBoardNo()%>">
					<input type="hidden" name="level" value="1">
					<input type="hidden" name="boardCommentWriter" value="<%=loginMember!=null?loginMember.getUserId():""%>"><!-- loginMember로 접근제한 필터처리 했기 때문에 이렇게 접근해야함  -->
					<input type="hidden" name="boardCommentRef" value="0"><!-- 번호 있으면 답글 없으면 댓글이기 때문에 내부적으로 아무 값을 넣는다. 나중에 null처리 -->
					<button type="submit" id="btn-insert">등록</button>
				</form>
			</div>
		</div>
		<table id="tbl-comment">
			<%if(comments!=null){
				for(BoardComment bc: comments){%>
					<tr class="level1">
						<td>
							<sub class="comment-writer"><%=bc.getBoardCommentWriter() %></sub>
							<sub class="comment-date"><%=bc.getBoardCommentDate() %></sub>
							<br>
							<%=bc.getBoardCommentContent() %>
						</td>
						<td>
							<button class="btn-reply" 
							onclick="location.assign('<%=request.getContextPath()%>/board/insertReplyComment.do?boardNo='+<%=b.getBoardNo()%>&boardCommentRef='+<%=bc.getBoardCommentRef()%>')">답글</button>
							<!-- 작성자일때만 노출 -->
							<button class="btn-reply" name="update">수정</button>
							<button class="btn-reply" name="delete">삭제</button>
						</td>
					</tr>
				<%}
			}%>
		</table>
</section>
<script>
	$("#comment-container textarea[name='boardCommentContent']").focus(e=>{
		// 로그인 확인 처리 -> 로그인 안 되어 있으면 alert("로그인 후 등록 가능합니다."), loginForm에 focus
		if(<%=loginMember==null%>){
			alert("로그인 후 등록 가능합니다.");
			$("#userId").focus();
		}
	});
</script>
<%@ include file="/views/common/footer.jsp"%>