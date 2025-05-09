<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create Cookie</title>
</head>
<%
	Cookie cookie = new Cookie("id", "Watermelon");
	response.addCookie(cookie);							// 내가 보내는 응답에 쿠키를 싫어서 보내는 기능
	cookie = new Cookie("id2", "Strawberry");
	response.addCookie(cookie);
%>
<body>
	<h1>Cookie Creation</h1>
	<hr>
	<a href="10_get_cookie.jsp">Send Cookie</a>
</body>
</html>