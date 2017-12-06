package com.april.member;

public interface InterfMemberDao {
	boolean addMember(MemberDto member);
	MemberDto login(MemberDto member);
}
