<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<!--网页左上角小图标-->
<link rel="shortcut icon" href="/client/images/icon.ico" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>科技小巨人-培育活动</title>
<!-- css -->
<link rel="stylesheet" type="text/css" href="/client/css/base.css"/>
<link rel="stylesheet" type="text/css" href="/client/css/news.css"/>
	<link rel="stylesheet" href="/client/css/news_base.css">
	<link rel="stylesheet" href="/client/css/news_main.css">
<link rel="stylesheet" type="text/css" href="/client/css/5icool.css"/>

<script type="text/javascript" src="/client/js/act_jquery.js"></script>
<script type="text/javascript" src="/client/js/5icool.js"></script>
<script type="text/javascript" src="/client/js/jquery-1.9.1.min.js"></script>
<script src="/client/js/l_main.js"></script>
<style type="">
div{ margin:0 auto;text-align:left;}
</style>
</head>

<body>
<!-- 导航 -->
<!-- head  -->
	<div class="wrapper">
		<ul class="wrapper-nav" id="wrapper-nav">
			<li><a href="/">首页</a></li>
			<li <#if active??&&active==1>class="active"</#if>><a href="/info/aIn">专项行动</a></li>
			<li <#if active??&&active==2>class="active"</#if>><a href="/info/activity/list">培育活动</a></li>
			<li <#if active??&&active==3>class="active"</#if>><a href="/info/index">新闻动态</a></li>
			<li <#if active??&&active==4>class="active"</#if>><a href="/info/projectshow">企业项目</a></li>
			<li <#if active??&&active==5>class="active"</#if>><a href="/info/resource">专家资源</a></li>
			<li <#if active??&&active==6>class="active"</#if>><a href="/info/host">合作机构</a></li>
			<li <#if active??&&active==7>class="active"</#if>><a href="/info/contact">联系方式</a></li>
		</ul>
                <#if username??>
                    <div class="logoin"><a href="/user" style="color:#ff4040;">${username}</a><span>|</span><a href="/logout">退出</a></div>
                <#else>
                    <div class="logoin"><a href="/login">登录</a><span>|</span><a href="/reg">注册</a></div>
                </#if>
	</div>
<!-- 导航end -->
<!-- banner -->
<div class="listbanner">
	<img src="/client/news_img/bglist.png"/>
	<div><span>关注成长，创领未来</span></div>
</div>
<!-- bannerend -->
    <!--右侧浮动导航开始-->
    <script>
function gotop()
{
    $('html,body').animate({scrollTop:0},400);
}
</script>
    <div class="floatbox">
      <a  href="/user" target="_blank" title="申报入口">
        <img src="/client/l_images/float_ico02.png" width="42" height="42" alt="申报入口">
      </a>
      <a href="/info/search" title="搜索" target="_blank" rel="nofollow">
        <img src="/client/l_images/float_ico01.png" width="42" height="42" alt="搜索">
      </a>
      <a id="BizQQWPA" href="http://wpa.qq.com/msgrd?v=3&uin=<#if site??>${site.qq1}</#if>&site=qq&menu=yes" target="_blank" title="在线客服">
        <img src="/client/l_images/float_ico06.png" width="42" height="42" alt="在线客服">
      </a>
      <a href="javascript:loginWinOpen('weixin_win','myselfbox',200);" title="官方微信">
        <img src="/client/l_images/float_ico03.png" width="42" height="42" alt="官方微信">
        <span class="qr" style="top:0;"><img src="<#if site??>${site.wxQrCode!''}</#if>"></span>
      </a>
      
      <a title="客服电话">
        <img src="/client/l_images/float_ico04.png" width="42" height="42" alt="客服电话">
        <span class="phone_num" style="top:0;"><#if site??>${site.telephone!''}</#if></span>
      </a>
      <a href="javascript:gotop();" title="到顶部">
        <img src="/client/l_images/float_ico05.png" width="42" height="42" alt="到顶部">
      </a>
    </div>
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
							<#assign year = activity.createTime?string("yyyy")>
						</#if>
						<div class="history-date">
							<ul>
							<#if activity_index == 0>
								<#if activity.createTime??>
									<h2 style="position: relative;" class="first">
										<a href="#nogo">${activity.createTime?string("yyyy")}年</a>
									</h2>
									<li style="margin-top: 0px;" class="<#if activity.sortId == 2 >bounceInDown<#else>green bounceInDown lcy_height</#if>">
										<#if activity.sortId == 2 >
											<h3 style="display: block;">${activity.createTime?string("MM.dd")}<span>${activity.createTime?string("yyyy")}</span></h3>
										</#if>
										<dl style="display: block;">
											<dt><p><a href="/info/list/content/${activity.id?c!''}?mid=13" title="查看详情">${activity.title!''}</a></p><span>${activity.brief!''}</span></dt>
										  <a style="margin-right:5%;float:right;" href="<#if activity.linkUrl??&&activity.linkUrl!="">${activity.linkUrl!''}<#else>/info/list/content/${activity.id?c!''}?mid=13</#if>" title="查看详情">详情</a>
										</dl>
									</li>
								</#if>
							<#else>
								<#if activity.createTime?string("yyyy") != year>
									<#assign year = activity.createTime?string("yyyy")>
									</ul>
								</div>
								<div class="history-date">
									<ul>
									<h2 style="position: relative;" class="date02 bounceInDown">
										<a href="#nogo">${activity.createTime?string("yyyy")}年</a>
									</h2>
								</#if>	
									<li style="margin-top: 0px;" class="<#if activity.sortId == 2 >bounceInDown<#else>green bounceInDown lcy_height</#if>">
										<h3 style="display: block;">${activity.createTime?string("MM.dd")}<span>${activity.createTime?string("yyyy")}</span></h3>
										<dl style="display: block;">
											<dt><a href="<#if activity.linkUrl??&&activity.linkUrl!="">${activity.linkUrl!''}<#else>/info/list/content/${activity.id?c!''}?mid=13</#if>" title=""><p>${activity.title!''}</p><span>${activity.theme!''}</span></a></dt>
										  <a style="margin-right:5%;float:right;" href="<#if activity.linkUrl??&&activity.linkUrl!="">${activity.linkUrl!''}<#else>/info/list/content/${activity.id?c!''}?mid=13</#if>" title="查看详情">详情</a>
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