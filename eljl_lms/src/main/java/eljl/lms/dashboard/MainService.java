package eljl.lms.dashboard;

import java.util.ArrayList;
import java.util.List;


import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import eljl.factory.bean.GradeBean;
import eljl.factory.bean.SubjectBean;
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

	public List<SubjectBean> createLectureCtl(SubjectBean sb) {
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
			}else {
				sb.setCsCode(session.selectOne("getcsName",sb));
			}
		}
		sb.setCsCode(number);
		System.out.println(sb.getCsCode());

		// opCode
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

		if(this.convertData(session.insert("insClass", sb))) {
			if(check = this.convertData(session.insert("insSubject",sb))){
				for(int i=0; i<sb.getGbList().size(); i++) {
					sb.getGbList().get(i).setItemCode(sb.getGbList().get(i).getItemCode() +"0" + i);
					try {
						sb.getGbList().get(i).setMbId((String)pu.getAttribute("mbId"));
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
		return null;
	}


	public boolean convertData(int data) {
		return (data > 0)? true:false;
	}


	// 강의 리스트 가져오기
	public List<SubjectBean> getMyLectureListCtl(SubjectBean sb) {
		try {
			sb.setMbId((String)pu.getAttribute("mbId"));
		} catch (Exception e) {
			e.printStackTrace();
		}

		List<SubjectBean> list;
		ArrayList<SubjectBean> sbList = new ArrayList<SubjectBean>();
		list = session.selectList("getLectureList", sb);
		sbList.addAll(list);
		return sbList;
	}
}
