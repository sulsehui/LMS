package eljl.service.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eljl.factory.bean.BoardCreateBeanHS;
import eljl.factory.bean.BoardSubmitBeanHS;
import eljl.factory.bean.NoticeBean;
import eljl.factory.bean.SubjectBean;
import eljl.lms.commonHS.MemberClass;
import eljl.lms.commonHS.MemberStream;

@RestController
@RequestMapping("/common")
public class CommonAjaxControllerHS {

	@Autowired
	MemberClass mc;
	
	@Autowired
	MemberStream ms;
	
	//수업 게시글 출력
	@PostMapping("/allPostList")
	public List<BoardCreateBeanHS> allPostList(@RequestBody List<BoardCreateBeanHS> bcb) {
		return mc.allPostListCtl(bcb.get(0)); 
	}	
	
	// 개설 항목 가져오기
	@PostMapping("/getCategoryList")
	public List<BoardCreateBeanHS> getCategoryList(@RequestBody List<BoardCreateBeanHS> bcb) {
		return mc.getCategoryListCtl(bcb.get(0));
	
	}
	
	//기타 만들기
		@PostMapping("/createEtc")
		public Map<String, String> createEtc(@ModelAttribute BoardCreateBeanHS bcb) {
			System.out.println("테스트 :"+bcb);
			return mc.createEtcCtl(bcb); 
		}
	
		
	//과제 만들기
		@PostMapping("/createTask")
		public Map<String, String> createTask(@ModelAttribute BoardCreateBeanHS bcb) {
			
			return mc.createTaskCtl(bcb); 
		}	
		
	//퀴즈 만들기
		@PostMapping("/createQuiz")
		public Map<String, String> createQuiz(@RequestBody List<BoardCreateBeanHS> bcb) {
			
			return mc.createQuizCtl(bcb.get(0)); 
		}	
		
	//과제 수정하기
		@PostMapping("/updateTask")
		public Map<String, String> updateTask(@RequestBody List<BoardCreateBeanHS> bcb) {
			return mc.updateTaskCtl(bcb.get(0)); 
		}		

	//퀴즈 수정하기
		@PostMapping("/updateQuiz")
		public Map<String, String> updateQuiz(@RequestBody List<BoardCreateBeanHS> bcb) {
			return mc.updateQuizCtl(bcb.get(0)); 
		}	
		
	//기타 수정하기
		@PostMapping("/updateETC")
		public Map<String, String> updateETC(@RequestBody List<BoardCreateBeanHS> bcb) {
			return mc.updateETCCtl(bcb.get(0)); 
		}	
		
	//과제 삭제하기
		@PostMapping("/deleteTask")
		public Map<String, String> deleteTask(@RequestBody List<BoardCreateBeanHS> bcb) {
			return mc.deleteTaskCtl(bcb.get(0)); 
		}	
		
	//퀴즈 삭제하기
		@PostMapping("/deleteQuiz")
		public Map<String, String> deleteQuiz(@RequestBody List<BoardCreateBeanHS> bcb) {
			return mc.deleteQuizCtl(bcb.get(0)); 
		}	
		
	//기타 삭제하기
		@PostMapping("/deleteETC")
		public Map<String, String> deleteETC(@RequestBody List<BoardCreateBeanHS> bcb) {
			return mc.deleteETCCtl(bcb.get(0)); 
		}	
		
	//퀴즈 제출하기
		@PostMapping("/submitQuiz")
		public Map<String, String> submitQuiz(@RequestBody List<BoardSubmitBeanHS> bsb) {
			return mc.submitQuizCtl(bsb.get(0)); 
		}	
		
	//과제 제출하기
		@PostMapping("/submitTask")
		public Map<String, String> submitTask(@RequestBody List<BoardSubmitBeanHS> bsb) {
			return mc.submitTaskCtl(bsb.get(0)); 
		}	
		
	//기타 제출하기
		@PostMapping("/submitETC")
		public Map<String, String> submitETC(@RequestBody List<BoardSubmitBeanHS> bsb) {
			return mc.submitETCCtl(bsb.get(0)); 
		}	
		
		
		
		
		// 과제 마감 날짜 출력
				@PostMapping("/getTaskDate")
				public List<BoardCreateBeanHS> getTaskDate(@RequestBody List<BoardCreateBeanHS> bsb) {
					return ms.getTaskDate(bsb.get(0)); 
				}	
		
		// 퀴즈 마감 날짜 출력
				@PostMapping("/getQuizDate")
				public List<BoardCreateBeanHS> getQuizDate(@RequestBody List<BoardCreateBeanHS> bsb) {
					return ms.getQuizDate(bsb.get(0)); 
				}
				
		//기타 마감 날짜 출력
				@PostMapping("/getETCDate")
				public List<BoardCreateBeanHS> getETCDate(@RequestBody List<BoardCreateBeanHS> bsb) {
					return ms.getETCDate(bsb.get(0)); 
				}
		// 공지사항 작성
				@PostMapping("/sendNotice")
				public Map<String, String> sendNotice(@RequestBody List<NoticeBean> bsb) {
					return ms.sendNotice(bsb.get(0)); 
				}
				
		// 공지사항 불러오기
				@PostMapping("/getNotice")
				public List<NoticeBean> getNotice(@RequestBody List<NoticeBean> bsb) {
					return ms.getNotice(bsb.get(0)); 
				}
		// 선택한 공지사항 불러오기
				@PostMapping("/selectNotice")
				public List<NoticeBean> selectNotice(@RequestBody List<NoticeBean> bsb) {
					return ms.selectNotice(bsb.get(0)); 
				}
		
		// 공지사항 삭제
				@PostMapping("/deleteNotice")
				public Map<String, String> deleteNotice(@RequestBody List<NoticeBean> bsb) {
					return ms.deleteNotice(bsb.get(0)); 
				}
		
		// 공지사항 수정
				@PostMapping("/updateNotice")
				public Map<String, String> updateNotice(@RequestBody List<NoticeBean> bsb) {
					return ms.updateNotice(bsb.get(0)); 
				}
				
			
				
				//퀴즈 결과 확인
				@PostMapping("/resultCheckQuiz")
				public List<BoardSubmitBeanHS> resultCheckQuiz(@RequestBody List<BoardSubmitBeanHS> bsb) {
					return mc.resultCheckQuizCtl(bsb.get(0)); 
						}	
				
			//기타 제출 취소
				@PostMapping("/cancelETCReport")
				public Map<String, String> cancelETCReport(@RequestBody List<BoardSubmitBeanHS> bsb) {
					return mc.cancelETCReportCtl(bsb.get(0)); 
								}	
				
			//내 기타 보기
				@PostMapping("/viewMyETC")
				public List<BoardSubmitBeanHS> viewMyETC(@RequestBody List<BoardSubmitBeanHS> bsb) {
					return mc.viewMyETCCtl(bsb.get(0)); 
								}	
				
			//내 과제 보기
				@PostMapping("/viewMyTask")
				public List<BoardSubmitBeanHS> viewMyTask(@RequestBody List<BoardSubmitBeanHS> bsb) {
					return mc.viewMyTaskCtl(bsb.get(0)); 
								}		
				
			//기타 제출 취소
				@PostMapping("/cancelTask")
				public Map<String, String> cancelTask(@RequestBody List<BoardSubmitBeanHS> bsb) {
					return mc.cancelTaskCtl(bsb.get(0)); 
								}	
			
			//학생 리스트 가져오기 (과제) 	
				@PostMapping("/viewAllReport")
				public List<BoardSubmitBeanHS> viewAllReport(@RequestBody List<BoardSubmitBeanHS> bsb) {
					return mc.viewAllReportCtl(bsb.get(0));
				}
			//학생 정답 가져오기 (과제) 	
				@PostMapping("/selectStuAnswer")
				public List<BoardSubmitBeanHS> selectStuAnswer(@RequestBody List<BoardSubmitBeanHS> bsb) {
					return mc.selectStuAnswerCtl(bsb.get(0));
				}		
				
			//학생 과제 가져오기 (과제) 	
				@PostMapping("/selectStuReport")
				public List<BoardSubmitBeanHS> selectStuReport(@RequestBody List<BoardSubmitBeanHS> bsb) {
					return mc.selectStuReportCtl(bsb.get(0));
				}	
				
			//학생 리스트 가져오기 (기타) 	
				@PostMapping("/viewAllETC")
				public List<BoardSubmitBeanHS> viewAllETC(@RequestBody List<BoardSubmitBeanHS> bsb) {
					return mc.viewAllETCCtl(bsb.get(0));
				}
				
			//학생 과제 가져오기 (기타) 	
				@PostMapping("/selectStuETC")
				public List<BoardSubmitBeanHS> selectStuETC(@RequestBody List<BoardSubmitBeanHS> bsb) {
					return mc.selectStuETCCtl(bsb.get(0));
				}				
				
				
				
				
				
}
	