<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript" src="resources/js/master.js"></script>
<script type="text/javascript">

// 로그인
function submit(){
	let mbId = document.getElementsByName("mbId")[0];
	let mbPw = document.getElementsByName("mbPw")[0];
	
	let mbType = document.getElementsByName("mbType");
	
	let f = makeForm('login','post');
	document.body.appendChild(f);
	
	if(mbType[0].checked == true){
		f.appendChild(mbType[0]);
	}else{
		f.appendChild(mbType[1]);
	}
	f.appendChild(mbId);
	f.appendChild(mbPw);
	f.submit();
}

// 로그인 실패시 알림
function message(message){
	if(message != ""){
		alert(message);
	}
}

</script>
<body onload="message('${message}')">

	<input type="radio" value="T" name="mbType" checked="checked">선생님
	<input type="radio" value="S" name="mbType">학생 <br>
	아이디<input type="text" name="mbId" /> <br>
	비밀번호<input type="password" name="mbPw" />
	<input type="button" value="로그인" onClick="submit()"/>
	<br>
	<a href="/joinTeForm">선생님회원가입</a>
	<br>
	<a href="/joinStuForm">학생회원가입</a>
	<br>
	<a href="/pwChangeForm">비밀번호변경</a>
	
</body>
</html>