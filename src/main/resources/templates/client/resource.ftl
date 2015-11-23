<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>专家资源</title>
	<link rel="shortcut icon" href="/client/images/icon.ico" />
	<link rel="stylesheet" href="/client/css/news_base.css">
	<link rel="stylesheet" href="/client/css/news_main.css">
	
	<script src="/client/js/jquery-1.9.1.min.js"></script>
</head>
<body>
<#include "/client/news_common_header.ftl" />
<!-- contend -->
<div class="titlelist">
	<div id="titlelist">
	<div>
		<a href="/info/resource/expert" >创业导师</a>
		<a href="/info/resource/3?mid=12">投资机构</a>
		</div>
	</div>
	<!-- 创业导师 -->
	<div class="teacher">
		<span>创业导师</span>
		<hr style="border-top:1px solid #DDDDDD;" />
		<ul class="teacher-list">
			<#if tutor_page??>
				<#list tutor_page.content as item>
					<#if item_index lt 9>
						<li>
							<img title="查看详情" style="cursor:pointer;" src="<#if item.imageUri??&&item.imageUri != "">${item.imageUri!''}<#else>/client/images/default.jpg</#if>" width="150px" height="150px" onclick="location.href='/info/list/content/expert/${item.id?c!''}'">
							<h6>${item.inCharge!''}</h6>
							<#if  item.content??>
								<#if  item.content?length lt 20>
									<p>${item.content!''}</p>
								<#else>
									<p>${item.content[0..20]!''}...</p>
								</#if>
							</#if>
						</li>
					</#if>
				</#list>
			</#if>	
			<li>
				<img style="cursor:pointer;"  src="/client/news_img/searchmore.png" alt="查看更多" onclick="location.href='/info/resource/expert'">
			</li>
		</ul>
	
	</div>
	<!-- 创业导师end -->
	<!-- 投资机构 -->
	<div class="teacher">
		<span>投资机构</span>
		<hr style="border-top:1px solid #DDDDDD;" />
		<ul class="teacher-list">
			<#if invest_page??>
				<#list invest_page.content as item>
					<#if item_index lt 9>
						<li>
							<img style="cursor:pointer;" src="<#if item.imgUri??&&item.imgUri != "">${item.imgUri!''}<#else>/client/images/default.jpg</#if>" width="150px" height="150px" onclick="location.href='/info/list/content/${item.id?c!''}?mid=${item.menuId?c!''}'">
							<h6>${item.title!''}</h6>
							<#if item.brief?length lt 20>
								<p>${item.brief!''}</p>
							<#else>
								<p>${item.brief[0..20]!''}...</p>
							</#if>
						</li>
					</#if>
				</#list>
			</#if>	
			<li>
				<img style="cursor:pointer;"  src="/client/news_img/searchmore.png" alt="查看更多" onclick="location.href='/info/resource/3?mid=12'">
			</li>
		</ul>
	
	</div>
	<!-- 投资机构end -->

</div>
<!-- contendend -->

<!-- 底部 -->
<#include "/client/news_common_footer.ftl" />
<!-- 底部end -->

</body>
</html>