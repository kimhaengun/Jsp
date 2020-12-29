package ch15;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ch15/boardPost")
public class BoardPostServlet extends HttpServlet{
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException , IOException{
		req.setCharacterEncoding("EUC-KR");
		BoardMgr bMgr = new BoardMgr();
		bMgr.insertBoard(req); //요청 req를 매개변수로 insertBoard를 호출한다.
		resp.sendRedirect("list.jsp");
	}
}
