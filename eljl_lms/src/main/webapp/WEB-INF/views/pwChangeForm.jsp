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
const pwdComp1 = /[a-z]/g;
const pwdComp2 = /[0-9]/g;
const pwdComp3 = /[!@#$%^&*]/g;
const pwdComp4 = /[A-Z]/g;
	
	// 아이디 인증
	function submit() {
		let id = document.getElementsByName("mbId")[0];
		let hiddenBox = document.getElementById("AuthHiddenBox");
		let mbType = document.getElementsByName("mbType");
		let result;
		if (mbType[0].checked == true) {
			result = mbType[0].value;
		} else {
			result = mbType[1].value;
		}
		
		hiddenBox.innerHTML = "<input type='hidden' name='mbId' value='"+id.value+"' />";
		
		postAjax("usAuthen", "mbId=" + id.value + "&" + "mbType=" + result,
				"selectMail", "form");
	}

	// 메일 불러오기
	function selectMail(data) {
	let authBox = document.getElementById("AuthBox");
	authBox.innerHTML = "" + data + "<br>" + "<input type='button' value = '메일전송' onClick = 'sendMail()'>"+"<input type='hidden' name='mbMail'value ='"+data+"'/>";
	}
	
	// 메일 전송하기
	function sendMail(){
		let mbMail = document.getElementsByName("mbMail")[0];
		let mbId = document.getElementsByName("mbId")[0];
		postAjax("pwChangeMail","mbMail="+mbMail.value +"&"+"mbId="+mbId.value,"mailAuth","form");
	}
	
	// 인증키 입력 양식
	function mailAuth(message){
		let authBox = document.getElementById("AuthBox");
		alert(JSON.parse(JSON.stringify(message)).message);
		authBox.innerHTML = "<input type='text' name='auth'><input type='button' value='인증하기' onClick='sendAuth()'/>";
	}
	
	// 인증키 서버에 보내기
	function sendAuth(){
		let auth = document.getElementsByName("auth")[0];
		let mbId = document.getElementsByName("mbId")[0];
		postAjax("sendAuth","mbId="+mbId.value +"&"+ "mbAuth="+auth.value ,"pwChangeForm","form");
	}
	
	// 인증키 인증 후 클라이언트
	function pwChangeForm(message){
		let authBox = document.getElementById("AuthBox");
		if((JSON.parse(JSON.stringify(message)).message) == "true"){
			authBox.innerHTML = "<p>새 비밀번호 입력</p><div><input type='password' name='mbPw' onblur='pwCheck()'/> </div>";
			authBox.innerHTML += "<div id='pwCheck'></div>";
			authBox.innerHTML += "<p>새 비밀번호 재입력</p><div><input type='password' name='rembPw' onblur='repwCheck()'/>";
			authBox.innerHTML += "<div id='repwCheck'></div>";
			authBox.innerHTML += "<input type='button' value='변경하기' onClick='sendNewPw()' />";
		}else{
			alert("인증키를 잘못 입력하였습니다");
			authBox.innerHTML = "<input type='text' name='auth'><input type='button' value='인증하기' onClick='sendAuth()'/>";
		}
	}
	
	
	//비밀번호 체크 (소문자, 특수문자, 숫자 포함)
	function pwCheck() {
		let pw = document.getElementsByName("mbPw")[0];
		let pwCheck = document.getElementById("pwCheck");
		let count = 0;
		if (pw.value.length > 5 && pw.value.length < 13) {
			count += pwdComp1.test(pw.value) ? 1 : 0;
			count += pwdComp2.test(pw.value) ? 1 : 0;
			count += pwdComp3.test(pw.value) ? 1 : 0;
			count == 3 ? pwCheck.innerText = "사용 가능한 비밀번호입니다."
					: pwCheck.innerText = "6-12자 소문자,특수문자,숫자를 사용하세요.";
		} else {
			pwCheck.innerText = "6-12자 소문자,특수문자,숫자를 사용하세요.";
		}
	}

	//비밀번호 재확인
	function repwCheck() {
		let pw = document.getElementsByName("mbPw")[0];
		let rePw = document.getElementsByName("rembPw")[0];
		let repwCheck = document.getElementById("repwCheck");
		if(pw.value != rePw.value){
			repwCheck.innerText = "비밀번호가 일치하지 않습니다.";
		}else{
			repwCheck.innerText = "비밀번호가 일치합니다.";
		}
	}
	
	// 새 비밀번호 전송
	function sendNewPw(){
		let pw = document.getElementsByName("mbPw")[0];
		let mbId = document.getElementsByName("mbId")[0];
		
		let f = document.createElement("form");
		f.action = "sendNewPW";
		f.method = "post";
		
		document.body.appendChild(f);
		f.appendChild(pw);
		f.appendChild(mbId);
		f.submit();
	}
	
</script>

<body>
	비밀번호 변경을 위한 인증 페이지입니다
	<br>

	<div id="AuthBox">
		<input type="radio" value="T" name="mbType" checked="checked">선생님
		<input type="radio" value="S" name="mbType">학생 <br> 아이디<input
			type="text" name="mbId" /> <br>
		<!-- <input type="text" name="mail" /> -->
		<input type="button" value="인증하기" onClick="submit()" />
	</div>
	
	<div id="AuthHiddenBox">
	</div>
	
	<br>
	<a href="/loginForm">로그인페이지</a>
</body>
</html>