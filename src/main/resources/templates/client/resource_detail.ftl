<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title><#if info_cat??>${info_cat.title!''}</#if></title>
	<link rel="shortcut icon" href="/client/images/icon.ico" />
	<link rel="stylesheet" href="/client/css/news_base.css">
	<link rel="stylesheet" href="/client/css/news_main.css">
	<script src="/client/js/l_main.js"></script>
	<script src="/client/js/jquery-1.9.1.min.js"></script>
</head>
<body>
<#include "/client/news_common_header.ftl" />

<!-- contend -->
<div class="titlelist">
	<div id="titlelist">
		<div>
			<a href="/info/resource/2?mid=12" <#if catId==2> class="active" </#if>>创业导师</a>
			<a href="/info/resource/3?mid=12" <#if catId==3> class="active" </#if>>投资机构</a>
		</div>
	</div>

	<div class="teacher">
		<span><#if info_cat??>${info_cat.title!''}</#if></span>
		<hr style="border-top:1px solid #DDDDDD;" />
	<ul class="teacher-list">
		<#if info_page??>
			<#list info_page.content as item>
				<li>
					<img src="${item.imgUrl!''}" alt="" onclick="location.href='/info/list/content/${item.id?c!''}?mid=12'">
					<h6>${item.title!''}</h6>
					<#if item.brief?length lt 20>
						<p>${item.brief!''}</p>
					<#else>
						<p>${item.brief[0..20]!''}...</p>
					</#if>
				</li>
			</#list>
		</#if>		
	</ul>
	
	</div>

</div>
<!-- 代码 开始 -->

	<#assign PAGE_DATA=info_page />
    <#include "/client/list_footer.ftl" />

<!-- 代码 结束 -->
<!-- contendend -->

<!-- 底部 -->
<#include "/client/news_common_footer.ftl" />
<!-- 底部end -->

</body>
</html>