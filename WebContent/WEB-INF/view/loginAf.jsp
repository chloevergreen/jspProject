<%@ page import = "java.security.PrivateKey" %>
<%@ page import = "javax.crypto.Cipher" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title></title>
	</head>
	<body>
<%!
	public String decryptRsa(PrivateKey privateKey, String securedValue) {
		String decruptedValue = "";
		
		try{
		
			Cipher cipher = Cipher.getInstance("RSA");
			/*
			암호화 된 값은 byte 배열이다.
			이를 문자열 폼으로 전송하기 위해 16진 문자열(hex)로 변경한다.
			서버 측에서도 값을 받을 떼 hex 문자열을 받아서 이를 다시 byte 배열로 바꾼 뒤 복호화 과정을 수행한다.
			*/
			byte[] encryptedBytes = hexToByteArray(securedValue);
		
		}catch(Exception e){
			
		}
		
		return decruptedValue;
	}

	public static byte[] hexToByteArray(String hex) {
		byte[] bytes = new byte[hex.length() / 2];
		return bytes;
	}
%>

	<!-- 로그인 체크 -->
	</body>
</html>