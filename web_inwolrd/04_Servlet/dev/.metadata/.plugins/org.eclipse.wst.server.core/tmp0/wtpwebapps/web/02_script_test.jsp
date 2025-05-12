<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome</title>
</head>
<body>
	<h1>Scripting Tag</h1>
	<hr>
	
	<%!
		public int myMethod(int count){
		return ++count;
	}
	%>
	<%
		int count = 5;
		out.println(myMethod(0));
		out.println(count);
	%>
	<%
		out.println(count);					// count는 전역변수 이므로 현재 count의 값은 5
	%>
	
</body>
</html>