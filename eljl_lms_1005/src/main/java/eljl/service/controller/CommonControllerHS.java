package eljl.service.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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

import eljl.factory.bean.BoardSubmitBean;
import eljl.factory.bean.BoardSubmitBeanHS;
import eljl.factory.bean.HistoryBean;
import eljl.factory.bean.NoticeBean;
import eljl.factory.bean.UserInfoBean;
import eljl.lms.commonHS.MemberClass;
import eljl.service.member.LoginService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class CommonControllerHS {
	private static final Logger logger = LoggerFactory.getLogger(CommonControllerHS.class);
	
	
	@Autowired
	MemberClass mc;
	
	ModelAndView mav;
	
	
	@RequestMapping(value = "/class", method = RequestMethod.GET)
	public String classpage() {
		return "class";
	}

	//제출 과제 보기
	@PostMapping(value = "stuReport")
	public ModelAndView stuReport(@ModelAttribute BoardSubmitBeanHS bsb) {
		return mc.stuReportCtl(bsb);
	}
	
	//제출 기타 보기
	@PostMapping(value = "stuETC")
	public ModelAndView stuETC(@ModelAttribute BoardSubmitBeanHS bsb) {
		return mc.stuETCCtl(bsb);
	}
	
}

