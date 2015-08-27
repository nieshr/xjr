 <div class="page">
 <#if PAGE_DATA??>
     <#assign continueEnter=false>
     
     <#if PAGE_DATA.totalPages gt 0>
         <#list 1..PAGE_DATA.totalPages as page>
             <#if page <= 3 || (PAGE_DATA.totalPages-page) < 3 || (PAGE_DATA.number+1-page)?abs<3 >
                 <#if page == PAGE_DATA.number+1>
                     <input type="button" value="${page }" />
                 <#else>
                     <input onclick="javascript:page2(${mid},'btnPage','${page-1}')" class="" type="button" value="${page}" /> 
                 </#if>
                 <#assign continueEnter=false>
             <#else>
                 <#if !continueEnter>
                     ...
                     <#assign continueEnter=true>
                 </#if>
             </#if>
         </#list>
     </#if>
     
     <#if PAGE_DATA.number+1 == 1>
         <input class="block" type="button" value="上一页" />
     <#else>
         <input onclick="javascript:page2(${mid},'btnPage','${PAGE_DATA.number-1}')" class="block" type="button" value="上一页" />                
     </#if>
     

     
     <#if PAGE_DATA.number+1 == PAGE_DATA.totalPages || PAGE_DATA.totalPages==0>
         <input class="block" type="button" value="下一页" />
     <#else>
         <input onclick="javascript:page2(${mid},'btnPage','${PAGE_DATA.number+1}')" class="block" type="button" value="下一页" /> 
     </#if>
 </#if>

    <input id="jump-page" class="page_text" type="" value="${PAGE_DATA.number+1 }" />
    <input class= "block" onclick="javascript:pageJump2(${mid},'btnPage')" type="button" value="确定" />
    
    <a>共${PAGE_DATA.totalPages}页</a>
</div>