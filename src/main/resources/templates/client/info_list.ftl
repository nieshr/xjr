<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7"/>
<title>相关常识</title>
<script type="text/javascript" src="/client/js/jquery-1.9.1.min.js"></script>
<link rel="stylesheet" type="text/css" href="/client/css/base.css"/>
<link rel="stylesheet" type="text/css" href="/client/css/style.css"/>
<script>
//选择栏目 zhangji
function selectCat(mid,catId)
{ 
	$(".menu_list").removeClass("li_nav");
	$("#list"+catId).addClass("li_nav");
	
    $.ajax({
        type:"post",
        url:"/info/list/select",
        data:{"mid":mid,"catId":catId},
        success:function(data){            	
            $("#info_list_detail").html(data);
        }       
    });
}
</script>
<script>
//翻页 zhangji
function page(mid,catId,eventTarget, eventArgument)
{ 
    $(".menu_list").removeClass("li_nav");
    $("#list"+catId).addClass("li_nav");
    
    $.ajax({
        type:"post",
        url:"/info/list/select",
        data:{"mid":mid,
        	  "catId":catId,
        	  "__EVENTTARGET":eventTarget,
        	  "__EVENTARGUMENT":eventArgument},
        success:function(data){             
            $("#info_list_detail").html(data);
        }       
    });
}
//[全部]分类翻页
function page2(mid,eventTarget, eventArgument)
{   
    $.ajax({
        type:"post",
        url:"/info/list/select",
        data:{"mid":mid,
              "__EVENTTARGET":eventTarget,
              "__EVENTARGUMENT":eventArgument},
        success:function(data){             
            $("#info_list_detail").html(data);
        }       
    });
}
</script>

</head>

<body>
<form name="form1" method="post" action="/info/list/8" id="form1">
<div>
<input type="hidden" name="__EVENTTARGET" id="__EVENTTARGET" value="${__EVENTTARGET!""}">
<input type="hidden" name="__EVENTARGUMENT" id="__EVENTARGUMENT" value="${__EVENTARGUMENT!""}">
<input type="hidden" name="__VIEWSTATE" id="__VIEWSTATE" value="${__VIEWSTATE!""}" >
</div>

<script type="text/javascript">
var theForm = document.forms['form1'];
    if (!theForm) {
        theForm = document.form1;
    }
    function __doPostBack(eventTarget, eventArgument) {
        if (!theForm.onsubmit || (theForm.onsubmit() != false)) {
            theForm.__EVENTTARGET.value = eventTarget;
            theForm.__EVENTARGUMENT.value = eventArgument;
            theForm.submit();
        }
    }
</script>
<#include "/client/commen_header.ftl" />
<!--内容-->
<div class="wrapper content">
    <div class="content_1">
        <div class="content_1_1"></div>
        <div class="content_1_2">
	       <ul>
		        <li class="li_su"><strong>${menu_name }</strong><br /><b>${menu_sub_name }</b></li>
		        <li ><a class="menu_list" id="list0" href="javascript:selectCat(${menu_id},0);" title="">全部</a></li>
		        <#if info_category_list??>
		            <#list info_category_list as item>
		                <li>		                    
		                     <a class="menu_list" id="list${item.id}" href="javascript:selectCat(${item.menuId},${item.id});" title="">${item.title!'' }</a>
		                </li>
		            </#list>
		        </#if>   
	       </ul>
        </div>
        <div class="content_1_3"></div>
    </div>
	<div class="content_2" id="info_list_detail">
         <#include "/client/info_list_detail.ftl" />
	</div>
</div>
<!--底部-->
<div class="footer">
    <#include "/client/commen_footer.ftl" />
</div>
</form>
</body>
</html>
