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

body {
  text-align: center;
  padding: 50px;
}

.button {
  display: inline-block;
  border : 1px solid #aec9f5; 
  background-color: #ffffff;
  text-transform: uppercase;
  color : #757575;
  padding: 20px 30px;
  border-radius: 5px;
  box-shadow: 0px 17px 10px -10px rgba(0, 0, 0, 0.4);
  cursor: pointer;
  -webkit-transition: all ease-in-out 300ms;
  transition: all ease-in-out 300ms;
  width : 200px;
  height : 50px;
  line-height:10px;
  font-weight : bold;
}
.button:hover {
  box-shadow: 0px 37px 20px -20px rgba(0, 0, 0, 0.2);
  -webkit-transform: translate(0px, -10px) scale(1.2);
          transform: translate(0px, -10px) scale(1.2);
}
</style>


<body onload="getStuList('${mbId}' ,'0009' , '0000006')">

	<nav class="navbar navbar-inverse navbar-fixed-top">
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
					<li><a href="#" id="test" style="color: white;"><img
							src="resources/images/ELJL_LOGO2.png"
							style="height: 30px; width: 30px;" /></a></li>
				</ul>
			</div>

			<div style="margin: 0 auto;">
				<ul class="nav navbar-nav">
					<li><a href="#" style="color: white;">스트림</a></li>
					<li><a href="#" style="color: white;">수업</a></li>
					<li><a href="#" style="color: white;">출석부</a></li>
					<li><a href="#" style="color: white;">학생관리</a></li>
					<li><a style="color: white;">설정</a></li>
				</ul>
			</div>
			<div>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="#"><span class="glyphicon glyphicon-cog"
							style="color: white;"></span></a></li>
					<li><a style="color: white;"><span
							class="glyphicon glyphicon-log-out"></span> LogOut</a></li>
				</ul>
			</div>
		</div>
	</nav>
	
	<div style="width: 100%; height: 30px;"></div>
	<div class="Attendmanage" style=" margin-right:auto; margin-top: 30px; height: 85px;">
	
	<div class="button" style="margin-right:150px;" onClick="todayAttend(), getStuList('${mbId}' ,'0001' , '0000002')" >당일 출석 관리</div>
		
	<div class="button" onClick="allAttend()">전체 출석 관리</div>
	
	
	</div>
	<div id = "changePage">
	<div style = "margin-left: 10px; font-size: 25px;" >
	 <%= sf.format(nowTime) %>
	 </div>
	<br>
	<div class="panel panel-info" style="margin-left: 100px;">
      <div class="panel-heading" style="font-weight:bold; text-align:center; margin-left: 250px; margin-right: 300px;">출석 리스트</div>
      <div class="col-xs-12 panel-body" id="stuList" style=" font-weight:bold; margin-left: auto;  margin-right: auto;"> 
      
     </div>
     
    </div>
	
	<div class="AttendCkeck">
		<input type="button"  class="btn btn-default btn-lg"
			style=" margin-left: 30px;  margin-top: 50px; width: 280px; height: 60px; "

			value="출석체크" onClick="sendAttendCheck()"  />
	</div>
	</div>
	
	<div id="hiddenBox">
	</div>
	
	<!-- 수정 모달창 -->

 <div class="editModal">
  <button type="button" id="editModal" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myModal" onClick="editAttendList()">출석 수정</button>

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

