<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
    <title>科技小巨人</title>
	<link rel="shortcut icon" href="/client/images/icon.ico" />    
    <link href="/client/css/form.css" rel="stylesheet" type="text/css" />
    <link href="/client/css/base.css" rel="stylesheet" type="text/css" />
</head>
<body style="background: none;">
    <table class="score">
        <caption>项目得分汇总表</caption>
        <tr class="tr01">
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
    <input style="margin-left: 640px; margin-top: 20px; width:100px;height: 30px; font-size: 14px;" type="button" value="打印评分表" />
</body>
</html>