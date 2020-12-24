package com.cos.test1.config;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConn {
	
	public static Connection getConnection() {
		Connection conn = null;
		String url = "jdbc:mysql://localhost:3306/ssar?serverTimezone=Asia/Seoul";
		String username = "ssar";
		String password = "bitc5600";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url, username, password);
			
			System.out.println("DB 연결성공");
			return conn;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("실패");
		}
		return null;
	}
}
