<div class="right_title">
    <a href="/">首页&nbsp;&gt;&nbsp;</a>
    <a>合作加盟</a>
</div>
 
<div class="right_crouse">
    <div class="crouse_title">
        <#if user_consult_page??>
            <#list user_consult_page.content as item>
                <#if item.mobile?length = 11>
                   <a>${item.mobile[0..2]!''}****${item.mobile[7..10]!''} ：${item.content}</a>
                <#elseif item.mobile?length = 8>
                   <a>${item.mobile[0..1]!''}****${item.mobile[6..7]!''} ：${item.content}</a>
                </#if>
                <#if item.reply??> <p>客服 : ${item.reply}</p> </#if>
            </#list>
        </#if>
    </div>
     <div class="page_info" >
               <#if user_consult_page??>
                    <#assign continueEnter=false>
                    <#if user_consult_page.number+1 == 1>
                        <span class="disabled">&lt;&lt;上一页</span>
                    <#else>
                        <a href="javascript:changePage(${user_consult_page.number-1})">&lt;&lt;上一页</a>
                    </#if>
                    
                    <#if user_consult_page.totalPages gt 0>
                        <#list 1..user_consult_page.totalPages as page>
                            <#if page <= 3 || (user_consult_page.totalPages-page) < 3 || (user_consult_page.number+1-page)?abs<3 >
                                <#if page == user_consult_page.number+1>
                                    <span class="current">${page}</span>
                                <#else>
                                    <a href="javascript:changePage(${page-1})">${page}</a>
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
                    
                    <#if user_consult_page.number+1 == user_consult_page.totalPages || user_consult_page.totalPages==0>
                        <span class="disabled">下一页&gt;&gt;</span>
                    <#else>
                        <a href="javascript:changePage(${user_consult_page.number+1})">下一页&gt;&gt;</a>
                    </#if>
                </#if>
             </div>
     
    <!--message-->
     <form action="/cooperation/submit" id="form1" method="post">
        <dl class="message">
            <dd>
            <span>姓名 ： </span>
            <input name="username" type="" value=""/>
        </dd>
        <dd>
            <span>电话 ： </span>
            <input name="mobile" type="" value=""/>
        </dd>
        <dd>
            <span>Email ： </span>
            <input name="email" type="" value=""/>
        </dd>
        <dd>
            <span>QQ ： </span>
            <input name="qq" type="" value=""/>
        </dd>
        <dd class="message_box">
            <span>留言 ： </span>
            <textarea name="content" cols="" rows=""></textarea>
            <a style="width:150px;">可输入200字</a>
        </dd>
        <dd class="message_btn">
            <input type="submit" value="确定"/>
        </dd>
    </form>
</div>