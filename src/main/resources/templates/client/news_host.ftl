<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>指导单位</title>
	<link rel="stylesheet" href="/client/css/news_base.css">
	<link rel="stylesheet" href="/client/css/news_main.css">
	<script type="text/javascript" src="/client/js/jquery-1.9.1.min.js"></script>
	<script src="/client/js/main.js"></script>
</head>
<body>
<!-- 导航 -->
<!-- head  -->
    <#include "/client/news_common_header.ftl" />
<!-- head end -->

<!-- contend -->
<div class="titlelist">
	<div id="titlelist">
		<div>
		<a href="#">主办单位</a>
		<a href="#">指导单位</a>
		<a href="#">支持单位</a>
		<a href="#">主办单位</a>
		<a href="#">主办单位</a>
		<a href="#">主办单位</a>
		</div>
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
						<a title="查看更多信息" href="<#if item.linkUrl??&&item.linkUrl?length gt 0>${item.linkUrl!''}<#else>/info/list/content/${item.id?c!''}?mid=${item.menuId!''}</#if>">
						<img src="${item.imgUrl!''}" alt="名称" width=150 height=150>
						<p>${item.title!''}</p>
						</a>
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