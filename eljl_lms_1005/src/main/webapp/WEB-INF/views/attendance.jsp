<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%
	Date nowTime = new Date();
	SimpleDateFormat sf = new SimpleDateFormat("yyyy년 MM월 dd일");
%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Attendance</title>
<meta charset="utf-8">
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
</head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">

<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.24.0/moment.min.js"></script>
<link href='/resources/css/attendance.css' rel='stylesheet' />
<script type="text/javascript" src="resources/js/master.js"></script>
<script type="text/javascript" src="resources/js/attendance.js"></script>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Gowun+Dodum&display=swap" rel="stylesheet"> 
<link rel="stylesheet" href="resources/css/master.css">

<body onload="mbTypeCheck('${mbType}'); getStuList();">

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
	
	<input type='hidden' name='mbId' value='${mbId}' />
	<input type='hidden' name='csCode' value='${csCode}' />
	<input type='hidden' name='opCode' value='${opCode}' />
	<input type='hidden' name='opName' value='${opName}' />
	
	<div style="width: 100%; height: 30px;"></div>
	<div class="Attendmanage">
	
	<div class="button" onClick="todayAttend(); getStuList();" >당일 출석 관리</div>
		
	<div class="button" onClick="allAttend()">전체 출석 관리</div>
	
	
	</div>
	<div id = "changePage">
	<div style = "margin-left: 5px; font-size: 25px;" >
	 <%= sf.format(nowTime) %>
	 </div>
	<br>
	<div class="panel panel-info">
	
      <div class="panel-heading">출석 리스트</div>
      
      <div class="col-xs-12 panel-body">
      <div id="stuList" ></div>
      
       </div> 
       </div>
     <br><br>
     <div class="AttendCkeck">
		<input type="button" class="btn btn-default btn-lg" value="출석체크" onClick="sendAttendCheck()"  />
	</div>
     
     
    
	
	
	</div>
	
	<div id="hiddenBox">
	</div>
	
	<!-- 수정 모달창 -->

 <div class="editModal">
  <button type="button" id="editModal" class="btn btn-info btn-lg" 
   data-toggle="modal" data-target="#myModal" onClick="editAttendList()">출석 수정</button>

  <div class="modal fade" id="myModal" role="dialog" >
    <div class="modal-dialog">
    
      <div class="modal-content"  style=" right : 135% ">
        <div class="modal-header">
         
          <h4 class="modal-title">출석 수정</h4>
        </div>
        <div class="modal-body" >
          <select name = "editStuName" onchange="editDate()">
          	<option value = "stu1">학생이름</option>
          </select>
          <br> <br>
          <select name = "editDateList" onchange = "editStatus()">
          	<option value = "date1">날짜선택</option>
           </select>
           <br><br>
           <input type = "button" class="btn btn-warning" name = "late" value="지각" onClick="editCheck(this.value)" />
           <input type = "button" class="btn btn-primary" name = "early" value="조퇴" onClick="editCheck(this.value)"/>
           <input type = "button" class="btn btn-danger" name = "absent" value="결석" onClick="editCheck(this.value)"/>
           <input type = "button" class="btn btn-success" name = "success" value="출석" onClick="editCheck(this.value)"/><br><br>
        	변경 전 :<div id = "beforeName"></div><br>
            변경 후 : <div id = "afterName"></div>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" onClick="editAttend()">수정</button>
          <button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
        </div>
      </div>
      
    </div>
  </div>
  </div>
  
</body>
</html>

