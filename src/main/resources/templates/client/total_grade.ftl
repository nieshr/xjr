<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
    <title>项目得分汇总表</title>
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
 
 function gradeOrder(orderId)
 {
	 location.href="/activity/getGrade?activityId=${activityId?c!''}&orderId="+orderId<#if mark??&&mark=="activity">+"&mark=activity"</#if>;

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
	        	<td id="order0"  width="3%" style="background-color:<#if orderId??&&orderId==0>#FFF3CD<#else>#D9E3F3</#if>;"><a href="javascript:gradeOrder(0);" title="点击按此评分项顺序排序">总分<img class="orderIcon" id="orderIcon0" style="margin-left:2px; <#if orderId??&&orderId!=0>display:none;</#if>"  src="/client/images/down1.png" width=8px height=10px alt=↓ /></a></td>
	        	<td id="order1"  width="3%" style="background-color:<#if orderId??&&orderId==1>#FFF3CD<#else>#D9E3F3</#if>;"><a href="javascript:gradeOrder(1);" title="点击按此评分项顺序排序">核心竞争力<img class="orderIcon" id="orderIcon1"  style="margin-left:2px; <#if orderId??&&orderId!=1>display:none;</#if>" src="/client/images/down1.png" width=8px height=10px alt=↓ /></td>
	        	<td id="order3"  width="3%" style="background-color:<#if orderId??&&orderId==3>#FFF3CD<#else>#D9E3F3</#if>;"><a href="javascript:gradeOrder(3);" title="点击按此评分项顺序排序">团队能力<img class="orderIcon" id="orderIcon3" style="margin-left:2px; <#if orderId??&&orderId!=3>display:none;</#if>"   src="/client/images/down1.png" width=8px height=10px alt=↓ /></td>
	        	<td id="order2"  width="3%" style="background-color:<#if orderId??&&orderId==2>#FFF3CD<#else>#D9E3F3</#if>;"><a href="javascript:gradeOrder(2);" title="点击按此评分项顺序排序">市场潜力<img class="orderIcon" id="orderIcon2"  style="margin-left:2px; <#if orderId??&&orderId!=2>display:none;</#if>"   src="/client/images/down1.png" width=8px height=10px alt=↓ /></td>
	        	<td id="order4"  width="3%" style="background-color:<#if orderId??&&orderId==4>#FFF3CD<#else>#D9E3F3</#if>;"><a href="javascript:gradeOrder(4);" title="点击按此评分项顺序排序">投资价值<img class="orderIcon" id="orderIcon4" style="margin-left:2px; <#if orderId??&&orderId!=4>display:none;</#if>"  src="/client/images/down1.png" width=8px height=10px alt=↓ /></td>
	        	<td id="order5"  width="3%" style="background-color:<#if orderId??&&orderId==5>#FFF3CD<#else>#D9E3F3</#if>;"><a href="javascript:gradeOrder(5);" title="点击按此评分项顺序排序">现场表现力<img class="orderIcon" id="orderIcon5" style="margin-left:2px; <#if orderId??&&orderId!=5>display:none;</#if>"  src="/client/images/down1.png" width=8px height=10px alt=↓ /></td>
	            <#if aex_list??>
	                <#list aex_list as item>
	                    <td width="4%">${item.name!''}</td>
	                </#list>
	            </#if>
	        </tr>
	        <#if grade_list??>
	            <#list grade_list.content as item>
			        <tr class="tr02" <#if item.win??&&item.win==activityId>style="background-color:#FFF3CD;"</#if>>
			        	<#if mark??&&mark=="activity">
		        		<td>
		        		<span class="checkall" style="vertical-align:middle;">
			        		<input style="width:15px;height:15px;float:left; margin:0 0 0 10px ;" <#if item.win??&&item.win==activityId || (activity.statusId==1 && item_index lt 7)>checked=""</#if> id="listChkId" type="checkbox" name="listChkId" value="${item_index}"/>
			        		<input type="hidden" name="listId" id="listId" value="${item.id}">
			        	</span>	
		        		</td>
		        		</#if>
			        	<td>${item_index+1}</td>
			        	<td>${item.number!''}</td>
				        <td><#if item.win??&&item.win==activityId><a title="分配投资机构" href="/activity/invest?enterpriseId=${item.enterpriseId?c!''}&activityId=${item.activityId?c!''}">${item.enterpriseTitle!''}</a><#else>${item.enterpriseTitle!''}</#if></td>
				        <td><#if item.totalPoint??>${item.totalPoint?c!''}</#if></td>
				        <td><#if item.totalTechnology??>${item.totalTechnology?c!''}</#if></td>
				        <td><#if item.totalGroup??>${item.totalGroup?c!''}</#if></td>
				        <td><#if item.totalFeasibility??>${item.totalFeasibility?c!''}</#if></td>
				        <td><#if item.totalMarketValue??>${item.totalMarketValue?c!''}</#if></td>
				        <td><#if item.totalExpression??>${item.totalExpression?c!''}</#if></td>
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
	    <#if mark??&&mark=="activity"&&activity.statusId??&&(activity.statusId==1||activity.statusId==2)>
        <a class="all" style="margin-left:10px;" href="javascript:;" onclick="checkAll(this);"><i></i><span>全选</span></a>
        <input style="cursor:pointer;" class="area_batch" style="margin-left:5%;" type="submit"  value="路演胜出" />
        </#if>
        <#if mark??&&mark=="activity">
        <input style="margin-left: 640px; margin-top: 20px; width:100px;height: 30px; font-size: 14px;" class="area_batch" onclick="location.href='/activity/export/totalGrade?activityId=<#if activityId??>${activityId?c!''}</#if>'"  type="button" value="打印评分表" />
		</#if>
	</form>    
    
</body>
</html>