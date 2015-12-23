<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<!--网页左上角小图标-->
	
<#--<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />-->
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1">
<#if site??>
<meta name="keywords" content="${site.seoKeywords!''}">
<meta name="description" content="${site.seoDescription!''}">
<meta name="copyright" content="${site.copyright!''}" />
</#if>
<title>科技小巨人-首页</title>
<!--css-->
<link rel="shortcut icon" href="/client/images/icon.ico" />
<link href="/client/css/l_base.css" rel="stylesheet" type="text/css" />
<link href="/client/css/index.css" rel="stylesheet" type="text/css" />
<style>
#example_video_1{float:left;*+text-align:left;}
</style>
	

  <#-- 手机网页播放器-->


  <#-- 手机网页播放器 end-->

</head>
<body>
 
<!--代码部分begin-->


 <!--右侧按钮-->
    <div class="swiper-pagination"></div>
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
        <span class="qr"><img src="<#if site??>${site.wxQrCode!''}</#if>"></span>
      </a>
      
      <a title="客服电话">
        <img src="/client/l_images/float_ico04.png" width="42" height="42" alt="客服电话">
        <span class="phone_num"><#if site??>${site.telephone!''}</#if></span>
      </a>
      <a href="javascript:gotop();" title="到顶部">
        <img src="/client/l_images/float_ico05.png" width="42" height="42" alt="到顶部">
      </a>
    </div>
    <!--右侧浮动导航结束-->
<div class="section sect1">
            <!-- 导航 -->
              <div class="wrapper">
                <ul class="wrapper-nav" id="wrapper-nav">
			<li  class="active" ><a href="/">首页</a></li>
			<li ><a href="/info/aIn">专项行动</a></li>
			<li ><a href="/info/activity/list">培育活动</a></li>
			<li ><a href="/info/index">新闻动态</a></li>
			<li ><a href="/info/projectshow">企业项目</a></li>
			<li ><a href="/info/resource">专家资源</a></li>
			<li><a href="/info/host">合作机构</a></li>
			<li><a href="/info/contact">联系方式</a></li>
                </ul>
                <#if username??>
                    <div class="logoin"><a href="/user" style="color:#ff4040;">${username}</a><span>|</span><a href="/logout">退出</a></div>
                <#else>
                    <div class="logoin"><a href="/login">登录</a><span>|</span><a href="/reg">注册</a></div>
                </#if>
              </div>
            <!-- 导航end -->

            <div class="clear"></div>
  <!-- banner -->
  <div class="banner1">
  	<div class="bg1"></div>
  	<div class="bg2"></div>
  
  <#if regionUsername??>
  	<a href="/region/enterprise/list" >区县管理员</a>
  <#elseif expertUsername??>
  	<a href="/expert/enterprise/list" >评分</a>
  <#elseif activityUsername??>
  	<a href="/activity/list" >活动管理员</a>
  <#else>  
    <a href="/enterprise/check">在线征集</a>
  </#if> 
  </div>
  <!-- banner END -->

</div>
  



  <div class="section sect2">
        <div class="section1">
            <div class="div1">
                <#--<p class="p1">组织体系</p>
                <p class="p2">Organization system</p>-->
                <img src="/client/images/zztx1.png" alt="组织体系"/>
				<br>
            	<img src="/client/images/zztx2.png" alt="System"/>
            </div>
            <ul>
            <#if organization_0_list??>
                <#list organization_0_list as item>
                    <#if item_index == 0>
                        <li class="li1" >
                            <p class="p1">${item.title!''}</p>
                            <#if ("organization_level1_"+item.id)?eval??>
                                <#list ("organization_level1_"+item.id)?eval as article>
                                    <#if article_index < 1>
                                        <p class="current">
	                                        <a title="${article.title!''}" target="_blank" href="<#if article.linkUrl??&&article.linkUrl?length gt 0>${article.linkUrl!''}<#else>/info/list/content/${article.id?c!''}?mid=${article.menuId!''}</#if>">
	                                        	<#if article.title?length gt 10>
	                                        		${article.title[0..9]}...
	                                        	<#else>
	                                        		${article.title!''}
	                                        	</#if>
	                                        </a>
                                        </p>
                                    </#if>
                                </#list>
                            </#if>
                         </li>
                                                            
                    <#elseif item_index == 1>
                     <li class="li1">
                        <p class="p1">${item.title!''}</p>
                        <#if ("organization_level1_"+item.id)?eval??>
                              <#list ("organization_level1_"+item.id)?eval as article>
                                    <#if article_index < 2>
                                        <p class="current">
	                                        <a title="${article.title!''}" target="_blank"  href="<#if article.linkUrl??&&article.linkUrl?length gt 0>${article.linkUrl!''}<#else>/info/list/content/${article.id?c!''}?mid=${article.menuId!''}</#if>">
	                                        	<#if article.title?length gt 10>
	                                        		${article.title[0..9]}...
	                                        	<#else>
	                                        		${article.title!''}
	                                        	</#if>
	                                        </a>
                                        </p>                                    
                                    </#if>
                              </#list>
                        </#if>                              
                        </li>
                    <#elseif item_index == 3>
                        <li class="li2">
                            <p class="p1">${item.title!''}</p>
                            <#if ("organization_level1_"+item.id)?eval??>
                                <#list ("organization_level1_"+item.id)?eval as article>
                                    <#if article_index < 6>
                                        <p class="current">
	                                        <a title="${article.title!''}"  target="_blank" href="<#if article.linkUrl??&&article.linkUrl?length gt 0>${article.linkUrl!''}<#else>/info/list/content/${article.id?c!''}?mid=${article.menuId!''}</#if>">
	                                        	<#if article.title?length gt 10>
	                                        		${article.title[0..9]}...
	                                        	<#else>
	                                        		${article.title!''}
	                                        	</#if>
	                                        </a>
                                        </p>                                      
                                    </#if>
                                </#list>
                            </#if>                                
                        </li>
                        <li class="li3">
                            <p class="p1">${item.title!''}</p>
                            <#if ("organization_level1_"+item.id)?eval??>
                                <#list ("organization_level1_"+item.id)?eval as article>
                                    <#if article_index gt 5 && article_index lt 12>
                                        <p class="current">
	                                        <a title="${article.title!''}" target="_blank"  href="<#if article.linkUrl??&&article.linkUrl?length gt 0>${article.linkUrl!''}<#else>/info/list/content/${article.id?c!''}?mid=${article.menuId!''}</#if>">
	                                        	<#if article.title?length gt 10>	
	                                        		${article.title[0..9]}...
	                                        	<#else>
	                                        		${article.title!''}
	                                        	</#if>
	                                        </a>
                                        </p>                                      
                                    </#if>
                                </#list>
                            </#if>                                
                        </li>
                    <#elseif item_index == 4|| item_index == 5>
                        <li class="li2">
                            <p class="p1">${item.title!''}</p>
                            <#if ("organization_level1_"+item.id)?eval??>
                                <#list ("organization_level1_"+item.id)?eval as article>
                                    <#if article_index < 5>
                                        <p class="current">
	                                        <a title="${article.title!''}"  target="_blank" href="<#if article.linkUrl??&&article.linkUrl?length gt 0>${article.linkUrl!''}<#else>/info/list/content/${article.id?c!''}?mid=${article.menuId!''}</#if>">
	                                        	<#if article.title?length gt 10>
	                                        		${article.title[0..9]}...
	                                        	<#else>
	                                        		${article.title!''}
	                                        	</#if>
	                                        </a>
                                        </p>                                      
                                    </#if>
                                </#list>
                            </#if>
                        </li>                        
                    <#else>
                        <li class="li4">
                            <p class="p1">${item.title!''}</p>
                            <#if ("organization_level1_"+item.id)?eval??>
                                <#list ("organization_level1_"+item.id)?eval as article>
                                    <#if article_index < 3>
                                        <p class="current">
	                                        <a title="${article.title!''}" target="_blank"  href="<#if article.linkUrl??&&article.linkUrl?length gt 0>${article.linkUrl!''}<#else>/info/list/content/${article.id?c!''}?mid=${article.menuId!''}</#if>">
	                                        	<#if article.title?length gt 10>
	                                        		${article.title[0..9]}...
	                                        	<#else>
	                                        		${article.title!''}
	                                        	</#if>
	                                        </a>
                                        </p>                                      
                                    </#if>
                                </#list>
                            </#if>
                            
                        </li>
                    </#if>
                </#list>
            </#if>                    
            </ul>
        </div>
        <div class="section2">
            <div class="div1">
            <#if activityAbout??>
                <img src="/client/images/xdgk1.png" alt="行动概况"/>
                <br>
            	<img src="/client/images/xdgk2.png" alt="Survey"/>
            </div>
                <p class="p3">${activityAbout.brief!''}</p>
                <p class="p4"><a title="查看更多信息" target="_blank"  href="<#if activityAbout.linkUrl??&&activityAbout.linkUrl?length gt 0>${activityAbout.linkUrl!''}<#else>/info/list/content/${activityAbout.id?c!''}?mid=${activityAbout.menuId!''}</#if>">查看更多</a></p>
           	</#if>
            </div>
        </div>
    </div>


	<div class="section sect3">
                <div class="section1">
                    <!-- <div>speed</div> -->
                    <div class="parnet">
                      <div class="child">
                     <#if breed_page??>
                        <#list breed_page.content as item>
                        <div class="child1">
                          <div class="child1_1">
                              <p class="p1">科技小巨人培育专项</p>
                              <p class="p2">${item.title!''}</p>
                              <p class="p3"><#if item.brief??&&item.brief?length gt 40>
                                                ${item.brief[0..39]}...
                                            <#else>
                                                ${item.brief!''}
                                            </#if>
                              </p>
                              <p class="p4">
                                  <a target="_blank"  href="<#if item.linkUrl??&&item.linkUrl?length gt 0>${item.linkUrl!''}<#else>/info/list/content/${item.id?c!''}?mid=${item.menuId!''}</#if>" class="a1">查看详情&rarr;</a>
                                  <#--<a href="<#if item.linkUrl??>${item.linkUrl}<#else>/info/aIn/${item.id?c!''}</#if>" class="a2">了解更多信息</a>-->
                              </p>  
                          </div>
                          <img src="${item.imgUrl!''}" width="420" height="280" style="border:0;"/>
                        </div>
                        </#list>
                    </#if>    
                        
                      </div>      
                    </div>
                    <a class="prev" href="javascript:void(0)"></a>
                    <a class="next" href="javascript:void(0)"></a>
                    <script type="text/javascript" src="/client/js/jquery.js"></script>
                    <script src="/client/js/scroll.js"></script>
                </div>
                
                <div class="clear"></div>

                <div class="section2">
                <div class="fivesupport" >
                    <div class="div1">
		                <img src="/client/images/wdzc1.png" alt="五大支持"/>
		                <br>
		            	<img src="/client/images/wdzc2_2.png" alt="Support" />
                    </div>
                    <#if fiveSupport_page??>
                    	<#list fiveSupport_page.content as item>
                    		<#if item_index lt 5>
		                    <div class="div${item_index+2}">
		                    	<#if  item_index gt 1>
			                    	<p class="p2">
			                            <i class="i${item_index+1}"></i>
			                            <b>
				                            <a target="_blank"  style="color:#01468f;" href="<#if item.linkUrl??&&item.linkUrl?length gt 0>${item.linkUrl!''}<#else>/info/list/content/${item.id?c!''}?mid=${item.menuId!''}</#if>" title="查看详细介绍">
				                           	    ${item.title!''}
				                            </a>
			                            </b>
			                        </p>
			                        <p class="p1">${item.brief!''}</p>
		                        <#else>
			                        <p class="p1">${item.brief!''}</p>
			                        <p class="p2">
			                            <i class="i${item_index+1}"></i>
			                            <b>
				                            <a target="_blank"  style="color:#01468f;" href="<#if item.linkUrl??&&item.linkUrl?length gt 0>${item.linkUrl!''}<#else>/info/list/content/${item.id?c!''}?mid=${item.menuId!''}</#if>" title="查看详细介绍">
				                           	    ${item.title!''}
				                            </a>
			                            </b>
			                        </p>
			                    </#if>    
		                    </div>
		                    </#if>
		                </#list>    
		            </#if>    
                </div>
            </div>

	<div class="section sect4">
                <div class="sect1">
                    <div class="div1">
		                <img src="/client/images/xwdt1.png" title="查看更多" alt="新闻动态" onclick="javascript:window.open('/info/index');" style="cursor:pointer;"/>
		                <br>
		            	<img src="/client/images/xwdt2.png" alt="News" style=" border:0;"/>
                    </div>
                    <div class="div2">
  
		<#-- 验证浏览器版本-->
<script type="text/javascript">
  var browser={
    versions:function(){
            var u = navigator.userAgent, app = navigator.appVersion;
            return {         //移动终端浏览器版本信息
                 trident: u.indexOf('Trident') > -1, //IE内核
                presto: u.indexOf('Presto') > -1, //opera内核
                webKit: u.indexOf('AppleWebKit') > -1, //苹果、谷歌内核
                gecko: u.indexOf('Gecko') > -1 && u.indexOf('KHTML') == -1, //火狐内核
                mobile: !!u.match(/AppleWebKit.*Mobile.*/), //是否为移动终端
                ios: !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/), //ios终端
                android: u.indexOf('Android') > -1 || u.indexOf('Linux') > -1, //android终端或uc浏览器
                iPhone: u.indexOf('iPhone') > -1 , //是否为iPhone或者QQHD浏览器
                iPad: u.indexOf('iPad') > -1, //是否iPad
                webApp: u.indexOf('Safari') == -1 //是否web应该程序，没有头部与底部
            };
         }(),
         language:(navigator.browserLanguage || navigator.language).toLowerCase()
}
<#--
 document.writeln("语言版本: "+browser.language);
document.writeln(" 是否为移动终端: "+browser.versions.mobile);
document.writeln(" ios终端: "+browser.versions.ios);
document.writeln(" android终端: "+browser.versions.android);
document.writeln(" 是否为iPhone: "+browser.versions.iPhone);
document.writeln(" 是否iPad: "+browser.versions.iPad);
document.writeln(navigator.userAgent);

$(document).ready(function(){
	if (browser.versions.ios)
	{
		$("#CuPlayer").css("display","none");
	}
	else{
		$("#example_video_1").css("display","none");
	}
});-->
  </script> 
                    <#-- 验证浏览器版本 end-->


					  
					  <div class="video" id="CuPlayer" style="width:480px;height:400px;float:left;" >
						<SCRIPT LANGUAGE=JavaScript>
						
						var vID        = ""; 
						var vWidth     = 480;
						var vHeight    = 400;
						var vFile      = "/client/js/CuSunV2set.xml";
						var vPlayer    = "/client/js/player.swf?v=2.5";    
						var vPic       = "/client/images/banner1.jpg";
						
						
						//PC,安卓,iOS
						var vMp4url    = "<#if video??><#list video.content as item><#if item_index=0>${item.imgUrl!''}</#if></#list></#if>";
						<#--var vMp4url    = "/images/test.mp4";
						var vIosurl  = "/images/test.mp4";-->
						</SCRIPT> 
						<script class="CuPlayerVideo" data-mce-role="CuPlayerVideo" type="text/javascript" src="/client/js/AsComV3.js"></script>
						</div>
					  
					    <script type="text/javascript" src="/client/js/jquery.js"></script>
  						<script type="text/javascript" src="/client/js/action.js"></script>
                        <#-- 视频兼容调试 end-->
                        <ul>
                        <#if news_list??>                   
                            <#list news_list as item>
                                <#if item_index < 3>
                                    <li class="li1"><a target="_blank" title="${item.title!''}"  href="<#if item.linkUrl??&&item.linkUrl?length gt 0>${item.linkUrl!''}<#else>/info/list/content/${item.id?c!''}?mid=${item.menuId!''}</#if>">
                                        <img src="${item.imgUrl!''}" width="171" height="120" alt="新闻图片" />
                                        <div class="newsmess">
                                            <p class="p1"><#if item.title?length lt 18>
                                                ${item.title!''}
                                            <#else>
                                                ${item.title[0..17]?default("")}...
                                            </#if></p>
                                            <p class="p2">${item.brief!''}</p>
                                            <p class="p3">
                                            <#--
                                                <span class="span2"><i class="i2"></i>${item.viewCount!'0'}</span>
                                                <span class="span1"><i class="i1"></i>03:22</span>
                                                -->
                                            </p>
                                        </div>
                                    </a></li>
                                </#if>
                            </#list>    
                        </#if>                            
                        </ul>
                    </div>
                </div>
                <div class="sect2">
                    <ol>
                    <#if news_list??>
                        <#list news_list as item>
                            <#if item_index gt 2&&item_index lt 7>
                                <li class="li1"><a target="_blank" title="${item.title!''}"  href="<#if item.linkUrl??&&item.linkUrl?length gt 0>${item.linkUrl!''}<#else>/info/list/content/${item.id?c!''}?mid=${item.menuId!''}</#if>">
                                   <img src="${item.imgUrl!''}" width="171" height="120" alt="新闻图片" />
                                    <div class="newsmess1">
                                        <p class="p1"><#if item.title?length lt 18>
                                            ${item.title!''}
                                        <#else>
                                            ${item.title[0..17]?default("")}...
                                        </#if></p>
                                        <p class="p2">${item.createTime?string("yyyy-MM-dd")}</p>
                                        <p class="p3">${item.brief!''}</p>
                                    </div> 
                                </a></li>
                            </#if>
                        </#list>
                    </#if>                       
                    </ol>
                </div>
            </div>


 	<div class="section sect5">
                <!-- 底部 -->
                <#include "/client/news_common_footer.ftl" />
                <!-- 底部end -->
            </div>

</body>
</html>