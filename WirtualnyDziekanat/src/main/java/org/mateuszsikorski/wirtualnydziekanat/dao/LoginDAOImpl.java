package org.mateuszsikorski.wirtualnydziekanat.dao;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.mateuszsikorski.wirtualnydziekanat.dao.interfaces.LoginDAO;
import org.mateuszsikorski.wirtualnydziekanat.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LoginDAOImpl implements LoginDAO{

	@Autowired
	SessionFactory sessionFactory;
	
	User tempUser = null;
	
	@Override
	public boolean checkUser(String user) {

		Session currentSession = sessionFactory.getCurrentSession();
		
		CriteriaBuilder builder = currentSession.getCriteriaBuilder();
		CriteriaQuery<User> query = builder.createQuery(User.class);
		Root<User> root = query.from(User.class);
		query.select(root).where(builder.equal(root.get("userName"), user));
		Query<User> q = currentSession.createQuery(query);
		
		try{
			tempUser = q.getSingleResult();
		} catch (Exception e){
			return false;
		}
		if(tempUser != null)
			return true;
		else return false;
	}
	
	@Override
	public String getUserPassHash(String user){
		
		return tempUser.getPassword();
	}

	@Override
	public User getUser(String user) {
		
		return tempUser;
	}
	
}
