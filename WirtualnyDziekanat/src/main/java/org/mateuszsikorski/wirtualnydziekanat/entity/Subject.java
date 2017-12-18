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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "subject")
public class Subject {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "name")
	private String name;

	@Column(name = "detail")
	private String detail;

	@Column(name = "time")
	private String time;

	@OneToMany(mappedBy = "subject", fetch = FetchType.LAZY, 
				cascade = { CascadeType.PERSIST, CascadeType.MERGE,
				CascadeType.DETACH, CascadeType.REFRESH })
	private List<Mark> marks;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "time_table_id")
	private TimeTable timeTable;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE,
				CascadeType.DETACH, CascadeType.REFRESH })
	@JoinTable(name = "subject_teacher", 
				joinColumns = @JoinColumn(name = "subject_id"), 
				inverseJoinColumns = @JoinColumn(name = "teacher_detail_id"))
	private List<TeacherDetail> teachers;

	public Subject() {
	}

	public Subject(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public void setId(int id) {
		this.id = id;
	}

	public TimeTable getTimeTable() {
		return timeTable;
	}

	public void setTimeTable(TimeTable timeTable) {
		this.timeTable = timeTable;
	}

	public List<Mark> getMarks() {
		return marks;
	}

	public void setMarks(List<Mark> marks) {
		this.marks = marks;
	}

	public List<TeacherDetail> getTeachers() {
		return teachers;
	}

	public void setTeachers(List<TeacherDetail> teachers) {
		this.teachers = teachers;
	}

	@Override
	public String toString() {
		return "Subject [id=" + id + ", name=" + name + ", detail=" + detail + ", time=" + time + ", timeTable="
				+ timeTable + ", teachers=" + teachers + "]";
	}

}
