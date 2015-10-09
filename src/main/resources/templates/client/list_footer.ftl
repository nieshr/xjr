<div class="pagnation" id="pagnation">
 <#if PAGE_DATA??>
	 <#if PAGE_DATA.number+1 == 1>
          <a disabled="disabled"  class="page-prev">上一页"</a>               
     <#else>
         <a href="/info/resource/${catId?c!''}?mid=${mid?c!''}&page=${PAGE_DATA.number-1}"  class="page-prev">上一页"</a>                
     </#if>
     
     <#assign continueEnter=false>
     
     <#if PAGE_DATA.totalPages gt 0>
         <#list 1..PAGE_DATA.totalPages as page>
             <#if page <= 3 || (PAGE_DATA.totalPages-page) < 3 || (PAGE_DATA.number+1-page)?abs<3 >
                 <#if page == PAGE_DATA.number+1>
                     <a  class ="current">${page }</a>
                 <#else>
                     <a href="/info/resource/${catId?c!''}?mid=${mid?c!''}&page=${page-1}">${page}</a> 
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
     
     
     <#if PAGE_DATA.number+1 == PAGE_DATA.totalPages || PAGE_DATA.totalPages==0>
         <a disabled="disabled" class="page-next">下一页</a> 
     <#else>
         <a href="/info/resource/${catId?c!''}?mid=${mid?c!''}&page=${PAGE_DATA.number+1}" class="page-next">下一页</a> 
     </#if>
 </#if>
    
</div>
