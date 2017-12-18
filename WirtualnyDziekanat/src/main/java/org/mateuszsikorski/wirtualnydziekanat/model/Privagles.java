package org.mateuszsikorski.wirtualnydziekanat.model;

import org.mateuszsikorski.wirtualnydziekanat.entity.AdminDetail;
import org.mateuszsikorski.wirtualnydziekanat.entity.StudentDetail;
import org.mateuszsikorski.wirtualnydziekanat.entity.TeacherDetail;
import org.mateuszsikorski.wirtualnydziekanat.entity.UserDetail;

public class Privagles {

	private boolean studentPrivagles;
	private boolean teacherPrivagles;
	private boolean adminPrivagles;
	
	private int id;
	
	public Privagles() {
		studentPrivagles = true;
		teacherPrivagles = true;
		adminPrivagles = true;
	}
	
	public UserDetail Validate(UserDetail userDetail) {
		
		if(studentPrivagles && userDetail.getStudentDetail() == null)
			userDetail.setStudentDetail(new StudentDetail(userDetail));
		else if(!studentPrivagles && !(userDetail.getStudentDetail() == null))
			userDetail.setStudentDetail(null);
		
		if(teacherPrivagles && userDetail.getTeacherDetail() == null)
			userDetail.setTeacherDetail(new TeacherDetail(userDetail));
		else if(!teacherPrivagles && !(userDetail.getTeacherDetail() == null))
			userDetail.setTeacherDetail(null);
		
		if(adminPrivagles && userDetail.getAdminDetail() == null)
			userDetail.setAdminDetail(new AdminDetail(userDetail));
		else if(!adminPrivagles && !(userDetail.getAdminDetail() == null))
			userDetail.setAdminDetail(null);
		
		return userDetail;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isStudentPrivagles() {
		return studentPrivagles;
	}

	public void setStudentPrivagles(boolean studentPrivagles) {
		this.studentPrivagles = studentPrivagles;
	}

	public boolean isTeacherPrivagles() {
		return teacherPrivagles;
	}

	public void setTeacherPrivagles(boolean teacherPrivagles) {
		this.teacherPrivagles = teacherPrivagles;
	}

	public boolean isAdminPrivagles() {
		return adminPrivagles;
	}

	public void setAdminPrivagles(boolean adminPrivagles) {
		this.adminPrivagles = adminPrivagles;
	}
	
}
