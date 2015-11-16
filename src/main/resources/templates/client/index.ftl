<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<!--网页左上角小图标-->
	<link rel="shortcut icon" href="/client/images/icon.ico" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1">
<title>科技小巨人-首页</title>
<!--css-->
<link rel="shortcut icon" href="/images/icon.ico" />
<link href="/client/css/l_base.css" rel="stylesheet" type="text/css" />
<link href="/client/css/jquery.fullPage.css" rel="stylesheet" type="text/css" />
<link href="/client/css/index.css" rel="stylesheet" type="text/css" />


</head>
<body>
 
<!--代码部分begin-->

<!-- 右侧圆点 -->
<ul id="menu">
  <li data-menuanchor="page1" class="active"><a href="#page1">1</a></li>
  <li data-menuanchor="page2"><a href="#page2">2</a></li>
  <li data-menuanchor="page3"><a href="#page3">3</a></li>
  <li data-menuanchor="page4"><a href="#page4">4</a></li>
  <li data-menuanchor="page5"><a href="#page5">5</a></li>
</ul>
<!-- 右侧圆点 END-->

 <!--右侧按钮-->
    <div class="swiper-pagination"></div>
    <!--右侧浮动导航开始-->
    <div class="floatbox">
      <a  href="/user" target="_blank" title="申报入口">
        <img src="/client/l_images/float_ico02.png" width="42" height="42" alt="申报入口">
      </a>
      <a href="#" title="搜索" target="_blank" rel="nofollow">
        <img src="/client/l_images/float_ico01.png" width="42" height="42" alt="搜索">
      </a>
      <a id="BizQQWPA" href="http://wpa.qq.com/msgrd?v=3&uin=1981148933&site=qq&menu=yes" target="_blank" title="在线客服">
        <img src="/client/l_images/float_ico06.png" width="42" height="42" alt="在线客服">
      </a>
      <a href="javascript:loginWinOpen('weixin_win','myselfbox',200);" title="官方微信">
        <img src="/client/l_images/float_ico03.png" width="42" height="42" alt="官方微信">
        <span class="qr"><img src="/client/l_images/QR_code.png"></span>
      </a>
      
      <a title="客服电话">
        <img src="/client/l_images/float_ico04.png" width="42" height="42" alt="客服电话">
        <span class="phone_num">023-8888 8888</span>
      </a>
      <a href="#page1" title="到顶部">
        <img src="/client/l_images/float_ico05.png" width="42" height="42" alt="到顶部">
      </a>
    </div>
    <!--右侧浮动导航结束-->
<div class="section sect1">
            <!-- 导航 -->
              <div class="wrapper">
                <ul class="wrapper-nav" id="wrapper-nav">
			<a href="/"><li class="active">首页</li></a>
			<a href="/info/aIn"><li >专项行动</li></a>
			<a href="/info/activity/list"><li >培育活动</li></a>
			<a href="/info/index"><li >新闻动态</li></a>
			<a href="/info/projectshow"><li >企业项目</li></a>
			<a href="/info/resource"><li >专家资源</li></a>
			<a href="/info/host"><li>合作机构</li></a>
			<a href="/info/contact"><li>联系方式</li></a>
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
  
  <#if regionUsername??>
  	<a href="/region/enterprise/list" >区县管理</a>
  <#elseif expertUsername??>
  	<a href="/expert/enterprise/list" >评分</a>
  <#elseif activityUsername??>
  	<a href="/activity/list" >活动管理</a>
  <#else>  
    <a href="/enterprise/check">在线征集</a>
  </#if> 
    <img src="<#if site??>${site.wxQrCode!''}</#if>" alt="二维码" />
  </div>
  <!-- banner END -->

</div>
  



  <div class="section sect2">
        <div class="section1">
            <div class="div1">
                <p class="p1">组织体系</p>
                <p class="p2">Organization system</p>
            </div>
            <ul>
            <#if organization_0_list??>
                <#list organization_0_list as item>
                    <#if item_index == 0>
                        <li class="li1">
                            <p class="p1">${item.title!''}</p>
                            <#if ("organization_level1_"+item.id)?eval??>
                                <#list ("organization_level1_"+item.id)?eval as article>
                                    <#if article_index < 1>
                                        <p><a title="查看更多信息" href="<#if article.linkUrl??&&article.linkUrl?length gt 0>${article.linkUrl!''}<#else>/info/list/content/${article.id?c!''}?mid=${article.menuId!''}</#if>">${article.title!''}</a></p>
                                    </#if>
                                </#list>
                            </#if>
                                                            
                    <#elseif item_index == 1>
                        <p class="p1">${item.title!''}</p>
                        <#if ("organization_level1_"+item.id)?eval??>
                              <#list ("organization_level1_"+item.id)?eval as article>
                                    <#if article_index < 5>
                                        <p><a title="查看更多信息" href="<#if article.linkUrl??&&article.linkUrl?length gt 0>${article.linkUrl!''}<#else>/info/list/content/${article.id?c!''}?mid=${article.menuId!''}</#if>">${article.title!''}</a></p>
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
                                        <p><a title="查看更多信息" href="<#if article.linkUrl??&&article.linkUrl?length gt 0>${article.linkUrl!''}<#else>/info/list/content/${article.id?c!''}?mid=${article.menuId!''}</#if>">${article.title!''}</a></p>
                                    </#if>
                                </#list>
                            </#if>                                
                        </li>
                        <li class="li3 current">
                            <p class="p1">${item.title!''}</p>
                            <#if ("organization_level1_"+item.id)?eval??>
                                <#list ("organization_level1_"+item.id)?eval as article>
                                    <#if article_index gt 5>
                                        <p><a title="查看更多信息" href="<#if article.linkUrl??&&article.linkUrl?length gt 0>${article.linkUrl!''}<#else>/info/list/content/${article.id?c!''}?mid=${article.menuId!''}</#if>">${article.title!''}</a></p>
                                    </#if>
                                </#list>
                            </#if>                                
                        </li>
                    <#elseif item_index == 4|| item_index == 5>
                        <li class="li2" style="width:19%;">
                            <p class="p1">${item.title!''}</p>
                            <#if ("organization_level1_"+item.id)?eval??>
                                <#list ("organization_level1_"+item.id)?eval as article>
                                    <#if article_index < 6>
                                        <p><a title="查看更多信息" href="<#if article.linkUrl??&&article.linkUrl?length gt 0>${article.linkUrl!''}<#else>/info/list/content/${article.id?c!''}?mid=${article.menuId!''}</#if>">${article.title!''}</a></p>
                                    </#if>
                                </#list>
                            </#if>
                        </li>                        
                    <#else>
                        <li class="li4">
                            <p class="p1">${item.title!''}</p>
                            <#if ("organization_level1_"+item.id)?eval??>
                                <#list ("organization_level1_"+item.id)?eval as article>
                                    <#if article_index < 6>
                                        <p><a title="查看更多信息" href="<#if article.linkUrl??&&article.linkUrl?length gt 0>${article.linkUrl!''}<#else>/info/list/content/${article.id?c!''}?mid=${article.menuId!''}</#if>">${article.title!''}</a></p>
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
                <p class="p1">${activityAbout.title!''}</p>
                <p class="p2">${activityAbout.callIndex!''}</p>
                <p class="p3">${activityAbout.brief!''}</p>
                <p class="p4"><a title="查看更多信息" href="<#if activityAbout.linkUrl??&&activityAbout.linkUrl?length gt 0>${activityAbout.linkUrl!''}<#else>/info/list/content/${activityAbout.id?c!''}?mid=${activityAbout.menuId!''}</#if>">查看更多</a></p>
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
                                  <a href="<#if item.linkUrl??>${item.linkUrl!''}<#else>/info/list/content/${item.id?c!''}?mid=${item.menuId!''}</#if>" class="a1">查看详情&rarr;</a>
                                  <#--<a href="<#if item.linkUrl??>${item.linkUrl}<#else>/info/aIn/${item.id?c!''}</#if>" class="a2">了解更多信息</a>-->
                              </p>  
                          </div>
                          <img src="${item.imgUrl!''}" width="420" height="280" />
                        </div>
                        </#list>
                    </#if>    
                        
                      </div>      
                    </div>
                    <a class="prev" href="#"></a>
                    <a class="next" href="#"></a>
                    <script type="text/javascript" src="/client/js/jquery.js"></script>
                    <script src="/client/js/scroll.js"></script>
                </div>
                
                <div class="clear"></div>

                <div class="section2">
                    <div class="div1">
                        <p class="p1_1">五大支持</p>
                        <p class="p2_1">Support</p>
                    </div>
                    <#if fiveSupport_page??>
                    	<#list fiveSupport_page.content as item>
                    		<#if item_index lt 5>
		                    <div class="div${item_index+2}">
		                    	<#if item_index == 2>
			                    	<p class="p2">
			                            <i class="i${item_index+1}"></i>
			                            <b>
				                            <a style="color:#01468f;" href="<#if item.linkUrl??&&item.linkUrl?length gt 0>${item.linkUrl!''}<#else>/info/list/content/${item.id?c!''}?mid=${item.menuId!''}</#if>" title="查看详细介绍">
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
				                            <a style="color:#01468f;" href="<#if item.linkUrl??&&item.linkUrl?length gt 0>${item.linkUrl!''}<#else>/info/list/content/${item.id?c!''}?mid=${item.menuId!''}</#if>" title="查看详细介绍">
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
                        <p class="p1">新闻动态</p>
                        <p class="p2">News dynamics</p>
                    </div>
                    <div class="div2">
                        <video width="480" height="400" src="<#if video??><#list video.content as item><#if item_index=0>${item.imgUrl!''}</#if></#list></#if>" controls="controls" id="video" autoplay="autoplay">
                                 您的浏览器不支持该视频播放               
                        </video>
                        <ul>
                        <#if news_list??>                   
                            <#list news_list as item>
                                <#if item_index < 3>
                                    <li class="li1"><a href="javascript:showVideo${item_index}();">
                                        <img src="${item.imgUrl!''}" width="173" height="120" alt="新闻图片" />
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
                            <#if item_index gt 2&&item_index lt 6>
                                <li class="li1"><a href="<#if item.linkUrl??&&item.linkUrl?length gt 0>${item.linkUrl!''}<#else>/info/list/content/${item.id?c!''}?mid=${item.menuId!''}</#if>">
                                   <img src="${item.imgUrl!''}" width="173" height="120" alt="新闻图片" />
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

<script src="/client/js/jquery.min.js"></script>
<script src="/client/js/jquery-ui-1.10.3.min.js"></script>
<script src="/client/js/jquery.fullPage.min.js"></script>
<script>
  $(function(){
    $.fn.fullpage({
      // slidesColor: ['#009999', '#CC6600', '#CCCC00', '#66CC66', '#CC3366'],
      anchors: ['page1', 'page2', 'page3', 'page4', 'page5'],
      menu: '#menu'
    });
  });
</script>
<!--代码部分end-->

</body>
</html>