package eljl.service.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
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
	
	//메인
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		return "login";
	}
	
	// 선생님 회원가입
	@PostMapping(value = "/joinTeacher")
	public ModelAndView joinTeacher(@ModelAttribute UserInfoBean ub) {
		return ls.joinTeacherCtl(ub);
	}
	
	// 학생 회원가입
	@PostMapping(value = "/joinStudent")
	public ModelAndView joinStudent(@ModelAttribute UserInfoBean ub) {
		return ls.joinStudentCtl(ub);
	}
	
	// 비밀번호 변경페이지로 이동
	@RequestMapping(value = "/pwChangeForm", method = RequestMethod.GET)
	public String pwChangeForm() {
		return "pwChangeForm";
	}
	
	// 로그인 페이지로 이동
	@RequestMapping(value = "/loginForm", method = RequestMethod.GET)
	public String loginForm() {
		return "login";
	}
	
	//회원가입폼으로 가기
	@RequestMapping(value = "/joinTeForm", method = RequestMethod.GET)
	public String joinTeForm() {
		return "joinTeacher";
	}
	
	//학생가입폼으로 가기
	@RequestMapping(value = "/joinStuForm", method = RequestMethod.GET)
	public String joinStuForm() {
		return "joinStudent";
	}
	
	//메인폼으로 가기
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String main() {
		return "main";
	}
	
	//중복체크
	@PostMapping("/dupCheck")
	@ResponseBody
	public String dupCheck(@ModelAttribute UserInfoBean ub) {
		return ls.dupCheck(ub);
	}
	
	// 로그인
	@PostMapping(value = "login")
	public ModelAndView login(@ModelAttribute UserInfoBean ub) {
		return ls.loginCtl(ub);
	}
	
	//비밀번호 변경 인증
	@PostMapping("/usAuthen")
	@ResponseBody
	public String usAuthen(@ModelAttribute UserInfoBean ub) {
		return ls.usAuthen(ub);
	}
	
	//비밀번호 변경 이메일 전송
	@PostMapping("/pwChangeMail")
	@ResponseBody
	public Map<String,String> pwChangeMail(@ModelAttribute UserInfoBean ub) {
		return ls.pwChangeMail(ub);
	}
	
	//인증키 서버에 전송
	@PostMapping("/sendAuth")
	@ResponseBody
	public Map<String,String> sendAuth(@ModelAttribute UserInfoBean ub) {
		return ls.sendAuth(ub);
	}
	
	//새 비밀번호 전송
	@PostMapping("/sendNewPW")
	public ModelAndView sendNewPW(@ModelAttribute UserInfoBean ub) {
		return ls.sendNewPW(ub);
	}
	
	
	// 캘린더 From 양식
	@RequestMapping(value = "/calendar", method = RequestMethod.GET)
	public String calendarForm() {
		return "calendar";
	}
}

