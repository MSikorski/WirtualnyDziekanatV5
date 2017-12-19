package org.mateuszsikorski.wirtualnydziekanat.config;

import org.mateuszsikorski.wirtualnydziekanat.entity.User;
import org.mateuszsikorski.wirtualnydziekanat.model.LoginDetail;
import org.mateuszsikorski.wirtualnydziekanat.service.interfaces.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
	
	@Autowired
	private LoginService loginService;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		String userName = authentication.getName();
		String password = authentication.getCredentials().toString();
		
		LoginDetail loginDetail = new LoginDetail(userName, password);
		
		System.out.println("auth: " + authentication.getCredentials());
		System.out.println("Username: " + userName);
		System.out.println("password: " + password);
		
		Authentication auth = null;
		
		User user;
		
		System.out.println(loginService);
		System.out.println(loginDetail);
		try {
		if (loginService.validate(loginDetail)) {
			System.out.println("Login Succesful with username=" + userName);
			user = loginService.getUser();
			user.processAuthorities();
			auth = new UsernamePasswordAuthenticationToken(user, null, user.getGrantedAuthorities());
		} else {
			throw new BadCredentialsException("Niepoprawne dane logowania");
		}
		}catch(Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
		return auth;
	}

	@Override
	public boolean supports(Class<? extends Object> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }

}
