package eljl.service.member;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import eljl.database.mapper.memberMapper;
import eljl.factory.bean.UserInfoBean;
import eljl.factory.util.Encryption;
import eljl.factory.util.ProjectUtils;


@Service
public class LoginService {

	@Autowired
	SqlSessionTemplate session;

	ModelAndView mav;

	@Autowired
	Encryption enc;

	@Autowired
	Gson gson;

	@Autowired
	JavaMailSenderImpl javaMail;
	
	@Autowired
	ProjectUtils pu;
	
	// 선생님 회원가입
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
		ub.setStickerPath(pu.savingFile(ub.getMbFile()));
		
		if(convertData(session.insert("joinTe",ub))) {
			mav.setViewName("login");
		}else {
			mav.addObject("message","회원가입을 실패했습니다");
			mav.setViewName("/:redirect");
		}
		return mav;
	}

	// 로그인(선생님,학생)
	public ModelAndView loginCtl(UserInfoBean ub) {
		mav = new ModelAndView();

		// T
		if(ub.getMbType().equals("T")){
			if(convertData(session.selectOne("isTeId",ub))) {
				String encTePw = session.selectOne("getTePw",ub);
				if(enc.matches(ub.getMbPw(),encTePw)) {
					mav.setViewName("main");
					try {
						pu.setAttribute("mbId", ub.getMbId());
					} catch (Exception e) {
						e.printStackTrace();
					}
				}else {
					mav.setViewName("login");
					mav.addObject("message","아이디나 비밀번호를 확인해주세요");
				}
			}else {
				mav.setViewName("login");
				mav.addObject("message","아이디나 비밀번호를 확인해주세요");
			}
		// S
		}else if(ub.getMbType().equals("S")){
			if(convertData(session.selectOne("isStuId",ub))) {
				String encTePw = session.selectOne("getStuPw",ub);
				if(enc.matches(ub.getMbPw(),encTePw)) {
					mav.setViewName("main");
				}else {
					mav.setViewName("login");
					mav.addObject("message","아이디나 비밀번호를 확인해주세요");
				}
			}else {
				mav.setViewName("login");
				mav.addObject("message","아이디나 비밀번호를 확인해주세요");
			}
		}

		return mav;
	}

	public boolean convertData(int data) {
		return (data > 0)? true:false;
	}

	// 아이디 중복체크
	public String dupCheck(UserInfoBean ub) {
		boolean message = false;
		if(!(convertData(session.selectOne("dupCheckTe", ub)))
				&& !(convertData(session.selectOne("dupCheckStu", ub)))){
			message = true;
		}
		return gson.toJson(message);
	}


	// 학생 회원가입
	public ModelAndView joinStudentCtl(UserInfoBean ub) {
		mav = new ModelAndView();
		try {
			ub.setMbName(enc.aesEncode(ub.getMbName(), ub.getMbId()));
			ub.setMbMail(enc.aesEncode(ub.getMbMail(), ub.getMbId()));
			ub.setMbPhone(enc.aesEncode(ub.getMbPhone(), ub.getMbId()));
			ub.setMbPw(enc.encode(ub.getMbPw()));
		}catch (Exception e) {
			e.printStackTrace();
		}

		if(convertData(session.insert("joinStu",ub))){
			mav.setViewName("login");
		}else {
			mav.addObject("message","회원가입을 실패했습니다");
			mav.setViewName("/:redirect");
		}

		return mav;
	}

	//비밀번호 변경 인증(교사, 학생)
	public String usAuthen(UserInfoBean ub) {
		String mail= null;
		try {
			if(ub.getMbType().equals("T")) {
				mail = enc.aesDecode(session.selectOne("selectTeMail", ub), ub.getMbId());
			}else {
				mail = enc.aesDecode(session.selectOne("selectStuMail", ub), ub.getMbId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return gson.toJson(mail);
	}

	//비밀번호 변경 이메일 인증(교사, 학생)
	public Map<String,String> pwChangeMail(UserInfoBean ub) {
			Map<String, String> map = new HashMap<String, String>();
			String subject = "[eljl_LMS] 비밀번호를 변경해주세요!";
			String contents = "인증해주세요";
			String from = "mywptjd0127@naver.com";
			
			String to = ub.getMbMail();
			
			MimeMessage mail = javaMail.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mail,"UTF-8");
				
			try {
				helper.setFrom(from);
				helper.setTo(to);
				helper.setSubject(subject);
				helper.setText(contents,true);
				javaMail.send(mail);
				map.put("message", ub.getMbMail()+"로 이메일이 전송 되었습니다.");
			} catch (MessagingException e) {
				e.printStackTrace();
			}
			
			return map;
	}

	public Map<String,String> sendAuth(UserInfoBean ub) {
		 Map<String, String> map = new HashMap<String, String>();
		 System.out.println(ub.getMbId()+" "+ub.getMbAuth());
		 if(this.convertData(session.selectOne("getAuth", ub))) {
			 map.put("message", "true");
		 }else {
			 map.put("message", "false");
		 }
		 return map;
	}
	
	
}
