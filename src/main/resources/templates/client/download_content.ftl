        <div class="right_title">
        <a>首页&nbsp;&gt;&nbsp;</a>
        <a>资料下载</a>
        </div>
        <div class="right_crouse">
        <#if load_data_page??>
        <#list load_data_page.content as item>
            <dl class="crouse">
                <dd><a>${item.title!''}</a><p>${item.content!''}</p>
                <a href="/download/data?name=${item.imgUrl}" style="  background-color: #008e45;
																	  width: 60px;
																	  height: 30px;
																	  color: white;
																	  border: none;
																	  float: right;
																	  margin-top: 20px;
																	  font-size:10px;
																	  line-height:30px;
																	  text-align:center;
																	  margin-right: 30px;">立即下载</a> 
                </dd>
            </dl>
         </#list>
         </#if>
            <div class="page">
               <#if load_data_page??>
                    <#assign continueEnter=false>
                    <#if load_data_page.number+1 == 1>
                        <span class="disabled">&lt;&lt;上一页</span>
                    <#else>
                        <a href="javascript:changePage(${load_data_page.number-1})">&lt;&lt;上一页</a>
                    </#if>
                    
                    <#if load_data_page.totalPages gt 0>
                        <#list 1..load_data_page.totalPages as page>
                            <#if page <= 3 || (load_data_page.totalPages-page) < 3 || (load_data_page.number+1-page)?abs<3 >
                                <#if page == load_data_page.number+1>
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
                    
                    <#if load_data_page.number+1 == load_data_page.totalPages || load_data_page.totalPages==0>
                        <span class="disabled">下一页&gt;&gt;</span>
                    <#else>
                        <a href="javascript:changePage(${load_data_page.number+1})">下一页&gt;&gt;</a>
                    </#if>
                </#if>
             </div>
        </div>