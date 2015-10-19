<#if content_list??>        
    <#list content_list as item>
        <dd>
            <span>时间:<#if item.coachDate??>${item.coachDate?string("yyyy-MM-dd")}</#if></span>
            <p>${item.content!''}</p>
        </dd>
    </#list>
</#if>