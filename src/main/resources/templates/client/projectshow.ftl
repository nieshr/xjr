<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>企业项目</title>
	<link rel="shortcut icon" href="/client/images/icon.ico" />
	<link rel="stylesheet" href="/client/css/news_base.css">
	<link rel="stylesheet" href="/client/css/news_main.css">
	
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
			<#if show_list??>
				<#list show_list as item>
					<a href="/info/projectshow/detail?mid=<#if item.menuId??>${item.menuId?c!''}</#if>&catId=${item.id?c!''}">${item.title!''}</a>
				</#list>
			</#if>		
		</div>
	</div>
	<div class="teamdisplay ">
		<p>企业展</p>
		<ul class="clearl">
			<#if team_page??>
				<#list team_page.content as item>
					<#if item_index lt 6>
					
						<li>
								<img style="cursor:pointer;" 
										src="<#if item.imgUrl??&&item.imgUrl?length gt 0>${item.imgUrl!''}<#else>/client/images/banner_bg1.png</#if>" 
										width="322px" height="144px" 
										title="查看详情" 
										onclick="location.href='<#if item.linkUrl??&&item.linkUrl?length gt 0>${item.linkUrl!''}<#else>/info/list/content/${item.id?c!''}?mid=${item.menuId!''}</#if>'">
								<a title="查看详情" style="padding: 0px;" href="<#if item.linkUrl??&&item.linkUrl?length gt 0>${item.linkUrl!''}<#else>/info/list/content/${item.id?c!''}?mid=${item.menuId!''}</#if>">
								<h3>${item.title!''}</h3>
								<#if item.brief?length lt 20>
								<h4>${item.brief!''}</h4>
								<#else>
								<h4>${item.brief[0..19]}...</h4>
								</#if>
								</a>
						</li>
						
					</#if>
				</#list>
			</#if>	
		</ul>
	</div>
	
	<div class="teamdisplay ">
		<p>团队展</p>
		<ul class="clearl">
			<#if project_page??>
				<#list project_page.content as item>
					<#if item_index lt 6>
						<li>
								<img style="cursor:pointer;" 
										src="<#if item.imgUrl??&&item.imgUrl?length gt 0>${item.imgUrl!''}<#else>/client/images/banner_bg1.png</#if>" 
										width="322px" height="144px"
										title="查看详情"
										 onclick="location.href='<#if item.linkUrl??&&item.linkUrl?length gt 0>${item.linkUrl!''}<#else>/info/list/content/${item.id?c!''}?mid=${item.menuId!''}</#if>'">
								<a style="padding: 0px;" href="<#if item.linkUrl??&&item.linkUrl?length gt 0>${item.linkUrl!''}<#else>/info/list/content/${item.id?c!''}?mid=${item.menuId!''}</#if>">
								<h3>${item.title!''}</h3>
								<#if item.brief?length lt 20>
									<h4>${item.brief!''}</h4>
								<#else>
									<h4>${item.brief[0..19]}...</h4>
								</#if>
								</a>
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
								<img style="cursor:pointer;" 
										src="<#if item.imgUrl??&&item.imgUrl?length gt 0>${item.imgUrl!''}<#else>/client/images/banner_bg1.png</#if>" 
										width="322px" height="144px" 
										title="查看详情"
										onclick="location.href='<#if item.linkUrl??&&item.linkUrl?length gt 0>${item.linkUrl!''}<#else>/info/list/content/${item.id?c!''}?mid=${item.menuId!''}</#if>'">
								<a style="padding: 0px;" href="<#if item.linkUrl??&&item.linkUrl?length gt 0>${item.linkUrl!''}<#else>/info/list/content/${item.id?c!''}?mid=${item.menuId!''}</#if>">
								<h3>${item.title!''}</h3>
								<#if item.brief?length lt 20>
									<h4>${item.brief!''}</h4>
								<#else>
									<h4>${item.brief[0..19]}...</h4>
								</#if>
								</a>
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