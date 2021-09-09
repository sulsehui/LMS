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

@Service
public class MainService {

	@Autowired
	SqlSessionTemplate session;

	ModelAndView mav;

	@Autowired
	Encryption enc;

	@Autowired
	Gson gson;

	public List<SubjectBean> createLectureCtl(SubjectBean sb) {
		boolean check =false; 
		if(check = this.convertData(session.insert("insClass",sb))){
			//ArrayList<String> List = new ArrayList<String>();
			for(int i=0; i<sb.getGbList().size(); i++) {
				sb.getGbList().get(i).setItemCode(sb.getGbList().get(i).getItemCode() +"0" + i);
				System.out.println(sb.getGbList().get(i).getItemCode());
				if(check = this.convertData(session.insert("insScoreStandard",sb.getGbList()))) {
					System.out.println("성공"+i);
				}
			}


		}
		return null;
	}




	public boolean convertData(int data) {
		return (data > 0)? true:false;
	}
}
