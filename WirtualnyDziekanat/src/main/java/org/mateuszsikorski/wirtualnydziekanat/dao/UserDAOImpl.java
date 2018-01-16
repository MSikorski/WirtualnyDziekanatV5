package org.mateuszsikorski.wirtualnydziekanat.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.mateuszsikorski.wirtualnydziekanat.dao.interfaces.UserDAO;
import org.mateuszsikorski.wirtualnydziekanat.entity.User;
import org.mateuszsikorski.wirtualnydziekanat.entity.UserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAOImpl implements UserDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void saveUser(User theUser) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		currentSession.saveOrUpdate(theUser);
	}
	
	@Override
	public void saveUserDetail(UserDetail theUserDetail){
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		currentSession.saveOrUpdate(theUserDetail);
	}

	@Override
	public List<User> getUserList() {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		CriteriaBuilder builder = currentSession.getCriteriaBuilder();
		CriteriaQuery<User> query = builder.createQuery(User.class);
		
		Root<User> root = query.from(User.class);
		query.select(root);
		Query<User> q = currentSession.createQuery(query);
		
		List<User> users = q.getResultList();
		
		return users;
	}

	@Override
	public User getUser(int id) {

		Session currentSession = sessionFactory.getCurrentSession();
		
		return currentSession.get(User.class, id);
	}

	@Override
	public User getUser(String userName) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		CriteriaBuilder builder = currentSession.getCriteriaBuilder();
		CriteriaQuery<User> query = builder.createQuery(User.class);
		
		Root<User> root = query.from(User.class);
		query.select(root).where(builder.equal(root.get("userName"), userName));
		Query<User> q = currentSession.createQuery(query);
		
		User user = q.getSingleResult();
		
		return user;
	}

}
