<div class="footer">
    <div class="footer_box">
    <div class="footer_box_01">
        <img src="/client/images/footer_logo.png" />
        <ul class="footer_coperit">
            <li><a>合作公司：</a></li>
                <#if site_link_list??>
                    <#list site_link_list as link>
                        <#if link_index lt 12> 
                            <li><a href="${link.linkUri}" target="_blank" title="${link.title}">${link.title}</a></li>
                        </#if>
                    </#list>
                </#if>         
        </ul>
    </div>
    <div class="footer_box_02"></div>
    <div class="footer_box_03">
        <a>地址：${site.address!''}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        电话：${site.telephone!''}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        QQ：${site.qq1!''}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
   邮箱： ${site.adminEmail!''}</a>
    </div>
    <div class="footer_box_04"><a>免责声明:如果侵犯了你的权益，请发邮件至：${site.adminEmail!''}，我们会及时删除侵权内容，谢谢合作！</a></div>
    <div class="footer_box_05"><a>Copyright © 2014 ${site.seoTitle!''} All Rights Reserved.</a> </div>
    </div>
</div>