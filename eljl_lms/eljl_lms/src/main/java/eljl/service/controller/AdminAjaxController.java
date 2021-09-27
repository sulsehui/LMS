package eljl.service.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import eljl.factory.bean.UserInfoBean;
import eljl.lms.admin.AdminManagement;


@RestController
@RequestMapping("/admin")
public class AdminAjaxController {

	@Autowired
	AdminManagement am;
	
	ModelAndView mav;
	//전체 멤버리스트 가져오기
		@PostMapping("/allMemberList")
		public List<UserInfoBean> allMemberList(@RequestBody List<UserInfoBean> ub) {
			return am.allMemberListCtl(ub.get(0)); 
		}
		
		
	//교사 인증 : 대기->대기완료
		@PostMapping("/teacherAuth")
		public Map<String,String> teacherAuth(@RequestBody List<UserInfoBean> ub) {
			return am.teacherAuthCtl(ub.get(0)); 
		}
		
	//교사 학생 정지 버튼 선택
		@PostMapping("/permission")
		public Map<String,String> permission(@RequestBody List<UserInfoBean> ub) {

			return am.permissionCtl(ub.get(0)); 
		}
		
	//현재 비밀번호 가져오기
		@PostMapping("/getNowPw")
		public ModelAndView getNowPw(@RequestBody List<UserInfoBean> ub) {
			 am.getNowPwCtl(ub); 
			return mav;
		}
		
}
