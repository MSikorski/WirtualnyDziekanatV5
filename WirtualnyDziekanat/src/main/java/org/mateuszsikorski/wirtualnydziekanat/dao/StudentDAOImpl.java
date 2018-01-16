package org.mateuszsikorski.wirtualnydziekanat.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

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
		
		StudentGroup studentGroup = user.getUserDetail().getStudentDetail().getStudentGroup();
		int timeTableId = studentGroup.getTimeTable().getId();
		
		CriteriaBuilder builder = currentSession.getCriteriaBuilder();
		CriteriaQuery<Subject> query = builder.createQuery(Subject.class);
		Root<Subject> root = query.from(Subject.class);
		query.select(root).where(builder.equal(root.get("timeTableId"), timeTableId));
		Query<Subject> q = currentSession.createQuery(query);
		
		List<Subject> tempList = q.getResultList();
		return tempList;
	}

	@Override
	public List<StudentGroup> getStudentGroupList() {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		CriteriaBuilder builder = currentSession.getCriteriaBuilder();
		CriteriaQuery<StudentGroup> query = builder.createQuery(StudentGroup.class);
		Root<StudentGroup> root = query.from(StudentGroup.class);
		query.select(root);
		Query<StudentGroup> q = currentSession.createQuery(query);
	
		List<StudentGroup> tempList = q.getResultList();
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
		
		CriteriaBuilder builder = currentSession.getCriteriaBuilder();
		CriteriaQuery<StudentDetail> query = builder.createQuery(StudentDetail.class);
		Root<StudentDetail> root = query.from(StudentDetail.class);
		query.select(root).where(builder.isNull(root.get("studentGroup")));
		Query<StudentDetail> q = currentSession.createQuery(query);
		
		List<StudentDetail> temp = q.getResultList();
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
		
		CriteriaBuilder builder = currentSession.getCriteriaBuilder();
		CriteriaQuery<Subject> query = builder.createQuery(Subject.class);
		Root<Subject> root = query.from(Subject.class);
		query.select(root);
		Query<Subject> q = currentSession.createQuery(query);
		
		List<Subject> subjects = null;
		
		subjects = q.getResultList();
		
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
		
		CriteriaBuilder builder = currentSession.getCriteriaBuilder();
		CriteriaQuery<Subject> query = builder.createQuery(Subject.class);
		Root<Subject> root = query.from(Subject.class);
		query.select(root).where(builder.equal(root.get("id"), subjectId));;
		Query<Subject> q = currentSession.createQuery(query);
		
		List<Subject> tempSubjectList = q.getResultList();
		List<TimeTable> tempTimeTableList = new ArrayList<TimeTable>();
		
		System.out.println("DEBUG----- " + tempSubjectList.toString());
		
		for(Subject tempSubject : tempSubjectList) {
			if(tempSubject.getTimeTable() != null)
				tempTimeTableList.add(tempSubject.getTimeTable());
		}
			
		List<StudentGroup> studentGroupList = new ArrayList<StudentGroup>();
		
		if(!tempTimeTableList.isEmpty()) {
			for(TimeTable tempTimeTable : tempTimeTableList) {
				StudentGroup tempStudentGroup = tempTimeTable.getStudentGroup();
				studentGroupList.add(tempStudentGroup);
			}
		} else return null;
		
		return studentGroupList;
	}

	@Override
	public List<StudentGroup> getStudentGroupListWithoutSubject(int subjectId) {
		
		List<StudentGroup> studentGroupListWithSubject = getStudentGroupListWithSubject(subjectId);
		List<StudentGroup> studentGroupListWithoutSubject = getStudentGroupList();
		
		if(studentGroupListWithSubject == null) {
			return studentGroupListWithoutSubject;
		} else {
			for(StudentGroup tempStudentGroupWithSubject : studentGroupListWithSubject) {
				for(int i=0; i < studentGroupListWithoutSubject.size(); i++) {
					if(tempStudentGroupWithSubject.equals(studentGroupListWithoutSubject.get(i))) {
						studentGroupListWithoutSubject.remove(i);
					}
				}
			}
		}
		
		return studentGroupListWithoutSubject;
	}

	@Override
	public TimeTable getTimeTableByStudentGroupId(int studentGroupId) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		CriteriaBuilder builder = currentSession.getCriteriaBuilder();
		CriteriaQuery<TimeTable> query = builder.createQuery(TimeTable.class);
		Root<TimeTable> root = query.from(TimeTable.class);
		query.select(root).where(builder.equal(root.get("studentGroup"), studentGroupId));
		Query<TimeTable> q = currentSession.createQuery(query);
		
		TimeTable timeTable = q.getSingleResult();
		return timeTable;
	}

	@Override
	public List<StudentDetail> getStudentListWithGrupId(int studentGroupId) {

		Session currentSession = sessionFactory.getCurrentSession();
		
		CriteriaBuilder builder = currentSession.getCriteriaBuilder();
		CriteriaQuery<StudentDetail> query = builder.createQuery(StudentDetail.class);
		Root<StudentDetail> root = query.from(StudentDetail.class);
		query.select(root).where(builder.equal(root.get("studentGroup"), studentGroupId));
		Query<StudentDetail> q = currentSession.createQuery(query);
		
		List<StudentDetail> studentDetailList = q.getResultList();
		return studentDetailList;
	}
	
}
