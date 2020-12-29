package ch15;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

public class BoardMgr {
	private DBConnectionMgr pool;
	private static final String  SAVEFOLDER="D:\\workspace\\jsp\\ch15\\WebContent";
	private static final String ENCTYPE="UTF-8";
	private static int MAXSIZE = 5*1024*1024;
	public BoardMgr() {
		try {
			pool = DBConnectionMgr.getInstance();	
		}catch (Exception e) {
		e.getMessage();
		}
		
	}
	//게시판 리스트
	public Vector<BoardBean> getBoardList(String keyField, String keyWord,
			int start, int end) {
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = null;
			Vector<BoardBean> vlist = new Vector<BoardBean>();
			try {
				con = pool.getConnection();
				if(keyWord.equals("null") || keyWord.equals("")) {
					sql = "select * from tblBoard order by ref desc,pos limit ?, ?";
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, start);
					pstmt.setInt(2, end);
				}else {
					sql = "select * from tblBoard where"+keyField+"like ? order by ref desc, pos limit ?, ?";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1 , "%" +keyWord+ "%");
					pstmt.setInt(2, start);
					pstmt.setInt(3, end);
				}
				rs = pstmt.executeQuery();
				while(rs.next()) {
					BoardBean bean = new BoardBean();
					bean.setNum(rs.getInt("num"));
					bean.setName(rs.getString("name"));
					bean.setSubject(rs.getString("subject"));
					bean.setPos(rs.getInt("pod"));
					bean.setRef(rs.getInt("ref"));
					bean.setDepth(rs.getInt("depth"));
					bean.setRegdate(rs.getString("regdate"));
					bean.setCount(rs.getInt("count"));
					vlist.add(bean);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				pool.freeConnection(con, pstmt, rs);
			}
				return vlist;
	}
	
	//총 게시물수
	public int getTotalCount(String keyField, String keyWord) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int totalCount = 0;
		try {
			con=pool.getConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return totalCount;
	}
	
	//게시판 입력
	public void insertBoard(HttpServletRequest req) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		MultipartRequest multi = null;
		int filesize = 0;
		String filename = null;
		con = pool.getConnection();
		sql = "select max(num) from tblBoard";
		pstmt = con.prepareStatement(sql);
		rs=pstmt.executeQuery();
		int ref=1;
		if (rs.next()) {
			ref=rs.getInt(1)+1;
			File file = new File(SAVEFOLDER);
			if (!file.exists())
				file.mkdirs();
			multi = new multipartRequest(req, SAVEFOLDER, MAXSIZE, ENCTYPE);
			if (multi.get) {
				
			}
		}
	}
	//게시물 리턴
	public BoardBean getBoard(int num) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		BoardBean bean = new BoardBean();
		try {
			con = pool.getConnection();
			sql = "select * from tblBoard where num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs=pstmt.executeQuery();
			if (rs.next()) {
				bean.setNum(rs.getInt("num"));
				bean.setName(rs.getString("name"));
				bean.setSubject(rs.getString("subject"));
				bean.setPos(rs.getInt("pod"));
				bean.setRef(rs.getInt("ref"));
				bean.setDepth(rs.getInt("depth"));
				bean.setRegdate(rs.getString("regdate"));
				bean.setPass(rs.getString("pass"));
				bean.setCount(rs.getInt("count"));
				bean.setFilename(rs.getString("filename"));
				bean.setFilesize(rs.getInt("filesize"));
				bean.setIp(rs.getString("ip"));
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			pool.freeConnection(con,pstmt,rs);
		}
		return bean;
	}
	//조회수 증가
	public void upCount(int num) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			con=pool.getConnection();
			sql = "update tblBoard set count=count+1 where num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			pool.freeConnection(con, pstmt);
		}
	}
	//게시물 삭제
	public void deleteBoard(int num) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		ResultSet rs = null;
		
		try {
			con=pool.getConnection();
			sql = "select filename from tblBoard where num = ?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if(rs.next() && rs.getString(1) !=null) {
				if(!rs.getString(1).equals("")) {
					File file = new File(SAVEFOLDER+"/"+rs.getString(1));
					if(file.exists())
						Ut
				}
			}
			sql = "delect from tblBoard where num =?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			pool.freeConnection(con,pstmt,rs);
		}
	}
	//게시물 수정
	public void updateBoard(BoardBean bean) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			con=pool.getConnection();
			sql = "update tblBoard set name = ?, subject=?, countent=? where num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, bean.getName());
			pstmt.setString(2, bean.getSubject());
			pstmt.setString(3, bean.getContent());
			pstmt.setInt(4, bean.getNum());
			pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			pool.freeConnection(con, pstmt);
		}
	}
	//게시물 답변
	public void replyBoard(BoardBean bean) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			con=pool.getConnection();
			sql = "insert tblBoard(name, content,subject,ref,pos,depth,regdate,pass,count,ip) "
					+ "values(?,?,?,?,?,?,now(),?,0,?)";
			int depth = bean.getDepth()+1;
			int pos = bean.getPos()+1;
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, bean.getName());
			pstmt.setString(2, bean.getContent());
			pstmt.setString(3, bean.getSubject());
			pstmt.setInt(4, bean.getRef());
			pstmt.setInt(5, pos);
			pstmt.setInt(6, depth);
			pstmt.setString(7, bean.getPass());
			pstmt.setString(8, bean.getIp());
			pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			pool.freeConnection(con, pstmt);
		}
	}
}
