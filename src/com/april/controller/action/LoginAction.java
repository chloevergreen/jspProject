package com.april.controller.action;

import java.io.IOException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.RSAPublicKeySpec;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		
		String url = "";
		
		HttpSession session = request.getSession();
		boolean isSuccessLogin = false;
		
		if(isSuccessLogin) { //로그인 처리 완료 조건
			
			url = "/view/board/boardList.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(url);
			dispatcher.forward(request, response);
		}
		else {
			KeyPairGenerator generator;
			
			try {
				generator = KeyPairGenerator.getInstance("RSA");
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
				
				url = "/view/member/login.jsp";
				RequestDispatcher dispatcher = request.getRequestDispatcher(url);
				dispatcher.forward(request, response);
				
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
}
