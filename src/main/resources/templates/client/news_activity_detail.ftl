<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<!--网页左上角小图标-->
<link rel="shortcut icon" href="/client/images/icon.ico" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>专项活动</title>
<!-- css -->
<link rel="stylesheet" type="text/css" href="/client/css/base.css"/>
<link rel="stylesheet" type="text/css" href="/client/css/news.css"/>
<link rel="stylesheet" href="/client/css/news_base.css">
<link rel="stylesheet" href="/client/css/news_main.css">
<!-- js -->
<script type="text/javascript" src="/client/js/main.js"></script>
</head>

<body>
<!-- 导航 -->
<!-- head  -->
<#include "/client/news_common_header.ftl" />
<!-- head end -->

<!--位置-->
<div class="location_back">
  <div class="location">
    <div class="location1"><img src="/client/images/luyi2.png" /></div>
    <div class="location2"><a href="/">首页</a> > <a href="/info/activity/list"> 专项活动</a> > <a href="">${info.title!''}</a> </div>
  </div>
</div>

<!--新闻题目-->
<div class="news_detail" style="min-height:500px;">
  <div class="news_name">${info.title!''}</div>
  <div class="news_writer"><span>${info.region!''}</span> <span>${info.date?string("yyyy-MM-dd HH:mm")}</span> </div>
  <div class="news_words">
    <div class="words1">${info.theme!''}</div>
    <div class="words1">${info.introduction!''}</div>
  </div>
</div>

<!--foot-->
<!-- 底部 -->
<#include "/client/news_common_footer.ftl" />
<!-- 底部end -->

</body>
</html>
