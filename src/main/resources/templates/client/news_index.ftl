<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<!--网页左上角小图标-->
<link rel="shortcut icon" href="/client/images/icon.ico" />
<#--<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />-->
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>科技小巨人-新闻动态</title>
<!-- css -->
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
	<img style="*+border:0;" src="/client/news_img/bglist.png"/>
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
      <li class="me"><a href="javascript:void(0)">新闻动态</a></li>
      <#if newsCat_list??>
           <#list newsCat_list as item>
                <li><a title="查看分类列表"  href="<#if item.linkUrl??&&item.linkUrl?length gt 0>${item.linkUrl!''}<#else>/info/list/10?catId=${item.id?c}</#if>">${item.title!''}</a></li>
           </#list>
      </#if>
    </ul>
  </div>
</div>

<!--新闻动态-->
<div class="newsBox">

  <div class="sect1">
    <div class="div1">
      <h3>
       <#if newsCat_list??>
           <#list newsCat_list as item>
           		<#if item_index == 1>
                	<li><a title="查看分类列表"  href="<#if item.linkUrl??&&item.linkUrl?length gt 0>${item.linkUrl!''}<#else>/info/list/10?catId=${item.id?c}</#if>">${item.title!''}</a></li>
           		</#if>
           </#list>
      </#if>     
      </h3>
      <div class="ggBox">
      <!--Luara图片切换骨架begin-->
      <div class="addWrap">
          <div class="swipe" id="mySwipe">
              <div class="swipe-wrap">
                <#if activity_page??>
                    <#list activity_page.content as item>
                        <#if item_index < 5>
                            <div><a target="_blank" href="<#if item.linkUrl??&&item.linkUrl?length gt 0>${item.linkUrl!''}<#else>/info/list/content/${item.id?c}?mid=${item.menuId?c}</#if>"><img class="img-responsive" src="${item.imgUrl!''}"/>
                            <span><#if item.title?length lt 36>
                                ${item.title!''}
                            <#else>
                                ${item.title[0..35]?default("")}...
                            </#if></span></a></div> 
                        </#if>  
                    </#list>            
                </#if>
              </div>
        </div>
        <ul id="position">
          <li class="cur"></li>
          <li class=""></li>
          <li class=""></li>
          <li class=""></li>
          <li class=""></li>
        </ul>
    </div>
    <script src="/client/js/swipe.js"></script> 
      <script type="text/javascript">
      var bullets = document.getElementById('position').getElementsByTagName('li');
      var banner = Swipe(document.getElementById('mySwipe'), {
          auto: 2000,
          continuous: true,
          disableScroll:false,
          callback: function(pos) {
              var i = bullets.length;
              while (i--) {
                bullets[i].className = ' ';
              }
              bullets[pos].className = 'cur';
          }
      });
      </script>
      </div>

      <div class="ggBox2">
      <#--
      <#if activity_page??>
           <#list activity_page.content as item>
                 <#if item_index gt 2 && item_index < 6>
                     <div class="div1"><a href="<#if item.linkUrl??&&item.linkUrl?length gt 0>${item.linkUrl!''}<#else>/info/list/content/${item.id?c}?mid=${item.menuId?c}</#if>"><img src="${item.imgUrl!''}" alt="新闻图片" width="122" height="92"/></a>
                     <span><#if item.title?length lt 18>
                                ${item.title!''}
                            <#else>
                                ${item.title[0..17]?default("")}...
                            </#if></span></div>
                 </#if>
           </#list>
      </#if>      
      -->         
      
        <#if activity_page??>
            <#list activity_page.content as item>
                <#if item_index gt 4 && item_index lt 8>
                     <div class="div1">
	                     <a href="<#if item.linkUrl??&&item.linkUrl?length gt 0>${item.linkUrl!''}<#else>/info/list/content/${item.id?c}?mid=${item.menuId?c}</#if>">
	                     	<img src="${item.imgUrl!''}" title="${item.title!''}"alt="新闻图片" width="122" height="92"/>
	                     </a>
                     </div>
                </#if>  
            </#list>            
        </#if>
      </div>

    </div>
    <div class="div1">
      <h3>
       <#if newsCat_list??>
           <#list newsCat_list as item>
           		<#if item_index == 2>
                	<li><a   title="查看分类列表" href="<#if item.linkUrl??&&item.linkUrl?length gt 0>${item.linkUrl!''}<#else>/info/list/10?catId=${item.id?c}</#if>">${item.title!''}</a></li>
           		</#if>
           </#list>
      </#if>  
      </h3>
      <dl>
      <#if media_page??>
          <#list media_page.content as item>
                <#if item_index < 4>
                     <dd>
                     <a target="_blank" class="left-pic" href="<#if item.linkUrl??&&item.linkUrl?length gt 0>${item.linkUrl!''}<#else>/info/list/content/${item.id?c}?mid=${item.menuId?c}</#if>">
                        <img src="${item.imgUrl!''}" width="195" height="128" alt="新闻图片" style="border: #666 1px solid;"/>
                      </a>
                      <div class="mess">
                        <p class="p1">
                        <a target="_blank"  href="<#if item.linkUrl??&&item.linkUrl?length gt 0>${item.linkUrl!''}<#else>/info/list/content/${item.id?c}?mid=${item.menuId?c}</#if>">
                        	<#if item.title?length lt 40>
                                ${item.title!''}
                            <#else>
                                ${item.title[0..39]?default("")}...
                            </#if>
                       </a>    
                            </p>
                        <p class="p2">${item.brief!''}</p>
                        
                        <p class="p3">
                          <span class="span1">${item.source!''}</span>
                          <span class="span2">${item.createTime?string("yyyy-MM-dd")}</span>
                          <span class="span3">阅读（<b>${item.viewCount!'0'}</b>）</span>
                        </p>
                       
                      </div>
                    </a></dd>
                </#if>
          </#list>
      </#if>        
      </dl>      
    </div>
    <div class="div1">
      <h3>
       <#if newsCat_list??>
           <#list newsCat_list as item>
           		<#if item_index == 4>
                	<li><a  title="查看分类列表" href="<#if item.linkUrl??&&item.linkUrl?length gt 0>${item.linkUrl!''}<#else>/info/list/10?catId=${item.id?c}</#if>">${item.title!''}</a></li>
           		</#if>
           </#list>
      </#if>        
      </h3>
      <dl>
       <#if hot_page??>
          <#list hot_page.content as item>
                <#if item_index < 3>
                     <dd>
                     <a target="_blank" class="left-pic" href="<#if item.linkUrl??&&item.linkUrl?length gt 0>${item.linkUrl!''}<#else>/info/list/content/${item.id?c}?mid=${item.menuId?c}</#if>">
                        <img src="${item.imgUrl!''}" width="195" height="128" alt="新闻图片" />
                      </a>
                      <div class="mess">
                        <p class="p1">
                        <a target="_blank"  href="<#if item.linkUrl??&&item.linkUrl?length gt 0>${item.linkUrl!''}<#else>/info/list/content/${item.id?c}?mid=${item.menuId?c}</#if>">	
                        	<#if item.title?length lt 20>
                                ${item.title!''}
                            <#else>
                                ${item.title[0..19]?default("")}...
                            </#if>
                        </a>    
                        </p>
                        <p class="p2">${item.brief!''}</p>
                        <p class="p3">
                          <span class="span1">${item.source!''}</span>
                          <span class="span2">${item.createTime?string("yyyy-MM-dd")}</span>
                          <span class="span3">阅读（<b>${item.viewCount!'0'}</b>）</span>

                        </p>
                      </div>
                    </a></dd>
                </#if>
                <#if item_index gt 2 && item_index < 4>
                    <dt><a target="_blank" href="<#if item.linkUrl??&&item.linkUrl?length gt 0>${item.linkUrl!''}<#else>/info/list/content/${item.id?c}?mid=${item.menuId?c}</#if>">
                      <img src="${item.imgUrl!''}" width="334" height="204" alt="新闻图片" />
                      <div class="mess">
                        <p class="p1"><#if item.title?length lt 20>
                                ${item.title!''}
                            <#else>
                                ${item.title[0..19]?default("")}...
                            </#if></p>
                        <p class="p3">
                          <span class="span1">${item.source!''}</span>
                          <span class="span2">${item.createTime?string("yyyy-MM-dd")}</span>
                          <span class="span3">阅读（<b>${item.viewCount!'0'}</b>）</span>

                        </p>
                        <p class="p2">${item.brief!''}</p>
                      </div>
                    </a></dt>
                </#if>
          </#list>
      </#if>        
      </dl>
    </div>
  </div>

  <!-- 媒体报道、数据公布 -->
  <div class="sect2">
  <div class="div2">
      <h3 class="h3_right">
        <#if newsCat_list??>
           <#list newsCat_list as item>
           		<#if item_index == 0>
                	<li><a  title="查看分类列表" href="<#if item.linkUrl??&&item.linkUrl?length gt 0>${item.linkUrl!''}<#else>/info/list/10?catId=${item.id?c}</#if>">${item.title!''}</a></li>
           		</#if>
           </#list>
      </#if>       
      </h3>
      <div class="sect1_right">
      <#if notice_page??>
           <#list notice_page.content as item>
                <#if item_index  lt 5>
                    <p style="height:auto;"><a target="_blank" title="${item.title!''}"  href="<#if item.linkUrl??&&item.linkUrl?length gt 0>${item.linkUrl!''}<#else>/info/list/content/${item.id?c}?mid=${item.menuId?c}</#if>"><i class="i1"></i>
                        <span style="height:auto;">
                                ${item.title!''} 
                           </span></a></p>
                </#if>
           </#list>
      </#if>
      </div>
    </div>   
    <div class="div2">
      <h3 class="h3_right">
       <#if newsCat_list??>
           <#list newsCat_list as item>
           		<#if item_index ==3>
                	<li><a  title="查看分类列表" href="<#if item.linkUrl??&&item.linkUrl?length gt 0>${item.linkUrl!''}<#else>/info/list/10?catId=${item.id?c}</#if>">${item.title!''}</a></li>
           		</#if>
           </#list>
      </#if>        
      </h3>
      <ul>
      <#if data_page??>
          <#list data_page.content as item>
                <#if item_index lt 5>
                    <li><a target="_blank" title="${item.title!''}" href="<#if item.linkUrl??&&item.linkUrl?length gt 0>${item.linkUrl!''}<#else>/info/list/content/${item.id?c}?mid=${item.menuId?c}</#if>">
                      <#--<img src="${item.imgUrl!''}" width="280" height="158" alt="新闻图片" />-->
                      <p style="height:auto;">${item.title!''}</p>
                    </a></li>
                </#if>
          </#list>
      </#if>               
      </ul>
    </div>
    
     <div class="div2">
      <h3 class="h3_right">
       <#if newsCat_list??>
           <#list newsCat_list as item>
           		<#if item_index == 5>
                	<li><a  title="查看分类列表" href="<#if item.linkUrl??&&item.linkUrl?length gt 0>${item.linkUrl!''}<#else>/info/list/10?catId=${item.id?c}</#if>">${item.title!''}</a></li>
           		</#if>
           </#list>
      </#if>        
      </h3>
      <ol>
      <#if SYB_page??>
          <#list SYB_page.content as item>
              <#if item_index < 8>
                   <li><a target="_blank" href="<#if item.linkUrl??&&item.linkUrl?length gt 0>${item.linkUrl!''}<#else>/info/list/content/${item.id?c}?mid=${item.menuId?c}</#if>">
                      <img src="${item.imgUrl!''}"  width="66" height="56" alt="新闻图片" />
                      <p>
                        <span class="span1"><#if item.title?length lt 17>
                                ${item.title!''}
                            <#else>
                                ${item.title[0..17]?default("")}...
                            </#if></span>
                        <span class="span2">${item.brief!''}</span>
                      </p>
                    </a></li>
               </#if>
          </#list>
      </#if>        
      </ol>
    </div>
  </div>
  <!-- 媒体报道、数据公布 End-->

  <!-- 热点追踪、创业风向 -->
  <!-- 热点追踪、创业风向 End-->
<!-- JiaThis Button BEGIN -->
<script type="text/javascript" >
var jiathis_config={
	url:"http://www.cqkjxjr.com/info/index",
	summary:"关注成长，创领未来。重庆培育千家众创空间 千家科技型小巨人...",
	title:"科技小巨人-新闻动态 ##",
	marginTop:272,
	showClose:true,
	shortUrl:false,
	hideMore:false
}
</script>
<script type="text/javascript" src="http://v3.jiathis.com/code/jiathis_r.js?type=left&btn=l4.gif&move=0" charset="utf-8"></script>
<!-- JiaThis Button END -->





  

</div>
<!--新闻动态 End-->

<!-- 底部 -->
<#include "/client/news_common_footer.ftl" />
<!-- 底部end -->

</body>
</html>
