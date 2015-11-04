<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
    <title>科技小巨人</title>
	<link rel="shortcut icon" href="/client/images/icon.ico" />    
    <link href="/client/css/form.css" rel="stylesheet" type="text/css" />
    <link href="/client/css/base.css" rel="stylesheet" type="text/css" />
    <link href="/client/css/area.css" rel="stylesheet" type="text/css" />
    <script src="/client/js/jquery-1.9.1.min.js"></script>
	<script src="/client/js/main.js"></script>
<script>    
 //全选取消按钮函数-入选
function checkAll(chkobj) {
    if ($(chkobj).text() == "全选") {
        $(chkobj).children("span").text("取消");
        $(".checkall input:enabled").prop("checked", true);
    } else {
        $(chkobj).children("span").text("全选");
        $(".checkall input:enabled").prop("checked", false);
    }
}    
</script>
</head>
<body style="background: none;">
    <form action="/activity/win" method="post">
    <input type="hidden" name="activityId" value="<#if activityId??>${activityId?c!''} </#if>" />
    <input type="hidden" name="__ACTION" value="win">
	    <table class="score">
	        <caption>项目得分汇总表——<#if activity??>${activity.title!''}</#if></caption>
	        <tr class="tr01">
	        	<#if mark??&&mark=="activity"><td width="1%">胜出</td></#if>
	        	<td width="2%">排名</td>
	        	<td width="3%">编号</td>
	        	<td width="15%">名称</td>
	        	<td width="8%">总分</td>
	            <#if aex_list??>
	                <#list aex_list as item>
	                    <td width="5%">${item.name!''}</td>
	                </#list>
	            </#if>
	        </tr>
	        <#if grade_list??>
	            <#list grade_list as item>
			        <tr class="tr02">
			        	<#if mark??&&mark=="activity">
		        		<td>
		        		<span class="checkall" style="vertical-align:middle;">
			        		<input style="width:15px;height:15px;float:left; margin:0 0 0 10px ;" <#if item.statusId??&&item.statusId==3>disabled=""</#if> id="listChkId" type="checkbox" name="listChkId" value="${item_index}"/>
			        		<input type="hidden" name="listId" id="listId" value="${item.id}">
			        	</span>	
		        		</td>
		        		</#if>
			        	<td>${item_index+1}</td>
			        	<td>${item.number!''}</td>
				        <td>${item.enterpriseTitle!''}</td>
				        <td><#if item.totalPoint??>${item.totalPoint?c!''}</#if></td>
				        <#if ("expert_list_"+item_index)?eval??>
				        	<#assign expertList=("expert_list_"+item_index)?eval>
				        	<#list expertList as expert>
				        		<td><#if expert.totalPoint??>${expert.totalPoint?c!''}</#if></td>
				        	</#list>
				        </#if>		
			        </tr>
			    </#list>
			</#if>        
	   
	    </table>
	    <#if mark??&&mark=="activity"&&activity.statusId??&&activity.statusId==1>
        <a class="all" style="margin-left:10px;" href="javascript:;" onclick="checkAll(this);"><i></i><span>全选</span></a>
        <input style="cursor:pointer;" class="area_batch" style="margin-left:5%;" type="submit"  value="路演胜出" />
        </#if>
        <input style="margin-left: 640px; margin-top: 20px; width:100px;height: 30px; font-size: 14px;" class="area_batch" onclick="location.href='/activity/export/totalGrade?activityId=<#if activityId??>${activityId?c!''}</#if>'"  type="button" value="打印评分表" />
	</form>    
    
</body>
</html>