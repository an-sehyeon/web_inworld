<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%	
		String site = request.getParameter("site");
		System.out.println("site : " + site);
		String url = "";
		if(site.equals("naver")){
			url = "08_forward_naver.jsp";
		}
		else {
			url = "index.html";
		}
	%>
	
	<jsp:forward page="<%=url%>" />
</body>
</html>