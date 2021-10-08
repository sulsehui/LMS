package eljl.lms.admin;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
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

import eljl.factory.bean.HistoryBean;
import eljl.factory.bean.UserInfoBean;
import eljl.factory.util.Encryption;
import eljl.factory.util.ProjectUtils;

@Service
public class AdminManagement {

	@Autowired
	SqlSessionTemplate session;

	ModelAndView mav;

	@Autowired
	Encryption enc;

	@Autowired
	Gson gson;

	@Autowired
	ProjectUtils pu;


	//전체 멤버리스트 가져오기
	public List<UserInfoBean> allMemberListCtl(UserInfoBean ub) {

		List<UserInfoBean> list;
		list = session.selectList("allMemberList", ub);

		for(int i=0; i<list.size(); i++) {
			try {
					list.get(i).setMbName(enc.aesDecode(list.get(i).getMbName(), list.get(i).getMbId()));
			} catch (Exception e) {e.printStackTrace();}
		}
		return list;
	}

	////교사 인증 : 대기->대기완료
	public Map<String,String> teacherAuthCtl(UserInfoBean ub) {
		Map<String,String> map = new HashMap<String, String>();
		map.put("message", "인증을 실패하였습니다.");

		if(this.convertData(session.update("teacherAuth", ub))) {
			map.put("message", "교사 인증되었습니다.");
		}

		return map;
	}


	public boolean convertData(int data) {
		return (data > 0)? true:false;
	}

	//교사 학생 정지 버튼 선택
	public Map<String, String> permissionCtl(UserInfoBean ub) {
		Map<String,String> map = new HashMap<String, String>();

		if(ub.getMbType().equals("T")) {
			if(this.convertData(session.update("tePermission", ub))) {

				if(ub.getStatus().equals("G")) {
					map.put("message", "[교사] '활동 중'으로 변경 완료");
				}else {
					map.put("message", "[교사] '정지'로 변경 완료");
				}
			}else {

				map.put("message", "[교사] 변경 실패!");
			}
		}else {
			if(this.convertData(session.update("stuPermission", ub))) {
				if(ub.getStatus().equals("G")) {
					map.put("message", "[학생] '정지'으로 변경 완료");
				}else {
					map.put("message", "[학생] '활동 중'로 변경 완료");
				}
			}else {
				map.put("message", "[학생] 변경 실패!");
			}
		}


		return map;
	}

	//현재 비밀번호 가져오기
	public HistoryBean getNowPwCtl(HistoryBean hb) {
		
		try {
			 hb.setMbId(((String)pu.getAttribute("mbId")));
		} catch (Exception e) {}
		
		String nowPw = session.selectOne("nowPw", hb);
		hb.setMbPw(nowPw);
		
		
		return hb;
	}




}
