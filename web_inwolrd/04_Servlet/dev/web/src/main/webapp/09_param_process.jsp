<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Parameter Test</title>
</head>
<body>
	<h1>Data Extraction</h1>
	<h2>ID : <%=request.getParameter("id") %></h2>
	<h2>NAME : <%=request.getParameter("name") %></h2>
	
	<fieldset>
		<legend><%=request.getParameter("name")+"님 반가워요" %></legend> 
		<button><%=request.getParameter("name") %></button>
		<input type="text" value="<%=request.getParameter("name") %>">
	</fieldset>
	<!-- http://localhost:10000/web/09_param_process.jsp?id=apple&name=코딩의신 -->
</body>
</html>