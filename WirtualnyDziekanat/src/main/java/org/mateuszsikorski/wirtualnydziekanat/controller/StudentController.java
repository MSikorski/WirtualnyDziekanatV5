package org.mateuszsikorski.wirtualnydziekanat.controller;

import java.util.List;

import org.mateuszsikorski.wirtualnydziekanat.entity.Subject;
import org.mateuszsikorski.wirtualnydziekanat.entity.User;
import org.mateuszsikorski.wirtualnydziekanat.service.interfaces.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes("user")
@RequestMapping("/student")
public class StudentController {

	@Autowired
	StudentService studentService;
	
	@ModelAttribute("user")
	public User getUser() {
		return new User();
	}
	
	public boolean checkPrivagles(User user) {
		if(user.getUserDetail().getStudentDetail() == null)
			return false;
		else return true;
	}
		
	@GetMapping("/detail")
	public ModelAndView studentDetailPage(@ModelAttribute("user") User user) {
		
		if(!checkPrivagles(user)) {
			String msg = "Brak dostepu do tej funkcjonalnosci";
			return HomePageController.actionFailed(msg);
		}
		
		ModelAndView mav = new ModelAndView();
	
		mav.setViewName("/student/student-detail-form");
		
		return mav;
	}
	
	@GetMapping("/marks")
	public ModelAndView studentMarks(@ModelAttribute("user") User user) {
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("/student/student-marks-form");
		
		return mav;
	}
	
	@GetMapping("/subjects")
	public ModelAndView studentSubjects(@ModelAttribute("user") User user) {
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("/student/student-subjects-form");
	
		List<Subject> subjects = user.getUserDetail().getStudentDetail().
									getStudentGroup().getTimeTable().getSubjects();
		
		mav.addObject("subjects", subjects);
		
		return mav;
	}
	
	
}
