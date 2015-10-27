<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
	<title>Document</title>
	<link rel="shortcut icon" href="/images/icon.ico" />
	<link href="/client/css/form.css" rel="stylesheet" type="text/css" />
	<script src="/client/js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript">
        window.onload = function(){
        	var canvas = document.getElementById('canvas');
        	var cxt=canvas.getContext("2d");
        		cxt.moveTo(180,0);
        		cxt.lineTo(300,150);
        		cxt.lineTo(0,75);
        		cxt.stroke();
        	}
        	
        function changeExpression(code){
            
            var oneValue = document.getElementById(code+"_oneExpression").value; 
            var twoValue = document.getElementById(code+"_twoExpression").value;
            var threeValue = document.getElementById(code+"_threeExpression").value;
            var fourValue = document.getElementById(code+"_fourExpression").value;
            
            var totalExpressionValue = parseInt(oneValue)+parseInt(twoValue)+parseInt(threeValue)+parseInt(fourValue);
            document.getElementById(code+"_totalExpression").innerHTML=totalExpressionValue;
            
            var totalFeasibilityValue = document.getElementById(code+"_totalFeasibility").innerHTML;
            var totalMarketValue = document.getElementById(code+"_totalMarketValue").innerHTML;
            var totalTechnologyValue = document.getElementById(code+"_totalTechnology").innerHTML;
            var totalGroupValue = document.getElementById(code+"_totalGroup").innerHTML;
            
            console.debug(code+"_totalPoint");
             console.debug(document.getElementById(code+"_totalPoint"));
            document.getElementById(code+"_totalPoint").innerHTML = parseInt(totalExpressionValue)+parseInt(totalFeasibilityValue)+parseInt(totalMarketValue)+parseInt(totalTechnologyValue)+parseInt(totalGroupValue);
        }
        
        function changeFeasibility(code){
            
            var oneValue = document.getElementById(code+"_oneFeasibility").value; 
            var twoValue = document.getElementById(code+"_twoFeasibility").value;
            var threeValue = document.getElementById(code+"_threeFeasibility").value;
            var fourValue = document.getElementById(code+"_fourFeasibility").value;
            
            var totalFeasibilityValue = parseInt(oneValue)+parseInt(twoValue)+parseInt(threeValue)+parseInt(fourValue);
            document.getElementById(code+"_totalFeasibility").innerHTML=totalFeasibilityValue;
            
            var totalExpressionValue = document.getElementById(code+"_totalExpression").innerHTML;
            var totalMarketValue = document.getElementById(code+"_totalMarketValue").innerHTML;
            var totalTechnologyValue = document.getElementById(code+"_totalTechnology").innerHTML;
            var totalGroupValue = document.getElementById(code+"_totalGroup").innerHTML;
            
            document.getElementById(code+"_totalPoint").innerHTML = parseInt(totalExpressionValue)+parseInt(totalFeasibilityValue)+parseInt(totalMarketValue)+parseInt(totalTechnologyValue)+parseInt(totalGroupValue);
        }
        
        function changeMarketValue(code){
            
            var oneValue = document.getElementById(code+"_oneMarketValue").value; 
            var twoValue = document.getElementById(code+"_twoMarketValue").value;
            var threeValue = document.getElementById(code+"_threeMarketValue").value;
            var fourValue = document.getElementById(code+"_fourMarketValue").value;
            
            var totalMarketValue = parseInt(oneValue)+parseInt(twoValue)+parseInt(threeValue)+parseInt(fourValue);
            document.getElementById(code+"_totalMarketValue").innerHTML=totalMarketValue;
            
            var totalExpressionValue = document.getElementById(code+"_totalExpression").innerHTML;
            var totalFeasibilityValue = document.getElementById(code+"_totalFeasibility").innerHTML;
            var totalTechnologyValue = document.getElementById(code+"_totalTechnology").innerHTML;
            var totalGroupValue = document.getElementById(code+"_totalGroup").innerHTML;
            
            document.getElementById(code+"_totalPoint").innerHTML = parseInt(totalExpressionValue)+parseInt(totalFeasibilityValue)+parseInt(totalMarketValue)+parseInt(totalTechnologyValue)+parseInt(totalGroupValue);
        }
        
        function changeTechnology(code){
            
            var oneValue = document.getElementById(code+"_oneTechnology").value; 
            var twoValue = document.getElementById(code+"_twoTechnology").value;
            var threeValue = document.getElementById(code+"_threeTechnology").value;
            var fourValue = document.getElementById(code+"_fourTechnology").value;
            var fiveValue = document.getElementById(code+"_fiveTechnology").value;
            
            var totalTechnologyValue = parseInt(oneValue)+parseInt(twoValue)+parseInt(threeValue)+parseInt(fourValue)+parseInt(fiveValue);
            document.getElementById(code+"_totalTechnology").innerHTML=totalTechnologyValue;
            
            var totalExpressionValue = document.getElementById(code+"_totalExpression").innerHTML;
            var totalFeasibilityValue = document.getElementById(code+"_totalFeasibility").innerHTML;
            var totalMarketValue = document.getElementById(code+"_totalMarketValue").innerHTML;
            var totalGroupValue = document.getElementById(code+"_totalGroup").innerHTML;
            
            document.getElementById(code+"_totalPoint").innerHTML = parseInt(totalExpressionValue)+parseInt(totalFeasibilityValue)+parseInt(totalMarketValue)+parseInt(totalTechnologyValue)+parseInt(totalGroupValue);
        }
        
        function changeGroup(code){
            
            var oneValue = document.getElementById(code+"_oneGroup").value; 
            var twoValue = document.getElementById(code+"_twoGroup").value;
            var threeValue = document.getElementById(code+"_threeGroup").value;
            
            var totalGroupValue = parseInt(oneValue)+parseInt(twoValue)+parseInt(threeValue);
            document.getElementById(code+"_totalGroup").innerHTML=totalGroupValue;
            
            var totalExpressionValue = document.getElementById(code+"_totalExpression").innerHTML;
            var totalFeasibilityValue = document.getElementById(code+"_totalFeasibility").innerHTML;
            var totalMarketValue = document.getElementById(code+"_totalMarketValue").innerHTML;
            var totalTechnologyValue = document.getElementById(code+"_totalTechnology").innerHTML;
            
            document.getElementById(code+"_totalPoint").innerHTML = parseInt(totalExpressionValue)+parseInt(totalFeasibilityValue)+parseInt(totalMarketValue)+parseInt(totalTechnologyValue)+parseInt(totalGroupValue);
        }
        
        function submitPoint(code,number){
            var totalExpressionValue = document.getElementById(code+"_totalExpression").innerHTML;
            var totalFeasibilityValue = document.getElementById(code+"_totalFeasibility").innerHTML;
            var totalMarketValue = document.getElementById(code+"_totalMarketValue").innerHTML;
            var totalTechnologyValue = document.getElementById(code+"_totalTechnology").innerHTML;
            var totalGroupValue = document.getElementById(code+"_totalGroup").innerHTML;
            
            var oneExpression = document.getElementById(code+"_oneExpression").value;
            var twoExpression = document.getElementById(code+"_twoExpression").value;
            var threeExpression = document.getElementById(code+"_threeExpression").value;
            var fourExpression = document.getElementById(code+"_fourExpression").value;
            
            var oneFeasibility = document.getElementById(code+"_oneFeasibility").value;
            var twoFeasibility = document.getElementById(code+"_twoFeasibility").value;
            var threeFeasibility = document.getElementById(code+"_threeFeasibility").value;
            var fourFeasibility = document.getElementById(code+"_fourFeasibility").value;
            
            var oneMarket = document.getElementById(code+"_oneMarketValue").value;
            var twoMarket = document.getElementById(code+"_twoMarketValue").value;
            var threeMarket = document.getElementById(code+"_threeMarketValue").value;
            var fourMarket = document.getElementById(code+"_fourMarketValue").value;
            
            var oneTechnology = document.getElementById(code+"_oneTechnology").value;
            var twoTechnology = document.getElementById(code+"_twoTechnology").value;
            var threeTechnology = document.getElementById(code+"_threeTechnology").value;
            var fourTechnology = document.getElementById(code+"_fourTechnology").value;
            var fiveTechnology = document.getElementById(code+"_fiveTechnology").value;
            
            var oneGroup = document.getElementById(code+"_oneGroup").value;
            var twoGroup = document.getElementById(code+"_twoGroup").value;
            var threeGroup = document.getElementById(code+"_threeGroup").value;
            
            $.post("/expert/grade/sure",{
                "totalExpression":totalExpressionValue,
                "totalFeasibility":totalFeasibilityValue,
                "totalMarketValue":totalMarketValue,
                "totalTechnology":totalTechnologyValue,
                "totalGroup":totalGroupValue,
                "oneExpression":oneExpression,
                "twoExpression":twoExpression,
                "threeExpression":threeExpression,
                "fourExpression":fourExpression,
                "oneFeasibility":oneFeasibility,
                "twoFeasibility":twoFeasibility,
                "threeFeasibility":threeFeasibility,
                "fourFeasibility":fourFeasibility,
                "oneMarketValue":oneMarket,
                "twoMarketValue":twoMarket,
                "threeMarketValue":threeMarket,
                "fourMarketValue":fourMarket,
                "oneTechnology":oneTechnology,
                "twoTechnology":twoTechnology,
                "threeTechnology":threeTechnology,
                "fourTechnology":fourTechnology,
                "fiveTechnology":fiveTechnology,
                "oneGroup":oneGroup,
                "twoGroup":twoGroup,
                "threeGroup":threeGroup,
                "number":number,
                "activityId":"${activityId}"
            },function(res){
                if(0 == res.status){
                    alert("评分成功！");
                }
            });
        }
	</script>
	<style type="text/css">
		.tr01 th{
			position: relative;
		}
		.tr01 .tabletitle-one{
			position: absolute;
			top: 0;
		}
		.tr01 .tabletitle-two{
			position: absolute;
			right: 0;
			top: 0;
		}
		.tr01 .tabletitle-three{
			position: absolute;
			bottom: 0;
		}
	</style>
</head>
<body>
	
	<table class="score">
		<caption>项目得分表</caption>
		<tr class="tr01">
			<th style="padding:0;margin:0;"><canvas id="canvas" style="width:100%;height:100%;;margin:0;padding:0;"></canvas>
				<p class="tabletitle-one">得分</p>
				<p class="tabletitle-two">项目编号</p>
				<p class="tabletitle-three">评分项</p>
			</th>
			<#if grade_list??>
			     <#list grade_list as item>
			         <td>${item.number!''}</td>
			     </#list>
			</#if>
		</tr>
		<tr class="tr02">
			<th>现场表现力(小计)</th>
			<#if grade_list??>
			     <#list grade_list as item>
			         <#if item.totalExpression??>
			             <td id="${item_index}_totalExpression">${item.totalExpression?string("0")}</td>
			         <#else>
			             <td id="${item_index}_totalExpression">0</td>
			         </#if>
			     </#list>
			</#if>
		</tr>
		<tr class="tr03">
			<th>DEMO发表方式的创新程度</th>
			<#if grade_list??>
			    <#list grade_list as item>
        			<td>
        				<select onChange="changeExpression('${item_index}');" id="${item_index}_oneExpression" <#if item.isGrade>disabled=""</#if>>
        				    <#list 0..5 as n>
        					   <option value="${n}" <#if item.oneExpression??&&n==item.oneExpression>selected=""</#if>>${n}</option>
        					</#list>
        				</select>
        			</td>
    			</#list>
			</#if>
		</tr>
		<tr class="tr03">
		    <th>团队解说效果</th>
			<#if grade_list??>
			    <#list grade_list as item>
        			<td>
        				<select onChange="changeExpression('${item_index}');" id="${item_index}_twoExpression" <#if item.isGrade==true>disabled=""</#if>>
        				    <#list 0..5 as n>
            					<option value="${n}" <#if item.twoExpression?? &&n==item.twoExpression>selected=""</#if>>${n}</option>
        					</#list>
        				</select>
        			</td>
    			</#list>
			</#if>
		</tr>
		<tr class="tr03">
			<th>现场营造效果</th>
			<#if grade_list??>
			    <#list grade_list as item>
        			<td>
        				<select onChange="changeExpression('${item_index}');" id="${item_index}_threeExpression" <#if item.isGrade==true>disabled=""</#if>>
        				    <#list 0..5 as n>
        					   <option value="${n}" <#if item.threeExpression?? &&n==item.threeExpression>selected=""</#if>>${n}</option>
        					</#list>
        				</select>
        			</td>
    			</#list>
			</#if>
		</tr> 
		<tr class="tr03">
			<th>项目吸引力</th>
			<#if grade_list??>
			     <#list grade_list as item>
    		        <td>
        				<select onChange="changeExpression('${item_index}');" id="${item_index}_fourExpression" <#if item.isGrade==true>disabled=""</#if>>
        				    <#list 0..5 as n>
        					   <option value="${n}" <#if item.fourExpression??&&n==item.fourExpression>selected=""</#if>>${n}</option>
        					</#list>
        				</select>
        			</td>
    			 </#list>
			 </#if>
		</tr>
		
			
		<tr class="tr02">
			<th>项目实现性(小计)</th>
			<#if grade_list??>
			     <#list grade_list as item>
			         <#if item.totalFeasibility??>
			             <td id="${item_index}_totalFeasibility">${item.totalFeasibility?string("0")}</td>
                     <#else>
                         <td id="${item_index}_totalFeasibility">0</td>
                     </#if>			             
			     </#list>
			</#if>
		</tr>
		<tr class="tr03">
			<th>行业环境</th>
			<#if grade_list??>
			    <#list grade_list as item>
        			<td>
        				<select onChange="changeFeasibility('${item_index}');" id="${item_index}_oneFeasibility" <#if item.isGrade==true>disabled=""</#if>>
        					<#list 0..5 as n>
        					   <option value="${n}" <#if item.oneFeasibility??&&n==item.oneFeasibility>selected=""</#if>>${n}</option>
        					</#list>
        				</select>
        			</td>
    			</#list>
			</#if>
		</tr>
		<tr class="tr03">
			<th>现有基础条件能否支撑</th>
			<#if grade_list??>	
			    <#list grade_list as item>
        			<td>
        				<select onChange="changeFeasibility('${item_index}');" id="${item_index}_twoFeasibility" <#if item.isGrade==true>disabled=""</#if>>
        				    <#list 0..5 as n>
        					   <option value="${n}" <#if item.twoFeasibility??&&n==item.twoFeasibility>selected=""</#if>>${n}</option>
        					</#list>
        				</select>
        			</td>
    			</#list>
			</#if>
		</tr>
		<tr class="tr03">
			<th>技术可实现性</th>
			<#if grade_list??>
			    <#list grade_list as item>
        			<td>
        				<select onChange="changeFeasibility('${item_index}');" id="${item_index}_threeFeasibility" <#if item.isGrade==true>disabled=""</#if>>
        				    <#list 0..5 as n>
        					   <option value="${n}" <#if item.threeFeasibility??&&n==item.threeFeasibility>selected=""</#if>>${n}</option>
        					</#list>
        				</select>
        			</td>
    			</#list>
			</#if>
		</tr>
		<tr class="tr03">
			<th>市场接受度</th>
			<#if grade_list??>
			    <#list grade_list as item>
        			<td>
        				<select onChange="changeFeasibility('${item_index}');" id="${item_index}_fourFeasibility" <#if item.isGrade==true>disabled=""</#if>>
        				    <#list 0..5 as n>
        					   <option value="${n}" <#if item.fourFeasibility??&&n==item.fourFeasibility>selected=""</#if>>${n}</option>
        					</#list>
        				</select>
        			</td>
    			</#list>
			</#if>
		</tr>
		
		<tr class="tr02">
			<th>市场价值(小计)</th>
			<#if grade_list??>
			     <#list grade_list as item>
			         <#if item.totalMarketValue??>
			             <td id="${item_index}_totalMarketValue">${item.totalMarketValue?string("0")}</td>
			         <#else>
			             <td id="${item_index}_totalMarketValue">0</td>
			         </#if>
			     </#list>
			</#if>
		</tr>
		<tr class="tr03">
			<th>潜在市场规模大小与技术开发成本的评估</th>
			<#if grade_list??>
			    <#list grade_list as item>
        			<td>
        				<select onChange="changeMarketValue('${item_index}');" id="${item_index}_oneMarketValue" <#if  item.isGrade??&&item.isGrade==true>disabled=""</#if>>
        				    <#list 0..5 as n>
        					   <option value="${n}" <#if item.oneMarketValue??&&n==item.oneMarketValue>selected=""</#if>>${n}</option>
        					</#list>
        				</select>
        			</td>
    			</#list>
			</#if>
		</tr>
		<tr class="tr03">
			<th>现行成本与市场现状的评估</th>	
			<#if grade_list??>
			    <#list grade_list as item>
        			<td>
        				<select onChange="changeMarketValue('${item_index}');" id="${item_index}_twoMarketValue" <#if item.isGrade==true>disabled=""</#if>>
        				    <#list 0..5 as n>
        					   <option value="${n}" <#if item.twoMarketValue??&&n==item.twoMarketValue>selected=""</#if>>${n}</option>
        					</#list>
        				</select>
        			</td>
    			</#list>
			</#if>
		</tr>
		<tr class="tr03">
			<th>创意是否具有市场开发价值</th>
			<#if grade_list??>
			    <#list grade_list as item>
        			<td>
        				<select onChange="changeMarketValue('${item_index}');" id="${item_index}_threeMarketValue" <#if item.isGrade==true>disabled=""</#if>>
        				    <#list 0..5 as n>
        					<option value="${n}" <#if item.threeMarketValue??&&n==item.threeMarketValue>selected=""</#if>>${n}</option>
        					</#list>
        				</select>
        			</td>
			    </#list>
			</#if>
		</tr>
		<tr class="tr03">
			<th>转成创业的机会</th>
			<#if grade_list??>
			    <#list grade_list as item>
        			<td>
        				<select onChange="changeMarketValue('${item_index}');" id="${item_index}_fourMarketValue" <#if item.isGrade==true>disabled=""</#if>>
        				    <#list 0..5 as n>
			         		  <option value="${n}" <#if item.fourMarketValue??&&n==item.fourMarketValue>selected=""</#if>>${n}</option>
			         		</#list>
        				</select>
        			</td>
			    </#list>
		    </#if>
		</tr>
		
		
		<tr class="tr02">
			<th>技术开发(小计)</th>
			<#if grade_list??>
			     <#list grade_list as item>
			         <#if item.totalTechnology??>
			             <td id="${item_index}_totalTechnology">${item.totalTechnology?string("0")}</td>
			         <#else>
			             <td id="${item_index}_totalTechnology">0</td>
			         </#if>
			     </#list>
			</#if>
		</tr>
		<tr class="tr03">
			<th>技术创新性与现有同类技术的差异性</th>
			<#if grade_list??>
			    <#list grade_list as item>
        			<td>
        				<select onChange="changeTechnology('${item_index}');" id="${item_index}_oneTechnology" <#if item.isGrade==true>disabled=""</#if>>
        				    <#list 0..5 as n>
        					   <option value="${n}" <#if item.oneTechnology??&&n==item.oneTechnology>selected=""</#if>>${n}</option>
        					</#list>
        				</select>
        			</td>
    			</#list>
			</#if>
		</tr>
		<tr class="tr03">
			<th>自身与竞争对手的SWOT分析评估</th>	
			<#if grade_list??>
			    <#list grade_list as item>
        			<td>
        				<select onChange="changeTechnology('${item_index}');" id="${item_index}_twoTechnology" <#if item.isGrade==true>disabled=""</#if>>
        				    <#list 0..5 as n>
        					   <option value="${n}" <#if item.twoTechnology??&&n==item.twoTechnology>selected=""</#if>>${n}</option>
        					</#list>
        				</select>
        			</td>
    			</#list>
			</#if>
		</tr>
		<tr class="tr03">
			<th>深度分析竞争对手的发展程度</th>
			<#if grade_list??>
			    <#list grade_list as item>
        			<td>
        				<select onChange="changeTechnology('${item_index}');" id="${item_index}_threeTechnology" <#if item.isGrade==true>disabled=""</#if>>
        				    <#list 0..5 as n>
        					   <option value="${n}" <#if item.threeTechnology??&&n==item.threeTechnology>selected=""</#if>>${n}</option>
        					</#list>
        				</select>
        			</td>
			    </#list>
			</#if>
		</tr>
		<tr class="tr03">
			<th>自身竞争优势</th>
			<#if grade_list??>
    			<#list grade_list as item>
        			<td>
        				<select onChange="changeTechnology('${item_index}');" id="${item_index}_fourTechnology" <#if item.isGrade==true>disabled=""</#if>>
        				    <#list 0..5 as n>
        					   <option value="${n}" <#if item.fourTechnology??&&n==item.fourTechnology>selected=""</#if>>${n}</option>
        					</#list>
        				</select>
        			</td>
    			</#list>
			</#if>
		</tr>
		<tr class="tr03">
			<th>其差异化程度与具有市场需求度的相互影响</th>
			<#if grade_list??>
    			<#list grade_list as item>
        			<td>
        				<select onChange="changeTechnology('${item_index}');" id="${item_index}_fiveTechnology" <#if item.isGrade==true>disabled=""</#if>>
        				    <#list 0..5 as n>
        					   <option value="${n}" <#if item.fiveTechnology??&&n==item.fiveTechnology>selected=""</#if>>${n}</option>
        					</#list>
        				</select>
        			</td>
			    </#list>
		    </#if>
		</tr>
		<tr class="tr02">
			<th>团队能力(小计)</th>
			<#if grade_list??>
			     <#list grade_list as item>
			         <#if item.totalGroup??>
			             <td id="${item_index}_totalGroup">${item.totalGroup?string("0")}</td>
			         <#else>
			             <td id="${item_index}_totalGroup">0</td>
			         </#if>
			     </#list>
			</#if>
		</tr>
		<tr class="tr03">
			<th>团队人员分工是否合理及其专业能力</th>
			<#if grade_list??>
    			<#list grade_list as item>
    			<td>
    				<select onChange="changeGroup('${item_index}');" id="${item_index}_oneGroup" <#if item.isGrade==true>disabled=""</#if>>
    				    <#list 0..5 as n>
    					   <option value="${n}" <#if item.oneGroup??&&n==item.oneGroup>selected=""</#if>>${n}</option>
    					</#list>
    				</select>
    			</td>
    			</#list>
		    </#if>
		</tr>
		<tr class="tr03">
            <th>创业家精神及企业愿景</th>
            <#if grade_list??>
                <#list grade_list as item>
                <td>
                    <select onChange="changeGroup('${item_index}');" id="${item_index}_twoGroup" <#if item.isGrade==true>disabled=""</#if>>
                        <#list 0..5 as n>
                           <option value="${n}" <#if item.twoGroup??&& n==item.twoGroup>selected=""</#if>>${n}</option>
                        </#list>
                    </select>
                </td>
                </#list>
            </#if>
        </tr>
		<tr class="tr03">
			<th>团队人员的沟通、信任和凝聚力</th>
			<#if grade_list??>
    			<#list grade_list as item>
        			<td>
        				<select onChange="changeGroup('${item_index}');" id="${item_index}_threeGroup" <#if item.isGrade==true>disabled=""</#if>>
        				    <#list 0..5 as n>
        					   <option value="${n}" <#if item.threeGroup??&&n==item.threeGroup>selected=""</#if>>${n}</option>
        					</#list>
        				</select>
        			</td>
    			</#list>
			</#if>
		</tr>
		
		<tr class="tr02">
			<th>合计</th>
			<#if grade_list??>
    			<#list grade_list as item>
    			     <#if item.totalPoint??>
    			         <td id="${item_index}_totalPoint">${item.totalPoint?string("0")}</td>
    			     <#else>
    			         <td id="${item_index}_totalPoint">0</td>
    			     </#if>
    			</#list>
			</#if>
		</tr>
		<tr class="tr02">
			<th></th>
			<#if grade_list??>
    			<#list grade_list as item>
    			     <td><input type="button" onClick="submitPoint('${item_index}','${item.number!''}');" value="确定" /></td>
    			</#list>
		    </#if>
		</tr>	





	</table>
</body>
</html>