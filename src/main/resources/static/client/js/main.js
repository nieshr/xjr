$(function(){
	var onOff = true;
	//下拉菜单
	$("#nav_guide").click(function(){
		if(onOff){
		$("#porfile_subnav").show();
		}else{
		$("#porfile_subnav").hide();
			};
			onOff = !onOff;
		})
	$(".content").click(function(){
		$("#porfile_subnav").hide();
		})
    //左导航点击事件
    $(".nav dd").click(function(){
		$(".nav dd").removeClass("red")
		 x=$(".nav dd").index($(this));
		 $(".nav dd").eq(x).addClass("red")
		});
	
	//面包屑导航下边框
	$(".crumb dd a").click(function(){
		$(".crumb dd a").removeClass("crumb_bo")
		 x=$(".crumb dd a").index($(this));
		 $(".crumb dd a").eq(x).addClass("crumb_bo")
		});
	});
	


/*表格冻结*/
