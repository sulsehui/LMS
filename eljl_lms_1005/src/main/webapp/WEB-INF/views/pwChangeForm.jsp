<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<link rel="stylesheet" href="resources/css/pwChange.css">
<script type="text/javascript" src="resources/js/master.js"></script>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Gowun+Dodum&display=swap" rel="stylesheet"> 
<link rel="stylesheet" href="resources/css/master.css">
<script type="text/javascript">
const pwdComp1 = /[a-z]/g;
const pwdComp2 = /[0-9]/g;
const pwdComp3 = /[!@#$%^&*]/g;
const pwdComp4 = /[A-Z]/g;
	
	let beforeMbPw;
	// 아이디 인증
	function submit() {
		let mbId = document.getElementsByName("mbId")[0];
		let hiddenBox = document.getElementById("AuthHiddenBox");
		let mbType = document.getElementsByName("checkMbType");
		let result;
		
		
		if (mbType[0].checked == true) {
			result = mbType[0].value;
		} else {
			result = mbType[1].value;
		}
		
		hiddenBox.innerHTML = "<input type='hidden' name='mbId' value='"+mbId.value+"' />";
		hiddenBox.innerHTML += "<input type='hidden' name='mbType' value='"+result+"' />";
		
		postAjax("usAuthen", "mbId="+mbId.value + "&" + "mbType=" + result,
				"selectMail", "form");
	}

	// 메일 불러오기
	function selectMail(data) {
	let authBox = document.getElementById("changeBox");
	authBox.innerHTML = "" + data + "<br>" + "<input type='button' value = '메일전송' onClick = 'sendMail()'>"+"<input type='hidden' name='mbMail' value ='"+data+"' />";
	}
	
	// 메일 전송하기
	function sendMail(){
		let mbMail = document.getElementsByName("mbMail")[0];
		let mbId = document.getElementsByName("mbId")[0];
		let mbType = document.getElementsByName("mbType")[1];
		
		postAjax("pwChangeMail","mbMail="+mbMail.value +"&"+"mbId="+mbId.value +"&"+ "mbType=" +mbType.value, "mailAuth","form");
	}
	
	// 인증키 입력 양식
	function mailAuth(message){
		let changeBox = document.getElementById("changeBox");
		alert(JSON.parse(JSON.stringify(message)).message);
		changeBox.innerHTML = "<input type='text' name='auth'><input type='button' value='인증하기' onClick='sendAuth()'/>";
	}
	
	// 인증키 서버에 보내기
	function sendAuth(){
		let auth = document.getElementsByName("auth")[0];
		let mbId = document.getElementsByName("mbId")[0];
		let mbType = document.getElementsByName("mbType")[1];
		
		postAjax("sendAuth","mbId="+mbId.value +"&"+ "mbAuth="+auth.value + "&" + "mbType=" +mbType.value ,"pwChangeForm","form");
	}
	
	// 인증키 인증 후 클라이언트
	function pwChangeForm(message){
		let changeBox = document.getElementById("changeBox");
		if((JSON.parse(JSON.stringify(message)).message) == "true"){
			changeBox.innerHTML = "<p>새 비밀번호 입력</p><div><input type='password' name='mbPw' onblur='pwCheck()'/> </div>";
			changeBox.innerHTML += "<div id='pwCheck'></div>";
			changeBox.innerHTML += "<p>새 비밀번호 재입력</p><div><input type='password' name='rembPw' onblur='repwCheck()'/>";
			changeBox.innerHTML += "<div id='repwCheck'></div>";
			changeBox.innerHTML += "<br><input type='button' value='변경하기' onClick='sendNewPw()' />";
		}else{
			alert("인증키를 잘못 입력하였습니다");
			changeBox.innerHTML = "<input type='text' name='auth'><input type='button' value='인증하기' onClick='sendAuth()'/>";
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
		
		let mbType = document.getElementsByName("mbType")[0];
		
		//alert(mbType.value);
		
		if(mbType.value == "M"){

			let mbPw = document.getElementsByName("mbPw")[0];
			
			let f = document.createElement("form");
			f.action = "sendNewPW";
			f.method = "post";
			
			document.body.appendChild(f);
			f.appendChild(mbPw);
			f.appendChild(mbType);
			f.submit();
			
		}else{
			
			let mbPw = document.getElementsByName("mbPw")[0];
			let mbId = document.getElementsByName("mbId")[0];
			let mbType = document.getElementsByName("mbType")[1];
			
			let f = document.createElement("form");
			f.action = "sendNewPW";
			f.method = "post";
			
			document.body.appendChild(f);
			f.appendChild(mbPw);
			f.appendChild(mbId);
			f.appendChild(mbType);
			
			f.submit();
			
		}
	}
	
	//(관리자) 비밀번호 변경 폼
	   function adPwChange(mbId){
	      
	      if(mbId != ""){
	         
	      let authBox = document.getElementById("changeBox");
	      authBox.innerHTML =  "<div><a href='/adminForm' style='color: #375b7f'>뒤로가기</a></div>";
	      authBox.innerHTML += "<div><input id = 'textbox' type = 'password' name = 'nowmbPw' onblur = 'sendNowPw()'  placeholder='현재 비밀번호 입력'/></div>";
	      authBox.innerHTML += "<div id='nowPwCheck'></div>";
	      authBox.innerHTML += "<div><input id = 'textbox' type = 'password' name = 'mbPw' onblur = 'pwChangeCheck()' placeholder='변경할 비밀번호 입력' /></div>";
	      authBox.innerHTML += "<div id='pwChangeCheck'></div>";
	      authBox.innerHTML += "<input id = 'box' type='button' value='변경하기' onClick='sendNewPw()' />";
	      
	      }
	      
	   }
	
	
	

	
	
	//(관리자)현재 비밀번호 가져오기
	function sendNowPw(){
		let nowmbPw =  document.getElementsByName("nowmbPw")[0];
		let sendJsonData = [];
		sendJsonData.push({mbPw:nowmbPw.value});
		let clientData = JSON.stringify(sendJsonData);
		
		postAjax('admin/getNowPw', clientData, 'nowPwCheck', 'json');
		
	
	}
	
	//(관리자)현재 비밀번호 확인
	function nowPwCheck(data){
		beforeMbPW = data.mbPw;
		console.log(beforeMbPW);
		let nowPw = document.getElementsByName("nowmbPw")[0];
		let nowPwCheck = document.getElementById("nowPwCheck");
		
		if(beforeMbPW == nowPw.value){
			nowPwCheck.innerText = "현재 비밀번호가 일치합니다.";
		}else{
			nowPwCheck.innerText = "비밀번호가 일치하지 않습니다. 다시 작성해주세요.";
		}
	}
	
	
	//(관리자)변경할 비밀번호 확인 (소문자, 특수문자, 숫자 포함)
	function pwChangeCheck() {
		
		let mbPw = document.getElementsByName("mbPw")[0];
		let pwChangeCheck = document.getElementById("pwChangeCheck");
		
		let count = 0;
		if (mbPw.value.length > 5 && mbPw.value.length < 13) {
			count += pwdComp1.test(mbPw.value) ? 1 : 0;
			count += pwdComp2.test(mbPw.value) ? 1 : 0;
			count += pwdComp3.test(mbPw.value) ? 1 : 0;
			count == 3 ? pwChangeCheck.innerText = "사용 가능한 비밀번호입니다."
					: pwChangeCheck.innerText = "6-12자 소문자,특수문자,숫자를 사용하세요.";
		} else if (beforeMbPW == mbPw.value){
			pwChangeCheck.innerText = "현재 비밀번호와 일치합니다. 다시 작성해주세요.";
		} else{
			pwChangeCheck.innerText = "6-12자 소문자,특수문자,숫자를 사용하세요.";
		}
	}
	
	
	
</script>

<body onLoad = "adPwChange('${mbId}')">
	<div id="accesszone">
		
		<input type='hidden' name='mbType' value='${mbType}' />
		
		<div class="title">비밀번호 변경</div>
		<br>
		<div style="display:flex;">
			<div id="changeBox" style="margin:0 auto;">
				
				<br>
				<div>
				
				<div id="ckbox">
					<div id="ck-button">
					 <label> 
						<input type="radio" value="T" name="checkMbType"
							checked="checked" style="zoom: 1.5;" />선생님
					 </label>
					</div>
					<div id="ck-button">
					 <label> 
						<input type="radio" value="S" name="checkMbType"
							style="zoom: 1.5;" />학생
					 </label>
					</div>
				</div>
			
				<input id="textbox" type="text" name="mbId" placeholder="아이디" /> <br>
				<!-- <input type="text" name="mail" /> -->
				<input id="box" type="button" value="인증하기" onClick="submit()" />
			</div>
			</div>
		</div>



		<div id="AuthHiddenBox"></div>

		<br> <a href="/loginForm" id="lbox"><img
			src="resources/images/left_arrow1.png"
			style="height: 35px; width: 35px;" /><span style="color: #375b7f">로그인</span></a>
	</div>
</body>
</html>