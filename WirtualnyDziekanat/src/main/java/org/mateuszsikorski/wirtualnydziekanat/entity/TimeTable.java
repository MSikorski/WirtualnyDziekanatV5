package org.mateuszsikorski.wirtualnydziekanat.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "time_table")
public class TimeTable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@OneToOne(cascade = CascadeType.ALL, 
			fetch = FetchType.LAZY)
	@JoinColumn(name = "student_group_id")
	private StudentGroup studentGroup;

	@OneToMany(mappedBy = "timeTable", cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH,
			CascadeType.REFRESH },
			fetch=FetchType.LAZY)
	private List<Subject> subjects;

	@Transient
	private Subject[][] subjectTime;

	private void processTimeTable() {
		for (Subject tempSubject : subjects) {

			// Time format DNR
			// D <0,5> states for day
			// NR <0,9> states for number of lesson counting from first which is at 8:00
			int time = Integer.parseInt(tempSubject.getTime());
			int hour = time % 10;
			int day = (time - hour) / 10;

			subjectTime[day][hour] = tempSubject;
		}
	}
	
	public TimeTable() {
	}
	
	public TimeTable(StudentGroup studentGroup) {
		this.studentGroup = studentGroup;
	}

	public Subject[][] getSubjectTimeTable() {
		processTimeTable();
		return subjectTime;
	}

	public StudentGroup getStudentGroup() {
		return studentGroup;
	}

	public void setStudentGroup(StudentGroup studentGroup) {
		this.studentGroup = studentGroup;
	}

	public int getId() {
		return id;
	}
	
	public List<Subject> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<Subject> subjects) {
		this.subjects = subjects;
	}

	@Override
	public String toString() {
		return "TimeTable [id=" + id + ", studentGroup=" + studentGroup + ", subjects=" + subjects + ", subjectTime="
				+ Arrays.toString(subjectTime) + "]";
	}

}