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
import com.cos.hello.service.UserJoinService;

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
		
		String gubun = req.getParameter("gubun"); // =/hello/front
		System.out.println(gubun);
		route(gubun, req, resp);
	}
	
	private void route(String gubun, HttpServletRequest req, HttpServletResponse resp)throws  IOException, ServletException {
		if(gubun.equals("login")) {
			resp.sendRedirect("auth/login.jsp"); // 한번더 request
			}else if(gubun.equals("join")) { //회원가입
				resp.sendRedirect("auth/join.jsp"); // 한번더 request
			}else if(gubun.equals("selectOne")) { //유저정보 보기
				//인증이 필요한 페이지
				String result;
				HttpSession session = req.getSession();
				if(session.getAttribute("sessionUser")!=null) { //인증끝.
					Users user = (Users)session.getAttribute("sessionUser");	
					result = "인증되었습니다.";
					System.out.println(user);
				}else {
					result = "인증되지 않았습니다.";
				}
			req.setAttribute("result", result);	//req에 result저장 세션에 하지말자
			RequestDispatcher dis = req.getRequestDispatcher("user/selectOne.jsp");
			dis.forward(req, resp);
			
			}else if(gubun.equals("updateOne")) {
				resp.sendRedirect("user/updateOne.jsp"); // 한번더 request
			}else if(gubun.equals("joinProc")) { // 회원가입 수행해줘
				UserJoinService userJoinService = new UserJoinService();
				userJoinService.회원가입(req, resp);


//				System.out.println("********joinProc Start*********");
//				System.out.println(username);
//				System.out.println(password);
//				System.out.println(email);
//				System.out.println("********joinProc Start*********");

				//3.INSERT가 정상적으로 되었다면  index.jsp를 응답
			}else if(gubun.equals("loginProc")) {
				//SELECT id, username, email FROM users WHERE username = ? AND password = ?
				//DAO의 함수명 : login() / 재사용 불가 return Users오브젝트를 리턴
				//정상이면 Users오브젝트에 담고 index.jsp / 비정상이면 login.jsp
				//1. 전달되는 값 받기
				String username = req.getParameter("username");
				String password = req.getParameter("password");
//				System.out.println("********loginProc Start*********");
//				System.out.println(username);
//				System.out.println(password);
//				System.out.println("********loginProc Start*********");
				//2.데이터베이스 값이 있는지 SELECT 해서 확인
				Users user = Users.builder().id(1).username(username).build();
				//3.
				HttpSession session = req.getSession();
				//session에는 사용자 패스워드 절대 넣지 않기
				session.setAttribute("sessionUser", user);
				//모든 응답에는 Jsession Id가 쿠키로 추가된다.
				
				//4.index.jsp페이지로 이동
				resp.sendRedirect("index.jsp");
			}
		}
}
