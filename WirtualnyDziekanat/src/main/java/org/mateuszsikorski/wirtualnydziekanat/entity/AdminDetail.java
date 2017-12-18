package org.mateuszsikorski.wirtualnydziekanat.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="admin_detail")
public class AdminDetail {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="flags")
	private String flags;
	
	@OneToOne(mappedBy="adminDetail", cascade=CascadeType.ALL)
	private UserDetail userDetail;
	
	public AdminDetail() {
	}
	
	public AdminDetail(UserDetail userDetail) {
		this.userDetail = userDetail;
	}

	public String getFlags() {
		return flags;
	}

	public void setFlags(String flags) {
		this.flags = flags;
	}

	public UserDetail getUserDetail() {
		return userDetail;
	}

	public void setUser(UserDetail user) {
		this.userDetail = user;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "AdminDetail [id=" + id + ", flags=" + flags + ", user=" + userDetail + "]";
	}
	
}
