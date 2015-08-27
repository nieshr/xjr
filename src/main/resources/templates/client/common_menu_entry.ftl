<div class="left_content">
    <dl class="leftbar">
        <dt><a>${info_name.content }</a><p>${info_name.title }</p></dt>
        <#if info_page??>
            <#list info_page.content as item>  
                <dd id="list${item.id}"  class="menu_list">
                    <a href="javascript:selectEntry(${item.id},${item.menuId});" title="">${item.title!'' }</a>
                </dd>
            </#list>
        </#if>    
    </dl>
    
    <dl class="course">
        <dt><a>开展课程</a><p>更多&gt;&gt;</p></dt>
        <dd><a>学校简介</a></dd>
        <dd><a>学校简介</a></dd>
        <dd><a>学校简介</a></dd>
        <dd><a>学校简介</a></dd>
        <dd><a>学校简介</a></dd>
        <dd><a>学校简介</a></dd>
        <dd><a>学校简介</a></dd>
        <dd><a>学校简介</a></dd>
        <dd><a>学校简介</a></dd>
        <dd><a>学校简介</a></dd>
    </dl>
    <dl class="call">
        <dt><a>开展课程</a><p>更多&gt;&gt;</p></dt>
        <dd class="call_text01">
            <a>学校简介:</a>
            <input type="" value="" />
        </dd>
        <dd class="call_text01">
            <a>学校简介:</a>
            <input type="" value="" />
        </dd>
        <dd class="call_btn">
            <p>立即联系</p>
        </dd>
        <dd class="call_tel">
            <span>报名热线：400-0011-652</span>
            <span>咨询老师：刘老师</span>
            <span>点击咨询：<img src="/client/images/about_qq_27.png" />&nbsp;QQ交谈</span>
        </dd>
    </dl>
    </div>