<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
	<title>企业列表</title>
	<link rel="shortcut icon" href="/client/images/icon.ico" />
	<link href="/client/css/base.css" rel="stylesheet" type="text/css" />
	<link href="/client/css/area.css" rel="stylesheet" type="text/css" />
	
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
            <dd><a href="#">企业列表</a></dd>
            <dd><a href="#">活动列表</a></dd>
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
                <a href="#">企业列表</a>
            </dd>
            <dt class="crumb_back"><a  href="#">返回上一页</a></dt>
        </dl>
        <div class="area_choice">
        		<span>关键字:</span>
        		<input style="margin: 0 14px 0 0;"  name="keywords"  class="area_text" type="text" value="" />
				<select name="keyword1" style="margin-left: 0px;">
        			<#if region_list??>
        				<#list region_list as item>
        					<option value="${item.title!''}">${item.title!''}</option>
        				</#list>
        			</#if>		
        		</select>
        		<select name="keyword2">
        			<#if enterpriseType_list??>
        				<#list enterpriseType_list as item>
        					<option value="${item.title!''}">${item.title!''}</option>
        				</#list>
        			</#if>		
        		</select>
        		<select name="isEnable">
        			<option value="true">通过</option>
        			<option value="false">未通过</option>
        		</select>
        		<input class="area_Btn02" type="submit" value="确认筛选" />
        </div>
        <div class="area_choice_two">
        	<span>一键筛选:</span>
        	<a href="#">未核验项目</a>
        	<p>丨</p>
        	<a href="#">已核验项目</a>
        	<p>丨</p>
        	<a href="#">已参加每周行活动</a>
        	<p>丨</p>
        	<a href="#">胜出项目</a>

        </div>
		<table class="new_list">
        	<tr class="list_title">
        		<th width="10%">编号</th>
        		<th>名称<th>
        		<th width="15%">地区</th>
        		<th width="15%">注册资本</th>
        		<th width="15%">法人代表</th>
        		<th width="15%">操作</th>
        	</tr>
        <#if enterprise_page??>
        	<#list enterprise_page.content as item>
	        	<tr>
	        		<td>${item.number!''}</td>
	        		<td>${item.title!''}<td>
	        		<td>${item.area!''}</td>
	        		<td style="color:#529c15;">${item.capital!''}万元</td>
	        		<td style="color:#e67817;">${item.representative!''}</td>
	        		<td><#if item.statusId == 0>
	        		     <a href="/region/enterprise/check/${item.id?c!''}">审核</a>
	        		    <#elseif item.statusId == 1>
	        		     <a href="/region/enterprise/check/${item.id?c!''}">取消审核</a>
	        		    </#if>
	        		     | <a href="#">站内信</a>
	        	</tr>
        	</#list>
        </#if>	   
        </table>
    </div> 
    </div>
</div><!--content_end-->
</div><!--main-->
</body>
</html>