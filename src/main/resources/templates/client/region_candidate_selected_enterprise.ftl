<div id="selectedEnterprise"  style="display:block;float:left;">			
<form name="removeEnterprise" action="/region/candidateEnterprise/${activityId?c!''}" method=post>
    <input type="hidden" name="keywords" value="<#if keywords??&&keywords?length gt 0>${keywords}</#if>">
	<input type="hidden" name="area" value="<#if area??&&area?length gt 0>${area}</#if>">
	<input type="hidden" name="type" value="<#if type??&&type?length gt 0>${type}</#if>">
	<input type="hidden" name="formType" value="<#if formType??&&formType?length gt 0>${formType}</#if>">
	<input type="hidden" name="__ACTION" value="remove">
	<input type="hidden" name="page" value="<#if page??>${page!''}"</#if>>
	<div class="list_base2" >				
		<table class="new_list">
        	<tr class="list_title">
        		<th></th>
        		<th width="30%">已选择列表</th>
        		<th width="25%">地区</th>
        		<th width="25%">所属类别</th>
        		<th width="20%">操作</th>
        	</tr>
        <#if selected_enterprise_list??>
        	<#list selected_enterprise_list as item>
	        	<tr>
	        		<td>
	        		<span class="checkall2" style="vertical-align:middle;">
		        		<input style="width:15px;height:15px;float:left; margin:0 0 0 10px ;" <#if item.isSelect??&&item.isSelect&&item.selectActivityId??&&item.selectActivityId==activityId>disabled=""</#if> id="listChkId" type="checkbox" name="listChkId" value="${item_index}"/>
		        		<input type="hidden" name="listId" id="listId" value="${item.id}">
		        	</span>	
	        		</td>		
	        		<td style="text-align:left;padding-left:10px ;"><a href="/region/enterprise/check/${item.enterpriseId?c!''}" target=_blank ><b style="float:left;margin-left:10px;">${item_index+1!''}.</b>${item.enterpriseTitle!''}</a></td>
	        		<td style="color:#0ab2cb;">${item.area!''}</td>
	        		<td style="color:#e67817;">${item.type!''}</td>
	        		<td><a href="javascript:candidateRemoveEnterprise(${item.id?c!''},${activityId?c!''});">取消</a></td>
	        		<#-- /region/candidateRemoveEnterprise?id=${item.id?c!''}&activityId=${activityId?c!''}-->
	        	</tr>
        	</#list>
        </#if>	   
		</table>
		</div>
	    <div class="area_add_btn" style="display:block;float:left;padding-bottom:12px;">
		    <a class="all" style="margin-left:-10px;float:left;margin-top:24px;" href="javascript:;" onclick="checkAll2(this);"><i></i><span>全选</span></a>
			<input style=" margin-left: 10px; cursor:pointer;float:left;" class="area_batch" type="submit" value="批量取消" />
			<input style="width:175px;" onclick="location.href='/region/export/candidate?activityId=${activityId?c!''}&area=${area!''}'" class="area_save_btn01" type="button" value="导出《预选项目汇总表》" />
		</div>    
</form>    		        
</div>        