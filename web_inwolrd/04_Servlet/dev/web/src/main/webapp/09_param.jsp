<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Parameter Tag</title>
</head>
<body>
	<h1>Parameter Action</h1>
	<h3>&lt;jsp:param&gt;</h3>
	<hr>
</body>
<jsp:forward page="09_param_process.jsp">
	<jsp:param name="id" value="administrator" />
	<jsp:param name="name" value="OneAndOnly" />
</jsp:forward>	
</html>