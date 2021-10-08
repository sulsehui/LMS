<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />

<meta name="description" content="" />
<meta name="author" content="" />
<title>관리자 페이지</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Gowun+Dodum&display=swap" rel="stylesheet"> 
<link rel="stylesheet" href="resources/css/master.css">
 
<link href="/resources/css/admin.css" rel="stylesheet" />

<script type="text/javascript" src="resources/js/master.js"></script>
<script type="text/javascript" src="resources/js/admin.js"></script>

<script type="text/javascript">
function message(message){
	if(message != ""){
		alert(message);
	}
}
</script>

</head>
<body id="page-top" onload ="message('${message}'); allMemberList(); getAjax('https://api.ipify.org','format=json', 'getPublicIp');">
	<!-- Navigation-->
	<nav class="navbar navbar-expand-lg navbar-dark bg-primary fixed-top"
		id="sideNav">
		
		<a class="navbar-brand js-scroll-trigger" href="#page-top"> 
		<!-- 로고 넣기!!! 
      <span class="d-none d-lg-block"><img class="img-fluid img-profile rounded-circle mx-auto mb-2" src="assets/img/profile.jpg" alt="..." /></span>-->
		</a>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#navbarResponsive" aria-controls="navbarResponsive"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarResponsive">
			<ul class="navbar-nav">

				<li class="nav-item"><a class="nav-link js-scroll-trigger"
					href="#authWait">인증대기</a></li>
				<br>
				<br>

				<li class="nav-item"><a class="nav-link js-scroll-trigger"
					href="#teacher">교사</a></li>
				<br>
				<br>

				<li class="nav-item"><a class="nav-link js-scroll-trigger"
					href="#sutdent">학생</a></li>


			</ul>
		</div>
	</nav>
	<!-- Page Content-->
	<div class="container-fluid p-0">
		<div class = "logOutBox">
		      <div id="logBoxBtn">
			   	<input type="button" class="logBoxBtn" value = "로그아웃" onClick="logOut('${mbId}')" >
			  <input type="button" class="logBoxBtn" value = "비밀번호 변경" onCLick="adPwChange()">
			  </div>
		</div>

		<!-- 인증대기 -->
		<section class="resume-section" id="authWait">
		 <div class="resume-section-content">
			
			<div><h4 class="mb-5">인증대기</h4></div>
			<div class = "bar"></div><br><br>
			
		<div id = "teAuthList" style = "margin-left:10%;" ></div>
		</div>
		</section>


		<!-- 교사-->
      <section class="resume-section" id="teacher">
      <div class="resume-section-content">
         <h4 class="mb-5">교사</h4>
         <div class = "bar"></div><br><br>
         
         <div class = "allTeBox">
      <div id = "teList"  style = "margin-left:10%;"></div>
         </div>
         </div>
      </section>


		  <!-- 학생-->
      <section class="resume-section" id="sutdent">
      <div class="resume-section-content">
         <h4 class="mb-5">학생</h4>
         <div class = "bar"></div><br><br>
         
         <div class = "allStuBox">
      <div id = "stuList" style = "margin-left:10%;"></div>
      </div>
      </div>
      </section>
	</div>
	<!-- Bootstrap core JS-->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js"></script>
	<!-- Core theme JS-->
	<script src="/resources/js/admin.js"></script>
</body>
</html>
