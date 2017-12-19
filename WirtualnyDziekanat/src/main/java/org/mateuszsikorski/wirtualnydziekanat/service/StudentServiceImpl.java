package org.mateuszsikorski.wirtualnydziekanat.service;

import java.util.List;

import org.mateuszsikorski.wirtualnydziekanat.dao.interfaces.StudentDAO;
import org.mateuszsikorski.wirtualnydziekanat.entity.Mark;
import org.mateuszsikorski.wirtualnydziekanat.entity.StudentDetail;
import org.mateuszsikorski.wirtualnydziekanat.entity.StudentGroup;
import org.mateuszsikorski.wirtualnydziekanat.entity.Subject;
import org.mateuszsikorski.wirtualnydziekanat.entity.TimeTable;
import org.mateuszsikorski.wirtualnydziekanat.entity.User;
import org.mateuszsikorski.wirtualnydziekanat.service.interfaces.StudentService;
import org.mateuszsikorski.wirtualnydziekanat.entity.UserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	StudentDAO studentDAO;
	
	@Override
	@Transactional
	public List<Subject> getUserSubjects(User user, UserDetail userDetail) {
		return studentDAO.getUserSubjects(user);
	}
	
	@Override
	@Transactional
	public List<Mark> getMarks(User user, UserDetail userDetail){
		
		return null;
	}

	@Override
	@Transactional
	public TimeTable getTimeTable(User user, UserDetail userDetail) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	@Transactional
	public List<StudentGroup> getStudentGroupList(UserDetail userDetail) {
		return studentDAO.getStudentGroupList();
	}

	@Override
	@Transactional
	public void saveStudentGroup(StudentGroup studentGroup, org.mateuszsikorski.wirtualnydziekanat.entity.UserDetail userDetail) {
		studentDAO.saveStudentGroup(studentGroup);
	}
	
	@Override
	@Transactional
	public void saveTimeTable(TimeTable timeTable, UserDetail userDetail) {
		studentDAO.saveTimeTable(timeTable);
	}

	@Override
	@Transactional
	public StudentGroup getStudentGroup(int id, UserDetail userDetail) {
		StudentGroup studentGroup = studentDAO.getStudentGroup(id);
		List<StudentDetail> studentList = studentDAO.getStudentListWithGrupId(id);
		
		if(studentList.isEmpty()) {
			System.out.println("StudentGroup with id " + id + " is empty");
		}
		
		studentGroup.setStudentDetailList(studentList);
		
		return studentGroup;
	}

	@Override
	@Transactional
	public List<StudentDetail> getStudentListWithoutGroup(UserDetail userDetail) {
		return studentDAO.getStudentListWithoutGroup();
	}

	@Override
	@Transactional
	public void saveStudent(StudentDetail studentDetail, UserDetail userDetail) {
		studentDAO.saveStudent(studentDetail);
	}

	@Override
	@Transactional
	public List<Subject> getSubjectList(UserDetail userDetail) {
		return studentDAO.getSubjectList();
	}

	@Override
	@Transactional
	public void saveSubject(Subject subject, UserDetail userDetail) {
		studentDAO.saveSubject(subject);
	}

	@Override
	@Transactional
	public Subject getSubject(int subjectId, UserDetail userDetail) {
		return studentDAO.getSubject(subjectId);
	}

	@Override
	@Transactional
	public List<StudentGroup> getStudentGroupListWithSubject(int subjectId, UserDetail userDetail) {
		return studentDAO.getStudentGroupListWithSubject(subjectId);
	}

	@Override
	@Transactional
	public List<StudentGroup> getStudentGroupListWithoutSubject(int subjectId, UserDetail userDetail) {
		return studentDAO.getStudentGroupListWithoutSubject(subjectId);
	}

	@Override
	@Transactional
	public TimeTable getTimeTableByStudentGroupId(int studentGroupId, UserDetail userDetail) {
		return studentDAO.getTimeTableByStudentGroupId(studentGroupId);
	}

	@Override
	@Transactional
	public List<StudentDetail> getStudentListWithGroupId(int groupId, UserDetail userDetail) {
		return studentDAO.getStudentListWithGrupId(groupId);
	}

}
