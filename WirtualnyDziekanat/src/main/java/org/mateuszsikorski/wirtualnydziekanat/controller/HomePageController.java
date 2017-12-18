package org.mateuszsikorski.wirtualnydziekanat.controller;

import org.mateuszsikorski.wirtualnydziekanat.entity.User;
import org.mateuszsikorski.wirtualnydziekanat.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
@SessionAttributes("user")
public class HomePageController {
	
	@Autowired
	UserService userService;
	
	@ModelAttribute("user")
	public User getUser() {
		if(getAuth() != null) {
			Authentication auth = getAuth();
			User user = (User) auth.getPrincipal();
			System.out.println("User recived from auth");
			return user;
		} else return new User();
	}

	private Authentication getAuth() {
		return SecurityContextHolder.getContext().getAuthentication();
	}
	
	@RequestMapping("/author")
	public String authorPage() { 
		return "author";
	}
	
	@GetMapping("/")
	public ModelAndView homePage(@ModelAttribute("user") User user) {
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("index");
		
		mav.addObject("user", getUser());
		
		return mav;
		
		
	}
	
	@GetMapping("/project")
	public String projectPage(@ModelAttribute("user") User user) {
		return "project";
	}
	
	public static ModelAndView actionFailed(String msg) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("msg", msg);
		mav.setViewName("/actionfailed");
		return mav;
	}
	
}
