<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- <%@include file="/views/common/header.jsp" %> --%>
<!-- jsp 액션태그 이용해서 헤더 불러오기 -->
<%request.setCharacterEncoding("utf-8"); %>
<jsp:include page="/views/common/header.jsp">
	<jsp:param name="title" value="main view"/>
</jsp:include>
<section>
	<h3>본문 내용</h3>
	<%-- <p>접속한 회원 : <%=userId %></p> --%>
</section>
<jsp:forward page="/views/board.jsp"/>
<!-- 서블릿에서 forward 하기 때문에 잘 사용하지 않는다. -->
</body>
</html>