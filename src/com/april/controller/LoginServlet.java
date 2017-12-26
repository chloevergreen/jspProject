package com.april.controller;

import java.io.IOException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.RSAPublicKeySpec;
import java.util.List;
import java.util.Map;

import javax.crypto.Cipher;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import com.april.controller.action.Action;
import com.april.controller.controller.ActionFactory;
import com.april.member.MemberDto;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public LoginServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		//doGet(request, response);
		
		String command = request.getParameter("command");
		System.out.println("LoginServlet에서 요청을 받음을 확인 : " + command);
		
		ActionFactory actionfactory = ActionFactory.getInstance();
		Action action = actionfactory.getAction(command);
		
		if(action != null) {
			action.execute(request, response);
		}
		
		HttpSession session = request.getSession();
		boolean isSuccessLogin = false;
		String url = "";
		
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
