package eljl.service.controller;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import eljl.factory.bean.BoardCreateBean;
import eljl.factory.bean.CalendarBean;
import eljl.factory.bean.HistoryBean;
import eljl.factory.bean.NoticeBean;
import eljl.factory.bean.StuManageBean;
import eljl.factory.bean.SubjectBean;
import eljl.factory.bean.UserInfoBean;
import eljl.factory.util.ProjectUtils;
import eljl.lms.dashboard.Calendar;
import eljl.lms.dashboard.MainService;

@Controller
public class MainController {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	MainService ms;
	
	@Autowired
	ProjectUtils pu;
	
	@Autowired
	Calendar cd;
	
	//메인 페이지로 이동
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String main(Locale locale, Model model) {
		return "main";
	}
	
	// 스트림 페이지로 이동
	@RequestMapping(value = "/streamForm", method = RequestMethod.GET)
	public String streamhome(Locale locale, Model model) {
		return "stream";
	}
	
	// 스트림 페이지로 이동
		@RequestMapping(value = "/stream", method = RequestMethod.GET)
		public String stream(Locale locale, Model model) {
			return "stream";
		}
	
	// 마이페이지로 이동
	@RequestMapping(value = "/myPage", method = RequestMethod.GET)
	public String myPage(Locale locale, Model model) {
		return "myPage";
	}
	
	// 마이페이지로 이동
	@RequestMapping(value = "/setting", method = RequestMethod.GET)
	public String setting(Locale locale, Model model) {
		return "setting";
	}
	
	
	// 클래스 삭제
	@PostMapping("/deleteClass")
	@ResponseBody
	public List<StuManageBean> deleteClass(@ModelAttribute SubjectBean sb) {
		return ms.deleteClassCtl(sb);
	}
	
	// 캘린더 From 양식
	@RequestMapping(value = "/calendar", method = RequestMethod.GET)
	public String calendarForm() {
			return "calendar";
	}
	
	//캘린더 일정 가져오기
	@PostMapping("/getScheduleList")
	@ResponseBody
	public List<CalendarBean> getScheduleList(@ModelAttribute StuManageBean smb) {
			return cd.getScheduleListCtl(smb); 
	}
	
	//공지사항 이동
	@PostMapping("/NoticeOutput")
	public ModelAndView NoticeOutput(NoticeBean nb) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("stream");
		return mav;
	}
	
	//스트림 이동
	@PostMapping("/moveStream")
	public ModelAndView moveStream(NoticeBean nb) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("mbId", nb.getMbId());
		mav.addObject("csCode", nb.getCsCode());
		mav.addObject("opCode", nb.getOpCode());
		mav.addObject("opName", nb.getOpName());
		
		mav.setViewName("main");
		
		try {
			if(((String)pu.getAttribute("mbType")).equals("T")) {
				mav.setViewName("stream");
			}else {
				mav.setViewName("streamS");
			}
			mav.addObject("mbType", pu.getAttribute("mbType"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return mav;
	}
	
	//수업 이동
		@PostMapping("/moveClass")
		public ModelAndView moveClass(NoticeBean nb) {
			ModelAndView mav = new ModelAndView();
			mav.addObject("mbId", nb.getMbId());
			mav.addObject("csCode", nb.getCsCode());
			mav.addObject("opCode", nb.getOpCode());
			mav.addObject("opName", nb.getOpName());
			try {
				mav.addObject("mbType", pu.getAttribute("mbType"));
			} catch (Exception e) {
				e.printStackTrace();
			}
			mav.setViewName("class");
			return mav;
		}
	
		//출석 이동
		@PostMapping("/moveAttend")
		public ModelAndView moveAttend(NoticeBean nb) {
			ModelAndView mav = new ModelAndView();
			mav.addObject("mbId", nb.getMbId());
			mav.addObject("csCode", nb.getCsCode());
			mav.addObject("opCode", nb.getOpCode());
			mav.addObject("opName", nb.getOpName());
			mav.setViewName("attendance");
			try {
				mav.addObject("mbType", pu.getAttribute("mbType"));
			} catch (Exception e) {
				e.printStackTrace();
			}
			return mav;
		}
	
		//학생관리 이동
		@PostMapping("/moveStuManage")
		public ModelAndView moveStuManage(NoticeBean nb) {
			ModelAndView mav = new ModelAndView();
			mav.addObject("mbId", nb.getMbId());
			mav.addObject("csCode", nb.getCsCode());
			mav.addObject("opCode", nb.getOpCode());
			mav.addObject("opName", nb.getOpName());
			mav.setViewName("stuManage");
			try {
				mav.addObject("mbType", pu.getAttribute("mbType"));
			} catch (Exception e) {
				e.printStackTrace();
			}
			return mav;
		}
		
		//마이페이지 이동
		@PostMapping("/moveMyPage")
		public ModelAndView moveMyPage(NoticeBean nb) {
			ModelAndView mav = new ModelAndView();
			mav.addObject("mbId", nb.getMbId());
			mav.addObject("csCode", nb.getCsCode());
			mav.addObject("opCode", nb.getOpCode());
			mav.addObject("opName", nb.getOpName());
			mav.setViewName("myPage");
			try {
				mav.addObject("mbType", pu.getAttribute("mbType"));
			} catch (Exception e) {
				e.printStackTrace();
			}
			return mav;
		}
		
		//설정 이동
		@PostMapping("/moveSetting")
		public ModelAndView moveSetting(NoticeBean nb) {
			ModelAndView mav = new ModelAndView();
			mav.addObject("mbId", nb.getMbId());
			mav.addObject("csCode", nb.getCsCode());
			mav.addObject("opCode", nb.getOpCode());
			mav.addObject("opName", nb.getOpName());
			mav.setViewName("setting");
			try {
				mav.addObject("mbType", pu.getAttribute("mbType"));
			} catch (Exception e) {
				e.printStackTrace();
			}
			return mav;
		}
}
