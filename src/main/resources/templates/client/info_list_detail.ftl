        <ul>
            <li class="li_su">
                <strong><#if info_name??>${info_name.title!'' }<#else>全部</#if></strong>
                <b>您当前的位置：<a href="/" title="">首页</a> >${menu_name }  <#if info_name??> > ${info_name.title!'' }</#if></b>
            </li>
            <#if info_page??>
                <#list info_page.content as item>             
                    <li>
                        <#if menu_name = "相关常识">    
                            <a href="/info/content/${item.id!''}?mid=${item.menuId!''}" title=""><span class="tu">${item.title!''}</span></a>                        
                        <#elseif menu_name = "新闻中心">
	                        <a href="/info/content/${item.id!''}?mid=${item.menuId!''}" title=""><span>${item.title!''}</span></a> 
	                        <b style="float:right;">${item.createTime!''}</b>
	                    </#if>    
                    </li>
                </#list>
            <#else>
            </#if>
        </ul>
        
<!--内容底部-->
<#assign PAGE_DATA=info_page />
<#if catId??>
    <#include "/client/list_footer.ftl" />
<#else>
    <#include "/client/list_footer_index.ftl" />
</#if>    
<!--/内容底部-->