package eljl.lms.dashboard;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eljl.factory.bean.BoardCreateBean;
import eljl.factory.bean.CalendarBean;
import eljl.factory.bean.StuManageBean;
import eljl.factory.bean.SubjectBean;
import eljl.factory.bean.UserInfoBean;
import eljl.factory.util.ProjectUtils;

@Service
public class Calendar {
	
	@Autowired
	SqlSessionTemplate session;
	
	@Autowired
	ProjectUtils pu;
	
	public List<CalendarBean> getScheduleListCtl(StuManageBean smb) {
		
		//System.out.println(ub.getMbId() + " " + ub.getCsCode() + " " + ub.getOpCode());
		
		//그 아이디가 가지고 있는 mbId, csCode , opCode : list로 담고 list for문 돌려서 출력
		try {
			smb.setStuId((String)pu.getAttribute("mbId"));
			smb.setMbType((String)pu.getAttribute("mbType"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		List<StuManageBean> list2;
		
		// 선생님이랑 학생 분기
		if(smb.getMbType().equals("T")) {
			//선생님
			list2 = session.selectList("getTeScheduleList",smb);
		}else {
			//학생
			list2 = session.selectList("getStuScheduleList", smb);
		}
		
		System.out.println(list2);
		
		List<CalendarBean> list = null;
		ArrayList<CalendarBean> result = new ArrayList<CalendarBean>();
		
		//과제 일정 출력	
		for(int i=0; i < list2.size(); i++) {
			list = session.selectList("getScheduleTask",list2.get(i));
			result.addAll(list);
		}
		
		
		//퀴즈 일정 출력
		for(int i=0; i < list2.size(); i++) {
			list = session.selectList("getScheduleQuiz",list2.get(i));
			result.addAll(list);
		}
		
		
		//기타 일정 출력
		for(int i=0; i < list2.size(); i++) {
			list = session.selectList("getScheduleETC",list2.get(i));
			result.addAll(list);
		}
		
		
		
		System.out.println(result);
		return result;
	}

}
