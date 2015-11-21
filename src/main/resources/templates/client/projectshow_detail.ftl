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
					<a <#if catId??&&catId==item.id>class="active"</#if> href="/info/projectshow/detail?mid=<#if item.menuId??>${item.menuId?c!''}</#if>&catId=${item.id?c!''}">${item.title!''}</a>
				</#list>
			</#if>		
		</div>
	</div>
	<div class="teamdisplay ">
		<p><#if menuName??>${menuName!''}</#if></p>
		<ul class="clearl">
			<#if show_page??>
				<#list show_page.content as item>
					
						<li>
								<img style="cursor:pointer;" 
										src="<#if item.imgUrl??&&item.imgUrl?length gt 0>${item.imgUrl!''}<#else>/client/news_img/projectdisplay.jpg</#if>" 
										width="322px" height="144px" 
										title="查看详情" 
										onclick="location.href='<#if item.linkUrl??&&item.linkUrl?length gt 0>${item.linkUrl!''}<#else>/info/list/content/${item.id?c!''}?mid=${item.menuId!''}</#if>'">
								<h3>${item.title!''}</h3>
								<#if item.brief?length lt 20>
									<h4>${item.brief!''}</h4>
								<#else>
									<h4>${item.brief[0..19]}...</h4>
								</#if>
						</li>
					
				</#list>
			</#if>	
		</ul>
	</div>
	
</div>

<!-- 代码 开始 -->

<#assign PAGE_DATA=show_page />

<div class="pagnation" id="pagnation">
 <#if PAGE_DATA??>
	 <#if PAGE_DATA.number+1 == 1>
          <a disabled="disabled"  class="page-prev" title="前面没有了">上一页"</a>               
     <#else>
         <a href="/info/projectshow/detail?page=${PAGE_DATA.number-1}&mid=11&catId=${catId?c!''}"  class="page-prev" title="上一页">上一页"</a>                
     </#if>
     
     <#assign continueEnter=false>
     
     <#if PAGE_DATA.totalPages gt 0>
         <#list 1..PAGE_DATA.totalPages as page>
             <#if page <= 3 || (PAGE_DATA.totalPages-page) < 3 || (PAGE_DATA.number+1-page)?abs<3 >
                 <#if page == PAGE_DATA.number+1>
                     <a  class ="current">${page }</a>
                 <#else>
                     <a href="/info/projectshow/detail?page=${page-1}&mid=11&catId=${catId?c!''}">${page}</a> 
                 </#if>
                 <#assign continueEnter=false>
             <#else>
                 <#if !continueEnter>
                     ...
                     <#assign continueEnter=true>
                 </#if>
             </#if>
         </#list>
     </#if>
     
     
     <#if PAGE_DATA.number+1 == PAGE_DATA.totalPages || PAGE_DATA.totalPages==0>
         <a disabled="disabled" class="page-next" title="已是最后一页">下一页</a> 
     <#else>
         <a href="/info/projectshow/detail?page=${PAGE_DATA.number+1}&mid=11&catId=${catId?c!''}" class="page-next" title="下一页">下一页</a> 
     </#if>
 </#if>
    
</div>

<!-- 代码 结束 -->
<!-- contendend -->

<!-- 底部 -->
<#include "/client/news_common_footer.ftl" />
<!-- 底部end -->

</body>
</html>