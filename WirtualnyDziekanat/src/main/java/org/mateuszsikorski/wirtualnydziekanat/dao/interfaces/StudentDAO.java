package org.mateuszsikorski.wirtualnydziekanat.dao.interfaces;

import java.util.List;

import org.mateuszsikorski.wirtualnydziekanat.entity.StudentDetail;
import org.mateuszsikorski.wirtualnydziekanat.entity.StudentGroup;
import org.mateuszsikorski.wirtualnydziekanat.entity.Subject;
import org.mateuszsikorski.wirtualnydziekanat.entity.TimeTable;
import org.mateuszsikorski.wirtualnydziekanat.entity.User;

public interface StudentDAO {

	List<Subject> getUserSubjects(User user);

	List<StudentGroup> getStudentGroupList();

	void saveStudentGroup(StudentGroup studentGroup);

	void saveTimeTable(TimeTable timeTable);

	StudentGroup getStudentGroup(int id);

	List<StudentDetail> getStudentListWithoutGroup();
	
	List<StudentDetail> getStudentListWithGrupId(int groupId);

	void saveStudent(StudentDetail studentDetail);

	List<Subject> getSubjectList();

	void saveSubject(Subject subject);

	Subject getSubject(int subjectId);

	List<StudentGroup> getStudentGroupListWithSubject(int subjectId);

	List<StudentGroup> getStudentGroupListWithoutSubject(int subjectId);

	TimeTable getTimeTableByStudentGroupId(int studentGroupId);

}
