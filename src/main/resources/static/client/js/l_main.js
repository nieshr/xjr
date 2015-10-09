window.onload=function(){
	
	var navul = document.getElementById('wrapper-nav');
	var lis = navul.getElementsByTagName('li');
		for(var i=0;i<lis.length;i++){
	    	lis[i].onmouseover = function(){
	    	for(var i=0;i<lis.length;i++){
	    		lis[i].className="";
	   		}
	       	this.className="active";
	    }
	}

	var titlelist = document.getElementById('titlelist');
		var oa = titlelist.getElementsByTagName('a');
		for(var i=0;i<oa.length;i++){
	    	oa[i].onmouseover = function(){
	    	for(var i=0;i<oa.length;i++){
	    		oa[i].className="";
	   		}
	       	this.className="active";
	    }
	}
	
}

