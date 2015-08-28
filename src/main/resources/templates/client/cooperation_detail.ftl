<div class="right_title">
    <a>首页&nbsp;&gt;&nbsp;</a>
    <a>合作加盟&nbsp;&gt;&nbsp;</a>
</div>
 
<div class="right_crouse">
    <div class="crouse_title">
        <#if user_consult_page??>
            <#list user_consult_page.content as item>
                <a>${item.username} ：${item.content}</a>
                <#if item.reply??> <p>A : ${item.reply}</p> </#if>
            </#list>
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
            <a>可输入200字</a>
        </dd>
        <dd class="message_btn">
            <input type="submit" value="报名"/>
        </dd>
    </form>
</div>