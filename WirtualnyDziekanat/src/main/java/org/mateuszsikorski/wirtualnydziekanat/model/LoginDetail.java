package org.mateuszsikorski.wirtualnydziekanat.model;

public class LoginDetail {

	private String user;
	private String pass;
	
	public LoginDetail() {
	}
	
	public LoginDetail(String userName, String password) {
		this.user = userName;
		this.pass = password;
	}

	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}

	@Override
	public String toString() {
		return "LoginDetail [user=" + user + ", pass=" + pass + "]";
	}
	
	
}
