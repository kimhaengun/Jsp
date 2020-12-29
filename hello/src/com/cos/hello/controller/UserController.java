package com.cos.hello.controller;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
//javax로 시작하는 패키지는 톰켓이 들고 있는 라이브러리
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cos.hello.config.DBConn;
import com.cos.hello.dao.UsersDao;
import com.cos.hello.model.Users;
import com.cos.hello.service.UserService;

//디스패쳐의 역할 = 분기 = 필요한 View를 응답해주는것
public class UserController extends HttpServlet{
	//req와 res는 톰켓이 만들어준다(사용자(클라이언트)요청이 있을 때 마다)
	//req는 BufferedReader 할 수 있는 ByteStream
	//res는 BufferedWriter 할 수 있는 ByteStream 
	
	//http://localhost:8000/hello/front
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
 //어? 포스트요청왔네 그럼 내가 디비 연결 인서트하면되겠네
		System.out.println("comment post 요청");
		doProcess(req, resp);
	}
	protected void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		System.out.println("comment process 요청");
		System.out.println("UserController 실행됨");
		//req.getparameter함수 실행시에 파싱하기 때문에
		//파싱전에 인코딩해줘야한다.
		String gubun = req.getParameter("gubun"); // =/hello/front
		System.out.println(gubun);
		route(gubun, req, resp);
	}
	
	private void route(String gubun, HttpServletRequest req, HttpServletResponse resp)throws  IOException, ServletException {

		UserService userService = new UserService();
		
		if(gubun.equals("login")) {
			resp.sendRedirect("auth/login.jsp"); // 한번더 request
			}else if(gubun.equals("join")) { //회원가입
				resp.sendRedirect("auth/join.jsp"); // 한번더 request
				
				
			}else if(gubun.equals("selectOne")) { //유저정보 보기
				userService.유저정보보기(req,resp);
			}else if(gubun.equals("updateOne")) {
				userService.유저정보수정페이지(req,resp);
			}else if(gubun.equals("joinProc")) { // 회원가입 수행해줘
				userService.회원가입(req, resp);
			}else if(gubun.equals("loginProc")) {
				//SELECT id, username, email FROM users WHERE username = ? AND password = ?
				//DAO의 함수명 : login() / 재사용 불가 return Users오브젝트를 리턴
				//정상이면 Users오브젝트에 담고 index.jsp / 비정상이면 login.jsp
				userService.로그인(req, resp);
				//모든 응답에는 Jsession Id가 쿠키로 추가된다.
			}else if(gubun.equals("updateProc")) {
				userService.회원수정(req,resp);
			}else if(gubun.equals("deleteProc")) {
				userService.회원삭제(req, resp);
			}
		}
}

