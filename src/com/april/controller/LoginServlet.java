package com.april.controller;

import java.io.IOException;
import java.security.PrivateKey;
import java.util.List;
import java.util.Map;

import javax.crypto.Cipher;
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
	}
}
