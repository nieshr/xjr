<div class="left_content">
    <dl class="leftbar">
        <dt><a>${menu_sub_name }</a><p>${menu_name }</p></dt>
            <#if menu_name= "新闻中心" || menu_name="课程设置">
	            <dd id="list0"  class="menu_list leftbar_color">
	            <a id="list0" href="javascript:selectCat(${menu_id},0);" title="全部">全部</a>
	            </dd>
            </#if>
            <#if info_category_list??>
                <#list info_category_list as item>  
                <dd id="list${item.id}"  class="menu_list">
                    <a href="javascript:selectCat(${item.menuId},${item.id});" title="">${item.title!'' }</a>
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