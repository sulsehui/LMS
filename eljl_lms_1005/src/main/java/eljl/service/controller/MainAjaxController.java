package eljl.service.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eljl.factory.bean.BoardCreateBean;
import eljl.factory.bean.StuManageBean;
import eljl.factory.bean.SubjectBean;
import eljl.factory.bean.UserInfoBean;
import eljl.lms.dashboard.Calendar;
import eljl.lms.dashboard.MainService;

@RestController
@RequestMapping("/dashboard")
public class MainAjaxController {
	
	@Autowired
	MainService ms;
	
	@Autowired
	Calendar cd;
	
	//클래스 만들기
	@PostMapping("/createLecture")
	public Map<String, String> createLecture(@RequestBody List<SubjectBean> sb) {
		for(int i=0; i<sb.get(0).getGbList().size(); i++) {
			if(sb.get(0).getGbList().get(i) != null) {
			}
		}
		return ms.createLectureCtl(sb.get(0)); 
	}
	
	//내 클래스 리스트 가져오기
	@PostMapping("/getMyLectureList")
	public List<StuManageBean> getMyLectureList(@RequestBody List<SubjectBean> sb) {
		return ms.getMyLectureListCtl(sb.get(0)); 
	}
	
	//다른 클래스 리스트 가져오기
	@PostMapping("/getTotalLectureList")
	public List<StuManageBean> getTotalLectureList(@RequestBody List<SubjectBean> sb) {
		return ms.getTotalLectureListCtl(sb.get(0)); 
	}
	
	//수강신청
		@PostMapping("/accessClass")
		public Map<String, String> accessClass(@RequestBody List<StuManageBean> smb) {
			return ms.accessClassCtl(smb.get(0)); 
		}
		
	//수강 취소
		@PostMapping("/cancelClass")
		public Map<String, String> cancelClass(@ModelAttribute StuManageBean smb) {
			return ms.cancelClassCtl(smb); 
		}
	
}
