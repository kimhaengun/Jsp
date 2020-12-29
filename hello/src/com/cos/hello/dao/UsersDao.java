package com.cos.hello.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.cos.hello.config.DBConn;
import com.cos.hello.model.Users;

import dto.JoinDto;
import dto.LoginDto;

public class UsersDao {
	
	public int insert(JoinDto joindto) { //user 오브젝트 안에 다 들어가있다.
		//2.DB에 연결해서 3가지 값을 insert하기
		StringBuffer sb = new StringBuffer(); //String 전용 컬렉션(동기화 / 동시접속 불가)
		sb.append("INSERT INTO users(username,password,email) ");
		sb.append("VALUES(?,?,?)");
		String sql = sb.toString();
		Connection conn = DBConn.getInstance();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, joindto.getUsername());
			pstmt.setString(2, joindto.getPassword());
			pstmt.setString(3, joindto.getEmail());
			int result = pstmt.executeUpdate();
			return result;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1; //실패
	}
	public Users login(LoginDto logindto) {
		String sql = "SELECT id, username, email FROM users WHERE username = ? AND password = ?";
		Connection conn = DBConn.getInstance();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, logindto.getUsername());
			pstmt.setString(2, logindto.getPassword());
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				Users userEntity = Users.builder()
						.id(rs.getInt("id"))
						.username(rs.getString("username"))
						.build();
				return userEntity;
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public Users selectById(int id) {
		String sql = "SELECT id, password, username, email FROM users WHERE id=?";
		Connection conn = DBConn.getInstance();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				Users userEntity = Users.builder()
						.id(rs.getInt("id"))
						.password(rs.getString("password"))
						.username(rs.getString("username"))
						.email(rs.getString("email"))
						.build();
				return userEntity;
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public int update(Users user) { //user 오브젝트 안에 다 들어가있다.
		//2.DB에 연결해서 3가지 값을 insert하기
		StringBuffer sb = new StringBuffer(); //String 전용 컬렉션(동기화 / 동시접속 불가)
		sb.append("UPDATE users SET password=?,email=? ");
		sb.append("WHERE id =?");
		String sql = sb.toString();
		Connection conn = DBConn.getInstance();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, user.getPassword());
			pstmt.setString(2, user.getEmail());
			pstmt.setInt(3, user.getId());
			int result = pstmt.executeUpdate();
			return result;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1; //실패
	}
	public int delete(int id) { //user 오브젝트 안에 다 들어가있다.
		//2.DB에 연결해서 3가지 값을 insert하기
		String sql = "DELETE FROM users WHERE id=?";
		Connection conn = DBConn.getInstance();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);;
			int result = pstmt.executeUpdate();
			return result;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1; //실패
	}
}
