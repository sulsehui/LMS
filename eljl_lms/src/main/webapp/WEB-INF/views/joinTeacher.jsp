<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	//아이디 체크
	function idCheck() {
		let id = document.getElementsByName("mbId")[0];
		let idCheck = document.getElementById("idCheck");
		if (id.value.length > 4 && id.value.length < 12) {
			idCheck.innerText = "사용가능합니다";
		} else {
			idCheck.innerText = "사용이 불가능합니다";
		}
	}

	//중복확인

	//비밀번호 체크 (소문자, 특수문자, 숫자 포함)
	function pwCheck() {
		const pwdComp1 = /[a-z]/g;
		const pwdComp2 = /[0-9]/g;
		const pwdComp3 = /[!@#$%^&*]/g;

		let pw = document.getElementsByName("mbPw")[0];
		let pwCheck = document.getElementById("pwCheck");
		let count = 0;
		if (pw.value.length > 6 && pw.value.length < 12) {
			count += pwdComp1.test(pw.value) ? 1 : 0;
			count += pwdComp2.test(pw.value) ? 1 : 0;
			count += pwdComp3.test(pw.value) ? 1 : 0;
			count == 3 ? pwCheck.innerText = "사용 가능한 비밀번호입니다."
					: pwCheck.innerText = "사용 불가능한 비밀번호입니다.";
		} else {
			pwCheck.innerText = "사용 불가능한 비밀번호입니다.";
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
	
	function makeInput(type,name,value){
		let input = document.createElement("input");
		
		input.setAttribute("type",type);
		input.setAttribute("name",name);
		input.setAttribute("value",value);
		
		return input;
	}
</script>
</head>
<body>

	<input type="text" name="mbId" onblur="idCheck()" /> id
	<br>
	<div id="idCheck"></div>
	<br>
	<input type="button" /> 중복
	<br>
	<input type="password" name="mbPw" onblur="pwCheck()" /> pw
	<br>
	<div id="pwCheck"></div>
	<br>
	<input type="password" name="rembPw" onblur="repwCheck()"/> rpw
	<div id="repwCheck"></div>
	<br>
	<input type="text" name="mbName" /> name
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