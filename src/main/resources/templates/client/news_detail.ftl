<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<!--网页左上角小图标-->
<link rel="shortcut icon" href="/client/images/icon.ico" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>
	<#if mid??&&mid==12>
		专家资源
	<#else>
		<#if info_name.title??>
			${info_name.title!''}
		<#else>>	
			科技小巨人-新闻详情
		</#if>	
	</#if>
</title>
<!-- css -->
<link rel="stylesheet" type="text/css" href="/client/css/base.css"/>
<link rel="stylesheet" type="text/css" href="/client/css/news.css"/>
<link rel="stylesheet" href="/client/css/news_base.css">
<link rel="stylesheet" href="/client/css/news_main.css">
<!-- js -->
	<script src="/client/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/client/js/main.js"></script>

</head>

<body>
<!-- 导航 -->
<!-- head  -->
<#include "/client/news_common_header.ftl" />
<!-- head end -->

<!--位置-->
<div class="location_back">
  <div class="location">
    <div class="location1"><img src="/client/images/luyi2.png" /></div>
    <div class="location2">
    <a href="/">首页</a> >
    <#if mid??&&mid==12>
    	<a href="/info/resource/${catId}?mid=${mid}">投资机构</a>
    <#elseif mid??&&mid==11>
    	<a href="/info/projectshow">企业项目</a>
    <#elseif mid??&&mid==10> <a href="/info/index"> 新闻动态</a>  > <a href="/info/list/${mid?c!''}?catId=${catId!'21'}">${info_name.title!''}</a>
    <#else>
    ${info_name.title!''}
    </#if>
    </div>
  </div>
</div>

<!--新闻题目-->
<div class="news_detail">
  <div class="news_name">${info.title!''}</div>
  <#if mid??&&mid==10>
  	<div class="news_writer">作者：<span>${info.source!''}</span> <span>${info.createTime?string("yyyy-MM-dd")}</span> </div>
  </#if>
  <div class="news_words">
    <div class="words1">${info.content!''}</div>
  </div>
  <div class="news_last">
    <div class="last2"><#if next_info??><a href="/info/list/content/${next_info.id?c}?mid=${next_info.menuId?c}">下一篇:<span>${next_info.title!''}</span></a></#if></div>
    <div class="last1"><#if prev_info??><a href="/info/list/content/${prev_info.id?c}?mid=${prev_info.menuId?c}">上一篇:<span>${prev_info.title!''}</span></a></#if></div>
  </div>
</div>

<!--foot-->
<!-- 底部 -->
<#include "/client/news_common_footer.ftl" />
<!-- 底部end -->

</body>
</html>
