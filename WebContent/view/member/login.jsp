<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>::RSA Login::</title>
		
		<script type="text/javascript" src="/js/jquery-3.2.1.js"></script>
		<!-- RSA 자바스크립트 라이브러리 -->
		<script type="text/javascript" src="/js/rsa/jsbn.js"></script>
		<script type="text/javascript" src="/js/rsa/rsa.js"></script>
		<script type="text/javascript" src="/js/rsa/prng4.js"></script>
		<script type="text/javascript" src="/js/rsa/rng.js"></script>
		<!-- RSA 암호화 처리 스크립트 -->
		<script type="text/javascript" src="/js/views/member/login.js"></script>
	</head>
	<body>
		<div>
			<h1>로그인</h1>
			<form id = "loginFrm" action = "/LoginServlet?command=login" method = "post">
				<!-- 서버에서 전달한 값을 세팅한다. -->
				<input type = "hidden" id = "RSAModulus" value = "${RSAModulus}" />
				<input type = "hidden" id = "RSAExponent" value = "${RSAExponent}" />
				
				<table border = "1">
					<tr>
						<th>ID</th>
						<td><input type = "text" id = "user_id" name = "user_id" placeholder = "아이디를 입력" value = "" size = "20" /></td>
					</tr>
					<tr>
						<th>PASSWORD</th>
						<td><input type = "password" id = "user_password" name = "user_password" placeholder = "비밀번호를 입력" value = "" size = "20" /></td>
					</tr>
					<tr>
						<td colspan = "2"><input type = "submit" id = "submit_login" value = "LOGIN" alt = "로그인 버튼"/></td>
					</tr>
				</table>
			</form>
			<a href = "/view/member/register.jsp">회원가입</a>		
		</div>
	</body>
</html>