<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7"/>
<title><#if site??>${site.seoTitle!''}-</#if>相关常识</title>
<meta name="keywords" content="${site.seoKeywords!''}">
<meta name="description" content="${site.seoDescription!''}">
<meta name="copyright" content="${site.copyright!''}" />
<script type="text/javascript" src="/client/js/jquery-1.9.1.min.js"></script>
<link rel="stylesheet" type="text/css" href="/client/css/base.css"/>
<link rel="stylesheet" type="text/css" href="/client/css/style.css"/>
<script>
//选择栏目 zhangji
function selectContent(id,mid)
{ 
    $(".menu_list").removeClass("li_nav");
    $("#list"+id).addClass("li_nav");
    
    $.ajax({
        type:"post",
        url:"/info/service/select",
        data:{"id":id,"mid":mid},
        success:function(data){             
            $("#info_service_content_detail").html(data);
        }       
    });
}
</script>
</head>

<body>
<#include "/client/commen_header.ftl" />
<!--内容-->
<div class="wrapper content">
    <div class="content_1">
        <div class="content_1_1"></div>
        <div class="content_1_2">
           <ul>
                <li class="li_su"><strong>${info_name.title }</strong><br /><b>${info_name.content!'' }</b></li>               
                <#if info_page??>
                    <#list info_page.content as item>      
                        <li>                            
                             <a class="menu_list" id="list${item.id}" href="javascript:selectContent(${item.id},${item.menuId});" title="">${item.title!'' }</a>
                        </li>
                    </#list>
                </#if>   
           </ul>
        </div>
        <div class="content_1_3"></div>
    </div>
    <div class="content_2" id="info_service_content_detail">
         <#include "/client/info_service_content_detail.ftl" />
    </div>
</div>
<!--底部-->
<div class="footer">
    <#include "/client/commen_footer.ftl" />
</div>
</body>
</html>
