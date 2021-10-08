let checkCount = 0;
let classcount = 0;
let checkCount2 = 0;
function count2(index){
   classcount = index;
}
function deleteGlypicon () {
   let mbType = document.getElementsByName("mbType")[0];
   let glypicon = document.getElementsByClassName("glyphicon glyphicon-plus");
   
   console.log(mbType.value)
   if(mbType.value=="S") {
      glypicon[0].style.display = "none";
   }
}

//수업페이지 가져오기
function getClassForm () {
   let mbId = document.getElementsByName("mbId")[0];
   let csCode = document.getElementsByName("csCode")[0];
   let opCode = document.getElementsByName("opCode")[0];

   
   let f = makeForm('memberClass', 'post');
   document.body.appendChild(f);
   
   f.appendChild(mbId);
   f.appendChild(csCode);
   f.appendChild(opCode);
   f.appendChild(mbType);
   
   f.submit();
   
}
//개설 항목 가져오기
function getCategoryList () {
   let mbId = document.getElementsByName("mbId")[0];
   let csCode = document.getElementsByName("csCode")[0];
   let opCode = document.getElementsByName("opCode")[0];

   
   let sendJsonData = [];

      sendJsonData.push({mbId:mbId.value, csCode:csCode.value, opCode:opCode.value});
      let clientData = JSON.stringify(sendJsonData);
   
      postAjax('common/getCategoryList',clientData, 'getCategoryListView', 'json');
}
//개설 항목 출력

function getCategoryListView(jsonData) {
   let score = document.getElementById("score");

   score.innerHTML="";
   checkCount = i;
   for(i=0; i < jsonData.length; i++) {
      if(jsonData[i].ssCode.substring(0,1) !== 'A') {
         if (jsonData[i].ssCode.substring(0,1)=="E") {
         score.innerHTML += `<div data-toggle="modal" data-target="#createScoreE" onClick="count2(`+i+`)">` + jsonData[i].ssName + `</div>`;
         score.innerHTML += "<input type='hidden' name='newssCode', value='" +jsonData[i].ssCode + "' />";   
         }else if (jsonData[i].ssCode.substring(0,1)=="Q") {
         score.innerHTML += `<div data-toggle="modal" data-target="#createScoreQ" onClick="count2(`+i+`)">` + jsonData[i].ssName + `</div>`;
         score.innerHTML += `<input type='hidden' name='newssCode', value=` +jsonData[i].ssCode +  ` />`;   
         }else if (jsonData[i].ssCode.substring(0,1)=="T") {
         score.innerHTML += `<div data-toggle="modal" data-target="#createScoreT"  onClick="count2(`+i+`)">` + jsonData[i].ssName + `</div>`;
         score.innerHTML += "<input type='hidden' name='newssCode', value='" +jsonData[i].ssCode + "' />";      
         }
      }
   }
}

function CreateEtc () {
      let mbId = document.getElementsByName("mbId")[0];
      let csCode = document.getElementsByName("csCode")[0];
      let opCode = document.getElementsByName("opCode")[0];
      let newssCode = document.getElementsByName("newssCode")[classcount];
      let title = document.getElementsByName("etcTitle")[0];      
      let contents = document.getElementsByName("etcContents")[0];
      let mbFile = document.getElementsByName("mbFile")[0];
      let startDate = document.getElementsByName("etcSDate")[0];
      let endDate = document.getElementsByName("etcEDate")[0];   
       console.log(newssCode.value)
       console.log(classcount)
	
	/*
      let sendJsonData = [];
      if(title.value == ""){
         alert('제목을 입력해주세요');
      }else if(contents.value == ""){
         alert('내용을 입력해주세요');   
      }else if (startDate ==""){
         alert('날짜를 입력해주세요');
      }else if (endDate ==""){
         alert('날짜를 입력해주세요');
      }else {
         sendJsonData.push({mbId:mbId.value, csCode:csCode.value, ssCode:newssCode.value, opCode:opCode.value, title:title.value, contents:contents.value, mbFile:file, startDate:startDate.value, endDate:endDate.value})
         let clientData =JSON.stringify(sendJsonData)
         postAjax('common/createEtc', clientData, 'sendMsgClass', 'json');
 
      }
	*/

var form = $('#etcForm')[0];

    // FormData 객체 생성
    var formData = new FormData(form);

    // 코드로 동적으로 데이터 추가 가능.
    
	
    formData.append("mbId", mbId.value);
    formData.append("csCode", csCode.value);
    formData.append("ssCode", newssCode.value);
    formData.append("opCode", opCode.value);
    formData.append("title", title.value);
    formData.append("contents", contents.value);
    formData.append("mbFile", mbFile);
    formData.append("startDate", startDate.value);
    formData.append("endDate", endDate.value);
	
	
    $.ajax({
	type:"post",
	enctype:'multipart/form-data',
	contentType: 'application/json',
    url:'common/createEtc',
    data:formData,
    dataType:'json',
    processData:false,
    contentType:false,
    cache:false,
    success:function(data){
    	console.log("success : ", data);
		sendMsgClass(data)
    },
    error:function(e){
        console.log("error : ", e);
    }
	});
	
}
      
      
function CreateTask () {
   let mbId = document.getElementsByName("mbId")[0];
   let csCode = document.getElementsByName("csCode")[0];
   let opCode = document.getElementsByName("opCode")[0];
   let newssCode = document.getElementsByName("newssCode")[classcount];
   let title = document.getElementsByName("taskTitle")[0];      
   let contents = document.getElementsByName("taskContents")[0];
   let file = document.getElementsByName("taskFile")[0];
   let startDate = document.getElementsByName("taskSDate")[0];
   let endDate = document.getElementsByName("taskEDate")[0];
   console.log(newssCode.value)
   console.log(classcount)

/*
      let sendJsonData = [];
      if(title.value == ""){
         alert('제목을 입력해주세요');
      }else if(contents.value == ""){
         alert('내용을 입력해주세요');   
      }else if (startDate ==""){
         alert('날짜를 입력해주세요');
      }else if (endDate ==""){
         alert('날짜를 입력해주세요');
      }else {
         sendJsonData.push({mbId:mbId.value, csCode:csCode.value, ssCode:newssCode.value, opCode:opCode.value, title:title.value, contents:contents.value, mbFile:file, startDate:startDate.value, endDate:endDate.value});
         let clientData =JSON.stringify(sendJsonData);
         postAjax('common/createTask', clientData, 'sendMsgClass', 'json');
      }
*/
	
	var form = $('#taskForm')[0];

    // FormData 객체 생성
    var formData = new FormData(form);

    // 코드로 동적으로 데이터 추가 가능.
    
	
    formData.append("mbId", mbId.value);
    formData.append("csCode", csCode.value);
    formData.append("ssCode", newssCode.value);
    formData.append("opCode", opCode.value);
    formData.append("title", title.value);
    formData.append("contents", contents.value);
    formData.append("file", file);
    formData.append("startDate", startDate.value);
    formData.append("endDate", endDate.value);
	
	
    $.ajax({
	type:"post",
	enctype:'multipart/form-data',
	contentType: 'application/json',
    url:'common/createTask',
    data:formData,
    dataType:'json',
    processData:false,
    contentType:false,
    cache:false,
    success:function(data){
    	console.log("success : ", data);
		sendMsgClass(data)
    },
    error:function(e){
        console.log("error : ", e);
    }
	});
   

}
   
   
   function CreateQuiz () {
   let mbId = document.getElementsByName("mbId")[0];
   let csCode = document.getElementsByName("csCode")[0];
   let opCode = document.getElementsByName("opCode")[0];
   let newssCode = document.getElementsByName("newssCode")[classcount];
   let title = document.getElementsByName("quizTitle")[0]      
   let contents = document.getElementsByName("quizContents")[0];
   let answer = document.getElementsByName("quizAnswer")[0];
   let startDate = document.getElementsByName("quizSDate")[0];
   let endDate = document.getElementsByName("quizEDate")[0];
   console.log(newssCode.value)
    console.log(classcount)
      let sendJsonData = [];
      if(title.value == ""){
         alert('제목을 입력해주세요');
      }else if(contents.value == ""){
         alert('내용을 입력해주세요');   
      }else if(answer.value =="") {
         alert('정답을 입력해주세요')
      }else if (startDate ==""){
         alert('날짜를 입력해주세요');
      }else if (endDate ==""){
         alert('날짜를 입력해주세요');
      }else {
         sendJsonData.push({mbId:mbId.value, csCode:csCode.value, ssCode:newssCode.value, opCode:opCode.value, title:title.value, contents:contents.value, answer:answer.value, startDate:startDate.value, endDate:endDate.value})
         let clientData =JSON.stringify(sendJsonData)
         postAjax('common/createQuiz', clientData, 'sendMsgClass', 'json');
      }   
      
      
      
   }

//개설 성적 기준 리스트 가져오기
function allPostList() {
   let mbId = document.getElementsByName("mbId")[0];
   let csCode = document.getElementsByName("csCode")[0];
   let opCode = document.getElementsByName("opCode")[0];
   let mbType = document.getElementsByName("mbType")[0];
   let sendJsonData = [];
   sendJsonData.push({mbId:mbId.value, csCode:csCode.value, opCode:opCode.value, mbType:mbType.value});
   console.log(sendJsonData);
   let clientData = JSON.stringify(sendJsonData);
    
     postAjax('common/allPostList',clientData,'allPostListView','json');
   
}
//개설 성적 기준 리스트 출력
function allPostListView(jsonData) {
   let mbType = document.getElementsByName("mbType")[0];
   console.log(mbType.value);
   console.log(jsonData);
   let allPostList = document.getElementById("allPostList");
   allPostList.innerHTML ="";
   for(i=0; i<jsonData.length; i++) {
      checkCount = i
      
      console.log(mbType.value);
      if(jsonData[i].ssCode.substring(0,1)=="Q") {
         if(mbType.value =="T") {
            allPostList.innerHTML += "<div id='cbox'>" + "<div onClick='viewQuizT("+i+") 'data-toggle='modal' data-target='#viewQuizT' style='display:inline-block'>"  + jsonData[i].title + "</div>" + "<span name='postBox' onClick='makePostBox("+i+")' data-toggle='modal' data-target='#viewPostBoxT' style='display:inline-block; float:right;'>" + "퀴즈수정/삭제" + "</span>" + "</div>"
            }else {allPostList.innerHTML += "<div id='cbox'>" + "<div onClick='viewQuizS("+i+"); deleteQuizInfo()' data-toggle='modal' data-target='#viewQuizS' style='display:inline-block'>"  + jsonData[i].title + "</div>"  + "<span name='submitBox' onClick='changeCheck("+ i + "); deleteQuizInfo()' data-toggle='modal' data-target='#resultCheckQuiz' style='display:inline-block; float:right;'>" + "  :  " + "</span>" + "</div>"
         }
         
      }else if(jsonData[i].ssCode.substring(0,1)=="T"){
         if(mbType.value =="T") {
            allPostList.innerHTML += "<div id='cbox'>" + "<div onClick='viewTaskT("+i+")'data-toggle='modal' data-target='#viewTaskT' style='display:inline-block' >"+ jsonData[i].title + "</div>" + "<span name='postBox' onClick='makePostBox("+i+")' data-toggle='modal' data-target='#viewPostBoxT' style='display:inline-block; float:right;'>" + "과제수정/삭제" + "</span>" + "</div>"
            }else {allPostList.innerHTML += "<div id='cbox'>" + "<div onClick='viewTaskS("+i+"); deleteTaskInfo() 'data-toggle='modal' data-target='#viewTaskS' style='display:inline-block'>"+ jsonData[i].title + "</div>" + "<span name='submitBox' onClick='changeCheck("+ i + "); deleteTaskInfo()' data-toggle='modal' data-target='#cancelTaskReport' style='display:inline-block; float:right;'>" + "  :  " + "</span>" + "</div>"
         }
         
      }else if(jsonData[i].ssCode.substring(0,1)=="E"){
         if(mbType.value =="T") {
            allPostList.innerHTML += "<div id='cbox'>" + "<div onClick='viewETCT("+i+")'data-toggle='modal' data-target='#viewETCT' style='display:inline-block'>"+ jsonData[i].title + "</div>" + "<span name='postBox' onClick='makePostBox("+i+")' data-toggle='modal' data-target='#viewPostBoxT' style='display:inline-block; float:right;'>" + "기타수정/삭제" + "</span>" + "</div>"
            }else {allPostList.innerHTML += "<div id='cbox'>" + "<div onClick='viewETCS("+i+"); deleteETCInfo()'data-toggle='modal' data-target='#viewETCS' style='display:inline-block'>"+ jsonData[i].title + "</div>" + "<span name='submitBox' onClick='changeCheck("+ i + "); deleteETCInfo()' data-toggle='modal' data-target='#cancelETCReport' style='display:inline-block; float:right;'>" + "  :  " + "</span>" + "</div>"
         }
      }
      
      allPostList.innerHTML += "<input type = 'hidden' name = 'numCode' value = '" +jsonData[i].numCode+ "'/>"
      allPostList.innerHTML += "<input type = 'hidden' name = 'ssCode' value = '" + jsonData[i].ssCode + "'/>"
      allPostList.innerHTML += "<input type = 'hidden' name = 'title' value = '" + jsonData[i].title + "'/>"
      allPostList.innerHTML += "<input type = 'hidden' name = 'answer' value = '" + jsonData[i].answer + "'/>"
      allPostList.innerHTML += "<input type = 'hidden' name = 'contents' value = '"+ jsonData[i].contents+"'/>"
      allPostList.innerHTML += "<input type = 'hidden' name = 'startDate' value = '"+ jsonData[i].startDate+"'/>"
      allPostList.innerHTML += "<input type = 'hidden' name = 'endDate' value = '"+ jsonData[i].endDate+"'/>"
   }
}
//선생 퀴즈 보기
function viewQuizT(i) {
     let title = document.getElementsByName("title")[i];
     let contents = document.getElementsByName("contents")[i];   
     let answer = document.getElementsByName("answer")[i];   
     let startDate = document.getElementsByName("startDate")[i];   
     let endDate = document.getElementsByName("endDate")[i];   
     let viewQuizTitleT = document.getElementById("viewQuizTitleT");
     let viewQuizContentsT = document.getElementById("viewQuizContentsT");
     let viewQuizAnswerT = document.getElementById("viewQuizAnswerT");
     let viewQuizStartT = document.getElementById("viewQuizStartT");
     let viewQuizEndT = document.getElementById("viewQuizEndT");



checkCount = i;
viewQuizTitleT.innerText = title.value;
viewQuizContentsT.innerText = contents.value;
viewQuizAnswerT.innerText = answer.value;
viewQuizStartT.innerText = moment(startDate.value).format('YYYY-MM-DD');
viewQuizEndT.innerText = moment(endDate.value).format('YYYY-MM-DD');
     
}

//학생 퀴즈 보기
function viewQuizS(i) {
     let title = document.getElementsByName("title")[i];
     let contents = document.getElementsByName("contents")[i];   
     let answer = document.getElementsByName("answer")[i];   
     let startDate = document.getElementsByName("startDate")[i];   
     let endDate = document.getElementsByName("endDate")[i];   
     let viewQuizTitleS = document.getElementById("viewQuizTitleS");
     let viewQuizContentsS = document.getElementById("viewQuizContentsS");
     let viewQuizAnswerS = document.getElementById("viewQuizAnswerS");
     let viewQuizStartS = document.getElementById("viewQuizStartS");
     let viewQuizEndS = document.getElementById("viewQuizEndS");
     checkCount = i
viewQuizTitleS.innerText = title.value;
viewQuizContentsS.innerText = contents.value;
viewQuizAnswerS.innerText = answer.value;
viewQuizStartS.innerText = moment(startDate.value).format('YYYY-MM-DD');
viewQuizEndS.innerText = moment(endDate.value).format('YYYY-MM-DD');
     
}

//선생 과제 보기
function viewTaskT(i) {
     let title = document.getElementsByName("title")[i];
     let contents = document.getElementsByName("contents")[i];   
     let answer = document.getElementsByName("answer")[i];   
     let startDate = document.getElementsByName("startDate")[i];   
     let endDate = document.getElementsByName("endDate")[i];      
     let viewTaskTitleT = document.getElementById("viewTaskTitleT");
     let viewTaskContentsT = document.getElementById("viewTaskContentsT");
     let viewTaskAnswerT = document.getElementById("viewTaskAnswerT");
     let viewTaskStartT = document.getElementById("viewTaskStartT");
     let viewTaskEndT = document.getElementById("viewTaskEndT");
     checkCount = i;
viewTaskTitleT.innerText = title.value;
viewTaskContentsT.innerText = contents.value;
viewTaskAnswerT.innerText = answer.value;
viewTaskStartT.innerText = moment(startDate.value).format('YYYY-MM-DD');
viewTaskEndT.innerText = moment(endDate.value).format('YYYY-MM-DD');
   }

//학생 과제 보기
function viewTaskS(i) {
     let title = document.getElementsByName("title")[i];
     let contents = document.getElementsByName("contents")[i];   
     let answer = document.getElementsByName("answer")[i];   
     let startDate = document.getElementsByName("startDate")[i];   
     let endDate = document.getElementsByName("endDate")[i];   
     let viewTaskTitleS = document.getElementById("viewTaskTitleS");
     let viewTaskContentsS = document.getElementById("viewTaskContentsS");
     let viewTaskfileS = document.getElementById("viewTaskfileS");
     let viewTaskStartS = document.getElementById("viewTaskStartS");
     let viewTaskEndS = document.getElementById("viewTaskEndS");
     checkCount = i;
viewTaskTitleS.innerText = title.value;
viewTaskContentsS.innerText = contents.value;
viewTaskfileS.innerText = answer.value;
viewTaskStartS.innerText = moment(startDate.value).format('YYYY-MM-DD');
viewTaskEndS.innerText = moment(endDate.value).format('YYYY-MM-DD');
   }   


//선생 기타 보기
function viewETCT(i) {
     let title = document.getElementsByName("title")[i];
     let contents = document.getElementsByName("contents")[i];   
     let answer = document.getElementsByName("answer")[i];   
     let startDate = document.getElementsByName("startDate")[i];   
     let endDate = document.getElementsByName("endDate")[i];   
     let viewETCTitleT = document.getElementById("viewETCTitleT");
     let viewETCContentsT = document.getElementById("viewETCContentsT");
     let viewETCAnswerT = document.getElementById("viewETCAnswerT");
     let viewETCStartT = document.getElementById("viewETCStartT");
     let viewETCEndT = document.getElementById("viewETCEndT");
     
viewETCTitleT.innerText = title.value;
viewETCContentsT.innerText = contents.value;
viewETCAnswerT.innerText = answer.value;
viewETCStartT.innerText = moment(startDate.value).format('YYYY-MM-DD');
viewETCEndT.innerText = moment(endDate.value).format('YYYY-MM-DD');
   
   }
   
   
//학생 기타 보기
function viewETCS(i) {
     let title = document.getElementsByName("title")[i];
     let contents = document.getElementsByName("contents")[i];   
     let answer = document.getElementsByName("answer")[i];   
     let startDate = document.getElementsByName("startDate")[i];   
     let endDate = document.getElementsByName("endDate")[i];   
     let viewETCTitleS = document.getElementById("viewETCTitleS");
     let viewETCContentsS = document.getElementById("viewETCContentsS");
     let viewETCfileS = document.getElementById("viewETCfileS");
     let viewETCStartS = document.getElementById("viewETCStartS");
     let viewETCEndS = document.getElementById("viewETCEndS");
     checkCount = i;
   viewETCTitleS.innerText = title.value;
   viewETCContentsS.innerText = contents.value;
   viewETCfileS.innerText = answer.value;
   viewETCStartS.innerText = moment(startDate.value).format('YYYY-MM-DD');
   viewETCEndS.innerText = moment(endDate.value).format('YYYY-MM-DD');
   
   }
   
function makePostBox (i) {
   let updatePostBox = document.getElementById("updatePostBox");
   let deletePostBox = document.getElementById("deletePostBox");
   let ssCode = document.getElementsByName("ssCode")[i];
   console.log(ssCode.value);
   updatePostBox.innerHTML = "";
   deletePostBox.innerHTML = "";
   checkCount = i
   if(ssCode.value.substring(0,1) =='T') {
         updatePostBox.innerHTML += `<div onClick="updateSSTask(` + i + `)" data-toggle='modal' data-target='#updateTask'>수정</div>`;
      }else if (ssCode.value.substring(0,1) =='E') {
            updatePostBox.innerHTML += `<div onClick="updateSSETC(` + i + `)" data-toggle='modal' data-target='#updateETC'>수정</div>`;
         }else if(ssCode.value.substring(0,1) =='Q') {
            updatePostBox.innerHTML += `<div onClick="updateSSQuiz(` + i + `)" data-toggle='modal' data-target='#updateQuiz'>수정</div>`;
         }
   if(ssCode.value.substring(0,1) == 'T') {
      deletePostBox.innerHTML += `<div onClick="deleteSS(` + i + `)" data-toggle='modal' data-target='#deleteTask'>삭제</div>`;
   }else if (ssCode.value.substring(0,1) == 'Q') {
      deletePostBox.innerHTML += `<div onClick="deleteSS(` + i + `)" data-toggle='modal' data-target='#deleteQuiz'>삭제</div>`;
   }else if (ssCode.value.substring(0,1) == 'E') {
      deletePostBox.innerHTML += `<div onClick="deleteSS(` + i + `)" data-toggle='modal' data-target='#deleteETC'>삭제</div>`;
   }
}


function makeSubmitBox (i) {
   let viewSubmitBox = document.getElementById("viewSubmitBox");
   let deleteSubmitBox = document.getElementById("deleteSubmitBox");
   let ssCode = document.getElementsByName("ssCode")[i];
   console.log(ssCode.value);
   viewSubmitBox.innerHTML = "";
   deleteSubmitBox.innerHTML = "";
   checkCount = i
   viewSubmitBox.innerHTML += `<div onClick="updateSS(` + i + `)" data-toggle='modal' data-target='#viewSubmitTask'>보기</div>`;
   if(ssCode.value.substring(0,1) == 'T') {
      deleteSubmitBox.innerHTML += `<div onClick="deleteSM(` + i + `)" data-toggle='modal' data-target='#deleteSubmitTask'>취소</div>`;
   }else if (ssCode.value.substring(0,1) == 'Q') {
      deleteSubmitBox.innerHTML += `<div onClick="deleteSM(` + i + `)" data-toggle='modal' data-target='#deleteSubmitQuiz'>취소</div>`;
   }else if (ssCode.value.substring(0,1) == 'E') {
      deleteSubmitBox.innerHTML += `<div onClick="deleteSM(` + i + `)" data-toggle='modal' data-target='#deleteSubmitETC'>취소</div>`;
   }
}


//과제 수정 만들기
function updateSSTask (i) {
     let title = document.getElementsByName("title")[i];
     let contents = document.getElementsByName("contents")[i];   
     let answer = document.getElementsByName("answer")[i];   
     let startDate = document.getElementsByName("startDate")[i];
     let endDate = document.getElementsByName("endDate")[i];   
     let updateTaskTitle = document.getElementById("updateTaskTitle");
     let updateTaskContents = document.getElementById("updateTaskContents");
     let updateTaskAnswer = document.getElementById("updateTaskAnswer");
     let updateTaskStart = document.getElementById("updateTaskStart");
     let updateTaskEnd = document.getElementById("updateTaskEnd");


updateTaskTitle.innerHTML = `<input type="text" name="changeTaskTitle" value="${title.value}">`;
updateTaskContents.innerHTML = `<input type="text" name="changeTaskContents" value="${contents.value}">`;
updateTaskAnswer.innerHTML = `<input type="text" name="changeTaskAnswer" value="${answer.value}">`;
updateTaskStart.innerHTML = `<input type="date" name="changeTaskStartDate" value="${moment(startDate.value).format('YYYY-MM-DD')}">`;
updateTaskEnd.innerHTML = `<input type="date" name="changeTaskEndDate" value="${moment(endDate.value).format('YYYY-MM-DD')}">`;

}


// 과제 수정
function updateTask () {
   let mbId = document.getElementsByName("mbId")[0];
   let csCode = document.getElementsByName("csCode")[0];
   let opCode = document.getElementsByName("opCode")[0];
   let ssCode = document.getElementsByName("ssCode")[checkCount];
   let numCode = document.getElementsByName("numCode")[checkCount];
   let title = document.getElementsByName("changeTaskTitle")[0];
   let contents = document.getElementsByName("changeTaskContents")[0];
   let answer = document.getElementsByName("changeTaskAnswer")[0];
   let startDate = document.getElementsByName("changeTaskStartDate")[0];
   let endDate = document.getElementsByName("changeTaskEndDate")[0];
   
   let sendJsonData = [];
   alert(checkCount);
   sendJsonData.push({mbId:mbId.value, csCode:csCode.value, opCode:opCode.value, ssCode:ssCode.value, numCode:numCode.value, title:title.value, contents:contents.value, answer:answer.value, startDate:moment(startDate.value).format('YYYY-MM-DD'), endDate:moment(endDate.value).format('YYYY-MM-DD')});
   let clientData = JSON.stringify(sendJsonData);
    
     postAjax('common/updateTask',clientData,'sendMsgClass','json');
   
}

//기타 수정 만들기
function updateSSETC (i) {
     let title = document.getElementsByName("title")[i];
     let contents = document.getElementsByName("contents")[i];   
     let answer = document.getElementsByName("answer")[i];   
     let startDate = document.getElementsByName("startDate")[i];
     let endDate = document.getElementsByName("endDate")[i];   
     let updateETCTitle = document.getElementById("updateETCTitle");
     let updateETCContents = document.getElementById("updateETCContents");
     let updateETCAnswer = document.getElementById("updateETCAnswer");
     let updateETCStart = document.getElementById("updateETCStart");
     let updateETCEnd = document.getElementById("updateETCEnd");


updateETCTitle.innerHTML = `<input type="text" name="changeETCTitle" value="${title.value}">`;
updateETCContents.innerHTML = `<input type="text" name="changeETCContents" value="${contents.value}">`;
updateETCAnswer.innerHTML = `<input type="text" name="changeETCAnswer" value="${answer.value}">`;
updateETCStart.innerHTML = `<input type="date" name="changeETCStartDate" value="${moment(startDate.value).format('YYYY-MM-DD')}">`;
updateETCEnd.innerHTML = `<input type="date" name="changeETCEndDate" value="${moment(endDate.value).format('YYYY-MM-DD')}">`;

}

//기타 수정
function updateETC () {
   let mbId = document.getElementsByName("mbId")[0];
   let csCode = document.getElementsByName("csCode")[0];
   let opCode = document.getElementsByName("opCode")[0];
   let ssCode = document.getElementsByName("ssCode")[checkCount];
   let numCode = document.getElementsByName("numCode")[checkCount];
   let title = document.getElementsByName("changeETCTitle")[0];
   let contents = document.getElementsByName("changeETCContents")[0];
   let answer = document.getElementsByName("changeETCAnswer")[0];
   let startDate = document.getElementsByName("changeETCStartDate")[0];
   let endDate = document.getElementsByName("changeETCEndDate")[0];
   
   let sendJsonData = [];
   console.log(numCode.value)
   console.log(title.value)
   sendJsonData.push({mbId:mbId.value, csCode:csCode.value, opCode:opCode.value, ssCode:ssCode.value, numCode:numCode.value, title:title.value, contents:contents.value, answer:answer.value, startDate:startDate.value, endDate:endDate.value});
   let clientData = JSON.stringify(sendJsonData);
    
     postAjax('common/updateETC',clientData,'sendMsgClass','json');
   
}


//퀴즈 수정 만들기
function updateSSQuiz (i) {
     let title = document.getElementsByName("title")[i];
     let contents = document.getElementsByName("contents")[i];   
     let answer = document.getElementsByName("answer")[i];   
     let startDate = document.getElementsByName("startDate")[i];
     let endDate = document.getElementsByName("endDate")[i];   
     let updateQuizTitle = document.getElementById("updateQuizTitle");
     let updateQuizContents = document.getElementById("updateQuizContents");
     let updateQuizAnswer = document.getElementById("updateQuizAnswer");
     let updateQuizStart = document.getElementById("updateQuizStart");
     let updateQuizEnd = document.getElementById("updateQuizEnd");


updateQuizTitle.innerHTML = `<input type="text" name="changeQuizTitle" value="${title.value}">`;
updateQuizContents.innerHTML = `<input type="text" name="changeQuizContents" value="${contents.value}">`;
updateQuizAnswer.innerHTML = `<input type="text" name="changeQuizAnswer" value="${answer.value}">`;
updateQuizStart.innerHTML = `<input type="date" name="changeQuizStartDate" value="${moment(startDate.value).format('YYYY-MM-DD')}">`;
updateQuizEnd.innerHTML = `<input type="date" name="changeQuizEndDate" value="${moment(endDate.value).format('YYYY-MM-DD')}">`;

}

//퀴즈 수정
function updateQuiz () {
   let mbId = document.getElementsByName("mbId")[0];
   let csCode = document.getElementsByName("csCode")[0];
   let opCode = document.getElementsByName("opCode")[0];
   let ssCode = document.getElementsByName("ssCode")[checkCount];
   let numCode = document.getElementsByName("numCode")[checkCount];
   let title = document.getElementsByName("changeQuizTitle")[0];
   let contents = document.getElementsByName("changeQuizContents")[0];
   let answer = document.getElementsByName("changeQuizAnswer")[0];
   let startDate = document.getElementsByName("changeQuizStartDate")[0];
   let endDate = document.getElementsByName("changeQuizEndDate")[0];
   
   let sendJsonData = [];
   
   sendJsonData.push({mbId:mbId.value, csCode:csCode.value, opCode:opCode.value, ssCode:ssCode.value, numCode:numCode.value, title:title.value, contents:contents.value, answer:answer.value, startDate:moment(startDate.value).format('YYYY-MM-DD'), endDate:moment(endDate.value).format('YYYY-MM-DD')});
   let clientData = JSON.stringify(sendJsonData);
    
     postAjax('common/updateQuiz',clientData,'sendMsgClass','json');
   
}

// 내 과제물보기 가져오기
function viewMyTask () {
   let mbId = document.getElementsByName("mbId")[0];
   let csCode = document.getElementsByName("csCode")[0];
   let opCode = document.getElementsByName("opCode")[0];
   let ssCode = document.getElementsByName("ssCode")[checkCount];
   let numCode = document.getElementsByName("numCode")[checkCount];
   let stuId = document.getElementsByName("stuId")[0];

   let sendJsonData = [];
   sendJsonData.push({mbId:mbId.value, csCode:csCode.value, opCode:opCode.value, ssCode:ssCode.value, numCode:numCode.value, stuId:stuId.value});
   let clientData = JSON.stringify(sendJsonData);
    
     postAjax('common/viewMyTask',clientData,'viewMyTaskView','json');
   
}
// 내 과제물보기 출력
function viewMyTaskView (jsonData) {
   let file = document.getElementById("resultTaskFile");
   let answer = document.getElementById("resultTaskAnswer");
   
   answer.innerText = jsonData[0].answer;
   file.innerText = jsonData[1].answer;
   
   
}


//제출한 퀴즈 확인 가져오기
function resultCheckQuiz () {
   let mbId = document.getElementsByName("mbId")[0];
   let csCode = document.getElementsByName("csCode")[0];
   let opCode = document.getElementsByName("opCode")[0];
   let stuId = document.getElementsByName("stuId")[0];
   let ssCode = document.getElementsByName("ssCode")[checkCount];
   let numCode = document.getElementsByName("numCode")[checkCount];
   
   let sendJsonData = [];
   sendJsonData.push({mbId:mbId.value, csCode:csCode.value, opCode:opCode.value, stuId:stuId.value, ssCode:ssCode.value, numCode:numCode.value});

   let clientData = JSON.stringify(sendJsonData);
    
     postAjax('common/resultCheckQuiz',clientData,'resultCheckQuizView','json');
   
}
// 제출한 퀴즈 확인 출력
function resultCheckQuizView (jsonData) {
   let resultQuizT = document.getElementById("resultQuizT");
   let resultQuizS = document.getElementById("resultQuizS");
   let resultQuiz = document.getElementById("resultQuiz");

   resultQuizT.innerText = jsonData[0].answer;
   resultQuizS.innerText = jsonData[1].answer;
   if(jsonData[0].answer==jsonData[1].answer) {
      resultQuiz.innerText = "정답입니다."
      }else {resultQuiz.innerText = "오답입니다."
         };

   
}


// 과제 삭제
function deleteTask () {
   let mbId = document.getElementsByName("mbId")[0];
   let csCode = document.getElementsByName("csCode")[0];
   let opCode = document.getElementsByName("opCode")[0];
   let ssCode = document.getElementsByName("ssCode")[checkCount];
   let numCode = document.getElementsByName("numCode")[checkCount];
   
   let sendJsonData = [];
   
   sendJsonData.push({mbId:mbId.value, csCode:csCode.value, opCode:opCode.value, ssCode:ssCode.value, numCode:numCode.value})
   let clientData = JSON.stringify(sendJsonData);
    
   postAjax('common/deleteTask',clientData,'sendMsgClass','json');
}

// 과제 제출 취소
function deleteTask () {
   let mbId = document.getElementsByName("mbId")[0];
   let csCode = document.getElementsByName("csCode")[0];
   let opCode = document.getElementsByName("opCode")[0];
   let ssCode = document.getElementsByName("ssCode")[checkCount];
   let numCode = document.getElementsByName("numCode")[checkCount];
   let stuId = document.getElementsByName("stuId")[0];
   
   let sendJsonData = [];
   
   sendJsonData.push({mbId:mbId.value, csCode:csCode.value, opCode:opCode.value, ssCode:ssCode.value, numCode:numCode.value, stuId:stuId.value})
   let clientData = JSON.stringify(sendJsonData);
    
   postAjax('common/cancelTask',clientData,'sendMsgClass','json');
}

//퀴즈 삭제
function deleteQuiz() {
   let mbId = document.getElementsByName("mbId")[0];
   let csCode = document.getElementsByName("csCode")[0];
   let opCode = document.getElementsByName("opCode")[0];
   let ssCode = document.getElementsByName("ssCode")[checkCount];
   let numCode = document.getElementsByName("numCode")[checkCount];
   console.log(checkCount);
   console.log(ssCode.value);
   console.log(numCode.value);
   let sendJsonData = [];
   
   sendJsonData.push({mbId:mbId.value, csCode:csCode.value, opCode:opCode.value, ssCode:ssCode.value, numCode:numCode.value})
   let clientData = JSON.stringify(sendJsonData);
    
   postAjax('common/deleteQuiz',clientData,'sendMsgClass','json');
}


//기타 삭제
function deleteETC() {
   let mbId = document.getElementsByName("mbId")[0];
   let csCode = document.getElementsByName("csCode")[0];
   let opCode = document.getElementsByName("opCode")[0];
   let ssCode = document.getElementsByName("ssCode")[checkCount];
   let numCode = document.getElementsByName("numCode")[checkCount];
   
   let sendJsonData = [];
   
   sendJsonData.push({mbId:mbId.value, csCode:csCode.value, opCode:opCode.value, ssCode:ssCode.value, numCode:numCode.value})
   let clientData = JSON.stringify(sendJsonData);
    
   postAjax('common/deleteETC',clientData,'sendMsgClass','json');
}

//제출한 기타 보기
function viewMyETC() {
   let mbId = document.getElementsByName("mbId")[0];
   let csCode = document.getElementsByName("csCode")[0];
   let opCode = document.getElementsByName("opCode")[0];
   let ssCode = document.getElementsByName("ssCode")[checkCount];
   let numCode = document.getElementsByName("numCode")[checkCount];
   
   let sendJsonData = [];
   
   sendJsonData.push({mbId:mbId.value, csCode:csCode.value, opCode:opCode.value, ssCode:ssCode.value, numCode:numCode.value})
   let clientData = JSON.stringify(sendJsonData);
    
   postAjax('common/viewMyETC',clientData,'viewMyETCView','json');
}

//제출한 기타 보기
function viewMyETCView(jsonData) {
   let resultETC = document.getElementById("resultETC");
   console.log(jsonData[0].answer);
   resultETC.innerText = jsonData[0].answer;

}


//제출한 기타 취소
function cancelETCReport() {
   let mbId = document.getElementsByName("mbId")[0];
   let csCode = document.getElementsByName("csCode")[0];
   let opCode = document.getElementsByName("opCode")[0];
   let ssCode = document.getElementsByName("ssCode")[checkCount];
   let numCode = document.getElementsByName("numCode")[checkCount];
   
   let sendJsonData = [];
   
   sendJsonData.push({mbId:mbId.value, csCode:csCode.value, opCode:opCode.value, ssCode:ssCode.value, numCode:numCode.value})
   let clientData = JSON.stringify(sendJsonData);
    
   postAjax('common/cancelETCReport',clientData,'sendMsgClass','json');
}

//학생 퀴즈 제출
function submitQuiz () {
   let mbId = document.getElementsByName("mbId")[0];
   let csCode = document.getElementsByName("csCode")[0];
   let opCode = document.getElementsByName("opCode")[0];
   let ssCode = document.getElementsByName("ssCode")[checkCount];
   let numCode = document.getElementsByName("numCode")[checkCount];
   let stuId = document.getElementsByName("stuId")[0];
   let title = document.getElementsByName("title")[checkCount];
   let answer = document.getElementById("viewQuizAnswerS");
   let contents = document.getElementsByName("contents")[checkCount];
   let sendJsonData = [];
   
   sendJsonData.push({mbId:mbId.value, csCode:csCode.value, opCode:opCode.value, ssCode:ssCode.value, numCode:numCode.value, stuId:stuId.value, title:title.value, contents:contents.value, answer:answer.value})
   let clientData = JSON.stringify(sendJsonData);
    
   postAjax('common/submitQuiz',clientData,'sendMsgClass','json');

}


//학생 과제 제출
function submitTask () {
   let mbId = document.getElementsByName("mbId")[0];
   let csCode = document.getElementsByName("csCode")[0];
   let opCode = document.getElementsByName("opCode")[0];
   let ssCode = document.getElementsByName("ssCode")[checkCount];
   let numCode = document.getElementsByName("numCode")[checkCount];
   let stuId = document.getElementsByName("stuId")[0];
   let title = document.getElementsByName("title")[checkCount];
   let answer = document.getElementById("submitTaskAnswerS");
   let filePath = document.getElementById("submitTaskfileS");
   let contents = document.getElementsByName("contents")[checkCount];
   let sendJsonData = [];
   
   sendJsonData.push({mbId:mbId.value, csCode:csCode.value, opCode:opCode.value, ssCode:ssCode.value, numCode:numCode.value, stuId:stuId.value, title:title.value, contents:contents.value, answer:answer.value, filePath:filePath.value})
   let clientData = JSON.stringify(sendJsonData);
    
   postAjax('common/submitTask',clientData,'sendMsgClass','json');

}

//학생 기타 제출
function submitETC () {
   let mbId = document.getElementsByName("mbId")[0];
   let csCode = document.getElementsByName("csCode")[0];
   let opCode = document.getElementsByName("opCode")[0];
   let ssCode = document.getElementsByName("ssCode")[checkCount];
   let numCode = document.getElementsByName("numCode")[checkCount];
   let stuId = document.getElementsByName("stuId")[0];
   let title = document.getElementsByName("title")[checkCount];
   let answer = document.getElementById("viewETCAnswerS");
   
   
   let contents = document.getElementsByName("contents")[checkCount];
   let sendJsonData = [];
   alert(answer.value);
   sendJsonData.push({mbId:mbId.value, csCode:csCode.value, opCode:opCode.value, ssCode:ssCode.value, numCode:numCode.value, stuId:stuId.value, title:title.value, contents:contents.value, answer:answer.value})
   let clientData = JSON.stringify(sendJsonData);
    
   postAjax('common/submitETC',clientData,'sendMsgClass','json');

}

//과제 제출 현황 보기
function moveStuReport () {
   let mbId = document.getElementsByName("mbId")[0];
   let csCode = document.getElementsByName("csCode")[0];
   let opCode = document.getElementsByName("opCode")[0];
   let mbType = document.getElementsByName("mbType")[0];
   let ssCode = document.getElementsByName("ssCode")[checkCount];
   let numCode = document.getElementsByName("numCode")[checkCount];

   let f = makeForm('stuReport','post');
   document.body.appendChild(f);
   
   f.appendChild(mbId);
   f.appendChild(csCode);
   f.appendChild(opCode);
   f.appendChild(mbType);
   f.appendChild(ssCode);
   f.appendChild(numCode);
   
   f.submit();
   
}


//과제 학생 리스트 가져오기
function viewAllReport(){
   let mbId = document.getElementsByName("mbId")[0];
   let csCode = document.getElementsByName("csCode")[0];
   let opCode = document.getElementsByName("opCode")[0];

   sendJsonData = [];
   sendJsonData.push ({mbId:mbId.value, csCode:csCode.value, opCode:opCode.value});
   let clientData = JSON.stringify(sendJsonData);
   
   postAjax('common/viewAllReport', clientData, 'viewAllReportView', 'json');

}

//과제 학생 리스트 출력하기
function viewAllReportView(jsonData) {

   let TaskStuList = document.getElementById("TaskStuList");

   for(i=0; i<jsonData.length; i++) {
          
   TaskStuList.innerText += jsonData[i].stuName;
   TaskStuList.innerHTML += "<input type = 'hidden' name = 'stuId', value='"+jsonData[i].stuId+"' />";
   }
}


//과제 학생 정답 가져오기
function selectStuAnswer(){
   let mbId = document.getElementsByName("mbId")[0];
   let csCode = document.getElementsByName("csCode")[0];
   let opCode = document.getElementsByName("opCode")[0];
   let ssCode = document.getElementsByName("ssCode")[checkCount];
   let numCode = document.getElementsByName("numCode")[checkCount];
   
   sendJsonData = [];
   sendJsonData.push ({mbId:mbId.value, csCode:csCode.value, opCode:opCode.value, ssCode:ssCode.value, numCode:numCode.value});
   let clientData = JSON.stringify(sendJsonData);
   
   postAjax('common/selectStuAnswer', clientData, 'selectStuAnswerView', 'json');

}

//과제 학생 정답 출력하기
function selectStuAnswerView(jsonData) {
   let selectStuAnswer = document.getElementById("selectStuAnswer");

   for(i=0; i<jsonData.length; i++) {
          
   selectStuAnswer.innerText += jsonData[i].answer;

   }
}

//과제 학생 과제 가져오기
function selectStuReport(){
   let mbId = document.getElementsByName("mbId")[0];
   let csCode = document.getElementsByName("csCode")[0];
   let opCode = document.getElementsByName("opCode")[0];
   let ssCode = document.getElementsByName("ssCode")[checkCount];
   let numCode = document.getElementsByName("numCode")[checkCount];
   
   console.log(ssCode.value);
   sendJsonData = [];
   sendJsonData.push ({mbId:mbId.value, csCode:csCode.value, opCode:opCode.value, ssCode:ssCode.value, numCode:numCode.value});
   let clientData = JSON.stringify(sendJsonData);
   
   postAjax('common/selectStuReport', clientData, 'selectStuReportView', 'json');

}

//과제 학생 과제 출력하기
function selectStuReportView(jsonData) {
   let selectStuReport = document.getElementById("selectStuReport");

   for(i=0; i<jsonData.length; i++) {
          
   selectStuReport.innerText += jsonData[i].answer;

   }
}

//기타 제출 현황 보기
function moveStuEtc () {
   let mbId = document.getElementsByName("mbId")[0];
   let csCode = document.getElementsByName("csCode")[0];
   let opCode = document.getElementsByName("opCode")[0];
   let mbType = document.getElementsByName("mbType")[0];
   let ssCode = document.getElementsByName("ssCode")[checkCount];
   let numCode = document.getElementsByName("numCode")[checkCount];

   let f = makeForm('stuETC','post');
   document.body.appendChild(f);
   
   f.appendChild(mbId);
   f.appendChild(csCode);
   f.appendChild(opCode);
   f.appendChild(mbType);
   f.appendChild(ssCode);
   f.appendChild(numCode);
   
   f.submit();
   
}


//기타 학생 리스트 가져오기
function viewAllETC(){
   let mbId = document.getElementsByName("mbId")[0];
   let csCode = document.getElementsByName("csCode")[0];
   let opCode = document.getElementsByName("opCode")[0];
   sendJsonData = [];
   sendJsonData.push ({mbId:mbId.value, csCode:csCode.value, opCode:opCode.value});
   let clientData = JSON.stringify(sendJsonData);
   
   postAjax('common/viewAllETC', clientData, 'viewAllETCView', 'json');

}

//기타 학생 리스트 출력하기
function viewAllETCView(jsonData) {

   let ETCStuList = document.getElementById("ETCStuList");

   for(i=0; i<jsonData.length; i++) {
          
   ETCStuList.innerText += jsonData[i].stuName;
   ETCStuList.innerHTML += "<input type = 'hidden' name = 'stuId', value='"+jsonData[i].stuId+"' />";
   }
}


//기타 학생 기타 가져오기
function selectStuETC(){
   let mbId = document.getElementsByName("mbId")[0];
   let csCode = document.getElementsByName("csCode")[0];
   let opCode = document.getElementsByName("opCode")[0];
   let ssCode = document.getElementsByName("ssCode")[checkCount];
   let numCode = document.getElementsByName("numCode")[checkCount];
   
   console.log(ssCode.value);
   sendJsonData = [];
   sendJsonData.push ({mbId:mbId.value, csCode:csCode.value, opCode:opCode.value, ssCode:ssCode.value, numCode:numCode.value});
   let clientData = JSON.stringify(sendJsonData);
   
   postAjax('common/selectStuETC', clientData, 'selectStuETCView', 'json');

}

//기타 학생 기타 출력하기
function selectStuETCView(jsonData) {
   let selectStuETC = document.getElementById("selectStuETC");

   for(i=0; i<jsonData.length; i++) {
          
   selectStuETC.innerText += jsonData[i].answer;

   }
}

//인덱스 변환
function changeCheck(i) {
   checkCount = i;
   
}

//퀴즈 입력값 초기화
function deleteQuizInfo() {
   let resultQuizT = document.getElementById("resultQuizT");
   let resultQuizS = document.getElementById("resultQuizS");
   let resultQuiz = document.getElementById("resultQuiz");
   let viewQuizAnswerS = document.getElementById("viewQuizAnswerS");
   
   resultQuizT.innerHTML = `<div></div>`;
   resultQuizS.innerHTML = `<div></div>`;
   resultQuiz.innerHTML = `<div></div>`;
   viewQuizAnswerS.value = "";
}

//기타 입력값 초기화
function deleteETCInfo() {
   let resultETC = document.getElementById("resultETC");
   let viewETCAnswerS = document.getElementById("viewETCAnswerS");
   resultETC.innerHTML = `<div></div>`;
   viewETCAnswerS.value = "";
   
}

//과제 입력값 초기화
function deleteTaskInfo() {
   let submitTaskAnswerS = document.getElementById("submitTaskAnswerS");
   submitTaskAnswerS.value = "";
   
}

function sendMsgClass(message){
   alert(JSON.parse(JSON.stringify(message)).message);
   allPostList()
}


//파일업로드
$(document).ready(function(){
   var fileTarget = $('.filebox .upload-hidden');

    fileTarget.on('change', function(){
        if(window.FileReader){
            // 파일명 추출
            var filename = $(this)[0].files[0].name;
        } 

        else {
            // Old IE 파일명 추출
            var filename = $(this).val().split('/').pop().split('\\').pop();
        };

        $(this).siblings('.upload-name').val(filename);
    });

    //preview image 
    var imgTarget = $('.preview-image .upload-hidden');

    imgTarget.on('change', function(){
        var parent = $(this).parent();
        parent.children('.upload-display').remove();

        if(window.FileReader){
            //image 파일만
            if (!$(this)[0].files[0].type.match(/image\//)) return;
            
            var reader = new FileReader();
            reader.onload = function(e){
                var src = e.target.result;
                parent.prepend('<div class="upload-display"><div class="upload-thumb-wrap"><img src="'+src+'" class="upload-thumb"></div></div>');
            }
            reader.readAsDataURL($(this)[0].files[0]);
        }

        else {
            $(this)[0].select();
            $(this)[0].blur();
            var imgSrc = document.selection.createRange().text;
            parent.prepend('<div class="upload-display"><div class="upload-thumb-wrap"><img class="upload-thumb"></div></div>');

            var img = $(this).siblings('.upload-display').find('img');
            img[0].style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(enable='true',sizingMethod='scale',src=\""+imgSrc+"\")";        
        }
    });
});
