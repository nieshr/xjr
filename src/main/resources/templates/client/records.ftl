<#if content_list??>        
    <#list content_list as item>
        <dd>
            <span style="width:150px;">时间：<#if item.coachDate??>${item.coachDate?string("yyyy-MM-dd")}</#if></span>
            <p>${item.content!''}</p>
        </dd>
    </#list>
</#if>