

let publicIp;

function makeInput(type,name,value){
		let input = document.createElement("input");
		
		input.setAttribute("type",type);
		input.setAttribute("name",name);
		input.setAttribute("value",value);
		
		return input;
}

function makeForm(action,method,name = null){
	let form = document.createElement("form");
	
	if(name != null){form.setAttribute("name",name);}
	form.setAttribute("action",action);
	form.setAttribute("method",method);
	form.setAttribute("enctype","multipart/form-data");
	
	return form;
}

function postAjax(jobCode,clientData,fn,type){
	//step1
	let ajax = new XMLHttpRequest();
	
	
	//step2
	/*
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4 && ajax.status == 200){
			if(type == "form"){
				alert(ajax.responseText);
			window[fn](ajax.responseText);	
			}else{
			window[fn](JSON.parse(ajax.responseText));
			}
		}
	};
	*/
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4 && ajax.status == 200){
			
			window[fn](JSON.parse(ajax.responseText));
		
		}
	};	
	
	//step3
	ajax.open("POST",jobCode);
	
	//step4
	if(type == "form"){
		ajax.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	}else{
		ajax.setRequestHeader("Content-type", "application/json");
	}
	
	ajax.send(clientData);
}


function logOut(id){
	let method = makeInput("hidden","method",-1);
	let pubIp = makeInput("hidden","publicIp",publicIp);
	let privateIp = makeInput("hidden","privateIp",location.host);
	
	let form = makeForm("logOut","post");
	
	form.appendChild(method);
	form.appendChild(pubIp);
	form.appendChild(privateIp);
	
	document.body.appendChild(form);
	
	form.submit();
}

function getAjax(jobCode,clientData,fn){
	
	console.log(jobCode);
	console.log(clientData);
	console.log(fn);
	
	//step1 객체 생성
	let ajax = new XMLHttpRequest();
	
	//step2 상태 설정
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4 && ajax.status == 200){
			window[fn](JSON.parse(ajax.responseText));
		}
	};
	
	//step3
	if(clientData != ""){ jobCode += "?" + clientData;}
	ajax.open("GET",jobCode);
	
	//step4
	ajax.send();
}


function getPublicIp(data){
	publicIp = data.ip;
}



function moveStream(){
	
	let mbId = document.getElementsByName("mbId")[0];
	let csCode = document.getElementsByName("csCode")[0];
	let opCode = document.getElementsByName("opCode")[0];
	let opName = document.getElementsByName("opName")[0];
	
	let f = document.createElement("form");

	f.action ="moveStream";
	f.method ="post";

	document.body.appendChild(f);

	f.appendChild(mbId);
	f.appendChild(csCode);
	f.appendChild(opCode);
	f.appendChild(opName);
	
	f.submit();
}


function moveClass(){
	
	let mbId = document.getElementsByName("mbId")[0];
	let csCode = document.getElementsByName("csCode")[0];
	let opCode = document.getElementsByName("opCode")[0];
	let opName = document.getElementsByName("opName")[0];
	
	let f = document.createElement("form");

	f.action ="moveClass";
	f.method ="post";

	document.body.appendChild(f);

	f.appendChild(mbId);
	f.appendChild(csCode);
	f.appendChild(opCode);
	f.appendChild(opName);
	
	f.submit();
}


function moveAttend(){
	
	let mbId = document.getElementsByName("mbId")[0];
	let csCode = document.getElementsByName("csCode")[0];
	let opCode = document.getElementsByName("opCode")[0];
	let opName = document.getElementsByName("opName")[0];
	
	let f = document.createElement("form");

	f.action ="moveAttend";
	f.method ="post";

	document.body.appendChild(f);

	f.appendChild(mbId);
	f.appendChild(csCode);
	f.appendChild(opCode);
	f.appendChild(opName);

	f.submit();
}


function moveStuManage(){
	
	let mbId = document.getElementsByName("mbId")[0];
	let csCode = document.getElementsByName("csCode")[0];
	let opCode = document.getElementsByName("opCode")[0];
	let opName = document.getElementsByName("opName")[0];
	
	let f = document.createElement("form");

	f.action ="moveStuManage";
	f.method ="post";

	document.body.appendChild(f);

	f.appendChild(mbId);
	f.appendChild(csCode);
	f.appendChild(opCode);
	f.appendChild(opName);
	
	f.submit();
}


function moveMyPage(){
	
	let mbId = document.getElementsByName("mbId")[0];
	let csCode = document.getElementsByName("csCode")[0];
	let opCode = document.getElementsByName("opCode")[0];
	let opName = document.getElementsByName("opName")[0];
	
	let f = document.createElement("form");

	f.action ="moveMyPage";
	f.method ="post";

	document.body.appendChild(f);

	f.appendChild(mbId);
	f.appendChild(csCode);
	f.appendChild(opCode);
	f.appendChild(opName);
	
	f.submit();
}


function moveSetting(){
	
	let mbId = document.getElementsByName("mbId")[0];
	let csCode = document.getElementsByName("csCode")[0];
	let opCode = document.getElementsByName("opCode")[0];
	let opName = document.getElementsByName("opName")[0];
	
	let f = document.createElement("form");

	f.action ="moveSetting";
	f.method ="post";

	document.body.appendChild(f);

	f.appendChild(mbId);
	f.appendChild(csCode);
	f.appendChild(opCode);
	f.appendChild(opName);
	
	f.submit();
}


// 타입 체크
function mbTypeCheck(mbType){
	let teItem = document.getElementsByClassName("teItem");
	let stuItem = document.getElementsByClassName("stuItem");
	let bannerBox = document.getElementById("bannerBox");
	
	if(mbType == "T"){
		stuItem[0].style.display = "none";
	}else{
		teItem[0].style.display = "none";
		teItem[1].style.display = "none";
		teItem[2].style.display = "none";
		bannerBox.style.paddingRight = "65px";
	}
}