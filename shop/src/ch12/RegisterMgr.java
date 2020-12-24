package ch12;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class RegisterMgr {
	private DBConnectionMgr conn;
	public RegisterMgr() {
		try {
			conn = DBConnectionMgr.getInstance();
			System.out.println("연결 성공");
		} catch (Exception e) {
			System.out.println("Error : 커넥션 연결 실패");
		}
			
	}
	public boolean loginRegister(String id, String pwd) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean loginCon = false;
		try {
			con = conn.getConnection();
			String query = "select count(*) from users where id = ? and pwd =?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			rs = pstmt.executeQuery();
			if(rs.next()&&rs.getInt(1)>0)
				loginCon = true;
		} catch (Exception ex) {
			System.out.println("Exception" + ex);
		}finally {
			conn.freeConnection(con, pstmt, rs);
		}
		return loginCon;
	}
	
}
