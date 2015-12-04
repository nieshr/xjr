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
	<title>活动管理员-添加项目</title>
	<link rel="shortcut icon" href="/client/images/icon.ico" />
	<link href="/client/css/base.css" rel="stylesheet" type="text/css" />
	<link href="/client/css/area.css" rel="stylesheet" type="text/css" />

<script src="/client/js/jquery-1.9.1.min.js"></script>
<script src="/client/js/main.js"></script>
<script>
function searchSubmit()
{
	$("#searchform").submit();
}
   document.onkeydown = function(event){
	    if((event.keyCode || event.which) == 13){
	        $("#selectSubmit").click();
	    }
   }
   
 //全选取消按钮函数-预选
function checkAll(chkobj) {
    if ($(chkobj).text() == "全选") {
        $(chkobj).children("span").text("取消");
        $(".checkall input:enabled").prop("checked", true);
    } else {
        $(chkobj).children("span").text("全选");
        $(".checkall input:enabled").prop("checked", false);
    }
}

 //全选取消按钮函数-取消
function checkAll2(chkobj) {
    if ($(chkobj).text() == "全选") {
        $(chkobj).children("span").text("取消");
        $(".checkall2 input:enabled").prop("checked", true);
    } else {
        $(chkobj).children("span").text("全选");
        $(".checkall2 input:enabled").prop("checked", false);
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
				<p>&gt;</p>
                <a href="#">添加企业/团队</a>
            </dd>
            <dt class="crumb_back"><a  href="javascript:history.go(-1);">返回上一页</a></dt>
        </dl>
        <div class="area_choice">
        	<form action="/activity/selectEnterprise" id="searchform">
        	<input type="hidden" name="activityId" value="${activityId?c!''}" />
        		<span>关键字:</span>
        		<input style="margin:0 14px 0 0;" class="area_text" name="keywords" type="text" value="<#if keywords??&&keywords?length gt 0>${keywords}</#if>" />
        		<select name="area" style="margin-left: 0px;"  onchange="javascript:searchSubmit(this);">
        			<option value="">区县</option>
        			<#if region_list??>
        				<#list region_list as item>
        					<option value="${item.title!''}" <#if area??&&area?length gt 0&&area==item.title>selected="selected"</#if>>${item.title!''}</option>
        				</#list>
        			</#if>		
        		</select>
        		<select name="type" onchange="javascript:searchSubmit(this);">
        			<option value="">行业归属</option>
        			<#if enterpriseType_list??>
        				<#list enterpriseType_list as item>
        					<option value="${item.title!''}" <#if type??&&type?length gt 0&&type==item.title>selected="selected"</#if>>${item.title!''}</option>
        				</#list>
        			</#if>		
        		</select>
        		<select name="formType"  onchange="javascript:searchSubmit(this);">
        			<option value="">类型</option>
        			<option value="0" <#if formType??&&formType == 0>selected="selected"</#if>>企业</option>
        			<option value="1" <#if formType??&&formType == 1>selected="selected"</#if>>团队</option>
        		</select>
        		<input style="cursor:pointer;" id="selectSubmit" class="area_Btn02" type="submit" value="确认筛选" />
        	</form>
        </div>
        
        
       <!-- <form name="addEnterprise" action="/activity/addEnterprises">-->
	        <div class="list_base2" style="padding-top:0;">
	        	<form action="/activity/selectEnterprise" method=post>
	        	<input type="hidden" name="keywords" value="<#if keywords??&&keywords?length gt 0>${keywords}</#if>">
	        	<input type="hidden" name="area" value="<#if area??&&area?length gt 0>${area}</#if>">
	        	<input type="hidden" name="type" value="<#if type??&&type?length gt 0>${type}</#if>">
	        	<input type="hidden" name="formType" value="<#if formType??&&formType?length gt 0>${formType}</#if>">
	        	<input type="hidden" name="__ACTION" value="candidate">
	        	<input type="hidden" name="activityId" value="${activityId?c!''}">
	        	<input type="hidden" name="page" value="<#if page??>${page!''}"</#if>>
				<table class="new_list">
		        	<tr class="list_title">
		        		<th></th>
		        		<th width="40%">备选企业列表</th>
		        		<th width="15%">地区</th>
		        		<th width="25%">行业归属</th>
		        		<th width="20%">操作</th>
		        	</tr>
		        <#if enterprise_page??>
		        	<#list enterprise_page.content as item>
			        	<tr>
			        		<td>
			        		<span class="checkall" style="vertical-align:middle;">
				        		<input style="width:15px;height:15px;float:left; margin:0 0 0 10px ;" <#if item.isSelect??&&item.isSelect&&item.selectActivityId??&&item.selectActivityId==activityId>disabled=""</#if> id="listChkId" type="checkbox" name="listChkId" value="${item_index}"/>
				        		<input type="hidden" name="listId" id="listId" value="${item.id}">
				        	</span>	
			        		</td>
			        		<td style="text-align:left;padding-left:10px ;"><a href="/activity/enterprise/check/${item.id?c!''}" target=_blank >${item.title!''}</a></td>
			        		<td style="color:#0ab2cb;">${item.area!''}</td>
			        		<td style="color:#e67817;">${item.type!''}</td>
			        		<td>
			        		<#if item.isSelect??&&item.isSelect&&item.selectActivityId??&&item.selectActivityId==activityId>
				        		<p>已添加</p>
				        	<#else>
			        			<a class="add${item.id?c}" href="javascript:addEnterprise(${item.id?c!''},${activityId?c!''});">添加</a>
			        		</#if>
			        		</td>
			        	</tr>
		        	</#list>
		        </#if>	   
		        </table>
		        <a class="all" style="margin-left:10px;" href="javascript:;" onclick="checkAll(this);"><i></i><span>全选</span></a>
		        <input style="cursor:pointer;" class="area_batch" style="margin-left:5%;" type="submit"  value="批量添加" />
		        </form>
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
			         <a href="/activity/selectEnterprise?activityId=${activityId?c!''}&page=${PAGE_DATA.number-1}&area=${area!''}&keywords=${keywords!''}&type=${type!''}&formType=${formType!''}"  class="page_next">上一页</a>                
			     </#if>
			     
			     <#assign continueEnter=false>
			     
			     <#if PAGE_DATA.totalPages gt 0>
			         <#list 1..PAGE_DATA.totalPages as page>
			             <#if page <= 3 || (PAGE_DATA.totalPages-page) < 3 || (PAGE_DATA.number+1-page)?abs<3 >
			                 <#if page == PAGE_DATA.number+1>
			                     <a  class ="current" style="color:#e67817;">${page }</a>
			                 <#else>
			                     <a href="/activity/selectEnterprise?activityId=${activityId?c!''}&page=${page-1}&area=${area!''}&keywords=${keywords!''}&type=${type!''}&formType=${formType!''}">${page}</a> 
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
			         <a href="/activity/selectEnterprise?activityId=${activityId?c!''}&page=${PAGE_DATA.number+1}&area=${area!''}&keywords=${keywords!''}&type=${type!''}&formType=${formType!''}" class="page_last">下一页</a> 
			     </#if>
			 </#if>
		  	<p>共${PAGE_DATA.totalPages!'1'}页  ${PAGE_DATA.totalElements!'1'}条</p>
		  	</#if>
		  </div>
		
		
		
		
        	<#include "/client/activity_selected_enterprise.ftl" />
        
        <div class="area_add_btn">
		<!--	<input style="cursor:pointer;"  type="button" value="批量取消预选" />-->
		</div>
		<input style="cursor:pointer;" class="area_save_btn" style="margin-left:45%;"type="button" onclick="location.href='/activity/enterprise/finish?id=${activityId?c!''}'" value="保存" />
    </div> 
    </div>
</div><!--content_end-->
</div><!--main-->
</body>
</html>