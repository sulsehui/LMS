<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%@ page import="java.util.*, java.text.*"%>
<head>
<title>LMS</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
   href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet"
   href="http://humy2833.dothome.co.kr/customizing.html#">
<script
   src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
   src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script type="text/javascript"
   src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.24.0/moment.min.js"></script>

<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Gowun+Dodum&display=swap" rel="stylesheet"> 
<link rel="stylesheet" href="resources/css/master.css">
<link rel="stylesheet" href="resources/css/class.css">
<style>
a:hover {
   color: black;
}

#test : hover {
   color: black;
}
/* Remove the navbar's default margin-bottom and rounded borders */
.navbar {
   margin-bottom: 0;
   border-radius: 0;
   background-color: #375a7f;
}

/* Set height of the grid so .sidenav can be 100% (adjust as needed) */
.row.content {
   height: 450px
}

/* Set gray background color and 100% height */
.sidenav {
   padding-top: 20px;
   background-color: #ffffff;
   height: 100%;
}

/* Set black background color, C and some padding */
footer {
   background-color: #555;
   color: blue;
   padding: 15px;
}

/* On small screens, set height to 'auto' for sidenav and grid */
@media screen and (max-width: 767px) {
   .sidenav {
      height: auto;
      padding: 15px;
   }
   .row.content {
      height: auto;
   }
}

#bannerBox>ul {
   margin-top: 5px;
}

#bannerBox2>ul {
   margin-top: 5px;
}
</style>
<script type="text/javascript" src="resources/js/master.js"></script>
<script type="text/javascript" src="resources/js/main.js"></script>
<script type="text/javascript" src="resources/js/class.js?var=1"></script>
<%-- <link rel="stylesheet" type=text/css href="resources/css/class.css"> --%>
</head>

<nav  class="navbar navbar-inverse navbar-fixed-top">
   <div class="container-fluid" style="display: flex;">
      <div class="navbar-header">
         <button type="button" class="navbar-toggle" data-toggle="collapse"
            data-target="#myNavbar">
            <span class="icon-bar"></span> <span class="icon-bar"></span> <span
               class="icon-bar"></span>
         </button>
         <a class="navbar-brand" href="#"></a>
      </div>
      <div class="collapse navbar-collapse" id="myNavbar">
         <ul class="nav navbar-nav">
            <li><a href="main" id="test" style="color: white;"><img
                  src="resources/images/eljl_LOGO_final.png"
                  style="height: 30px; width: 100px;" /></a></li>
         </ul>
      </div>

      <div id= "bannerBox" style="margin: 0 auto;">
         <ul class="nav navbar-nav">
            <li><a style="color: white;" onClick="moveStream()">스트림</a></li>
               <li><a  style="color: white;" onClick="moveClass()">수업</a></li>
               <li class="teItem"><a  style="color: white;" onClick="moveAttend()">출석부</a></li>
               <li class="teItem"><a  style="color: white;" onClick="moveStuManage()">학생관리</a></li>
               <li class="stuItem"><a  style="color: white;" onClick="moveMyPage()">마이페이지</a></li>
         </ul>
      </div>
      <div id="bannerBox2">
         <ul class="nav navbar-nav navbar-right">
            <li class="teItem"><a onClick="moveSetting()"><span class="glyphicon glyphicon-cog"
                  style="color: white;"></span></a></li>
            <li><a style="color: white;" onClick="logOut('${mbId}')"><span
                  class="glyphicon glyphicon-log-out"></span> 로그아웃</a></li>
         </ul>
      </div>
   </div>
</nav>


<header>
   <div class="container" style="padding-top: 60px"></div>
</header>
<body onload="mbTypeCheck('${mbType}'); allPostList(); deleteGlypicon();">
   <%
   Date date = new Date();
   SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");
   String strdate = simpleDate.format(date);
   %>

   <input type='hidden' name='mbId' value='${mbId}' />
   <input type='hidden' name='csCode' value='${csCode}' />
   <input type='hidden' name='opCode' value='${opCode}' />
   <input type='hidden' name='mbType' value='${mbType}' />
   <input type='hidden' name='stuId' value='${stuId}' />
   <input type='hidden' name='opName' value='${opName}' />

   <div class="row content" style="display:flex;">
   	<div style="margin:0 auto;">
	      <div class="col-sm-1 text-left"  ></div>
	      <div class="col-sm-10 text-left" style="margin-right:150px;" >
	         <h1>
	            수업리스트 <a data-toggle="modal" data-target="#scoreList"><span
	               class="glyphicon glyphicon-plus" style="float: right"
	               onClick="getCategoryList()"></span></a>
	         </h1>
	
	         <div>
	            <div id="allPostList" class="well well-lg"></div>
	         </div>
	         <div>
	        </div>
	     </div>
	 </div>
    </div>
            <!-- 성적기준 모달창 -->
            <div class="modal fade" id="scoreList" role="dialog">
               <div class="modal-dialog">

                  <!-- Modal content-->
                  <div class="modal-content">
                     <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal"
                           onClick="deleteScoreValue()">&times;</button>
                        <h4 class="modal-title">성적기준</h4>
                     </div>
                     <div class="modal-body">

                        <div id="score" data-dismiss="modal"></div>
                        <div class="modal-footer">
                           <button type="button" class="btn btn-default"
                              data-dismiss="modal" >닫기</button>
                        </div>
                     </div>

                  </div>
               </div>
            </div>



            <!-- 기타 모달창 -->
            <div class="modal fade" id="createScoreE" role="dialog" style="overflow:scroll;">
               <div class="modal-dialog">
                  <!-- Modal content-->
                  <div class="modal-content">
                     <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal"
                           onClick="deleteScoreValue()">&times;</button>
                        <h4 class="g-modal-title">
                           <img src="resources/images/class_e3.png"
                              style="height: 45px; width: 45px;" /> 기타 </h4>
                     </div>
                     <div class="modal-body-m">
                     
                      <form method="POST" enctype="multipart/form-data" id="etcForm">
                     
                        <p>제목</p>
                        <input class="mbox" type="text" name="etcTitle">
                        <p>내용</p>
                        <input class="mbox2" type="text" name="etcContents">

                        <div class="filebox bs3-primary preview-image">
                           <img src="resources/images/file-up.png"
                              style="height: 35px; width: 35px;" /> 
                              <input class="upload-name" value="파일" disabled="disabled" style="width: 200px;"> 
                              <label for="input_file">업로드</label>
                           <input type="file" id="input_file" name="mbFile" class="upload-hidden">
                        </div>

                        <p>시작 날짜</p>
                        <input type="date" name="etcSDate" min="<%=strdate%>">
                        <p>종료 날짜</p>
                        <input type="date" name="etcEDate" min="<%=strdate%>"
                           onblur="endDateCheck()">
                           
                       </form>    
                           
                        <div class="modal-footer">
                           <button type="button" class="btn btn-default"
                              onClick="CreateEtc()">생성</button>
                           <button type="button" class="btn btn-default"
                              data-dismiss="modal" onClick="deleteScoreValue()">닫기</button>
                        </div>
                     </div>

                  </div>
               </div>
            </div>

            <!-- 퀴즈 모달창 -->
            <div class="modal fade" id="createScoreQ" role="dialog" style="overflow:scroll;">
               <div class="modal-dialog">

                  <!-- Modal content-->
                  <div class="modal-content">
                     <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal"
                           onClick="deleteScoreValue()">&times;</button>
                        <h4 class="q-modal-title"><img
                  src="resources/images/class_q3.png"
                  style="height: 45px; width: 45px;" /> 퀴즈</h4>
                     </div>
                     <div class="modal-body-m">
                        <p>제목</p>
                           <input class="mbox" type="text" name="quizTitle">
                        <p>질문</p>
                           <input class="mbox" type="text" name="quizContents">
                        <p>정답</p>
                           <input class="mbox" type="text" name="quizAnswer">
                        <p>시작 날짜</p>
                        <input type="date" name="quizSDate" min="<%=strdate%>">
                        <p>종료 날짜</p>
                        <input type="date" name="quizEDate" min="<%=strdate%>"
                           onblur="endDateCheck()">
                        <div class="modal-footer">
                           <button type="button" class="btn btn-default" onClick="CreateQuiz()">생성</button>
                           <button type="button" class="btn btn-default"
                              data-dismiss="modal" onClick="deleteScoreValue()">닫기</button>
                        </div>
                     </div>

                  </div>
               </div>
            </div>


            <!-- 과제 모달창 -->
            <div class="modal fade" id="createScoreT" role="dialog" style="overflow:scroll;">
               <div class="modal-dialog">

                  <!-- Modal content-->
                  <div class="modal-content">
                     <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal"
                           onClick="deleteScoreValue()">&times;</button>
                        <h4 class="h-modal-title"><img
                  src="resources/images/class_h4.png"
                  style="height: 45px; width: 45px;" /> 과제</h4>
                     </div>
                     <div class="modal-body-m">
                     
                     <form method="POST" enctype="multipart/form-data" id="taskForm">
                     
                        <p>제목</p>
                           <input class="mbox" type="text" name="taskTitle">
                        <p>내용</p>
                           <input class="mbox2" type="text" name="taskContents">
                           
                        <div class="filebox bs3-primary preview-image">
                           <img src="resources/images/file-up.png"
                              style="height: 35px; width: 35px;" /> 
                              <input class="upload-name" value="파일" disabled="disabled" style="width: 200px;"> 
                              <label for="input_file">업로드</label>
                           <input type="file" id="input_file" class="upload-hidden">
                        </div>
                        <p>시작 날짜</p>
                        <input type="date" name="taskSDate" min="<%=strdate%>">
                        <p>종료 날짜</p>
                        <input type="date" name="taskEDate" min="<%=strdate%>"
                           onblur="endDateCheck()">
                          
                         </form> 
                           
                        <div class="modal-footer">
                           <button type="button" class="btn btn-default" onClick="CreateTask()">생성</button>
                           <button type="button" class="btn btn-default"
                              data-dismiss="modal" onClick="deleteScoreValue()">닫기</button>
                        </div>
                     </div>
                  </div>
               </div>
            </div>


            <!-- 퀴즈 보기(선생) -->
            <div class="modal fade" id="viewQuizT" role="dialog">
               <div class="modal-dialog">

                  <!-- Modal content-->
                  <div class="modal-content">
                     <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal"
                           onClick="deleteScoreValue()">&times;</button>
                        <h4 class="modal-title">선생퀴즈</h4>
                     </div>
                     <div class="modal-body">
                        <div>제목</div>
                        <div id="viewQuizTitleT"></div>
                        <div>질문</div>
                        <div id=viewQuizContentsT></div>
                        <div>정답</div>
                        <div id="viewQuizAnswerT"></div>
                        <div>기간</div>
                        <div>
                           <span id="viewQuizStartT"></span> ~ <span id="viewQuizEndT"></span>
                        </div>
                        <div class="modal-footer">
                          
                           <button type="button" class="btn btn-default"
                              data-dismiss="modal">닫기</button>
                        </div>
                     </div>
                  </div>
               </div>
            </div>

            <!-- 퀴즈 보기(학생) -->
            <div class="modal fade" id="viewQuizS" role="dialog">
               <div class="modal-dialog">

                  <!-- Modal content-->
                  <div class="modal-content">
                     <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal"
                           onClick="deleteScoreValue()">&times;</button>
                        <h4 class="modal-title">학생퀴즈</h4>
                     </div>
                     <div class="modal-body">
                        <div>제목</div>
                        <div id="viewQuizTitleS"></div>
                        <div>질문</div>
                        <div id=viewQuizContentsS></div>
                        <div>정답</div>
                        <input type="text" id="viewQuizAnswerS">
                        <div>기간</div>
                        <div>
                           <span id="viewQuizStartS"></span> ~ <span id="viewQuizEndS"></span>
                        </div>
                        <div class="modal-footer">
                           <button type="button" class="btn btn-default"
                              onClick="submitQuiz()">제출</button>
                           <button type="button" class="btn btn-default"
                              data-dismiss="modal">닫기</button>
                        </div>
                     </div>
                  </div>
               </div>
            </div>



            <!-- 과제 보기(선생) -->
            <div class="modal fade" id="viewTaskT" role="dialog">
               <div class="modal-dialog">

                  <!-- Modal content-->
                  <div class="modal-content">
                     <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal"
                           onClick="deleteScoreValue()">&times;</button>
                        <h4 class="modal-title">선생과제</h4>
                     </div>
                     <div class="modal-body">
                        <div>제목</div>
                        <div id="viewTaskTitleT"></div>
                        <div>질문</div>
                        <div id=viewTaskContentsT></div>
                        <div>파일</div>
                        <div id="viewTaskAnswerT"></div>
                        <div>기간</div>
                        <div>
                           <span id="viewTaskStartT"></span> ~ <span id="viewTaskEndT"></span>
                        </div>
                        <div class="modal-footer">
                           <button type="button" class="btn btn-default" onClick="moveStuReport()">현황 보기</button>
                           <button type="button" class="btn btn-default"
                              data-dismiss="modal" onClick="allPostList()">닫기</button>
                        </div>
                     </div>
                  </div>
               </div>
            </div>


            <!-- 과제 보기(학생) -->
            <div class="modal fade" id="viewTaskS" role="dialog">
               <div class="modal-dialog">

                  <!-- Modal content-->
                  <div class="modal-content">
                     <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal"
                           onClick="deleteScoreValue()">&times;</button>
                        <h4 class="modal-title">학생과제</h4>
                     </div>
                     <div class="modal-body">
                        <div>제목</div>
                        <div id="viewTaskTitleS"></div>
                        <div>내용</div>
                        <div id=viewTaskContentsS></div>
                        <div>정답</div>
                        <input type="text" id="submitTaskAnswerS">
                        <div>파일</div>
                        <div id="viewTaskfileS"></div>
                        <div>파일 제출</div>
                        <input type="text" id="submitTaskfileS">
                     </div>
                     <div>기간</div>
                     <div>
                        <span id="viewTaskStartS"></span> ~ <span id="viewTaskEndS"></span>
                     </div>
                     <div class="modal-footer">
                        <button type="button" class="btn btn-default"
                           onClick="submitTask()">제출</button>
                        <button type="button" class="btn btn-default"
                           data-dismiss="modal">닫기</button>
                     </div>
                  </div>
               </div>
            </div>
       

         <!-- 기타 보기(선생) -->
         <div class="modal fade" id="viewETCT" role="dialog">
            <div class="modal-dialog">

               <!-- Modal content-->
               <div class="modal-content">
                  <div class="modal-header">
                     <button type="button" class="close" data-dismiss="modal"
                        onClick="deleteScoreValue()">&times;</button>
                     <h4 class="modal-title">선생기타</h4>
                  </div>
                  <div class="modal-body">
                     <div>제목</div>
                     <div id="viewETCTitleT"></div>
                     <div>질문</div>
                     <div id=viewETCContentsT></div>
                     <div>파일</div>
                     <div id="viewETCAnswerT"></div>
                     <div>기간</div>
                     <div>
                        <span id="viewETCStartT"></span> ~ <span id="viewETCEndT"></span>
                     </div>
                     <div class="modal-footer">
                        <button type="button" class="btn btn-default" onClick="moveStuEtc()">현황 보기</button>
                        <button type="button" class="btn btn-default"
                           data-dismiss="modal">닫기</button>
                     </div>
                  </div>
               </div>
            </div>
         </div>


         <!-- 기타 보기(학생) -->
         <div class="modal fade" id="viewETCS" role="dialog">
            <div class="modal-dialog">

               <!-- Modal content-->
               <div class="modal-content">
                  <div class="modal-header">
                     <button type="button" class="close" data-dismiss="modal"
                        onClick="deleteScoreValue()">&times;</button>
                     <h4 class="modal-title">학생기타</h4>
                  </div>
                  <div class="modal-body">
                     <div>제목</div>
                     <div id="viewETCTitleS"></div>
                     <div>내용</div>
                     <div id=viewETCContentsS></div>
                     <div>파일</div>
                     <div id="viewETCfileS"></div>
                     <div>정답 제출</div>
                     <input type="text" id="viewETCAnswerS">
                  </div>
                  <div>기간</div>
                  <div>
                     <span id="viewETCStartS"></span> ~ <span id="viewETCEndS"></span>
                  </div>
                  <div class="modal-footer">
                     <button type="button" class="btn btn-default"
                        onClick="submitETC()">제출</button>
                     <button type="button" class="btn btn-default"
                        data-dismiss="modal">닫기</button>
                  </div>
               </div>
            </div>
         </div>
     

      <!-- 수정 삭제 보기(선생) -->
      <div class="modal fade" id="viewPostBoxT" role="dialog">
         <div class="modal-dialog">

            <!-- Modal content-->
            <div class="modal-content">
               <div class="modal-header">
                  <button type="button" class="close" data-dismiss="modal"
                     onClick="allPostList()">&times;</button>
                  <h4 class="modal-title">수정&삭제</h4>
               </div>
               <div class="modal-body">
                  <div id="updatePostBox" data-dismiss="modal"></div>
                  <div id="deletePostBox" data-dismiss="modal"></div>
               </div>
               <div class="modal-footer">
                  <button type="button" class="btn btn-default" data-dismiss="modal"
                     onClick="allPostList()">닫기</button>
               </div>
            </div>
         </div>
      </div>
 

            
            
            <!-- 과제 수정 -->
            <div class="modal fade" id="updateTask" role="dialog">
               <div class="modal-dialog">

                  <!-- Modal content-->
                  <div class="modal-content">
                     <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal"
                           onClick="allPostList()">&times;</button>
                        <h4 class="modal-title">과제수정</h4>
                     </div>
                     <div class="modal-body">
                     
                     <div class="m-view">제목</div>
                        <div id="updateTaskTitle"></div>
                     <div class="m-view">내용</div>
                        <div id=updateTaskContents></div>
                     <div class="m-view">파일 <img
                  src="resources/images/file-down.png"
                  style="height: 35px; width: 35px;" /></div>      
                        <div id="updateTaskAnswer"></div>
                     <div class="m-view">기간</div><div>        
                        <span id="updateTaskStart"></span>
                         ~ 
                        <span id="updateTaskEnd"></span>   
                                    </div>   
                        <div class="modal-footer">
                        <button type="button" class="btn btn-default" onClick="updateTask()">수정</button>
                           <button type="button" class="btn btn-default"
                              data-dismiss="modal" onClick="allPostList()">닫기</button>
                        </div>
                     </div>
                  </div>
               </div>
            </div>
            
            
            
            <!-- 퀴즈 수정 -->
            <div class="modal fade" id="updateQuiz" role="dialog">
               <div class="modal-dialog">

                  <!-- Modal content-->
                  <div class="modal-content">
                     <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal"
                           onClick="allPostList()">&times;</button>
                        <h4 class="modal-title">퀴즈수정</h4>
                     </div>
                     <div class="modal-body2">
                     
                     <div class="m-view2">제목</div>
                        <div id="updateQuizTitle"></div>
                     <div class="m-view2">질문</div>
                        <div id=updateQuizContents></div>
                     <div class="m-view2">정답</div>      
                        <div id="updateQuizAnswer"></div>
                     <div class="m-view2">기간</div><div>        
                        <span id="updateQuizStart"></span>
                         ~ 
                        <span id="updateQuizEnd"></span>   
                                    </div>   
                        <div class="modal-footer">
                        <button type="button" class="btn btn-default" onClick="updateQuiz()">수정</button>
                           <button type="button" class="btn btn-default"
                              data-dismiss="modal" onClick="allPostList()">닫기</button>
                        </div>
                     </div>
                  </div>
               </div>
            </div>
            
            
            
            <!-- 과제 삭제 -->
            <div class="modal fade" id="deleteTask" role="dialog">
               <div class="modal-dialog">

                  <!-- Modal content-->
                  <div class="modal-content">
                     <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal"
                           onClick="allPostList()">&times;</button>
                        <h4 class="modal-title">과제삭제</h4>
                     </div>
                     <div id="dbox">정말 삭제하시겠습니까?</div>
                        <div class="modal-footer">
                        <button type="button" class="btn btn-default" onClick="deleteTask()">삭제</button>
                           <button type="button" class="btn btn-default"
                              data-dismiss="modal" onClick="allPostList()">닫기</button>
                        </div>
                     </div>
                  </div>
               </div>
               
            <!-- 퀴즈 삭제 -->
            <div class="modal fade" id="deleteQuiz" role="dialog">
               <div class="modal-dialog">

                  <!-- Modal content-->
                  <div class="modal-content">
                     <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal"
                           onClick="allPostList()">&times;</button>
                        <h4 class="modal-title">퀴즈삭제</h4>
                     </div>
                     <div id="dbox">정말 삭제하시겠습니까?</div>
                        <div class="modal-footer">
                        <button type="button" class="btn btn-default" onClick="deleteQuiz()">삭제</button>
                           <button type="button" class="btn btn-default"
                              data-dismiss="modal" onClick="allPostList()">닫기</button>
                        </div>
                     </div>
                  </div>
               </div>
               
               
            <!-- 기타 삭제 -->
            <div class="modal fade" id="deleteETC" role="dialog">
               <div class="modal-dialog">

                  <!-- Modal content-->
                  <div class="modal-content">
                     <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal"
                           onClick="allPostList()">&times;</button>
                        <h4 class="modal-title">기타삭제</h4>
                     </div>
                     <div id="dbox">정말 삭제하시겠습니까?</div>
                        <div class="modal-footer">
                        <button type="button" class="btn btn-default" onClick="deleteETC()">삭제</button>
                           <button type="button" class="btn btn-default"
                              data-dismiss="modal" onClick="allPostList()">닫기</button>
                        </div>
                     </div>
                  </div>
               </div>

   <!-- 퀴즈 결과 확인(학생)1 -->
   <div class="modal fade" id="resultCheckQuiz" role="dialog">
      <div class="modal-dialog">

         <!-- Modal content-->
         <div class="modal-content">
            <div class="modal-header">
               <button type="button" class="close" data-dismiss="modal"
                  onClick="allPostList()">&times;</button>
               <h4 class="modal-title">퀴즈 보기</h4>
            </div>
            <div class="modal-body">
               <div id="resultCheckQuizBox" onClick="resultCheckQuiz()"
                  data-toggle="modal" data-target="#resultCheckQuizView"
                  data-dismiss="modal">보기</div>
            </div>
            <div class="modal-footer">
               <button type="button" class="btn btn-default" data-dismiss="modal"
                  onClick="allPostList()">닫기</button>
            </div>
         </div>
      </div>
   </div>

   <!-- 퀴즈 결과 확인(학생)2 -->
   <div class="modal fade" id="resultCheckQuizView" role="dialog">
      <div class="modal-dialog">

         <!-- Modal content-->
         <div class="modal-content">
            <div class="modal-header">
               <button type="button" class="close" data-dismiss="modal"
                  onClick="deleteQuizInfo()">&times;</button>
               <h4 class="modal-title">내 퀴즈 결과</h4>
            </div>
            <div class="modal-body">
               <div>
                  선생 정답
                  <div id="resultQuizT"></div>
               </div>
               <br>
               <div>
                  내 정답
                  <div id="resultQuizS"></div><br>
                  <div id="resultQuiz"></div>
               </div>
            </div>
            <div class="modal-footer">
               <button type="button" class="btn btn-default" data-dismiss="modal"
                  onClick="deleteQuizInfo()">닫기</button>
            </div>
         </div>
      </div>
   </div>

<!-- 기타 제출 확인 및 취소(학생)1 -->
   <div class="modal fade" id="cancelETCReport" role="dialog">
      <div class="modal-dialog">

         <!-- Modal content-->
         <div class="modal-content">
            <div class="modal-header">
               <button type="button" class="close" data-dismiss="modal"
                  onClick="allPostList()">&times;</button>
               <h4 class="modal-title">기타 확인&취소</h4>
            </div>
            <div class="modal-body">
               <div id="viewMyETC" onClick="viewMyETC()"
                  data-toggle="modal" data-target="#viewMyETCView"
                  data-dismiss="modal">보기</div><br>
               <div id="cancelETCBox" onClick="cancelETCReport()"
                  data-dismiss="modal">취소</div>
            </div>
            <div class="modal-footer">
               <button type="button" class="btn btn-default" data-dismiss="modal"
                  onClick="allPostList()">닫기</button>
            </div>
         </div>
      </div>
   </div>

<!-- 기타 제출 확인 및 취소(학생)2 -->
   <div class="modal fade" id="viewMyETCView" role="dialog">
      <div class="modal-dialog">

         <!-- Modal content-->
         <div class="modal-content">
            <div class="modal-header">
               <button type="button" class="close" data-dismiss="modal"
                  onClick="allPostList()">&times;</button>
               <h4 class="modal-title">내 기타</h4>
            </div>
            <div class="modal-body">
               <div>내 정답</div>
               <div id="resultETC"></div>
            </div>
            <div class="modal-footer">
               <button type="button" class="btn btn-default" data-dismiss="modal"
                  onClick="allPostList()">닫기</button>
            </div>
         </div>
      </div>
   </div>


<!-- 과제 제출 확인 및 취소(학생)1 -->
   <div class="modal fade" id="cancelTaskReport" role="dialog">
      <div class="modal-dialog">

         <!-- Modal content-->
         <div class="modal-content">
            <div class="modal-header">
               <button type="button" class="close" data-dismiss="modal"
                  onClick="allPostList()">&times;</button>
               <h4 class="modal-title">과제 확인&취소</h4>
            </div>
            <div class="modal-body">
               <div id="viewMyTask" onClick="viewMyTask()"
                  data-toggle="modal" data-target="#viewMyTaskView"
                  data-dismiss="modal">보기</div><br>
               <div id="cancelETCBox" onClick="deleteTask()"
                  data-dismiss="modal">취소</div>
            </div>
            <div class="modal-footer">
               <button type="button" class="btn btn-default" data-dismiss="modal"
                  onClick="allPostList()">닫기</button>
            </div>
         </div>
      </div>
   </div>

<!-- 과제 제출 확인 및 취소(학생)2 -->
   <div class="modal fade" id="viewMyTaskView" role="dialog">
      <div class="modal-dialog">

         <!-- Modal content-->
         <div class="modal-content">
            <div class="modal-header">
               <button type="button" class="close" data-dismiss="modal"
                  onClick="allPostList()">&times;</button>
               <h4 class="modal-title">내 과제</h4>
            </div>
            <div class="modal-body">
               <div>내 파일</div>
               <div id="resultTaskFile"></div><br><br>
               <div>내 정답</div>
               <div id="resultTaskAnswer"></div>
            </div>
            <div class="modal-footer">
               <button type="button" class="btn btn-default" data-dismiss="modal"
                  onClick="allPostList()">닫기</button>
            </div>
         </div>
      </div>
   </div>

</body>
</html>