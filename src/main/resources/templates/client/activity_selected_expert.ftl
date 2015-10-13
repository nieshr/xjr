				<table class="new_list">
		        	<tr class="list_title">
		        		<th width="30%">姓名</th>
		        		<th width="25%">联系电话</th>
		        		<th width="25%">邮箱</th>
		        		<th width="20%">操作</th>
		        	</tr>
		        <#if selected_expert_list??>
		        	<#list selected_expert_list as item>
			        	<tr>
			        		<!--<td><input style="width:15px;height:15px;float:left; margin:0 0 0 10px ;" type="checkbox" value=""/></td>-->
			        		<td>${item.name!''}</td>
			        		<td style="color:#0ab2cb;">${item.usermobile!''}</td>
			        		<td style="color:#e67817;">${item.email!''}</td>
			        		<td><a href="javascript:removeExpert(${item.id?c!''},${activityId?c!''});">取消</a></td>
			        	</tr>
		        	</#list>
		        </#if>	   
		        </table>