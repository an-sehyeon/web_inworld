<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Delete Cookie</title>
</head>
<%
	String cookie_check = request.getHeader("Cookie");
	System.out.println("Cookie Check Result : " + cookie_check);
	
	// 모든 쿠키정보를 삭제
	if(cookie_check != null){						
		System.out.println("[GetCoolie] Cookie(s) found");
		Cookie[] cookies = request.getCookies();
		for(Cookie cookie : cookies){
			cookie.setMaxAge(0);
			response.addCookie(cookie);
		}
		
	}
%>
<body>
	<h1>Delete All Cookies OK</h1>
</body>
</html>