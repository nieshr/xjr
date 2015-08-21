<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7"/>
<title><#if site??>${site.seoTitle!''}-</#if>${site.company!''}</title>
<meta name="keywords" content="${site.seoKeywords!''}">
<meta name="description" content="${site.seoDescription!''}">
<meta name="copyright" content="${site.copyright!''}" />
<script type="text/javascript" src="/client/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/client/js/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="/client/css/base.css"/>
<link rel="stylesheet" type="text/css" href="/client/css/index.css"/>
</head>

<body>
<#include "/client/commen_header.ftl" />

<!--内容-->
<div class="wrapper content">
   <div class="content_main">
	    <div class="content_left">
	      <div class="content_left_1">服务项目</div>
	      <div class="content_left_2">
	        <ul>
	         <#if service_page??>
                <#list service_page.content as item>
                    <#if item_index lt 6> 
	                   <li><a href="/info/service/content/${item.id}?mid=10" title="">${item.title!'' }</a></li>
	                </#if>
	            </#list>
	         </#if>       
	        </ul>
	      </div>
	    </div>
	    <div class="content_center">
	      <div class="content_center_1">
	          <strong>公司简介</strong><b>Company Profile</b>
	      </div>
	      <#if profile_page??>
	         <#list profile_page.content as item>
	            <#if item_index lt 1> 
			      <div class="content_center_2">
				      <img src="${item.imgUrl!"/client/images/t01f0cfa9b4ca48760d.jpg"}" />
				      <span>
				         <label>
				             <#if item.content?length lt 150>
				                 ${item.content!'' }
				             <#else>
				                 ${item.content[0..147]}...     
				             </#if>
				         </label>
				         <a href="/info/service/content/${item.id}?mid=10" title="">【查看全部】</a>
				      </span>
			      </div>
			    </#if>
			 </#list>
		  </#if>	    
	    </div>
	   <div class="content_right">
	     <div class="content_center_1">
	         <strong>新闻中心</strong><b>News</b><a href="/info/list/8" title="">更多>></a>
	     </div>
	     <div class="content_center_2">
	       <ul>
	       <#if news_page??>
	           <#list news_page.content as item>
	               <#if item_index lt 6> 
			       <li><a href="/info/content/${item.id!''}?mid=${item.menuId!''}" title=""><span>${item.title!''}</span></a>
			           <label>${item.createTime?string("yyyy-MM-dd")!'' }</label>
			       </li>
			    </#if>
	           </#list>
	       </#if>         
	       </ul>
	     </div>
	   </div>
   </div>
  <div class="content_main">
    <div class="content_left">
          <div class="content_center_1"><strong>相关常识 </strong><b>Related Knowledge </b><a href="/info/list/11?" title="">更多>></a></div>
	      <div class="content_left_22">
	        <ul>
	        <#if knowledge_page??>
               <#list knowledge_page.content as item>
                  <#if item_index lt 6> 
	                 <li><a href="/info/content/${item.id!''}?mid=${item.menuId!''}" title="">${item.title!''}</a></li>
	              </#if>
	           </#list>
	        </#if>      
	        </ul>
	      </div>
    </div>
    <div class="content_center content_right">
        <div class="content_center_1"><strong>快速通道</strong><b>Links</b><a href="#" title="">更多>></a></div>
        <div class="content_center_2 content_center_botton">
	        <ul>
	            <#if site_link_list??>
	                <#list site_link_list as link>
	                    <#if link_index lt 6>                    
	                        <li><a href="${link.linkUri}" target="_blank" title="">${link.title}</a></li>  
	                    </#if>
	                </#list>
	            </#if>
	        </ul>
        </div>
    </div>
    <div class="content_right">
      <div class="content_center_1"><strong>联系我们 

</strong><b>Contact Us</b></div>
      <div class="content_center_2">
        <ul>
          <li>联系电话：<span>${site.telephone!'' }</span></li>
          <li>详细地址：<span>${site.address!'' }</span></li>
       <#--   <li>公司邮箱：<span>12345678@163.com</span></a> -->
          <li>公司传真：<span>${site.fax!'' }</span></li>
          <li>客服QQ：<span>                        
                        ${site.qq1!'' } 
                    </span>                 
          </li>
        </ul>
      </div>
    </div>
  </div>
</div>
<!--底部-->
<div class="footer">
   <#include "/client/commen_footer.ftl" />
</div>
</body>
</html>
