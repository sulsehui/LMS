package eljl.lms.teacher;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

import eljl.factory.bean.StuManageBean;
import eljl.factory.util.Encryption;
import eljl.factory.util.ProjectUtils;


@Service
public class AttendService {
	@Autowired
	SqlSessionTemplate session;

	ModelAndView mav;

	@Autowired
	Encryption enc;

	@Autowired
	Gson gson;

	@Autowired
	ProjectUtils pu;

	//학생 리스트 가져오기
	public List<StuManageBean> getStuListCtl(StuManageBean smb) {
		
		List<StuManageBean> sBean = null;

		sBean = session.selectList("getStuList", smb);
		
		for(int i=0; i < sBean.size(); i++) {
			try {
				sBean.get(i).setStuName(enc.aesDecode(sBean.get(i).getStuName(), sBean.get(i).getStuId()));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return sBean;
	}

	//전체 출석 정보 가져오기(학생리스트, 출석현황)
	public List<StuManageBean> allAttendListCtl(StuManageBean smb) {
		List<StuManageBean> list;
		list = session.selectList("getAllAttendList", smb);
		
		for(int i=0; i < list.size(); i++) {
			try {
				list.get(i).setStuName(enc.aesDecode(list.get(i).getStuName(), list.get(i).getStuId()));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}

	//출석체크
	public Map<String, String> sendAttendCheckCtl(List<StuManageBean> smb) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("message", "출석체크를 실패하였습니다. 다시 시도해주세요.");

		if(!this.convertData(session.selectOne("selectAttendCheck", smb.get(0)))) {
			for(int i=0; i<smb.size(); i++) {
				session.insert("sendAttendCheck", smb.get(i));
				map.put("message", "출석체크 성공!");
			}
		}else {
			map.put("message", "오늘은 이미 출석체크가 완료되었습니다.");
		}
		return map;
	}

	private boolean convertData(int data) {
		return (data>0)? true : false;
	}
	
	
	//수정할 날짜 가져오기
	public List<StuManageBean> getEditDateListCtl(StuManageBean smb) {
		
		
		return session.selectList("editDateList", smb);
	}
	
	//수정하기 위한 현재 출석 상태 가져오기
	public List<StuManageBean> getBeforeStatusCtl(StuManageBean smb) {
		
		
		List<StuManageBean> list;
		
		
		list = session.selectList("beforeStatus", smb);
		
		return list;
	}
	
	//출석 수정하기
	public Map<String, String> getEditAttendCtl(StuManageBean smb) {
		Map<String, String> map = new HashMap<String, String>();
		
		if(this.convertData(session.update("getEditAttend", smb))) {
			map.put("message", "출석 수정이 완료되었습니다.");
		} else {
			map.put("message", "수정 실패하였습니다. 다시 시도해주세요.");
		}
		
		return map;
	}

	
	// 한 학생 출석 리스트 가져오기
	public List<StuManageBean> selectStuStatusCtl(StuManageBean smb) {
		
		List<StuManageBean> list;
		
		list = session.selectList("selectStuStatus",smb);
		
		for(int i=0; i < list.size(); i++) {
			try {
				list.get(i).setStuName(enc.aesDecode(list.get(i).getStuName(), list.get(i).getStuId()));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		System.out.println("학생 :"+list);
		return list;
	}




}
