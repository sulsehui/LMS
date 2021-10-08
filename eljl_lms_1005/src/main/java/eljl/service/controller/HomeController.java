package eljl.service.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import eljl.factory.bean.HistoryBean;
import eljl.factory.bean.UserInfoBean;
import eljl.factory.util.ProjectUtils;
import eljl.service.member.LoginService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	LoginService ls;

	ModelAndView mav;

	@Autowired
	ProjectUtils pu;

	//메인
	@RequestMapping(value = "/", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView home(@ModelAttribute UserInfoBean ub) {
		return  ls.rootCtl(ub);
	}

	// 선생님 회원가입
	@PostMapping(value = "/joinTeacher")
	public ModelAndView joinTeacher(@ModelAttribute UserInfoBean ub) {
		return ls.joinTeacherCtl(ub);
	}

	// 학생 회원가입
	@PostMapping(value = "/joinStudent")
	public ModelAndView joinStudent(@ModelAttribute UserInfoBean ub) {
		return ls.joinStudentCtl(ub);
	}

	// 비밀번호 변경페이지로 이동
	@RequestMapping(value = "/pwChangeForm", method = RequestMethod.GET)
	public String pwChangeForm() {
		return "pwChangeForm";
	}

	// 로그인 페이지로 이동
	@RequestMapping(value = "/loginForm", method = RequestMethod.GET)
	public String loginForm() {
		try {
			pu.removeAttribute("mbId");
			pu.removeAttribute("message");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "login";
	}

	//회원가입폼으로 가기
	@RequestMapping(value = "/joinTeForm", method = RequestMethod.GET)
	public String joinTeForm() {
		return "joinTeacher";
	}

	//학생가입폼으로 가기
	@RequestMapping(value = "/joinStuForm", method = RequestMethod.GET)
	public String joinStuForm() {
		return "joinStudent";
	}

	//중복체크
	@PostMapping("/dupCheck")
	@ResponseBody
	public String dupCheck(@ModelAttribute UserInfoBean ub) {
		return ls.dupCheck(ub);
	}

	// 로그인
	@PostMapping(value = "login")
	public ModelAndView login(@ModelAttribute HistoryBean hb) {
		return ls.loginCtl(hb);
	}

	// 로그아웃
	@PostMapping(value = "/logOut")
	public ModelAndView logOut(@ModelAttribute HistoryBean hb){
		System.out.println(hb);
		return ls.LogOutCtl(hb);
	}

	//비밀번호 변경 인증
	@PostMapping("/usAuthen")
	@ResponseBody
	public String usAuthen(@ModelAttribute UserInfoBean ub) {
		return ls.usAuthen(ub);
	}

	//비밀번호 변경 이메일 전송
	@PostMapping("/pwChangeMail")
	@ResponseBody
	public Map<String,String> pwChangeMail(@ModelAttribute UserInfoBean ub) {
		return ls.pwChangeMail(ub);
	}

	//인증키 서버에 전송
	@PostMapping("/sendAuth")
	@ResponseBody
	public Map<String,String> sendAuth(@ModelAttribute UserInfoBean ub) {
		return ls.sendAuth(ub);
	}

	//새 비밀번호 전송
	@PostMapping("/sendNewPW")
	public ModelAndView sendNewPW(@ModelAttribute UserInfoBean ub) {
		return ls.sendNewPW(ub);
	}

	//파일 다운로드
	@PostMapping("/downLoadFile")
	public void downLoadFile(@ModelAttribute UserInfoBean ub, HttpServletResponse res, HttpServletRequest req) {
		System.out.println("스티커 ::" + ub.getStickerPath());  
		String saveDir = 
				"C:\\coding\\springProject\\eljl_final\\src\\main\\webapp\\resources\\images";
		String fileName = ub.getStickerPath();

		File file = new File(saveDir+"/"+fileName);

		FileInputStream fis = null;
		BufferedInputStream bis = null;
		ServletOutputStream sos = null;
		try {
			fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis);
			sos = res.getOutputStream();

			String reFileName = "";
			System.out.println(fileName);
			System.out.println(saveDir+"/"+fileName);
			/*
			         reFileName = URLEncoder.encode(fileName,"UTF-8");
			         reFileName = reFileName.replaceAll("\\+", "%20");*/

			reFileName = new String(fileName.getBytes("utf-8"), "ISO-8859-1");

			res.setContentType("application/octet-stream;charset=utf-8");
			res.addHeader("Content-Disposition", "attachment;filename=\""+reFileName+"\"");
			res.setContentLength((int) file.length());

			int read = 0;
			while((read=bis.read()) != -1) {
				sos.write(read);
			}

			mav.setViewName("/:redirect");
			mav.addObject("file", file);
		} catch (Exception e) {e.printStackTrace();
		}finally {
			try {
				sos.close();
				bis.close();

			}catch(Exception e) {
				e.printStackTrace();
			}
		}


	}




}

