package com.aizant.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		HttpSession session = request.getSession();
		System.out.println("SESSION ID AND NAME AND STUFF " + session.getId());
		System.out.println("Login INFOOO " + request.getParameter("username") + " " + request.getParameter("password") + "#$#$#$");
		response.sendRedirect("/welcome");
	}

}
