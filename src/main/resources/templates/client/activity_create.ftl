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
    
    <#if upload??&&upload == 1>
        var height = $(window).height();
       $('html,body').animate({scrollTop:height+'px'},100);
    </#if>    
});

$(function(){
    $('#selectEnterprise').click(function(){
    var activitydate = $("#date").val()
    var  prepareOn = $("#prepareOn").val()
    var  prepareOff = $("#prepareOff").val()
    var  eventEnd  = $("#eventEnd").val()
    if (activitydate == ""||prepareOn == ""||prepareOff == "" || eventEnd == "")
    {
    	alert("请完整填写资料！")
    }
    else{    
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
		                     location.href="/activity/selectEnterprise?activityId="+data.activityId+"&area="+$("#region").val();
		                 }
		                 else 
		                 {
		                     alert(data.msg);
		                 }
                      }
         });
     }    
    });
});

$(function(){
    $('#selectExpert').click(function(){
    var activitydate = $("#date").val()
    var  prepareOn = $("#prepareOn").val()
    var  prepareOff = $("#prepareOff").val()
    var  eventEnd  = $("#eventEnd").val()
    if (activitydate == ""||prepareOn == ""||prepareOff == "" || eventEnd == "")
    {
    	alert("请完整填写资料！")
    }
    else{
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
                             location.href="/activity/selectExpert?activityId="+data.activityId;
                         }
                         else 
                         {
                             alert(data.msg);
                         }
                      }
         });
    } 
    });
});

function activityPass(activityId)
{
         $.ajax({
             type: "GET",
             url: "/activity/pass",
             contentType: "application/json; charset=utf-8",
             data: {"activityId" : activityId},
             dataType: "json",
             success: function(data){
                         if (data.code == 0)
                         {
                             alert("审核成功！");
                             location.reload();
                         }
                         else 
                         {
                             alert(data.msg);
                         }
                      }
         });
}

function sendSms(id,activityId,roleId)
{
         $.ajax({
             type: "GET",
             url: "/activity/sendSms",
             contentType: "application/json; charset=utf-8",
             data: {"id":id,"activityId" : activityId,"roleId":roleId},
             dataType: "json",
             success: function(data){
                         if (data.code == 0)
                         {
                             alert("已发送！");
                         }
                         else 
                         {
                             alert(data.msg);
                         }
                      }
         });
}

function passCheck(activityId) {
    if (confirm("已审核过该推荐表，点击将更新列表到评分表并重新发送通知，确认吗？")) {
         $.ajax({
             type: "GET",
             url: "/activity/pass",
             contentType: "application/json; charset=utf-8",
             data: {"activityId" : activityId},
             dataType: "json",
             success: function(data){
                         if (data.code == 0)
                         {
                             alert("推荐列表更新成功，已向各企业发送活动通知！");
                             location.reload();
                         }
                         else 
                         {
                             alert(data.msg);
                         }
                      }
         });
    }
}


function sortUp(id , activityId)
{
         $.ajax({
             type: "GET",
             url: "/activity/sortUp",
             contentType: "application/json; charset=utf-8",
             data: {"id":id ,"activityId" : activityId},
             dataType: "json",
             success: function(data){
                         if (data.code == 0)
                         {
                             location.reload();
                         }
                         else 
                         {
                             alert(data.msg);
                         }
                      }
         });
}

function sortDown(id , activityId)
{
         $.ajax({
             type: "GET",
             url: "/activity/sortDown",
             contentType: "application/json; charset=utf-8",
             data: {"id":id ,"activityId" : activityId},
             dataType: "json",
             success: function(data){
                         if (data.code == 0)
                         {
                             location.reload();
                         }
                         else 
                         {
                             alert(data.msg);
                         }
                      }
         });
}


function submitCheck()
{
    var filedata = $("#file").val();

    if (filedata == "")
    {
        alert("请添加文件！")
    }
    else{
        $("#upload").submit();
    }   
}

function pptSubmitCheck()
{
    var filedata = $("#pptfile").val();

    if (filedata == "")
    {
        alert("请添加文件！")
    }
    else{
        $("#pptupload").submit();
    }   
}


function enterpptSubmitCheck()
{
    var filedata = $("#enterpptfile").val();

    if (filedata == "")
    {
        alert("请添加文件！")
    }
    else{
        $("#enterpptupload").submit();
    }   
}

function done()
{
	alert("上传成功！");
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
	            <dd><a href="/region/enterprise/list">企业列表</a></dd>
	            <dd><a href="/region/activity/list">活动列表</a></dd>
	            <dd><a href="">档案跟踪</a></dd>
	        </dl>
	   <#elseif mark?? && mark == "expert">
			<dl class="nav">
            <dd><a href="/expert/enterprise/list">活动列表</a></dd>
            <dd><a href="/expert/enterprises">辅导企业</a></dd>
            <dd><a href="/expert/lyfd">路演辅导</a></dd>
			</dl>	   
        <#elseif mark?? && mark == "enterprise">
          <dl class="nav">
          <dd><a href="/enterprise/check">基本资料</a></dd>
          <dd><a href="/enterprise/activity/list">活动列表</a></dd>
          <dd><a href="/enterprise/project">申请展示</a></dd>
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
    <form action="/activity/submit" id="form1">
    <dl class="active_content">
    	<dd>
            <#if activity??>
                <input type="hidden" name="id" id="id" value="${activity.id?c!''}"/>
                <input type="hidden" name="statusEn" id="statusEn" value="${activity.statusEn!''}"/>
                <input type="hidden" name="statusEx" id="statusEx" value="${activity.statusEx!''}"/>
                <input type="hidden" name="fileUrl" id="fileUrl" value="<#if activity.fileUrl??>${activity.fileUrl!''}</#if>"/>
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
    				<span style="margin-top: 10px;">预选项目：</span>
    				<ul class="active_project_text">
    				    <#if selected_enterprise_list??>
    				        <#list selected_enterprise_list as item>
		    					<li>
		    						<p class="p01" style="width: 130px; float: left;text-align:left;">${item_index+1}.${item.enterpriseTitle!''}</p>
		    						<a style="display:block;  width:100px;"></a>
		    						<a href="/activity/enterprise/check/${item.enterpriseId?c!''}" target=_blank>查看</a>
		    						<#--
		                            <a>丨</a>
		                            <a href="/enterprise/grade?activityId=${item.activityId?c!''}&enterpriseId=${item.enterpriseId?c!''}">得分</a>
		                            <a>丨</a>
		                            <a href="/activity/getCoach?enterpriseId=${item.enterpriseId?c!''}&activityId=${item.activityId?c!''}">分配路演辅导</a>
		                            <a>丨</a>
		                            <a href="#">下载</a>
		                            <a style="display:block;  width:80px;"></a>
		                            <p class="p02">辅导专家，李专家</p>
		    						-->
		    					</li>
    					    </#list>
    					</#if>    
                        <#if pagetype??&& pagetype == "check">
                        <#else>
    					   <input id="selectEnterprise" style="width:100px; height:30px;cursor:pointer; line-height: 30px; border: none;background:white url(images/active_add_project.png) no-repeat 10px; padding-left: 13px;" type="button" value="添加项目" />
    				    </#if>
    				</ul>
    			</div>
 				<#if recommend_list??>
    			<div>
    				<span style="margin-top: 10px;">推荐项目：</span>
    				<ul class="active_project_text">
    				    <#if recommend_list??>
    				        <#list recommend_list as item>
		    					<li>
		    						<p class="p01" style="width: 130px; float: left;text-align:left;">${item_index+1}.${item.enterpriseTitle!''}</p>
		    						<a style="display:block;  width:100px;"></a>
		    						<a href="/activity/enterprise/check/${item.enterpriseId?c!''}" target=_blank>查看</a>
		                            <a>丨</a>
		                            <a <#if item.isGrade??&&item.isGrade> href="/enterprise/grade/?activityId=${item.activityId?c!''}&enterpriseId=${item.enterpriseId?c!''}"<#else>style="color:#666;" </#if>>得分</a>
		                            <#if mark??&&mark="activity">
			                            <a>丨</a>
			                            <#if item.statusId??&&item.statusId==1>
			                              <a href="/activity/getCoach?enterpriseId=${item.enterpriseId?c!''}&activityId=${item.activityId?c!''}">分配路演辅导</a>
			                            <#else>
			                              <a style="color:#666;" href="javascript:void(0)">分配路演辅导</a>
			                            </#if> 
			                            <a>丨</a>
			                            <a <#if item.pptUrl??>href="${item.pptUrl}" <#else>style="color:#666;"</#if>>PPT下载</a>
			                            <a>丨</a>
			                            <#if activity??&&activity.statusId??&&activity.statusId==1>
				                            <a href="javascript:sendSms(${item.enterpriseId?c!''},${item.activityId?c!''},1);">短信通知</a>
				                            <a>丨</a> 
			                            </#if>
			                            <#if item_has_next>
			                           	 	<a href="javascript:sortDown(${item.id?c!''} , ${item.activityId?c!''});"><img style="width:10px;height:13px;margin-top: 8px;"src="/client/images/down1.png" alt="下移" title="下移排序"/></a>
			                            </#if>		                            
			                            <#if item_index != 0>
			                           	 	<a href="javascript:sortUp(${item.id?c!''} , ${item.activityId?c!''});"><img style="width:10px;height:13px;margin-top: 8px; <#if item_has_next>margin-left:3px;</#if>" src="/client/images/up1.png" alt="上移" title="上移排序"/></a>
			                            </#if>
			                        <#else>
			                            <a>丨</a>
                                        <a <#if item.pptUrl??>href="${item.pptUrl}" <#else>style="color:#666;"</#if>>PPT下载</a>
			                        </#if>    
		                            <a style="display:block;  width:80px;"></a>
		                            <p class="p02"><#if item.coachName??>辅导专家，${item.coachName!''}</#if></p>
		    						
		    					</li>
    					    </#list>
    					</#if>    
                        <#if pagetype??&& pagetype == "check">
                        	<#if activity??&&activity.fileUrl??>
	                        	<input  style="width:100px; height:30px;cursor:pointer; line-height: 30px; border: none;background-color:#e67817;color:#fff;" type="button" onclick="location.href='/download/data?name=${activity.fileUrl!''}'" value="推荐表下载" />
                        	<#else>
                        		<input  style="width:100px; height:30px;cursor:pointer; line-height: 30px; border: none;background-color:#666;color:#fff;" type="button"  value="推荐表下载" />
                        	</#if>	
                        	<#if mark??&&mark="activity">
	                        	<#if activity??&&activity.statusId??&&activity.statusId==1>
	                        		<input  style="width:100px; height:30px;cursor:pointer; line-height: 30px; border: none;background-color:#666;color:#fff; " type="button" onclick="javascript:passCheck(${activity.id?c!''});"  value="已审核" />
	                        	<#elseif activity??&&activity.statusId??&&activity.statusId==0>	
	                        		<input  style="width:100px; height:30px;cursor:pointer; line-height: 30px; border: none;background-color:#e67817;color:#fff; " type="button" onclick="javascript:activityPass(${activity.id?c!''});" value="通过审核" />
	                        	</#if>
                        	</#if>
                        <#else>
                        	
    					    	<input id="selectEnterprise" style="width:100px; height:30px;cursor:pointer; line-height: 30px; border: none;background:white url(images/active_add_project.png) no-repeat 10px; padding-left: 13px;" type="button" value="添加项目" />
    				    
    				    </#if>
    				</ul>
    			</div> 
    			</#if>
    			<div style="margin-top:50px;">
    				<span style="margin-top: 10px;">评委专家：</span>
    				<ul class="active_project_text">
    		    	    <#if selected_expert_list??>
    				        <#list selected_expert_list as item>
		    					<li>
		    						<p class="p01" style="  width: 130px;float: left; text-align: left;">${item_index+1}.${item.name!''}</p>
		    						<a style="display:block;  width:100px;"></a>
                                    <a href="/expert/search/grade?activityId=${activity.id?c!''}&expertId=${item.expertId?c!''}" title="查看该评委的评分详情" target="_blank">评分情况</a>
                                    <#if activity??&&activity.statusId??&&activity.statusId==1&&mark??&&mark="activity">
	                                    <a>丨</a> 
	                                    <a href="javascript:sendSms(${item.expertId?c!''},${item.activityId?c!''},3);">短信通知</a>
                                    </#if>
		    					</li>
    					    </#list>
    					</#if>    
                        <#if pagetype??&& pagetype == "check">
                        <#else>
    					   <input id="selectExpert" style="cursor:pointer;width:100px; height:30px; line-height: 30px; border: none;background:white url(images/active_add_project.png) no-repeat 10px; padding-left: 13px;" type="button"  value="添加评委" />
    				    </#if>
    				</ul>
    			</div>
            <!-- 评分汇总 -->
            	<#if activity??&&activity.statusId??&&activity.statusId==1>
                    <div>
                        <span style="margin-top: 6px;">路演结果：</span>
                        <ul class="active_project_text">
                            <li>
                                <a href="/activity/getGrade?activityId=${activity.id?c!''}" target=_blank class="p01">查看排名</a>
                                <a style="display:block;  width:60px;"></a>
                            </li>
                        </ul>
                    </div>
                </#if>    
    			<!-- 评分汇总 end-->
     <#if pagetype?? && pagetype == "check">
     <#else>
    	<dt style=" margin-top: 40px;" class="dt05">
    	   <input type="submit" value="保存" style="cursor:pointer;"/>
    	</dt>
     </#if>
    </dl>
    </form>
    <#if activity??>
     <h2 style="margin:0 0 20px 20px;">附件：</h2>
    <dl class="active_content">
    	<dd>
    	 <#if pagetype?? && pagetype == "check">
    	 <#else>
		<form id="upload" enctype="multipart/form-data" action="/client/activity/upload" method="post">
		<input type="hidden" id="id" name="id" <#if activity??>value="${activity.id?c}"</#if>></input>
		<div>
			<span>添加文件：</span>
			<input name="Filedata" type="file" id="file" value="" />
			<input  style="margin-left:20px;    border-radius: 8px;" class="area_save_btn" type="button" onclick="javascript:submitCheck();" value="上传" />
		</div>
		</form>
		</#if>
		<#if activity?? && activity.download??>
			<div>
				<span style="margin-top: 10px;">相关下载：</span>
				<ul class="active_add_file">
					<li>
						<img src="/client/images/active_file.png" />
						<p class="p01">${activity.download!''}</p>
						<a href="/download/data?name=${activity.download!''}">下载</a>
					</li>
				</ul>
			</div>
	    </#if>	
		</dl>
		
		<#if pagetype??&&pagetype == "check">
    <dl class="active_content">
        <dd>
        <input type="button" value="PPT模板下载" onclick="location.href='/download/data?name=${activity.pptUrl!''}'"  style="margin:10px 0 30px 48px;    border-radius: 8px;
                                                float: left;
                                                height: 26px;
                                                line-height: 26px;
                                                width: 170px;"></input>
        </dd>
        </dl>
		<#else>
		    <dl class="active_content" style="margin-top:50px;">
        <dd>
		<form id="pptupload" enctype="multipart/form-data" action="/client/activity/pptupload" method="post">
        <input type="hidden" id="id" name="id" <#if activity??>value="${activity.id?c}"</#if>></input>
        <div>
            <span>添加PPT模板：</span>
            <input name="Filedata" type="file" id="pptfile" value="" />
            <input type="button" value="PPT模板上传" onclick="javascript:pptSubmitCheck();"  style="margin:-5px 0 30px 48px;
                                                             border-radius: 8px;
			                                                float: left;
			                                                height: 26px;
			                                                line-height: 26px;
			                                                width: 170px;"></input>
        
        </div>
        </form>
        <#if activity??&&activity.pptUrl??>
             <div>
                <span style="margin-top: 10px;">已上传PPT模板：</span>
                <ul class="active_add_file">
                    <li>
                        <img src="/client/images/active_file.png" />
                        <p class="p01">${activity.pptUrl!''}</p>
                        <a href="/download/data?name=${activity.pptUrl!''}">下载</a>
                    </li>
                </ul>
            </div>
        </#if>

		</#if>
		<#if mark??&&mark == "enterprise">
		<dl class="active_content" style="margin-top:50px;">
        <dd>
		<form id="enterpptupload" enctype="multipart/form-data" action="/client/enterprise/pptupload" method="post">
        <input type="hidden" name="id" <#if enterprise??>value="${enterprise.id?c}"</#if>></input>
         <input type="hidden"  name="activityId" <#if activity??>value="${activity.id?c}"</#if>></input>
        <div>
            <span>上传PPT：</span>
            <input name="Filedata" type="file" id="enterpptfile" value="" />
            <input type="button" value="PPT上传" onclick="javascript:enterpptSubmitCheck();"  style="margin:-5px 0 30px 48px;
                                                             border-radius: 8px;
			                                                float: left;
			                                                height: 26px;
			                                                line-height: 26px;
			                                                width: 170px;"></input>
        
        </div>
        </form>
        	<#if enterprise.pptUrl??>
             <div>
                <span style="margin-top: 10px;">已上传PPT模板：</span>
                <ul class="active_add_file">
                    <li>
                        <img src="/client/images/active_file.png" />
                        <p class="p01">${enterprise.pptUrl!''}</p>
                        <a href="/download/data?name=${enterprise.pptUrl!''}">下载</a>
                    </li>
                </ul>
            </div>
            </#if>
        </#if>
	</#if>
		
    </div>
</div><!--content_end-->
</div><!--main-->
</body>
</html>
