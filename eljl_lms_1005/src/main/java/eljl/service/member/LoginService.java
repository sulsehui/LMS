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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.jstl.sql.Result;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import eljl.database.mapper.memberMapper;
import eljl.factory.bean.HistoryBean;
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
			mav.addObject("message","선생님 회원가입을 성공했습니다");
			mav.setViewName("login");
		}else {
			mav.addObject("message","회원가입을 실패했습니다");
			mav.setViewName("/:redirect");
		}
		return mav;
	}


	// 로그인(선생님,학생)
	public ModelAndView loginCtl(HistoryBean hb) {
		mav = new ModelAndView();
		boolean check = false;

		// T
		if(hb.getMbType().equals("T")){
			
			// 관리자 검사
			if(this.convertData(session.selectOne("adminCheck", hb))) {
				
				String encTePw = session.selectOne("getAmPw",hb);
				System.out.println(encTePw);
				System.out.println(hb.getMbPw());
				
				if(hb.getMbPw().equals(encTePw)) {
					if(check = this.insAmHistory(hb)) {
						mav.addObject("publicIp", hb.getPublicIp());
						mav.addObject("privateIp", hb.getPrivateIp());
						mav.addObject("mbType","M");
						mav.addObject("message","환영합니다 관리자님");
						mav.setViewName("admin");
						try {
							pu.setAttribute("mbId", hb.getMbId());
							pu.setAttribute("mbType", "M");
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}else {
					mav.setViewName("login");
					mav.addObject("message","아이디나 비밀번호를 확인해주세요");
				}
				

			
			// 선생님
			}else {
				if(convertData(session.selectOne("isTeId",hb))) {
					String encTePw = session.selectOne("getTePw",hb);
					if(enc.matches(hb.getMbPw(),encTePw)) {
						if(this.convertData(session.selectOne("teStatusCheck",hb))) {
							if(check = this.insTeHistory(hb)) {
								mav.addObject("publicIp", hb.getPublicIp());
								mav.addObject("privateIp", hb.getPrivateIp());
								mav.addObject("mbType",hb.getMbType());
								mav.setViewName("main");
								try {
									pu.setAttribute("mbId", hb.getMbId());
									pu.setAttribute("mbType", hb.getMbType());
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						}else {
							mav.setViewName("login");
							mav.addObject("message","정지 또는 인증이 안된 계정입니다 관리자에게 문의하세요.");
						}
					}else {
						mav.setViewName("login");
						mav.addObject("message","아이디나 비밀번호를 확인해주세요");
					}
				}else {
					mav.setViewName("login");
					mav.addObject("message","아이디나 비밀번호를 확인해주세요");
				}
			}
			// S
		}else if(hb.getMbType().equals("S")){
			if(convertData(session.selectOne("isStuId",hb))) {
				String encTePw = session.selectOne("getStuPw",hb);
				if(enc.matches(hb.getMbPw(),encTePw)) {
					if(this.convertData(session.selectOne("stuStatusCheck",hb))) {
						if(check = this.insStuHistory(hb)) {
							mav.addObject("publicIp", hb.getPublicIp());
							mav.addObject("privateIp", hb.getPrivateIp());
							mav.addObject("mbType",hb.getMbType());
							mav.setViewName("main");
							try {
								pu.setAttribute("stuId", hb.getMbId());
								pu.setAttribute("mbType", hb.getMbType());
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}else {
						mav.setViewName("login");
						mav.addObject("message","정지된 계정입니다.");
					}
				}
				else {
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
	
	//history(교사) 
	boolean insAmHistory(HistoryBean hb) {
		return convertData(session.insert("insAmHistory", hb));
	}
	
	//history(교사) 
	boolean insTeHistory(HistoryBean hb) {
		return convertData(session.insert("insTeHistory", hb));
	}

	//history(학생) 
	boolean insStuHistory(HistoryBean hb) {
		return convertData(session.insert("insStuHistory", hb));
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
			mav.addObject("message","학생 회원가입을 성공했습니다");
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
		System.out.println(ub);
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
		String contents = "";

		String subject = "[eljl_LMS] 비밀번호를 변경해주세요!";
		if(ub.getMbType().equals("T")) {
			contents = this.getTeAccessPw(ub.getMbId())+"로 인증해주세요";
		}else {
			contents = this.getStuAccessPw(ub.getMbId())+"로 인증해주세요";
		}

		String from = "ssh994@naver.com";

		String to = ub.getMbMail();

		MimeMessage mail = javaMail.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mail,"UTF-8");

		try {
			helper.setFrom(from);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(contents, true);
			javaMail.send(mail);
			map.put("message", ub.getMbMail()+"로 이메일이 전송 되었습니다.");
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return map;
	}

	//메일에 인증키 출력(교사)
	private String getTeAccessPw(String mbId) {
		return session.selectOne("getTeAuth", mbId);
	}

	//메일에 인증키 출력(학생)
	private String getStuAccessPw(String mbId) {
		return session.selectOne("getStuAuth", mbId);
	}

	// 인증키 검사
	public Map<String,String> sendAuth(UserInfoBean ub) {
		Map<String, String> map = new HashMap<String, String>();

		if(ub.getMbAuth().equals(this.getTeAccessPw(ub.getMbId()))) {
			map.put("message", "true");
		}else if(ub.getMbAuth().equals(this.getStuAccessPw(ub.getMbId()))){
			map.put("message", "true");
		}else {
			map.put("message", "false");
		}

		return map;
	}

	// 새 비밀번호 변경 update
	public ModelAndView sendNewPW(UserInfoBean ub) {
		ModelAndView mav = new ModelAndView();
		String result = "비밀번호 변경 실패하였습니다.";
		mav.setViewName("login");
		
		System.out.println(ub.getMbType());
		
		if(ub.getMbType().equals("M")) {
			
			try {
				ub.setMbId((String)pu.getAttribute("mbId"));
			} catch (Exception e) {
				e.printStackTrace();
			}
			this.updateAdPw(ub);
			result = "관리자 비밀번호가 변경 되었습니다.";
			mav.setViewName("admin");
			
		}else {
			try {
				ub.setMbPw(enc.encode(ub.getMbPw()));
			}catch (Exception e) {
				e.printStackTrace();
			}
			if(updateTePw(ub)) {
				result = "선생님 비밀번호가 변경되었습니다.";
			} else if(updateStuPw(ub)) {
				result = "학생 비밀번호가 변경 되었습니다.";
			}
		}
		

		mav.addObject("message",result);
		return mav;
	}

	private boolean updateTePw(UserInfoBean ub) {
		return this.convertData(session.update("updateTeNewAuth", ub));
	}

	private boolean updateStuPw(UserInfoBean ub) {
		return this.convertData(session.update("updateStuNewAuth", ub));
	}
	
	private boolean updateAdPw(UserInfoBean ub) {
		return this.convertData(session.update("updateAdNewAuth", ub));
	}


	// 로그아웃
	public ModelAndView LogOutCtl(HistoryBean hb) {
		boolean check = false;
		mav = new ModelAndView();
		
		try {
			hb.setMbType((String)pu.getAttribute("mbType"));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if(hb.getMbType().equals("T")) {
			try {
				if(pu.getAttribute("mbId") != null) {
					hb.setMbId((String)pu.getAttribute("mbId"));
					while(!check) {
						check = this.convertData(session.insert("insTeHistory",hb));
					}
					pu.removeAttribute("mbId");
					mav.setViewName("login");
					//mav.setViewName("redirect:/");
					//pu.setAttribute("message", "정상적으로 로그아웃 성공");
					mav.addObject("message", "정상적으로 로그아웃 성공");

				}else {
					mav.setViewName("redirect:/");
					//pu.setAttribute("message", "이미 로그아웃 되었습니다");
					mav.addObject("message", "이미 로그아웃 되었습니다");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(hb.getMbType().equals("S")) {
			try {
				if(pu.getAttribute("stuId") != null) {
					hb.setMbId((String)pu.getAttribute("stuId"));
					while(!check) {
						check = this.convertData(session.insert("insStuHistory",hb));
					}
					pu.removeAttribute("mbId");
					mav.setViewName("login");
					//mav.setViewName("redirect:/");
					pu.setAttribute("message", "정상적으로 로그아웃 성공");

				}else {
					mav.setViewName("redirect:/");
					pu.setAttribute("message", "이미 로그아웃 되었습니다");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		}else {
			try {
				if(pu.getAttribute("mbId") != null) {
					hb.setMbId((String)pu.getAttribute("mbId"));
					while(!check) {
						check = this.convertData(session.insert("insAmHistory",hb));
					}
					pu.removeAttribute("mbId");
					mav.setViewName("login");
					//mav.setViewName("redirect:/");
					pu.setAttribute("message", "정상적으로 로그아웃 성공");

				}else {
					mav.setViewName("redirect:/");
					pu.setAttribute("message", "이미 로그아웃 되었습니다");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return mav;
	}


	public ModelAndView rootCtl(UserInfoBean ub) {
		mav = new ModelAndView();
		try {
			// 교사
			if(pu.getAttribute("mbId") != null) {
				if(((String)pu.getAttribute("mbType")).equals("T")) {
					if(pu.getAttribute("mbId") != null && ub.getMbId() != null) {
						
						mav.setViewName("main");
						//mav.addObject("umail",enc.aesDecode((String)pu.getAttribute("umail"), (String)pu.getAttribute("uCode")));
						
						if(!this.convertData(session.selectOne("sumTeHistory",ub))) {
							pu.removeAttribute("mbId");
							pu.removeAttribute("mbType");
							mav.setViewName("login");
						}
						
					}else if(pu.getAttribute("mbId") != null) {
						mav.setViewName("main");
						//mav.addObject("umail",enc.aesDecode((String)pu.getAttribute("umail"), (String)pu.getAttribute("uCode")));
					}
					
					else {
						mav.setViewName("login");
					}
				
				// 관리자
				}else if(((String)pu.getAttribute("mbType")).equals("M")){
					if(pu.getAttribute("mbId") != null && ub.getMbId() != null) {
						
						mav.setViewName("admin");
						//mav.addObject("umail",enc.aesDecode((String)pu.getAttribute("umail"), (String)pu.getAttribute("uCode")));
						
						if(!this.convertData(session.selectOne("sumAmHistory",ub))) {
							pu.removeAttribute("mbId");
							mav.setViewName("login");
						}
						
					}else if(pu.getAttribute("mbId") != null) {
						mav.setViewName("admin");
						//mav.addObject("umail",enc.aesDecode((String)pu.getAttribute("umail"), (String)pu.getAttribute("uCode")));
					}
					
					else {
						mav.setViewName("login");
					}
				}else {
					mav.setViewName("login");
				}
				
				
			}else if(pu.getAttribute("stuId") != null) {
				// 학생
				if(((String)pu.getAttribute("mbType")).equals("S")){
					if(pu.getAttribute("stuId") != null && ub.getMbId() != null) {
						mav.setViewName("main");
						//mav.addObject("umail",enc.aesDecode((String)pu.getAttribute("umail"), (String)pu.getAttribute("uCode")));
						
						if(!this.convertData(session.selectOne("sumStuHistory",ub))) {
							pu.removeAttribute("mbId");
							pu.removeAttribute("mbType");
							mav.setViewName("login");
						}
						
					}else if(pu.getAttribute("stuId") != null) {
						mav.setViewName("main");
						//mav.addObject("umail",enc.aesDecode((String)pu.getAttribute("umail"), (String)pu.getAttribute("uCode")));
					}
					
					else {
						mav.setViewName("login");
					}	
				}else {
					mav.setViewName("login");
				}
				
			}else {
				mav.setViewName("login");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return mav;
	}


}
