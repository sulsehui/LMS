<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript">

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


function makeForm(action,method,name = null){
	let form = document.createElement("form");
	
	if(name != null){form.setAttribute("name",name);}
	form.setAttribute("action",action);
	form.setAttribute("method",method);
	form.setAttribute("enctype","multipart/form-data");
	
	return form;
}
</script>
<body>

	<input type="radio" value="T" name="mbType" checked="checked">선생님
	<input type="radio" value="S" name="mbType">학생 <br>
	아이디<input type="text" name="mbId" /> <br>
	비밀번호<input type="password" name="mbPw" />
	<input type="button" value="로그인" onClick="submit()"/>

</body>
</html>