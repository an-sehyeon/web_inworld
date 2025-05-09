<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ajax Response</title>
</head>
<body>
<%
	String city = request.getParameter("city");
	String zipcd = request.getParameter("zipcd");
	
	// 3초를 쉰 다음 코드 실행
	try {
		Thread.sleep(3000);
	}
	catch(Exception e){ e.printStackTrace(); }
	
	out.println("자네가 전달한 위대한 도시는 바로 " + city + "로군...");
	out.println("<br>");
	out.println("보물은 우편번호 " + zipcd + "에 묻혀있다네...");
%>
</body>
</html>