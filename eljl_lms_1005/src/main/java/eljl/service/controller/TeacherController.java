package eljl.service.controller;

import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import eljl.factory.bean.StuManageBean;
import eljl.lms.teacher.TeacherStumanage;

@Controller
public class TeacherController {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	ModelAndView mav;
	
	@Autowired
	TeacherStumanage ts;
	
	@RequestMapping(value = "/stuManage", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		return "stuManage";
	}
	
	@RequestMapping(value = "/attendance", method = RequestMethod.GET)
	public String attendance() {
		return "attendance";
	}
	
}
