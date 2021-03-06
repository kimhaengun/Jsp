package config;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	//함수 만들기
	public static Connection getInstance() {
		//new 할 필요가 없기 때문에 static
		Connection conn = null;
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		//thin or oci 차이점
		//thin : 항상자바
		//oci : 운영체제에 따라 달라짐
		String username = "SCOTT";
		String password = "TIGER";
		
		//OracleDriver 클래스를 메모리에 로드
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, username, password);
			System.out.println("DB 연결 성ds공");
			return conn;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("연결 실패");
		return null;
		
	}
}
