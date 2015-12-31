<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<!--网页左上角小图标-->
<link rel="shortcut icon" href="/client/images/icon.ico" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>科技小巨人<#if activityType??>-${activityType!''}</#if></title>
<link rel="stylesheet" type="text/css" href="/client/css/base.css"/>
<link rel="stylesheet" type="text/css" href="/client/css/news.css"/>
<link rel="stylesheet" href="/client/css/news_base.css">
<link rel="stylesheet" href="/client/css/news_main.css">
<script type="text/javascript" src="/client/js/jquery-1.9.1.min.js"></script>
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

<!--菜单导航-->
<div class="location_nav">
  <div class="location">
    <ul>
      <li <#if !activityType??>class="me"</#if>><a href="/info/activity/list">培育活动</a></li>
      <#if activityType_list??>
           <#list activityType_list as item>
                <li <#if activityType?? && activityType == item.title>class="me"</#if>><a href="/info/activity/list?activityType=${item.title!'' }">${item.title!''}</a></li>
           </#list>
      </#if>
    </ul>
  </div>
</div>

<!--各种新闻-->
<div class="news_b" style="min-height:300px;">
  <ul>
    <#if info_page??>
      <#list info_page.content as item>
            <li><a target="_blank"  href="<#if item.linkUrl??&&item.linkUrl?length gt 0>${item.linkUrl!''}<#else>/info/list/content/${item.id?c!''}?mid=${item.menuId!''}</#if>">
              <div class="pic"><img src="<#if item.imgUrl??&&item.imgUrl?length gt 0>${item.imgUrl!''}<#else>/client/news_img/modification.png</#if>" /></div>
              <div class="news_words">
                <div class="news_title">${item.title!''}</div>
                <#if item.timeAbleId??&&item.timeAbleId == 0><div class="news_time"><#if item.createTime??>${item.createTime?string("yyyy-MM-dd")}</#if></div><#else>筹备中</#if>
                <div class="news_detail">${item.brief!''}</div>
              </div>
              </a></li>
      </#list>
     </#if>  
  </ul>
</div>

<!--页码按钮-->
<#if info_page??>
<#assign PAGE_DATA=info_page />
<div class="pagnation" id="pagnation">
 <#if PAGE_DATA??>
     <#if PAGE_DATA.number+1 == 1>
          <a disabled="disabled"  class="page-prev">上一页"</a>              
     <#else>
         <a href="/info/activity/list?activityType=${activityType!'' }&page=${PAGE_DATA.number-1}"  class="page-prev">上一页"</a>                
     </#if>
     
     <#assign continueEnter=false>
     
     <#if PAGE_DATA.totalPages gt 0>
         <#list 1..PAGE_DATA.totalPages as page>
             <#if page <= 3 || (PAGE_DATA.totalPages-page) < 3 || (PAGE_DATA.number+1-page)?abs<3 >
                 <#if page == PAGE_DATA.number+1>
                     <a  class ="current">${page }</a>
                 <#else>
                     <a href="/info/activity/list?activityType=${activityType!'' }&page=${page-1}&keywords=${keywords!''}">${page}</a> 
                 </#if>
                 <#assign continueEnter=false>
             <#else>
                 <#if !continueEnter>
                     ...
                     <#assign continueEnter=true>
                 </#if>
             </#if>
         </#list>
     </#if>
     
     
     <#if PAGE_DATA.number+1 == PAGE_DATA.totalPages || PAGE_DATA.totalPages==0>
        <a disabled="disabled" class="page-next">下一页</a>
     <#else>
         <a href="/info/activity/list?activityType=${activityType!'' }&page=${PAGE_DATA.number+1}&keywords=${keywords!''}" class="page-next">下一页</a> 
     </#if>
 </#if>
    
</div>
  </#if>  


<!-- 底部 -->
<#include "/client/news_common_footer.ftl" />
<!-- 底部end -->


</body>
</html>
