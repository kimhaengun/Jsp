package address.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import address.DBConn;
import address.model.User;

public class UserDao {
	
	public static void 삭제하기(int id) {
		// stream에 연결된 객체
		Connection conn = DBConn.getConnection();

		// 버퍼에 담을 메시지
		String query = "delete from users where id = ?";

		try {
			// 버퍼연결해서 쿼리 전송
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, id);
			// commit
			pstmt.executeUpdate();
			System.out.println("DELETE가 완료되었습니다.");
		} catch (SQLException e) {
			System.out.println("Query 전송시 오류가 발생했습니다.");
		}
	}

	
	public static void 수정하기(int id, String name, String phone, String address, String relation) {
		// stream에 연결된 객체
		Connection conn = DBConn.getConnection();

		// 버퍼에 담을 메시지
		String query = "update users set name = ?, phone = ?, address = ?, relation = ? where id = ?";

		try {
			// 버퍼연결해서 쿼리 전송
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, name);
			pstmt.setString(2, phone);
			pstmt.setString(3, address);
			pstmt.setString(4, relation);
			pstmt.setInt(5, id);
			
			// commit
			pstmt.executeUpdate();
			System.out.println("UPDATE가 완료되었습니다.");
		} catch (SQLException e) {
			System.out.println("Query 전송시 오류가 발생했습니다."+e.getMessage());
		}
	}
	
	public static ArrayList<User> 찾기() {
		// 컬렉션 만들기
		ArrayList<User> list = new ArrayList<User>();
		
		// stream에 연결된 객체
		Connection conn = DBConn.getConnection();

		// 버퍼에 담을 메시지
		String query = "select * from users";

		try {
			// 버퍼연결해서 쿼리 전송
			PreparedStatement pstmt = conn.prepareStatement(query);
			// 결과 받기
			ResultSet rs = pstmt.executeQuery();
			System.out.println("SELECT가 완료되었습니다.");
			
			while(rs.next()) { // 검색 결과가 한건이 아니면  while로 변경한다.
				int id = rs.getInt("id"); // 칼럼명
				String name = rs.getString("name");
				String phone = rs.getString("phone");
				String address = rs.getString("address");
				String relation = rs.getString("relation");
				
				User user = new User(id, name, phone, address, relation);
				list.add(user);
			}	
			return list;
			
		} catch (SQLException e) {
			System.out.println("Query 전송시 오류가 발생했습니다."+e.getMessage());
		}
		return null;
	}
	
	
	public static ArrayList<User> 검색(String relation) {
		// 컬렉션 만들기
		ArrayList<User> list = new ArrayList<User>();
		
		// stream에 연결된 객체
		Connection conn = DBConn.getConnection();

		// 버퍼에 담을 메시지
		String query = "select * from users where relation = ?";

		try {
			// 버퍼연결해서 쿼리 전송
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, relation);
			
			// 결과 받기
			ResultSet rs = pstmt.executeQuery();
			System.out.println("SELECT가 완료되었습니다.");
			
			while(rs.next()) { // 검색 결과가 한건이 아니면  while로 변경한다.
				int id = rs.getInt("id"); // 칼럼명
				String name = rs.getString("name");
				String phone = rs.getString("phone");
				String address = rs.getString("address");
				String relation2 = rs.getString("relation");
				
				User user = new User(id, name, phone, address, relation2);
				list.add(user);
			}	
			return list;
			
		} catch (SQLException e) {
			System.out.println("Query 전송시 오류가 발생했습니다."+e.getMessage());
		}
		return null;
	}
	
	public static User 찾기(int id) {
		// stream에 연결된 객체
		Connection conn = DBConn.getConnection();

		// 버퍼에 담을 메시지
		String query = "select id, name, phone, address, relation from users where id = ?";

		try {
			// 버퍼연결해서 쿼리 전송
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, id);
			// 결과 받기
			ResultSet rs = pstmt.executeQuery();
			System.out.println("SELECT가 완료되었습니다.");
			
			if(rs.next()) { // 검색 결과가 한건이 아니면  while로 변경한다.
				int id2 = rs.getInt("id"); // 칼럼명
				String name = rs.getString("name");
				String phone = rs.getString("phone");
				String address = rs.getString("address");
				String relation = rs.getString("relation");
				
				return new User(id2, name, phone, address, relation);
			}			
			
		} catch (SQLException e) {
			System.out.println("Query 전송시 오류가 발생했습니다."+e.getMessage());
		}
		return null;
	}
	


	public static void 추가하기(String name, String phone, String address, String relation) {
		// TODO Auto-generated method stub
		// stream에 연결된 객체
		Connection conn = DBConn.getConnection();

		// 버퍼에 담을 메시지
		String query = "INSERT INTO users(name, phone, address, relation) VALUES(?, ?, ?, ?)";

		try {
			// 버퍼연결해서 쿼리 전송
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, name);
			pstmt.setString(2, phone);
			pstmt.setString(3, address);
			pstmt.setString(4, relation);
			// commit
			pstmt.executeUpdate();
			System.out.println("INSERT가 완료되었습니다.");
		} catch (SQLException e) {
			System.out.println("Query 전송시 오류가 발생했습니다.");
		}
	}
}
