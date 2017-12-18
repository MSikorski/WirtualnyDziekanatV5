package org.mateuszsikorski.wirtualnydziekanat.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.mateuszsikorski.wirtualnydziekanat.dao.interfaces.StudentDAO;
import org.mateuszsikorski.wirtualnydziekanat.entity.StudentDetail;
import org.mateuszsikorski.wirtualnydziekanat.entity.StudentGroup;
import org.mateuszsikorski.wirtualnydziekanat.entity.Subject;
import org.mateuszsikorski.wirtualnydziekanat.entity.TimeTable;
import org.mateuszsikorski.wirtualnydziekanat.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class StudentDAOImpl implements StudentDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Subject> getUserSubjects(User user){
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		List<Subject> tempList;
		StudentGroup studentGroup = user.getUserDetail().getStudentDetail().getStudentGroup();
		int timeTableId = studentGroup.getTimeTable().getId();
		String hql = "FROM Subject s WHERE s.timeTableId=" + timeTableId;
		System.out.println(hql);
		
		Query query = currentSession.createQuery(hql);
		
		try{
			tempList = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		return tempList;
	}

	@Override
	public List<StudentGroup> getStudentGroupList() {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		List<StudentGroup> tempList;
		String hql = "FROM StudentGroup";
		Query query = currentSession.createQuery(hql);
		
		try{
			tempList = query.getResultList();
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		
		return tempList;
	}

	@Override
	public void saveStudentGroup(StudentGroup studentGroup) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		currentSession.saveOrUpdate(studentGroup);
	}

	@Override
	public void saveTimeTable(TimeTable timeTable) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		currentSession.saveOrUpdate(timeTable);
	}

	@Override
	public StudentGroup getStudentGroup(int id) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		StudentGroup studentGroup = currentSession.get(StudentGroup.class, id);
		
		return studentGroup;
	}

	@Override
	public List<StudentDetail> getStudentListWithoutGroup() {

		Session currentSession = sessionFactory.getCurrentSession();
		
		String hql = "FROM StudentDetail s WHERE s.studentGroup IS NULL";
		
		Query query = currentSession.createQuery(hql);
		
		List<StudentDetail> temp;
		
		try {
			temp = query.getResultList();
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		
		return temp;
	}

	@Override
	public void saveStudent(StudentDetail studentDetail) {
			
		Session currentSession = sessionFactory.getCurrentSession();
		
		currentSession.saveOrUpdate(studentDetail);
	}

	@Override
	public List<Subject> getSubjectList() {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		String hql = "FROM Subject";
		
		Query query = currentSession.createQuery(hql);
		
		List<Subject> subjects = query.getResultList();
		
		return subjects;
	}

	@Override
	public void saveSubject(Subject subject) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		currentSession.save(subject);
	}

	@Override
	public Subject getSubject(int subjectId) {

		Session currentSession = sessionFactory.getCurrentSession();
		
		Subject subject = currentSession.get(Subject.class, subjectId);
		
		return subject;
	}

	@Override
	public List<StudentGroup> getStudentGroupListWithSubject(int subjectId) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		String hql = "SELECT s.timeTable FROM Subject s WHERE s.id=" + subjectId;
		
		Query query = currentSession.createQuery(hql);
		
		List<TimeTable> timeTableList = query.getResultList();
		
		List<StudentGroup> studentGroupList = new ArrayList();
		
		for(TimeTable temp : timeTableList) {
			hql = "SELECT t.studentGroup FROM TimeTable t WHERE t.id=" + temp.getId();
			query = currentSession.createQuery(hql);
			StudentGroup tempStudentGroup = (StudentGroup) query.getSingleResult();
			studentGroupList.add(tempStudentGroup);
		}
		
		return studentGroupList;
	}

	@Override
	public List<StudentGroup> getStudentGroupListWithoutSubject(int subjectId) {
		
		List<StudentGroup> studentGroupListWithSubject = getStudentGroupListWithSubject(subjectId);
		List<StudentGroup> studentGroupListWithoutSubject = getStudentGroupList();
		
		for(StudentGroup tempStudentGroupWithSubject : studentGroupListWithSubject) {
			for(int i=0; i < studentGroupListWithoutSubject.size(); i++) {
				if(tempStudentGroupWithSubject.equals(studentGroupListWithoutSubject.get(i))) {
					studentGroupListWithoutSubject.remove(i);
				}
			}
		}
		
		return studentGroupListWithoutSubject;
	}

	@Override
	public TimeTable getTimeTableByStudentGroupId(int studentGroupId) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		String hql = "FROM TimeTable t WHERE t.studentGroup=" + studentGroupId;
		
		Query query = currentSession.createQuery(hql);
		
		TimeTable timeTable = (TimeTable) query.getSingleResult();
		
		return timeTable;
	}

	@Override
	public List<StudentDetail> getStudentListWithGrupId(int groupId) {

		Session currentSession = sessionFactory.getCurrentSession();
		
		String hql = "FROM StudentDetail s WHERE s.studentGroup=" + groupId;
		
		Query query = currentSession.createQuery(hql);
		
		List<StudentDetail> studentDetailList = query.getResultList();
		
		return studentDetailList;
	}
	
}
