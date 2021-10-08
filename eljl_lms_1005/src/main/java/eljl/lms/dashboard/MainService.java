package eljl.lms.dashboard;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServletRequest;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import eljl.factory.bean.GradeBean;
import eljl.factory.bean.HistoryBean;
import eljl.factory.bean.StuManageBean;
import eljl.factory.bean.SubjectBean;
import eljl.factory.bean.UserInfoBean;
import eljl.factory.util.Encryption;
import eljl.factory.util.ProjectUtils;

@Service
public class MainService {

	@Autowired
	SqlSessionTemplate session;

	ModelAndView mav;

	@Autowired
	Encryption enc;

	@Autowired
	Gson gson;

	@Autowired
	ProjectUtils pu;
	
	
	//강의 생성
	public Map<String, String> createLectureCtl(SubjectBean sb) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("message", "강좌 생성을 실패하였습니다.");
		boolean check =false; 

		// 선생님아이디 , csCode(4) , opCode(7)
		try {
			sb.setMbId((String)pu.getAttribute("mbId"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println(sb.getMbId());

		// csCode
		String number = "";
		//초기값 검사
		if(session.selectOne("getcsCode") == null) {
			number = "0001";
		}else{
			if(session.selectOne("getcsName",sb) == null) {
				// "0004"
				//3 - number.length(); // 2
				String result = (Integer.parseInt(session.selectOne("getcsCode"))+1) + "";
				for(int i=0; i < 4-result.length(); i++) {
					number += "0"; // 00
				}
				number += result;	
				sb.setCsCode(number);
				
			}else { // 중복일 때
				sb.setCsCode(session.selectOne("getcsName",sb));
			}
		}
		
		System.out.println(sb.getCsCode());

		// opCode
		number = "";
		if(session.selectOne("getopCode") == null) {
			number = "0000001";
		}else {
			String result = (Integer.parseInt(session.selectOne("getopCode"))+1) + "";
			for(int i=0; i < 7-result.length(); i++) {
				number += "0";
			}
			number += result;
		}
		sb.setOpCode(number);
		System.out.println(sb.getOpCode());
		
		if(!(this.convertData(session.selectOne("selectClass",sb)))) {
			if(check = this.convertData(session.insert("insClass", sb))) {
				if(check = this.convertData(session.insert("insSubject",sb))){
					for(int i=0; i<sb.getGbList().size(); i++) {
						sb.getGbList().get(i).setItemCode(sb.getGbList().get(i).getItemCode() +"0" + i);
						try {
							sb.getGbList().get(i).setMbId((String)pu.getAttribute("mbId"));
							map.put("message", "강좌를 개설하였습니다.");
						} catch (Exception e) {
							e.printStackTrace();
						}
						sb.getGbList().get(i).setCsCode(sb.getCsCode());
						sb.getGbList().get(i).setOpCode(sb.getOpCode());
					}
					if(check = this.convertData(session.insert("insScoreStandard",sb.getGbList()))) {
					}
				}
			}
		}else {
			if(check = this.convertData(session.insert("insSubject",sb))){
				for(int i=0; i<sb.getGbList().size(); i++) {
					sb.getGbList().get(i).setItemCode(sb.getGbList().get(i).getItemCode() +"0" + i);
					try {
						sb.getGbList().get(i).setMbId((String)pu.getAttribute("mbId"));
						map.put("message", "강좌를 개설하였습니다.");
					} catch (Exception e) {
						e.printStackTrace();
					}
					sb.getGbList().get(i).setCsCode(sb.getCsCode());
					sb.getGbList().get(i).setOpCode(sb.getOpCode());
				}
				if(check = this.convertData(session.insert("insScoreStandard",sb.getGbList()))) {
				}
			}
		}
		return map;
	}


	public boolean convertData(int data) {
		return (data > 0)? true:false;
	}
	
	public boolean convertType(int data) {
		return (data == 1)? true:false;
	}


	// 강의 리스트 가져오기
	public List<StuManageBean> getMyLectureListCtl(SubjectBean sb) {
		
		List<StuManageBean> list;
		ArrayList<StuManageBean> sbList = new ArrayList<StuManageBean>();
		
		if(sb.getMbType().equals("T")) {
			try {
				sb.setMbId((String)pu.getAttribute("mbId"));
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			list = session.selectList("getTeLectureList", sb);
			
			for(int i=0; i < list.size(); i++) {
				try {
					list.get(i).setTeName(enc.aesDecode(list.get(i).getTeName(), list.get(i).getTeId()));
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		}else {
			try {
				sb.setMbId((String)pu.getAttribute("stuId"));
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			list = session.selectList("getStuLectureList", sb);
			
			for(int i=0; i < list.size(); i++) {
				try {
					list.get(i).setTeName(enc.aesDecode(list.get(i).getTeName(), list.get(i).getTeId()));
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		}
		
		//sbList.addAll(list);
		return list;
	}

	// 클래스 삭제하기
	public List<StuManageBean> deleteClassCtl(SubjectBean sb) {
		if(this.convertData(session.delete("deleteClass_SS",sb))) {
			System.out.println("ss삭제");
			if(this.convertData(session.delete("deleteClass_SUB", sb))) {
				System.out.println("sub삭제");
			}
		}
		return getMyLectureListCtl(sb);
	}

	//로그아웃
	public ModelAndView LogOutCtl(UserInfoBean ub, HttpServletRequest req) {
		mav = new ModelAndView();
		
		
		return mav;
	}
	
	boolean insTeHistory(HistoryBean hb) {
		return convertData(session.insert("insTeHistory", hb));
	}
	
	boolean insStuHistory(HistoryBean hb) {
		return convertData(session.insert("insStuHistory", hb));
	}

	//다른 클래스 리스트 출력
	public List<StuManageBean> getTotalLectureListCtl(SubjectBean sb) {
		
		List<StuManageBean> list;
		ArrayList<StuManageBean> sbList = new ArrayList<StuManageBean>();
		if(sb.getMbType().equals("T")) {
			try {
				sb.setMbId((String)pu.getAttribute("mbId"));
			} catch (Exception e) {e.printStackTrace();}
		
			list = session.selectList("getTeOtherClassList", sb);
			
			for(int i=0; i < list.size(); i++) {
				try {
					list.get(i).setTeName(enc.aesDecode(list.get(i).getTeName(), list.get(i).getTeId()));
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		} else {
			try {
				sb.setMbId((String)pu.getAttribute("stuId"));
			} catch (Exception e) {e.printStackTrace();}
		
			list = session.selectList("getStuOtherClassList", sb);
			
			for(int i=0; i < list.size(); i++) {
				try {
					list.get(i).setTeName(enc.aesDecode(list.get(i).getTeName(), list.get(i).getTeId()));
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		sbList.addAll(list);
		
		
		return sbList;
	}

	
	//수강 신청
	public Map<String, String> accessClassCtl(StuManageBean smb) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("message", "수강신청 실패");
		try {
			smb.setStuId((String)pu.getAttribute("stuId"));
		} catch (Exception e) {e.printStackTrace();}
		System.out.println(smb.getTeId());
		System.out.println(smb.getCsCode());
		System.out.println(smb.getOpCode());
		if(convertData(session.insert("insAccessClass", smb))) {
			map.put("message", "수강신청 성공!!");
		}
		
		return map;
	}

	//수강 취소
	public  Map<String, String> cancelClassCtl(StuManageBean smb) {
		 Map<String, String> map = new HashMap<String, String>();
		map.put("message", "수강 취소를 실패하였습니다.");
		try {
			System.out.println("확인"+":"+smb.getCsCode()+":"+smb.getOpCode()+":"+smb.getTeId());
			smb.setStuId(((String)pu.getAttribute("stuId")));
		} catch (Exception e) {e.printStackTrace();}
		
		if(this.convertType(session.delete("cancelClass", smb))) {
			//커밋
			map.put("message", "수강이 취소되었습니다.");
		}else {
			//롤백
		}
		return map;
	}
	

	
}






