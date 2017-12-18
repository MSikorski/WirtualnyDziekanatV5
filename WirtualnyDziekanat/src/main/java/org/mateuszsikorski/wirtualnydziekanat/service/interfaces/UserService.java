package org.mateuszsikorski.wirtualnydziekanat.service.interfaces;

import java.util.List;

import org.mateuszsikorski.wirtualnydziekanat.entity.User;
import org.mateuszsikorski.wirtualnydziekanat.entity.UserDetail;

public interface UserService {

	public void saveUser(User theUser);

	public void saveUserDetail(UserDetail theUserDetail);

	public List<User> getUserList();

	public User getUser(int id);

	void saveUserFirstTime(User theUser);

	 User getUser(String userName);
	
}
