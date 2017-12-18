package org.mateuszsikorski.wirtualnydziekanat.model;

import java.util.ArrayList;
import java.util.List;

import org.mateuszsikorski.wirtualnydziekanat.entity.StudentDetail;

public class UpdatingStudentGroupHelpFrom {

	private List<StudentDetail> studentList;
	
	private List<StudentDetail> studentListWithoutGroup;
	
	private MultipleCheckBoxes addCB;
	
	private MultipleCheckBoxes removeCB;
	
	private int studentGroupId;
	
	public UpdatingStudentGroupHelpFrom() {
		addCB = new MultipleCheckBoxes();
		removeCB = new MultipleCheckBoxes();
		studentList = new ArrayList();
		studentListWithoutGroup = new ArrayList();
	}
	
	public int getStudentGroupId() {
		return studentGroupId;
	}

	public void setStudentGroupId(int studentGroupId) {
		this.studentGroupId = studentGroupId;
	}

	public List<StudentDetail> getStudentList() {
		return studentList;
	}

	public void setStudentList(List<StudentDetail> studentList) {
		this.studentList = studentList;
	}

	public List<StudentDetail> getStudentListWithoutGroup() {
		return studentListWithoutGroup;
	}

	public void setStudentListWithoutGroup(List<StudentDetail> studentListWithoutGroup) {
		this.studentListWithoutGroup = studentListWithoutGroup;
	}

	public MultipleCheckBoxes getAddCB() {
		return addCB;
	}

	public void setAddCB(MultipleCheckBoxes addCB) {
		this.addCB = addCB;
	}

	public MultipleCheckBoxes getRemoveCB() {
		return removeCB;
	}

	public void setRemoveCB(MultipleCheckBoxes removeCB) {
		this.removeCB = removeCB;
	}
	
	
	
}
