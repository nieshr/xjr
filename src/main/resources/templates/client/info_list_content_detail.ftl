<div class="right_title_body">
<a>首页&nbsp;&gt;&nbsp;</a>
<a href="/info/list/${menu_id}">${menu_name}&nbsp;&gt;&nbsp;</a>
<a href="javascript:selectCat(${menu_id},${catId})">${info_name.title!'' }</a>
</div>

<#if menu_name= "新闻中心">
<div class="name_body">
<a>${info.title!'' }</a>
<a style="font-size:12px; color:#666666; margin-top:10px;">${info.createTime?string("yyyy-MM-dd HH:mm")!'' }<span>来源：${info.source!'' }</span>
<span>点击：${info.viewCount!'0' }</span></a>
</div>

<div class="right_box_body">
    <#if info.imgUrl??>
        <img src="${info.imgUrl!'' }"/>
    </#if>  
    <p>
        ${info.content!'' }
    </p>
    
    <div class="news_page_left_body">
       <#if prev_info??>
           <a href="/info/list/content/${prev_info.id}?mid=${prev_info.menuId}">上一篇：${prev_info.title}</a>
       </#if>
    </div>

    <div class="news_page_right_body">
        <#if next_info??>
            <a href="/info/list/content/${next_info.id}?mid=${next_info.menuId}">下一篇：${next_info.title}</a>
        </#if>
    </div>  

</div>
<#elseif menu_name= "名师精英">
    <div class="right_teacher">
        <dl class="teacher_img">
            <dt><a>教师风采</a></dt>
            <dd>
                <#if info.showPictures??>
	                <#list info.showPictures?split(",") as uri>
	                    <#if ""!=uri && uri_index < 4>
                            <img src="${uri!''}" />
                        </#if>
                    </#list>
                </#if>                        
            </dd>
        </dl>  
        <dl class="teacher_intro">
            <dt><a>教师简介</a></dt>
            <dd>
                <p>${info.content!''}</p>
            </dd>
        </dl>  
    </div>
</#if>
