package com.april.member;

public interface imemberDao {
	boolean addMember(memberDto member);
	memberDto login(memberDto member);
}
