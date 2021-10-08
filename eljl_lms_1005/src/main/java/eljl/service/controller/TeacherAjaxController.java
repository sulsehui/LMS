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

import eljl.factory.bean.GradeBean;
import eljl.factory.bean.JunseoBean;
import eljl.factory.bean.StuManageBean;
import eljl.factory.bean.SubjectBean;
import eljl.lms.teacher.AttendService;
import eljl.lms.teacher.TeacherSetting;
import eljl.lms.teacher.TeacherStumanage;

@RestController
@RequestMapping("/teacher")
public class TeacherAjaxController {

	@Autowired
	TeacherSetting ts;
	
	@Autowired
	TeacherStumanage tsm;
	
	@Autowired
	AttendService as;
	
	//이전 개설 강좌 내용 가져오기
	@PostMapping("/getSetting")
	public List<SubjectBean> getSetting(@RequestBody List<SubjectBean> sb) {
		return ts.getSetting(sb.get(0));
	}
	
	//개설 강좌 내용 수정 하기
	@PostMapping("/updateSubject")
	public Map<String, String> updateSubject(@RequestBody List<SubjectBean> sb) {
		return ts.updateSubject(sb.get(0));
	}
	
	// 이전 성적 관리 기준 내용 가져오기
	@PostMapping("/getScoreStandard")
	public List<GradeBean> getScoreStandard(@RequestBody List<SubjectBean> sb) {
		return ts.getScoreStandard(sb.get(0));
	}
	
	//전체 학생 리스트 불러오기
		@PostMapping("/allStuList")
		public List<JunseoBean> allStuList(@RequestBody StuManageBean smb) {
			return tsm.allStuListCtl(smb); 
		}
		@PostMapping("/selectGradeList")
		public List<JunseoBean> selectGradeList(@RequestBody StuManageBean smb) {
		//	System.out.println("성적:  "+ts.selectGradeListCtl(smb));
			return tsm.selectGradeListCtl(smb); 
		}
		
		@PostMapping("/selectSiList")
		public List<JunseoBean> selectSiList(@RequestBody StuManageBean smb) {
		//	System.out.println("출결:  "+ts.selectSiListCtl(smb));
			return tsm.selectSiListCtl(smb); 
		}
		
		@PostMapping("/selectCoList")
		public List<JunseoBean> selectCoList(@RequestBody StuManageBean smb) {
		//	System.out.println("상담일지:  "+ts.selectCoListCtl(smb));
			return tsm.selectCoListCtl(smb); 
		}
		
		@PostMapping("/selectCosList")
		public List<JunseoBean> selectCosList(@RequestBody StuManageBean smb) {
		//	System.out.println("상담일지:  "+ts.selectCoListCtl(smb));
			return tsm.selectCosListCtl(smb); 
		}
		
		@PostMapping("/updateStuAtt")
		public boolean updateStuAtt(@RequestBody JunseoBean jb) {
			return tsm.updateStuAttCtl(jb); 
		}
		
		@PostMapping("/updateStuCounSeling")
		public boolean updateStuCounSeling(@RequestBody JunseoBean jb) {
			return tsm.updateStuCounSelingCtl(jb); 
		}
		
		@PostMapping("/deleteStuCounSeling")
		public boolean deleteStuCounSeling(@RequestBody JunseoBean jb) {
			return tsm.deleteStuCounSelingCtl(jb); 
		}
		
		@PostMapping("/insertStuCounSeling")
		public boolean insertStuCounSeling(@RequestBody JunseoBean jb) {
			return tsm.insertStuCounSelingCtl(jb); 
		}
		
		@PostMapping("/updateStuGrade")
		public boolean updateStuGrade(@RequestBody List<JunseoBean> jbList) {
			return tsm.updateStuGrade(jbList); 
		}
		
		@PostMapping("/yesOrNoCos")
		public boolean yesOrNoCos(@RequestBody JunseoBean jb) {
			return tsm.yesOrNoCosCtl(jb); 
		}
		

		//학생 리스트 가져오기
				@PostMapping("/getStuList")
				public List<StuManageBean> getStuList(@RequestBody List<StuManageBean> smb) {
					return as.getStuListCtl(smb.get(0)); 
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
			// 한 학생 출석 가져오기
				@PostMapping("/selectStuStatus")
				public List<StuManageBean>  selectStuStatus(@RequestBody List<StuManageBean> smb) {
					return as.selectStuStatusCtl(smb.get(0)); 
				}
				
				// 백분율 추가 설정
				@PostMapping("/plusScoreStandard")
				public Map<String, String>  plusScoreStandard(@RequestBody List<GradeBean> gb) {
						 return ts.plusScoreStandard(gb);
				}
				
				// 백분율 삭제 설정
				@PostMapping("/minusScoreStandard")
				public Map<String, String>  minusScoreStandard(@RequestBody List<GradeBean> gb) {
						return ts.minusScoreStandard(gb);
				}
				
				// 백분율 업데이트 설정
				@PostMapping("/updateScoreStandard")
				public Map<String, String>  updateScoreStandard(@RequestBody List<GradeBean> gb) {
						return ts.updateScoreStandard(gb);
				}
}
