package org.mateuszsikorski.wirtualnydziekanat.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="student_detail")
public class StudentDetail {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="index_number")
	private String indexNumber;
	
	@Column(name="specialization")
	private String specialization;
	
	@OneToOne(mappedBy="studentDetail",
			fetch = FetchType.EAGER,
			cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
					CascadeType.REFRESH})
	private UserDetail userDetail;
	
	@OneToMany(mappedBy="studentDetail",
			fetch = FetchType.LAZY,
			cascade = {CascadeType.REFRESH, CascadeType.MERGE, 
			CascadeType.PERSIST, CascadeType.REFRESH})
	private List <Mark> marks;
	
	@ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
					CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name="student_group_id")
	private StudentGroup studentGroup;

	public StudentDetail(String indexNumber) {
		this.indexNumber = indexNumber;
	}
	
	public StudentDetail(UserDetail userDetail){
		this.userDetail = userDetail;
	}
	
	public StudentDetail() {}

	public String getIndexNumber() {
		return indexNumber;
	}

	public void setIndexNumber(String indexNumber) {
		this.indexNumber = indexNumber;
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	public UserDetail getUserDetail() {
		return userDetail;
	}

	public void setUserDetail(UserDetail userDetail) {
		this.userDetail = userDetail;
	}

	public List<Mark> getMarks() {
		return marks;
	}

	public void setMarks(List<Mark> marks) {
		this.marks = marks;
	}

	public int getId() {
		return id;
	}

	public StudentGroup getStudentGroup() {
		return studentGroup;
	}

	public void setStudentGroup(StudentGroup studentGroup) {
		this.studentGroup = studentGroup;
	}

	@Override
	public String toString() {
		return "StudentDetail [id=" + id + ", indexNumber=" + indexNumber + ", specialization=" + specialization
				+ ", user=" + userDetail + ", marks=" + marks + ", studentGroup=" + studentGroup + "]";
	}

}