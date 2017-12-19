package org.mateuszsikorski.wirtualnydziekanat.aspect;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.mateuszsikorski.wirtualnydziekanat.entity.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LogDAO {

	@Autowired
	SessionFactory sessionFactory;
	
	public void saveLog(Log log) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		currentSession.save(log);
	}

	public List<Log> getLogs() {

		Session currentSession = sessionFactory.getCurrentSession();
		
		String hql = "FROM Log";
		
		Query query = currentSession.createQuery(hql);
		
		List<Log> logs = query.getResultList();
		
		return logs;
	}
	
}
