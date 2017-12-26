package com.april.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.april.member.MemberDao;
import com.april.member.MemberDto;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RegisterServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		MemberDao memberDao = MemberDao.getInstance();
		
		String id		= request.getParameter("id");
		String password	= request.getParameter("password");
		String name		= request.getParameter("name");
		String email	= request.getParameter("email");
		
		boolean isSuccessRegister = memberDao.addMember(new MemberDto(id, name, password, email, 0));
	
		if(isSuccessRegister) {
			out.print("<script type='text/javascript'>"
						+ "alert('가입되엇습니다.');"
						+ "location.href='/view/board/boardList.jsp';</script>");
			out.close();
		}else {
			out.print("<script type='text/javascript'>"
					+ "alert('가입에 실패했습니다.');"
					+ "location.href='/view/member/register.jsp';</script>");
			out.close();
		}
	}

}
