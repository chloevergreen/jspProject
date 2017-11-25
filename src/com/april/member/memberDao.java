package com.april.member;

public class memberDao implements imemberDao {
	private boolean isSuccess = false;
	private memberDao memberDao;
	
	//생성자에 ojdbc
	
	//상굹톤 dao
	
	//디비 연결 메소드
	
	//디비 닫는 메소드
	
	//추가 멤버 오버라이드
	//sql작성
	@Override
	public boolean addMember(memberDto member) {
		return false;
	}

	@Override
	public memberDto login(memberDto member) {
		return null;
	}
	
	//로그
}
