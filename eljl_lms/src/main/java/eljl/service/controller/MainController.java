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
import org.springframework.web.bind.annotation.ResponseBody;

import eljl.factory.bean.SubjectBean;
import eljl.factory.bean.UserInfoBean;
import eljl.lms.dashboard.MainService;

@Controller
public class MainController {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	MainService ms;
	
	@RequestMapping(value = "/streamForm", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		return "stream";
	}
	
	// 클래스 삭제
	@PostMapping("/deleteClass")
	@ResponseBody
	public List<SubjectBean> deleteClass(@ModelAttribute SubjectBean sb) {
		return ms.deleteClassCtl(sb);
	}
}
