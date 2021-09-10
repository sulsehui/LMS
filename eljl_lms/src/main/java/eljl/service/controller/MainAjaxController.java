package eljl.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eljl.factory.bean.SubjectBean;
import eljl.lms.dashboard.MainService;

@RestController
@RequestMapping("/dashboard")
public class MainAjaxController {
	
	@Autowired
	MainService ms;
	
	
	@PostMapping("/createLecture")
	public List<SubjectBean> createLecture(@RequestBody List<SubjectBean> sb) {
		for(int i=0; i<sb.get(0).getGbList().size(); i++) {
			if(sb.get(0).getGbList().get(i) != null) {
			}
		}
		return ms.createLectureCtl(sb.get(0)); 
	}
	
	@PostMapping("/getMyLectureList")
	public List<SubjectBean> getMyLectureList(@RequestBody List<SubjectBean> sb) {
		return ms.getMyLectureListCtl(sb.get(0)); 
	}
	
}
