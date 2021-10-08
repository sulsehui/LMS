<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Gowun+Dodum&display=swap" rel="stylesheet"> 
<link rel="stylesheet" href="resources/css/master.css">
<link rel="stylesheet" href="resources/css/login.css">
<script type="text/javascript" src="resources/js/master.js"></script>
<script type="text/javascript">

// 로그인
function submit(){
	let mbId = document.getElementsByName("mbId")[0];
	let mbPw = document.getElementsByName("mbPw")[0];
	
	let mbType = document.getElementsByName("mbType");
	
	let method = makeInput("hidden","method",1);
	let pubIp = makeInput("hidden","publicIp",publicIp);
	let privateIp = makeInput("hidden","privateIp",location.host);
	
	let f = makeForm('login','post');
	document.body.appendChild(f);
	
	if(mbType[0].checked == true){
		f.appendChild(mbType[0]);
	}else{
		f.appendChild(mbType[1]);
	}
	f.appendChild(mbId);
	f.appendChild(mbPw);
	
	f.appendChild(method);
	f.appendChild(pubIp);
	f.appendChild(privateIp);
	
	f.submit();
}

// 로그인 실패시 알림
function message(message){
	if(message != ""){
		alert(message);
	}
}

function checkOnlyOne(element) {
	  
	  const checkboxes 
	      = document.getElementsByName("mbType");
	  
	  checkboxes.forEach((cb) => {
	    cb.checked = false;
	  })
	  
	  element.checked = true;
	}

</script>
<body onload="getAjax('https://api.ipify.org','format=json', 'getPublicIp'),message('${message}')">

<div id="accesszone">
  
  <div class="title">ELJL</div><br>
  
<div>
	<div id="ckbox">
    <div id="ck-button">
       <label>
     <input style="zoom:1.7;" type="checkbox" name="mbType" value="T" checked="checked" onclick='checkOnlyOne(this)' /><span>교사</span>
       </label>
    </div>
    <div id="ck-button">
       <label>
     <input style="zoom:1.7;" type="checkbox" name="mbType" value="S"  onclick='checkOnlyOne(this)' /><span>학생</span>
       </label>
    </div>
    </div>
  <div>
   <input id="textbox" name="mbId" type="text" style="margin-bottom:15px;" placeholder="아이디"  />
  </div>
  <div>
   <input id="textbox" name="mbPw" type="password" style="margin-bottom:15px;" placeholder="비밀번호">
  </div>
  <div>
   <a href="/joinStuForm" id="dubBtn" type="button" class="button" > 회원가입</a> 
  </div>
  <div>
   <a href="/pwChangeForm" id="dubdubBtn" class="button" > 비밀번호 변경</a>
  </div>
    <div id="boxbox">
    <input id="box" type="button" value="로그인" onClick="submit()">
  </div>
 </div>
 </div>
</body>
</html>


