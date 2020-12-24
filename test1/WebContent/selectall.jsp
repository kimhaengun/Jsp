<%@page import="java.util.ArrayList"%>
<%@page import="com.mysql.cj.protocol.Resultset"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="com.mysql.cj.xdevapi.Result"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="com.cos.test1.config.DBConn"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>찾아보기</title>
</head>
<body>
<%
	String sql = "SELECT * FROM users";
	Connection conn = DBConn.getConnection();
	PreparedStatement pstmt = conn.prepareStatement(sql);
 	ResultSet rs = pstmt.executeQuery();
 	ArrayList<> list = new ArrayList<>;
 	while(rs.next()){
 	int id = rs.getInt("id");
 	String username = rs.getString("username");
 	String password = rs.getString("password");
 	String email = rs.getString("email");
 	}
%>
<h3>id : <%=id %></h3>
<h3>username : <%=username %></h3>
<h3>password : <%=password %></h3>
<h3>email : <%=email %></h3>
</body>
</html>