const main = new Vue({
  el: '#vue',
  data: {
   display:[{show:false},{show:false},{show:false}]
    ,list:[] //the declared array
   ,gradeList:[]
   ,siList:[]
   ,coList:[]
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
      getDetailsList:function(data){
         this.info = data;
           let clientData = JSON.stringify(data);
           postAjax('teacher/selectGradeList',clientData,'getGradeListView','json');
         postAjax('teacher/selectSiList',clientData,'getSiListView','json');
         postAjax('teacher/selectCoList',clientData,'getCoListView','json');
         postAjax('teacher/selectCosList',clientData,'getCosListView','json');
      },
      getTime:function(index){
         this.info.startDate = moment(main.siList[index].startDate).format('YYYY-MM-DD');
         main.info.createDate = main.list[index].createDate;
      },
      updateSign:function(){
         let status = $('input:radio[name=status]:checked').val();
         this.info.status = status;
         this.info.createDate = moment(this.info.createDate).format('YYYYMMDDHHmmss');  
         let clientData = JSON.stringify(this.info);
         
         postAjax('teacher/updateStuAtt',clientData,'messages','json');
      },
      getCounSeling:function(coCode,sjTitle,contents){
         this.info.coCode = coCode;
         this.info.sjTitle = sjTitle;
         this.info.contents = contents;
         let atitle = document.getElementsByName("sjTitle")[0];
         let acontents = document.getElementsByName("contents")[0];
         atitle.value = this.info.sjTitle;
         acontents.value = this.info.contents;
      },
      updateCounSeling:function(){
         let sjTitle = document.getElementsByName("sjTitle")[0];
         let contents = document.getElementsByName("contents")[0];
         this.info.sjTitle = sjTitle.value;
         this.info.contents = contents.value;
         let clientData = JSON.stringify(this.info);
         postAjax('teacher/updateStuCounSeling',clientData,'messages','json');
      },
      deleteCounSeling:function(){
         
         let clientData = JSON.stringify(this.info);
         postAjax('teacher/deleteStuCounSeling',clientData,'messages','json');
         
      },
      insertCounSeling:function(){
         let insTitle = document.getElementsByName("insTitle")[0];
         let insContents = document.getElementsByName("insContents")[0];
         this.info.sjTitle = insTitle.value;
         this.info.contents = insContents.value;
         let clientData = JSON.stringify(this.info);
         postAjax('teacher/insertStuCounSeling',clientData,'messages','json');
      },
      updateGrade:function(){
         let clientData = JSON.stringify(this.gradeList);
         postAjax('teacher/updateStuGrade',clientData,'messages','json');
      },
      redirect:function(){
         let clientData = JSON.stringify(this.info);
         postAjax('teacher/selectCoList',clientData,'getCoListView','json');
      },
      completeCos:function(index){
         main.info.stuTitle = main.cosList[index].stuTitle;
         main.info.contents = main.cosList[index].contents;   
         main.info.requestDate = main.cosList[index].requestDate;
         main.info.cosCode = main.cosList[index].cosCode;
      },
      yesOrNoCos:function(status){
         main.info.status = status;
         let clientData = JSON.stringify(this.info);
         postAjax('teacher/yesOrNoCos',clientData,'messages','json');      
      },
      resetContents:function(){         
         let atitle = document.getElementsByName("insTitle")[0];
         let acontents = document.getElementsByName("insContents")[0];
         atitle.value = "";
         acontents.value = "";
      },
 	  returnPage:function(){
        postAjax('teacher/allStuList',clientData,'getAllStuListView','json');
      },
   }
});

function messages(message){
   if(message == true){
      alert("성공하였습니다.");
   }else{
      alert("실패하였습니다.");
   }
   let clientData = JSON.stringify(main.info);
   
   postAjax('teacher/selectGradeList',clientData,'getGradeListView','json');
   postAjax('teacher/selectSiList',clientData,'getSiListView','json');
   postAjax('teacher/selectCoList',clientData,'getCoListView','json');
   postAjax('teacher/selectCosList',clientData,'getCosListView','json');
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
      main.list[i].createDate = main.siList[i].startDate;
      main.siList[i].startDate = moment(main.siList[i].startDate).format('YYYY-MM-DD');
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

function getCoListView(jsonData){
   main.coList = jsonData;
   for(i=0; i<main.coList.length; i++){
   main.coList[i].createDate = main.coList[i].createDate.substring(0,4)+"-"+ main.coList[i].createDate.substring(4,6) +"-"+ main.coList[i].createDate.substring(6,8);
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

function portal(value){
   let teId = document.getElementsByName("teId")[0];
   let csCode = document.getElementsByName("csCode")[0];
   let opCode = document.getElementsByName("opCode")[0];
   let mbType = document.getElementsByName("mbType")[0];      
   let f="";
   
  switch (value){
    case "1":
      f = makeForm('classStream','post');
      break;
    case "2":
      f = makeForm('rollBook','post');
      break;
    case "3":
      f = makeForm('lesson','post');
      break;
   case "4":
      f = makeForm('stuManage','post');
      break;
   case "5":
      f = makeForm('myPage','post');
      break;
    default:
      f = makeForm('main','post');
      break;
  }
   document.body.appendChild(f);
   
   if(mbType[0].checked == true){
      f.appendChild(mbType[0]);
   }else{
      f.appendChild(mbType[1]);
   }
   f.appendChild(teId);
   f.appendChild(csCode);   
   f.appendChild(opCode);
   
   f.submit();

  return answer;
}


// 166 수정
function getAllStuList(){
      let mbId = document.getElementsByName("mbId")[0];
       let csCode = document.getElementsByName("csCode")[0];
       let opCode = document.getElementsByName("opCode")[0];
   
     let sendJsonData={teId:mbId.value, csCode:csCode.value, opCode:opCode.value};
     let clientData = JSON.stringify(sendJsonData);
    
     postAjax('teacher/allStuList',clientData,'getAllStuListView','json');
}

function getAllStuListView(jsonData){
   main.changepage(0);
   main.list = jsonData;
   }

