package org.mateuszsikorski.wirtualnydziekanat.dao.interfaces;

import java.util.List;

import org.mateuszsikorski.wirtualnydziekanat.entity.User;
import org.mateuszsikorski.wirtualnydziekanat.entity.UserDetail;

public interface UserDAO {

	public void saveUser(User theUser);

	void saveUserDetail(UserDetail theUserDetail);

	public List<User> getUserList();

	public User getUser(int id);

	public User getUser(String userName);
	
}
