package org.mateuszsikorski.wirtualnydziekanat.service.interfaces;

import java.util.List;

import org.mateuszsikorski.wirtualnydziekanat.entity.Mark;
import org.mateuszsikorski.wirtualnydziekanat.entity.StudentDetail;
import org.mateuszsikorski.wirtualnydziekanat.entity.StudentGroup;
import org.mateuszsikorski.wirtualnydziekanat.entity.Subject;
import org.mateuszsikorski.wirtualnydziekanat.entity.TimeTable;
import org.mateuszsikorski.wirtualnydziekanat.entity.User;

public interface StudentService {
	
	public TimeTable getTimeTable(User user);

	public List<Mark> getMarks(User user);

	List<StudentGroup> getStudentGroupList();

	public void saveStudentGroup(StudentGroup studentGroup);

	void saveTimeTable(TimeTable timeTable);

	public StudentGroup getStudentGroup(int id);

	public List<StudentDetail> getStudentListWithoutGroup();

	public void saveStudent(StudentDetail tempStudent);

	public List<Subject> getSubjectList();

	List<Subject> getUserSubjects(User user);

	public void saveSubject(Subject subject);

	public Subject getSubject(int subjectId);

	public List<StudentGroup> getStudentGroupListWithSubject(int subjectId);

	public List<StudentGroup> getStudentGroupListWithoutSubject(int id);

	public TimeTable getTimeTableByStudentGroupId(int addedGroupId);

	public List<StudentDetail> getStudentListWithGroupId(int id);
}
