package org.mateuszsikorski.wirtualnydziekanat.dao;

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
		
		System.out.println("checkUser called");
		
		String hql = "FROM User u WHERE u.userName=" + user;
		Query query = currentSession.createQuery(hql);
		
		try{
			tempUser = (User) query.getSingleResult();
		} catch (Exception e){
			return false;
		}
		return true;
	}
	
	@Override
	public String getUserPassHash(String user){
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		String hql = "SELECT u.password FROM User u WHERE u.userName=" + user;
		System.out.println(hql);
		Query query = currentSession.createQuery(hql);

		String temp = "";
		
		try {
		temp = (String) query.getSingleResult();
		} catch(Exception e){
		}
		
		return temp;
	}

	@Override
	public User getUser(String user) {
		
		return tempUser;
	}
	
}
