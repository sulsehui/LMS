<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>학생관리</title>
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
<script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.24.0/moment.min.js"></script>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Gowun+Dodum&display=swap" rel="stylesheet"> 
<link rel="stylesheet" href="resources/css/master.css">

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
<body onLoad="mbTypeCheck('${mbType}'); getAllStuList(); getAjax('https://api.ipify.org','format=json', 'getPublicIp');">

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
            <li><a style="color: white;" onClick="logOut('${mbId}')"><span
                  class="glyphicon glyphicon-log-out"></span> 로그아웃</a></li>
         </ul>
      </div>
   </div>
</nav>
   <!-- 테이블 -->
   <div style="height: 60px;"></div>
   <div id="vue" class="container">
      <div class="row">
         <h2 @click="changepage(0),returnPage()">학생관리</h2>
         <div class="col-lg-2">
            <table class="table table-hover">
               <thead>
                  <tr>
                     <h3>학생리스트</h3>
                     <th scope="col"></th>
                  </tr>
               </thead>
               <tbody>
                  <tr v-for="name in list">
                     <td
                        @click="getDetailsList(name),changepage(1)">{{name.stuName}}</td>                        
                  </tr>
               </tbody>
            </table>
         </div>
         <div class="col-lg-1"></div>
         <div v-if="display[0].show" id="displayon" class="col-lg-9">
            <table  class="table table-hover">
               <thead>
                  <tr>
                     <th scope="col">순번</th>
                     <th scope="col">성적</th>
                     <th scope="col">결석 횟수</th>
                     <th scope="col">상담일지</th>
                  </tr>
               </thead>
               <tbody>
                  <tr v-for="(manages, index) in list">
                     <th scope="row">{{index + 1}}</th>
                     <td>{{manages.allGrade}}</td>
                     <td>{{manages.absence}}</td>
                     <td>{{manages.coCount}}</td>
                  </tr>
               </tbody>
            </table>
         </div>
         <!-- 성적목록 -->
         <div v-if="display[1].show" class="col-lg-9">
            <div>
               <table  class="table table-hover">
                  <thead>
                     <h3>성적 목록</h3>
                     <button @click="updateGrade()" class="glyphicon glyphicon-download-alt">저장</button>
                     <tr>
                        <th scope="col">순번</th>
                        <th scope="col">과제</th>
                        <th scope="col">제출물</th>
                        <th scope="col">성적</th>
                        <th scope="col">제출날짜</th>
                     </tr>
                  </thead>
                  <tbody>
                     <tr v-for="(grade, index) in gradeList">
                        <th scope="row">{{index + 1}}</th>
                        <td>{{grade.sjTitle}}</td>
                        <td>{{grade.stuTitle}}</td>
                        <td><input v-model="gradeList[index].grade"></td>
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
                     <tr data-toggle="modal" data-target="#updateSign" @click="getTime(index)" v-for="(sign, index) in siList">
                        <th scope="row">{{index + 1}}</th>
                        <td>{{sign.startDate}}</td>
                        <td>{{sign.status}}</td>
                     </tr>
                  </tbody>
               </table>
            </div>
<!-------------------------------------------------------------- 출결 수정 -------------------------------------------------------------------->
<div class="modal fade" id="updateSign" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" >&times;</button>
          <h4 class="modal-title">출결 수정 </h4>
        </div>
        <div class="modal-body">
          <h4>출결을 수정 해주세요</h4>
             <pre class="text-primary">{{info.stuName}}</pre>
           <h4>수정 날짜</h4>            
             <pre class="text-primary">{{info.startDate}}</pre>       
             <h4>분류</h4>
             
             <form>
             <label class="radio-inline">
               <input type="radio" name="status" value="O"><span class="glyphicon glyphicon-ok"> 출석</span>
             </label>
             <label class="radio-inline">
               <input type="radio" name="status" value="L"><span class="glyphicon glyphicon-triangle-top"> 지각</span>
             </label>
             <label class="radio-inline">
               <input type="radio"  name="status" value="P"><span class="glyphicon glyphicon-minus"> 조퇴</span>
             </label>
             <label class="radio-inline">
               <input type="radio" name="status" value="X"><span class="glyphicon glyphicon-remove"> 결석</span>
             </label>
           
           </form>
           
        </div>
        <div class="modal-footer">
         <button type="button" class="btn btn-default" data-dismiss="modal" @click="updateSign()">수정</button>
          <button type="button" class="btn btn-default" data-dismiss="modal" >닫기</button>
        </div>
      </div> 
    </div>
  </div>

            <!-- 상담일지 목록 -->
            <div>
               <table class="table table-hover">
                  <thead>
                     <h3>상담일지 목록</h3>
                     <button data-toggle="modal" data-target="#insertCounSeling" @click="resetContents()" class="glyphicon glyphicon-plus"></button>
                     <tr>
                        <th scope="col">순번</th>
                        <th scope="col">상담일지</th>
                        <th scope="col">상담날짜</th>
                     </tr>
                  </thead>
                  <tbody>
                     <tr data-toggle="modal" data-target="#selectBox" @click="getCounSeling(counSeling.coCode,counSeling.sjTitle,counSeling.contents)" v-for="(counSeling, index) in coList">
                        <th scope="row">{{index + 1}}</th>
                        <td>{{counSeling.sjTitle}}</td>
                        <td>{{counSeling.createDate}}</td>
                     </tr>
                  </tbody>
               </table>
            </div>
<!-- 상담신청 목록 -->
            
            <div>
               <table class="table table-hover">
                  <thead>
                     <h3>상담신청 목록</h3>
                     <tr>
                        <th scope="col">순번</th>
                        <th scope="col">상담신청제목</th>
                        <th scope="col">작성날짜</th>
                        <th scope="col">상담신청날짜</th>
                        <th scope="col">상태</th>
                     </tr>
                  </thead>
                  <tbody>
                     <tr data-toggle="modal" data-target="#cosDetails"  @click="completeCos(index)" v-for="(counSeling, index) in cosList">
                        <th scope="row">{{index + 1}}</th>
                        <td>{{counSeling.stuTitle}}</td>
                        <td>{{counSeling.createDate}}</td>
                        <td>{{counSeling.requestDate}}</td>
                        <td>{{counSeling.status}}</td>
                     </tr>
                  </tbody>
               </table>
            </div>
            
<!-------------------------------------------------------------- 상담신청 디테일 and 수정 -------------------------------------------------------------------->
            <div class="modal fade" id="cosDetails" role="dialog">
               <div class="modal-dialog">

                  <!-- Modal content-->
                  <div class="modal-content">
                     <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">상담신청</h4>
                     </div>
                     <div class="modal-body">
                        <h5>상담신청 제목</h5>
                        <pre class="text-primary">{{info.stuTitle}}</pre>
                        <h5>상담신청 내용</h5>
                        <pre class="text-primary">{{info.contents}}</pre>
                        <h5>상담신청 날짜</h5>
                        <pre class="text-primary">{{info.requestDate}}</pre>
                     </div>
                     <div class="modal-footer">
                        <button type="button" class="btn btn-default"
                           data-dismiss="modal" @click="yesOrNoCos('O')">수락</button>
                        <button type="button" class="btn btn-default"
                           data-dismiss="modal" @click="yesOrNoCos('X')">거절</button>
                        <button type="button" class="btn btn-default"
                           data-dismiss="modal">닫기</button>
                     </div>
                  </div>
               </div>
            </div>            
<!-------------------------------------------------------------- 상담일지 생성 -------------------------------------------------------------------->
<div class="modal fade" id="insertCounSeling" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" >&times;</button>
          <h4 class="modal-title">상담일지 생성</h4>
        </div>
        <div class="modal-body">
          <h5>상담일지 제목</h5>
             <input style="width:513px;" name="insTitle"  placeholder= "제목을 입력하세요.">
           <h5>상담일지 내용</h5>            
             <textarea rows="15" cols="70" name="insContents" placeholder= "내용을 입력하세요.">   </textarea>         
        </div>
        <div class="modal-footer">
         <button type="button" class="btn btn-default" data-dismiss="modal" @click="insertCounSeling()">생성</button>
          <button type="button" class="btn btn-default" data-dismiss="modal" >닫기</button>
        </div>
      </div> 
    </div>
  </div>
<!-------------------------------------------------------------- 선택 버튼(수정,삭제) -------------------------------------------------------------------->
<div class="modal fade" id="selectBox" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" >&times;</button>
          <h4 class="modal-title">선택 상자</h4>
        </div>
        <div class="modal-body">
          <pre>다음 중 실행할 행동을 선택하세요.</pre>         
        </div>
        <div class="modal-footer">
         <button type="button" class="btn btn-default" data-dismiss="modal" data-toggle="modal" data-target="#updateCounSeling">수정</button>
         <button type="button" class="btn btn-default" data-dismiss="modal" data-toggle="modal" data-target="#deleteCounSeling">삭제</button>
          <button type="button" class="btn btn-default" data-dismiss="modal" >닫기</button>
        </div>
      </div> 
    </div>
  </div>

<!-------------------------------------------------------------- 상담일지 수정 -------------------------------------------------------------------->
<div class="modal fade" id="updateCounSeling" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" >&times;</button>
          <h4 class="modal-title">상담일지 수정 </h4>
        </div>
        <div class="modal-body">
          <h5>상담일지 제목</h5>
             <input style="width:513px;" name="sjTitle"  placeholder= "수정할 제목을 입력하세요.">
           <h5>상담일지 내용</h5>            
             <textarea rows="15" cols="70" name="contents" placeholder= "수정할 내용을 입력하세요."> </textarea>           
        </div>
        <div class="modal-footer">
         <button type="button" class="btn btn-default" data-dismiss="modal" @click="updateCounSeling()">수정</button>
          <button type="button" class="btn btn-default" data-dismiss="modal" >닫기</button>
        </div>
      </div> 
    </div>
  </div>
<!-------------------------------------------------------------- 상담일지 삭제 -------------------------------------------------------------------->
<div class="modal fade" id="deleteCounSeling" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" >&times;</button>
          <h4 class="modal-title">상담일지 삭제</h4>
        </div>
        <div class="modal-body">
          <pre>해당 상담일지를 삭제하시겠습니까?</pre>
        </div>
        <div class="modal-footer">
         <button type="button" class="btn btn-default" data-dismiss="modal" @click="deleteCounSeling()">삭제</button>
          <button type="button" class="btn btn-default" data-dismiss="modal" >닫기</button>
        </div>
      </div> 
    </div>
  </div>            
            
         </div>
      </div>
   </div>
   </div>

   <script type="text/javascript" src="resources/js/master.js"></script>
   <script type="text/javascript" src="resources/js/stuManage.js"></script>
   <script type="text/javascript" src="resources/vue/vue.js"></script>



</body>

</html>
