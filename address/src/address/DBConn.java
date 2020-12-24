package address;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConn {
	
	public static Connection getConnection() {
		Connection conn = null;
		String url = "jdbc:mysql://localhost:3306/sys?serverTimezone=Asia/Seoul";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url, "root", "20174053");
			
			System.out.println("DB 연결성공");
			return conn;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("실패");
		}
		return null;
	}
}
