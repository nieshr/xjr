<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>指导单位</title>
	<link rel="shortcut icon" href="/client/images/icon.ico" />
    <link rel="stylesheet" href="/client/css/news_base.css">
    <link rel="stylesheet" href="/client/css/news_main.css">

    <script src="/client/js/jquery-1.9.1.min.js"></script>
</head>
<body>
<!-- 导航 -->
<#include "/client/resource_common_header.ftl" />


<!-- contend -->
<div class="titlelist">
	<div id="titlelist">
		<div>
		<#if actegory_list?? && actegory_list?size gt 0>
		      <#list actegory_list as acte>
            		<a href="#">${acte.title!''}</a>
		      </#list>
		</#if>
		</div>
	</div>
<div class="contactustitle">
        <#if actegory_list?? && actegory_list?size gt 0>
              <#list actegory_list as acte>
                    <span>${acte。title!''}</span>
                        <hr style="border-top:1px solid #DDDDDD;" />
                        <ul class="teacher-list-danwei">
                            <#if ("article_"+acte_index+"_list")?eval??>
                                <#list ("article_"+acte_index+"_list")?eval as art>
                                    <li>
                                        <img src="${art.imgUrl!''}" alt="">
                                        <p>${art.title!''}</p>
                                    </li>
                                </#list>
                             </#if>
                        </ul>
              </#list>
        </#if>
</div>
</div>

<!-- contendend -->

<!-- 底部 -->
<#include "/client/news_common_footer.ftl" />
<!-- 底部end -->

</body>
</html>