<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>专项行动</title>
	<link rel="stylesheet" href="/client/css/news_base.css">
	<link rel="stylesheet" href="/client/css/news_main.css">
	<script src="/client/js/main.js"></script>
</head>
<body>
<!-- head  -->
    <#include "/client/news_common_header.ftl" />
<!-- head end -->

<!-- contend -->
<div class="titlelist">
	<div id="titlelist">
		<div>
			<a href="#" class="active">行动介绍</a>
			<a href="#">种子引导基金</a>
			<a href="#">报名方式</a>
			<a href="#">评选方式</a>
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
				<img src="${item.imgUrl!''}">
				<h4>${item.title!''}</h4>
				<h5>${item.brief!''}</h5>
			</div>
			</#list>
		</#if>	
		</div>
		</div>
		<div class="specialaction-third clearl">
		<#if activityType_list??>
		  <#list activityType_list as item>
			<div class="specialaction-third-one">
				<img src="${item.imgUrl!''}">
				<h3>${item.title!''}</h3>
				<h4>${item.content!''}</h4>
			</div>
		  </#list>
		</#if>	
		</div>
		<div class="specialaction-fourth clearl">
		<div class="specialaction-fourth-body">
		<#if intro2??>
		  <#list intro2 as item>
			<div class="specialaction-fourth-one">
				<img src="${item.imgUrl!''}">
				<h4>${item.title!''}</h4>
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