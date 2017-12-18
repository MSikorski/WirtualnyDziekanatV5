package org.mateuszsikorski.wirtualnydziekanat.controller;

import java.util.ArrayList;
import java.util.List;

import org.mateuszsikorski.wirtualnydziekanat.entity.StudentDetail;
import org.mateuszsikorski.wirtualnydziekanat.entity.StudentGroup;
import org.mateuszsikorski.wirtualnydziekanat.entity.Subject;
import org.mateuszsikorski.wirtualnydziekanat.entity.TimeTable;
import org.mateuszsikorski.wirtualnydziekanat.entity.User;
import org.mateuszsikorski.wirtualnydziekanat.model.Privagles;
import org.mateuszsikorski.wirtualnydziekanat.model.UpdatingStudentGroupHelpFrom;
import org.mateuszsikorski.wirtualnydziekanat.service.interfaces.StudentService;
import org.mateuszsikorski.wirtualnydziekanat.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
@SessionAttributes("user")
public class AdminController {
	
	@Autowired
	StudentService studentService;
	
	@Autowired
	UserService userService;
	
	@ModelAttribute("user")
	public User getUser() {
		Authentication auth = getAuth();
		String userName = auth.getName();
		User user = userService.getUser(userName);
		System.out.println("User recived from auth");
		return user;
	}

	private Authentication getAuth() {
		return SecurityContextHolder.getContext().getAuthentication();
	}
	
	public boolean checkPrivagles(User user) {
		if(user.getUserDetail().getAdminDetail() == null)
			return false;
		else return true;
	}
	
	@GetMapping("/panel")
	public ModelAndView getAdminPanel(@ModelAttribute("user") User user) {
		
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("/admin/admin-panel");
		
		return mav;
	}
	
	@GetMapping("/manageStudentGroups")
	public ModelAndView getStudentGroupManagment(@ModelAttribute("user") User user) {
		
		if(!checkPrivagles(user)) {
			String msg = "Brak dostepu do tej funkcjonalnosci";
			return HomePageController.actionFailed(msg);
		}
		
		ModelAndView mav = new ModelAndView();
		
		List<StudentGroup> studentGroupList = studentService.getStudentGroupList();
		mav.addObject("studentGroupList", studentGroupList);
	
		mav.setViewName("/admin/student-group-list");
		
		return mav;
	}
	
	@GetMapping("/createStudentGroup")
	public ModelAndView createStudentGroup(@ModelAttribute("user") User user) {
		
		if(!checkPrivagles(user)) {
			String msg = "Brak dostepu do tej funkcjonalnosci";
			return HomePageController.actionFailed(msg);
		}
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("/admin/create-student-group");
		
		StudentGroup studentGroup = new StudentGroup();
		mav.addObject("studentGroup", studentGroup);
		
		return mav;
	}
	
	@PostMapping("/saveStudentGroup")
	public ModelAndView saveStudentGroup(@ModelAttribute("user") User user,
					@ModelAttribute("studentGroup") StudentGroup studentGroup) {
		
		if(!checkPrivagles(user)) {
			String msg = "Brak dostepu do tej funkcjonalnosci";
			return HomePageController.actionFailed(msg);
		}
		
		ModelAndView mav = new ModelAndView();
		
		studentService.saveStudentGroup(studentGroup);
		studentService.saveTimeTable(studentGroup.getTimeTable());
		
		String msg = "Pomylsnie zapisano grupe";
		List<StudentGroup> studentGroupList = studentService.getStudentGroupList();
		mav.addObject("msg", msg);
		mav.addObject("studentGroupList", studentGroupList);
		
		mav.setViewName("/admin/student-group-list");
		
		return mav;
	}
	
	// redirecting get requqests from post mapping to avoid throwing error
	@GetMapping("/saveStudentGroup")
	public String redirectFromPost1() {
		return "redirect:/admin/manageStudentGroups";				
	}
	
	@GetMapping("/updateStudentGroup")
	public ModelAndView updateStudentGroup(@ModelAttribute("user") User user,
										@RequestParam("studentGroupId") int id) {
		
		if(!checkPrivagles(user)) {
			String msg = "Brak dostepu do tej funkcjonalnosci";
			return HomePageController.actionFailed(msg);
		}
		
		ModelAndView mav = new ModelAndView();
		
		StudentGroup studentGroup = studentService.getStudentGroup(id);
		
		
		UpdatingStudentGroupHelpFrom helpForm = new UpdatingStudentGroupHelpFrom();
		
		try{
			if(studentGroup.getStudentDetailList() != null) { 
				List<StudentDetail> studentList = new ArrayList();
				studentList = studentGroup.getStudentDetailList();
				helpForm.setStudentList(studentList);
			}
		} catch(Exception e){
			System.out.println("Lista studentow nalezacych do grupy jest pusta");
		}
		
		
		helpForm.setStudentListWithoutGroup(studentService.getStudentListWithoutGroup());
		helpForm.setStudentGroupId(id);
		
		mav.addObject("studentGroup", studentGroup);
		mav.addObject("helpForm", helpForm);
		
		mav.setViewName("/admin/student-group-update");
		
		return mav;
	}
	
	@PostMapping("/saveUpdatedStudentGroup")
	public ModelAndView saveUpdatedStudentGroup(@ModelAttribute("user") User user,
			@ModelAttribute("helpForm") UpdatingStudentGroupHelpFrom helpForm) {
		
		ModelAndView mav = new ModelAndView();
		
		List<StudentDetail> studentList = null;
		
		boolean studentListPresent = false;
		
		System.out.println(helpForm.getStudentGroupId());
		
		StudentGroup studentGroup = studentService.getStudentGroup(helpForm.getStudentGroupId());
		
		try {
			if(studentGroup.getStudentDetailList() != null)
				studentListPresent = true;
		} catch(Exception e) {
			System.out.println("StudentList not present");
		}
		
		List<Integer> processedAddCb = helpForm.getAddCB().processCheckBoxes();
		List<Integer> processedRemoveCb = helpForm.getRemoveCB().processCheckBoxes();
		
		if(studentListPresent)
			studentList = studentGroup.getStudentDetailList();
		
		List<StudentDetail> studentListWithoutGroup = studentService.getStudentListWithoutGroup();
		
		for(int index : processedAddCb) {
			studentListWithoutGroup.get(index).setStudentGroup(studentGroup);
			studentService.saveStudent(studentListWithoutGroup.get(index));
		}
		
		for(int index : processedRemoveCb) {
			studentList.get(index).setStudentGroup(null);
			studentService.saveStudent(studentList.get(index));
		}
		
		mav.setViewName("redirect:/admin/updateStudentGroup?studentGroupId=" + studentGroup.getId());
				
		return mav;
	}
	
	@GetMapping("/saveUpdatedStudentGroup")
	public String redirectFromPost2() {
		return "redirect:/admin/manageStudentGroups";
	}
	
	@GetMapping("/manageUsers")
	public ModelAndView manageUsers(@ModelAttribute("user") User user) {
		
		ModelAndView mav = new ModelAndView();
		
		List<User> userList = userService.getUserList();
		
		System.out.println(userList);
		
		mav.addObject("userList", userList);
		
		mav.setViewName("/admin/manage-users");
		
		return mav;
	}
	
	@GetMapping("/showUserDetail")
	public ModelAndView showUserDetail(@ModelAttribute("user") User user,
									@RequestParam("userId") int id) {
		
		ModelAndView mav = new ModelAndView();
		
		User checkedUser = userService.getUser(id);
		
		Privagles privagles = new Privagles();
		
		if ((checkedUser.getUserDetail().getAdminDetail() == null) && (checkedUser.getUserDetail().getStudentDetail() == null)
				&& (checkedUser.getUserDetail().getTeacherDetail() == null)) {
	
		} else {
			if (checkedUser.getUserDetail().getStudentDetail() == null)
				privagles.setStudentPrivagles(false);

			if (checkedUser.getUserDetail().getTeacherDetail() == null)
				privagles.setTeacherPrivagles(false);

			if (checkedUser.getUserDetail().getAdminDetail() == null)
				privagles.setAdminPrivagles(false);

		}
		
		privagles.setId(id);
		
		mav.addObject("privagles", privagles);
		mav.addObject("checkedUser", checkedUser);
		
		mav.setViewName("/admin/user-detail");
		
		return mav;
	}
	
	@PostMapping("/saveUserDetail")
	public ModelAndView saveUserDetail(@ModelAttribute("user") User user,
										@ModelAttribute("checkedUser") User checkedUser,
										@ModelAttribute("privagles") Privagles privagles) {
		
		User tempUser = userService.getUser(privagles.getId());
		
		tempUser.pasteProporties(checkedUser);
		tempUser.setUserDetail(privagles.Validate(tempUser.getUserDetail()));
		
		System.out.println(tempUser);
		
		userService.saveUser(tempUser);
		
		System.out.println(tempUser);
		
		return manageUsers(user);
	}
	
	@GetMapping("/saveUserDetail")
	public String redirectFromPost3() {
		return "redirect:/admin/manageUsers";
	}
	
	@GetMapping("/subjects")
	public ModelAndView showSubjects(@ModelAttribute("user") User user) {
		
		ModelAndView mav = new ModelAndView();
		
		List<Subject> subjects = studentService.getSubjectList();
		
		mav.addObject("subjects", subjects);
		
		mav.setViewName("/admin/show-subjects");
		
		return mav;
	}
	
	@GetMapping("/createSubject")
	public ModelAndView createSubject(@ModelAttribute("user") User user) {
		
		ModelAndView mav = new ModelAndView();

		Subject subject = new Subject();
		
		mav.addObject("subject", subject);
		
		mav.setViewName("/admin/create-subject");
		
		return mav;
	}
	
	@PostMapping("/saveSubject") 
	public ModelAndView saveSubject(@ModelAttribute("user") User user,
										@ModelAttribute("subject") Subject subject) {
			
		ModelAndView mav = new ModelAndView();
		
		studentService.saveSubject(subject);
			
		mav.setViewName("redirect:/admin/subjects");	
			
		return mav;
	}
	
	@GetMapping("/saveSubject")
	public String redirectFromPost4() {
		return "redirect:/admin/subjects";
	}
	
	@GetMapping("/updateSubject")
	public ModelAndView updateSubject(@ModelAttribute("user") User user,
									@RequestParam("subjectId") int subjectId) {
		
		ModelAndView mav = new ModelAndView();
		
		Subject subject = studentService.getSubject(subjectId);

		List<StudentGroup> studentGroupListWithSubject = studentService.getStudentGroupListWithSubject(subject.getId());
		List<StudentGroup> studentGroupListWithoutSubject = studentService.getStudentGroupListWithoutSubject(subject.getId());
		
		mav.addObject("subject", subject);
		mav.addObject("studentGroupList", studentGroupListWithSubject);
		mav.addObject("studentGroupListWithoutSubject", studentGroupListWithoutSubject);
		
		mav.setViewName("/admin/update-subject");
		
		return mav;
	}
	
	@GetMapping("/updateSubjectAddGroup")
	public ModelAndView updateSubjectWithGroupAdd(@ModelAttribute("user") User user,
									@RequestParam("subjectId") int subjectId,
									@RequestParam("addedGroupId") int addedGroupId) {
		
		ModelAndView mav = new ModelAndView();
		
		Subject subject = studentService.getSubject(subjectId);
		
		System.out.println(addedGroupId);
		
		if(addedGroupId != 0) {
			TimeTable timeTable = studentService.getTimeTableByStudentGroupId(addedGroupId);
			subject.setTimeTable(timeTable);
			studentService.saveSubject(subject);
		}
		
		mav.setViewName("redirect:/admin/updateSubject?subjectId=" + subjectId);
		
		return mav;
	}
	
	@GetMapping("/updateSubjectRemoveGroup")
	public ModelAndView updateSubjectWithGroupRemove(@ModelAttribute("user") User user,
									@RequestParam("subjectId") int subjectId,
									@RequestParam("removedGroupId") int removedGroupId) {
		
		ModelAndView mav = new ModelAndView();
		
		Subject subject = studentService.getSubject(subjectId);
		
		if(removedGroupId != 0) {
			StudentGroup temp = studentService.getStudentGroup(removedGroupId);
			temp.getTimeTable().getSubjects().remove(subject);
			studentService.saveSubject(subject);
			System.out.println(subject);
			System.out.println(temp);
		}
		
		mav.setViewName("redirect:/admin/updateSubject?subjectId=" + subjectId);
		
		return mav;
	}
	
	
	

}
