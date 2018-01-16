package org.mateuszsikorski.wirtualnydziekanat.controller;

import java.util.List;

import org.mateuszsikorski.wirtualnydziekanat.aspect.LogService;
import org.mateuszsikorski.wirtualnydziekanat.entity.Log;
import org.mateuszsikorski.wirtualnydziekanat.entity.StudentDetail;
import org.mateuszsikorski.wirtualnydziekanat.entity.StudentGroup;
import org.mateuszsikorski.wirtualnydziekanat.entity.Subject;
import org.mateuszsikorski.wirtualnydziekanat.entity.TimeTable;
import org.mateuszsikorski.wirtualnydziekanat.entity.User;
import org.mateuszsikorski.wirtualnydziekanat.model.UpdatingStudentGroupHelpFrom;
import org.mateuszsikorski.wirtualnydziekanat.model.UserDetailForm;
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
	
	@Autowired
	LogService logService;
	
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
	
	@GetMapping("/panel")
	public ModelAndView getAdminPanel(@ModelAttribute("user") User user) {
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("/admin/admin-panel");
		
		return mav;
	}
	
	@GetMapping("/manageStudentGroups")
	public ModelAndView getStudentGroupManagment(@ModelAttribute("user") User user) {
		
		ModelAndView mav = new ModelAndView();
		
		List<StudentGroup> studentGroupList = studentService.getStudentGroupList(user.getUserDetail());
		mav.addObject("studentGroupList", studentGroupList);
	
		mav.setViewName("/admin/student-group-list");
		
		return mav;
	}
	
	@GetMapping("/createStudentGroup")
	public ModelAndView createStudentGroup(@ModelAttribute("user") User user) {

		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("/admin/create-student-group");
		
		StudentGroup studentGroup = new StudentGroup();
		mav.addObject("studentGroup", studentGroup);
		
		return mav;
	}
	
	@PostMapping("/saveStudentGroup")
	public ModelAndView saveStudentGroup(@ModelAttribute("user") User user,
					@ModelAttribute("studentGroup") StudentGroup studentGroup) {
		
		ModelAndView mav = new ModelAndView();
		
		studentService.saveStudentGroup(studentGroup, user.getUserDetail());
		studentService.saveTimeTable(studentGroup.getTimeTable(), user.getUserDetail());
		
		String msg = "Pomylsnie zapisano grupe";
		List<StudentGroup> studentGroupList = studentService.getStudentGroupList(user.getUserDetail());
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
		
		ModelAndView mav = new ModelAndView();
		
		StudentGroup studentGroup = studentService.getStudentGroup(id, user.getUserDetail());
		
		UpdatingStudentGroupHelpFrom helpForm = new UpdatingStudentGroupHelpFrom();
		
		helpForm.setStudentList(studentGroup.getStudentDetailList());
		
		helpForm.setStudentListWithoutGroup(studentService.getStudentListWithoutGroup(user.getUserDetail()));
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
		
		System.out.println(helpForm);
		
		StudentGroup studentGroup = studentService.getStudentGroup(helpForm.getStudentGroupId(), user.getUserDetail());
		
		List<StudentDetail> studentListWithoutGroup = studentService.getStudentListWithoutGroup(user.getUserDetail());
		
		if(!studentListWithoutGroup.isEmpty()) {
			
			List<Integer> processedAddCb = helpForm.getAddCB().processCheckBoxes();
			
			for(int index : processedAddCb) {
				studentListWithoutGroup.get(index).setStudentGroup(studentGroup);
				studentService.saveStudent(studentListWithoutGroup.get(index), user.getUserDetail());
			}
		}
		
		
		if(!studentGroup.getStudentDetailList().isEmpty()) {
			
			List<Integer> processedRemoveCb = helpForm.getRemoveCB().processCheckBoxes();
			studentList = studentGroup.getStudentDetailList();
			
			for(int index : processedRemoveCb) {
				studentList.get(index).setStudentGroup(null);
				studentService.saveStudent(studentList.get(index), user.getUserDetail());
			}
		}
		
		mav.setViewName("redirect:/admin/updateStudentGroup?studentGroupId=" + helpForm.getStudentGroupId());
				
		return mav;
	}
	
	@GetMapping("/saveUpdatedStudentGroup")
	public String redirectFromPost2() {
		return "redirect:/admin/manageStudentGroups";
	}
	
	@GetMapping("/manageUsers")
	public ModelAndView manageUsers(@ModelAttribute("user") User user) {
		
		ModelAndView mav = new ModelAndView();
		
		List<User> userList = userService.getUserList(user.getUserDetail());
		
		System.out.println(userList);
		
		mav.addObject("userList", userList);
		
		mav.setViewName("/admin/manage-users");
		
		return mav;
	}
	
	@GetMapping("/showUserDetail")
	public ModelAndView showUserDetail(@ModelAttribute("user") User user,
									@RequestParam("userId") int id) {
		
		ModelAndView mav = new ModelAndView();
		
		User checkedUser = userService.getUser(id, user.getUserDetail());
		
		UserDetailForm userDetailForm = new UserDetailForm(checkedUser);

		if (checkedUser.getUserDetail().getStudentDetail() == null)
			userDetailForm.getPrivagles().setStudentPrivagles(false);

		if (checkedUser.getUserDetail().getTeacherDetail() == null)
			userDetailForm.getPrivagles().setTeacherPrivagles(false);

		if (checkedUser.getUserDetail().getAdminDetail() == null)
			userDetailForm.getPrivagles().setAdminPrivagles(false);
		
		userDetailForm.getPrivagles().setId(id);

		mav.addObject("userDetailForm", userDetailForm);
		
		mav.setViewName("/admin/user-detail");
		
		return mav;
	}
	
	@PostMapping("/saveUserDetail")
	public ModelAndView saveUserDetail(@ModelAttribute("user") User user,
						@ModelAttribute("userDetailForm") UserDetailForm userDetailForm) {
		
		User tempUser = userService.getUser(userDetailForm.getPrivagles().getId(), user.getUserDetail());
		User checkedUser = userDetailForm.getUser();
		
		tempUser.pasteProporties(checkedUser);
		tempUser.setUserDetail(userDetailForm.getPrivagles().Validate(tempUser.getUserDetail()));
		
		StudentDetail tempDetail = tempUser.getUserDetail().getStudentDetail();
		
		tempDetail.setIndexNumber(checkedUser.getUserDetail().getStudentDetail().getIndexNumber());
		tempDetail.setSpecialization(checkedUser.getUserDetail().getStudentDetail().getSpecialization());
		
		System.out.println(tempUser);
		
		userService.saveUser(tempUser, user.getUserDetail());
		
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
		
		List<Subject> subjects = studentService.getSubjectList(user.getUserDetail());
		
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
		
		studentService.saveSubject(subject, user.getUserDetail());
			
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
		
		Subject subject = studentService.getSubject(subjectId, user.getUserDetail());

		List<StudentGroup> studentGroupListWithSubject = 
				studentService.getStudentGroupListWithSubject(subject.getId(), user.getUserDetail());
		List<StudentGroup> studentGroupListWithoutSubject = 
				studentService.getStudentGroupListWithoutSubject(subject.getId(), user.getUserDetail());
		
		mav.addObject("subject", subject);
		mav.addObject("studentGroupListWithSubject", studentGroupListWithSubject);
		mav.addObject("studentGroupListWithoutSubject", studentGroupListWithoutSubject);
		
		mav.setViewName("/admin/update-subject");
		
		return mav;
	}
	
	@GetMapping("/updateSubjectAddGroup")
	public ModelAndView updateSubjectWithGroupAdd(@ModelAttribute("user") User user,
									@RequestParam("subjectId") int subjectId,
									@RequestParam("addedGroupId") int addedGroupId) {
		
		ModelAndView mav = new ModelAndView();
		
		Subject subject = studentService.getSubject(subjectId, user.getUserDetail());
		
		System.out.println(addedGroupId);
		
		if(addedGroupId != 0) {
			TimeTable timeTable = studentService.getTimeTableByStudentGroupId(addedGroupId, user.getUserDetail());
			subject.setTimeTable(timeTable);
			studentService.saveSubject(subject, user.getUserDetail());
		}
		
		mav.setViewName("redirect:/admin/updateSubject?subjectId=" + subjectId);
		
		return mav;
	}
	
	@GetMapping("/updateSubjectRemoveGroup")
	public ModelAndView updateSubjectWithGroupRemove(@ModelAttribute("user") User user,
									@RequestParam("subjectId") int subjectId,
									@RequestParam("removedGroupId") int removedGroupId) {
		
		ModelAndView mav = new ModelAndView();
		
		Subject subject = studentService.getSubject(subjectId, user.getUserDetail());
		
		if(removedGroupId != 0) {
			StudentGroup temp = studentService.getStudentGroup(removedGroupId, user.getUserDetail());
			temp.getTimeTable().getSubjects().remove(subject);
			studentService.saveSubject(subject, user.getUserDetail());
			System.out.println(subject);
			System.out.println(temp);
		}
		
		mav.setViewName("redirect:/admin/updateSubject?subjectId=" + subjectId);
		
		return mav;
	}
	
	@GetMapping("/logs")
	public ModelAndView showLogs(@ModelAttribute("user") User user) {
		
		ModelAndView mav = new ModelAndView();
		
		List<Log> logs = logService.getLogs();
		
		mav.addObject("logs", logs);
		
		mav.setViewName("/admin/show-logs-form");
		
		return mav;
	}
	

}
