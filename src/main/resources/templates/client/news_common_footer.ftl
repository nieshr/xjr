<div class="footlist">
<div class="footlist-title">
	<ul>
		<li class="footlist-title-title">友情链接：</li>
	    <#if site_link_list??>
	        <#list site_link_list as link>
	            <#if link_index lt 12> 
	                <li><a href="${link.linkUri}" target="_blank" title="${link.title}">${link.title}</a></li>
	            </#if>
	        </#list>
	    </#if>        
	</ul>
</div>
<div class="footlist-body">
	<h5>联系电话：
	<#list site.telephone?split(",") as item>
		<#if item !="">
			${item!''} &nbsp;
		</#if>
	</#list>		
	</h5>
	<h5>邮箱：${site.adminEmail!''}</h5>
	<h5>传真：
	<#list site.fax?split(",") as item>
		<#if item !="">	
			${item!''} &nbsp;
		</#if>
	</#list>					
	</h5>
	<h5>地址：${site.address!''}</h5>
	<h5>${site.copyright!''}</h5> 
	<div class="footercode"> 
	<img src="${site.wxQrCode!''}">
	<p>微信公众号</p>
	</div>
</div>         
</div>
