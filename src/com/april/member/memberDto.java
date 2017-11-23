package com.april.member;

import java.io.Serializable;

public class memberDto {
	private String id;
	private String name;
	private String pw;
	private String email;
	private int authority;
	
	public memberDto(){}
	
	public memberDto(String id, String name, String pw, String email, int authority) {
		this.id = id;
		this.name = name;
		this.pw = pw;
		this.email = email;
		this.authority = authority;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getAuthority() {
		return authority;
	}
	public void setAuthority(int authority) {
		this.authority = authority;
	}

	@Override
	public String toString() {
		return "memberDto [id=" + id + ", name=" + name + ", pw=" + pw + ", email=" + email + ", authority=" + authority
				+ "]";
	}
}
