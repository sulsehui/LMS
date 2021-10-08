
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
	
	console.log(jsonData);
	
	for(i=0; i<jsonData.length; i++){
		
		
		if(jsonData[i].mbType == "T" && jsonData[i].status =="W"){
			teAuthList.innerHTML += "<input type = 'hidden' name = 'mbId' value = '"+jsonData[i].mbId+"' ><input type = 'hidden' name = 'stickerPath' value = '"+jsonData[i].stickerPath+"'>";
			
			teAuthList.innerHTML += "<div style =' margin:auto;width:80%; margin-top:20px; margit-left:'10%';>"+
									"<div class = 'mbId'>"+jsonData[i].mbId+"</div>"+
									"<div class = 'mbName'>"+jsonData[i].mbName+"</div>"+
									"<div name = 'stickerPath' style = 'float: left; width: 30%; line-height:2;' input type = 'button'  onClick='fileDownload("+i+")'>교사 인증 파일 다운로드</div>"+
									"<div name = 'confirm' style = 'float: left; width: 10%; line-height:2;'>대기 중</div>"+
									"<div class=\"authBtn\"><input type = 'button' class = 'auth'  name = 'stop' value='인증' onClick='teAuthCheck("+i+")'></div>"+
									"</div><br><br>";
		
		
		
		}else if(jsonData[i].mbType == "T"){
			if(jsonData[i].status == "G")
				{
				teList.innerHTML += "<input type = 'hidden' name = 'mbId' value = '"+jsonData[i].mbId+"' >";
				teList.innerHTML += "<div style =' margin:auto; width:80%;'>";
				teList.innerHTML += "<div class = 'mbId'>"+jsonData[i].mbId+"</div>";
				teList.innerHTML += "<div class = 'mbName'>"+jsonData[i].mbName+"</div>";
				teList.innerHTML += "<span name='confirm' style = 'float: left; width: 20%; line-height:2;'>활동 중</span>";
				teList.innerHTML += "<div ><input type = 'button' class = 'auth' name = 'stop' value='"+"정지"+"' onClick='tePermission(this.value, "+i+")' /></div></div><br>";
			}else{
				teList.innerHTML += "<input type = 'hidden' name = 'mbId' value = '"+jsonData[i].mbId+"' >";
				teList.innerHTML += "<div style =' margin:auto; width:80%;'>";
				teList.innerHTML += "<div class = 'mbId'>"+jsonData[i].mbId+"</div>";
				teList.innerHTML += "<div class = 'mbName'>"+jsonData[i].mbName+"</div>";
				teList.innerHTML += "<div name='confirm' style = 'float: left; width: 20%; color: red; font-weight:bold;'>정지</div>";
				teList.innerHTML += "<div><input type = 'button' class= 'auth' name = 'stop' value='"+"활동"+"' onClick='tePermission(this.value, "+i+")' /></div></div><br>";
			}
			
		}else{
         if(jsonData[i].status == "O"){
            
            stuList.innerHTML += "<input type = 'hidden' name = 'mbId' value = '"+jsonData[i].mbId+"' >";
            stuList.innerHTML += "<div style = 'margin:auto; width:80%;'>";
            stuList.innerHTML += "<div class = 'mbId2'>"+jsonData[i].mbId+"</div>";
            stuList.innerHTML += "<div class = 'mbName2'>"+jsonData[i].mbName+"</div>";
            stuList.innerHTML +="<div><span name='confirm' style = 'float: left; width: 20%; line-height:2;'>활동 중</span></div>";
            stuList.innerHTML +="<div><input type = 'button' class= 'auth2' name = 'stop' value='"+"정지"+"' onClick='stuPermission(this.value, "+i+")' /></div></div><br>";
         }else{
            stuList.innerHTML += "<input type = 'hidden' name = 'mbId' value = '"+jsonData[i].mbId+"' >";
            stuList.innerHTML += "<div style = 'margin:auto; width:80%;'>";
            stuList.innerHTML += "<div class = 'mbId2'>"+jsonData[i].mbId+"</div>";
            stuList.innerHTML +="<div class = 'mbName2'>"+jsonData[i].mbName+"</div>";
            stuList.innerHTML += "<div name='confirm' style = 'float: left; width: 20%; color: red; font-weight:bold;'>정지<div>";
            stuList.innerHTML += "<div><input type = 'button' class= 'auth2' name = 'stop' value='"+"활동"+"' onClick='stuPermission(this.value, "+i+")' /></div><div><br>";
         }
         
      }
	}
}

//파일 다운로드
function fileDownload(index){
	let stickerPath = document.getElementsByName("stickerPath")[index];
	alert(stickerPath);
	
	let form = makeForm("downLoadFile", "post", "multipart/form-data");
		
	form.appendChild(stickerPath);
	document.body.appendChild(form);
	
	form.submit();
	
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
		status = "G";
	}else{
		confirm.innerText = "활동 중";
		stop.value = "정지";
	    status = "O";
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

//비밀번호 변경 폼
function adPwChange(){
	
	let f = document.createElement("form");
	f.action = "adPwChange";
	f.method = "post";
	
	document.body.appendChild(f);
	f.submit();
	
}





