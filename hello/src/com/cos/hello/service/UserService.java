package com.cos.hello.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cos.hello.dao.UsersDao;
import com.cos.hello.model.Users;
import com.cos.hello.util.Script;

import dto.JoinDto;
import dto.LoginDto;

public class UserService {
	public void 회원가입(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//		// 데이터 원형 username = ssar&password=1234&email=ssar@nate.com
//		// 1.Form의 input태그에 있는 3가지 값 username/password/email받기
//		String username = req.getParameter("username"); // key 값은 = name값
//		// getParameter = 파싱 해주는 함수
//		String password = req.getParameter("password"); // key 값은 = name값
//		String email = req.getParameter("email"); // key 값은 = name값
//		Users user = Users.builder().username(username).password(password).email(email).build();
		JoinDto joindto = (JoinDto)req.getAttribute("dto");
		UsersDao usersDao = new UsersDao(); // getinstance형식으로 변경 userdao private로
		int result = usersDao.insert(joindto);

		if (result == 1) {
			resp.sendRedirect("auth/login.jsp");
		} else {
			resp.sendRedirect("auto/join.jsp");
		}

	}

	public void 로그인(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		// 1. 전달되는 값 받기
//		String username = req.getParameter("username");
//		String password = req.getParameter("password");
//		System.out.println("********loginProc Start*********");
//		System.out.println(username);
//		System.out.println(password);
//		System.out.println("********loginProc Start*********");
		// 2.데이터베이스 값이 있는지 SELECT 해서 확인
//		Users user = Users.builder().username(username).password(password).build();
		// 3.
		LoginDto logindto = (LoginDto)req.getAttribute("dto");
		UsersDao usersDao = new UsersDao();
		Users userEntity = usersDao.login(logindto);

		if (userEntity != null) {
			HttpSession session = req.getSession();
			// session에는 사용자 패스워드 절대 넣지 않기
			session.setAttribute("sessionUser", userEntity);
			//한글 처리를 위해 resp 객체를 건드린다.
			//MIME 타입 공부, Http Header에 Content-Type 공부
			Script.href(resp, "index.jsp", "로그인 성공");
		} else {
			Script.back(resp, "로그인 실패");
		}

	}

	public void 유저정보보기(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		//세션 체크
		HttpSession session = req.getSession();
		Users user = (Users)session.getAttribute("sessionUser");
		UsersDao usersDao = new UsersDao();
		
		if(user != null) {
			Users userEntity = usersDao.selectById(user.getId()); 
			req.setAttribute("user", userEntity);
			RequestDispatcher dis = req.getRequestDispatcher("user/selectOne.jsp");
			dis.forward(req, resp);
		}else {
			resp.sendRedirect("auth/login.jsp");
		}
	}
	public void 유저정보수정페이지(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		HttpSession session = req.getSession();
		Users user = (Users) session.getAttribute("sessionUser");
		UsersDao usersDao = new UsersDao();

		if(user != null) {
			Users userEntity = usersDao.selectById(user.getId());
			req.setAttribute("user", userEntity);

			RequestDispatcher dis =
					req.getRequestDispatcher("user/updateOne.jsp");
			dis.forward(req, resp);
		}else {
			resp.sendRedirect("auth/login.jsp");
		}

	}
	public void 회원수정(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		//세션 체크
		// getParameter = 파싱 해주는 함수
		int id = Integer.parseInt(req.getParameter("id"));
		String password = req.getParameter("password"); // key 값은 = name값
		String email = req.getParameter("email"); // key 값은 = name값

		Users user = Users.builder()
				.id(id)
				.password(password)
				.email(email)
				.build();

		UsersDao usersDao = new UsersDao(); // getinstance형식으로 변경 userdao private로
		int result = usersDao.update(user);

		if (result == 1) {
			resp.sendRedirect("user?gubun=updateOne");
		} else {
			resp.sendRedirect("user?gubun=updateOne");
		}
	}
	public void 회원삭제(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		//세션 체크
		// getParameter = 파싱 해주는 함수
		int id = Integer.parseInt(req.getParameter("id"));

		UsersDao usersDao = new UsersDao(); // getinstance형식으로 변경 userdao private로
		int result = usersDao.delete(id);

		if (result == 1) {
			HttpSession session =req.getSession();
			session.invalidate(); //세션 무효화
			resp.sendRedirect("index.jsp");
		} else {
			resp.sendRedirect("user?gubun=selectOne");
		}
	}
	
}
