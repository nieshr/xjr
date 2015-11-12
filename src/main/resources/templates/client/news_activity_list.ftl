<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<!--网页左上角小图标-->
<link rel="shortcut icon" href="/client/images/icon.ico" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>科技小巨人-专项行动</title>
<!-- css -->
<link rel="stylesheet" type="text/css" href="/client/css/base.css"/>
<link rel="stylesheet" type="text/css" href="/client/css/news.css"/>
<link rel="stylesheet" href="/client/css/l_base.css">
<link rel="stylesheet" href="/client/css/l_main.css">
<link rel="stylesheet" type="text/css" href="/client/css/5icool.css"/>

<script type="text/javascript" src="/client/js/act_jquery.js"></script>
<script type="text/javascript" src="/client/js/5icool.js"></script>
<script type="text/javascript" src="/client/js/jquery-1.9.1.min.js"></script>
</head>

<body>
<!-- 导航 -->
<!-- head  -->
    <#include "/client/news_common_header.ftl" />
<!-- head end -->

<!--菜单导航-->
<div class="location_nav">
  <div class="location">
    <ul>
      <li <#if !activityType??>class="me"</#if>><a href="/info/activity/list">专项行动</a></li>
      <#if activityType_list??>
           <#list activityType_list as item>
                <li <#if activityType?? && activityType == item.title>class="me"</#if>><a href="/info/activity/list?activityType=${item.title!'' }">${item.title!''}</a></li>
           </#list>
      </#if>
    </ul>
  </div>
</div>

<div class="demo">

	<div style="/*height: 2592px; */display: block;" class="history">


				<#if activity_list??>
					<#list activity_list as activity>
						<#if activity_index ==0>
							<#assign year = activity.date?string("yyyy")>
						</#if>
						<div class="history-date">
							<ul>
							<#if activity_index == 0>
								<#if activity.date??>
									<h2 style="position: relative;" class="first">
										<a href="#nogo">${activity.date?string("yyyy")}年</a>
									</h2>
									<li style="margin-top: 0px;" class="<#if activity.statusId == 2 >bounceInDown<#else>green bounceInDown lcy_height</#if>">
										<h3 style="display: block;">${activity.date?string("MM.dd")}<span>${activity.date?string("yyyy")}</span></h3>
										<dl style="display: block;">
											<dt><p><a href="/info/activity/detail/${activity.id?c!''}" title="查看详情">${activity.title!''}</a></p><span>${activity.theme!''}</span></dt>
										  <a style="margin-right:5%;float:right;" href="/info/activity/detail/${activity.id?c!''}" title="查看详情">详情</a>
										</dl>
									</li>
								</#if>
							<#else>
								<#if activity.date?string("yyyy") != year>
									<#assign year = activity.date?string("yyyy")>
									</ul>
								</div>
								<div class="history-date">
									<ul>
									<h2 style="position: relative;" class="date02 bounceInDown">
										<a href="#nogo">${activity.date?string("yyyy")}年</a>
									</h2>
								</#if>	
									<li style="margin-top: 0px;" class="<#if activity.statusId == 2 >bounceInDown<#else>green bounceInDown lcy_height</#if>">
										<h3 style="display: block;">${activity.date?string("MM.dd")}<span>${activity.date?string("yyyy")}</span></h3>
										<dl style="display: block;">
											<dt><a href="/info/activity/detail/${activity.id?c!''}" title=""><p>${activity.title!''}</p><span>${activity.theme!''}</span></a></dt>
										  <a style="margin-right:5%;float:right;" href="/info/activity/detail/${activity.id?c!''}" title="查看详情">详情</a>
										</dl>
									</li>
									</ul>
									</div>
							</#if>		
					</#list>
				</#if>			

	</div>

</div>
</div>


<div style="width:1200px; height:200px;">&nbsp;</div>
<#include "/client/news_common_footer.ftl" />





</body></html>