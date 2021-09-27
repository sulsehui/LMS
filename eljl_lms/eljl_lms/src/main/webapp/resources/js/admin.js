
window.addEventListener('DOMContentLoaded', event => {

    // Activate Bootstrap scrollspy on the main nav element
    const sideNav = document.body.querySelector('#sideNav');
    if (sideNav) {
        new bootstrap.ScrollSpy(document.body, {
            target: '#sideNav',
            offset: 74,
        });
    };

    // Collapse responsive navbar when toggler is visible
    const navbarToggler = document.body.querySelector('.navbar-toggler');
    const responsiveNavItems = [].slice.call(
        document.querySelectorAll('#navbarResponsive .nav-link')
    );
    responsiveNavItems.map(function (responsiveNavItem) {
        responsiveNavItem.addEventListener('click', () => {
            if (window.getComputedStyle(navbarToggler).display !== 'none') {
                navbarToggler.click();
            }
        });
    });

});

//전체 멤버 리스트 가져오기
function allMemberList(){
	
	
	let sendJsonData = [];
	sendJsonData.push({mbId:""})
	let clientData = JSON.stringify(sendJsonData);
	
	postAjax('admin/allMemberList', clientData, 'allMemberListView', 'json');
}

//전체 멤버 리스트 출력
function allMemberListView(jsonData){
	let teAuthList = document.getElementById("teAuthList");
	let teList = document.getElementById("teList");
	let stuList = document.getElementById("stuList");
	
	teAuthList.innerHTML = "";
	teList.innerHTML = "";
	stuList.innerHTML = "";
	for(i=0; i<jsonData.length; i++){
		if(jsonData[i].mbType == "T" && jsonData[i].status =="W"){
			teAuthList.innerHTML += "<input type = 'hidden' name = 'mbId' value = '"+jsonData[i].mbId+"' ><div>"+jsonData[i].mbId+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+jsonData[i].mbName+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+jsonData[i].mbFlie+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span name = 'confirm'>대기 중</span> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type = 'button' name = 'stop' value='인증' onClick='teAuthCheck("+i+")'></div>";
		}else if(jsonData[i].mbType == "T"){
			if(jsonData[i].status == "G")
				{
				teList.innerHTML += "<input type = 'hidden' name = 'mbId' value = '"+jsonData[i].mbId+"' ><div>"+jsonData[i].mbId+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+jsonData[i].mbName+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span name='confirm'>활동 중</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type = 'button' name = 'stop' value='"+"정지"+"' onClick='tePermission(this.value, "+i+")' /></div>";
			}else{
				teList.innerHTML += "<input type = 'hidden' name = 'mbId' value = '"+jsonData[i].mbId+"' ><div>"+jsonData[i].mbId+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+jsonData[i].mbName+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span name='confirm'>정지</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type = 'button' name = 'stop' value='"+"활동"+"' onClick='tePermission(this.value, "+i+")' /></div>";
			}
			
		}else{
			if(jsonData[i].status == "G"){
				stuList.innerHTML += "<input type = 'hidden' name = 'mbId' value = '"+jsonData[i].mbId+"' ><div>"+jsonData[i].mbId+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+jsonData[i].mbName+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span name='confirm'>활동 중</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type = 'button' name = 'stop' value='"+"정지"+"' onClick='stuPermission(this.value, "+i+")' /></div>";
			}else{
				stuList.innerHTML += "<input type = 'hidden' name = 'mbId' value = '"+jsonData[i].mbId+"' ><div>"+jsonData[i].mbId+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+jsonData[i].mbName+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span name='confirm'>정지</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type = 'button' name = 'stop' value='"+"활동"+"' onClick='stuPermission(this.value, "+i+")' /></div>";
			}
			
		}
	}
}

//교사 정지 버튼 선택
function tePermission(data, index){
	let confirm = document.getElementsByName("confirm")[index];
	let stop = document.getElementsByName("stop")[index];
	let mbId = document.getElementsByName("mbId")[index];
	
	let status = "";
	if(data == "정지"){
		confirm.innerText = "정지";
		stop.value = "활동";
		status = "N";
		
	}else{
		confirm.innerText = "활동 중";
		stop.value = "정지";
		status = "G";
	}
	
	let sendJsonData = [];
	sendJsonData.push({mbId:mbId.value, status:status, mbType:"T"});
	let clientData = JSON.stringify(sendJsonData);
	alert(clientData);
	postAjax('admin/permission', clientData, 'sendMsg', 'json');
	
}

//학생 정지 버튼 선택
function stuPermission(data, index){
	let confirm = document.getElementsByName("confirm")[index];
	let stop = document.getElementsByName("stop")[index];
	let mbId = document.getElementsByName("mbId")[index];
	
	let status = "";
	if(data == "정지"){
		confirm.innerText = "정지";
		stop.value = "활동";
		status = "N";
	}else{
		confirm.innerText = "활동 중";
		stop.value = "정지";
	    status = "G";
	}
	
	let sendJsonData = [];
	sendJsonData.push({mbId:mbId.value, status:status, mbType:"S"});
	let clientData = JSON.stringify(sendJsonData);
	
	postAjax('admin/permission', clientData, 'sendMsg', 'json');
	
	
}

//교사 인증 버튼
function teAuthCheck(index){
	let teList = document.getElementById("teList");
	let mbId = document.getElementsByName("mbId")[index];
	
	let sendJsonData = [];
	sendJsonData.push({mbId:mbId.value});
	let clientData = JSON.stringify(sendJsonData);
	
	postAjax('admin/teacherAuth', clientData, 'sendMsg', 'json');
	
}


function sendMsg(message){
	alert(JSON.parse(JSON.stringify(message)).message);
	allMemberList();
}


function adPwChange(){
	
	let f = document.createElement("form");
	f.action = "adPwChange";
	f.method = "post";
	
	document.body.appendChild(f);
	f.submit();
	
	
}



