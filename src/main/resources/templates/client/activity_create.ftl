<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>活动</title>
<link rel="shortcut icon" href="/client/images/icon.ico" />
<link href="/client/css/base.css" rel="stylesheet" type="text/css" />
<link href="/client/css/active.css" rel="stylesheet" type="text/css" />
<link href="/mag/style/WdatePicker.css" rel="stylesheet" type="text/css">

<script src="/client/js/jquery-1.9.1.min.js"></script>
<script src="/client/js/main.js"></script>
<script type="text/javascript" src="/mag/js/WdatePicker.js"></script>
<script type="text/javascript" src="/client/js/Validform_v5.3.2_min.js"></script>
<script>
$(document).ready(function(){

    $("#form1").Validform({
            tiptype:4,
            ajaxPost:true,
            callback: function (data) { 
                if (data.code == 0)
                {
                    alert("创建成功");
                    location.href="/activity/list";
                }
                else 
                {
                    alert(data.msg);
                }
             }
    });
});

$(function(){
    $('#selectEnterprise').click(function(){
         $.ajax({
             type: "GET",
             url: "/activity/bufferEn",
             contentType: "application/json; charset=utf-8",
             data: {id:$("#id").val(), 
            	   title:$("#title").val(), 
            	   activityType:$("#activityType").val(),
            	   region:$("#region").val(),
            	   date:$("#date").val(),
            	   address:$("#address").val(),
            	   theme:$("#theme").val(),
            	   introduction:$("#introduction").val(),
            	   prepareOn:$("#prepareOn").val(),
            	   prepareOff:$("#prepareOff").val(),
            	   eventEnd:$("#eventEnd").val()},
             dataType: "json",
             success: function(data){
		                 if (data.code == 0)
		                 {
		                     location.href="/activity/selectEnterprise";
		                 }
		                 else 
		                 {
		                     alert(data.msg);
		                 }
                      }
         });
    });
});

$(function(){
    $('#selectExpert').click(function(){
         $.ajax({
             type: "GET",
             url: "/activity/bufferEn",
             contentType: "application/json; charset=utf-8",
             data: {id:$("#id").val(), 
                   title:$("#title").val(), 
                   activityType:$("#activityType").val(),
                   region:$("#region").val(),
                   date:$("#date").val(),
                   address:$("#address").val(),
                   theme:$("#theme").val(),
                   introduction:$("#introduction").val(),
                   prepareOn:$("#prepareOn").val(),
                   prepareOff:$("#prepareOff").val(),
                   eventEnd:$("#eventEnd").val()},
             dataType: "json",
             success: function(data){
                         if (data.code == 0)
                         {
                             location.href="/activity/selectExpert";
                         }
                         else 
                         {
                             alert(data.msg);
                         }
                      }
         });
    });
});


function done()
{
	alert("上传成功！");
	location.reload();
}
<#if done?? &&done == 1>
window.onload=done;
</#if>

</script>
<style>
.Validform_wrong {  background-position: 20px center;}
.Validform_right {  background-position: 7px center;}
</style>
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
	   <#if mark?? && mark == "region">
		   <dl class="nav">
	            <dd><a href="/region/enterprise/create">企业列表</a></dd>
	            <dd><a href="/region/activity/list">活动列表</a></dd>
	            <dd><a href="">档案跟踪</a></dd>
	        </dl>
	   <#elseif mark?? && mark == "enterprise">
			<dl class="nav">
	            <dd><a href="/enterprise/check">基本资料</a></dd>
	            <dd><a href="#">活动列表</a></dd>
	            <dd><a href="#">申请展示</a></dd>
			</dl>	        
	   <#else>
			<dl class="nav">
	            <dd><a href="/activity/create">创建活动</a></dd>
	            <dd><a href="/activity/list">活动列表</a></dd>
			</dl>
		</#if>
	</div>
<!--right-->
    <div class="right_content">
    <div class="right_box">
    	<dl class="crumb">
        	<dt><a href="#"></a></dt>
            <dd>
            	<p>当前所在位置:</p>
            	<#if mark?? && mark == "region">
            	   <a href="#">查看活动</a>
            	<#else>
                <a href="#">创建活动</a>
                <p>&gt;</p>
                <a href="#">资料填写</a>
                </#if>
            </dd>
            <dt class="crumb_back"><a  href="javascript:history.go(-1);">返回上一页</a></dt>
        </dl>
        
    </div>  
    <form action="/activity/submit" id="form1" method="post">
    <dl class="active_content">
    	<dd>
            <#if activity??>
                <input type="hidden" name="id" id="id" value="${activity.id?c!''}"/>
                <input type="hidden" name="statusEn" id="statusEn" value="${activity.statusEn!''}"/>
                <input type="hidden" name="statusEx" id="statusEx" value="${activity.statusEx!''}"/>
            </#if>
    			<div><span>活动名称：</span><input <#if pagetype??&& pagetype == "check">disabled=""</#if> type="text" name="title" id="title" datatype="*"value="<#if activity??>${activity.title!''}</#if>" /></div>
    			<div>
    				<span>活动类型：</span>
    				<select name="activityType" id="activityType">
    				    <#if activityType_list??>
    				        <#list activityType_list as item>
    					        <option value="${item.title!''}" <#if activity??&&activity.activityType?? &&activity.activityType == item.title>selected="selected"</#if> <#if pagetype??&& pagetype == "check">disabled=""</#if>>${item.title!''}</option>
    					    </#list>
    					</#if>        
    				</select>
    			</div>
    			<div>
    			    <span>地区 ：</span>
                    <select name="region" id="region" >
                        <#if region_list??>
                            <#list region_list as item>
                                <option value="${item.title!''}" <#if activity?? &&activity.region == item.title>selected="selected"</#if>  <#if pagetype??&& pagetype == "check">disabled=""</#if>>${item.title!''}</option>
                            </#list>
                        </#if>        
                    </select>
    			</div>
    			<div><span>日期：</span>
	    		
	                    <input <#if pagetype??&& pagetype == "check">disabled=""</#if> name="date" type="text" id="date" value="<#if activity??>${activity.date!""}</#if>" class="input date" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',lang:'zh-cn'})" datatype="/^\s*$|^\d{4}\-\d{1,2}\-\d{1,2}\s{1}(\d{1,2}:){2}\d{1,2}$/" errormsg="填写正确格式" sucmsg=" " >
	            </div>    
    			
    			<div><span>地址：</span><input <#if pagetype??&& pagetype == "check">disabled=""</#if> type="text" name="address" id="address" datatype="*" value="<#if activity??>${activity.address!''}</#if>" /></div>
    			<div><span>主题：</span><input <#if pagetype??&& pagetype == "check">disabled=""</#if> type="text" name="theme" id="theme" datatype="*"  value="<#if activity??>${activity.theme!''}</#if>" /></div>
    			
				<div><span>简介：</span><textarea <#if pagetype??&& pagetype == "check">disabled=""</#if> name="introduction" id="introduction" datatype="*" ><#if activity??>${activity.introduction!''}</#if></textarea></div>
    			<div>
    				<span>筹备开始时刻：</span>
                        <input <#if pagetype??&& pagetype == "check">disabled=""</#if> name="prepareOn" id="prepareOn" type="text"  value="<#if activity??>${activity.prepareOn!""}</#if>" class="input date" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',lang:'zh-cn'})" datatype="/^\s*$|^\d{4}\-\d{1,2}\-\d{1,2}\s{1}(\d{1,2}:){2}\d{1,2}$/" errormsg="填写正确格式" sucmsg=" ">
    				<span>筹备结束时刻：</span>
                        <input <#if pagetype??&& pagetype == "check">disabled=""</#if> name="prepareOff" id="prepareOff" type="text" value="<#if activity??>${activity.prepareOff!""}</#if>" class="input date" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',lang:'zh-cn'})" datatype="/^\s*$|^\d{4}\-\d{1,2}\-\d{1,2}\s{1}(\d{1,2}:){2}\d{1,2}$/" errormsg="填写正确格式" sucmsg=" ">
    			</div>
    			<div>
    			    <span>活动结束时刻：</span>
                    <input <#if pagetype??&& pagetype == "check">disabled=""</#if> name="eventEnd" id="eventEnd" type="text" value="<#if activity??>${activity.eventEnd!""}</#if>" class="input date" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',lang:'zh-cn'})" datatype="/^\s*$|^\d{4}\-\d{1,2}\-\d{1,2}\s{1}(\d{1,2}:){2}\d{1,2}$/" errormsg="填写正确格式" sucmsg=" ">
    			</div>
    			<div>
    				<span style="margin-top: 10px;">项目列表：</span>
    				<ul class="active_project_list">
    				    <#if selected_enterprise_list??>
    				        <#list selected_enterprise_list as item>
		    					<li>
		    						<a class="p01">${item_index+1}.${item.enterpriseTitle!''}</a>
		    					</li>
    					    </#list>
    					</#if>    
                        <#if pagetype??&& pagetype == "check">
                        <#else>
    					   <input id="selectEnterprise" style="width:100px; height:30px;cursor:pointer; line-height: 30px; border: none;background:white url(images/active_add_project.png) no-repeat 10px; padding-left: 13px;" type="button" value="添加项目" />
    				    </#if>
    				</ul>
    			</div>
    			
    			<div style="margin-top:50px;">
    				<span style="margin-top: 10px;">评委专家：</span>
    				<ul class="active_project_list">
    		    	    <#if selected_expert_list??>
    				        <#list selected_expert_list as item>
		    					<li>
		    						<a class="p01">${item_index+1}.${item.name!''}</a>
		    					</li>
    					    </#list>
    					</#if>    
                        <#if pagetype??&& pagetype == "check">
                        <#else>
    					   <input id="selectExpert" style="cursor:pointer;width:100px; height:30px; line-height: 30px; border: none;background:white url(images/active_add_project.png) no-repeat 10px; padding-left: 13px;" type="button" onclick="location.href='/activity/selectExpert'" value="添加评委" />
    				    </#if>
    				</ul>
    			</div>
    			
     <#if pagetype?? && pagetype == "check">
     <#else>
    	<dt style=" margin-top: 40px;" class="dt05">
    	   <input type="submit" value="创建" style="cursor:pointer;"/>
    	</dt>
     </#if>
    </dl>
    </form>
     <h2 style="margin:0 0 20px 20px;">附件：</h2>
    <dl class="active_content">
    	<dd>
		<form id="upload" enctype="multipart/form-data" action="/client/activity/upload" method="post">
		<input type="hidden" id="id" name="id" value="${activity.id?c!''}"></input>
		<div>
			<span>添加文件：</span>
			<input name="Filedata" type="file" id="file" value="" />
			<input  style="margin-left:20px;" class="area_save_btn" type="submit" value="上传" />
		</div>
		</form>
		<div>
			<span style="margin-top: 10px;">相关下载：</span>
			<ul class="active_add_file">
			<#if activity??>
				<li>
					<img src="/client/images/active_file.png" />
					<p class="p01">${activity.fileUrl!''}</p>
					<a href="/download/data?name=${activity.fileUrl!''}">下载</a>
				</li>
			</#if>	
			</ul>
		</div>
		</dl>
		
		
    </div>
</div><!--content_end-->
</div><!--main-->
</body>
</html>
