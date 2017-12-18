package org.mateuszsikorski.wirtualnydziekanat.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.mateuszsikorski.wirtualnydziekanat.entity.User;
import org.mateuszsikorski.wirtualnydziekanat.model.LoginDetail;
import org.mateuszsikorski.wirtualnydziekanat.model.Privagles;
import org.mateuszsikorski.wirtualnydziekanat.service.interfaces.LoginService;
import org.mateuszsikorski.wirtualnydziekanat.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
		return new User();
	}

	// Logowanie
	@GetMapping("/login")
	public String loginPage() {

		return "login/login-form";
	}
/*
	// Autoryzacja logowania
	@PostMapping("/loginproceed")
	public ModelAndView loginValidation(@ModelAttribute("user") User user,
			@ModelAttribute("loginDetail") LoginDetail loginDetail) {

		ModelAndView mav = new ModelAndView();

		if (loginService.validate(loginDetail)) {
			user = loginService.getUser();
			mav.addObject("user", user);
			String msg = "Zalogowano poprawnie";
			mav.addObject("msg", msg);
			mav.setViewName("/login/login-succed");
		} else {
			String msg = "Niepoprawna nazwa uzytkownika lub haslo";
			mav.addObject("msg", msg);
			mav.setViewName("/login/login-form");
			return mav;
		}
		return mav;
	}
*/
	@GetMapping("/loginproceed")
	public String redirectFromPost1() {
		return "forward:/user/login";
	}

	@GetMapping("/logout")
	public String logout(@ModelAttribute("user") User user, HttpSession session, SessionStatus status) {

		status.setComplete();
		session.removeAttribute("user");
		session.invalidate();

		return "redirect:/";
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
			//userService.saveUserDetail(user.getUserDetail());
			try {
				System.out.println("\n-----\n/user/save/ Saving the user in db: " + user);
				userService.saveUserFirstTime(user);
			} catch (Exception e) {
				msg = "Nazwa uzytkownika jest juz wykorzystana";
				mav.addObject("msg", msg);
				mav.setViewName("/user/add-user-form");
				return mav;
			}
		}

		mav.setViewName("redirect:/user/detail");
		
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

		Privagles privagles = new Privagles();
		
		if (user.getUserName() == "Niezarejestrowany") {
			return HomePageController.actionFailed("Uzytkownik nie jest zarejestrowany");
		}

		if ((user.getUserDetail().getAdminDetail() == null) && (user.getUserDetail().getStudentDetail() == null)
				&& (user.getUserDetail().getTeacherDetail() == null)) {

			mav.addObject("privagles", privagles);
		} else {
			if (user.getUserDetail().getStudentDetail() == null)
				privagles.setStudentPrivagles(false);

			if (user.getUserDetail().getTeacherDetail() == null)
				privagles.setTeacherPrivagles(false);

			if (user.getUserDetail().getAdminDetail() == null)
				privagles.setAdminPrivagles(false);

			mav.addObject("privagles", privagles);
		}

		mav.setViewName("/user/user-detail-form");

		System.out.println("\n-----\n/user/detail User + " + user);

		return mav;
	}

	// Szczegoly profilu uzytkownika
	@PostMapping("/saveDetail")
	public ModelAndView saveUserDetail(@ModelAttribute("user") @Valid User user,
			@ModelAttribute("privagles") Privagles privagles, BindingResult bR) {

		ModelAndView mav = new ModelAndView();

		mav.setViewName("/user/user-detail-form");

		String msg = "";

		if (bR.hasErrors()) {
			System.out.println("\n-----\n/user/saveDetail Incorrect data recieved");
			msg = "Niepoprawnie wprowadzone dane";
		} else {
			user.setUserDetail(privagles.Validate(user.getUserDetail()));
			
			System.out.println("\n-----\n/user/saveDetail Saving the user in db: " + user.getUserDetail());
			
			userService.saveUser(user);
			msg = "Poprawnie zapisano dane";
		}

		mav.addObject("msg", msg);

		return mav;
	}

	@GetMapping("/saveDetail")
	public String redirectFromPost3() {
		return "forward:/user/detail";
	}

}
