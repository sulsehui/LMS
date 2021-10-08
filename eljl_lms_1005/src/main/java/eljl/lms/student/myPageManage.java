package eljl.lms.student;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import eljl.factory.bean.JunseoBean;
import eljl.factory.bean.StuManageBean;
import eljl.factory.util.Encryption;
import eljl.factory.util.ProjectUtils;

@Service
public class myPageManage {

	@Autowired
	SqlSessionTemplate session;

	ModelAndView mav;

	@Autowired
	Encryption enc;

	@Autowired
	Gson gson;

	@Autowired
	ProjectUtils pu;

	public JunseoBean selectListCtl(StuManageBean smb) {
		try {
			smb.setStuId((String)pu.getAttribute("stuId"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return session.selectOne("getList", smb);
	}
	//성적 목록 가져오기
	public List<JunseoBean> selectGradeListCtl(StuManageBean smb) {
		try {
			smb.setStuId((String)pu.getAttribute("stuId"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return session.selectList("getStuGradeList", smb);
	}
	// 출결 목록 가져오기
	public List<JunseoBean> selectSiListCtl(StuManageBean smb) {
		try {
			smb.setStuId((String)pu.getAttribute("stuId"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return session.selectList("getStuSignList", smb);
	}
	// 상담일지 목록 가져오기
	public List<JunseoBean> selectCosListCtl(StuManageBean smb) {
		try {
			smb.setStuId((String)pu.getAttribute("stuId"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return session.selectList("getStuCosList", smb);
	}
	public boolean insertStuCounSelingCtl(JunseoBean jb) {
		boolean result =false;
		try {
			jb.setStuId((String)pu.getAttribute("stuId"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		jb.setCosCode(this.makeCode(jb));
		if(this.convertBoolean(session.insert("insertStuCos", jb))) {
			result = true;
		}
		return result;
	}
	public String makeCode(JunseoBean jb) {
		try {
			jb.setStuId((String)pu.getAttribute("stuId"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		Date day = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String date = sdf.format(day);
		int number = 0;
		String code;
		if(session.selectOne("getMaxCosCode").equals(0)) {
			code = 1+"";
		}else {
			number = session.selectOne("getMaxCosCode");    
		}	        
		code = (number+1)+"";

		for(int i=code.length(); i<3; i++) {
			code = "0"+code;
		}
		return date + code;
	}
	
	private boolean convertBoolean(int number) {
		return (number == 1)?true:false;
	}
	public boolean deleteCosCtl(JunseoBean jb) {
		try {
			jb.setStuId((String)pu.getAttribute("stuId"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		boolean result = false;
		if(this.convertBoolean(session.delete("deleteCos",jb))) {
			result = true;
		}
		return result;
	}
	public boolean updateCosCtl(JunseoBean jb) {
		try {
			jb.setStuId((String)pu.getAttribute("stuId"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		boolean result = false;
		if(this.convertBoolean(session.update("updateCos",jb))) {
			result = true;
		}
		return result;
	}
	
}
