package eljl.lms.teacher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eljl.factory.bean.GradeBean;
import eljl.factory.bean.SubjectBean;
import eljl.factory.util.ProjectUtils;

@Service
public class TeacherSetting {
	
	@Autowired
	SqlSessionTemplate session;
	
	@Autowired
	ProjectUtils pu;
	
	// 이전 강좌 개설 내용 불러오기
	public List<SubjectBean> getSetting(SubjectBean sb) {
		
		List<SubjectBean> list;
		ArrayList<SubjectBean> result = new ArrayList<SubjectBean>();
		
		// 학과명
		list = session.selectList("getClass",sb);
		result.addAll(list);
		// 강좌명 , 시작날짜 , 종료날짜
		list = session.selectList("getSubject",sb);
		result.addAll(list);
		
		System.out.println(result);
		
		return result;
	}
	
	// 강좌 내용 수정하기
	public Map<String, String> updateSubject(SubjectBean sb) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("message", "강좌 변경을 실패하였습니다.");
		
		try {
			sb.setMbId((String)pu.getAttribute("mbId"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(this.convertData(session.update("updateSubject",sb))) {
			map.put("message", "강좌 변경을 성공하였습니다.");
		}
		
		return map;
	}
	
	// 이전 성적 관리 기준 내용 가져오기
	public List<GradeBean> getScoreStandard(SubjectBean sb) {
		System.out.println(sb);
		List<GradeBean> list;
		
		list = session.selectList("getScoreStandard",sb);
		System.out.println(list);
		return list;
	}
	
	public boolean convertData(int data) {
		return (data > 0)? true:false;
	}
	
	// 추가
	public Map<String, String> plusScoreStandard(List<GradeBean> gb) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("message", "추가를 실패하였습니다.");
		String newScore = ""+(Integer.parseInt(session.selectOne("getMaxScoreStandard", gb.get(0)))+1);
		String result = "";
		boolean check = false;
		if(newScore.length() == 1) {
			result = "0" + newScore; 
		}else {
			result = newScore;
		}
		
		// 새로 추가되는 값에 0 추가하기 위해 select
		gb.get(0).setItemCode(gb.get(0).getItemCode()+result);
		System.out.println(gb.get(0).getItemCode());
		
		// 0번 insert
		if(this.convertData(session.insert("addScoreStandard",gb.get(0)))) {
			// 나머지 update
			for(int i=1; i < gb.size(); i++) {
				check = this.convertData(session.update("updateScoreStandard",gb.get(i)));	
			}
		}
		
		if(check){
			map.put("message", "추가되었습니다.");
		}
		
		return map;
	}
	
	// 삭제
	public Map<String, String> minusScoreStandard(List<GradeBean> gb) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("message", "삭제를 실패하였습니다.");
		boolean check = false;
		
		// delete T,Q,E 
		if(gb.get(0).getItemCode().substring(0, 1).equals("T")) {
			session.delete("minusScoreStandard",gb.get(0));
		}else if(gb.get(0).getItemCode().substring(0, 1).equals("Q")) {
			session.delete("minusScoreStandard",gb.get(0));
		}else {
			session.delete("minusScoreStandard",gb.get(0));
		}
		
		// 나머지 update
		for(int i=1; i < gb.size(); i++) {
			check = this.convertData(session.update("updateScoreStandard",gb.get(i)));	
		}
		
		if(check) {
			map.put("message", "삭제를 성공하였습니다.");
		}
		
		return map;
	}
	
	// 수정
	public Map<String, String> updateScoreStandard(List<GradeBean> gb) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("message", "수정을 실패하였습니다.");
		boolean check = false;
		
		// 나머지 update
		for(int i=0; i < gb.size(); i++) {
			check = this.convertData(session.update("updateScoreStandard",gb.get(i)));	
		}
		
				
		if(check) {
			map.put("message", "수정을 성공하였습니다.");
		}
				
		return map;
	}

}
