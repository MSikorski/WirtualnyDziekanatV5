package org.mateuszsikorski.wirtualnydziekanat.entity;

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

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="user_detail")
public class UserDetail {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	//@Email(message = "Prosze podac prawdilowy adres email")
	@Column(name="email")
	private String email;
	
	@NotEmpty(message = "Prosze podac imie")
	@Column(name="first_name")
	private String firstName;
	
	@NotEmpty(message = "Prosze podac nazwisko")
	@Column(name="last_name")
	private String lastName;
	
	//@TelephoneNumber
	@Column(name="telephone_number")
	private String telephoneNumber;
	
	@OneToOne(cascade=CascadeType.ALL, mappedBy="userDetail")
	private User user;
	
	@OneToOne(cascade=CascadeType.ALL,
			fetch = FetchType.EAGER)
	@JoinColumn(name="student_detail_id")
	private StudentDetail studentDetail;
	
	@OneToOne(cascade=CascadeType.ALL,
			fetch=FetchType.EAGER)
	@JoinColumn(name="admin_detail_id")
	private AdminDetail adminDetail;
	
	@OneToOne(cascade=CascadeType.ALL,
			fetch=FetchType.EAGER)
	@JoinColumn(name="teacher_detail_id")
	private TeacherDetail teacherDetail;
	
	public UserDetail() {
		this.email = "Email@host.com";
		this.firstName = "Imie";
		this.lastName  = "Nazwisko";
		this.telephoneNumber = "Numer telefonu";
	}
	
	public UserDetail(User user) {
		this.email = "Email@host.com";
		this.firstName = "Imie";
		this.lastName  = "Nazwisko";
		this.telephoneNumber = "Numer telefonu";
		this.user = user;
	}

	public UserDetail(String email, String firstName, String lastName, String telephoneNumber) {
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.telephoneNumber = telephoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getTelephoneNumber() {
		return telephoneNumber;
	}

	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	public int getId() {
		return id;
	}

	public StudentDetail getStudentDetail() {
		return studentDetail;
	}

	public void setStudentDetail(StudentDetail studentDetail) {
		this.studentDetail = studentDetail;
	}

	public AdminDetail getAdminDetail() {
		return adminDetail;
	}

	public void setAdminDetail(AdminDetail adminDetail) {
		this.adminDetail = adminDetail;
	}

	public TeacherDetail getTeacherDetail() {
		return teacherDetail;
	}

	public void setTeacherDetail(TeacherDetail teacherDetail) {
		this.teacherDetail = teacherDetail;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "UserDetail [id=" + id + ", email=" + email + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", telephoneNumber=" + telephoneNumber + "]";
	}
	
}