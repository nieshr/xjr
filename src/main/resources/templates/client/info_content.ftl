<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7"/>
<title>公司简介</title>
<script type="text/javascript" src=/client/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/client/js/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="/client/css/base.css"/>
<link rel="stylesheet" type="text/css" href="/client/css/style.css"/>
</head>
<body>

<#include "/client/commen_header.ftl" />
<!--内容-->
<div class="wrapper content">
  <div class="content_1">
     <div class="content_1_1"></div>
     <div class="content_1_L">
       <ul>
          <#if navi_item_list??>
              <#list navi_item_list as bar>
                  <li ><a href="${bar.linkUri}" title="" ><strong> ${bar.title}</strong><br /></a></li>
              </#list>
          </#if>                 
       </ul>
     </div>
     <div class="content_1_3"></div>
  </div>
  <div class="content_2">
     <ul>         
         <li class="li_su"><strong><a href="/info/list/${menu_id}">${info_name.title!'' }</a></strong><b>您当前的位置：<a href="/" title="">首页</a> > <a href="/info/list/${menu_id}">${info_name.title!'' }</a></b></li>
     </ul>
		         <h2>${info.title!'' }</h2>
		         <p>&nbsp;</p>
		         <p>${info.content!'' }</p>
		         <p>&nbsp;</p>
		         <p>
		             <#if prev_info??>
		             <b style="float:left;"><a href="/info/content/${prev_info.id}?mid=${prev_info.menuId}">上一篇：${prev_info.title}</a></b>
		             </#if>
		             <#if next_info??>
		             <b style="float:right;"><a href="/info/content/${next_info.id}?mid=${next_info.menuId}">下一篇：${next_info.title}</a></b>
		             </#if>
		         </p>
  </div>
</div>
<!--底部-->
<div class="footer">
   <#include "/client/commen_footer.ftl" />
</div>
</body>
</html>
