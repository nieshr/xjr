<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
    <style type="text/css">
        .page{ width: 600px; float: right; margin-top: 30px;}
        .page *{ float: left;}
        .page span{ color: #333333; line-height: 20px; display: block;}
        .page a{  width: 20px; height: 20px;}
        .page .page_next{ width: 60px;}
        .page .page_last{width: 40px;}
        .page p{  margin-left: 10px;}
    </style>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
    <title><#if statusId??&&statusId == 1>添加预选项目<#elseif statusId??&&statusId == 2>添加推荐项目</#if></title>
    <link rel="shortcut icon" href="/client/images/icon.ico" />
    <link href="/client/css/base.css" rel="stylesheet" type="text/css" />
    <link href="/client/css/area.css" rel="stylesheet" type="text/css" />
<style>
.Validform_wrong {  background-position: -20px center;}
.Validform_right {  background-position: -20px center;}
</style>
<script src="/client/js/jquery-1.9.1.min.js"></script>
<script src="/client/js/main.js"></script>
<script type="text/javascript" src="/client/js/Validform_v5.3.2_min.js"></script>
<script>
function done()
{
	alert("上传成功！");
	location.href="/region/activity/list";
}
<#if done?? &&done == 1>
window.onload=done;
</#if>
</script>
</head>
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
            <dd><a href="#">档案跟踪</a></dd>
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
                <#if statusId??&&statusId == 1>
                <a href="#">预选企业/团队</a>
                <#elseif statusId??&&statusId == 2>
                 <a href="#">推荐企业/团队</a>
                 </#if>
            </dd>
            <dt class="crumb_back"><a  href="javascript:history.go(-1);">返回上一页</a></dt>
        </dl>
       <!-- <form name="addEnterprise" action="/activity/addEnterprises">-->
            <div class="list_base2" style="padding-top:0;">
            <h1 style="line-height:50px;  width:100%; text-align:center; float:left;">${activity.title!''}</h1>
                <table class="new_list">
                    <tr class="list_title">
                        <th width="20%">备选企业列表</th>
                        <th width="20%">地区</th>
                        <th width="20%">行业归属</th>
                        <#if statusId??&&statusId == 2>
                        	<th width="20%">推荐理由</th>
                        </#if>
                        <th width="20%">操作</th>
                    </tr>
                <#if enterprise_page??>
                    <#list enterprise_page.content as item>
                        <tr>
                            <#--
                            <td>
                                <input style="width:15px;height:15px;float:left; margin:0 0 0 10px ;" id="listChkId" type="checkbox" name="listChkId" value="${item_index}"/>
                                <input type="hidden" name="listId" id="listId" value="${item.id}">
                            </td>
                            -->
                            <td>${item.enterpriseTitle!''}</td>
                            <td style="color:#0ab2cb;">${item.area!''}</td>
                            <td style="color:#e67817;">${item.type!''}</td>
                            <#if statusId??&&statusId == 2>
                            <td><input style="width: 98%;height: 30px;" type="text" id="reason${item.id?c!''}"/></td>
                            </#if>
                            <td>
	                            <#if item.statusId == 2>
	                           	 	<p>已添加</p>
	                            <#else>
	                          	  	<a href="javascript:addEnterprise1(${item.id?c!''},${activityId?c!''},${statusId!''});">添加推荐</a>
	                        	</#if>
                        	</td>
                        </tr>
                    </#list>
                </#if>     
                </table>
            </div>
            <div class="area_add_btn">
            <!--<input style=" margin-left: 0px; cursor:pointer;" type="submit" value="批量加入预选" />-->
            </div>
        <!--</form>-->
        <div class="page">
        <#if enterprise_page??>
        <#assign PAGE_DATA = enterprise_page>
             <#if PAGE_DATA??>
                 <#if PAGE_DATA.number+1 == 1>
                      <a disabled="disabled"  class="page_next">上一页</a>               
                 <#else>
                     <a href="/region/recommendEnterprise?page=${PAGE_DATA.number-1}&id=${activityId?c!''}"  class="page_next">上一页</a>                
                 </#if>
                 
                 <#assign continueEnter=false>
                 
                 <#if PAGE_DATA.totalPages gt 0>
                     <#list 1..PAGE_DATA.totalPages as page>
                         <#if page <= 3 || (PAGE_DATA.totalPages-page) < 3 || (PAGE_DATA.number+1-page)?abs<3 >
                             <#if page == PAGE_DATA.number+1>
                                 <a  class ="current" style="color:#e67817;">${page }</a>
                             <#else>
                                 <a href="/region/recommendEnterprise?page=${page-1}&id=${activityId?c!''}">${page}</a> 
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
                     <a href="/region/recommendEnterprise?page=${PAGE_DATA.number+1}&id=${activityId?c!''}" class="page_last">下一页</a> 
                 </#if>
             </#if>
            <p>共${PAGE_DATA.totalPages!'1'}页  ${PAGE_DATA.totalElements!'1'}条</p>
            </#if>
          </div>
        
        
        
        <div class="list_base2" id="selectedEnterprise">
            <#include "/client/region_selected_enterprise.ftl" />
        </div>
        <div class="area_add_btn">
        <!--    <input style="cursor:pointer;"  type="button" value="批量取消预选" />-->
        </div>
        <#if statusId??&&statusId == 2>
         <form id="upload" enctype="multipart/form-data" action="/client/recommend/upload" method="post">
            <input type="hidden" id="activityId" name="activityId" value="${activity.id?c!''}"></input>
		    <dl class="area_up">
			    <dd><input onclick="location.href='/region/export/recommend?activityId=${activityId?c!''}'" class="area_save_btn01" type="button" value="生成并下载《区县推荐项目汇总表》" /></dd>
			    <dd>
			        <span style="font-size: 14px; margin-left: 20px;">添加文件：</span>
			        <input id="file" style="margin-left: 20px; margin-top: 20px;" name="Filedata" type="file" value="" />
			    </dd>
			    <dd><input  style="margin-left:20px;" class="area_save_btn" type="submit" value="上传" /></dd>
			</dl>
		  </form>	
		</#if>	
        
        
        <input style="cursor:pointer;" class="area_save_btn" style="margin-left:45%;"type="button" onclick="location.href='/region/enterprise/finish?id=${activityId?c!''}&statusId=${statusId!''}'" value="完成" />
    </div> 
    </div>
</div><!--content_end-->
</div><!--main-->
</body>
</html>