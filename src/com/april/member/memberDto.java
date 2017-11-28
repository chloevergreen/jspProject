package com.april.member;

import java.io.Serializable;

public class memberDto {
	private static final long serialVersionUID = 1L;
	
	private String id_;
	private String name_;
	private transient String pw_;
	private String email_;
	private int authority_;
	
	public memberDto(){}
	
	public memberDto(String id, String name, String pw, String email, int authority) {
		this.id_ = id;
		this.name_ = name;
		this.pw_ = pw;
		this.email_ = email;
		this.authority_ = authority;
	}

	public String getId() {
		return id_;
	}
	public void setId(String id) {
		this.id_ = id;
	}
	public String getName() {
		return name_;
	}
	public void setName(String name) {
		this.name_ = name;
	}
	public String getPw() {
		return pw_;
	}
	public void setPw(String pw) {
		this.pw_ = pw;
	}
	public String getEmail() {
		return email_;
	}
	public void setEmail(String email) {
		this.email_ = email;
	}
	public int getAuthority() {
		return authority_;
	}
	public void setAuthority(int authority) {
		this.authority_ = authority;
	}

	@Override
	public String toString() {
		return "memberDto [id=" + id_ + ", name=" + name_ + ", pw=" + pw_ + ", email=" + email_ + ", authority=" + authority_
				+ "]";
	}
}
