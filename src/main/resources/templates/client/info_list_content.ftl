<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="/client/css/base.css" rel="stylesheet" type="text/css" />
<link href="/client/css/news_body.css" rel="stylesheet" type="text/css" />
<link href="/client/css/news_center.css" rel="stylesheet" type="text/css" />
<link href="/client/css/teacher_center.css" rel="stylesheet" type="text/css" />
<link href="/client/css/teacher.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/client/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/client/js/info-list.js"></script> 
</head>

<body>
<!--header-->
<#include "/client/common_header.ftl" />
<!--header_end -->

<!--main-->
<div class="main">
	<!--left_content-->
	<#include "/client/common_menu.ftl" /> 
    
    <!--right_content-->
    <div class="right_content">
    <#include "/client/info_list_content_detail.ftl" />
    </div>
</div>











<!--footer-->
<#include "/client/common_footer.ftl" />
<!--footer_end-->

</body>
</html>