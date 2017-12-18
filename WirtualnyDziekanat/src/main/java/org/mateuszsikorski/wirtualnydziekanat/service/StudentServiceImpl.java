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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	StudentDAO studentDAO;
	
	@Override
	@Transactional
	public List<Subject> getUserSubjects(User user) {
		return studentDAO.getUserSubjects(user);
	}
	
	@Override
	@Transactional
	public List<Mark> getMarks(User user){
		
		return null;
	}

	@Override
	@Transactional
	public TimeTable getTimeTable(User user) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	@Transactional
	public List<StudentGroup> getStudentGroupList() {
		return studentDAO.getStudentGroupList();
	}

	@Override
	@Transactional
	public void saveStudentGroup(StudentGroup studentGroup) {
		studentDAO.saveStudentGroup(studentGroup);
	}
	
	@Override
	@Transactional
	public void saveTimeTable(TimeTable timeTable) {
		studentDAO.saveTimeTable(timeTable);
	}

	@Override
	@Transactional
	public StudentGroup getStudentGroup(int id) {
		return studentDAO.getStudentGroup(id);
	}

	@Override
	@Transactional
	public List<StudentDetail> getStudentListWithoutGroup() {
		return studentDAO.getStudentListWithoutGroup();
	}

	@Override
	@Transactional
	public void saveStudent(StudentDetail studentDetail) {
		studentDAO.saveStudent(studentDetail);
	}

	@Override
	@Transactional
	public List<Subject> getSubjectList() {
		return studentDAO.getSubjectList();
	}

	@Override
	@Transactional
	public void saveSubject(Subject subject) {
		studentDAO.saveSubject(subject);
	}

	@Override
	@Transactional
	public Subject getSubject(int subjectId) {
		return studentDAO.getSubject(subjectId);
	}

	@Override
	@Transactional
	public List<StudentGroup> getStudentGroupListWithSubject(int subjectId) {
		return studentDAO.getStudentGroupListWithSubject(subjectId);
	}

	@Override
	@Transactional
	public List<StudentGroup> getStudentGroupListWithoutSubject(int subjectId) {
		return studentDAO.getStudentGroupListWithoutSubject(subjectId);
	}

	@Override
	@Transactional
	public TimeTable getTimeTableByStudentGroupId(int studentGroupId) {
		return studentDAO.getTimeTableByStudentGroupId(studentGroupId);
	}

	@Override
	@Transactional
	public List<StudentDetail> getStudentListWithGroupId(int groupId) {
		return studentDAO.getStudentListWithGrupId(groupId);
	}

}
