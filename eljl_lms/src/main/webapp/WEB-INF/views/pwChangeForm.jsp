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
	function submit() {
		let id = document.getElementsByName("mbId")[0];
		let mbType = document.getElementsByName("mbType");
		let result;
		if (mbType[0].checked == true) {
			result = mbType[0].value;
		} else {
			result = mbType[1].value;
		}

		postAjax("usAuthen", "mbId=" + id.value + "&" + "mbType=" + result,
				"selectMail", "form");
	}

	function selectMail(data) {
	let authBox = document.getElementById("AuthBox");
	
	authBox.innerHTML = "" + data + "<br>" + "<input type='button' value = '메일전송' onClick = 'sendMail()'>"+"<input type='hidden' name='mbMail'value ='"+data+"'/>";
	}
	
	function sendMail(){
		let data = document.getElementsByName("mbMail")[0];
		let f = document.createElement("form");
		f.action = "pwChangeMail";
		f.method = "post";
		
		document.body.appendChild(f);
		
		f.appendChild(data);
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

	<br>
	<a href="/loginForm">로그인페이지</a>
</body>
</html>