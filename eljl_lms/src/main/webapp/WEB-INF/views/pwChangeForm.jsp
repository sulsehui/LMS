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
		
		hiddenBox.innerHTML = "<input='hidden' name='mbId' value='"+id.value+"' />";
		
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
		postAjax("pwChangeMail","mbMail="+mbMail.value,"mailAuth","form");
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
		alert(JSON.parse(JSON.stringify(message)).message);
		if((JSON.parse(JSON.stringify(message)).message) == "true"){
			authBox.innerHTML = "<div>성공입니다</div>";
		}else{
			authBox.innerHTML = "<div>실패입니다 ㅠㅠ</div>";
		}
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