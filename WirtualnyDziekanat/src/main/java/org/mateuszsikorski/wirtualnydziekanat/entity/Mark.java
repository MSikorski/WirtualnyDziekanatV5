package org.mateuszsikorski.wirtualnydziekanat.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="marks")
public class Mark {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="student_detail_id")
	private StudentDetail studentDetail;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="teacher_detail_id")
	private TeacherDetail teacherDetail;
	
	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
					CascadeType.REFRESH})
	@JoinColumn(name="subject_id")
	private Subject subject;
	
	@Column(name="value")
	private String value;
	
	@Column(name="creation_time")
	private String creationTime;
	
	@Column(name="eddition_time")
	private String edditionTime;
	
	public Mark() {}

	public StudentDetail getStudentDetail() {
		return studentDetail;
	}

	public void setStudentDetail(StudentDetail studentDetail) {
		this.studentDetail = studentDetail;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(String creationTime) {
		this.creationTime = creationTime;
	}

	public String getEdditionTime() {
		return edditionTime;
	}

	public void setEdditionTime(String edditionTime) {
		this.edditionTime = edditionTime;
	}

	public int getId() {
		return id;
	}

	public TeacherDetail getTeacherDetail() {
		return teacherDetail;
	}

	public void setTeacherDetail(TeacherDetail teacherDetail) {
		this.teacherDetail = teacherDetail;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	@Override
	public String toString() {
		return "Mark [id=" + id + ", studentDetail=" + studentDetail + ", teacherDetail=" + teacherDetail + ", value="
				+ value + ", creationTime=" + creationTime + ", edditionTime=" + edditionTime + "]";
	}
	
}
