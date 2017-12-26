<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title></title>
	</head>
	<body>
		<div>
			<h1>회원 가입</h1>
			
			<form action = "/register" method="post" >
				<table>
					<tr>
						<th>아이디</th>
						<td><input type="text" name="id" size="20" /></td>
					</tr>
					<tr>
						<th>비밀번호</th>
						<td><input type="password" name="password" size="20" /></td>
					</tr>
					<tr>
						<th>이름</th>
						<td><input type="text" name="name" size="20" /></td>
					</tr>
					<tr>
						<th>이메일</th>
						<td><input type="text" name="email" size="20" /></td>
					</tr>
					<tr>
						<td colspan = "2"><input type="submit" value="회원가입" /></td>
					</tr>
				</table>
			</form>
			<a href = "/index.jsp">홈</a>
		</div>
	</body>
</html>