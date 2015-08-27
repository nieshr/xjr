<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7"/>
<title><#if site??>${site.seoTitle!''}-</#if>${menu_name!''}</title>
<meta name="keywords" content="${site.seoKeywords!''}">
<meta name="description" content="${site.seoDescription!''}">
<meta name="copyright" content="${site.copyright!''}" />
<link href="/client/css/base.css" rel="stylesheet" type="text/css" />
<link href="/client/css/course.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="/client/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/client/js/info-list.js"></script> 
</head>
<body>
<!--header-->
<#include "/client/common_header.ftl" />
<!--header_end -->

<!--main-->
<div class="main">
    <!--left_content-->
       <#include "/client/common_menu.ftl" />  
    
    <!--right_content-->
    <div class="right_content">
        <div class="right_title">
    <a>首页&nbsp;&gt;&nbsp;</a>   
    <a>课程设置</a>
    <a><#if info_name??> > ${info_name.title!'' }</#if></a>
</div>    
<div class="right_crouse">
    <#if course_page??>
        <#list course_page.content as item> 
            <dl class="crouse">
                <dt><img src="${item.imgUrl!''}" /></dt>
                <dd>
                    <a href="javascript:courseTake(${item.id },${item.menuId })">${item.title!''}</a>
                    <#if item.content?length lt 400>
                        <p>${item.content!''}</p>
                    <#else>
                        <p>${item.content[0..400] }...</p>
                    </#if>
                </dd>
            </dl>
        </#list>
    </#if>        
</div>
<!--内容底部-->
<#assign PAGE_DATA=course_page />
<#if catId??>
    <#include "/client/list_footer.ftl" />
<#else>
    <#include "/client/list_footer_index.ftl" />
</#if>            
 <!--内容底部 end-->       
         
    </div>

</div>
<!--footer-->
<#include "/client/common_footer.ftl" />
<!--footer_end-->

</body>
</html>
