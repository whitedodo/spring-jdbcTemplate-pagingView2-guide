<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${foodMenu.name} - 세부 항목</title>
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
	
	.foot_tbl{
		width:700px;
		text-align:center;
		margin:auto;
	}
	
	h1{
		text-align:center;
	}
	
</style>
</head>
<body>

<!-- 상단 -->
<a href="logout.do">Log-out</a>
<h1>글 상세</h1>
<hr />

<!-- 본문 -->
<form action="update.do" method="post">

	<input name="id" type="hidden" value="${foodMenu.id}" />
	
	<table class="board_tbl">
		<tr>
			<td bgcolor="orange" width="70">식당메뉴</td>
			<td align="left"><input name="title" type="text"
				value="${foodMenu.name}" /></td>
		</tr>
		<tr>
			<td bgcolor="orange">가격</td>
			<td align="left">${foodMenu.price}</td>
		</tr>
		<tr>
			<td bgcolor="orange">가게명</td>
			<td align="left">
				<textarea name="content" cols="40" rows="10">${foodMenu.storename }</textarea>
			</td>
		</tr>
		<tr>
			<td bgcolor="orange">등록일</td>
			<td align="left">${foodMenu.regidate }</td>
		</tr>
		<tr>
			<td bgcolor="orange">조회수</td>
			<td align="left">${foodMenu.cnt }</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<input type="submit" value="글 수정" />
			</td>
		</tr>
	</table>
</form>

<hr>

<!-- 하단 메뉴 -->
<table class="foot_tbl">
	<tr>
		<td>
			<a href="insert.jsp">메뉴 등록</a>
		</td>
		<td>
			<a href="delete.do?id=${foodMenu.id }">메뉴 삭제</a>		
		</td>
		<td>
			<a href="list.do">글목록</a>		
		</td>
	</tr>
</table>

</body>
</html>