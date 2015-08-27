<div class="right_title">
    <a>首页&nbsp;&gt;&nbsp;</a>   
    <a>${menu_name }</a>
    <a><#if info_name??> > ${info_name.title!'' }</#if></a>
</div>    
<#if menu_name= "新闻中心">
	<div class="name"><#if info_name??>${info_name.title!'' }<#else>新闻中心</#if></div>
	<dl class="news_box">
	    <#if info_page??>
	        <#list info_page.content as item>           
	            <dd>
	                <a href="javascript:selectContent(${item.id },${item.menuId })" title="">${item.title!''}</a>
	                <span style="float:right;">${item.updateTime!''}</span>
	                <span style="float:right;">浏览次数：${item.viewCount!'0'}</span>
	            </dd>
	        </#list>
	    </#if>
	</dl>
<#elseif menu_name="名师精英">
    <div class="right_teacher">
        <#if info_page??>
            <#list info_page.content as item> 
		        <dl class="teacher">
		            <dt><a href="javascript:selectContent(${item.id },${item.menuId })"><img src="${item.imgUrl!''}" title="${item.brief!''}"/></a></dt>
		            <dd><a href="javascript:selectContent(${item.id },${item.menuId })">${item.title!'' }</a></dd>
		        </dl>
		    </#list>
		</#if>        
    </div>
<#elseif menu_name="课程设置">
    <div class="right_crouse">
        <#if info_page??>
            <#list info_page.content as item> 
		        <dl class="crouse">
		            <dt><img src="${item.imgUrl!''}" /></dt>
		            <dd>
		                <a href="javascript:courseTake(${item.id },${item.menuId })">${item.title!''}</a>
		                <#if item.content?length lt 400>
		                    <p>${item.content!''}</p>
		                <#else>
		                    <p>${item.content[0..400] }...</p>
		                </#if>
		            </dd>
		        </dl>
		    </#list>
		</#if>        
    </div>
<#elseif menu_name="招贤纳才">
    <div class="name">招贤纳才</div>
        <#if info_page??>
            <#list info_page.content as item>
			    <dl class="news_box">
			        <dt><a>${item.title!''}</a></dt>
			        <dd>${item.content!''}</dd>
			    </dl>
			</#list>
		</#if>	    
</#if>

<!--内容底部-->
<#assign PAGE_DATA=info_page />
<#if catId??>
    <#include "/client/list_footer.ftl" />
<#else>
    <#include "/client/list_footer_index.ftl" />
</#if>            
 <!--内容底部 end-->       
         