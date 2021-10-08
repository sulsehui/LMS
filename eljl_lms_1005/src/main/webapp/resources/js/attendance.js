
let count = 0;

//학생 리스트 가져오기
function getStuList(){
	let mbId = document.getElementsByName("mbId")[0];
	let csCode = document.getElementsByName("csCode")[0];
	let opCode = document.getElementsByName("opCode")[0];
	
	let hiddenBox= document.getElementById("hiddenBox");
	let editModal = document.getElementById("editModal");
	
	editModal.style.display="none";
	
	let sendJsonData = [];
	sendJsonData.push({teId:mbId.value, csCode:csCode.value, opCode:opCode.value});
	let clientData = JSON.stringify(sendJsonData);
	
	postAjax('teacher/getStuList',clientData, 'getStuListView','json');
}
	


//학생 리스트 가져와서 출력
function getStuListView(jsonData){
	let stuList = document.getElementById("stuList");
	
	stuList.innerHTML="";
	
	for(i=0; i<jsonData.length; i++){
		if(jsonData[i].stuName != null){
			stuList.innerHTML += "<br><div><span>"+jsonData[i].stuName+"</div>";
			stuList.innerHTML += "<div class= 'container'>";
			stuList.innerHTML += "<input type = 'button' class='btn btn-warning' name = 'late', value='"+"지각"+"' style='margin-right:20px;' onClick='check(this.value, "+i+")' />";
			stuList.innerHTML += "<input type = 'button' class='btn btn-primary' name = 'early', value='"+"조퇴"+"' style='margin-left:20px;margin-right:20px;' onClick='check(this.value, "+i+")' />";
			stuList.innerHTML += "<input type = 'button' class='btn btn-danger' name = 'absent', value='"+"결석"+"' style='margin-left:20px;margin-right:20px;' onClick='check(this.value, "+i+")' />";
			stuList.innerHTML += "상태 :<span name='attend' style='padding-left:10px; font-style: italic;' >출석</span></span></div><br>";
			stuList.innerHTML += "<input type = 'hidden' name = 'stuId', value='"+jsonData[i].stuId+"' />";
			
	
		}
		count++;
	}
}

//버튼 선택
function check(data, index){

	let attend = document.getElementsByName("attend")[index];
	
	if(attend.innerText == data){
		attend.innerText = "출석";
	}else{
		attend.innerText = data;
	}
	
}

//출석체크
function sendAttendCheck(){
	
	let mbId = document.getElementsByName("mbId")[0];
	let csCode = document.getElementsByName("csCode")[0];
	let opCode = document.getElementsByName("opCode")[0];
	
	let sendJsonData = [];
	
	for(i=0; i<count; i++){
		let stuId = document.getElementsByName("stuId")[i];
		let attend = document.getElementsByName("attend")[i];
		let status = "";
		if(attend.innerText == "출석"){
			status = "O";
		}else if(attend.innerText == "지각"){
			status = "L";
		}else if(attend.innerText == "조퇴"){
			status = "P";
		}else {
			status = "X";
		}
		sendJsonData.push({teId:mbId.value, csCode:csCode.value, opCode:opCode.value, stuId:stuId.value, status:status});
	}
	
	count = 0;
	
	let clientData = JSON.stringify(sendJsonData);
	postAjax('teacher/sendAttendCheck', clientData, 'sendMsg', 'json');
	
}

function sendMsg(message){
	alert(JSON.parse(JSON.stringify(message)).message);
}

//당일 출석 관리
function todayAttend(){
	let changePage = document.getElementById("changePage");
	let today = moment();
	
	changePage.innerHTML = "";

	changePage.innerHTML += "<div style = 'margin-left: 5px; font-size: 25px;'>"+today.format('YYYY년 MM월 DD일')+"</div>";
	changePage.innerHTML += "<br>";
	changePage.innerHTML += "<div class='panel panel-info'> <div class='panel-heading'>출석 리스트</div><div class='col-xs-12 panel-body' >";
	changePage.innerHTML += "<div id='stuList'></div></div></div><br><br>";
	changePage.innerHTML += "<div class='AttendCkeck'>";
	changePage.innerHTML += "<input type='button' class='btn btn-default btn-lg'  value='출석체크' onClick='sendAttendCheck()' />";
	changePage.innerHTML += "</div>";
	}

//전체 출석 관리 리스트 불러오기
function allAttend(){
	let changePage = document.getElementById("changePage");
	let mbId = document.getElementsByName("mbId")[0];
	let csCode = document.getElementsByName("csCode")[0];
	let opCode = document.getElementsByName("opCode")[0];
	let editModal = document.getElementById("editModal");
	
	editModal.style.display="block";
	
	
	changePage.innerHTML = "<div class='container'>";
	changePage.innerHTML += "<table class='table table-bordered'><thead><tr id = 'day'><th>학생이름</th></tr></thead>";
	changePage.innerHTML += "<tbody id = 'attStu'></tbody></table></div>";
	
	let sendJsonData = [];
	sendJsonData.push({teId:mbId.value, csCode:csCode.value, opCode:opCode.value});
	let clientData = JSON.stringify(sendJsonData);
	
	postAjax('teacher/allAttendList', clientData, 'attendList', 'json');
}



//전체 출석 리스트 출력
function attendList(jsonData){
	
	let day = document.getElementById("day");
	let attStu = document.getElementById("attStu");
	let editModal = document.getElementById("editModal");
	
	
	for(i=0; i < 7; i++){ //오늘 ~ 현재 날짜 -7 
		day.innerHTML += "<th>"+moment().subtract(i,'days').format("MM-DD")+"</th>";	
	}	 	
	
	for(j=0; j<jsonData.length; j++){ // 이름 중복제거
		let result ="";
			if(j > 0){
				if(jsonData[j-1].stuId != jsonData[j].stuId){
					result += "<tr id='"+jsonData[j].stuId+"' name='stuName'>"+"<td>"+jsonData[j].stuName+"</td>";
					
					for(i=0; i < 7; i++){ // status
						result += "<td></td>";
					}
				}	
			}else{
				result += "<tr id='"+jsonData[j].stuId+"' name='stuName'>"+"<td>"+jsonData[j].stuName+"</td>";
				
					for(i=0; i < 7; i++){ // status
						result += "<td></td>";
					}			
			}
			result += "</tr>";
		attStu.innerHTML += result;
		
		attendStuName(jsonData[j].stuId)
	}
	
	
	}
	
	// 학생이름 뽑아서 그리기
	function attendStuName(stuId){
		let mbId = document.getElementsByName("mbId")[0];
		let csCode = document.getElementsByName("csCode")[0];
		let opCode = document.getElementsByName("opCode")[0];
		
		let sendJsonData = [];
		sendJsonData.push({teId:mbId.value, csCode:csCode.value, opCode:opCode.value,stuId:stuId});
		let clientData = JSON.stringify(sendJsonData);
		postAjax('teacher/selectStuStatus', clientData, 'makeStatus', 'json');
	}
	
	function makeStatus(jsonData){
		let make = document.getElementById(jsonData[0].stuId);
		
		let result = "<td>"+jsonData[0].stuName+"</td>";
		
		for(i=0; i < 7; i++){ // status
			let check = false;
			for(j=0; j < jsonData.length; j++){
				if(moment().subtract(i,'days').format("MM-DD") == moment(jsonData[j].attDate).format("MM-DD")){
					result += "<td>"+jsonData[j].status+"</td>";
					check = true;
					break;
				}
			}
			if(!check){
				result +="<td></td>";
			}
						
		}
		console.log(result);
		make.innerHTML = result;
	}
	
//수정 모달창 내용 가져오기(학생리스트, 날짜, 버튼)
function editAttendList(){
	let mbId = document.getElementsByName("mbId")[0];
	let csCode = document.getElementsByName("csCode")[0];
	let opCode = document.getElementsByName("opCode")[0];
	
	let sendJsonData = [];
	sendJsonData.push({teId:mbId.value, csCode:csCode.value, opCode:opCode.value});
	let clientData = JSON.stringify(sendJsonData);
	
	postAjax('teacher/getStuList', clientData, 'getEditStuListView','json');
	
	
}

//수정 모달창 -> 수정할 학생 리스트 출력
function getEditStuListView(json){
	let editStuName = document.getElementsByName("editStuName")[0];
	
	editStuName.innerHTML = "<option value = 'defaultStuName'>학생이름</option>";
	for(i=0; i<json.length; i++){
		editStuName.innerHTML += "<option value = '"+json[i].stuId+"'>"+json[i].stuName+"</option>";
	}
	
}

//수정 모달창 -> 수정할 날짜 가져오기
function editDate(){
	let mbId = document.getElementsByName("mbId")[0];
	let csCode = document.getElementsByName("csCode")[0];
	let opCode = document.getElementsByName("opCode")[0];
	let editStuName = document.getElementsByName("editStuName")[0];
	
	
	let sendJsonData = [];
	sendJsonData.push({teId:mbId.value, csCode:csCode.value, opCode:opCode.value, stuId:editStuName.value});
	let clientData = JSON.stringify(sendJsonData);
	
	postAjax('teacher/getEditDateList', clientData, 'getEditDateListView','json');
	
}

//수정 모달창 -> 수정할 날짜 출력
function getEditDateListView(json){
	let editDateList = document.getElementsByName("editDateList")[0];
	
	
	editDateList.innerHTML="<option value = 'defaultDate'>날짜선택</option>";
	for(i=0; i<json.length; i++){
			editDateList.innerHTML += "<option value = '"+moment(json[i].attDate).format('YYYY-MM-DD')+"' >"+moment(json[i].attDate).format('YYYY년 MM월 DD일')+"</option>";
	}

}


//수정 모달창 ->  수정할 출석상태 가져오기 (변경 전 ajax)
function editStatus(){
	let mbId = document.getElementsByName("mbId")[0];
	let csCode = document.getElementsByName("csCode")[0];
	let opCode = document.getElementsByName("opCode")[0];
	let editStuName = document.getElementsByName("editStuName")[0];
	let editDateList = document.getElementsByName("editDateList")[0];
	
	let sendJsonData = [];
	sendJsonData.push({teId:mbId.value, csCode:csCode.value, opCode:opCode.value, stuId:editStuName.value, attDate: editDateList.value});
	let clientData = JSON.stringify(sendJsonData);
	
	postAjax('teacher/getBeforeStatus', clientData, 'getBeforeStatusView','json');
	
}

//수정할 출석상태 출력
function getBeforeStatusView(json){
	let beforeName = document.getElementById("beforeName");
	

	let resultBeforeName = "";
	if(json[0].status == "L"){
		resultBeforeName = '지각'
	}else if(json[0].status == "P"){
		resultBeforeName = '조퇴'
	}else if(json[0].status == "X"){
		resultBeforeName = '결석'
	}else{
		resultBeforeName = '출석'
	}
	beforeName.innerText= resultBeforeName;
	
}

//수정 모달창 -> 버튼 변경(변경 후 ajax)
function editCheck(data){
	let afterName = document.getElementById("afterName");
	
	afterName.innerText = "";
	if(afterName.innerText == data){
		
	}else{
		afterName.innerText = data;
	}
}

//출석 수정
function editAttend(){
	let mbId = document.getElementsByName("mbId")[0];
	let csCode = document.getElementsByName("csCode")[0];
	let opCode = document.getElementsByName("opCode")[0];
	let editStuName = document.getElementsByName("editStuName")[0];
	let editDateList = document.getElementsByName("editDateList")[0];
	let beforeName = document.getElementById("beforeName");
	let afterName = document.getElementById("afterName");
	
	if(afterName.innerText != ""){
	
	let resultAfterName = "";
	if(afterName.innerText == "지각"){
		resultAfterName = 'L'
	}else if(afterName.innerText == "조퇴"){
		resultAfterName = 'P'
	}else if(afterName.innerText == "결석"){
		resultAfterName = 'X'
	}else{
		resultAfterName = 'O'
	}
	let sendJsonData = [];
	sendJsonData.push({teId:mbId.value, csCode:csCode.value, opCode:opCode.value, stuId:editStuName.value, attDate:editDateList.value, status:resultAfterName});
	let clientData = JSON.stringify(sendJsonData);
	
	postAjax('teacher/getEditAttend', clientData, 'sendMsg','json');
	
	editStuName.value = "학생이름";
	editDateList.value = "날짜입력";
	beforeName.innerText = "";
	afterName.innerText = "";
	
	}else {
		alert("수정할 버튼을 눌러주세요.");
	}
	
}






