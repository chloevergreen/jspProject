<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>::RSA Login::</title>
	</head>
	<body>
		<h1>로그인</h1>
		<div>
			<form id = "loginFrm" action = "view/loginAf.jsp" method = "post">
				<!-- 서버에서 전달한 값을 세팅한다. -->
				<input type = "hidden" id = "RSAModulus" value = "${RSAModulus}" />
				<input type = "hidden" id = "RSAExponent" value = "${RSAExponent}" />
				
				<table border = "1">
					<tr>
						<th>ID</th>
						<td><input type = "text" id = "user_id" name = "user_id" emptyMsg = "아이디를 입력" value = "" size = "20" /></td>
					</tr>
					<tr>
						<th>PW</th>
						<td><input type = "text" id = "user_password" name = "user_password" empty = "비밀번호를 입력" value = "" size = "20" /></td>
					</tr>
					<tr>
						<td colspan = "2"><input type = "submit" value = "login"/></td>
					</tr>
				</table>
			</form>
			<a href = "view/regi.jsp">회원가입</a>		
		</div>
	</body>
</html>