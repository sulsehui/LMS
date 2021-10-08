package eljl.lms.commonHS;

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

import eljl.factory.bean.BoardCreateBeanHS;
import eljl.factory.bean.BoardSubmitBeanHS;
import eljl.factory.bean.HistoryBean;
import eljl.factory.bean.StuManageBean;
import eljl.factory.util.Encryption;
import eljl.factory.util.ProjectUtils;

@Service
public class MemberClass {

   @Autowired
   SqlSessionTemplate session;

   ModelAndView mav;

   @Autowired
   Encryption enc;

   @Autowired
   Gson gson;

   @Autowired
   ProjectUtils pu;

   public List<BoardCreateBeanHS> getCategoryListCtl(BoardCreateBeanHS bcb) {
      return session.selectList("getCategoryList", bcb);

   }

   public boolean convertData(int data) {
      return (data > 0)? true:false;
   }

   public Map<String, String> createEtcCtl(BoardCreateBeanHS bcb) {
      Map<String, String> map = new HashMap<String, String>();
      map.put("message", "실패하였습니다.");
      // TaskCode
      bcb.setNumCode(this.makeCode(bcb));
      bcb.setStickerPath(pu.savingFile(bcb.getMbFile())); 
      if(convertData(session.insert("insEtc", bcb))) {
         map.put("message", "기타를 생성하였습니다.");
      }
      return map;
   }

   public Map<String, String> createTaskCtl(BoardCreateBeanHS bcb) {
      Map<String, String> map = new HashMap<String, String>();
      map.put("message", "실패하였습니다.");
      // TaskCode
      bcb.setNumCode(this.makeCode(bcb));
      bcb.setStickerPath(pu.savingFile(bcb.getMbFile())); 
      if(convertData(session.insert("insTask", bcb))) {
         map.put("message", "과제를 생성하였습니다.");
      }
      return map;
   }


   public Map<String, String> createQuizCtl(BoardCreateBeanHS bcb) {
      Map<String, String> map = new HashMap<String, String>();
      map.put("message", "실패하였습니다.");
      // QuizCode
      bcb.setNumCode(this.makeCode(bcb));
   
      if(convertData(session.insert("insQuiz", bcb))) {
         map.put("message", "퀴즈를 생성하였습니다.");
      }
      return map;
   }



   public String makeCode(BoardCreateBeanHS bcb) {
      Date day = new Date();
      SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
      String date = sdf.format(day);
      int number = 0;
      if(bcb.getSsCode().substring(0,1).equals("E")) {
         number = session.selectOne("getEtcCode");
      }else if(bcb.getSsCode().substring(0,1).equals("T")) {
         number = session.selectOne("getTaskCode");
      }else if(bcb.getSsCode().substring(0,1).equals("Q")) {
         number = session.selectOne("getQuizCode");
      }
      String code = (number+1)+"";

      for(int i=code.length(); i<3; i++) {
         code = "0"+code;
      }
      return date + code;
   }

   public List<BoardCreateBeanHS> allPostListCtl(BoardCreateBeanHS bcb) {
      List<BoardCreateBeanHS> list;
      list = session.selectList("allPostList", bcb);
      return list;
   }

   public Map<String, String> updateTaskCtl(BoardCreateBeanHS bcb) {
      Map<String, String> map = new HashMap<String, String>();
      map.put("message", "실패하였습니다.");
      System.out.println(bcb.getStartDate() + bcb.getEndDate());
      if(convertData(session.update("updateTask", bcb))) {
         session.update("updateTS", bcb);
         map.put("message", "수정했습니다.");
      };
      return map;
   }

   public Map<String, String> updateQuizCtl(BoardCreateBeanHS bcb) {
      Map<String, String> map = new HashMap<String, String>();
      map.put("message", "실패하였습니다.");
      System.out.println(bcb.getStartDate() + bcb.getEndDate());
      
      if(convertData(session.update("updateQuiz", bcb))) {
         map.put("message", "수정했습니다.");
      };
      return map;
   }

   public Map<String, String> updateETCCtl(BoardCreateBeanHS bcb) {
      Map<String, String> map = new HashMap<String, String>();
      map.put("message", "실패하였습니다.");
      if(convertData(session.update("updateETC", bcb))) {
         session.update("updateES", bcb);
         map.put("message", "수정했습니다.");
      };
      return map;
   }

   public Map<String, String> deleteTaskCtl(BoardCreateBeanHS bcb) {
      Map<String, String> map = new HashMap<String, String>();
      map.put("message", "실패하였습니다.");
      if(convertData(session.delete("deleteTask", bcb))) {
         session.delete("deleteTS", bcb);
         session.delete("deleteTF", bcb);
         map.put("message", "삭제했습니다.");
      }
      return map;
   }

   public Map<String, String> deleteQuizCtl(BoardCreateBeanHS bcb) {
      Map<String, String> map = new HashMap<String, String>();
      map.put("message", "실패하였습니다.");
      if(convertData(session.delete("deleteQuiz", bcb))) {
         session.delete("deleteQS", bcb);
         map.put("message", "삭제했습니다.");
      }
      return map;
   }
   
   
   public Map<String, String> deleteETCCtl(BoardCreateBeanHS bcb) {
      Map<String, String> map = new HashMap<String, String>();
      map.put("message", "실패하였습니다.");
      if(convertData(session.delete("deleteETC", bcb))) {
         session.delete("deleteES", bcb);
         map.put("message", "삭제했습니다.");
      }
      return map;
   }

   public Map<String, String> submitQuizCtl(BoardSubmitBeanHS bsb) {
      Map<String, String> map = new HashMap<String, String>();
      map.put("message", "실패하였습니다.");
      if(convertData(session.insert("submitQuiz", bsb))) {
         map.put("message", "제출했습니다.");
      }
      return map;
   }
   
   public Map<String, String> submitTaskCtl(BoardSubmitBeanHS bsb) {
      Map<String, String> map = new HashMap<String, String>();
      map.put("message", "실패하였습니다.");
      if(convertData(session.insert("submitTask", bsb))) {
         convertData(session.insert("submitTask2", bsb));
         map.put("message", "제출했습니다.");
      }
      return map;
   }
   
   public Map<String, String> submitETCCtl(BoardSubmitBeanHS bsb) {
      Map<String, String> map = new HashMap<String, String>();
      map.put("message", "실패하였습니다.");
      if(convertData(session.insert("submitETC", bsb))) {
         map.put("message", "제출했습니다.");
      }
      return map;
   }

   public ModelAndView stuReportCtl(BoardSubmitBeanHS bsb) {
      mav = new ModelAndView();
      mav.addObject("mbId", bsb.getMbId());
      mav.addObject("csCode", bsb.getCsCode());
      mav.addObject("opCode", bsb.getOpCode());
      mav.addObject("ssCode", bsb.getSsCode());
      mav.addObject("numCode", bsb.getNumCode());
      System.out.println(bsb.getMbId() + ":" + bsb.getSsCode());
      mav.setViewName("stuReport");
      
      return mav;
   }
   
   public ModelAndView stuETCCtl(BoardSubmitBeanHS bsb) {
      mav = new ModelAndView();
      mav.addObject("mbId", bsb.getMbId());
      mav.addObject("csCode", bsb.getCsCode());
      mav.addObject("opCode", bsb.getOpCode());
      mav.addObject("ssCode", bsb.getSsCode());
      mav.addObject("numCode", bsb.getNumCode());
      System.out.println(bsb.getMbId() + ":" + bsb.getSsCode());
      mav.setViewName("stuETC");
      
      return mav;
   }

   public List<BoardSubmitBeanHS> resultCheckQuizCtl(BoardSubmitBeanHS bsb) {
      List<BoardSubmitBeanHS> list;
      list = session.selectList("resultCheckQuiz", bsb);
      System.out.println(list.get(0).getAnswer()  + " : " +  list.get(1).getAnswer());
      return list;
   }

   public Map<String, String> cancelETCReportCtl(BoardSubmitBeanHS bsb) {
      Map<String, String> map = new HashMap<String, String>();
      map.put("message", "실패하였습니다.");
      if(convertData(session.delete("cancelETCReport", bsb))) {
         map.put("message", "삭제했습니다.");
      }
      return map;
   }

   public List<BoardSubmitBeanHS> viewMyETCCtl(BoardSubmitBeanHS bsb) {
      List<BoardSubmitBeanHS> list;
      list = session.selectList("viewMyETC", bsb);
      return list;
   }

   public List<BoardSubmitBeanHS> viewMyTaskCtl(BoardSubmitBeanHS bsb) {
      List<BoardSubmitBeanHS> list;
      list = session.selectList("viewMyTask", bsb);
      return list;
   }

   public Map<String, String> cancelTaskCtl(BoardSubmitBeanHS bsb) {
      Map<String, String> map = new HashMap<String, String>();
      map.put("message", "실패하였습니다.");
      if(convertData(session.delete("cancelTask", bsb))) {
         convertData(session.delete("cancelTask2", bsb));
         map.put("message", "삭제했습니다.");
      }
      return map;
   }

   public List<BoardSubmitBeanHS> viewAllReportCtl(BoardSubmitBeanHS bsb) {
      List<BoardSubmitBeanHS> list;
      
      list = session.selectList("viewAllReport", bsb);
      
      for(int i=0; i < list.size(); i++) {
    	  try {
			list.get(i).setStuName(enc.aesDecode(list.get(i).getStuName(), list.get(i).getStuId()));
		} catch (Exception e) {
			e.printStackTrace();
		}
      }
      
      return list;

   
   }
   
   public List<BoardSubmitBeanHS> selectStuAnswerCtl(BoardSubmitBeanHS bsb) {
      List<BoardSubmitBeanHS> list;
      
      list = session.selectList("selectStuAnswer", bsb);
      
      return list;
   }

   public List<BoardSubmitBeanHS> selectStuReportCtl(BoardSubmitBeanHS bsb) {
      List<BoardSubmitBeanHS> list;
      
      list = session.selectList("selectStuReport", bsb);
      
      return list;
   }

   public List<BoardSubmitBeanHS> viewAllETCCtl(BoardSubmitBeanHS bsb) {
      List<BoardSubmitBeanHS> list;
      
      list = session.selectList("viewAllETC", bsb);
      
      for(int i=0; i < list.size(); i++) {
    	  try {
			list.get(i).setStuName(enc.aesDecode(list.get(i).getStuName(), list.get(i).getStuId()));
		} catch (Exception e) {
			e.printStackTrace();
		}
      }
      
      return list;
   }

   public List<BoardSubmitBeanHS> selectStuETCCtl(BoardSubmitBeanHS bsb) {
      List<BoardSubmitBeanHS> list;
      
      list = session.selectList("selectStuETC", bsb);
      
      return list;
   }



}