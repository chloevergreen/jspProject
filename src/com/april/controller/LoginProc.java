package com.april.controller;

import java.security.PrivateKey;
import java.util.List;
import java.util.Map;

import javax.crypto.Cipher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import com.april.member.MemberDto;

public class LoginProc {
	/* 로그인 체크  /proc/login.proc*/
	public JSONObject loginCheck(HttpServletRequest request, Map mMap) {
		List<MemberDto> loginMembers = null;
		JSONObject listObj = new JSONObject();
		
		String id = request.getParameter("user_id");
		String password = request.getParameter("user_password");
		HttpSession session = request.getSession();
		
		//로그인 전에 세션에 저장된 개인키를 가져온다.
		PrivateKey privateKey = (PrivateKey)session.getAttribute("_RSA_WEB_Key_");
		
		if(privateKey == null) {
			listObj.put("state", "false");
		}else {
			try {
				//암호화 처리된 사용자 계정정보를 복호화 처리한다.
				String id_ = decryptRsa(privateKey, id);
				String password_ = decryptRsa(privateKey, password);
				
				//복호화 처리된 계정정보를 map에 감아서 DB와 연동한다.
				mMap.put("user_id", id_);
				mMap.put("user_password", password_);
				
				//DB 처리 및 로그인 후 session 처리
			}
			catch(Exception e) {
				listObj.put("state", "false");
				System.out.println("login ERROR : " + e.getMessage());
			}
		}
		
		return listObj;
	}
	
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
			cipher.init(Cipher.DECRYPT_MODE, privateKey);
			byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
			decruptedValue = new String(decryptedBytes, "utf-8");
			
		}catch(Exception e) {
			//log4j로 로그 기록하기
			System.out.println("decryptRsa Exception Error : " + e.getMessage());
		}
		
		return decruptedValue;
	}
	
	/*
	16진 문자열을 byte 배열로 변환한다.
	*/
	public static byte[] hexToByteArray(String hex) {
		if(hex == null || hex.length() % 2 != 0) {
			return new byte[]{};
		}
		
		byte[] bytes = new byte[hex.length() / 2];
		for(int i = 0; i < hex.length(); i += 2) {
			byte value = (byte)Integer.parseInt(hex.substring(i, i + 2), 16);
			bytes[(int)Math.floor(i / 2)] = value;
		}
		
		return bytes;
	}
}
