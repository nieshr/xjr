<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title><#if info_cat??>${info_cat.title!''}<#else>创业导师</#if></title>
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
			<a href="/info/resource/expert" <#if !catId??> class="active" </#if>>创业导师</a>
			<a href="/info/resource/3?mid=12" <#if catId??> class="active" </#if>>投资机构</a>
		</div>
	</div>

	<div class="teacher">
		<span><#if info_cat??>${info_cat.title!''}</#if></span>
		<hr style="border-top:1px solid #DDDDDD;" />
	<ul class="teacher-list">
		<#if info_page??>
			<#list info_page.content as item>
				<#if catId??>
					<li>
						<img  style="cursor:pointer;" title="查看详情" src="<#if item.imgUrl??&&item.imgUrl != "">${item.imgUrl!''}<#else>/client/images/default.jpg</#if>" alt="展示图片" onclick="location.href='/info/list/content/${item.id?c!''}?mid=12'">
						<h6>${item.title!''}</h6>
						<#if item.brief?length lt 20>
							<p>${item.brief!''}</p>
						<#else>
							<p>${item.brief[0..20]!''}...</p>
						</#if>
					</li>
				<#else>
					<li>
						<img style="cursor:pointer;" title="查看详情" src="<#if item.imageUri??&&item.imageUri != "">${item.imageUri!''}<#else>/client/images/default.jpg</#if>" alt="展示图片" onclick="location.href='/info/list/content/expert/${item.id?c}'">
						<h6>${item.inCharge!''}</h6>
						<#if item.content??>
							<#if item.content?length lt 20>
								<p>${item.content!''}</p>
							<#else>
								<p>${item.content[0..20]!''}...</p>
							</#if>
						</#if>	
					</li>		
				</#if>				
			</#list>
		</#if>		
	</ul>
	
	</div>

</div>
<!-- 代码 开始 -->

<#assign PAGE_DATA=info_page />
<#if catId??>	
    <#include "/client/list_footer.ftl" />
<#else>
<div class="pagnation" id="pagnation">
 <#if PAGE_DATA??>
	 <#if PAGE_DATA.number+1 == 1>
          <a disabled="disabled"  class="page-prev">上一页"</a>               
     <#else>
	     <#if catId??>
	     	<a href="/info/resource/3?mid=12&page=${PAGE_DATA.number-1}">下一页</a> 
	     <#else>
	     	<a href="/info/resource/expert?page=${PAGE_DATA.number-1}">下一页</a> 
	     </#if>	              
     </#if>
     
     <#assign continueEnter=false>
     
     <#if PAGE_DATA.totalPages gt 0>
         <#list 1..PAGE_DATA.totalPages as page>
             <#if page <= 3 || (PAGE_DATA.totalPages-page) < 3 || (PAGE_DATA.number+1-page)?abs<3 >
                 <#if page == PAGE_DATA.number+1>
                     <a  class ="current">${page }</a>
                 <#else>
                 	<#if catId??>
                     	<a href="/info/resource/3?mid=12&page=${page-1}">${page}</a> 
                     <#else>
                     	<a href="/info/resource/expert?page=${page-1}">${page}</a> 
                     </#if>	
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
         <a disabled="disabled" class="page-next">下一页</a> 
     <#else>
	     <#if catId??>
	     	<a href="/info/resource/3?mid=12&page=${PAGE_DATA.number+1}">下一页</a> 
	     <#else>
	     	<a href="/info/resource/expert?page=${PAGE_DATA.number+1}">下一页</a> 
	     </#if>	
     </#if>
 </#if>
    
</div>
</#if>
<!-- 代码 结束 -->
<!-- contendend -->

<!-- 底部 -->
<#include "/client/news_common_footer.ftl" />
<!-- 底部end -->

</body>
</html>