package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import config.DBConnection;
import model.Dept;

//Data Access Object(데이터에 접근하게 해주는 객체)
//재사용!!!

public class DeptDao {
	public  void 추가(int id) {
		String sql = "INSERT INTO test1(id) VALUES(?)";
		Connection conn =DBConnection.getInstance();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			int result = pstmt.executeUpdate();
			//executeUpdate --> 변경된 row count를 리턴, -1값 = 오류
			System.out.println("result : "+result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public  void 삭제(int id) {
		String sql = "DELETE FROM test1 WHERE id =?";
		Connection conn =DBConnection.getInstance();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			int result = pstmt.executeUpdate();
			//executeUpdate --> 변경된 row count를 리턴, -1값 = 오류
			System.out.println("result : "+result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public  void 업데이트(int id) {
		String sql = "UPDATE test1 SET id= 9 WHERE id=?" ;
		Connection conn =DBConnection.getInstance();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			int result = pstmt.executeUpdate();
			//executeUpdate --> 변경된 row count를 리턴, -1값 = 오류
			System.out.println("result : "+result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public  Dept 찾기(int deptno) { //Dept클래스와 연결하기
		String sql = "SELECT deptno, dname, loc FROM dept WHERE deptno= ?" ;
		Connection conn =DBConnection.getInstance();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, deptno);
			ResultSet rs = pstmt.executeQuery();
			//rs = 결과 집합 
			if(rs.next()) {
				Dept dept = Dept.builder()
						.deptno(rs.getInt("deptno"))
						.dname(rs.getString("dname"))
						.loc(rs.getString("loc"))
						.build();
				return dept;
//				int deptno2 = rs.getInt("deptno");
//				String dname = rs.getString("dname");
//				String loc = rs.getString("loc");
//				System.out.println(deptno2);
//				System.out.println(dname);
//				System.out.println(loc);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public  List<Dept> 전체찾기() {
		String sql = "SELECT deptno, dname, loc FROM dept" ;
		Connection conn =DBConnection.getInstance();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			//rs = 결과 집합 
			ArrayList<Dept> d = new ArrayList<Dept>();
			while(rs.next()) {
				Dept dept2 = Dept.builder()
						.deptno(rs.getInt("deptno"))
						.dname(rs.getString("dname"))
						.loc(rs.getString("loc"))
						.build();
				d.add(dept2);
//				int deptno2 = rs.getInt("deptno");
//				String dname = rs.getString("dname");
//				String loc = rs.getString("loc");
//				System.out.println(deptno2);
//				System.out.println(dname);
//				System.out.println(loc);
			}
			return  d; //while이 끝나면 return
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
