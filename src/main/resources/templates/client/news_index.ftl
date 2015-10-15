<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<!--网页左上角小图标-->
<link rel="shortcut icon" href="/client/images/icon.ico" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>科技小巨人-新闻动态</title>
<!-- css -->
<link rel="stylesheet" type="text/css" href="/client/css/base.css"/>
<link rel="stylesheet" type="text/css" href="/client/css/news.css"/>
<link rel="stylesheet" href="/client/css/news_base.css">
<link rel="stylesheet" href="/client/css/news_main.css">
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
      <li class="me"><a href="#">新闻动态</a></li>
      <#if newsCat_list??>
           <#list newsCat_list as item>
                <li><a href="/info/list/10?catId=${item.id?c}">${item.title!''}</a></li>
           </#list>
      </#if>
    </ul>
  </div>
</div>

<!--新闻动态-->
<div class="newsBox">

  <div class="sect1">
    <div class="div1">
      <h3>活动动态</h3>
      <div class="ggBox">
      <!--Luara图片切换骨架begin-->
      <div class="addWrap">
          <div class="swipe" id="mySwipe">
              <div class="swipe-wrap">
                <#if activity_page??>
                    <#list activity_page.content as item>
                        <#if item_index < 3>
                            <div><a href="/info/list/content/${item.id?c}?mid=${item.menuId?c}"><img class="img-responsive" src="${item.imgUrl!''}"/>
                            <span><#if item.title?length lt 18>
                                ${item.title!''}
                            <#else>
                                ${item.title[0..17]?default("")}...
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
      <#if activity_page??>
           <#list activity_page.content as item>
                 <#if item_index gt 2 && item_index < 6>
                     <div class="div1"><a href="/info/list/content/${item.id?c}?mid=${item.menuId?c}"><img src="${item.imgUrl!''}" alt="新闻图片" width="122" height="92"/></a>
                     <span><#if item.title?length lt 18>
                                ${item.title!''}
                            <#else>
                                ${item.title[0..17]?default("")}...
                            </#if></span></div>
                 </#if>
           </#list>
      </#if>               
      </div>

    </div>
    <div class="div2">
      <h3>通知公告</h3>
      <div class="sect1_right">
      <#if notice_page??>
           <#list notice_page.content as item>
                <#if item_index < 1>
                    <a href="/info/list/content/${item.id?c}?mid=${item.menuId?c}">
                    <img class="news_photo_1" src="${item.imgUrl!''}" alt="新闻图片" />
                    <span class="tips"><#if item.title?length lt 20>
                                ${item.title!''}
                            <#else>
                                ${item.title[0..19]?default("")}...
                            </#if></span></a>
                <#else>
                    <p><a href="/info/list/content/${item.id?c}?mid=${item.menuId?c}"><i class="i1"></i>
                        <span><#if item.title?length lt 20>
                                ${item.title!''}
                            <#else>
                                ${item.title[0..19]?default("")}...
                            </#if></span></a></p>
                </#if>
           </#list>
      </#if>
      </div>
    </div>
  </div>

  <!-- 媒体报道、数据公布 -->
  <div class="sect2">
    <div class="div1">
      <h3>媒体报道</h3>
      <dl>
      <#if media_page??>
          <#list media_page.content as item>
                <#if item_index < 3>
                     <dd><a href="/info/list/content/${item.id?c}?mid=${item.menuId?c}">
                      <img src="${item.imgUrl!''}" width="195" height="128" alt="新闻图片" />
                      <div class="mess">
                        <p class="p1"><#if item.title?length lt 20>
                                ${item.title!''}
                            <#else>
                                ${item.title[0..19]?default("")}...
                            </#if></p>
                        <p class="p2">${item.brief!''}</p>
                        <p class="p3">
                          <span class="span1">${item.source!''}</span>
                          <span class="span2">${item.createTime?string("yyyy-MM-dd")}</span>
                          <span class="span3">阅读（<b>${item.viewCount!'0'}</b>）</span>
                          <span class="span4">
                            <!-- JiaThis Button BEGIN -->
                            <div class="jiathis_style">
                                <a class="jiathis_button_qzone"></a>
                                <a class="jiathis_button_tsina"></a>
                                <a class="jiathis_button_tqq"></a>
                                <a class="jiathis_button_weixin"></a>
                                <a class="jiathis_button_renren"></a>
                                <a href="http://www.jiathis.com/share" class="jiathis jiathis_txt jtico jtico_jiathis" target="_blank"></a>
                            </div>
                            <script type="text/javascript" src="http://v3.jiathis.com/code/jia.js" charset="utf-8"></script>
                            <!-- JiaThis Button END -->
                          </span>
                        </p>
                      </div>
                    </a></dd>
                </#if>
                <#if item_index gt 2 && item_index < 4>
                    <dt><a href="/info/list/content/${item.id?c}?mid=${item.menuId?c}">
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
                          <span class="span4">
                           <!-- JiaThis Button BEGIN -->
                            <div class="jiathis_style">
                                <a class="jiathis_button_qzone"></a>
                                <a class="jiathis_button_tsina"></a>
                                <a class="jiathis_button_tqq"></a>
                                <a class="jiathis_button_weixin"></a>
                                <a class="jiathis_button_renren"></a>
                                <a href="http://www.jiathis.com/share" class="jiathis jiathis_txt jtico jtico_jiathis" target="_blank"></a>
                            </div>
                            <script type="text/javascript" src="http://v3.jiathis.com/code/jia.js" charset="utf-8"></script>
                            <!-- JiaThis Button END -->
                          </span>
                        </p>
                        <p class="p2">${item.brief!''}</p>
                      </div>
                    </a></dt>
                </#if>
          </#list>
      </#if>        
      </dl>      
    </div>
    
    <div class="div2">
      <h3 class="h3_right">数据公布</h3>
      <ul>
      <#if data_page??>
          <#list data_page.content as item>
                <#if item_index < 3>
                    <li><a href="/info/list/content/${item.id?c}?mid=${item.menuId?c}">
                      <img src="${item.imgUrl!''}" width="280" height="158" alt="新闻图片" />
                      <p>${item.title!''}</p>
                    </a></li>
                </#if>
          </#list>
      </#if>               
      </ul>
    </div>
  </div>
  <!-- 媒体报道、数据公布 End-->

  <!-- 热点追踪、创业风向 -->
  <div class="sect2">
    <div class="div1">
      <h3>热点追踪</h3>
      <dl>
       <#if hot_page??>
          <#list hot_page.content as item>
                <#if item_index < 3>
                     <dd><a href="/info/list/content/${item.id?c}?mid=${item.menuId?c}">
                      <img src="${item.imgUrl!''}" width="195" height="128" alt="新闻图片" />
                      <div class="mess">
                        <p class="p1"><#if item.title?length lt 20>
                                ${item.title!''}
                            <#else>
                                ${item.title[0..19]?default("")}...
                            </#if></p>
                        <p class="p2">${item.brief!''}</p>
                        <p class="p3">
                          <span class="span1">${item.source!''}</span>
                          <span class="span2">${item.createTime?string("yyyy-MM-dd")}</span>
                          <span class="span3">阅读（<b>${item.viewCount!'0'}</b>）</span>
                          <span class="span4">
                           <!-- JiaThis Button BEGIN -->
                            <div class="jiathis_style">
                                <a class="jiathis_button_qzone"></a>
                                <a class="jiathis_button_tsina"></a>
                                <a class="jiathis_button_tqq"></a>
                                <a class="jiathis_button_weixin"></a>
                                <a class="jiathis_button_renren"></a>
                                <a href="http://www.jiathis.com/share" class="jiathis jiathis_txt jtico jtico_jiathis" target="_blank"></a>
                            </div>
                            <script type="text/javascript" src="http://v3.jiathis.com/code/jia.js" charset="utf-8"></script>
                            <!-- JiaThis Button END -->
                          </span>
                        </p>
                      </div>
                    </a></dd>
                </#if>
                <#if item_index gt 2 && item_index < 4>
                    <dt><a href="/info/list/content/${item.id?c}?mid=${item.menuId?c}">
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
                          <span class="span4">
                           <!-- JiaThis Button BEGIN -->
                            <div class="jiathis_style">
                                <a class="jiathis_button_qzone"></a>
                                <a class="jiathis_button_tsina"></a>
                                <a class="jiathis_button_tqq"></a>
                                <a class="jiathis_button_weixin"></a>
                                <a class="jiathis_button_renren"></a>
                                <a href="http://www.jiathis.com/share" class="jiathis jiathis_txt jtico jtico_jiathis" target="_blank"></a>
                            </div>
                            <script type="text/javascript" src="http://v3.jiathis.com/code/jia.js" charset="utf-8"></script>
                            <!-- JiaThis Button END -->
                          </span>
                        </p>
                        <p class="p2">${item.brief!''}</p>
                      </div>
                    </a></dt>
                </#if>
          </#list>
      </#if>        
      </dl>
    </div>
    <div class="div2">
      <h3 class="h3_right">创业风向</h3>
      <ol>
      <#if SYB_page??>
          <#list SYB_page.content as item>
              <#if item_index < 8>
                   <li><a href="/info/list/content/${item.id?c}?mid=${item.menuId?c}">
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
  <!-- 热点追踪、创业风向 End-->

</div>
<!--新闻动态 End-->

<!-- 底部 -->
<#include "/client/news_common_footer.ftl" />
<!-- 底部end -->
</body>
</html>