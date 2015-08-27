<div class="right_title">
    <a>首页&nbsp;&gt;&nbsp;</a>   
    <a>${menu_name }</a>
    <a><#if info_name??> > ${info_name.title!'' }<#elseif coursetake??> > 课程报名</#if></a>
</div>    
<#if menu_name= "新闻中心">
	<div class="name"><#if info_name??>${info_name.title!'' }<#else>新闻中心</#if></div>
	<dl class="news_box">
	    <#if info_page??>
	        <#list info_page.content as item>           
	            <dd>
	                <a href="javascript:selectContent(${item.id },${item.menuId })" title="">${item.title!''}</a>
	                <span style="float:right;">${item.updateTime!''}</span>
	                <span style="float:right;">浏览次数：${item.viewCount!'0'}</span>
	            </dd>
	        </#list>
	    </#if>
	</dl>
<#elseif menu_name="名师精英">
    <div class="right_teacher">
        <#if info_page??>
            <#list info_page.content as item> 
		        <dl class="teacher">
		            <dt><a href="javascript:selectContent(${item.id },${item.menuId })"><img src="${item.imgUrl!''}" title="${item.brief!''}"/></a></dt>
		            <dd><a href="javascript:selectContent(${item.id },${item.menuId })">${item.title!'' }</a></dd>
		        </dl>
		    </#list>
		</#if>        
    </div>
<#elseif menu_name="课程设置">
    <#if coursetake??>
    <script type="text/javascript">
    $(document).ready(function(){
        
        //初始化表单验证
        $("#form1").Validform({
            tiptype: 3,
            ajaxPost:true,
            callback: function(data) {
                if (data.code==0)
                {
                    alert("提交成功");
                    window.location.reload();
                }
                else
                {
                    alert(data.message);
                }
            }
        });
       $("#Validform_msg").hide();
    });
    </script> 
    <div class="crouse_title">
        <p style="margin-top:20px;">您选择的是 :<b style="color:#ef0000;">&nbsp;${coursetake!""}&nbsp;</b>课程，请详细填写下方联系方式预约报名。</p>
    </div>
    <form action="/info/submit" id="form1" method="post">
    <input type="hidden" name="courseId" value=${courseId}>
    <input type="hidden" name="courseName" value=${coursetake}>
    <dl class="message">
        <dd>
            <span>姓名 ： </span>
            <input name="name" type="" value=""/>
        </dd>
        <dd>
            <span>电话 ： </span>
            <input name="mobile" type="" value=""/>
        </dd>
        <dd>
            <span>Email ： </span>
            <input name="mail" type="" value=""/>
        </dd>
        <dd>
            <span>QQ ： </span>
            <input name="qq" type="" value=""/>
        </dd>
        <dd class="message_box">
            <span>留言 ： </span>
            <textarea name="content" cols="" rows=""></textarea>
            <a>可输入200字</a>
        </dd>
        <dd class="message_btn">
            <input type="submit" value="报名"/>
        </dd>
    </dl>
    
    </form>
    <#else>
    <div class="right_crouse">
        <#if info_page??>
            <#list info_page.content as item> 
		        <dl class="crouse">
		            <dt><img src="${item.imgUrl!''}" /></dt>
		            <dd>
		                <a href="javascript:courseTake(${item.id },${item.menuId })">${item.title!''}</a>
		                <#if item.content?length lt 400>
		                    <p>${item.content!''}</p>
		                <#else>
		                    <p>${item.content[0..400] }...</p>
		                </#if>
		            </dd>
		        </dl>
		    </#list>
		</#if>        
    </div>
    </#if>
<#elseif menu_name="招贤纳才">
    <div class="name">招贤纳才</div>
        <#if info_page??>
            <#list info_page.content as item>
			    <dl class="news_box">
			        <dt><a>${item.title!''}</a></dt>
			        <dd>${item.content!''}</dd>
			    </dl>
			</#list>
		</#if>

<!-- mdj 2015-8-27 10:14:03 -->
<#elseif nemu_name="课程报名">
    
    
    <!-- mdj 2015-8-27 10:14:03 end-->
</#if>

<!--内容底部-->
<#if coursetake??>
<#else>
<#assign PAGE_DATA=info_page />
<#if catId??>
    <#include "/client/list_footer.ftl" />
<#else>
    <#include "/client/list_footer_index.ftl" />
</#if>   
</#if>         
 <!--内容底部 end-->       
         