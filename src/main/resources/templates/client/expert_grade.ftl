<#--<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">-->
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
	<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
	<title>评分</title>
	<link rel="shortcut icon" href="/client/images/icon.ico" />
	<link href="/client/css/formGrade.css" rel="stylesheet" type="text/css" />
	<link type="text/css" rel="stylesheet" href="/client/css/showBo.css" />
	<script type="text/javascript" src="/client/js/showBo_grade.js"></script>
	
	<script src="/client/js/jquery-1.9.1.min.js"></script>

    <script type="text/javascript"   src="/client/js/jquery-1.11.2.min.js"></script>
	<script type="text/javascript"   src="/client/js/jquery.barrating.js"></script>
	<script type="text/javascript"   src="/client/js/examples.js"></script>	
    
	<style>
	#dvMsgBox .top{height:50px;}
	#dvMsgBox .top .right .title{font-size:20px;}
	#dvMsgBox .body .right .ct {line-height:32px;font-size:18px;}
	input.btn{height: 42px;font-size: 18px;  width: 68px;}
	input.btnfocus{height:42px; font-size:18px;	  width: 68px;}
	#dvMsgBox .height{height:16px;}
	</style>

	<script type="text/javascript">

	
        window.onload = function(){
        
		Showbo.Msg.alert(
		<#if grade_list??>
	     	<#list grade_list as item>
	     		<#if item.gradeAble?? && item.gradeAble>
	     			"${item.enterpriseTitle!''}"
	     		</#if>	
	     	</#list>
	    </#if> 	
		);

        
      /**
        	var canvas = document.getElementById('canvas');
        	var cxt=canvas.getContext("2d");
        		cxt.moveTo(180,0);
        		cxt.lineTo(300,150);
        		cxt.lineTo(0,75);
        		cxt.stroke();
        **/		
        		 $('select').barrating();
        	}
     
        function changeExpression(code){
            
            var oneValue = document.getElementById(code+"_oneExpression").value; 
            
            var totalExpressionValue = parseInt(oneValue);
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
            
            var totalFeasibilityValue = parseInt(oneValue)+parseInt(twoValue);
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
            
            var totalMarketValue = parseInt(oneValue)+parseInt(twoValue);
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
            
            var totalTechnologyValue = parseInt(oneValue)+parseInt(twoValue)+parseInt(threeValue);
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
            
            var totalGroupValue = parseInt(oneValue)+parseInt(twoValue);
            document.getElementById(code+"_totalGroup").innerHTML=totalGroupValue;
            
            var totalExpressionValue = document.getElementById(code+"_totalExpression").innerHTML;
            var totalFeasibilityValue = document.getElementById(code+"_totalFeasibility").innerHTML;
            var totalMarketValue = document.getElementById(code+"_totalMarketValue").innerHTML;
            var totalTechnologyValue = document.getElementById(code+"_totalTechnology").innerHTML;
            
            document.getElementById(code+"_totalPoint").innerHTML = parseInt(totalExpressionValue)+parseInt(totalFeasibilityValue)+parseInt(totalMarketValue)+parseInt(totalTechnologyValue)+parseInt(totalGroupValue);
        }
        
        function submitPoint(code,number,title){
			 var totalPoint = document.getElementById(code+"_totalPoint").innerHTML;
	
			Showbo.Msg.confirm("<span style='font-size:24px;text-align:left;'>"+title+"<br><b style='color:red;'>"+totalPoint+"</b>分"+"</span>"+"<br>请确认评分结果，确认后不可更改",function(p){
				if (p == "yes")
				{

		            var totalExpressionValue = document.getElementById(code+"_totalExpression").innerHTML;
		            var totalFeasibilityValue = document.getElementById(code+"_totalFeasibility").innerHTML;
		            var totalMarketValue = document.getElementById(code+"_totalMarketValue").innerHTML;
		            var totalTechnologyValue = document.getElementById(code+"_totalTechnology").innerHTML;
		            var totalGroupValue = document.getElementById(code+"_totalGroup").innerHTML;
		            
		            var oneExpression = document.getElementById(code+"_oneExpression").value;
		            
		            var oneFeasibility = document.getElementById(code+"_oneFeasibility").value;
		            var twoFeasibility = document.getElementById(code+"_twoFeasibility").value;
		            
		            var oneMarket = document.getElementById(code+"_oneMarketValue").value;
		            var twoMarket = document.getElementById(code+"_twoMarketValue").value;
		            
		            var oneTechnology = document.getElementById(code+"_oneTechnology").value;
		            var twoTechnology = document.getElementById(code+"_twoTechnology").value;
		            var threeTechnology = document.getElementById(code+"_threeTechnology").value;
		            
		            var oneGroup = document.getElementById(code+"_oneGroup").value;
		            var twoGroup = document.getElementById(code+"_twoGroup").value;
		            
		            $.post("/expert/grade/sure",{
		                "totalExpression":totalExpressionValue,
		                "totalFeasibility":totalFeasibilityValue,
		                "totalMarketValue":totalMarketValue,
		                "totalTechnology":totalTechnologyValue,
		                "totalGroup":totalGroupValue,
		                "oneExpression":oneExpression,
		                "oneFeasibility":oneFeasibility,
		                "twoFeasibility":twoFeasibility,
		                "oneMarketValue":oneMarket,
		                "twoMarketValue":twoMarket,
		                "oneTechnology":oneTechnology,
		                "twoTechnology":twoTechnology,
		                "threeTechnology":threeTechnology,
		                "oneGroup":oneGroup,
		                "twoGroup":twoGroup,
		                "number":number,
		                "activityId":"${activityId?c}"
		            },function(res){
		                if(0 == res.status){
		                
		                	if(typeof res.msg != "undefined")
		                	{
			                      Showbo.Msg.alert(res.msg);
			                      setTimeout("location.href='/expert/enterprise/list';",2000);
		                	}
		                	else{
		                		location.href='/expert/grade?activityId='+${activityId?c};
		                	}
		                  
		                }
		            });
					}
				});
        }
    
	</script>
	<script>
	$(document).ready(function(){

     $(".mOn").mouseover(function(){
        $(this).css({"background" : "#FFF3CD"});
    });
    	 $(".mOn").mouseout(function(){
        $(this).css({"background" : "#fff"});
    });
    
});


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
	<div style="width:35%;float:left;display:block;">
		<ul style="border-left:1px #dddddd solid;border-bottom:1px #dddddd solid;margin:0 auto; padding:0; display: inline-block; text-align:center; list-style-type:none; width:100%; height: 31px; font-size:14px;line-height:31px;">
			<#if grade_list??>
				<#list grade_list as item>
					<li style="border-top:1px #dddddd solid;border-right:1px #dddddd solid; display: block; padding:0 10px;  text-align:center;background-color: <#if item.gradeAble??&&item.gradeAble>#5CADE7<#elseif item.isGrade??&&item.isGrade>#C7C7C7<#else>#EAF4FF</#if>;color: <#if item.gradeAble??&&item.gradeAble>#ffffff<#else>#333333</#if>; ">
					<a  href="/expert/grade?activityId=${activityId?c!''}&enterpriseId=${item.enterpriseId?c!''}" title="评分：${item.enterpriseTitle!''}" style="background-color: <#if item.gradeAble??&&item.gradeAble>#5CADE7<#elseif item.isGrade??&&item.isGrade>#C7C7C7<#else>#EAF4FF</#if>; color: <#if item.gradeAble??&&item.gradeAble>#ffffff<#else>#333333</#if>;text-decoration:none; display:block; text-align:left;">
					${item_index+1}.${item.enterpriseTitle!''}
					</a>
					</li>
				</#list>
		    </#if>				
		</ul>		
	</div>
	<img src="/client/images/middle.png" style="float:left;margin:25% 1% 0 0;width:3%;" />
	<div>
		<table class="score2" align=right style="width:60%; ">
<#--
			<#if msg??&&msg!="">
			<caption>${msg!''}</caption>
			</#if>
	-->					
			<tr class="tr02 "> 
				<td colspan="2"  style="font-size:16px; background:#5CADE7;">
					<#if grade_list??>
				     	<#list grade_list as item>
				     		<#if item.gradeAble?? && item.gradeAble>
				     			<a target="_blank" title="查看当前企业详情" style="text-decoration:none;font-size:24px; color:#ffffff;" href="/activity/enterprise/check/${item.enterpriseId?c!''}">${item.enterpriseTitle!''}</a>
				     		</#if>	
				     	</#list>
				    </#if> 	
				</td>
			</tr>
			<tr class="tr02 " style="background:#EAF4FF;">
				<th>核心竞争力(小计)</th>
				<#if grade_list??>
				     <#list grade_list as item>
				     <#if item.gradeAble??&&item.gradeAble||item_index==0&&(!item.gradeAble??||item.gradeAble??&&item.gradeAble)>
				         <#if item.totalTechnology??>
				             <td id="${item_index}_totalTechnology">${item.totalTechnology?string("0")}</td>
				         <#else>
				             <td id="${item_index}_totalTechnology">0</td>
				         </#if>
				     </#if>    
				     </#list>
				</#if>
			</tr>
			<tr class="tr03 mOn">
				<th>技术、产品、服务、商业模式领先性、创新性</th>
				<#if grade_list??>
				    <#list grade_list as item>
				    <#if item.gradeAble??&&item.gradeAble||item_index==0&&(!item.gradeAble??||item.gradeAble??&&item.gradeAble)>
	        			<td>
	        			
	        				<select class="setGrade${item_index}" onChange="changeTechnology('${item_index}');" id="${item_index}_oneTechnology" <#if item.isGrade??&&item.isGrade|| type??&&type=="check"|| (!item.gradeAble?? ||item.gradeAble??&& !item.gradeAble)&&item_index gt 0>disabled="" style="background : #EDEDED;"</#if>>
	        				    <#list 0..10 as n>
	        					   <option value="${n}" <#if item.oneTechnology??&&n==item.oneTechnology>selected=""</#if>>${n}</option>
	        					</#list>
	        				</select>
	        		
	<#-- 评分效果试验版1
	                            <div class="evaluate_star">
	                                <input type="text"  value="0" style="width:20px;"/>
	                                <input class="goodsStar" name="goodsStar" type="hidden" value="1" />
	                                <a class="goodsStar" href="javascript:starChange('goodsStar', 1);"><img src="/client/images/start03.png"></a>
	                                <a class="goodsStar" href="javascript:starChange('goodsStar', 2);"><img src="/client/images/start03.png"></a>
	                                <a class="goodsStar" href="javascript:starChange('goodsStar', 3);"><img src="/client/images/start03.png"></a>
	                                <a class="goodsStar" href="javascript:starChange('goodsStar', 4);"><img src="/client/images/start03.png"></a>
	                                <a class="goodsStar" href="javascript:starChange('goodsStar', 5);"><img src="/client/images/start03.png"></a>
	                            </div>  
	                            -->     
	 <#-- 评分效果试验版2  
	 							<div class="box-body">
									<select id="${item_index}_oneTechnology" name="rating" onChange="changeTechnology('${item_index}');">
										<option value="1">1</option>
										<option value="2">2</option>
										<option value="3">3</option>
										<option value="4">4</option>
										<option value="5">5</option>
										<option value="6">6</option>
										<option value="7" selected="selected">7</option>
										<option value="8">8</option>
										<option value="9">9</option>
										<option value="10">10</option>
									</select>
								</div>
	      -->                     				
	        			</td>
	        		</#if>	
	    			</#list>
				</#if>
			</tr>
			<tr class="tr03 mOn">
				<th>专利、商标、著作登记、双软、双高证书</th>	
				<#if grade_list??>
				    <#list grade_list as item>
				    <#if item.gradeAble??&&item.gradeAble||item_index==0&&(!item.gradeAble??||item.gradeAble??&&item.gradeAble)>
	        			<td>
	        				<select class="setGrade${item_index}" onChange="changeTechnology('${item_index}');" id="${item_index}_twoTechnology" <#if item.isGrade??&&item.isGrade|| type??&&type=="check"|| (!item.gradeAble?? ||item.gradeAble??&& !item.gradeAble)&&item_index gt 0>disabled="" style="background : #EDEDED;"</#if>>
	        				    <#list 0..10 as n>
	        					   <option value="${n}" <#if item.twoTechnology??&&n==item.twoTechnology>selected=""</#if>>${n}</option>
	        					</#list>
	        				</select>
	        			</td>
	        		</#if>	
	    			</#list>
				</#if>
			</tr>		
			<tr class="tr03 mOn">
				<th>与竞争对手相比的优势程度</th>	
				<#if grade_list??>
				    <#list grade_list as item>
				    <#if item.gradeAble??&&item.gradeAble||item_index==0&&(!item.gradeAble??||item.gradeAble??&&item.gradeAble)>
	        			<td>
	        				<select class="setGrade${item_index}"  onChange="changeTechnology('${item_index}');" id="${item_index}_threeTechnology" <#if item.isGrade??&&item.isGrade|| type??&&type=="check"|| (!item.gradeAble?? ||item.gradeAble??&& !item.gradeAble)&&item_index gt 0>disabled="" style="background : #EDEDED;"</#if>>
	        				    <#list 0..10 as n>
	        					   <option value="${n}" <#if item.threeTechnology??&&n==item.threeTechnology>selected=""</#if>>${n}</option>
	        					</#list>
	        				</select>
	        			</td>
	        		</#if>	
	    			</#list>
				</#if>
			</tr>				
	
			<tr class="tr02 " style="background:#EAF4FF;">
				<th>团队能力(小计)</th>
				<#if grade_list??>
				     <#list grade_list as item>
				     <#if item.gradeAble??&&item.gradeAble||item_index==0&&(!item.gradeAble??||item.gradeAble??&&item.gradeAble)>
				         <#if item.totalGroup??>
				             <td id="${item_index}_totalGroup">${item.totalGroup?string("0")}</td>
				         <#else>
				             <td id="${item_index}_totalGroup">0</td>
				         </#if>
				     </#if>    
				     </#list>
				</#if>
			</tr>
			<tr class="tr03 mOn">
				<th>核心领头人的专业能力及资源</th>
				<#if grade_list??>
	    			<#list grade_list as item>
	    			<#if item.gradeAble??&&item.gradeAble||item_index==0&&(!item.gradeAble??||item.gradeAble??&&item.gradeAble)>
	    			<td>
	    				<select class="setGrade${item_index}"  onChange="changeGroup('${item_index}');" id="${item_index}_oneGroup" <#if item.isGrade??&&item.isGrade|| type??&&type=="check"|| (!item.gradeAble?? ||item.gradeAble??&& !item.gradeAble)&&item_index gt 0>disabled="" style="background : #EDEDED;"</#if>>
	    				    <#list 0..10 as n>
	    					   <option value="${n}" <#if item.oneGroup??&&n==item.oneGroup>selected=""</#if>>${n}</option>
	    					</#list>
	    				</select>
	    			</td>
	    			</#if>
	    			</#list>
			    </#if>
			</tr>
			<tr class="tr03 mOn">
	            <th>团队成员的专业能力及分工是否合理</th>
	            <#if grade_list??>
	                <#list grade_list as item>
	                <#if item.gradeAble??&&item.gradeAble||item_index==0&&(!item.gradeAble??||item.gradeAble??&&item.gradeAble)>
	                <td>
	                    <select class="setGrade${item_index}"  onChange="changeGroup('${item_index}');" id="${item_index}_twoGroup" <#if item.isGrade??&&item.isGrade|| type??&&type=="check"|| (!item.gradeAble?? ||item.gradeAble??&& !item.gradeAble)&&item_index gt 0>disabled="" style="background : #EDEDED;"</#if>>
	                        <#list 0..10 as n>
	                           <option value="${n}" <#if item.twoGroup??&& n==item.twoGroup>selected=""</#if>>${n}</option>
	                        </#list>
	                    </select>
	                </td>
	                </#if>
	                </#list>
	            </#if>
	        </tr>		
				
			<tr class="tr02 " style="background:#EAF4FF;">
				<th>市场潜力(小计)</th>
				<#if grade_list??>
				     <#list grade_list as item>
				     <#if item.gradeAble??&&item.gradeAble||item_index==0&&(!item.gradeAble??||item.gradeAble??&&item.gradeAble)>
				         <#if item.totalFeasibility??>
				             <td id="${item_index}_totalFeasibility">${item.totalFeasibility?string("0")}</td>
	                     <#else>
	                         <td id="${item_index}_totalFeasibility">0</td>
	                     </#if>			             
	                 </#if>    
				     </#list>
				</#if>
			</tr>
			<tr class="tr03 mOn">
				<th>潜在市场规模大小及已有的市场份额</th>
				<#if grade_list??>
				    <#list grade_list as item>
				    <#if item.gradeAble??&&item.gradeAble||item_index==0&&(!item.gradeAble??||item.gradeAble??&&item.gradeAble)>
	        			<td>
	        				<select  class="setGrade${item_index}" onChange="changeFeasibility('${item_index}');" id="${item_index}_oneFeasibility" <#if item.isGrade??&&item.isGrade|| type??&&type=="check"|| (!item.gradeAble?? ||item.gradeAble??&& !item.gradeAble)&&item_index gt 0>disabled="" style="background : #EDEDED;"</#if>>
	        					<#list 0..10 as n>
	        					   <option value="${n}" <#if item.oneFeasibility??&&n==item.oneFeasibility>selected=""</#if>>${n}</option>
	        					</#list>
	        				</select>
	        			</td>
	        		</#if>	
	    			</#list>
				</#if>
			</tr>
			<tr class="tr03 mOn">
				<th>市场开发价值与开发成本</th>
				<#if grade_list??>	
				    <#list grade_list as item>
				    <#if item.gradeAble??&&item.gradeAble||item_index==0&&(!item.gradeAble??||item.gradeAble??&&item.gradeAble)>
	        			<td>
	        				<select class="setGrade${item_index}"  onChange="changeFeasibility('${item_index}');" id="${item_index}_twoFeasibility" <#if item.isGrade??&&item.isGrade|| type??&&type=="check"|| (!item.gradeAble?? ||item.gradeAble??&& !item.gradeAble)&&item_index gt 0>disabled="" style="background : #EDEDED;"</#if>>
	        				    <#list 0..10 as n>
	        					   <option value="${n}" <#if item.twoFeasibility??&&n==item.twoFeasibility>selected=""</#if>>${n}</option>
	        					</#list>
	        				</select>
	        			</td>
	        		</#if>	
	    			</#list>
				</#if>
			</tr>
	
			<tr class="tr02 " style="background:#EAF4FF;">
				<th>投资价值(小计)</th>
				<#if grade_list??>
				     <#list grade_list as item>
				     <#if item.gradeAble??&&item.gradeAble||item_index==0&&(!item.gradeAble??||item.gradeAble??&&item.gradeAble)>
				         <#if item.totalMarketValue??>
				             <td id="${item_index}_totalMarketValue">${item.totalMarketValue?string("0")}</td>
				         <#else>
				             <td id="${item_index}_totalMarketValue">0</td>
				         </#if>
				     </#if>    
				     </#list>
				</#if>
			</tr>
			<tr class="tr03 mOn">
				<th>行业环境及现有基础条件能否支撑</th>
				<#if grade_list??>
				    <#list grade_list as item>
				    <#if item.gradeAble??&&item.gradeAble||item_index==0&&(!item.gradeAble??||item.gradeAble??&&item.gradeAble)>
	        			<td>
	        				<select class="setGrade${item_index}"  onChange="changeMarketValue('${item_index}');" id="${item_index}_oneMarketValue" <#if item.isGrade??&&item.isGrade|| type??&&type=="check"|| (!item.gradeAble?? ||item.gradeAble??&& !item.gradeAble)&&item_index gt 0>disabled="" style="background : #EDEDED;"</#if>>
	        				    <#list 0..10 as n>
	        					   <option value="${n}" <#if item.oneMarketValue??&&n==item.oneMarketValue>selected=""</#if>>${n}</option>
	        					</#list>
	        				</select>
	        			</td>
	        		</#if>	
	    			</#list>
				</#if>
			</tr>
			<tr class="tr03 mOn">
				<th>财务状况及融资条件</th>	
				<#if grade_list??>
				    <#list grade_list as item>
				    <#if item.gradeAble??&&item.gradeAble||item_index==0&&(!item.gradeAble??||item.gradeAble??&&item.gradeAble)>
	        			<td>
	        				<select  class="setGrade${item_index}" onChange="changeMarketValue('${item_index}');" id="${item_index}_twoMarketValue" <#if item.isGrade??&&item.isGrade|| type??&&type=="check"|| (!item.gradeAble?? ||item.gradeAble??&& !item.gradeAble)&&item_index gt 0>disabled="" style="background : #EDEDED;"</#if>>
	        				    <#list 0..10 as n>
	        					   <option value="${n}" <#if item.twoMarketValue??&&n==item.twoMarketValue>selected=""</#if>>${n}</option>
	        					</#list>
	        				</select>
	        			</td>
	        		</#if>	
	    			</#list>
				</#if>
			</tr>
			
			<tr class="tr02 " style="background:#EAF4FF;">
				<th>现场表现力(小计)</th>
				<#if grade_list??>
				     <#list grade_list as item>
				     <#if item.gradeAble??&&item.gradeAble||item_index==0&&(!item.gradeAble??||item.gradeAble??&&item.gradeAble)>
				         <#if item.totalExpression??>
				             <td id="${item_index}_totalExpression">${item.totalExpression?string("0")}</td>
				         <#else>
				             <td id="${item_index}_totalExpression">0</td>
				         </#if>
				     </#if>    
				     </#list>
				</#if>
			</tr>
			<tr class="tr03 mOn">
				<th>路演方式的创新程度及现场感染力</th>
				<#if grade_list??>
				    <#list grade_list as item>
				    <#if item.gradeAble??&&item.gradeAble||item_index==0&&(!item.gradeAble??||item.gradeAble??&&item.gradeAble)>
	        			<td>
	        				<select class="setGrade${item_index}"  onChange="changeExpression('${item_index}');" id="${item_index}_oneExpression" <#if item.isGrade??&&item.isGrade|| type??&&type=="check"|| (!item.gradeAble?? ||item.gradeAble??&& !item.gradeAble)&&item_index gt 0>disabled="" style="background : #EDEDED;"</#if>>
	        				    <#list 0..10 as n>
	        					   <option value="${n}" <#if item.oneExpression??&&n==item.oneExpression>selected=""</#if>>${n}</option>
	        					</#list>
	        				</select>
	        			</td>
	        		</#if>	
	    			</#list>
				</#if>
			</tr>		
			
			<tr class="tr02 "style="background:#EAF4FF;">
				<th>合计</th>
					<#if grade_list??>
		    			<#list grade_list as item>
		    			<#if item.gradeAble??&&item.gradeAble||item_index==0&&( !item.gradeAble?? || item.gradeAble?? && item.gradeAble )>
		    			     <td  rowspan="2"><input  class="setGrade${item_index}"  <#if item.isGrade??&&item.isGrade || type??&&type=="check"|| (!item.gradeAble?? ||item.gradeAble??&& !item.gradeAble)&&item_index gt 0>disabled="" style="background : #EDEDED; -webkit-appearance:none;" value="确定" <#else>style="cursor:pointer;border: none; background-color: #e67817; height:80%; color: #fff;-webkit-appearance:none;" value="确定"</#if> type="button" onClick="submitPoint('${item_index}','${item.number!''}','${item.enterpriseTitle!''}');"  /></td>
		    			</#if>
		    			</#list>
				    </#if>
			</tr>
			<#--<#if !type??>-->
				<tr class="tr02 mOn" style="height:48px;">
					<#if grade_list??>
		    			<#list grade_list as item>
		    			<#if item.gradeAble??&&item.gradeAble||item_index==0&&(!item.gradeAble??||item.gradeAble??&&item.gradeAble)>
		    			     <#if item.totalPoint??>
		    			         <th rolspan="2" ><b style="font-size: 36px;color: red;" id="${item_index}_totalPoint">${item.totalPoint?string("0")}</b></th>
		    			     <#else>
		    			         <th rolspan="2" ><b style="font-size: 36px;color: red;" id="${item_index}_totalPoint">0</b></th>
		    			     </#if>
		    			 </#if>    
		    			</#list>
					</#if>
				</tr>
			<#--</#if>	-->
				<script type="text/javascript"   src="/client/js/rollValue.js"></script>
	<script type="text/javascript">
		$("select").rollValue({minValue:0,maxValue:10,step:1});
	</script>
		</table>
</div>

	<#if print??&&print=="print">
	   <input style="margin-left: 640px; margin-top: 20px; width:100px;height: 30px; font-size: 14px;" type="button" class="area_batch" onclick="location.href='/expert/export/grade?activityId=${activityId?c!''}&expertId=${expertId?c!''}'"  value="打印评分表" />
	</#if>
</body>
</html>