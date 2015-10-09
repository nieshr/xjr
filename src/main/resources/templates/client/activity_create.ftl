<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>创建活动</title>
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
            <dd><a href="#">创建活动</a></dd>

		</dl>
	</div>
<!--right-->
    <div class="right_content">
    <div class="right_box">
    	<dl class="crumb">
        	<dt><a href="#"></a></dt>
            <dd>
            	<p>当前所在位置:</p>
                <a href="#">创建活动</a>
                <p>&gt;</p>
                <a href="#">资料填写</a>
                
            </dd>
            <dt class="crumb_back"><a  href="#">返回上一页</a></dt>
        </dl>
        
    </div>  
    <form action="/activity/submit" id="form1" method="post">
    <dl class="active_content">
    	<dd>

    			<div><span>活动名称：</span><input type="text" name="title" value="<#if activity??>${activity.title!''}</#if>" /></div>
    			<div>
    				<span>活动类型：</span>
    				<select name="type" datatype="*">
    				    <#if type_list??>
    				        <#list type_list as item>
    					        <option value="${item.title!''}" <#if activity?? &&activity.type == item.title>selected="selected"</#if>>${item.title!''}</option>
    					    </#list>
    					</#if>        
    				</select>
    			</div>
    			<div>
    			    <span>地区 ：</span>
                    <select name="region" datatype="*">
                        <#if region_list??>
                            <#list region_list as item>
                                <option value="${item.title!''}" <#if activity?? &&activity.region == item.title>selected="selected"</#if>>${item.title!''}</option>
                            </#list>
                        </#if>        
                    </select>
    			</div>
    			<div><span>日期：</span>
	    		
	                    <input name="date" type="text" id="txtAddTime" value="<#if activity??>${activity.date!""}</#if>" class="input date" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',lang:'zh-cn'})" datatype="/^\s*$|^\d{4}\-\d{1,2}\-\d{1,2}\s{1}(\d{1,2}:){2}\d{1,2}$/" errormsg="请选择正确的日期" sucmsg=" ">
	            </div>    
    			
    			<div><span>地址：</span><input type="text" name="address" datatype="*" value="<#if activity??>${activity.address!''}</#if>" /></div>
    			<div><span>主题：</span><input type="text" name="theme" datatype="*"  value="<#if activity??>${activity.theme!''}</#if>" /></div>
    			
				<div><span>简介：</span><textarea  name="introduction" datatype="*" ><#if activity??>${activity.introduction!''}</#if></textarea></div>
    			<div>
    				<span>筹备开始时刻：</span>
                        <input name="prepareOn" type="text" id="txtAddTime" value="<#if activity??>${activity.prepareOn!""}</#if>" class="input date" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',lang:'zh-cn'})" datatype="/^\s*$|^\d{4}\-\d{1,2}\-\d{1,2}\s{1}(\d{1,2}:){2}\d{1,2}$/" errormsg="请选择正确的日期" sucmsg=" ">
    				<span>筹备结束时刻：</span>
                        <input name="prepareOff" type="text" id="txtAddTime" value="<#if activity??>${activity.prepareOff!""}</#if>" class="input date" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',lang:'zh-cn'})" datatype="/^\s*$|^\d{4}\-\d{1,2}\-\d{1,2}\s{1}(\d{1,2}:){2}\d{1,2}$/" errormsg="请选择正确的日期" sucmsg=" ">
    			</div>
    			<div>
    			    <span>活动结束时刻：</span>
                    <input name="eventEnd" type="text" id="txtAddTime" value="<#if activity??>${activity.eventEnd!""}</#if>" class="input date" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',lang:'zh-cn'})" datatype="/^\s*$|^\d{4}\-\d{1,2}\-\d{1,2}\s{1}(\d{1,2}:){2}\d{1,2}$/" errormsg="请选择正确的日期" sucmsg=" ">
    			</div>
    			<div><span>添加文件：</span><input type="file" value="" /></div>
    			<div>
    				<span style="margin-top: 10px;">相关下载：</span>
    				<ul class="active_add_file">
    					<li>
    						<img src="images/active_file.png" />
    						<p class="p01">1.文件sdfsdfsdfsdfsdfsdfsdfsdfdfdsf名字</p>
    						<a href="#">下载</a>
    					</li>
    					<li>
    						<img src="images/active_file.png" />
    						<p class="p01">1.文件sdfsdfsdfsdfsdfsdfsdfsdfdfdsf名字</p>
    						<a href="#">下载</a>
    					</li>
    					<li>
    						<img src="images/active_file.png" />
    						<p class="p01">1.文件sdfsdfsdfsdfsdfsdfsdfsdfdfdsf名字</p>
    						<a href="#">下载</a>
    					</li>
    				</ul>
    			</div>
    			<div>
    				<span style="margin-top: 10px;">项目列表：</span>
    				<ul class="active_project_list">
    				    <#if enterprise_list??>
    				        <#list enterprise_list as item>
		    					<li>
		    						<a class="p01">${item_index}.${item.title!''}</a>
		    					</li>
    					    </#list>
    					</#if>    
    					<input style="width:100px; height:30px;cursor:pointer; line-height: 30px; border: none;background:white url(images/active_add_project.png) no-repeat 10px; padding-left: 13px;" type="button" value="添加项目" />
    				</ul>
    			</div>
    			
    			<div>
    				<span style="margin-top: 10px;">评委专家：</span>
    				<ul class="active_project_list">
    					<li>
    						<a class="p01">1.如何使用好微波炉sad护发素</a>

    					</li>
    					<li>
    						<a class="p01">1.如何使用好微波炉sad护发素</a>

    					</li>
    					<li>
    						<a class="p01">1.如何使用好微波炉sad护发素</a>

    					</li>
    					<li>
    						<a class="p01">1.如何使用好微波炉sad护发素</a>
    					</li>
    					<li>
    						<a class="p01">1.如何使用好微波炉sad护发素</a>
    					</li>

    					<input style="width:100px; height:30px; line-height: 30px; border: none;background:white url(images/active_add_project.png) no-repeat 10px; padding-left: 13px;" type="button" value="添加评委" />
    				</ul>
    			</div>
    			
    
    	<dt style=" margin-top: 40px;" class="dt05">
    		<input type="submit" value="创建" />
    	</dt>
    </dl>
    </form>
    </div>
</div><!--content_end-->
</div><!--main-->
</body>
</html>
