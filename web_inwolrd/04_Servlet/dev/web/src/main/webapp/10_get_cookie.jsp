<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Display & Modify Cookie</title>
</head>
<%
	String id = "";
	String cookie_check = request.getHeader("Cookie");
	System.out.println("Cookie Check Result : " + cookie_check);
	
	if(cookie_check != null){						// header에 쿠키가 담겨있다면?
		System.out.println("[GetCoolie] Cookie(s) found");
		Cookie[] cookies = request.getCookies();
		for(Cookie cookie : cookies){
			System.out.println("[GetCookie] " + cookie.getName() + ":" + cookie.getValue());
			if(cookie.getName().equals("id")){
				id = cookie.getValue();
				break;									// id를 발견하면 for문 중단
			}
		}
	
		
	}
%>
<body>
	<h1>Get Cookie</h1>
	<hr>
	<h1>User ID : <%=id %></h1>
	<a href="10_delete_cookie.jsp">Delete Cookie</a>
</body>
</html>