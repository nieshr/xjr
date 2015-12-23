				
	   <form name="removeEnterprise" action="/activity/selectEnterprise" method=post>
        <input type="hidden" name="keywords" value="<#if keywords??&&keywords?length gt 0>${keywords}</#if>">
    	<input type="hidden" name="area" value="<#if area??&&area?length gt 0>${area}</#if>">
    	<input type="hidden" name="type" value="<#if type??&&type?length gt 0>${type}</#if>">
    	<input type="hidden" name="formType" value="<#if formType??&&formType?length gt 0>${formType}</#if>">
    	<input type="hidden" name="__ACTION" value="remove">
    	<input type="hidden" name="activityId" value="${activityId?c!''}">
    	<input type="hidden" name="page" value="<#if page??>${page!''}"</#if>>	
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
			        		<td style="text-align:left;padding-left:10px ;"><a href="/activity/enterprise/check/${item.enterpriseId?c!''}" target=_blank ><b style="float:left;margin-left:10px;">${item_index+1!''}.</b>${item.enterpriseTitle!''}</a></td>
			        		<td style="color:#0ab2cb;">${item.area!''}</td>
			        		<td style="color:#e67817;">${item.type!''}</td>
			        		<td><a href="javascript:removeEnterprise(${item.id?c!''},${activityId?c!''});">取消</a></td>
			        	</tr>
		        	</#list>
		        </#if>	   
		        </table>
			    <div class="area_add_btn">
				    <a class="all" style="margin-left:-10px;" href="javascript:;" onclick="checkAll2(this);"><i></i><span>全选</span></a>
					<input style=" margin-left: 10px; cursor:pointer;" class="area_batch" type="submit" value="批量取消" />
				</div>    
			</form>		        

