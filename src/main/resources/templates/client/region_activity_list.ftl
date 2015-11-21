<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
    <title>活动列表</title>
    <link rel="shortcut icon" href="/client/images/icon.ico" />
    <link href="/client/css/base.css" rel="stylesheet" type="text/css" />
    <link href="/client/css/area.css" rel="stylesheet" type="text/css" />
    
    <style type="text/css">
        .page{ width: 600px; float: right; margin-top: 30px;}
        .page *{ float: left;}
        .page span{ color: #333333; line-height: 20px; display: block;}
        .page a{  width: 20px; height: 20px;}
        .page .page_next{ width: 60px;}
        .page .page_last{width: 40px;}
        .page p{  margin-left: 10px;}
    </style>
</head>
<script src="/client/js/jquery-1.9.1.min.js"></script>
<script src="/client/js/main.js"></script>
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
            <dd><a href="/region/enterprise/list">企业列表</a></dd>
            <dd><a href="/region/activity/list">活动列表</a></dd>
            <#--<dd><a href="#">档案跟踪</a></dd>-->

        </dl>
    </div>
<!--right-->
    <div class="right_content">
    <div class="right_box">
        <dl class="crumb">
            <dt><a href="#"></a></dt>
            <dd>
                <p>当前所在位置:</p>
                <a href="#">区县管理</a>
                <p>&gt;</p>
                <a href="#">活动列表</a>
            </dd>
            <dt class="crumb_back"><a  href="javascript:history.go(-1);">返回上一页</a></dt>
        </dl>
        
        <table class="new_list">
            <tr class="list_title">
                <th width="30%">活动名称</th>
                <th width="12%">日期<th>
                <th width="15%">筹备开始</th>
                <th width="15%">筹备结束</th>
                <th width="10%">状态</th>
                <th width="20%">操作</th>
            </tr>
        <#if activity_page??>
            <#list activity_page.content as item>
                <tr>
                    <td>${item.title!''} 
                    <#if item.statusId??&&item.statusId == 0>
                    	<img src="/client/images/n00.gif" style="top:2px;left:3px;" title="未审核" alt="未审核" />
                    <#elseif  item.statusId??&&item.statusId == 1>
                    	<img src="/client/images/n0.png" style="top:2px;left:3px;" title="已审核" alt="已审核" />
                    </#if>
                    </td>
                    <td style="color:#0ab2cb;">${item.date?string("yyyy-MM-dd")!''}<td>
                    <td style="color:#e67817;">${item.prepareOn?string("MM-dd HH:mm")!''}</td>
                    <td style="color:#529c15;">${item.prepareOff?string("MM-dd HH:mm")!''}</td>
                   
                    <#if item.timeId??>
	                    <#if item.timeId ==0>
	                    		 <td>未启动</td>
	                    <#elseif  item.timeId == 1>
	                    		 <td style="color:#0ab2cb;">筹备中</td>
	                    <#elseif  item.timeId == 2>
	                    		 <td  style="color:#529c15;">已结束</td>
	                    </#if>
	                </#if>    
                    
                    <td>
                         <a href="/region/activity/detail?id=${item.id?c!''}">详情</a>
                         <#if  item.statusId??&&item.statusId == 1>
	                         | <a href="" style="color: #666;" title="已审核，无法修改">预选</a>
	                         | <a href=""  style="color: #666;"  title="已审核，无法修改">推荐企业</a>
                         <#else>
	                         | <a href="/region/candidateEnterprise/${item.id?c!''}?area=${item.region!''}">预选</a>
	                         | <a href="/region/recommendEnterprise?id=${item.id?c!''}">推荐企业</a>    
                         </#if>                    
                     <#--     | <a <#if item.pptUrl??> href="/download/data?name=${item.pptUrl!''}" <#else> style="color:#999999;"</#if>>下载模板</a>
                        | <a href="">上传推荐表</a> -->

                     </td>
                </tr>
            </#list>
        </#if>      
            
        </table>
    </div> 
    
    <div class="page">
        <#if activity_page??>
        <#assign PAGE_DATA = activity_page>
             <#if PAGE_DATA??>
                 <#if PAGE_DATA.number+1 == 1>
                      <a disabled="disabled"  class="page_next">上一页</a>               
                 <#else>
                     <a href="/region/activity/list?page=${PAGE_DATA.number-1}"  class="page_next">上一页</a>                
                 </#if>
                 
                 <#assign continueEnter=false>
                 
                 <#if PAGE_DATA.totalPages gt 0>
                     <#list 1..PAGE_DATA.totalPages as page>
                         <#if page <= 3 || (PAGE_DATA.totalPages-page) < 3 || (PAGE_DATA.number+1-page)?abs<3 >
                             <#if page == PAGE_DATA.number+1>
                                 <a  class ="current" style="color:#e67817;">${page }</a>
                             <#else>
                                 <a href="/region/activity/list?page=${page-1}">${page}</a> 
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
                     <a href="/region/activity/list?page=${PAGE_DATA.number+1}" class="page_last">下一页</a> 
                 </#if>
             </#if>
            <p>共${PAGE_DATA.totalPages!'1'}页  ${PAGE_DATA.totalElements!'1'}条</p>
            </#if>
          </div>
        
    
    </div>
</div><!--content_end-->
</div><!--main-->
</body>
</html>