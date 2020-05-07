<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.ycjung.paging.*" %>
<%@ page import="com.ycjung.dto.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.lang.*" %>

<%
    PagingVo pv = (PagingVo)request.getAttribute("pv");
	String requestCurrCheck = request.getParameter("currentPage");

	int currentPage = pv.getCurrentPage();
	if(requestCurrCheck != null && !requestCurrCheck.isEmpty()){
	    currentPage = Integer.parseInt(request.getParameter("currentPage"));
	}
	int beginPage = 1;
	int endPage = pv.getDisplayPage();
	while(currentPage > endPage){
	    endPage += pv.getDisplayPage();
	    beginPage = endPage - (pv.getDisplayPage()-1);
	}
	
	if(endPage > pv.getTotalPage()){
	    if(pv.getTotalPage() == 0){
	        endPage = 1;
	    }else{
	        endPage = pv.getTotalPage();
	    }
	}
    ArrayList<ContentDto> dtos = (ArrayList<ContentDto>)request.getAttribute("list");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
	<style type="text/css">
		table {
			width: 100%;
			border-top: 1px solid #444444;
			border-collapse: collapse;
		}
		
		th,td{
			border-bottom: 1px solid #444444;
			padding: 10px;
			text-align: center;
		}
		
		a:link { text-decoration: none; text-shadow: 0 0 24px; }
		a:visited { text-decoration: none; text-shadow: 0 0 24px; }
	</style>
</head>                      
<body>
	<table>
		<thead>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>날짜</th>
				<th>조회수</th>
			</tr>
		</thead>
		
		<tbody>
			<tr>
				<%
					int j = (currentPage - 1) * pv.getDisplayRow();
					int viewPageSize = j + pv.getDisplayRow();
					if(viewPageSize > dtos.size()){
					    viewPageSize = dtos.size();
					}
					
					for(;j<viewPageSize;j++){ // 한 페이지에 보여줄 만큼 j 증가.
					  %><tr>
					  	<td><%=dtos.get(j).getbCd() %></td>
					    <td>
					    	<U>
					    		<a href="content_view?bCd=<%=dtos.get(j).getbCd()%>">
					 		 	  <%=dtos.get(j).getbTitle() %></a>
					 		</U>
					 	</td>
					    <td><%=dtos.get(j).getbAuthor() %></td>
					    <td><%=dtos.get(j).getbDate() %></td>
					    <td><%=dtos.get(j).getbHits() %></td>
					    </tr>
					    <%
					}
				%>
			</tr>
			<tr>
				<div style="width:100px; height:50px; margin:10px; float:right">
					<a href="write_view"> 글쓰기</a>
				</div>
				<div style="width:100px; height:50px; margin:10px; float:right">
					<a href="deleteAll"> 모두삭제</a>
				</div>
				<div style="width:100px; height:50px; margin:10px; float:right">
					<a href="dummy"> 더미데이터</a>
				</div>
			</tr>
			

		</tbody>
	</table>
			<div style="width:100%; text-align: center; margin-top: 50px">
					<%
						if(beginPage > pv.getDisplayPage()){
						    %>
						    <a href="list?currentPage=<%=beginPage-1 %>"><td>《 이전</td></a>
						    <%
						}
						for(int i=beginPage;i<=endPage;i++){
						    %>
						    	<U><a href="list?currentPage=<%=i %>"><td><%=i %></td></a></U>
						    <% 
						}
						if(pv.getTotalPage() > endPage){
						    %>
						    <a href="list?currentPage=<%=endPage+1 %>"><td>다음 》</td></a>
						    <%
						}
					%>
			</div>

</body>
</html>