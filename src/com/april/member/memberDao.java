package com.april.member;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class memberDao implements imemberDao {
	private boolean isSuccess = false;
	private static memberDao memberDao;
	
	private memberDao(){
		try {
			Class.forName("oracle.jdbc.diver.OracleDriver");
			log("1/6 Success");
		} catch (ClassNotFoundException e) {
			log("1/6 Fail", e);
			System.out.println(e.getMessage());
		}
	}
	
	public static memberDao getInstance(){
		if(memberDao == null){
			memberDao = new memberDao();
		}
		return memberDao;
	}
	
	public static Connection getConnection() throws SQLException{
		Connection connection = null;
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "hr";
		String password = "hr";
		
		connection = DriverManager.getConnection(url, user, password);
		
		return connection;
	}
	
	public static void close(Connection conn, PreparedStatement psmt, ResultSet rs){
		if(rs != null){
			try {
				rs.close();
			} catch (SQLException e) {
				//예외 표시에 대하여 ...
				//e.printStackTrace();}
			}
		}
		if(psmt != null){
			try {
				psmt.close();
			} catch (SQLException e) {}
		}
		if(conn != null){
			try {
				conn.close();
			} catch (SQLException e) {}
		}
	}
	
	@Override
	public boolean addMember(memberDto member) {
		
		String sql = " INSERT INTO MEMBER "
				+ " VALUES(?, ?, ?, ?, 3) ";
		
		Connection conne = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		int count = 0;
		
		//연결하고
		//로그 디버그
		//작성해보기
		
		return ; //삼항연산자
	}

	@Override
	public memberDto login(memberDto member) {
		return null;
	}
	
	public void log(String msg){
		if(isSuccess){
			System.out.println(getClass() + " : " + msg);
		}
	}
	public void log(String msg, Exception e){
		//isSuccess 를 true로 따로 대입 해야하는가...?
		if(isSuccess){
			System.out.println(e + " : " + getClass() + " : " + msg);
		}
	}
}
