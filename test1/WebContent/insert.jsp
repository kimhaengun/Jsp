<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="com.cos.test1.config.DBConn"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>추가하기</title>
</head>
<body>
	<%
		Connection conn = DBConn.getConnection();
		String sql = "INSERT INTO users(username, password, email) VALUES (?, ?, ?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, "love");
		pstmt.setString(2, "1234");
		pstmt.setString(3, "love@nate.com");
		pstmt.execute();
	%>
	성공
	<%
	%>
</body>
</html>