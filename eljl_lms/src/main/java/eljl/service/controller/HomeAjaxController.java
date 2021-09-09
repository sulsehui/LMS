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
	public void createLecture(@RequestBody List<SubjectBean> sb) {
		System.out.println(sb.get(0).getCsName()+sb.get(0).getStartDate()+sb.get(0).getEndDate());
		
	
			System.out.println(sb.get(0).getGbList().get(0).getItemCode());
			System.out.println(sb.get(0).getGbList().get(0).getItemPercent());
			System.out.println(sb.get(0).getGbList().get(0).getItemName());
	
	}
	
}
