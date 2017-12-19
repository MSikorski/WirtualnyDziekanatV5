package org.mateuszsikorski.wirtualnydziekanat.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
@Component
@Entity
@Table(name="user")
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Size(min = 5, max = 15, message = "Nazwa uzytkownika powinna zawierac od 5 do 15 znakow")
	@Column(name="user_name")
	private String userName;
	
	@Column(name="password")
	private String password;
	
	@OneToOne(cascade=CascadeType.ALL,
			fetch=FetchType.EAGER)
	@JoinColumn(name="user_detail_id")
	private UserDetail userDetail;
	
	@Transient
	List<SimpleGrantedAuthority> grantedAuthorities;
	
	@Transient
	@Size(min = 5, max = 25, message = "Haslo powinno zawierac od 5 do 25 znakow")
	private String tempPass;
	
	public void processAuthorities() {
		
		grantedAuthorities = new ArrayList();
		
		grantedAuthorities.add(new SimpleGrantedAuthority("USER"));
		
		if(userDetail.getAdminDetail().getId() > 0) {
			grantedAuthorities.add(
					new SimpleGrantedAuthority("ADMIN"));
		}
		
		if(userDetail.getTeacherDetail().getId() > 0) {
			grantedAuthorities.add(
					new SimpleGrantedAuthority("TEACHER"));
		}
		
		if(userDetail.getAdminDetail().getId() > 0) {
			grantedAuthorities.add(
					new SimpleGrantedAuthority("STUDENT"));
		}

	}
	
	public void pasteProporties(User tempUser) {
		
		UserDetail tempUserDetail = tempUser.getUserDetail();
		
		if(userDetail.getFirstName() != tempUserDetail.getFirstName())
			userDetail.setFirstName(tempUserDetail.getFirstName());
		
		if(userDetail.getLastName() != tempUserDetail.getLastName())
			userDetail.setLastName(tempUserDetail.getLastName());
		
		if(userDetail.getEmail() != tempUserDetail.getEmail())
			userDetail.setEmail(tempUserDetail.getEmail());
		
		if(userDetail.getTelephoneNumber() != tempUserDetail.getTelephoneNumber())
			userDetail.setTelephoneNumber(tempUserDetail.getTelephoneNumber());
	}
	
	public User() { 
		this.userName = "Niezarejestrowany";
		this.userDetail = new UserDetail(this);
	}

	public User(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserDetail getUserDetail() {
		return userDetail;
	}

	public void setUserDetail(UserDetail userDetail) {
		this.userDetail = userDetail;
	}

	public int getId() {
		return id;
	}
	
	public String getTempPass() {
		return tempPass;
	}

	public void setTempPass(String tempPass) {
		this.tempPass = tempPass;
	}

	public List<SimpleGrantedAuthority> getGrantedAuthorities() {
		return grantedAuthorities;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", password=" + password + "\nuserDetail=" + userDetail
				+ "]";
	}
	
}
