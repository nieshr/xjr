<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>科技小巨人-专项行动</title>
	<link rel="shortcut icon" href="/client/images/icon.ico" />
	<link rel="stylesheet" href="/client/css/news_base.css">
	<link rel="stylesheet" href="/client/css/news_main.css">
	<script src="/client/js/main.js"></script>
	<script src="/client/js/jquery-1.9.1.min.js"></script>
	<script src="/client/js/l_main.js"></script>
</head>
<body>
<!-- head  -->
    <#include "/client/news_common_header.ftl" />
<!-- head end -->

<!-- contend -->
<div class="titlelist">
	<div id="titlelist">
		<div>
			<#if aboutActivity_page??>
				<#list aboutActivity_page.content as item>
					<a href="<#if item.linkUrl??&&item.linkUrl!="">${item.linkUrl!''}<#else>/info/list/content/${item.id?c!''}?mid=13</#if>" title="">${item.title!''}</a>
				</#list>
			</#if>	
		</div>
	</div>
	<!-- 专项行动body -->
	<div class="specialaction">
		<div class="specialaction-first">
			<dl>
			    <#if article??>
			    
				<dt>专项行动简介</dt>
				${article.content}
				</#if>
			</dl>
		</div>
		<div class="specialaction-secend clearl">
		<div class="specialaction-secend-body">
		<#if intro1??>
		  <#list intro1 as item>
			<div class="specialaction-secend-one">
				<img src="${item.imgUrl!''}"  alt="封面图片" width=88 height=88>
				<h4><a href="<#if item.linkUrl??&&item.linkUrl?length gt 0>${item.linkUrl!''}<#else>/info/list/content/${item.id?c!''}?mid=${item.menuId!''}</#if>" title="查看更多信息">${item.title!''}</a></h4>
				<h5>${item.brief!''}</h5>
			</div>
			</#list>
		</#if>	
		</div>
		</div>
		<div class="specialaction-third clearl">
		<#if breed_page??>
		  <#list breed_page.content as item>
			<div class="specialaction-third-one">
				<img src="${item.imgUrl!''}" alt="封面图片" width=120 height=100 />
				<h3><a href="<#if item.linkUrl??&&item.linkUrl?length gt 0>${item.linkUrl!''}<#else>/info/list/content/${item.id?c!''}?mid=${item.menuId!''}</#if>" title="查看更多信息">${item.title!''}</a></h3>
				<h4>${item.brief!''}</h4>
			</div>
			
		  </#list>
		</#if>	
		</div>
		<div class="specialaction-fourth clearl">
		<div class="specialaction-fourth-body">
		<#if intro2??>
		  <#list intro2 as item>
			<div class="specialaction-fourth-one">
				<img src="${item.imgUrl!''}"  alt="封面图片" width=100 height=100>
				<h4><a href="<#if item.linkUrl??&&item.linkUrl?length gt 0>${item.linkUrl!''}<#else>/info/list/content/${item.id?c!''}?mid=${item.menuId!''}</#if>" title="查看更多信息">${item.title!''}</a></h4>
				<h5>${item.brief!''}</h5>
			</div>
		   </#list>
		</#if>	
		</div>
		</div>
	</div>
	<!-- 专项行动bodyend -->
</div>
<!-- contendend -->

<!-- 底部 -->
<#include "/client/news_common_footer.ftl" />
<!-- 底部end -->

</body>
</html>