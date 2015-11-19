<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<title>个人中心-站内信</title>
<link rel="shortcut icon" href="/client/images/icon.ico" />
<link href="/client/css/base.css" rel="stylesheet" type="text/css" />
<link href="/client/css/team.css" rel="stylesheet" type="text/css" />

<script src="/client/js/jquery-1.9.1.min.js"></script>
<script src="/client/js/main.js"></script>
<script type="text/javascript" src="/client/js/Validform_v5.3.2_min.js"></script>
        <style type="text/css">
        .page{ width: 600px; float: right; margin-top: 30px;}
        .page *{ float: left;}
        .page span{ color: #333333; line-height: 20px; display: block;}
        .page a{  width: 20px; height: 20px;}
        .page .page_next{ width: 60px;}
        .page .page_last{width: 40px;}
        .page p{  margin-left: 10px;}
    </style>
<body>
<!--main-->
<div class="main">
<!--header-->
<#include "/client/user_common_header.ftl" />
<!--header_end-->
<!--content-->
<div class="content">
<!--left-->
    <div class="leftbar">
        <dl class="nav">
            <dd><a href="/enterprise/check">网上报名</a></dd>
            <dd><a href="/enterprise/data">项目资料</a></dd>
            <dd><a href="/enterprise/activity/list">活动列表</a></dd>
            <dd><a href="/enterprise/project">申请展示</a></dd>

        </dl>
    </div>
<!--right-->
    <div class="right_content">
    <div class="right_box">
        <dl class="crumb">
            <dt><a href="#"></a></dt>
            <dd>
                <p>当前所在位置:</p>
                <a href="#">企业/团队</a>
                <p>&gt;</p>
                <a href="#">站内信息</a>

            </dd>
            <dt class="crumb_back"><a  href="javascript:history.go(-1);">返回上一页</a></dt>
        </dl>
		<div class="btwz_1">站内消息</div> 
             <div class="list_base2" style="padding-top:0;">
                <table class="new_list">
                <tr class="list_title">
                    <th width="20%">状态</th>
                    <th width="30%">标题</th>
                    <th width="20%">时间</th>
                    <th width="30%">操作</th>
                </tr>
            <#if message_page??>
                <#list message_page.content as item>
                    <tr>
                        <td><#if item.statusE?? && item.statusE == 1>已查看<#else>未查看</#if></td>
                        <td style="color:#0ab2cb;">${item.title!''}</td>
                        <td style="color:#e67817;"><#if item.time??>${item.time?string("yyyy-MM-dd HH:mm:ss")!''}</#if></td>
                        <td>
                        <a href="/enterprise/message/detail?id=${item.id?c!''}">查看</a>
                        </td>
                    </tr>
                </#list>
            </#if>     
            </table>
    
       <div class="page">
        <#if message_page??>
        <#assign PAGE_DATA = message_page>
             <#if PAGE_DATA??>
                 <#if PAGE_DATA.number+1 == 1>
                      <a disabled="disabled"  class="page_next">上一页</a>               
                 <#else>
                     <a href="/enterprise/message?page=${PAGE_DATA.number-1}"  class="page_next">上一页</a>                
                 </#if>
                 
                 <#assign continueEnter=false>
                 
                 <#if PAGE_DATA.totalPages gt 0>
                     <#list 1..PAGE_DATA.totalPages as page>
                         <#if page <= 3 || (PAGE_DATA.totalPages-page) < 3 || (PAGE_DATA.number+1-page)?abs<3 >
                             <#if page == PAGE_DATA.number+1>
                                 <a  class ="current" style="color:#e67817;">${page }</a>
                             <#else>
                                 <a href="/enterprise/message?page=${page-1}">${page}</a> 
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
                     <a disabled="disabled" class="page_last">下一页</a> 
                 <#else>
                     <a href="/enterprise/message?page=${PAGE_DATA.number+1}" class="page_last">下一页</a> 
                 </#if>
             </#if>
            <p>共${PAGE_DATA.totalPages!'1'}页  ${PAGE_DATA.totalElements!'1'}条</p>
            </#if>
          </div>
        </div>           
        
        
			
    </div> 

    </div>
</div><!--content_end-->
</div><!--main-->
</body>
</html>