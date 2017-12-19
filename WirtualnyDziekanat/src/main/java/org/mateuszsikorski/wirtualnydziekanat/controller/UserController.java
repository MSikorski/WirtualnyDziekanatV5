package org.mateuszsikorski.wirtualnydziekanat.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.mateuszsikorski.wirtualnydziekanat.entity.User;
import org.mateuszsikorski.wirtualnydziekanat.model.LoginDetail;
import org.mateuszsikorski.wirtualnydziekanat.model.Privagles;
import org.mateuszsikorski.wirtualnydziekanat.model.UserDetailForm;
import org.mateuszsikorski.wirtualnydziekanat.service.interfaces.LoginService;
import org.mateuszsikorski.wirtualnydziekanat.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
@SessionAttributes("user")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private LoginService loginService;

	@ModelAttribute("user")
	public User getUser() {
		if(getAuth().getPrincipal() instanceof User) {
			Authentication auth = getAuth();
			User user = (User) auth.getPrincipal();
			System.out.println("User recived from auth");
			return user;
		} else return new User();
	}

	private Authentication getAuth() {
		return SecurityContextHolder.getContext().getAuthentication();
	}

	// Logowanie
	@GetMapping("/login")
	public String loginPage() {

		return "login/login-form";
	}

	@GetMapping("/loginproceed")
	public String redirectFromPost1() {
		return "forward:/user/login";
	}

	@GetMapping("/logout")
	public String logout(@ModelAttribute("user") User user, HttpSession session, SessionStatus status) {

		status.setComplete();
		session.removeAttribute("user");
		session.invalidate();

		return "redirect:/user/login";
	}

	// Rejestracja uzytkownika
	@GetMapping("/create")
	public ModelAndView createUser(@ModelAttribute("user") User user) {

		ModelAndView mav = new ModelAndView();

		String msg;

		if (!(user.getUserName() == "Niezarejestrowany")) {
			msg = "Uzytkownik jest juz zarejestrowany";
			HomePageController.actionFailed(msg);
			System.out.println("\n-----\n/user/create Recived request from registered user: " + user);
		} else {
			user = new User();
			mav.setViewName("/user/add-user-form");
			System.out.println("\n-----\n/user/create Creating new user in memory: " + user);
		}

		mav.addObject("user", user);

		return mav;
	}

	// Rejestracja uzytkownika
	@PostMapping("/save")
	public ModelAndView saveUser(@ModelAttribute("user") @Valid User user, BindingResult bR) {

		ModelAndView mav = new ModelAndView();

		String msg;

		if (bR.hasErrors()) {
			System.out.println("\n-----\n/user/save Incorrect data recieved");
			mav.setViewName("/user/add-user-form");
			return mav;
		} else {
			try {
				System.out.println("\n-----\n/user/save/ Saving the user in db: " + user);
				userService.saveUserFirstTime(user, user.getUserDetail());
			} catch (Exception e) {
				System.out.println(e);
				msg = "Nazwa uzytkownika jest juz wykorzystana";
				mav.addObject("msg", msg);
				mav.setViewName("/user/add-user-form");
				return mav;
			}
		}
		
		mav.setViewName("redirect:/user/logout");
		
		return mav;
	}

	@GetMapping("/save")
	public String redirectFromPost2() {
		return "forward:/user/create";
	}

	// Szczegoly profilu uzytkownika
	@GetMapping("/detail")
	public ModelAndView showDetail(@ModelAttribute("user") User user) {

		ModelAndView mav = new ModelAndView();
		
		UserDetailForm userDetailForm = new UserDetailForm(user);

		if (user.getUserDetail().getStudentDetail() == null)
			userDetailForm.getPrivagles().setStudentPrivagles(false);

		if (user.getUserDetail().getTeacherDetail() == null)
			userDetailForm.getPrivagles().setTeacherPrivagles(false);

		if (user.getUserDetail().getAdminDetail() == null)
			userDetailForm.getPrivagles().setAdminPrivagles(false);

		mav.addObject("userDetailForm", userDetailForm);
		

		mav.addObject("userDetailForm", userDetailForm);
		
		mav.setViewName("/user/user-detail-form");

		System.out.println("\n-----\n/user/detail User + " + user);

		return mav;
	}

	// Szczegoly profilu uzytkownika
	@PostMapping("/saveDetail")
	public ModelAndView saveUserDetail(@ModelAttribute("user") User user,
			@ModelAttribute("userDetailForm") UserDetailForm userDetailForm) {

		ModelAndView mav = new ModelAndView();
		
		user.pasteProporties(userDetailForm.getUser());

		user.setUserDetail(userDetailForm.getPrivagles().Validate(user.getUserDetail()));
			
		System.out.println("\n-----\n/user/saveDetail Saving the user in db: " + user.getUserDetail());
			
		userService.saveUser(user, user.getUserDetail());
		
		mav.setViewName("redirect:/user/detail");
		
		return mav;
	}

	@GetMapping("/saveDetail")
	public String redirectFromPost3() {
		return "forward:/user/detail";
	}

}
