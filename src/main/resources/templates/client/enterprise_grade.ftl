<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
	<title>得分</title>
	<link rel="shortcut icon" href="/client/images/icon.ico" />
	<link href="/client/css/form.css" rel="stylesheet" type="text/css" />
	<link href="/client/css/base.css" rel="stylesheet" type="text/css" />
</head>
<body style="background: none;">
  <#if grade_list??>
	<table class="score">
		<caption>项目得分表——${enterprise.title!''}</caption>
		
		<tr class="tr01">
			<th>标题</th>
			    <#list experts as item>
			       <td>${item.name!''}</td>
			    </#list>
		</tr>

		<tr class="tr02" style="background:#DFEBF7;">
			<th>核心竞争力小计)</th>
            <#list grade_list as item>
                <td>${item.totalTechnology!''}</td>
            </#list> 
			
		</tr>
		<tr class="tr03">
			<th>技术、产品、服务、商业模式领先性、创新性</th>
			
	        <#list grade_list as item>
                <td>${item.oneTechnology!''}</td>
            </#list> 
		</tr>
		<tr class="tr03">
			<th>专利、商标、著作登记、双软、双高证书</th>	
            <#list grade_list as item>
                <td>${item.twoTechnology!''}</td>
            </#list> 
		</tr>
		<tr class="tr03">
			<th>与竞争对手相比的优势程度</th>
			
            <#list grade_list as item>
                <td>${item.threeTechnology!''}</td>
            </#list> 
		</tr>

		<tr class="tr02" style="background:#DFEBF7;">
			<th>市场潜力(小计)</th>
            <#list grade_list as item>
                <td>${item.totalFeasibility!''}</td>
            </#list> 
		</tr>
		<tr class="tr03">
			<th>潜在市场规模大小及已有的市场份额</th>
            <#list grade_list as item>
                <td>${item.oneFeasibility!''}</td>
            </#list> 
		</tr>
		<tr class="tr03">
			<th>市场开发价值与开发成本</th>	
            <#list grade_list as item>
                <td>${item.twoFeasibility!''}</td>
            </#list> 
		</tr>
		
		<tr class="tr02" style="background:#DFEBF7;">
			<th>团队能力(小计)</th>
            <#list grade_list as item>
                <td>${item.totalGroup!''}</td>
            </#list> 
			
		</tr>
		<tr class="tr03">
			<th>核心领头人的专业能力及资源</th>
			
            <#list grade_list as item>
                <td>${item.oneGroup!''}</td>
            </#list> 
		</tr>
		<tr class="tr03">
			<th>团队成员的专业能力及分工是否合理</th>	
            <#list grade_list as item>
                <td>${item.twoGroup!''}</td>
            </#list> 
		</tr>
		
		<tr class="tr02" style="background:#DFEBF7;">
			<th>投资价值(小计)</th>
	            <#list grade_list as item>
                <td>${item.totalMarketValue!''}</td>
            </#list> 
			
		</tr>
		<tr class="tr03">
			<th>行业环境及现有基础条件能否支撑</th>
			
            <#list grade_list as item>
                <td>${item.oneMarketValue!''}</td>
            </#list> 
		</tr>
		<tr class="tr03">
			<th>财务状况及融资条件</th>	
	            <#list grade_list as item>
                <td>${item.twoMarketValue!''}</td>
            </#list> 
		</tr>
		
		<tr class="tr02" style="background:#DFEBF7;">
			<th>现场表现力(小计)</th>
			<#list grade_list as item>
			    <td>${item.totalExpression!''}</td>
			</#list>         
		</tr>
		<tr class="tr03">
			<th>路演方式的创新程度及现场感染力</th>
            <#list grade_list as item>
                <td>${item.oneExpression!''}</td>
            </#list> 			
		</tr>

		<tr class="tr03" style="background:#DFEBF7;">
			<th>小计</th>
			<#assign count = 0>
            <#list grade_list as item>
                <td>${item.totalPoint!''}</td>
                <#if item.totalPoint??>
                    <#assign count = count+item.totalPoint>
                </#if>
            </#list> 
		</tr>
		<tr class="tr03" style="background:#FFF3CD;" >
			<th >合计</th>
			
			<td colspan="20">${count!'0'}</td>
			
		</tr>
	</table>
	</#if>
</body>
</html>