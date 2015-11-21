<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>科技小巨人-指导单位</title>
	<link rel="shortcut icon" href="/client/images/icon.ico" />
	<link rel="stylesheet" href="/client/css/news_base.css">
	<link rel="stylesheet" href="/client/css/news_main.css">
	<script type="text/javascript" src="/client/js/jquery-1.9.1.min.js"></script>
	<script src="/client/js/main.js"></script>

</head>
<script>
function subActivity(hight)
{
    $('html,body').animate({scrollTop:hight},400);
}    
</script>
<body>
<!-- 导航 -->
<!-- head  -->
    <#include "/client/news_common_header.ftl" />
<!-- head end -->

<!-- contend -->
<div class="titlelist">
	<div id="titlelist">
	<#--
		<div>
			<#if article_list??>
				<#list article_list as item>
					<a href="javascript:subActivity(100 + ${item_index}*100)">${item.title!''}</a>
				</#list>
			</#if>		
		</div>
		-->
	</div>
<div class="contactustitle">
	<#if host_list??>
		<#list host_list as hostList>
		<span>
			<#if hostList_index == 0 >
				${menu_name1}
			<#elseif hostList_index == 1>
				${menu_name2}	
			<#elseif hostList_index == 2>
				${menu_name3}			
			<#elseif hostList_index == 3>
				${menu_name4}		
			<#elseif hostList_index == 4>
				${menu_name5}		
			<#elseif hostList_index == 5>
				${menu_name6}		
			</#if>					
		</span>
		<hr style="border-top:1px solid #DDDDDD;" />
		<ul class="teacher-list-danwei">
			<#if hostList??>
				<#list hostList as item>
					<li>
						<img  style="cursor:pointer;" 
								src="<#if item.imgUrl??&&item.imgUrl?length gt 0>${item.imgUrl!''}<#else>/client/news_img/modification01.png</#if>" 
								alt="名称" 
								width=150 height=150
								title="查看详情"
								onclick="location.href='<#if item.linkUrl??&&item.linkUrl?length gt 0>${item.linkUrl!''}<#else>/info/list/content/${item.id?c!''}?mid=${item.menuId!''}</#if>'">
						<p>${item.title!''}</p>
					
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