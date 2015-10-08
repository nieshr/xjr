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
}