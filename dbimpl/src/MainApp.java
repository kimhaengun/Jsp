import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import config.DBConnection;
import dao.DeptDao;
import model.Dept;

public class MainApp {

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		추가(9);
//		삭제(1);
////		업데이트(10);
//		Dept dept = 찾기(10);
//		System.out.println(dept);
		DeptDao dao = new DeptDao();
		List<Dept> d= dao.전체찾기();
		System.out.println(d);
	}

}
