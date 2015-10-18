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
		<caption>项目得分表</caption>
		<tr class="tr01">
			<th>标题</th>
			    <#list grade_list as item>
			       <td>${item.expertId!''}</td>
			    </#list>
		</tr>
		<tr class="tr02">
			<th>现场表现力(小计)</th>
			<#list grade_list as item>
			    <td>${item.totalExpression!''}</td>
			</#list>         
		</tr>
		<tr class="tr03">
			<th>DEMO发表方式的创新程度</th>
            <#list grade_list as item>
                <td>${item.oneExpression!''}</td>
            </#list> 			
		</tr>
		<tr class="tr03">
			<th>团队解说效果</th>	
            <#list grade_list as item>
                <td>${item.twoExpression!''}</td>
            </#list>    
		</tr>
		<tr class="tr03">
			<th>现场营造效果</th>
            <#list grade_list as item>
                <td>${item.threeExpression!''}</td>
            </#list> 
		</tr>
		<tr class="tr03">
			<th>项目吸引力</th>
            <#list grade_list as item>
                <td>${item.fourExpression!''}</td>
            </#list> 
		</tr>
			
		<tr class="tr02">
			<th>项目实现性(小计)</th>
            <#list grade_list as item>
                <td>${item.totalFeasibility!''}</td>
            </#list> 
		</tr>
		<tr class="tr03">
			<th>行业环境</th>
            <#list grade_list as item>
                <td>${item.oneFeasibility!''}</td>
            </#list> 
		</tr>
		<tr class="tr03">
			<th>现有基础条件能否支撑</th>	
            <#list grade_list as item>
                <td>${item.twoFeasibility!''}</td>
            </#list> 
		</tr>
		<tr class="tr03">
			<th>技术可实现性</th>
			
	            <#list grade_list as item>
                <td>${item.threeFeasibility!''}</td>
            </#list> 
		</tr>
		<tr class="tr03">
			<th>市场接受度</th>
			
	            <#list grade_list as item>
                <td>${item.fourFeasibility!''}</td>
            </#list> 
		</tr>
		
		
		<tr class="tr02">
			<th>市场价值(小计)</th>
	            <#list grade_list as item>
                <td>${item.totalMarketValue!''}</td>
            </#list> 
			
		</tr>
		<tr class="tr03">
			<th>潜在市场规模大小与技术开发成本的评估</th>
			
            <#list grade_list as item>
                <td>${item.oneMarketValue!''}</td>
            </#list> 
		</tr>
		<tr class="tr03">
			<th>现行成本与市场现状的评估</th>	
	            <#list grade_list as item>
                <td>${item.twoMarketValue!''}</td>
            </#list> 
		</tr>
		<tr class="tr03">
			<th>创意是否具有市场开发价值</th>
			
            <#list grade_list as item>
                <td>${item.threeMarketValue!''}</td>
            </#list> 
		</tr>
		<tr class="tr03">
			<th>转成创业的机会</th>
			
            <#list grade_list as item>
                <td>${item.fourMarketValue!''}</td>
            </#list> 
		</tr>
		
		
		<tr class="tr02">
			<th>技术开发(小计)</th>
            <#list grade_list as item>
                <td>${item.totalTechnology!''}</td>
            </#list> 
			
		</tr>
		<tr class="tr03">
			<th>技术创新性与现有同类技术的差异性</th>
			
	        <#list grade_list as item>
                <td>${item.oneTechnology!''}</td>
            </#list> 
		</tr>
		<tr class="tr03">
			<th>自身与竞争对手的SWOT分析评估</th>	
            <#list grade_list as item>
                <td>${item.twoTechnology!''}</td>
            </#list> 
		</tr>
		<tr class="tr03">
			<th>深度分析竞争对手的发展程度</th>
			
            <#list grade_list as item>
                <td>${item.threeTechnology!''}</td>
            </#list> 
		</tr>
		<tr class="tr03">
			<th>自身竞争优势</th>
            <#list grade_list as item>
                <td>${item.fourTechnology!''}</td>
            </#list> 
		</tr>
		<tr class="tr03">
			<th>其差异化程度与具有市场需求度的相互影响</th>
			
	            <#list grade_list as item>
                <td>${item.fiveTechnology!''}</td>
            </#list> 
		</tr>
		
		
		<tr class="tr02">
			<th>团队能力(小计)</th>
            <#list grade_list as item>
                <td>${item.totalGroup!''}</td>
            </#list> 
			
		</tr>
		<tr class="tr03">
			<th>团队人员分工是否合理及其专业能力</th>
			
            <#list grade_list as item>
                <td>${item.oneGroup!''}</td>
            </#list> 
		</tr>
		<tr class="tr03">
			<th>创业家精神及企业愿景</th>	
            <#list grade_list as item>
                <td>${item.twoGroup!''}</td>
            </#list> 
		</tr>
		<tr class="tr03">
			<th>团队人员的沟通、信任和凝聚力</th>
            <#list grade_list as item>
                <td>${item.threeGroup!''}</td>
            </#list> 
		</tr>
		<tr class="tr03">
			<th>小计</th>
			<#asign count = 0>
            <#list grade_list as item>
                <td>${item.totalPoint!''}</td>
                <#asign count = count+item.titalPoint>
            </#list> 
		</tr>
		<tr class="tr03">
			<th>合计</th>
			
			<td colspan="20">${count!'0'}</td>
			
		</tr>
	</table>
	</#if>
	<input style="margin-left: 640px; margin-top: 20px; width:100px;height: 30px; font-size: 14px;" type="button" value="打印评分表" />
</body>
</html>