					<#if msg??>
					<h1 style="line-height:30px;  width:100%; text-align:center; float:left;">
					${msg!''}
					</h1>
					</#if>
				<table class="new_list">
		        	<tr class="list_title">
		        		<th width="20%">已选择列表</th>
		        		<th width="20%">地区</th>
		        		<th width="20%">行业归属</th>
		                <#if statusId??&&statusId == 2>
                        	<th width="20%">推荐理由</th>
                        </#if>
		        		<th width="20%">操作</th>
		        	</tr>
		        <#if selected_enterprise_list??>
		        	<#list selected_enterprise_list as item>
			        	<tr>
			        		<!--<td><input style="width:15px;height:15px;float:left; margin:0 0 0 10px ;" type="checkbox" value=""/></td>-->
			        		<td><a href="/region/enterprise/check/${item.enterpriseId?c!''}" target=_blank>${item_index+1!''}、${item.enterpriseTitle!''}</a></td>
			        		<td style="color:#0ab2cb;">${item.area!''}</td>
			        		<td style="color:#e67817;">${item.type!''}</td>
			        		<#if statusId??&&statusId == 2>
			        			<td><input style="width: 98%;height: 30px;" disabled="" type="text" id="reason${item.id?c!''}" value="${item.reason!''}"/></td>
			        		</#if>
			        		<td><a href="javascript:removeEnterprise1(${item.id?c!''},${activityId?c!''},${statusId!''});">取消</a></td>
			        	</tr>
		        	</#list>
		        </#if>	   
		        </table>