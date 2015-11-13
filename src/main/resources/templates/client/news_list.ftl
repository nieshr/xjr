<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<!--网页左上角小图标-->
<link rel="shortcut icon" href="/client/images/icon.ico" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>科技小巨人-新闻动态</title>
<link rel="stylesheet" type="text/css" href="/client/css/base.css"/>
<link rel="stylesheet" type="text/css" href="/client/css/news.css"/>
<link rel="stylesheet" href="/client/css/news_base.css">
<link rel="stylesheet" href="/client/css/news_main.css">
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
      <li><a href="/info/index">新闻动态</a></li>
      <#if newsCat_list??>
           <#list newsCat_list as item>
                <li <#if item.id==catId>class="me"</#if>><a href="/info/list/10?catId=${item.id?c}">${item.title!''}</a></li>
           </#list>
      </#if>      
    </ul>
  </div>
</div>

<!--各种新闻-->
<div class="news_b">
  <ul>
  <#if info_page??>
      <#list info_page.content as item>
            <li><a href="/info/list/content/${item.id?c}?mid=10">
              <div class="pic"><img src="${item.imgUrl!''}" /></div>
              <div class="news_words">
                <div class="news_title">${item.title!''}</div>
                <div class="news_time">${item.createTime?string("yyyy-MM-dd")}</div>
                <div class="news_detail">${item.brief!''}</div>
              </div>
              </a></li>
      </#list>
  </#if>  
  </ul>
</div>

<!--页码按钮-->
<#assign PAGE_DATA=info_page />
<div class="pagnation" id="pagnation">
 <#if PAGE_DATA??>
     <#if PAGE_DATA.number+1 == 1>
          <a disabled="disabled"  class="page-prev">上一页"</a>               
     <#else>
         <a href="/info/list/10?catId=${item.id?c}&page=${PAGE_DATA.number-1}"  class="page-prev">上一页"</a>                
     </#if>
     
     <#assign continueEnter=false>
     
     <#if PAGE_DATA.totalPages gt 0>
         <#list 1..PAGE_DATA.totalPages as page>
             <#if page <= 3 || (PAGE_DATA.totalPages-page) < 3 || (PAGE_DATA.number+1-page)?abs<3 >
                 <#if page == PAGE_DATA.number+1>
                     <a  class ="current">${page }</a>
                 <#else>
                     <a href="/info/list/10?catId=${item.id?c}&page=${page-1}">${page}</a> 
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
         <a href="/info/list/10?catId=${item.id?c}&page=${PAGE_DATA.number+1}" class="page-next">下一页</a> 
     </#if>
 </#if>
    
</div>



<!-- 底部 -->
<#include "/client/news_common_footer.ftl" />
<!-- 底部end -->


</body>
</html>
