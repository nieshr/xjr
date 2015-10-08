// 轮播效果，向左
$(function(){
    var speed = -860;
    function scl(){
        $(".child").animate({marginLeft:speed+"px"},500);
            speed-=860;
            if(speed==-3440){
            speed=0;
            }
    }
    setInterval(scl,2000);
})
// 前后按钮
$(function(){
    var n=0;
    $(".next").click(function(){
    	window.clearInterval('scl()');	
        if(n>2){
            n=3;
        }else{
            n=n+1;
        }                              
        $(".child").animate({marginLeft:-860*n},500);

    })
    $(".prev").click(function(){
        if(n<2){
            n=0;
        }else{
            n=n-1;
        }      
        $(".child").animate({marginLeft:-860*n},500);
    }) 
})
//鼠标悬停清除定时器
