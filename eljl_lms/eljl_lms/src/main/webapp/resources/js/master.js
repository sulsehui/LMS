/**
 * 
 */

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
	let uCode = makeInput("hidden","mbId",id);
	let method = makeInput("hidden","method",-1);
	let pubIp = makeInput("hidden","publicIp",publicIp);
	let privateIp = makeInput("hidden","privateIp",location.host);
	let mbType = document.getElementsByName("mbType")[0];
	
	let form = makeForm("logOut","post");
	
	form.appendChild(uCode);
	form.appendChild(method);
	form.appendChild(pubIp);
	form.appendChild(privateIp);
	form.appendChild(mbType);
	
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
