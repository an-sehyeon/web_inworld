<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page info="Date Class를 이용한 현재 날짜 출력" %>		<!-- 현재 JSP에 대한 설명 -->
    <%@ page import="java.util.Date" %>						<!-- 현재 JSP에서 사용할 자바 패키지, 클래스 설정 -->
	<%@ page errorPage="06_error_page.jsp" %>			    <!-- 현재 JSP에서 오류 발생 시 이동할 페이지 설정 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Directive Example</title>
</head>
<body>
	<h1>Directive Example</h1>
	<hr>
	<p>Today : <%=new Date()%></p>
	<%
		// int i = 10/0;
		String s= "Boys be ambitious";
		
		try {
				char p = s.charAt(100);
		}
		catch(Exception e){
			
		} 
	%>
</body>
</html>