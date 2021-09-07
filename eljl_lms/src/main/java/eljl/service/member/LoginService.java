package eljl.service.member;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import eljl.database.mapper.memberMapper;
import eljl.factory.bean.UserInfoBean;
import eljl.factory.util.Encryption;

@Service
public class LoginService {
	
	@Autowired
	SqlSessionTemplate session;
	
	ModelAndView mav;
	
	@Autowired
	Encryption enc;
	
	@Autowired
	Gson gson;
	
	public ModelAndView joinTeacherCtl(UserInfoBean ub) {
		mav = new ModelAndView();
		try {
			ub.setMbName(enc.aesEncode(ub.getMbName(), ub.getMbId()));
			ub.setMbPhone(enc.aesEncode(ub.getMbPhone(), ub.getMbId()));
			ub.setMbMail(enc.aesEncode(ub.getMbMail(), ub.getMbId()));
			ub.setMbPw(enc.encode(ub.getMbPw()));
		}catch (Exception e) {
			e.printStackTrace();
		}
	
		if(convertData(session.insert("join",ub))) {
			mav.setViewName("login");
		}else {
			mav.addObject("message","회원가입을 실패했습니다");
			mav.setViewName("/:redirect");
		}
		return mav;
	}

	public ModelAndView loginCtl(UserInfoBean ub) {
		mav = new ModelAndView();
		
		// T
		if(ub.getMbType().equals("T")){
			if(convertData(session.selectOne("isTeId",ub))) {
				String encTePw = session.selectOne("getTePw",ub);
				
				//if(encTePw.equals(ub.getMbPw())) {
				if(enc.matches(ub.getMbPw(),encTePw)) {
					mav.setViewName("main");
				}else {
					mav.setViewName("login");
					mav.addObject("message","아이디나 비밀번호를 확인해주세요");
				}
			}
		}
		
		// S
		
		return mav;
	}
	
	public boolean convertData(int data) {
		return (data > 0)? true:false;
	}
	
	// 아이디 중복체크
	public String dupCheck(UserInfoBean ub) {
		System.out.println(ub.getMbId());
		boolean message = false;
		if(!(convertData(session.selectOne("dupCheck", ub)))){
			message = true;
		}
		return gson.toJson(message);
	}
}
