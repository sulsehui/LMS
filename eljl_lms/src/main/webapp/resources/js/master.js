/**
 * 
 */

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