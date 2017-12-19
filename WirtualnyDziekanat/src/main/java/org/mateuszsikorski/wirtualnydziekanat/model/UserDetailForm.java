package org.mateuszsikorski.wirtualnydziekanat.model;

import org.mateuszsikorski.wirtualnydziekanat.entity.User;

public class UserDetailForm {

	private User user;
	private Privagles privagles;
	
	public UserDetailForm() {} 
	
	public UserDetailForm (User user) {
		this.user = user;
		this.privagles = new Privagles();
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Privagles getPrivagles() {
		return privagles;
	}
	public void setPrivagles(Privagles privagles) {
		this.privagles = privagles;
	}
	
	
}
