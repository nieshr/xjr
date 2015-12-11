<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<!--网页左上角小图标-->
<link rel="shortcut icon" href="/client/images/icon.ico" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>
科技小巨人-
	<#if mid??&&mid==12>
		专家资源
	<#else>
		<#if info_name.title??>
			${info_name.title!''}
		<#else>>	
			新闻详情
		</#if>	
	</#if>
</title>
<!-- css -->
<link rel="stylesheet" type="text/css" href="/client/css/base.css"/>
<link rel="stylesheet" type="text/css" href="/client/css/news.css"/>
<link rel="stylesheet" href="/client/css/news_base.css">
<link rel="stylesheet" href="/client/css/news_main.css">
<style>
.news_detail table,td,tr{border:1px #333 solid; }
.news_detail li{list-style-type:disc;}
</style>
<!-- js -->
	<script src="/client/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/client/js/main.js"></script>

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
	<img style="border:0;" src="/client/news_img/bglist.png"/>
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
    	<#if info_name.title??>
			>  ${info_name.title!''}
		</#if>	
    <#elseif mid??&&mid==10> <a href="/info/index"> 新闻动态</a>  > <a href="/info/list/${mid?c!''}?catId=${catId!'21'}">${info_name.title!''}</a>
    <#else>
	    <#if info_name.title == "行动概况">
	    	<a href="/info/aIn">${info_name.title!''}</a>
	    <#else>
	    	${info_name.title!''}
	    </#if>
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
    <div class="last2" style="float:right;margin: 0 10px 20px 0 ;">
	    <#if next_info??>
		    <a href="/info/list/content/${next_info.id?c}?mid=${next_info.menuId?c}" title="${next_info.title!''}">
		    	下一篇:<span>
		    					<#if next_info.title?length lt 24>
		    						${next_info.title!''}
		    					<#else>
		    						${next_info.title[0..23]}...
		    					</#if>	
		    				</span>
		    </a>
	    </#if>
    </div>
    <div class="last1" style="float:left;margin: 0 0 20px 10px ;">
	    <#if prev_info??>
		    <a href="/info/list/content/${prev_info.id?c}?mid=${prev_info.menuId?c}" title="${prev_info.title!''}">
			    上一篇:<span>
		    					<#if prev_info.title?length lt 24>
		    						${prev_info.title!''}
		    					<#else>
		    						${prev_info.title[0..23]}...
		    					</#if>							    	
						    </span>
		    </a>
	    </#if>
    </div>
  </div>
</div>

<!--foot-->
<!-- 底部 -->
<#include "/client/news_common_footer.ftl" />
<!-- 底部end -->

</body>
</html>
