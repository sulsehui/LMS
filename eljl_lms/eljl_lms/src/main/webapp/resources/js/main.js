
	let number;
	let count = 1;
  // 클래스 생성
  function createClass(){
	
	  let csName = document.getElementsByName("csName")[0];
	  let sDate = document.getElementsByName("startDate")[0];
	  let eDate = document.getElementsByName("endDate")[0];  
	  let opName = document.getElementsByName("opName")[0];	
	  
	  let sendJsonData = [];
	  let sendJsonData2 = [];
	  let result = false;

	  let grade = 0;
	 
	if(csName.value != "" && sDate.value != "" && eDate.value != "" && opName.value != ""){

	  for(i=0; i < count-1; i++){
			grade += parseInt(document.getElementsByName("addGrade")[i].value);
         
		}
		if(grade == 100){
			result = true;
		}else{
			alert("백분율을 맞춰주세요");
		}
		
	let up = 0;
 	if(result == true){
	  for(i=0; i < count-1; i++){
		
	  	let item = document.getElementsByName("category")[i];

		let grade = document.getElementsByName("addGrade")[i];
	  
	  	//let addName = document.getElementById("addName"+i);
		if(item.value == "E"){
			let nameBox = document.getElementsByName("nameBox")[up];
			if(nameBox.value == ""){
				sendJsonData2.push({itemCode:item.value,itemName:"기타",itemPercent:grade.value});
			}else{
				sendJsonData2.push({itemCode:item.value,itemName:nameBox.value,itemPercent:grade.value});
			}
			up++;
		}else if(item.value == "T"){
			sendJsonData2.push({itemCode:item.value,itemName:"과제",itemPercent:grade.value}); 
		}else if(item.value == "Q"){
			sendJsonData2.push({itemCode:item.value,itemName:"퀴즈",itemPercent:grade.value}); 
		}else {
			sendJsonData2.push({itemCode:item.value,itemName:"출석",itemPercent:grade.value}); 
		}
	  			
	  }
	  sendJsonData.push({csName:csName.value,opName:opName.value,startDate:sDate.value,endDate:eDate.value,gbList:sendJsonData2});
	  let clientData = JSON.stringify(sendJsonData);
	  
	  postAjax('dashboard/createLecture',clientData,'sendMsg','json');
     }

	}else{
		alert("빈칸없이 채워주세요");
	}
	 
  }

 //날짜 
 function endDateCheck(){
	
	let startDate = parseInt(document.getElementsByName("startDate")[0].value.replace(/-/gi,""));
	let endDate = parseInt(document.getElementsByName("endDate")[0].value.replace(/-/gi,""));  
	
   if(startDate > endDate){
		alert("날짜를 다시 확인해주세요");
	let endDate = document.getElementsByName("endDate")[0];
	endDate.value = "";
     }
	
   }
  
 
  
  // 값 지우기(닫기)
  function deleteValue(){
	  let csName = document.getElementsByName("csName")[0];
	  let startDate = document.getElementsByName("startDate")[0];
	  let endDate = document.getElementsByName("endDate")[0];
	  let opName = document.getElementsByName("opName")[0];	  
	  let modalBox = document.getElementById("modalBox");
	
	
	  csName.value = "";
	  startDate.value = "";
	  endDate.value = "";
      opName.value = "";	  
	  modalBox.innerHTML = "";	
		
	  count = 1;
	  //let class2 = document.getElementById("modal-body"); 
	  //class2.innerHTML = "<p>클래스 타이틀<a onClick='addCategory()'><span class='glyphicon glyphicon-plus' ></span></a></p><div id='box'><select name='category' onChange='addName(this.options[this.selectedIndex].value,')'><option value='T'>과제</option><option value='Q'>퀴즈</option><option value='A'>출결</option><option value='E'>기타</option></select><input type='text' name=''/><div id='addName'></div></div>";
  }
  
  
  
  function displayCate(){
	
 }

	
  let save;
  // 백분율 추가
  function addCategory() {
	let div = document.getElementById("modalBox");
	save = div.innerHTML;
	let score ="";
	let type = "";
	let text = "";
	let index = 0;
	
	if(count == 1){
	 div.innerHTML = "<div id='Category"+count+"'><select name='category' onChange='addName(this.options[this.selectedIndex].value,"+count+")'> "+"<option value='T'>과제</option>" + "<option value='Q'>퀴즈</option>" + "<option value='A'>출결</option>"+ "<option value='E'>기타</option>"+ "</select>"+ "<input type='text' name='addGrade' value='' /><div id='addName"+count+"'></div><input type='button' value='삭제' onClick='deleteCategory("+count+")'> </div>";
	}else{
	 for(i=0; i < count-1; i++){
		 score += document.getElementsByName("addGrade")[i].value + ",";
	     type += document.getElementsByName("category")[i].value + ",";
		 if(document.getElementsByName("category")[i].value == "E"){
			text += document.getElementsByName("nameBox")[index].value + ",";
			index++;
		}
	}
	
	alert("현재 text :"+text);
	
	index = 0;
	div.innerHTML = save + "<div id='Category"+count+"'><select name='category' onChange='addName(this.options[this.selectedIndex].value,"+count+")'> "+"<option value='T'>과제</option>" + "<option value='Q'>퀴즈</option>" + "<option value='A'>출결</option>"+ "<option value='E'>기타</option>"+ "</select>"+ "<input type='text' name='addGrade' value='' /><div id='addName"+count+"'></div><input type='button' value='삭제' onClick='deleteCategory("+count+")'> </div>";
	
	 for(i=0; i < count; i++){
		 let addGrade = document.getElementsByName("addGrade")[i];
		 let category =	document.getElementsByName("category")[i];
	     
		 addGrade.value = score.split(",")[i] + "";
			 if(type.split(",")[i] != ""){
				category.value = type.split(",")[i] + "";
			 }
		if(type.split(",")[i] == "E"){
			let namebox = document.getElementsByName("nameBox")[index];
			namebox.value = text.split(",")[index] + "";
			alert(namebox.value);
			index++; 
		}
	  }
	}
	
	count++;
  }
  
  // 백분율 삭제
  function deleteCategory(number){
	  let div = document.getElementById("Category"+number);
  	  div.remove();
	  count--;
  }
  
  // 기타일 경우 -> 이름
  function addName(data,number){
	  if(data == "E"){
		  let div = document.getElementById("addName"+number);
		  div.innerHTML = "이름 : <input name='nameBox' type='text'/>";
	  }else{
		  let test = document.getElementById("addName"+number);
		  if(test.innerText != ""){
			  test.innerText = ""; 
		  }	  
	  }
  }
  
  // 클래스 리스트 가져오기
  function getList(){
	  let mbType = document.getElementsByName("mbType")[0];
	
	  let sendJsonData = [];
	  sendJsonData.push({mbType:mbType.value});
	  let clientData = JSON.stringify(sendJsonData);
	 
	  postAjax('dashboard/getMyLectureList',clientData,'getListView','json');
  }
  
  //클래스 리스트 가져와서 출력
  function getListView(jsonData){
	  let mbType = document.getElementsByName("mbType")[0];
	  let myClass = document.getElementById("myClass");
	  myClass.innerHTML = "";
	  for(i=0; i < jsonData.length; i++){
		if(mbType.value == "T") {
			if(jsonData[i].opName != null){
			myClass.innerHTML += "<div><a href='/streamForm'>"+jsonData[i].opName +" "+ moment(jsonData[i].startDate).format("YYYY-MM-DD") +" "+ moment(jsonData[i].endDate).format("YYYY-MM-DD")+"</a></div>" ;
			myClass.innerHTML += "<input type='hidden' name='myTeId', value='"+jsonData[i].teId+"' />";
			myClass.innerHTML += "<input type='hidden' name='myCsCode', value='"+jsonData[i].csCode+"' />";
			myClass.innerHTML += "<input type='hidden' name='myOpCode', value='"+jsonData[i].opCode+"' />";
			myClass.innerHTML += "<input type='hidden' name='number', value='"+i+"' />";
			}
		}else{
			if(jsonData[i].opName != null){
			myClass.innerHTML += "<div><a href='/streamForm'>"+jsonData[i].opName +" "+ moment(jsonData[i].startDate).format("YYYY-MM-DD") +" "+ moment(jsonData[i].endDate).format("YYYY-MM-DD")+"</a><div data-toggle='modal' data-target='#cancelClass' class='glyphicon glyphicon-option-vertical' onClick='addNumber("+i+")'></div></div>" ;
			myClass.innerHTML += "<input type='hidden' name='myTeId', value='"+jsonData[i].teId+"' />";
			myClass.innerHTML += "<input type='hidden' name='myCsCode', value='"+jsonData[i].csCode+"' />";
			myClass.innerHTML += "<input type='hidden' name='myOpCode', value='"+jsonData[i].opCode+"' />";
			myClass.innerHTML += "<input type='hidden' name='number', value='"+i+"' />";
			}
		}
		
	}
	  
  }
  
  // 삭제할 클래스 리스트 가져오기
  function deleteClassList(){
	 let mbType = document.getElementsByName("mbType")[0];
	 let sendJsonData = [];
	  sendJsonData.push({mbType:mbType.value});
	  let clientData = JSON.stringify(sendJsonData);
  
	  postAjax('dashboard/getMyLectureList',clientData,'getdeleteClassList','json');
  }
  
  // 삭제할 클래스 리스트 가져와서 출력
  function getdeleteClassList(jsonData){
	let deleteBox = document.getElementById("deleteClassBox");
	   deleteBox.innerHTML = "";
	for(i=0; i < jsonData.length; i++){
		if(jsonData[0].opName != null){
			deleteBox.innerHTML += "<tr><td>"+jsonData[i].opName+"</td><input type='hidden' name='deleteClass' value='"+jsonData[i].opCode+"'/><td><button type='button' class='btn btn-default'  onClick='deleteClass("+i+")'>삭제</button></td></tr>";
		}  
	}
	
}

	// 클래스 삭제
  	function deleteClass(number){
	let mbType = document.getElementsByName("mbType")[0];
	let deleteClass = document.getElementsByName("deleteClass")[number];
	postAjax('deleteClass',"opCode="+deleteClass.value+"&"+"mbType="+mbType.value,'getdeleteClassList','form');
}

	//클래스생성,삭제 아이콘 (교사로 로그인했을때만 화면에 보이게 출력)
	function divCreate(){
		let mbType = document.getElementsByName("mbType")[0].value;
		let user = document.getElementById("user");
		
		if(mbType == "T"){
			user.style.display="block";
			}
	   }

	//다른 클래스 가져오기
	function otherClass(){
		let mbType = document.getElementsByName("mbType")[0];
		let sendJsonData = [];
	  sendJsonData.push({mbId:"", mbType:mbType.value});
	  let clientData = JSON.stringify(sendJsonData);
	  
	  postAjax('dashboard/getTotalLectureList',clientData,'getOtherListView','json');
		}
		
	//다른 클래스 리스트 출력
	function getOtherListView(jsonData){

		let getOther = document.getElementById("otherClassList");
		let mbType = document.getElementsByName("mbType")[0];
		getOther.innerHTML = "";

		if(mbType.value == "S"){
			for(i=0; i < jsonData.length; i++){
			if(jsonData[i].opName != null){
			getOther.innerHTML += "<div onClick='addNumber("+i+")' data-toggle='modal' data-target='#joinClass'>"+jsonData[i].opName + "</div>" ;
			getOther.innerHTML += "<input type = 'hidden' name = 'teId' value = '"+ jsonData[i].teId+"'/>";
			getOther.innerHTML += "<input type = 'hidden' name = 'csCode' value = '"+ jsonData[i].csCode+"'/>";
			getOther.innerHTML += "<input type = 'hidden' name = 'opCode' value = '"+ jsonData[i].opCode+"'/>";
				}
			}
		}else{
			for(i=0; i < jsonData.length; i++){
			if(jsonData[i].opName != null){
			getOther.innerHTML += "<div>"+jsonData[i].opName + "</div>" ;
			getOther.innerHTML += "<input type = 'hidden' name = 'teId' value = '"+ jsonData[i].teId+"'/>";
			getOther.innerHTML += "<input type = 'hidden' name = 'csCode' value = '"+ jsonData[i].csCode+"'/>";
			getOther.innerHTML += "<input type = 'hidden' name = 'opCode' value = '"+ jsonData[i].opCode+"'/>";
				}
			}
		}
	}
	
	//수강신청
	function joinClass(){
	
	 let teId = document.getElementsByName("teId")[number];
	 let csCode = document.getElementsByName("csCode")[number];
	 let opCode = document.getElementsByName("opCode")[number];
		
	 let sendJsonData = [];
	  sendJsonData.push({teId:teId.value, csCode:csCode.value, opCode:opCode.value});

	 let clientData = JSON.stringify(sendJsonData);
	 
	  postAjax('dashboard/accessClass',clientData,'sendMsg','json');
		
	}
	
	
	 
	function addNumber(index){
		number = index;
	}
	
	//수강 취소
	function cancelClass(number){
		
	  let teId=document.getElementsByName("myTeId")[number];
	  let csCode=document.getElementsByName("myCsCode")[number];
	  let opCode=document.getElementsByName("myOpCode")[number];

	//console.log(teId);
	//console.log(csCode);
	//console.log(opCode);
	
 	//let sendJsonData = [];
	  //sendJsonData.push({teId:teId.value, csCode:csCode.value, opCode:opCode.value});
		let clientData = "teId="+teId.value+"&csCode="+csCode.value+"&opCode="+opCode.value;
		
	 //let clientData = JSON.stringify(sendJsonData);
	postAjax('dashboard/cancelClass', clientData,'sendMsg','form');

	 }

function sendMsg(message){
	alert(JSON.parse(JSON.stringify(message)).message);
	 getList();
	otherClass();
}

	
	
	
	
	
	
	