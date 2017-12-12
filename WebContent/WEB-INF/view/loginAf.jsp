<%@ page import = "java.security.KeyPairGenerator" %>
<%@ page import = "java.security.KeyPair" %>
<%@ page import = "java.security.KeyFactory" %>
<%@ page import = "java.security.PublicKey" %>
<%@ page import = "java.security.PrivateKey" %>
<%@ page import = "java.security.spec.RSAPublicKeySpec" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title></title>
	</head>
	<body>
		<script type = "text/javascript">
		
		</script>
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
			
			response.sendRedirect("index.jsp");
		}
%>
	</body>
</html>