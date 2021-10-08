const master = new Vue({
  el: '#master11',
  data: {
	display:[{show:false},{show:false},{show:false}]
    ,list:[] //the declared array
	,info:{}
  },
	methods:{
		changepage:function(page){
			for(i=0; i<this.display.length; i++){
				this.display[i].show = false;
			}
			this.display[page].show = true;
		},
		
	}
});

function typeConvert(type){
			if(type=='T'){
				master.changepage(0);
			}else if(type=='S'){
				master.changepage(1);
			}
		}
