package eljl.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eljl.factory.bean.UserInfoBean;
import eljl.service.member.LoginService;

@RestController
@RequestMapping("/member")
public class HomeAjaxController {

	@Autowired
	LoginService ls;
	

	
}
