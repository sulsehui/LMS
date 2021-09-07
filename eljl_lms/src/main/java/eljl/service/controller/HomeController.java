package eljl.service.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import eljl.factory.bean.UserInfoBean;
import eljl.service.member.LoginService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	LoginService ls;
	
	ModelAndView mav;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		return "login";
	}
	
	// 선생님 회원가입
	@PostMapping(value = "/joinTeacher")
	public String joinTeacher(@ModelAttribute UserInfoBean ub) {
		System.out.println(ub.getMbId() + ub.getMbPw() + ub.getMbName());
		ls.joinTeacherCtl(ub);
		return "joinTeacher";
	}
	
	// 학생 회원가입
	@PostMapping(value = "/joinStudent")
	public String joinStudent() {
		return "joinStudent";
	}
	
	// 로그인
	@PostMapping(value = "login")
	public ModelAndView login(@ModelAttribute UserInfoBean ub) {
		return ls.loginCtl(ub);
	}
	

	@RequestMapping(value = "/joinForm", method = RequestMethod.GET)
	public String joinForm() {
		return "joinTeacher";
	}
	
	
}

