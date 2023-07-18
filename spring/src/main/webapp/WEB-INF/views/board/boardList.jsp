<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath }"/>
<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param name="title" value="게시판"/>
</jsp:include>
<section id="board-container" class="container">
        <p>총 ${totalContents }건의 게시물이 있습니다.</p><!-- left join 해서 count 한 값이랑 실제 데이터의 count가 달라서 1. 페이징 처리하는 계산 문법 변경 2. 전체 데이터 수와 count를 따로 구하기 -->
        <button class="btn btn-outline-primary" onclick="location.assign('${path}/board/boardForm.do')">글쓰기</button>
        <table id="tbl-board" class="table table-striped table-hover">
            <tr>
                <th>번호</th>
                <th>제목</th>
                <th>작성자</th>
                <th>작성일</th>
                <th>첨부파일</th>
                <th>조회수</th>
            </tr>
            <c:if test="${not empty boards }">
            	<c:forEach items="${boards }" var="b">
            		<tr>
            			<td>${b.boardNo }</td>
            			<td><a href="${path }/board/selectBoardByNo.do?boardNo=${b.boardNo}">${b.boardTitle}</a></td>
            			<td>${b.boardWriter.userId}</td>
            			<td>${b.boardDate }</td>
            			<td>
            				<c:if test="${not empty b.file && b.file.get(0).attachmentNo!=0 }">
            					<img src="${path }/resources/images/file.png" alt="첨부파일사진"/>
            					<span>(${b.file.size()})</span>
            				</c:if>
            			</td>
            			<td>${b.boardReadCount }</td>
            		</tr>
            	</c:forEach>
            </c:if>
        </table> 
        <div class="pagination-container">
        	<c:out value="${pageBar}" escapeXml="false"/>
        </div>
        <script>
        	const fn_insertBoard=()=>{
        		location.assign('${path}/board/insertBoardPage.do');
        	}
        </script>
</section>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>