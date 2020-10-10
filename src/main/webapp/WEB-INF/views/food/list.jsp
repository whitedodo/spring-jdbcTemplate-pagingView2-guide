<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>음식 목록표</title>
<style>

	.board_tbl{
		border-top:1px solid #666;
		width:700px;
		
		font-family:Arial;
		font-size:15px;
		margin:auto;
		text-align:center;
		
	}
	
	.board_tbl td{
		border-right:1px solid #e6e6e6;
		border-bottom:1px solid #333;
		height:20px;
		text-align:center;
	}
	
	.board_tbl th{
		background-color:#e6e6e6;
		border-bottom:1px solid #e2e2e2;
	}
	
	.board_title{
		font-size:20px;
		text-align:center;
	}
	
</style>
</head>
<body>

<!-- 상단 -->
<h3 class="board_title">음식 목록표</h3>
<hr/>

<table class="board_tbl">
	<tr>
		<th style="width:10%;">
			번호
		</th>
		<th style="width:35%;">
			상품명
		</th>
		<th style="width:10%;">
			가격
		</th>
		<th style="width:10%;">
			상점명
		</th>
		<th style="width:10%;">
			조회수
		</th>
		<th style="width:25%;">
			등록일자
		</th>
	</tr>
	<c:forEach items="${foodMenuList}" var="board">
		<tr>
			<td>${board.id }</td>
			<td align="left">
				<a href="view.do?id=${board.id}">${board.name }</a>
			</td>
			<td><fmt:formatNumber value="${board.price}" /></td>
			<td>${board.storename }</td>
			<td>${board.cnt }</td>
			<td style="border-right:0px">
				<fmt:formatDate value="${board.regidate}" pattern="yyyy-MM-DD hh:mm:ss"/>
			</td>
		</tr>
	</c:forEach>
</table>

<!-- 페이징 -->
<div style="text-align:center">
<jsp:include page="/WEB-INF/views/pager/paging.jsp">
	<jsp:param name="customURL" value="${pagingUrl}" />
    <jsp:param name="firstPageNo" value="${paging.firstPageNo}" />
    <jsp:param name="prevPageNo" value="${paging.prevPageNo}" />
    <jsp:param name="startPageNo" value="${paging.startPageNo}" />
    <jsp:param name="pageNo" value="${paging.pageNo}" />
    <jsp:param name="endPageNo" value="${paging.endPageNo}" />
    <jsp:param name="nextPageNo" value="${paging.nextPageNo}" />
    <jsp:param name="finalPageNo" value="${paging.finalPageNo}" />
</jsp:include>
</div>

</body>
</html>