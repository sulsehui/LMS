package eljl.service.member;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import eljl.database.mapper.memberMapper;
import eljl.factory.bean.UserInfoBean;

@Service
public class LoginService {
	
	@Autowired
	SqlSessionTemplate session;
	
	ModelAndView mav;
	
	public void joinTeacherCtl(UserInfoBean ub) {
		session.insert("join",ub);
	}

	public ModelAndView loginCtl(UserInfoBean ub) {
		mav = new ModelAndView();
		
		//비밀번호 가져오기
		String encPw= session.selectOne("getPw",ub);
		
		//아이디 체크
		if(convertData(session.selectOne("getId",ub))) {
			System.out.println("아이디 맞음");
			if(ub.getMbPw().equals(encPw)) {
				System.out.println("비밀번호 맞음");
				mav.setViewName("main");
			}else {
				mav.setViewName("login");
				mav.addObject("message","아이디나 비밀번호를 확인하세요");
			}
		}else {
			mav.setViewName("login");
			mav.addObject("message","아이디나 비밀번호를 확인하세요");
		}
		
		return mav;
	}
	
	public boolean convertData(int data) {
		return (data > 0)? true:false;
	}
}
