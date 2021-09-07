<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="resources/js/master.js"></script>
<script type="text/javascript">
	//아이디 체크
	const pwdComp1 = /[a-z]/g;
	const pwdComp2 = /[0-9]/g;
	const pwdComp3 = /[!@#$%^&*]/g;
	const pwdComp4 = /[A-Z]/g;
	
	function idCheck() {
		let id = document.getElementsByName("mbId")[0];
		let idCheck = document.getElementById("idCheck");
		if (id.value.length > 3 && id.value.length < 13) {
			if(pwdComp3.test(id) == true) {
				idCheck.innerText = "4-12자의 영문 소문자,숫자만 사용가능합니다";
			}
			idCheck.innerText = "중복확인";
		} else {
			idCheck.innerText = "4-12자의 영문 소문자,숫자만 사용가능합니다";
		}
	}

	//중복확인
	function dupCheck(){
		let id = document.getElementsByName("mbId")[0];
		postAjax("dupCheck","mbId="+id.value,"dupCheckMessage","form");
	}
	//중복확인 메세지
	function dupCheckMessage(jsonData){
		let idCheck = document.getElementById("idCheck");
		if(jsonData){
			idCheck.innerText = "사용가능한 아이디입니다";
		}else{
			idCheck.innerText = "사용 불가능한 아이디입니다";
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
	
	// 회원가입 서버 전송
	function submit () {
		let id = document.getElementsByName("mbId")[0];		
		let pw = document.getElementsByName("mbPw")[0];
		let name = document.getElementsByName("mbName")[0];
		let phone = document.getElementsByName("mbPhone")[0];
		let mail1 = document.getElementsByName("mail1")[0];
		let mail2 = document.getElementsByName("mail2")[0];
		let mbMail = makeInput('hidden','mbMail',mail1.value+mail2.value);
		//let file = document.getElementsByName("mbFile")[0];
		
		if(id.value == ""){
			id.focus();
		}else if(pw.value == ""){
			pw.focus();
		}else if(name.value == ""){
			name.focus();	
		}else if(phone.value == ""){
			phone.focus();	
		}else if(mail1.value == ""){
			mail1.focus();		
		}else{
			let f = document.createElement("form");
			f.action="joinTeacher";
			f.method="post";
			
			document.body.appendChild(f);
			f.appendChild(id);
			f.appendChild(pw);
			f.appendChild(name);
			f.appendChild(phone);
			f.appendChild(mbMail);
			//f.appendChild(file);
			
			f.submit();
		}
		
		// 파일하자
		
			
	}
	
</script>
</head>
<body>

	<input type="text" name="mbId" onblur="idCheck()" /> id
	<br>
	<div id="idCheck"></div>
	<br>
	<input type="button" value="중복" onClick="dupCheck()"/>
	<br>
	<input type="password" name="mbPw" onblur="pwCheck()" /> pw
	<br>
	<div id="pwCheck"></div>
	<br>
	<input type="password" name="rembPw" onblur="repwCheck()"/> rpw
	<div id="repwCheck"></div>
	<br>
	<input type="text" name="mbName" maxlength=5/> name
	<br>
	<input type="text" maxlength=11 name="mbPhone" /> phone
	<br>
	<input type="text" name="mail1" /> @<select name="mail2"> 
	<option value="@naver.com">naver.com</option>
	<option value="@google.com">google.com</option>
	</select>
	<br>
	<input type="file" />
	<br>
	<input type="button" onClick="submit()" value="전송" />

</body>
</html>