<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Session Login</title>
</head>
<%
	String userid = "";
	if(session.getAttribute("userid") != null) {
		System.out.println("[getSession] Login OK");
		userid = (String)session.getAttribute("userid");
		
		// 환영 인사
%>
		<h1><%=userid%>님 반갑습니다.</h1>
		<h1>회비는 29만원입니다.</h1>
<%		
	}
	else{
%>
<body>
	<form name="loginForm" method="post" action="11_session_ok.jsp">
		<fieldset>
			<legend>Login Info.</legend>
			id : <input type="text" name="userid"><br>
			pw : <input type="password" name="passwd"><br>
			<input type ="button" value="login" onclick="sendIt()">
		</fieldset>
		
	</form>
<%
	}
%>
</body>
<script>
	var frm = document.loginForm;
	
	function sendIt(){
		if(frm.userid.value == ''){
			alert("넌 이름도 없냐?");
			return false;
		}
		if(frm.passwd.value ==''){
			alert("니 정보 다 샌다 괜찮냐?");
			return false;
		}
		
		frm.submit();
	}
</script>
</html>