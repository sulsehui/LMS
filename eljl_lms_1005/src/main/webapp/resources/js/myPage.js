
const main = new Vue({
  el: '#vue',
  data: {
   display:[{show:false},{show:false},{show:true},{show:false},{show:false}]
    ,list:[] //the declared array
   ,gradeList:[]
   ,siList:[]
   ,cosList:[]
   ,info:{}
  },
methods:{
   changepage:function(page){
         for(i=0; i<this.display.length; i++){
            this.display[i].show = false;
         }
         this.display[page].show = true;
      },   
      window:onload = function(){
         // type onload
         let teItem = document.getElementsByClassName("teItem");
         let stuItem = document.getElementsByClassName("stuItem");
         let bannerBox = document.getElementById("bannerBox");
         let mbType = document.getElementsByName("mbType")[0];
         if(mbType.value == "T"){
            stuItem[0].style.display = "none";
         }else{
            teItem[0].style.display = "none";
            teItem[1].style.display = "none";
            teItem[2].style.display = "none";
            bannerBox.style.paddingRight = "65px";
         }
         // 리스트 onload
         let mbId = document.getElementsByName("mbId")[0];
          let csCode = document.getElementsByName("csCode")[0];
          let opCode = document.getElementsByName("opCode")[0];
         
          let sendJsonData={teId:mbId.value, csCode:csCode.value, opCode:opCode.value};
         this.info = sendJsonData;
         
           let clientData = JSON.stringify(this.info);
         postAjax('student/selectList',clientData,'getListView','json');
           postAjax('student/selectGradeList',clientData,'getGradeListView','json');
         postAjax('student/selectSiList',clientData,'getSiListView','json');
         postAjax('student/selectCosList',clientData,'getCosListView','json');
      },
      insertCos:function(){
         let insTitle = document.getElementsByName("insTitle")[0];
         let insContents = document.getElementsByName("insContents")[0];
         let insDate = document.getElementsByName("requestDate")[0];
         
         this.info.sjTitle = insTitle.value;
         this.info.contents = insContents.value;
         this.info.requestDate = insDate.value;
         insTitle.value="";
         insContents.value="";
         insDate.value="";
         let clientData = JSON.stringify(this.info);
         postAjax('student/insertStuCo',clientData,'messages','json');
      },
      indexCheck:function(index){
         let stuTitle = document.getElementsByName("updateTitle")[0];
         let contents = document.getElementsByName("updateContents")[0];
         let requestDate = document.getElementsByName("requestDate")[0];
         
         
         stuTitle.value = main.cosList[index].stuTitle;
         contents.value = main.cosList[index].contents;
         requestDate.value = main.cosList[index].requestDate;
         main.info.stuTitle = main.cosList[index].stuTitle;
         main.info.contents = main.cosList[index].contents;   
         main.info.requestDate = main.cosList[index].requestDate;
         main.info.cosCode = main.cosList[index].cosCode; 
      },
      statusCheck:function(status){
         if(status == '수락'){
            main.changepage(0);
            main.changepage(2);
            alert("이미 처리된 상담입니다.");
         }else{
            main.changepage(1);

         }
      },
      statusCheck2:function(status,index){
         if(status == '수락'){
            main.changepage(0);
            let stuTitle = document.getElementsByName("updateTitle")[0];
            let contents = document.getElementsByName("updateContents")[0];
            let requestDate = document.getElementsByName("requestDate")[0];
            stuTitle.value = main.cosList[index].stuTitle;
            contents.value = main.cosList[index].contents;
            requestDate.value = main.cosList[index].requestDate;
            main.info.stuTitle = main.cosList[index].stuTitle;
            main.info.contents = main.cosList[index].contents;   
            main.info.requestDate = main.cosList[index].requestDate;
            main.info.cosCode = main.cosList[index].cosCode; 
            alert("이미 처리된 상담입니다.");
         }else{
            main.changepage(2);
            let stuTitle = document.getElementsByName("updateTitle")[1];
            let contents = document.getElementsByName("updateContents")[1];
            let requestDate = document.getElementsByName("requestDate")[0];
         
            stuTitle.value = main.cosList[index].stuTitle;
            contents.value = main.cosList[index].contents;
            requestDate.value = main.cosList[index].requestDate;
            main.info.stuTitle = main.cosList[index].stuTitle;
            main.info.contents = main.cosList[index].contents;   
            main.info.requestDate = main.cosList[index].requestDate;
            main.info.cosCode = main.cosList[index].cosCode; 
         }
      },
      deleteCos:function(){
         
         let clientData = JSON.stringify(this.info);         
         postAjax('student/deleteCos',clientData,'messages','json');
      },
      updateCos:function(requestDate){
         let stuTitle = document.getElementsByName("updateTitle")[1];
         let contents = document.getElementsByName("updateContents")[1];
         main.info.stuTitle = stuTitle.value;
         main.info.contents = contents.value;
         main.info.requestDate = requestDate;
         console.log(main.info);
         let clientData = JSON.stringify(this.info);
         if(stuTitle.value == "" || contents.value == "" || requestDate == ""){
            alert("제목 또는 내용을 입력해 주세요.");
         }else{
            postAjax('student/updateCos',clientData,'messages','json');
         }
      },
      dataMove:function(index){
         main.info.sjTitle = main.gradeList[index].sjTitle;
         main.info.startDate = moment(main.gradeList[index].startDate).format('YYYY-MM-DD');
         main.info.endDate = moment(main.gradeList[index].endDate).format('YYYY-MM-DD');
         main.info.contents = main.gradeList[index].contents;
         if(main.gradeList[index].file == null){
            main.info.file = '없음';
         }else{
            main.info.file = main.gradeList[index].file;
         }
         if(main.gradeList[index].ssCode.substring(0,1) == 'T'){
            main.changepage(2);
         }else if(main.gradeList[index].ssCode.substring(0,1) == 'Q'){
            main.changepage(3);
         }else{
            main.changepage(2);
         }
         main.info.ssName = main.gradeList[index].ssName;
      },
      logOut:function (id){
         let uCode = makeInput("hidden","mbId",id);
         let method = makeInput("hidden","method",-1);
         let pubIp = makeInput("hidden","publicIp",publicIp);
         let privateIp = makeInput("hidden","privateIp",location.host);
   
         let form = makeForm("logOut","post");
   
         form.appendChild(uCode);
         form.appendChild(method);
         form.appendChild(pubIp);
         form.appendChild(privateIp);
         
         document.body.appendChild(form);
         
         form.submit();
      }
   }   
});


function messages(message){
   if(message == true){
      alert("성공하였습니다.");
   }else{
      alert("실패하였습니다.");
   }
   let clientData = JSON.stringify(main.info);
   postAjax('student/selectGradeList',clientData,'getGradeListView','json');
   postAjax('student/selectSiList',clientData,'getSiListView','json');
   postAjax('student/selectCosList',clientData,'getCosListView','json');
}

function getListView(jsonData){
   main.info = jsonData;
}
function getGradeListView(jsonData){
   main.gradeList = jsonData;
   for(i=0; i<main.gradeList.length; i++){
   main.gradeList[i].createDate = main.gradeList[i].createDate.substring(0,4)+"-"+ main.gradeList[i].createDate.substring(4,6) +"-"+ main.gradeList[i].createDate.substring(6,8);
   }
}

function getSiListView(jsonData){
   main.siList = jsonData;
   for(i=0; i<main.siList.length; i++){
      main.siList[i].createDate = moment(main.siList[i].startDate).format('YYYY-MM-DD');
      if(main.siList[i].status == 'X'){
         main.siList[i].status = '결석';
      }else if(main.siList[i].status == 'L'){
         main.siList[i].status = '지각';
      }else if(main.siList[i].status == 'P'){
         main.siList[i].status = '조퇴';
      }else{
         main.siList[i].status = '출석';
      }   
   }
}

function getCosListView(jsonData){
   
   main.cosList = jsonData;
   for(i=0; i<main.cosList.length; i++){
   main.cosList[i].createDate = main.cosList[i].createDate.substring(0,4)+"-"+ main.cosList[i].createDate.substring(4,6) +"-"+ main.cosList[i].createDate.substring(6,8);
   main.cosList[i].requestDate = main.cosList[i].requestDate.substring(0,4)+"-"+ main.cosList[i].requestDate.substring(4,6) +"-"+ main.cosList[i].requestDate.substring(6,8);
   if(main.cosList[i].status == 'O'){
         main.cosList[i].status = '수락';
      }else if(main.cosList[i].status == 'W'){
         main.cosList[i].status = '대기';
      }else if(main.cosList[i].status == 'X'){
         main.cosList[i].status = '거절';
      }
   }
}