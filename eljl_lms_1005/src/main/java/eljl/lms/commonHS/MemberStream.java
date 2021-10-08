package eljl.lms.commonHS;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eljl.factory.bean.BoardCreateBeanHS;
import eljl.factory.bean.NoticeBean;

@Service
public class MemberStream {
	
	@Autowired
	SqlSessionTemplate session;
	
	// 과제 기간 불러오기
	public List<BoardCreateBeanHS> getTaskDate(BoardCreateBeanHS nb) {
		List<BoardCreateBeanHS> list;
		list = session.selectList("getTaskDate",nb);
		
		return list;
	}
	
	// 퀴즈 기간 불러오기
	public List<BoardCreateBeanHS> getQuizDate(BoardCreateBeanHS nb) {
		System.out.println(nb);
		List<BoardCreateBeanHS> list;
		list = session.selectList("getQuizDate",nb);
		System.out.println(list);
		return list;
	}
	
	// 기타 기간 불러오기
	public List<BoardCreateBeanHS> getETCDate(BoardCreateBeanHS nb) {
		List<BoardCreateBeanHS> list;
		list = session.selectList("getETCDate",nb);
		
		return list;
	}
	
	// 공지사항 작성
	public Map<String, String> sendNotice(NoticeBean nb) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("message", "공지사항 등록을 실패하였습니다.");
		
		// 시퀀스 증가
		int ntCode = session.selectOne("getNtNumber", nb);
		String add = "";
		String result = "";
		
		if(ntCode == 0) {
			nb.setNtCode("0000000001");
		}else {
			for(int i=0; i < 10-(ntCode+"").length(); i++) {
				add += "0";
			}		
			result = add + (ntCode+1);
			nb.setNtCode(result);
		}
		
		System.out.println(nb.getNtCode());
		
		if(this.convertData(session.insert("insNotice",nb))) {
			map.put("message", "공지사항을 등록하였습니다.");
		}
		
		return map;
	}
	
	
	public boolean convertData(int data) {
		return (data > 0)? true:false;
	}
	
	
	// 공지사항 리스트 불러오기
	public List<NoticeBean> getNotice(NoticeBean nb) {
		
		List<NoticeBean> list;
		
		list = session.selectList("getNoticeList",nb);
		
		return list;
	}
	
	// 선택한 공지사항 불러오기
	public List<NoticeBean> selectNotice(NoticeBean nb) {
		
		System.out.println("1 :"+nb);
		
		List<NoticeBean> list;
		
		list = session.selectList("selectNotice",nb);
		
		System.out.println("2 :"+list);
		
		return list;
	}
	
	// 공지사항 삭제
	public Map<String, String> deleteNotice(NoticeBean nb) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("message", "공지사항 삭제를 실패하였습니다.");
		
		if(this.convertData(session.delete("deleteNotice",nb))) {
			map.put("message", "공지사항을 삭제하였습니다.");
		}
		
		return map;
	}
	
	
	// 공지사항 수정
	public Map<String, String> updateNotice(NoticeBean nb) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("message", "공지사항 수정을 실패하였습니다.");
		
		if(this.convertData(session.delete("updateNotice",nb))) {
			map.put("message", "공지사항을 수정하였습니다.");
		}
		
		return map;
	}
	
	// 공지사항 삭제하기
	
}
