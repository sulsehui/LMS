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
	public void createLecture(@RequestBody List<SubjectBean> sb,@RequestBody List<GradeBean> gb) {
		System.out.println(sb.get(0).getCsName()+sb.get(0).getStartDate()+sb.get(0).getEndDate());
		
		for(int i=0; i<gb.size(); i++) {
			System.out.println(gb.get(i).getItemCode()+gb.get(i).getItemPercent()+gb.get(i).getItemName());
		}
	}
	
}
