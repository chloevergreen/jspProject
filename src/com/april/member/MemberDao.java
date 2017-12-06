package com.april.member;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberDao implements InterfMemberDao {
	private boolean isSuccess = false;
	private static MemberDao memberDao;
	
	private MemberDao(){
		try {
			Class.forName("oracle.jdbc.diver.OracleDriver");
			log("Success");
		} catch (ClassNotFoundException e) {
			log("Fail", e);
			System.out.println(e.getMessage());
		}
	}
	
	public static MemberDao getInstance(){
		if(memberDao == null){
			memberDao = new MemberDao();
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
			} catch (SQLException e) {}
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
	public boolean addMember(MemberDto member) {
		
		String sql = " INSERT INTO MEMBER "
				+ " VALUES(?, ?, ?, ?, 3) ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		int count = 0;
		
		try{
			conn = memberDao.getConnection();
			log("1/4 Success");
			
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, member.getId());
			psmt.setString(2, member.getName());
			psmt.setString(3, member.getPw());
			psmt.setString(4, member.getEmail());
			psmt.setInt(5, member.getAuthority());
			log("2/4 Success");
			
			count = psmt.executeUpdate(); //executeUpdate() return 0 : nothing
			log("3/4 Success");
		} catch (SQLException e) {
			log("Fail", e);
		} finally {
			memberDao.close(conn, psmt, rs);
			log("4/4 Success");
		}
		
		return count > 0 ? true : false;
	}

	@Override
	public MemberDto login(MemberDto member) {
		String sql = " SELECT ID, NAME, EMAIL, AUTH FROM MEMBER WHERE ID = ? AND PW = ? ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		MemberDto loginMember = null;

		try {
			conn = memberDao.getConnection();
			log("1/5 Success");
			
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, member.getId());
			psmt.setString(2, member.getPw());
			log("2/5 Success");
			
			rs = psmt.executeQuery();
			log("3/5 Success");
			
			while(rs.next()) {
				String id = rs.getString(1);
				String name = rs.getString(2);
				String email = rs.getString(3);
				int authority = rs.getInt(4);
				
				loginMember = new MemberDto(id, name, null, email, authority);
			}
			log("4/5 Success");
			
		} catch (SQLException e) {
			log("Fail", e);
		} finally {
			memberDao.close(conn, psmt, rs);
			log("5/5 Success");
		}
		
		return loginMember;
	}
	
	public void log(String msg){
		isSuccess = true;
		if(isSuccess){
			System.out.println(getClass() + " : " + msg);
		}
	}
	public void log(String msg, Exception e){
		isSuccess = true;
		if(isSuccess){
			System.out.println(e + " : " + getClass() + " : " + msg);
		}
	}
}
