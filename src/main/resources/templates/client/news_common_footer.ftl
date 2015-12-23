<div class="footlist">
<div class="footlist-title" style="height:<#if site_link_list??>${(site_link_list?size / 6 )?ceiling * 32}px<#else>32px</#if>;">
	<ul>
		<li class="footlist-title-title">友情链接：</li>
	    <#if site_link_list??>
	        <#list site_link_list as link>
	        
	            <#if link_index  % 6 == 0 && link_index gt 0> 
	            	 <li style="margin-left : 75px;"><a href="${link.linkUri}" target="_blank" title="${link.linkUri}">${link.title}</a><span>|</span></li>
	            <#elseif ( link_index + 1 ) %6== 0 && link_index gt 0> 
	            	 <li><a href="${link.linkUri}" target="_blank" title="${link.linkUri}">${link.title}</a><span>|</span></li> <br>
	            <#else>
	           
	            	<#if link_has_next>
		                <li><a href="${link.linkUri}" target="_blank" title="${link.linkUri}">${link.title}</a><span>|</span></li>
	                <#else>
	                	<li><a href="${link.linkUri}" target="_blank" title="${link.linkUri}">${link.title}</a><span>|</span></li>
	                </#if>
	            </#if>
	        </#list>
	    </#if>        
	</ul>
</div>
<div class="footlist-body">
	<h5><span>电话：
	<#list site.telephone?split(",") as item>
		<#if item !="" && item_index lt 3>
			${item!''} &nbsp;
		</#if>
	</#list>		
	</span>
	<span style= "margin-left : 320px;">
	邮箱：${site.adminEmail!''}
	</span>
	</h5>
	<h5>
	<span>传真：
	<#list site.fax?split(",") as item>
		<#if item !="" && item_index lt 3>	
			${item!''} &nbsp;
		</#if>
	</#list>
	</span>
	<span  style= "margin-left : 320px;">
		地址：${site.address!''}
	</span>					
	</h5>
	<h5><#if site.copyright??&&site.copyright?length gt 0>${site.copyright!''}<#else>科技小巨人</#if>${site.icpNumber!''}</h5> 
	<div class="footercode"> 
	<img src="${site.wxQrCode!''}">
	<p>微信公众号</p>
	</div>
</div>         
</div>
<span style="display:none;">
<script language="javascript" src="http://count26.51yes.com/click.aspx?id=261575405&logo=1" charset="gb2312"></script>
</span>