<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
	<title>活动列表</title>
	<link rel="shortcut icon" href="images/icon.ico" />
	<link href="/client/css/base.css" rel="stylesheet" type="text/css" />
	<link href="/client/css/list_base.css" rel="stylesheet" type="text/css" />
		<style type="text/css">
		.page{ width: 600px; float: right; margin-top: 30px;}
		.page *{ float: left;}
		.page span{ color: #333333; line-height: 20px; display: block;}
		.page a{  width: 20px; height: 20px;}
		.page .page_next{ width: 60px;}
		.page .page_last{width: 40px;}
		.page p{  margin-left: 10px;}
	</style>

<script src="/client/js/jquery-1.9.1.min.js"></script>
<script src="/client/js/main.js"></script>
<script>
<!--
function unfinish()
{
	alert("有未完成创建的活动！");
}
<#if alert?? &&alert == 1>
window.onload=unfinish;
</#if>
-->
function deleteConfirm() {
    if (!confirm("确认删除活动？")) {
        window.event.returnValue = false;
    }
}

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
            <dd><a href="/activity/create">创建活动</a></dd>
            <dd><a href="/activity/list">活动列表</a></dd>
            
		</dl>
	</div>
<!--right-->
    <div class="right_content">
    <div class="right_box">
    	<dl class="crumb">
        	<dt><a href="#"></a></dt>
            <dd>
            	<p>当前所在位置:</p>
                <a href="#">活动列表</a>
            </dd>
        </dl>
        <!--
       <#if unfinish??>
	       <div class="list_base2" style="padding-top:0;">
				<table class="new_list">
		        	<tr class="list_title">
		        		<th width="30%">未完成创建活动</th>
		        		<th width="20%">地区</th>
		        		<th width="20%">活动类型</th>
		        		<th width="30%">操作</th>
		        	</tr>
		        		<td>${unfinish.title!''}</td>
		        		<td style="color:#0ab2cb;">${unfinish.region!''}</td>
		        		<td style="color:#e67817;">${unfinish.activityType!''}</td>
		        		<td><a href="/activity/edit?id=${unfinish.id?c!''}">管理</a>丨<a href="/activity/check?id=${unfinish.id?c!''}">查看</a>丨<a onclick="javascript:deleteConfirm();"  href="/activity/delete?id=${unfinish.id?c!''}">删除</a></td>
			        </tr>
		        </table>
	        </div>
	    </#if>    
        -->
        <div class="list_base2" style="padding-top:0;">
			<table class="new_list">
	        	<tr class="list_title">
	        		<th width="30%">活动</th>
	        		<th width="13%">地址</th>
	        		<th width="13%">活动类型</th>
	        	<#--	<th width="13%">审核</th> -->
	        		<th width="10%">状态</th>
	        		<th width="20%">操作</th>
	        	</tr>
	        <#if activity_page??>
	        	<#list activity_page.content as item>
		        	<tr>
		        		<#--
		        		<td>
			        		<input style="width:15px;height:15px;float:left; margin:0 0 0 10px ;" id="listChkId" type="checkbox" name="listChkId" value="${item_index}"/>
			        		<input type="hidden" name="listId" id="listId" value="${item.id}">
		        		</td>
		        		-->
		        		<td>${item.title!''}<#if item.statusEx??&&item.statusEx == 0 || !item.statusEx??><img src="/client/images/n11.gif" style="top:2px;left:3px;" title="未选择评委" alt="未选择评委" /></#if></td>
		        		<td style="color:#0ab2cb;">${item.address!''}</td>
		        		<td style="color:#e67817;">${item.activityType!''}</td>
		        		<#--
		        		<#if item.statusId?? && item.statusId == 1>
		        			<td style="color:green;">已审核 </td>
		        		<#else>
		        			<td style="color:purple;"> 未审核 </td>
		        		</#if>
		        		-->
	                    <#if item.timeId??&&item.statusId??>
		                    <#if item.statusId ==0 &&item.timeId ==0>
		                    		 <td>未启动</td>
		                    <#elseif  item.statusId ==0 &&item.timeId ==1>
		                    		 <td style="color:#0ab2cb;">筹备中</td>
		                    <#elseif  item.statusId ==0 &&(item.timeId == 2 ||item.timeId == 3)>
		                    		 <td  style="color:#e67817;">已过期</td>
		                    <#elseif item.statusId ==1 &&( item.timeId ==0 || item.timeId ==1|| item.timeId ==2)>
		                    		 <td  style="color:#529c15;">进行中</td>
		                    <#elseif item.statusId ==2>
		                    	 <td style="color:purple;">活动已结束</td>
		                    <#else>
		                    	<td>&nbsp;</td>	 
		                    </#if>			
		                <#else>
		                	     <td>未启动</td>
		                </#if>    		        		
		        		<td>
		        			<a <#if item.statusId?? && item.statusId == 1>href="javascript:void(0)" style="color : #666;" title="已审核的活动无法修改"<#else> href="/activity/edit?id=${item.id?c!''}" </#if> >编辑信息</a>丨<a href="/activity/check?id=${item.id?c!''}">操作</a>丨<a  onclick="javascript:deleteConfirm();" href="/activity/delete?id=${item.id?c!''}">删除</a>
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
			         <a href="/activity/list?page=${PAGE_DATA.number-1}"  class="page_next">上一页</a>                
			     </#if>
			     
			     <#assign continueEnter=false>
			     
			     <#if PAGE_DATA.totalPages gt 0>
			         <#list 1..PAGE_DATA.totalPages as page>
			             <#if page <= 3 || (PAGE_DATA.totalPages-page) < 3 || (PAGE_DATA.number+1-page)?abs<3 >
			                 <#if page == PAGE_DATA.number+1>
			                     <a  class ="current" style="color:#e67817;">${page }</a>
			                 <#else>
			                     <a href="/activity/list?page=${page-1}">${page}</a> 
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
			         <a href="/activity/list?page=${PAGE_DATA.number+1}" class="page_last">下一页</a> 
			     </#if>
			 </#if>
		  	<p>共${PAGE_DATA.totalPages!'1'}页  ${PAGE_DATA.totalElements!'1'}条</p>
		  	</#if>
		  </div>
		  
		  
    </div> 
    </div>
</div><!--content_end-->
</div><!--main-->
</body>
</html>