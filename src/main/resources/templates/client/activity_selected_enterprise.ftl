	<div class="list_base2" id="selectedEnterprise">			
				<table class="new_list">
		        	<tr class="list_title">
		        		<th width="30%">��ѡ���б�</th>
		        		<th width="25%">����</th>
		        		<th width="25%">��ҵ����</th>
		        		<th width="20%">����</th>
		        	</tr>
		        <#if selected_enterprise_list??>
		        	<#list selected_enterprise_list as item>
			        	<tr>
			        		<!--<td><input style="width:15px;height:15px;float:left; margin:0 0 0 10px ;" type="checkbox" value=""/></td>-->
			        		<td><a href="/activity/enterprise/check/${item.enterpriseId?c!''}" target=_blank >${item.enterpriseTitle!''}</a></td>
			        		<td style="color:#0ab2cb;">${item.area!''}</td>
			        		<td style="color:#e67817;">${item.type!''}</td>
			        		<td><a href="javascript:removeEnterprise(${item.id?c!''},${activityId?c!''});">ȡ��</a></td>
			        	</tr>
		        	</#list>
		        </#if>	   
		        </table>
    </div>