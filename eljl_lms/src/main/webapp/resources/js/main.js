
  // 클래스 생성
  function createClass(){
	  let title = document.getElementsByName("title")[0];
	  let sDate = document.getElementsByName("startDate")[0];
	  let eDate = document.getElementsByName("endDate")[0];  
	  let csName = document.getElementsByName("csName")[0];	  

	  let sendJsonData = [];
	  let sendJsonData2 = [];
	 	 
	  for(i=0; i < count; i++){
	  	let item = document.getElementsByName("category")[i];
	  	let grade = document.getElementsByName("addGrade")[i];
	  	let addName = document.getElementById("addName"+i);		
	  		if(i == 0){
	  			let nameBox = document.getElementById("nameBox");
	  		}else{
	  			let nameBox = document.getElementById("nameBox"+i);
	  		}
	  		
	  		sendJsonData2.push({itemCode:item.value,itemPercent:grade.value}); 
	  		
	  }
	  sendJsonData.push({csName:csName.value,opName:title.value,startDate:sDate.value,endDate:eDate.value,gbList:sendJsonData2});
	  let clientData = JSON.stringify(sendJsonData);
	  alert(clientData);
	  postAjax('dashboard/createLecture',clientData,'test','json');
  }
  
  function test(jsonData){
	  alert("끝");
  }
  
  
  // 값 지우기(닫기)
  function deleteValue(){
	  let title = document.getElementsByName("title")[0];
	  let startDate = document.getElementsByName("startDate")[0];
	  let endDate = document.getElementsByName("endDate")[0];
	  
	  title.value = "";
	  startDate.value = "";
	  endDate.value = "";
	
	  let class2 = document.getElementById("modal-body"); 
	  class2.innerHTML = "<p>클래스 타이틀<a onClick='addCategory()'><span class='glyphicon glyphicon-plus' ></span></a></p><div id='box'><select name='category' onChange='addName(this.options[this.selectedIndex].value,')'><option value='T'>과제</option><option value='Q'>퀴즈</option><option value='A'>출결</option><option value='E'>기타</option></select><input type='text' name=''/><div id='addName'></div></div>";
  }
  
  let count = 1;
  
  // 백분율 추가
  function addCategory() {
	let div = document.getElementById("modal-body");
	div.innerHTML += "<div id='Category"+count+"'><select name='category' onChange='addName(this.options[this.selectedIndex].value,"+count+")'> "+"<option value='T'>과제</option>" + "<option value='Q'>퀴즈</option>" + "<option value='A'>출결</option>"+ "<option value='E'>기타</option>"+ "</select>"+ "<input type='text' name='addGrade'/><div id='addName"+count+"'></div><input type='button' value='삭제' onClick='deleteCategory("+count+")'> </div>";
	count++;
  }
  
  // 백분율 삭제
  function deleteCategory(number){
	  let div = document.getElementById("Category"+number);
  	  div.remove();
  }
  
  // 기타일 경우 -> 이름
  function addName(data,number){
	  if(data == "E"){
		  let div = document.getElementById("addName"+number);
		  div.innerHTML = "이름 : <input id='nameBox"+number+"' type='text'/>";
	  }else{
		  let test = document.getElementById("addName"+number);
		  if(test.innerText != ""){
			  let div = document.getElementById("nameBox"+number);
			  div.remove();
			  test.innerText = ""; 
		  }	  
	  }
  }
  
  // 클래스 리스트 가져오기
  function getList(){
	  let sendJsonData = [];
	  sendJsonData.push({mbId:""});
	  let clientData = JSON.stringify(sendJsonData);
	  alert(clientData);
	  postAjax('dashboard/getMyLectureList',clientData,'getListView','json');
  }
  
  //클래스 리스트 가져와서 출력
  function getListView(jsonData){
	  let myClass = document.getElementById("myClass");
	  myClass.innerHTML = "<div>"+jsonData[0].opName +" "+ jsonData[0].startDate +" "+ jsonData[0].endDate+"</div>" ;
  }
  
  // 삭제할 클래스 리스트 가져오기
  function deleteClassList(){
	 let sendJsonData = [];
	  sendJsonData.push({mbId:""});
	  let clientData = JSON.stringify(sendJsonData);
	  alert(clientData);
	  postAjax('dashboard/getMyLectureList',clientData,'getdeleteClassList','json');
  }
  
  // 삭제할 클래스 리스트 가져와서 출력
  function getdeleteClassList(jsonData){
	let deleteBox = document.getElementById("deleteClassBox");
	   deleteBox.innerHTML = "";
	for(i=0; i < jsonData.length; i++){
		if(jsonData[0].opName != null){
			deleteBox.innerHTML += "<tr><td>"+jsonData[i].opName+"</td><td><button type='button' class='btn btn-default'  onClick='deleteClass()'>삭제</button></td></tr>";
		}  
	}
	
}
  	
