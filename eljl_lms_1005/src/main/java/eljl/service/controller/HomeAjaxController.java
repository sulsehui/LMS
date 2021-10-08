package eljl.service.controller;

import java.util.List;

import javax.security.auth.Subject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eljl.factory.bean.ClassBean;
import eljl.factory.bean.GradeBean;
import eljl.factory.bean.SubjectBean;
import eljl.factory.bean.UserInfoBean;
import eljl.service.member.LoginService;

@RestController
@RequestMapping("/member")
public class HomeAjaxController {

	@Autowired
	LoginService ls;
	
	//createLecture
	@PostMapping("/createLecture")
	public List<SubjectBean> createLecture(@RequestBody List<SubjectBean> sb) {
		return null;
	
	}
	
}
