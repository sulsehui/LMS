<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ page import="java.util.*, java.text.*" %>
<html lang="en">
<head>
  <title>LMS</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
  <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.24.0/moment.min.js"></script>
  <style>
    /* Remove the navbar's default margin-bottom and rounded borders */ 
    .navbar {
      margin-bottom: 0;
      border-radius: 0;
    }
    
    /* Set height of the grid so .sidenav can be 100% (adjust as needed) */
    .row.content {height: 450px}
    
    /* Set gray background color and 100% height */
    .sidenav {
      padding-top: 20px;
      background-color: #f1f1f1;
      height: 100%;
    }
    
    /* Set black background color, C and some padding */
    footer {
      background-color: #555;
      color: white;
      padding: 15px;
    }
    
    /* On small screens, set height to 'auto' for sidenav and grid */
    @media screen and (max-width: 767px) {
      .sidenav {
        height: auto;
        padding: 15px;
      }
      .row.content {height:auto;} 
    }
  </style>
  <script type="text/javascript" src="resources/js/master.js"></script>
  <script type="text/javascript" src="resources/js/main.js"></script>
</head>
<body onload="getList('${mbId}'), divCreate(), otherClass(), getAjax('https://api.ipify.org','format=json', 'getPublicIp')">

<% Date date = new Date(); 
SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");
String strdate = simpleDate.format(date);
%>


<div id="hiddenBox">
<input type='hidden' name='mbType' value='${mbType}' />

</div>
<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>                        
      </button>
      <a class="navbar-brand" href="#"></a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav">
        <li class="active"><a href="#">이러닝저러닝</a></li>
        <li class="dropdown" id="user" style = "display:none;" >
            <a class="dropdown-toggle" data-toggle="dropdown" href="#"> <p><span class="glyphicon glyphicon-plus-sign"><span class="caret"></span></span></p> </a>
            <ul class="dropdown-menu" id="classMenu">
              <li><a data-toggle="modal" data-target="#createClass1"><span class="glyphicon glyphicon-plus" > Class</span></a></li> 
              
              <li onClick="deleteClassList()"><a data-toggle="modal" data-target="#deleteClass"><span class="glyphicon glyphicon-trash" > Delete</span></a></li>                        
           </ul>
       </li>
        <li><a href="#">Calender</a></li>
        
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <li><a href="#" onClick="logOut('${mbId}')"><span class="glyphicon glyphicon-log-in" > LogOut</span></a></li>
      </ul>
    </div>
  </div>
</nav>
  
<div class="container-fluid text-center">    
  <div class="row content">
    <div class="col-sm-1 text-left"> </div>
    <div class="col-sm-10 text-left"> 
      <h1>내 강좌 목록</h1>
      <div id="myClass" class="well well-lg">내 강좌</div>
      <hr>
      <h1>다른 강좌 목록</h1>
      <div id ="otherClassList" class="well well-lg"></div>
    </div>
    
    </div>
  </div>
</div>
<!-- 클래스 생성 모달창 첫번째 -->
<div class="modal fade" id="createClass1" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" onClick="deleteValue()" >&times;</button>
          <h4 class="modal-title">강좌 생성(1/2) </h4>
        </div>
        <div class="modal-body">
          <p>학과 명</p>
          	<input type="text" name="csName">
           <p>강좌 명</p>
             <input type="text" name="opName">	
           <p>시작 날짜</p>
          	<input type="date" name="startDate" min="<%=strdate %>" >
            <p>종료 날짜</p>
          	<input type="date" name="endDate"  min="<%=strdate %>" onblur="endDateCheck()">
        </div>
        <div class="modal-footer">
         <button type="button" class="btn btn-default" data-dismiss="modal" data-toggle="modal" data-target="#createClass2" onClick="">다음</button>
          <button type="button" class="btn btn-default" data-dismiss="modal" onClick="deleteValue()">닫기</button>
        </div>
        
         
      
      </div>
      
    </div>
  </div>
  <!-- 클래스 생성 모달창 두번째 -->
  <div style="overflow:scroll;" class="modal fade" id="createClass2" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">강좌 생성(2/2) </h4>
        </div>
        <div class="modal-body" id="modal-body">
          <p>성적관리<a onClick="addCategory()"><span class="glyphicon glyphicon-plus" ></span></a></p>
          <div id="modalBox">
          </div>
          
          
         
        </div>
        <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal" data-toggle="modal" data-target="#createClass1" >이전</button>
          <button type="button" class="btn btn-default" onClick="createClass()">생성</button>
          <button type="button" class="btn btn-default" data-dismiss="modal" onClick="deleteValue()">닫기</button>
        </div>
        
          
        
      </div>
      
    </div>
  </div>
  
 <!-- 삭제 모달창 -->
  <div class="modal fade" id="deleteClass" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">강좌 삭제 </h4>
        </div>
        <div class="modal-body">
        
          	<table class="table table-striped">
		    <thead>
		      <tr>
		        <th>삭제할 강좌 목록</th>
		        <th>분류</th>
		      </tr>
		    </thead>
		    <tbody id="deleteClassBox">
		
		    </tbody>
		    </table>
            
            
        </div>
        <div class="modal-footer">
        
          <button type="button" class="btn btn-default" data-dismiss="modal" onClick="getList()">닫기</button>
        </div>
        
         
      
      </div>
      
    </div>
  </div> 
  
  <!-- 수강신청 모달창-->
  <div class="modal fade" id="joinClass" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" onClick="deleteValue()" >&times;</button>
          <h4 class="modal-title">수강신청 </h4>
        </div>
        <div class = "modal-body">수강신청 하시겠습니까?</div>
        <div class="modal-footer">
         <button type="button" class="btn btn-default" data-dismiss="modal" onClick="joinClass()">예</button>
          <button type="button" class="btn btn-default" data-dismiss="modal" >아니오</button>
        </div>
        
      </div>
      
    </div>
  </div> 
  
  <!-- 수강 취소 모달창-->
  <div class="modal fade" id="cancelClass" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" >&times;</button>
          <h4 class="modal-title">수강 취소 </h4>
        </div>
        <div class = "modal-body">해당 강좌를 취소하시겠습니까?</div>
        <div class="modal-footer">
         <button type="button" class="btn btn-default" data-dismiss="modal" onClick="cancelClass(number)">예</button>
          <button type="button" class="btn btn-default" data-dismiss="modal" >아니오</button>
        </div>
        
      </div>
      
    </div>
  </div> 
  
</body>
</html>