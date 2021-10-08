<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Stream</title>
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
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Gowun+Dodum&display=swap" rel="stylesheet"> 
<link rel="stylesheet" href="resources/css/master.css">
<script type="text/javascript">
function NoticeOutput(){
	let mbId = document.getElementsByName("mbId")[0];
	let csCode = document.getElementsByName("csCode")[0];
	let opCode = document.getElementsByName("opCode")[0];
	
	console.log(mbId.value);
	console.log(csCode.value);
	console.log(opCode.value);
}
</script>
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

#classTitle {
	margin-top: 50px;
	height: 100px;
	background-color: #EBF7FF;
	text-align: center;
}

/* 공지사항 제목칸 */
#titlebox{
	box-sizing: border-box;
	width: 400px; /* 100% */
	height: 100px;
	margin-bottom: -2px;
	border-style: solid;
	border-width: 4px;
	border-color: skyblue;
}

/* 공지사항 작성칸 */
#textbox {
	box-sizing: border-box;
	width: 400px; /* 100% */
	height: 300px;
	border-style: solid;
	border-width: 4px;
	border-color: skyblue;
	word-break:normal; /* 줄바꿈 */
}

/* 마감과제일정 글자영역 */
#scfontbox-sfb{
	margin-bottom: 5px;
	margin-left: 95px;
	
	float: left; /* 가로정렬 */
}

/* 퀴즈일정 글자영역 */
#scfontbox-sf{
	margin-bottom: 5px;
	margin-left: 275px;
	
	float: left; /* 가로정렬 */
}

/* 기타일정 글자영역 */
#scfontbox-sb{
	margin-bottom: 5px;
	margin-left: 295px;
	
	float: left; /* 가로정렬 */
}

/* 일정글자 */
#scfont{
	font-size: 17px;
}

.button {
	width: 80px;
	height: 40px;
	margin-top: 7px;
	margin-left: 318px;
	background-color: #5AAEFF;
	border-color: transparent; /* 박스 테두리 투명 */
	color: #FFFFFF;
	font-size: 20px;
}

/* 일정박스 */
.col-sm-4{
	width: 300px;
	height: 200px;
	margin-right: 60px;
	border-radius: 20px;
	border: 2px solid #C7C6FF;
	box-shadow: 2px 3px 5px #999; /* h-offset v-offset blur spread color type */
}

/* 일정박스영역 */
.row{
	margin-top: 30px;
	margin-bottom: 50px;
	margin-left: 55px;
	
}

/* 공지사항 작성*/
.notice {
	position: relative;
	left: -310px;
}

/* 공지 체크박스 */
.noticebox {
	position: relative;
	left: -80px;
	font-size: 35px;
	
}

/* 공지사항 영역 */
.no-outbox {
	position: relative;
	top: -465px;
	left: 490px;
}

/* 공지사항 박스 */
.well {
	background-color: #EBF7FF;
	width: 591px;
	height: 55px;
}

/* 타이틀-도덕 */
h3 {
	position: absolute;
	top: 4%;
	left: 50%;
	width: 30%;
	height: 24px;
	-webkit-transform: translate(-50%, -50%);
	-ms-transform: translate(-50%, -50%);
	transform: translate(-50%, 100%);
	font-weight: bold;
	text-shadow: -1px -1px 1px #aaa, 3px 3px 4px black;
	font-size: 45px;
	color: #FFFFFF;
}

p {
	font-family: "맑은 고딕";
	font-size: 20px;
}


#bannerBox > ul{
	margin-top: 5px;
	margin-left: 20px;
}

#bannerBox2 > ul{
	margin-top: 5px;
}
</style>
<script type="text/javascript" src="resources/js/master.js"></script>
<script type="text/javascript" src="resources/js/main.js"></script>
<script type="text/javascript" src="resources/js/stream.js"></script>
<script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.24.0/moment.min.js"></script>

</head>
<body onload="mbTypeCheck('${mbType}'); NoticeOutput(); getTaskDate(); getQuizDate(); getETCDate(); getNotice(); getAjax('https://api.ipify.org','format=json', 'getPublicIp');">
	<input type='hidden' name='mbId' value='${mbId}' />
	<input type='hidden' name='csCode' value='${csCode}' />
	<input type='hidden' name='opCode' value='${opCode}' />
	<input type='hidden' name='opName' value='${opName}' />
	<input type='hidden' name='mbType' value='${mbType}' />
	
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
	<!-- Wrapper for slides -->
	<!-- 학과명 타이틀 -->
	<div id="classTitle">
		<h3>${opName}</h3>
	</div>
	<!-- 일정 박스 -->
	<div class="container text-center">
		<div class="row">
			<div id="scfontbox-sfb">
				<span id="scfont">마감 과제 일정</span>
			</div>
			<div id="scfontbox-sf">
				<span id="scfont">퀴즈 일정</span>
			</div>
			<div id="scfontbox-sb">
				<span id="scfont">기타 일정</span>
			</div>
			<div class="col-sm-4" id="TaskBoard"></div>
			<div class="col-sm-4" id="QuizBoard"></div>
			<div class="col-sm-4" id="ETCBoard"></div>
		</div>
		<!-- 공지사항 -->
		<div class="notice">
			<div>
				<div class="noticebox">
					<input type="radio" class="btn-check" name="options-outlined" id="success-outlined" autocomplete="off" checked>
					<label class="btn btn-outline-important" for="important-outlined">중요공지</label>
                  
					<input type="radio" class="btn-check" name="options-outlined" id="danger-outlined" autocomplete="off">
					<label class="btn btn-outline-general" for="general-outlined">일반공지</label>
				</div>
				<div>
				<input id="titlebox" class="textbox" name="ntTitle" placeholder="제목을 입력해주세요." />
				</div>
				<div>
				<input id="textbox" class="textbox" name="ntContents" placeholder="내용을 입력해주세요." />
				</div>
			</div>
			<div>
				<input class="button" type="button" onClick="sendNotice()" value="작성" />
			</div>
		</div>
		<br>
		<!-- 공지사항 출력 예시 박스 -->
		<div id="noticeBox" class="no-outbox">
			<div class="well">
			<i class="fas fa-ellipsis-v" style="margin-left: 540px;"></i>
			</div>
			<br> <br>
			<div class="well">
			<i class="fas fa-ellipsis-v" style="margin-left: 540px;"></i>
			</div>
			<br> <br>
			<div class="well">
			<i class="fas fa-ellipsis-v" style="margin-left: 540px;"></i>
			</div>
			<br> <br>
			<div class="well">
			<i class="fas fa-ellipsis-v" style="margin-left: 540px;"></i>
			</div>
		</div>
	</div>
	
	<!-- 공지사항 보기 -->
<div class="modal fade" id="notice" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header" style="text-align:center;">
          <button type="button" class="close" data-dismiss="modal" onClick="deleteValue()" >&times;</button>
          <h4 class="modal-title">공지사항</h4>
        </div>
        <div class="modal-body1" style="text-align:center;">
          <p>제목</p>
          <h6 id="ntTitle"></h6>
          <p>내용</p>
          <div id="ntContents"></div>
          <div id="ntBox"></div>
        </div>
        <br>
        <div class="modal-footer">
         <button type="button" class="btn btn-default" onClick="updateNotice()">수정</button>
          <button type="button" class="btn btn-default" data-dismiss="modal" onClick="deleteNotice()">삭제</button>
          <button type="button" class="btn btn-default" data-dismiss="modal" >닫기</button>
        </div>
        
         
      
      </div>
      
    </div>
  </div>
	
</body>
</html>