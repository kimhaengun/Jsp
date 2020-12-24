package com.cos.hello.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BoardController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}
	//Post요청은 FORM태그 만들고 요청 
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
 //어? 포스트요청왔네 그럼 내가 디비 연결 인서트하면되겠네
		System.out.println("comment post 요청");
		doProcess(req, resp);
	}
	protected void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("comment process 요청");
		System.out.println("boardController 실행됨");
		
		String gubun = req.getParameter("gubun");
		System.out.println("gubun");
		
		//http://localhost:8000/hello/board?gubun=deleteOne
		if(gubun.equals("deleteOne")) {
			resp.sendRedirect("board/deleteOne.jsp");
		}else if(gubun.equals("insertOne")) {
			resp.sendRedirect("board/insertOne.jsp");
		}else if(gubun.equals("selectAll")) {
			resp.sendRedirect("board/selectAll.jsp");
		}else if(gubun.equals("updateOne")) {
			resp.sendRedirect("board/updateOne.jsp");
		}
	}
}
