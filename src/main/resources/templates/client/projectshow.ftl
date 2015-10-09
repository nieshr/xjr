<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>企业项目</title>
	<link rel="shortcut icon" href="/client/images/icon.ico" />
	<link rel="stylesheet" href="/client/css/news_base.css">
	<link rel="stylesheet" href="/client/css/news_main.css">
	<script src="/client/js/l_main.js"></script>
	<script src="/client/js/jquery-1.9.1.min.js"></script>
<script>
function move1()
{
    $('html,body').animate({scrollTop:290},400);
}
function move2()
{
    $('html,body').animate({scrollTop:890},400);
}
function move3()
{
    $('html,body').animate({scrollTop:1490},400);
}
</script>
</head>
<body>
<#include "/client/news_common_header.ftl" />

<!-- contend -->
<div class="titlelist">
	<div id="titlelist">
		<div>
			<a href="javascript:move1();" class="active">团队展</a>
			<a href="javascript:move2();">项目展</a>
			<a href="javascript:move3();">创业故事</a>
		</div>
	</div>
	<div class="teamdisplay ">
		<p>团队展</p>
		<ul class="clearl">
			<#if team_page??>
				<#list team_page.content as item>
					<#if item_index lt 6>
						<li>
								<img style="cursor:pointer;" src="${item.imgUrl!''}" width="322px" height="144px" onclick="location.href='/info/list/content/${item.id?c!''}?mid=${item.menuId?c!''}'">
								<h3>${item.title!''}</h3>
								<#if item.brief?length lt 20>
									<h4>${item.brief!''}</h4>
								<#else>
									<h4>${item.brief[0..19]}...</h4>
								</#if>
						</li>
					</#if>
				</#list>
			</#if>	
		</ul>
	</div>
	
	<div class="teamdisplay ">
		<p>项目展</p>
		<ul class="clearl">
			<#if project_page??>
				<#list project_page.content as item>
					<#if item_index lt 6>
						<li>
								<img style="cursor:pointer;" src="${item.imgUrl!''}" width="322px" height="144px" onclick="location.href='/info/list/content/${item.id?c!''}?mid=${item.menuId?c!''}'">
								<h3>${item.title!''}</h3>
								<#if item.brief?length lt 20>
									<h4>${item.brief!''}</h4>
								<#else>
									<h4>${item.brief[0..19]}...</h4>
								</#if>
						</li>
					</#if>
				</#list>
			</#if>	
		</ul>
	</div>

	<div class="teamdisplay ">
		<p>创业故事</p>
		<ul class="clearl">
			<#if story_page??>
				<#list story_page.content as item>
					<#if item_index lt 6>
						<li>
								<img style="cursor:pointer;" src="${item.imgUrl!''}" width="322px" height="144px" onclick="location.href='/info/list/content/${item.id?c!''}?mid=${item.menuId?c!''}'">
								<h3>${item.title!''}</h3>
								<#if item.brief?length lt 20>
									<h4>${item.brief!''}</h4>
								<#else>
									<h4>${item.brief[0..19]}...</h4>
								</#if>
						</li>
					</#if>
				</#list>
			</#if>	
		</ul>
	</div>
</div>
<!-- contendend -->

<!-- 底部 -->
<#include "/client/news_common_footer.ftl" />
<!-- 底部end -->

</body>
</html>