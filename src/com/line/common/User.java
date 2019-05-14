package com.line.common;
/*
 * 用戶信息
 * */
public class User implements java.io.Serializable{
	private String userId;
	private String userPasswd;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPasswd() {
		return userPasswd;
	}
	public void setUserPasswd(String userPasswd) {
		this.userPasswd = userPasswd;
	}
	
	
}
