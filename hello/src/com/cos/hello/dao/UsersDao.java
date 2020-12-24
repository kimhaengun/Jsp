package com.cos.hello.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.cos.hello.config.DBConn;
import com.cos.hello.model.Users;

public class UsersDao {
	
	public int insert(Users user) { //user 오브젝트 안에 다 들어가있다.
		//2.DB에 연결해서 3가지 값을 insert하기
		StringBuffer sb = new StringBuffer(); //String 전용 컬렉션(동기화 / 동시접속 불가)
		sb.append("INSERT INTO users(username,password,email) ");
		sb.append("VALUES(?,?,?)");
		String sql = sb.toString();
		Connection conn = DBConn.getInstance();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getEmail());
			int result = pstmt.executeUpdate();
			return result;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1; //실패
	}
}
