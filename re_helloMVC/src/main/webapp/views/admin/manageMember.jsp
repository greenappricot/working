<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List,java.io.*" %>    
<%-- <%
	List<Member> members=(List)request.getAttribute("members");	
	String type=request.getParameter("searchType");
	String keyword=request.getParameter("searchKeyword");
%>   --%>  
<c:set var="path" value="${pageContext.request.contextPath }"/>
<%@ include file="/views/common/header.jsp"%>
<style type="text/css">
    section#memberList-container {text-align:center;}
    
    section#memberList-container table#tbl-member {width:100%; border:1px solid gray; border-collapse:collapse;}
    section#memberList-container table#tbl-member th, table#tbl-member td {border:1px solid gray; padding:10px; }
    #pageBar a,#pageBar span{
    	text-decoration:none;
    	font-size:24px;
    	margin-left:2%;
    	margin-right:2;
    	color:magenta;
    }
    #pageBar a:hover{
    	background-color:lime;
    }
    #pageBar span{
    	color:gray;
    }
    div#search-container {margin:0 0 10px 0; padding:3px; 
    background-color: rgba(0, 188, 212, 0.3);}
    div#search-userId{display:inline-block;}
    div#search-userName{display:none;}
    div#search-gender{display:none;}
    div#numPerpage-container{float:right;}
    form#numperPageFrm{display:inline;}
</style>
<section id="memberList-container">
      <h2>회원관리</h2>
         <div id="search-container">
        	검색타입 : 
        	<select id="searchType">
        		<%-- <option value="userId" <%=type!=null&&type.equals("userId")?"selected":"" %>>아이디</option>
        		<option value="userName" <%=type!=null&&type.equals("userName")?"selected":"" %>>회원이름</option>
        		<option value="gender" <%=type!=null&&type.equals("gender")?"selected":"" %>>성별</option> --%>
        		<option value="userId" ${not empty type&&type==loginMember.userId?'selected':''}>아이디</option>
        		<option value="userName" ${not empty type&&type==loginMember.userName?'selected':''}>회원이름</option>
        		<option value="gender" ${not empty type&&type==loginMember.gender?'selected':''}>성별</option>
        	</select>
        	<div id="search-userId">
        		<form action="${path }/admin/searchMember">
        			<input type="hidden" name="searchType" value="userId" >
        			<input type="text" name="searchKeyword" size="25" placeholder="검색할 아이디를 입력하세요" value="${not empty type&& type==loginMember.userId?keyword:''}">
        			<button type="submit">검색</button>
        		</form>
        	</div>
        	<div id="search-userName">
        		<form action="${path }/admin/searchMember">
        			<input type="hidden" name="searchType" value="userName">
        			<input type="text" name="searchKeyword" size="25" placeholder="검색할 이름을 입력하세요" value="${not empty type&&type==loginMember.userName?keyword:''}">
        			<button type="submit">검색</button>
        		</form>
        	</div>
        	<div id="search-gender">
        		<form action="${path }/admin/searchMember">
        			<input type="hidden" name="searchType" value="gender">
        			<label><input type="radio" name="searchKeyword" value="M" 
        			${not empty type&&type==loginMember.gender?'checked':'' }>남</label>
        			<label><input type="radio" name="searchKeyword" value="F" 
        				${not empty type&&type==loginMember.gender&&keyword=='F'?'checked':'' }>여</label>
        			<button type="submit">검색</button>
        		</form>
        	</div>
        </div>
        <div id="numPerpage-container">
        	페이지당 회원수 : 
        		<select name="numPerpage" id="numPerpage">
        			<option value="10">10</option>
        			<option value="5" >5</option>
        			<option value="3" >3</option>
        		</select>
        	<!-- </form> -->
        </div>
      
      <table id="tbl-member">
          <thead>
              <tr>
                <th>아이디</th>
			    <th>이름</th>
			    <th>성별</th>
			    <th>나이</th>
			    <th>이메일</th>
			    <th>전화번호</th>
			    <th>주소</th>
			    <th>취미</th>
			    <th>가입날짜</th>
              </tr>
          </thead>
          <tbody>
          <c:if test="${empty members }">
          		<tr>
          			<td colspan="9">조회된 회원이 없습니다</td>
          		</tr>
          </c:if>
          <c:if test="${not empty members }">
          	<c:forEach items="${members }" var="m">
          	<tr>
          			<td>${m.userId}</td>
          			<td>${m.userName}</td>
          			<td>${m.gender}</td>
          			<td>${m.age}</td>
          			<td>${m.email}</td>
          			<td>${m.phone}</td>
          			<td>${m.address}</td>
          			<td>${m.hobby}</td>
          			<td>${m.enrollDate }</td>
          		</tr>
          	</c:forEach>	
          </c:if>
          </tbody>
      </table>
      <div id="pageBar">
      	${pageBar }
<%--       	<%=request.getAttribute("pageBar") %> --%>
      </div>	
 </section>
 <script>
	<%-- $("#numPerpage-container select").change(e=>{
		const pageNum=$(e.target).val();
		console.log(pageNum);
		let url=location.href;
			if(url.includes("?")){
 			url=url.substring(0,url.indexOf("?")+1)
 			+'searchType='+searchType
 			+'&searchKeyword='+keyword
 			+'&cPage=<%=request.getParameter("cPage")!=null
 				?request.getParameter("cPage"):1%>'
 			+'&numPerpage='+e.target.value;
			}else{
				url+='?';
				url+='cPage=<%=request.getParameter("cPage")!=null
				?request.getParameter("cPage"):1%>'
 			+'&numPerpage='+e.target.value;
			}
	}) --%>
	 console.log(l);
 	$("#searchType").change(e=>{
 		const type=$(e.target).val();
 		$(e.target).parent().find("div").css("display","none");
 		$("#search-"+type).css("display","inline-block");
 	});
 	var searchType=$("#searchType option:selected");
 	var keyword=$("#search-userId input[name:searchKeyword]").val();
 	()=>{
 		$("#searchType").change();
 		 $("#numPerpage-container select").change(e=>{
 			const pageNum=$(e.target).val();
 			let url=location.href;
 			if(url.includes("?")){
	 			url=url.substring(0,url.indexOf("?")+1)
	 			+'searchType='+searchType
	 			+'&searchKeyword='+keyword
	 			+'&cPage=<%=request.getParameter("cPage")!=null
	 				?request.getParameter("cPage"):1%>'
	 			+'&numPerpage='+pageNum;
 			}else{
 				url+='?';
 				url+='cPage=<%=request.getParameter("cPage")!=null
 				?request.getParameter("cPage"):1%>'
	 			+'&numPerpage='+pageNum;
 			}
 			//console.log(url);
 			//url+='&numPerpage='+e.target.value;
 			location.assign(url);
 		});
 	};
 	
 </script>
<%@ include file="/views/common/footer.jsp"%>