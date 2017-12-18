package org.mateuszsikorski.wirtualnydziekanat.service.interfaces;

import org.mateuszsikorski.wirtualnydziekanat.entity.User;
import org.mateuszsikorski.wirtualnydziekanat.model.LoginDetail;

public interface LoginService {

	public boolean validate(LoginDetail loginDetail);
	
	public User getUser();
	
	
}
