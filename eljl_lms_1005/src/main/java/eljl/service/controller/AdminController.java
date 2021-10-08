package eljl.service.controller;

import java.util.Locale;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import eljl.factory.util.ProjectUtils;

@Controller
public class AdminController {

	ModelAndView mav;

	@Autowired
	ProjectUtils pu;
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@RequestMapping(value = "/adminForm", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		return "admin";
	}

	@PostMapping(value = "/adPwChange")
	public ModelAndView adPwChange() {
		mav = new ModelAndView();

		try {
			mav.addObject("mbId", pu.getAttribute("mbId"));
			mav.addObject("mbType", pu.getAttribute("mbType"));
		} catch (Exception e) {e.printStackTrace();}
		
		mav.setViewName("pwChangeForm");
		
		return mav;
	}

}
