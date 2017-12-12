<%@ page import = "java.security.KeyPairGenerator" %>
<%@ page import = "java.security.KeyPair" %>
<%@ page import = "java.security.KeyFactory" %>
<%@ page import = "java.security.PublicKey" %>
<%@ page import = "java.security.PrivateKey" %>
<%@ page import = "java.security.spec.RSAPublicKeySpec" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>::RSA Login::</title>
		<script type="text/javascript" src="/lib/js/jquery-3.2.1.js"></script>
		
		<!-- RSA 자바스크립트 라이브러리 -->
		<!-- RSA 암호화 처리 스크립트 -->
	</head>
	<body>
<%
	//HttpSession session = request.getSession();
	boolean isSuccessLogin = false;
	
	if(isSuccessLogin){
		response.sendRedirect("main.jsp");
	}
	else{
		KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
		generator.initialize(1024);
		KeyPair keyPair = generator.genKeyPair();
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PublicKey publicKey = keyPair.getPublic();
		PrivateKey privateKey = keyPair.getPrivate();
		
		//세션에 RSA 개인키를 세션에 저장함
		session.setAttribute("_RSA_WEB_Key_", privateKey);
		RSAPublicKeySpec publicSpec = keyFactory.getKeySpec(publicKey, RSAPublicKeySpec.class);
		String publicKeyModulus = publicSpec.getModulus().toString(16);
		String publicKeyExponent = publicSpec.getPublicExponent().toString(16);
		
		//로그인 폼에 Input Hidden에 값을 세팅하기 위해서
		request.setAttribute("RSAModulus", publicKeyModulus);
		request.setAttribute("RSAExponent", publicKeyExponent);
		
		response.sendRedirect("login.jsp");
	}
%>	
		<div>
			<h1>로그인</h1>
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
						<th>PASSWORD</th>
						<td><input type = "text" id = "user_password" name = "user_password" empty = "비밀번호를 입력" value = "" size = "20" /></td>
					</tr>
					<tr>
						<td colspan = "2"><input type = "submit" id = "submit_login" value = "LOGIN" alt = "로그인 버튼"/></td>
					</tr>
				</table>
			</form>
			<a href = "view/regi.jsp">회원가입</a>		
		</div>
	</body>
	
	<script>
		$("#submit_login").click(function(){
			//사용자 계정 정보 암호화 전 평문
			var id = $("#user_id").val();
			var password = $("#user_password").val();
			
			//RSA 암호화 생성
			var rsa = new RSAKey();
			rsa.setPublic($("#OASISKeyModulus").val(), $("OASISKeyExponent").val());
			
			//사용자 계정 정보를 암호화 처리
			id = rsa.encrypt(id);
			password = rsa.encrypt(password);
			
			$.ajax({
				//암호화된 계정 정보를 서버로 전송
				type: "POST",
				url: "veiw/loginAf.jsp",
				data: {user_id:id, user_password:password},
				ㅇㅁㅅㅁ쑈ㅔㄷ: "json",
				success: function(msg){
					if(msg.state == "true"){
						location.href = "view/main.jsp"
					}else if(msg.state == "false"){
						THIS.oWin.alert("로그인", "로그인에 실패했습니다. <br> 아이디, 비밀번호를 확인하세요.");
					}else{
						THIS.oWin.alert("로그인", "잘못된 경로입니다. <br> 암호화 인증에 실패했습니다.");
					}
				}
			});
		});
	</script>
</html>