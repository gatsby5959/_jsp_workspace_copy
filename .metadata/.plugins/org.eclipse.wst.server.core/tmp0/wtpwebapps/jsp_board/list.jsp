<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Board List Page</h1>
<table border="1">
	<tr>
		<th>BNO</th>
		<th>TITLE</th>
		<th>WRITER</th>
		<th>REG_DATE</th>
	</tr>
	
	<!-- DB의 전체 리스트를 반복 c:foreach     1-->
	
	<c:forEach items="${list }" var="bvo">
		<tr>
			<td><a href="/brd/detail?bno=${bvo.bno}">${bvo.bno}</a></td><!-- 화면 이동은 / 을 쓴다 쩜 안쓴다 -->
			<td><a href="/brd/detail?bno=${bvo.bno}">${bvo.title}</a></td>
			<td>${bvo.writer}</td>
			<td>${bvo.regdate}</td>
		</tr>
	</c:forEach>	
</table>
<a href="/index.jsp"><button type="button">index</button></a>
<a href="/brd/register"><button>register</button></a>
</body>
</html>