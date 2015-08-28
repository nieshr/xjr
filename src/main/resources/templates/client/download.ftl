<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7"/>
<title><#if site??>${site.seoTitle!''}-</#if>资料下载</title>
<meta name="keywords" content="${site.seoKeywords!''}">
<meta name="description" content="${site.seoDescription!''}">
<meta name="copyright" content="${site.copyright!''}" />
<link href="client/css/base.css" rel="stylesheet" type="text/css" />
<link href="client/css/download.css" rel="stylesheet" type="text/css" />
</head>

<body>
<!--header-->
<#include "/client/common_header.ftl" />
<!--header_end -->

<!--main-->
<div class="main">
    <!--left_content-->
    <div class="left_content">
    <dl class="leftbar">
            <dt><a>DOWNLOAD</a><p>资料下载</p></dt>
        
        </dl>
    
    <dl class="course">
      <dt><a>开展课程</a><p>更多&gt;&gt;</p></dt>
        <dd><a>学校简介</a></dd>
        <dd><a>学校简介</a></dd>
        <dd><a>学校简介</a></dd>
        <dd><a>学校简介</a></dd>
        <dd><a>学校简介</a></dd>
        <dd><a>学校简介</a></dd>
        <dd><a>学校简介</a></dd>
        <dd><a>学校简介</a></dd>
        <dd><a>学校简介</a></dd>
        <dd><a>学校简介</a></dd>
    </dl>
    <dl class="call">
        <dt><a>开展课程</a><p>更多&gt;&gt;</p></dt>
        <dd class="call_text01">
            <a>学校简介:</a>
            <input type="" value="" />
        </dd>
        <dd class="call_text01">
            <a>学校简介:</a>
            <input type="" value="" />
        </dd>
        <dd class="call_btn">
            <p>立即联系</p>
        </dd>
        <dd class="call_tel">
            <span>报名热线：400-0011-652</span>
            <span>咨询老师：刘老师</span>
            <span>点击咨询：<img src="images/about_qq_27.png" />&nbsp;QQ交谈</span>
        </dd>
    </dl>
    </div>
    
    <!--right_content-->
    <div class="right_content">
        <div class="right_title">
        <a>首页&nbsp;&gt;&nbsp;</a>
        <a>资料下载</a>
        </div>
 
    <div class="right_crouse">
    <#if load_data_page??>
    <#list load_data_page.content as item>
        <dl class="crouse">
            <dd><a>${item.title!''}</a><p>${item.content!''}</p>
            <input type="button" onclick="javascript:window.location.href='/download/data?name=${item.imgUrl}'" value="立即下载" /> 
            </dd>
        </dl>
     </#list>
     </#if>
      <div class="page">
        <input type="button" value="1" />
        <input type="button" value="2" />
        <input type="button" value="3" />
        <input class="block" type="button" value="上一页" />
        <input class="block" type="button" value="下一页" />
        <input class="page_text" type="" value="1" />
        <input class="block" type="button" value="确定" />
        
        <a>共3页</a>
        </div>
    
</div>
</div>











<!--footer-->
<#include "/client/common_footer.ftl" />
<!--footer_end-->

</body>
</html>
