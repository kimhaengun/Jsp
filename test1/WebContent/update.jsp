<%@page import="java.sql.PreparedStatement"%>
<%@page import="com.cos.test1.config.DBConn"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	Connection conn = DBConn.getConnection();
	String sql = "UPDATE users SET username = 'love' WHERE username = ? ";
	PreparedStatement pstmt = conn.prepareStatement(sql);
	pstmt.setString(1, "loves");
	pstmt.executeUpdate();
%>
<h3>변경 성공</h3>
</body>
</html>