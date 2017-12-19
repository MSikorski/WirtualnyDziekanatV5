package org.mateuszsikorski.wirtualnydziekanat.service.interfaces;

import java.util.List;

import org.mateuszsikorski.wirtualnydziekanat.entity.User;
import org.mateuszsikorski.wirtualnydziekanat.entity.UserDetail;

public interface UserService {

	public void saveUser(User theUser, UserDetail userDetail);

	public void saveUserDetail(UserDetail theUserDetail, UserDetail userDetail);

	public List<User> getUserList(UserDetail userDetail);

	public User getUser(int id, UserDetail userDetail);

	void saveUserFirstTime(User theUser, UserDetail userDetail);

	 User getUser(String userName, UserDetail userDetail);
	
}
