<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath }"/>
<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param name="title" value="게시판 상세"/>
</jsp:include>
<section id="board-container" class="container">
	 <div id="board-container">
        <input type="text" class="form-control" placeholder="제목" name="boardTitle" id="boardTitle"  required value="${boards.boardTitle }">
       	<input type="text" class="form-control" name="boardWriter"  readonly required value="${boards.boardWriter.userId }">
        <textarea class="form-control" name="boardContent" placeholder="내용" required>${boards.boardContent }</textarea>
        <c:if test="${not empty boards.file }">
	        <c:forEach var="f" items="${boards.file }">
	        	<button type="button" class="btn btn-outline-success btn-block" onclick="fn_fileDownload('${f.originalFilename}','${f.renamedFilename }');">${f.originalFilename }</button>
	        </c:forEach>
        </c:if>
    </div>
</section>
<script>
	function fn_fileDownload(oriName,reName){
		location.assign('${path}/board/fileDownload?oriName='+oriName+'&reName='+reName);
	}
</script>
     <style>
    div#board-container{ width:400px; margin:0 auto; text-align:center;}
    div#board-container input,div#board-container button{margin-bottom:15px;}
    div#board-container label.custom-file-label{text-align:left;}
    </style>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>