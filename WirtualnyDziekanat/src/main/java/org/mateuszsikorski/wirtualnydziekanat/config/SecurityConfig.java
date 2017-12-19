package org.mateuszsikorski.wirtualnydziekanat.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;



@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	CustomAuthenticationProvider customAuthenticationProvider;
	
	@Override
	  public void configure(AuthenticationManagerBuilder builder)
	          throws Exception {
	      builder.authenticationProvider(customAuthenticationProvider);
	  }

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
			.antMatchers("/", "/project", "/user/create", "/actionfailed", "/user/save", "/user/logout").permitAll()
			.antMatchers("/user/detail").hasAuthority("USER")
			.antMatchers("/admin/**").hasAuthority("ADMIN")
			.antMatchers("/student/**").hasAuthority("STUDENT")
			.antMatchers("/teacher/**").hasAuthority("TEACHER")
			.anyRequest().authenticated()
		.and()
		.formLogin()
			.loginPage("/user/login")
			.usernameParameter("username")
			.passwordParameter("password")
			.loginProcessingUrl("/user/logintry")
			.defaultSuccessUrl("/")
			.permitAll();
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
	    web.ignoring().antMatchers("/resources/**");
	}
	
	

	
}
