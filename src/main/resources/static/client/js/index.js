// JavaScript Document
$(function(){
	$(".parent>ul>li").on("mouseover mouseout",function(event){
		if(event.type=="mouseover"){
			$(".parent>ul>li").removeClass("nav_choiced");	
			$(this).addClass("nav_choiced");
			$(this).find(".child").show();
		}
		if(event.type=="mouseout"){
			$(this).find(".child").hide();
		}
	});	
	$(".child>ul>li").on("mouseover mouseout",function(event){
		if(event.type=="mouseover"){
			$(".parent>ul>li").removeClass("nav_choiced");	
			$(this).addClass("nav_choiced");
		}
	});	
});