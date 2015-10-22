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


//添加筛选企业
function addEnterprise(id,activityId)
{
    $.ajax({
        type:"post",
        url:"/activity/addEnterprise",
        data:{"id":id,"activityId":activityId},
        success:function(data){
            $("#selectedEnterprise").html(data);
            location.reload();
          
        }
    });
}

//取消筛选企业
function removeEnterprise(id,activityId)
{
    $.ajax({
        type:"post",
        url:"/activity/removeEnterprise",
        data:{"id":id,"activityId":activityId},
        success:function(data){
            $("#selectedEnterprise").html(data);
        }
    });
}


////添加筛选专家
//function addExpert(id,activityId)
//{
//    $.ajax({
//        type:"post",
//        url:"/activity/addExpert",
//        data:{"id":id,"activityId":activityId},
//        success:function(data){
//            $("#selectedExpert").html(data);
//            location.reload();
//        }
//    });
//}

//取消筛选专家
function removeExpert(id,activityId)
{
    $.ajax({
        type:"post",
        url:"/activity/removeExpert",
        data:{"id":id,"activityId":activityId},
        success:function(data){
        	if(data.code == 0)
        		{
        			location.reload();
        		}
        	else{
        		alert(data.msg);
        	}
        }
    });
}

//添加预选企业
function candidateAddEnterprise(id,activityId)
{
	
  $.ajax({
      type:"post",
      url:"/region/candidateAddEnterprise",
      data:{"id":id,"activityId":activityId},
      success:function(data){
    	  if (data.code == 0)
    		  {
    		  	alert("sucess");
    		  	location.reload();
    		  }
    	  else
    		  {
    		  	alert(date.msg);
    		  }
      }
  });
}


//添加预选企业
function addEnterprise1(id,activityId,statusId)
{
	var reason = $("#reason"+id).val();
	
  $.ajax({
      type:"post",
      url:"/region/addEnterprise",
      data:{"id":id,"activityId":activityId,"statusId":statusId,"reason":reason},
      success:function(data){
          $("#selectedEnterprise").html(data);
          location.reload();
      }
  });
}

//取消预选企业
function removeEnterprise1(id,activityId,statusId)
{
  $.ajax({
      type:"post",
      url:"/region/removeEnterprise",
      data:{"id":id,"activityId":activityId,"statusId":statusId},
      success:function(data){
          $("#selectedEnterprise").html(data);
          location.reload();
      }
  });
}