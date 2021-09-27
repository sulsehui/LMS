package eljl.service.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eljl.factory.bean.StuManageBean;
import eljl.lms.teacher.AttendService;


@RestController
@RequestMapping("/Attendance")
public class TeacherAjaxController {

	@Autowired
	AttendService as;
	
	//학생 리스트 가져오기
		@PostMapping("/getStuList")
		public List<StuManageBean> getStuList(@ModelAttribute StuManageBean smb) {
			return as.getStuListCtl(smb); 
		}
		
	//전체 출석 리스트 가져오기(학생리스트, 날짜)
		@PostMapping("/allAttendList")
		public List<StuManageBean> allAttendList(@RequestBody List<StuManageBean> smb) {
			return as.allAttendListCtl(smb.get(0)); 
		}
		
	//출석체크
		@PostMapping("/sendAttendCheck")
		public Map<String,String>  sendAttendCheck(@RequestBody List<StuManageBean> smb) {
			return as.sendAttendCheckCtl(smb); 
		}
		
	//개인 학생 출석날짜 가져오기
		@PostMapping("/getEditDateList")
		public List<StuManageBean>  getEditDateList(@RequestBody List<StuManageBean> smb) {
			return as.getEditDateListCtl(smb.get(0)); 
		}
		
	//개인 학생 출석 상태 가져오기
		@PostMapping("/getBeforeStatus")
		public List<StuManageBean>  getBeforeStatus(@RequestBody List<StuManageBean> smb) {
			return as.getBeforeStatusCtl(smb.get(0)); 
		}
		
	//출석 수정하기
		@PostMapping("/getEditAttend")
		public Map<String, String>  getEditAttend(@RequestBody List<StuManageBean> smb) {
			return as.getEditAttendCtl(smb.get(0)); 
		}
}
