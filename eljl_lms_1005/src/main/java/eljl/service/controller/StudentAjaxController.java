package eljl.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eljl.factory.bean.JunseoBean;
import eljl.factory.bean.StuManageBean;
import eljl.lms.student.myPageManage;

@RestController
@RequestMapping("/student")
public class StudentAjaxController {

	@Autowired
	myPageManage mpm;
	
	@PostMapping("/selectList")
	public JunseoBean selectList(@RequestBody StuManageBean smb) {
	//	System.out.println("성적:  "+ts.selectGradeListCtl(smb));
		return mpm.selectListCtl(smb); 
	}
	//전체 과제 + 성적 불러오기
	@PostMapping("/selectGradeList")
	public List<JunseoBean> selectGradeList(@RequestBody StuManageBean smb) {
	//	System.out.println("성적:  "+ts.selectGradeListCtl(smb));
		return mpm.selectGradeListCtl(smb); 
	}
	
	@PostMapping("/selectSiList")
	public List<JunseoBean> selectSiList(@RequestBody StuManageBean smb) {
	//	System.out.println("출결:  "+ts.selectSiListCtl(smb));
		return mpm.selectSiListCtl(smb); 
	}
	
	@PostMapping("/selectCosList")
	public List<JunseoBean> selectCosList(@RequestBody StuManageBean smb) {
	//	System.out.println("상담일지:  "+ts.selectCoListCtl(smb));
		return mpm.selectCosListCtl(smb); 
	}
	
	@PostMapping("/insertStuCo")
	public boolean insertStuCounSeling(@RequestBody JunseoBean jb) {
		return mpm.insertStuCounSelingCtl(jb); 
	}
	
	@PostMapping("/deleteCos")
	public boolean deleteCos(@RequestBody JunseoBean jb) {
		return mpm.deleteCosCtl(jb); 
	}
	
	@PostMapping("/updateCos")
	public boolean updateCos(@RequestBody JunseoBean jb) {
		return mpm.updateCosCtl(jb); 
	}
}
