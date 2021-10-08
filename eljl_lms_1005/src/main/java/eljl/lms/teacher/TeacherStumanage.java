package eljl.lms.teacher;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import eljl.factory.bean.JunseoBean;
import eljl.factory.bean.StuManageBean;
import eljl.factory.bean.SubjectBean;
import eljl.factory.util.Encryption;
import eljl.factory.util.ProjectUtils;

@Service
public class TeacherStumanage {

	@Autowired
	SqlSessionTemplate session;

	ModelAndView mav;

	@Autowired
	Encryption enc;

	@Autowired
	Gson gson;

	@Autowired
	ProjectUtils pu;

	public List<JunseoBean> allStuListCtl(StuManageBean smb) {
		List<JunseoBean> list;
		//전체 학생 리스트, 성적평균, 결석횟수, 상담횟수 select
		list = session.selectList("getAllStudentList", smb);
		try {
			for(int i=0 ; i<list.size(); i++) {
				list.get(i).setStuName(enc.aesDecode(list.get(i).getStuName(), list.get(i).getStuId()));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
	// 성적 목록 가져오기
	public List<JunseoBean> selectGradeListCtl(StuManageBean smb) {
		//List<JunseoBean> list;
		//ArrayList<JunseoBean> jsbList = new ArrayList<JunseoBean>();
		return session.selectList("getGradeList", smb);
	}
	// 출결 목록 가져오기
	public List<JunseoBean> selectSiListCtl(StuManageBean smb) {	
		return session.selectList("getSignList", smb);
	}
	// 상담일지 목록 가져오기
	public List<JunseoBean> selectCoListCtl(StuManageBean smb) {	
		return session.selectList("getCounselingList", smb);
	}
	
	public List<JunseoBean> selectCosListCtl(StuManageBean smb) {
		return session.selectList("getCosList", smb);
	}
	

	public boolean updateStuAttCtl(JunseoBean jb) {
		boolean result = false;
		if(this.convertBoolean(session.update("updateStuSign", jb))) {
			result = true;
		}
		return result;
	}

	private boolean convertBoolean(int number) {
		return (number == 1)?true:false;
	}
	public boolean updateStuCounSelingCtl(JunseoBean jb) {
		boolean result = false;
		if(this.convertBoolean(session.update("updateStuCounSeling", jb))) {
			result = true;
		}
		return result;
	}
	public boolean deleteStuCounSelingCtl(JunseoBean jb) {
		boolean result = false;
		if(this.convertBoolean(session.delete("deleteStuCounSeling", jb))) {
			result = true;
		}
		return result;
	}
	public boolean insertStuCounSelingCtl(JunseoBean jb) {
		boolean result =false;
		jb.setCoCode(this.makeCode(jb));
		if(this.convertBoolean(session.insert("insertStuCounSeling", jb))) {
			result = true;
		}
		return result;
	}
	public String makeCode(JunseoBean jb) {
		Date day = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String date = sdf.format(day);
		int number = 0;
		String code;
		if(session.selectOne("getMaxCoCode").equals(0)) {
			code = 1+"";
		}else {
			number = session.selectOne("getMaxCoCode");    
		}	        
		code = (number+1)+"";

		for(int i=code.length(); i<3; i++) {
			code = "0"+code;
		}
		return date + code;
	}
	public boolean updateStuGrade(List<JunseoBean> jbList) {
		boolean result = false;
		for(int i=0 ;i<jbList.size();i++) {
			if(jbList.get(i).getSsCode().substring(0,1).equals("E")) {
				if(this.convertBoolean(session.update("updateEtcGrade",jbList.get(i)))){
					result =true;
				}
			}else if(jbList.get(i).getSsCode().substring(0,1).equals("T")) {
				if(this.convertBoolean(session.update("updateTaskGrade",jbList.get(i)))){
					result =true;
				}
			}else if(jbList.get(i).getSsCode().substring(0,1).equals("Q")) {
				if(this.convertBoolean(session.update("updateQuizGrade",jbList.get(i)))){
					result =true;
				}
			}
		}
		return result;
	}
	public boolean yesOrNoCosCtl(JunseoBean jb) {
		boolean result = false;
		if(this.convertBoolean(session.update("yesOrNoCos",jb))){
			result = true;
		}
		return result;
	}



}
