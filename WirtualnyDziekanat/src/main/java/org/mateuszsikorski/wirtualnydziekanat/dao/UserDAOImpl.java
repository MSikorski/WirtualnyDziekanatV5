package org.mateuszsikorski.wirtualnydziekanat.dao;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
		
		String hql = "FROM User";
		
		Query query = currentSession.createQuery(hql);
		
		List<User> users = query.getResultList();
		
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
		
		String hql = "FROM User u WHERE u.userName=" + userName;
		
		Query query = currentSession.createQuery(hql);
		
		User user = (User) query.getSingleResult();
		
		return user;
	}

}
