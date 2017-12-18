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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="teacher_detail")
public class TeacherDetail {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="test")
	private int test;
	
	@OneToOne(mappedBy="teacherDetail",
			cascade= {CascadeType.DETACH, CascadeType.MERGE,
					CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.REMOVE})
	private UserDetail userDetail;
	
	@OneToMany(mappedBy="teacherDetail", fetch=FetchType.LAZY,
			cascade = {CascadeType.PERSIST, CascadeType.MERGE,
					CascadeType.DETACH, CascadeType.REFRESH})
	private List<Mark> marks;
	
	@ManyToMany(fetch=FetchType.LAZY,
			cascade={CascadeType.PERSIST, CascadeType.MERGE,
					CascadeType.DETACH, CascadeType.REFRESH})
	@JoinTable(name = "subject_teacher", 
				joinColumns = @JoinColumn(name = "teacher_detail_id"), 
				inverseJoinColumns = @JoinColumn(name = "subject_id"))
	private List<Subject> subjectsList;
	
	public int getTest() {
		return test;
	}

	public void setTest(int test) {
		this.test = test;
	}

	public TeacherDetail(UserDetail userDetail) {
		this.userDetail = userDetail;
	}
	
	public TeacherDetail() { 
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

	public List<Subject> getSubjectsList() {
		return subjectsList;
	}

	public void setSubjectsList(List<Subject> subjectsList) {
		this.subjectsList = subjectsList;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "TeacherDetail [id=" + id + ", userDetail=" + userDetail + ", marks=" + marks + ", subjectsList="
				+ subjectsList + "]";
	}

}