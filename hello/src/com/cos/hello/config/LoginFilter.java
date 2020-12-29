package com.cos.hello.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.cos.hello.model.Users;

import dto.LoginDto;

public class LoginFilter implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest req = (HttpServletRequest)request;
		String gubub = req.getParameter("gubun");
		
		if(gubub.equals("loginProc")) {
			if(req.getParameter("username")==null || 
					req.getParameter("password")==null || 
					req.getParameter("username").equals("") ||
					req.getParameter("password").equals("") ) {
				chain.doFilter(request, response);
				return;
			}
			//xss 공격 막기
			
			String username = req.getParameter("username");
			String password = req.getParameter("password");
			
//			Users userRequest = Users.builder()
//					.username(username)
//					.password(password)
//					.email(email)
//					.build();
			LoginDto dto = new LoginDto();
			dto.setUsername(username);
			dto.setPassword(password);
			request.setAttribute("user", dto);
					
		}
		chain.doFilter(request, response);
	}

}
