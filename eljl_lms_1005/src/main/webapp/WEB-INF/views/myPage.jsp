<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>마이페이지</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
   href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet"
   href="http://humy2833.dothome.co.kr/customizing.html#">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Gowun+Dodum&display=swap" rel="stylesheet"> 
<link rel="stylesheet" href="resources/css/master.css">
<script
   src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
   src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script type="text/javascript"
   src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.24.0/moment.min.js"></script>
   
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

#bannerBox > ul{
   margin-top: 5px;
}

#bannerBox2 > ul{
   margin-top: 5px;
}

</style>

</head>
<body onload="getAjax('https://api.ipify.org','format=json', 'getPublicIp');">
<div id="vue" class="container">
<input type='hidden' name='mbType' value='${mbType}' />
   <input type='hidden' name='mbId' value='${mbId}' />
   <input type='hidden' name='csCode' value='${csCode}' />
   <input type='hidden' name='opCode' value='${opCode}' />
   <input type='hidden' name='opName' value='${opName}' />

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
            <li><a style="color: white;" @click="logOut(info.stuId)"><span
                  class="glyphicon glyphicon-log-out"></span> 로그아웃</a></li>
         </ul>
      </div>
   </div>
</nav>
   <!-- 테이블 -->
   <div style="height: 60px;"></div>
   <div class="container">
      <div class="row">
         <h2>마이페이지</h2>
         <!-- 성적목록 -->
         
         <div>
            <div>
               <table class="table table-hover">
                  <thead>
                     <h3>성적 목록</h3>
                     <tr>
                        <th scope="col">순번</th>
                        <th scope="col">과제</th>
                        <th scope="col">제출물</th>
                        <th scope="col">성적</th>
                        <th scope="col">제출날짜</th>
                     </tr>
                  </thead>
                  <tbody>
                     <tr data-toggle="modal" data-target="#showTaskView" @click="dataMove(index)" v-for="(grade, index) in gradeList">
                        <th scope="row">{{index + 1}}</th>
                        <td>{{grade.sjTitle}}</td>
                        <td>{{grade.stuTitle}}</td>
                        <td>{{grade.grade}}</td>
                        <td>{{grade.createDate}}</td>
                     </tr>
                  </tbody>
               </table>
            </div>
            <!-- 출결 목록 -->
         
            <div>
               <table class="table table-hover">
                  <thead>
                     <h3>출결 목록</h3>
                     <tr>
                        <th scope="col">순번</th>
                        <th scope="col">날짜</th>
                        <th scope="col">상태</th>
                     </tr>
                  </thead>
                  <tbody>
                     <tr v-for="(sign, index) in siList">
                        <th scope="row">{{index + 1}}</th>
                        <td>{{sign.createDate}}</td>
                        <td>{{sign.status}}</td>
                     </tr>
                  </tbody>
               </table>
            </div>
            <!-- 상담신청 목록 -->
            
            <div>
               <table class="table table-hover">
                  <thead>
                     <h3>상담신청 목록</h3>
                     <button data-toggle="modal" data-target="#insertCos"
                        class="glyphicon glyphicon-plus"></button>
                     <tr>
                        <th scope="col">순번</th>
                        <th scope="col">상담신청제목</th>
                        <th scope="col">작성날짜</th>
                        <th scope="col">상담신청날짜</th>
                        <th scope="col">상태</th>
                        <th scope="col">기록 삭제</th>
                     </tr>
                  </thead>
                  <tbody>
                     <tr v-for="(counSeling, index) in cosList">
                        <th scope="row">{{index + 1}}</th>
                        <td @click="statusCheck2(counSeling.status,index)" data-toggle="modal" data-target="#cosDetails">{{counSeling.stuTitle}}</td>
                        <td @click="statusCheck2(counSeling.status,index)" data-toggle="modal" data-target="#cosDetails">{{counSeling.createDate}}</td>
                        <td @click="statusCheck2(counSeling.status,index)" data-toggle="modal" data-target="#cosDetails">{{counSeling.requestDate}}</td>
                        <td @click="statusCheck2(counSeling.status,index)" data-toggle="modal" data-target="#cosDetails">{{counSeling.status}}</td>
                        <td><button @click="statusCheck(counSeling.status),indexCheck(index)" class="glyphicon glyphicon-trash" data-toggle="modal" data-target="#deleteCos"></button></td>
                     </tr>
                  </tbody>
               </table>
            </div>
            <!-------------------------------------------------------------- 상담신청 -------------------------------------------------------------------->
            <div class="modal fade" id="insertCos" role="dialog">
               <div class="modal-dialog">

                  <!-- Modal content-->
                  <div class="modal-content">
                     <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">상담신청</h4>
                     </div>
                     <div class="modal-body">
                        <h5>상담신청 제목</h5>
                        <input style="width:513px;" name="insTitle" placeholder="제목을 입력하세요.">
                        <h5>상담신청 내용</h5>
                        <textarea rows="15" cols="70" name="insContents" placeholder="내용을 입력하세요."></textarea>
                        <h5>상담신청 날짜</h5>
                        <input type="date" name="requestDate">
                     </div>
                     <div class="modal-footer">
                        <button type="button" class="btn btn-default"
                           data-dismiss="modal" @click="insertCos()">생성</button>
                        <button type="button" class="btn btn-default"
                           data-dismiss="modal">닫기</button>
                     </div>
                  </div>
               </div>
            </div>
<!-------------------------------------------------------------- 상담신청 삭제 -------------------------------------------------------------------->
            <div v-if="display[1].show" class="modal fade" id="deleteCos" role="dialog">
               <div class="modal-dialog">

                  <!-- Modal content-->
                  <div class="modal-content">
                     <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">상담신청 삭제</h4>
                     </div>
                     <div class="modal-body">
                        <h5>삭제하시겠습니까?</h5>
                        <div></div>
                        <h5>상담신청 제목</h5>
                        <div class="text-primary">{{info.stuTitle}}</div>
                        <h5>상담신청 내용</h5>
                        <div class="text-primary">{{info.contents}}</div>
                        <h5>상담신청 날짜</h5>
                        <div class="text-primary">{{info.requestDate}}</div>                        
                     </div>
                     <div class="modal-footer">
                        <button type="button" class="btn btn-default"
                           data-dismiss="modal" @click="deleteCos()">삭제</button>
                        <button type="button" class="btn btn-default"
                           data-dismiss="modal">닫기</button>
                     </div>
                  </div>
               </div>
            </div>
<!-------------------------------------------------------------- 상담신청 디테일 and 수정 -------------------------------------------------------------------->
            <div>
            <input type="hidden" name="updateTitle">
            <input type="hidden" name="updateContents">
            </div>   
            <div v-if="display[2].show" class="modal fade" id="cosDetails" role="dialog">
               <div class="modal-dialog">

                  <!-- Modal content-->
                  <div class="modal-content">
                     <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">상담신청</h4>
                     </div>
                     <div class="modal-body">
                        <h5>상담신청 제목</h5>
                        <input style="width:513px;" name="updateTitle" placeholder="제목을 입력하세요.">
                        
                        <h5>상담신청 내용</h5>
                        <textarea rows="15" cols="70" name="updateContents" placeholder="내용을 입력하세요."></textarea>
                        <h5>상담신청 날짜</h5>
                        <div class="text-primary">{{info.requestDate}}</div>
                     </div>
                     <div class="modal-footer">
                        <button id ="button1" type="button" class="btn btn-default"
                         data-dismiss="modal" @click="updateCos(info.requestDate)">수정</button>
                        <button type="button" class="btn btn-default"
                           data-dismiss="modal">닫기</button>
                     </div>
                  </div>
               </div>
            </div>            
<!---------------------------------------------------------- TASK,ETC 디테일 -------------------------------------------------------------------->
            <div v-if="display[3].show" class="modal fade" id="showTaskView" role="dialog">
               <div class="modal-dialog">

                  <!-- Modal content-->
                  <div class="modal-content">
                     <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">{{info.ssName}}</h4>
                     </div>
                     <div class="modal-body">
                        <div></div>
                        <h5>{{info.ssName}}</h5>
                        <pre class="pre-scrollable text-primary text-center" >{{info.sjTitle}}</pre>
                        <h5>{{info.ssName}} 기간</h5>
                        <div>
                        <pre class="pre-scrollable text-primary text-center" >{{info.startDate}} ~ {{info.endDate}}</pre>
                        </div>   
                        <h5>{{info.ssName}} 내용</h5>
                        <pre class="pre-scrollable text-primary text-center" >{{info.contents}}</pre>
                        <h5>제출 파일</h5>
                        <pre class="pre-scrollable text-primary text-center" >{{info.file}}</pre>
                                             
                     </div>
                     <div class="modal-footer">
                        <button type="button" class="btn btn-default"
                           data-dismiss="modal">닫기</button>
                     </div>
                  </div>
               </div>
            </div>
<!---------------------------------------------------------- QUIZ 디테일 -------------------------------------------------------------------->
            <div v-if="display[4].show" class="modal fade" id="showTaskView" role="dialog">
               <div class="modal-dialog">

                  <!-- Modal content-->
                  <div class="modal-content">
                     <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">{{info.ssName}}</h4>
                     </div>
                     <div class="modal-body">
                        <div></div>
                        <h5>{{info.ssName}}</h5>
                        <pre class="pre-scrollable text-primary text-center" >{{info.sjTitle}}</pre>
                        <h5>{{info.ssName}} 기간</h5>
                        <div>
                        <pre class="pre-scrollable text-primary text-center" >{{info.startDate}} ~ {{info.endDate}}</pre>
                        </div>   
                        <h5>{{info.ssName}}</h5>
                        <pre class="pre-scrollable text-primary text-center" >{{info.contents}}</pre>
                        <h5>제출한 답</h5>
                        <pre class="pre-scrollable text-primary text-center" >{{info.file}}</pre>
                                             
                     </div>
                     <div class="modal-footer">
                        <button type="button" class="btn btn-default"
                           data-dismiss="modal">닫기</button>
                     </div>
                  </div>
               </div>
            </div>            
            
            
            
            
         </div>
      </div>
   </div>
   </div>
   <script type="text/javascript" src="resources/vue/vue.js"></script>
<script type="text/javascript" src="resources/js/master.js"></script>
<script type="text/javascript" src="resources/js/myPage.js"></script>   
<script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
</body>
   
</html>
