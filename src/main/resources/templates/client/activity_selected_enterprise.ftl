				<table class="new_list">
		        	<tr class="list_title">
		        		<th width="30%">备选企业列表</th>
		        		<th width="25%">地区</th>
		        		<th width="25%">行业归属</th>
		        		<th width="20%">操作</th>
		        	</tr>
		        <#if selected_enterprise_list??>
		        	<#list selected_enterprise_list as item>
			        	<tr>
			        		<!--<td><input style="width:15px;height:15px;float:left; margin:0 0 0 10px ;" type="checkbox" value=""/></td>-->
			        		<td>${item.enterpriseTitle!''}</td>
			        		<td style="color:#0ab2cb;">${item.area!''}</td>
			        		<td style="color:#e67817;">${item.type!''}</td>
			        		<td><a href="javascript:removeEnterprise(${item.id?c!''},${activityId?c!''});">取消预选</a></td>
			        	</tr>
		        	</#list>
		        </#if>	   
		        </table>