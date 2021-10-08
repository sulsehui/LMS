let noticeCount = 0;

// 과제 기간 불러오기
function getTaskDate(){
	let mbId = document.getElementsByName("mbId")[0];
	let csCode = document.getElementsByName("csCode")[0];
	let opCode = document.getElementsByName("opCode")[0];
	
	let sendJsonData = [];
	sendJsonData.push({mbId:mbId.value,csCode:csCode.value,opCode:opCode.value});
	let clientData = JSON.stringify(sendJsonData);
	 
	postAjax('common/getTaskDate',clientData,'getTaskDateList','json');
	
}

// 과제 기간 불러와서 출력
function getTaskDateList(jsonData){

	let TaskBoard = document.getElementById("TaskBoard");
	
	for(i=0; i < jsonData.length; i++){
		let TaskDay = moment(jsonData[i].endDate , 'YYYYMMDD');
		let today = moment(new Date() , 'YYYYMMDD');
		TaskBoard.innerHTML += "<br>"+ jsonData[i].title +" D-"+ TaskDay.diff(today,'days');
	}
	
}

// 퀴즈 기간 불러오기

function getQuizDate(){
	let mbId = document.getElementsByName("mbId")[0];
	let csCode = document.getElementsByName("csCode")[0];
	let opCode = document.getElementsByName("opCode")[0];
	
	let sendJsonData = [];
	sendJsonData.push({mbId:mbId.value,csCode:csCode.value,opCode:opCode.value});
	let clientData = JSON.stringify(sendJsonData);
	 
	postAjax('common/getQuizDate',clientData,'getQuizDateList','json');
	
}

// 퀴즈 기간 불러와서 출력

function getQuizDateList(jsonData) {
	let QuizBoard = document.getElementById("QuizBoard");
	
	for(i=0; i < jsonData.length; i++){
		let QuizDay = moment(jsonData[i].endDate , 'YYYYMMDD');
		let today = moment(new Date() , 'YYYYMMDD');
		QuizBoard.innerHTML += "<br>"+ jsonData[i].title +" D-"+ QuizDay.diff(today,'days');
	}
}


// 기타 기간 불러오기

function getETCDate(){
	let mbId = document.getElementsByName("mbId")[0];
	let csCode = document.getElementsByName("csCode")[0];
	let opCode = document.getElementsByName("opCode")[0];
	
	let sendJsonData = [];
	sendJsonData.push({mbId:mbId.value,csCode:csCode.value,opCode:opCode.value});
	let clientData = JSON.stringify(sendJsonData);
	 
	postAjax('common/getETCDate',clientData,'getETCDateList','json');
	
}

// 기타 기간 불러와서 출력

function getETCDateList(jsonData) {
	let ETCBoard = document.getElementById("ETCBoard");
	
	
	for(i=0; i < jsonData.length; i++){
		let ETCDay = moment(jsonData[i].endDate , 'YYYYMMDD');
		let today = moment(new Date() , 'YYYYMMDD');
		ETCBoard.innerHTML += "<br>"+ jsonData[i].title +" D-"+ ETCDay.diff(today,'days');
	}
}


// 공지사항 작성

function sendNotice(){
	let mbId = document.getElementsByName("mbId")[0];
	let csCode = document.getElementsByName("csCode")[0];
	let opCode = document.getElementsByName("opCode")[0];
	
	// 타입 , 타이틀 , 내용
	let ntType = document.getElementsByName("options-outlined");
	let ntTitle = document.getElementsByName("ntTitle")[0];
	let ntContents = document.getElementsByName("ntContents")[0];
	 
	if(ntType[0].checked == true){
		ntType = "U";
	}else{
		ntType = "C";
	}
	
	let sendJsonData = [];
	sendJsonData.push({mbId:mbId.value,csCode:csCode.value,opCode:opCode.value,ntType:ntType,ntTitle:ntTitle.value,ntContents:ntContents.value});
	let clientData = JSON.stringify(sendJsonData);
	
	ntTitle.value = "";
	ntContents.value = "";
	
	postAjax('common/sendNotice',clientData,'sendMsg','json');
}


// 결과
function sendMsg(message){
	alert(JSON.parse(JSON.stringify(message)).message);
	getNotice()
}


// 공지사항 불러오기
function getNotice(){
	let mbId = document.getElementsByName("mbId")[0];
	let csCode = document.getElementsByName("csCode")[0];
	let opCode = document.getElementsByName("opCode")[0];
	
	
	let sendJsonData = [];
	sendJsonData.push({mbId:mbId.value,csCode:csCode.value,opCode:opCode.value});
	let clientData = JSON.stringify(sendJsonData);
	
	postAjax('common/getNotice',clientData,'getNoticeList','json');
}

// 공지사항 리스트 불러와서 출력
function getNoticeList(jsonData){
	let noticeBox = document.getElementById("noticeBox");
	let mbType = document.getElementsByName("mbType")[0];
	
	noticeBox.innerHTML = "";
	for(i=0; i < jsonData.length; i++){
		if(mbType.value == "T"){
			noticeBox.innerHTML += "<div class='well'>"+"<span style='float:left'>"+moment(jsonData[i].createTime).format("YYYY-MM-DD")+"</span>"+ jsonData[i].ntTitle +"<i class='fas fa-ellipsis-v' style='float:right;' data-toggle='modal' data-target='#notice' onClick='selectNotice("+i+")'></i></div>";
			noticeBox.innerHTML += "<input type='hidden' name='ntCode' value='"+jsonData[i].ntCode+"'>";
		}else{
			noticeBox.innerHTML += "<div class='well'>"+"<span style='float:left'>"+moment(jsonData[i].createTime).format("YYYY-MM-DD")+"</span>" + jsonData[i].ntTitle+"<i class='fas fa-ellipsis-v' style='float:right;' data-toggle='modal' data-target='#notice' onClick='selectNotice("+i+")'></i></div>";
			noticeBox.innerHTML += "<input type='hidden' name='ntCode' value='"+jsonData[i].ntCode+"'>";
		}
		
	}
}

// 선택한 공지사항 불러오기
function selectNotice(index){
	noticeCount = index;
	
	let mbId = document.getElementsByName("mbId")[0];
	let csCode = document.getElementsByName("csCode")[0];
	let opCode = document.getElementsByName("opCode")[0];
	let ntCode = document.getElementsByName("ntCode")[index];
	
	
	let sendJsonData = [];
	sendJsonData.push({mbId:mbId.value,csCode:csCode.value,opCode:opCode.value,ntCode:ntCode.value});
	let clientData = JSON.stringify(sendJsonData);
	
	postAjax('common/selectNotice',clientData,'selectViewNotice','json');
}

// 선택한 공지사항 모달에 출력
function selectViewNotice(jsonData){
	let ntTitle = document.getElementById("ntTitle");
	let ntContents = document.getElementById("ntContents");
	
	ntTitle.innerText = jsonData[0].ntTitle;
	ntContents.innerText = jsonData[0].ntContents;
	
}


// 공지사항 수정
function updateNotice(){
	let ntTitle = document.getElementById("ntTitle");
	let ntContents = document.getElementById("ntContents");
	let ntBox = document.getElementById("ntBox");
	
	ntTitle.innerHTML = "<input type='text' name='noticeTitle' value='"+ntTitle.innerText+"' />";
	ntContents.innerHTML = "<input type='textarea' name='noticeContents' style='resize: none; height:300px; width:300px;' value='"+ntContents.innerText+"' />";
	ntBox.innerHTML = "<input type='button' value='수정하기' onClick='sendUpdateNotice()' />";
}

// 공지사항 수정 보내기

function sendUpdateNotice(){
	let mbId = document.getElementsByName("mbId")[0];
	let csCode = document.getElementsByName("csCode")[0];
	let opCode = document.getElementsByName("opCode")[0];
	let ntCode = document.getElementsByName("ntCode")[noticeCount];
	let noticeTitle = document.getElementsByName("noticeTitle")[0];
	let noticeContents = document.getElementsByName("noticeContents")[0];
	
	let sendJsonData = [];
	sendJsonData.push({mbId:mbId.value,csCode:csCode.value,opCode:opCode.value,ntCode:ntCode.value,ntTitle:noticeTitle.value,ntContents:noticeContents.value});
	let clientData = JSON.stringify(sendJsonData);
	
	postAjax('common/updateNotice',clientData,'sendMsg','json');
}


// 공지사항 삭제
function deleteNotice(){
	let mbId = document.getElementsByName("mbId")[0];
	let csCode = document.getElementsByName("csCode")[0];
	let opCode = document.getElementsByName("opCode")[0];
	let ntCode = document.getElementsByName("ntCode")[noticeCount];
	
	
	let sendJsonData = [];
	sendJsonData.push({mbId:mbId.value,csCode:csCode.value,opCode:opCode.value,ntCode:ntCode.value});
	let clientData = JSON.stringify(sendJsonData);
	
	postAjax('common/deleteNotice',clientData,'sendMsg','json');
}