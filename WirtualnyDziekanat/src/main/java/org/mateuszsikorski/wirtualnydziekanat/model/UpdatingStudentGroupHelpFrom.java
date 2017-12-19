package org.mateuszsikorski.wirtualnydziekanat.model;

import java.util.List;

import org.mateuszsikorski.wirtualnydziekanat.entity.StudentDetail;
import org.springframework.stereotype.Component;

@Component
public class UpdatingStudentGroupHelpFrom {

	private List<StudentDetail> studentList;
	
	private List<StudentDetail> studentListWithoutGroup;
	
	private MultipleCheckBoxes addCB;
	
	private MultipleCheckBoxes removeCB;
	
	private Integer studentGroupId;
	
	public UpdatingStudentGroupHelpFrom() {}

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

	@Override
	public String toString() {
		return "UpdatingStudentGroupHelpFrom [studentList=" + studentList + ", studentListWithoutGroup="
				+ studentListWithoutGroup + ", addCB=" + addCB + ", removeCB=" + removeCB + ", studentGroupId="
				+ studentGroupId + "]";
	}
	
	
}
