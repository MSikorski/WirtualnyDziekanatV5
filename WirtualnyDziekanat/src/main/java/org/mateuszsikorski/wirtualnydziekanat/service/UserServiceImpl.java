package org.mateuszsikorski.wirtualnydziekanat.service;

import java.util.List;

import javax.transaction.Transactional;

import org.mateuszsikorski.wirtualnydziekanat.dao.interfaces.UserDAO;
import org.mateuszsikorski.wirtualnydziekanat.entity.User;
import org.mateuszsikorski.wirtualnydziekanat.entity.UserDetail;
import org.mateuszsikorski.wirtualnydziekanat.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDAO userDAO;

	@Override
	@Transactional
	public void saveUserFirstTime(User theUser, UserDetail userDetail) {
		String temp = theUser.getTempPass();
		System.out.println(temp);
		theUser.setPassword(BCrypt.hashpw(temp, BCrypt.gensalt()));
		System.out.println(theUser);
		userDAO.saveUser(theUser);
	}
	
	@Override
	@Transactional
	public void saveUser(User theUser, UserDetail userDetail) {
		
		userDAO.saveUser(theUser);
	}

	@Override
	@Transactional
	public void saveUserDetail(UserDetail theUserDetail, UserDetail userDetail) {

		userDAO.saveUserDetail(theUserDetail);
	}

	@Override
	@Transactional
	public List<User> getUserList(UserDetail userDetail) {
		return userDAO.getUserList();
	}

	@Override
	@Transactional
	public User getUser(int id, UserDetail userDetail) {
		return userDAO.getUser(id);
	}

	@Override
	@Transactional
	public User getUser(String userName, UserDetail userDetail) {
		return userDAO.getUser(userName);
	}

}
