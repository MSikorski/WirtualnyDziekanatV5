package org.mateuszsikorski.wirtualnydziekanat.service.interfaces;

import java.util.List;

import org.mateuszsikorski.wirtualnydziekanat.entity.Mark;
import org.mateuszsikorski.wirtualnydziekanat.entity.StudentDetail;
import org.mateuszsikorski.wirtualnydziekanat.entity.StudentGroup;
import org.mateuszsikorski.wirtualnydziekanat.entity.Subject;
import org.mateuszsikorski.wirtualnydziekanat.entity.TimeTable;
import org.mateuszsikorski.wirtualnydziekanat.entity.User;
import org.mateuszsikorski.wirtualnydziekanat.entity.UserDetail;

public interface StudentService {
	
	public TimeTable getTimeTable(User user, UserDetail userDetail);

	public List<Mark> getMarks(User user, UserDetail userDetail);

	public List<StudentGroup> getStudentGroupList(UserDetail userDetail);

	public void saveStudentGroup(StudentGroup studentGroup, UserDetail userDetail);

	public void saveTimeTable(TimeTable timeTable, UserDetail userDetail);

	public StudentGroup getStudentGroup(int id, UserDetail userDetail);

	public List<StudentDetail> getStudentListWithoutGroup(UserDetail userDetail);

	public void saveStudent(StudentDetail tempStudent, UserDetail userDetail);

	public List<Subject> getSubjectList(UserDetail userDetail);

	public List<Subject> getUserSubjects(User user, UserDetail userDetail);

	public void saveSubject(Subject subject, UserDetail userDetail);

	public Subject getSubject(int subjectId, UserDetail userDetail);

	public List<StudentGroup> getStudentGroupListWithSubject(int subjectId, UserDetail userDetail);

	public List<StudentGroup> getStudentGroupListWithoutSubject(int id, UserDetail userDetail);

	public TimeTable getTimeTableByStudentGroupId(int addedGroupId, UserDetail userDetail);

	public List<StudentDetail> getStudentListWithGroupId(int id, UserDetail userDetail);
}
