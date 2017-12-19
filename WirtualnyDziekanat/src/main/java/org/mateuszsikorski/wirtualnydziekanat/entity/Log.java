package org.mateuszsikorski.wirtualnydziekanat.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="log")
public class Log {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH},
			fetch=FetchType.EAGER)
	@JoinColumn(name="user_detail_id")
	private UserDetail userDetail;
	
	@Column(name="type")
	private String type;
	
	@Column(name="detail")
	private String detail;
	
	public Log () {}

	public Log(UserDetail userDetail, String type, String detail) {
		this.userDetail = userDetail;
		this.type = type;
		this.detail = detail;
	}

	public UserDetail getUserDetail() {
		return userDetail;
	}

	public void setUserDetail(UserDetail userDetail) {
		this.userDetail = userDetail;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Log [id=" + id + ", userDetail=" + userDetail + ", type=" + type + ", detail=" + detail + "]";
	}
}
