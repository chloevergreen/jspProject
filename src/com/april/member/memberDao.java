package com.april.member;

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
	
	//디비 연결 메소드
	
	//디비 연결 종료하는 메서드
	
	//디비 닫는 메소드
	
	@Override
	public boolean addMember(memberDto member) {
		//sql작성
		return false;
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
