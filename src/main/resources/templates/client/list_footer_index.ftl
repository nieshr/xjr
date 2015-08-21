<div class="line20"></div>
<div class="pagelist">
  <div class="l-btns">
  <#--  <span>第</span><input name="size" type="text" value="${size!""}" onchange="javascript:setTimeout(__doPostBack('btnSize',''), 0)" onkeypress="if 

(WebForm_TextBoxKeyHandler(event) == false) return false;" class="pagenum" onkeydown="return checkNumber(event);"><span>条/页</span>
   -->
    <input name="page" type="hidden" value="${PAGE_DATA.number!'0'}" />
  </div>
  <div id="PageContent" class="default"><span>共&nbsp;<#if PAGE_DATA??><label>${PAGE_DATA.totalElements}</label></#if>&nbsp;条&nbsp;&nbsp;&nbsp;第&nbsp;<#if 

PAGE_DATA??><label>${PAGE_DATA.number+1}</label></#if>/<#if PAGE_DATA??><label>${PAGE_DATA.totalPages}</label></#if>&nbsp;页&nbsp;&nbsp;&nbsp;</span>
        <#if PAGE_DATA??>
            <#assign continueEnter=false>
            <#if PAGE_DATA.number+1 == 1>
                <span class="disabled">&lt;&lt;上一页</span>
            <#else>
                <a href="javascript:page2(${mid},'btnPage','${PAGE_DATA.number-1}')">&lt;&lt;上一页</a>
            </#if>
            
            <#if PAGE_DATA.totalPages gt 0>
                <#list 1..PAGE_DATA.totalPages as page>
                    <#if page <= 3 || (PAGE_DATA.totalPages-page) < 3 || (PAGE_DATA.number+1-page)?abs<3 >
                        <#if page == PAGE_DATA.number+1>
                            <span class="current">${page}</span>
                        <#else>
                            <a href="javascript:page2(${mid},'btnPage','${page-1}')">${page}</a>
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
                <span class="disabled">下一页&gt;&gt;</span>
            <#else>
                <a href="javascript:page2(${mid},'btnPage','${PAGE_DATA.number+1}')">下一页&gt;&gt;</a>
            </#if>
        </#if>
  </div>
</div>