<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7"/>
<title><#if site??>${site.seoTitle!''}-</#if>${site.company!''}</title>
<meta name="keywords" content="${site.seoKeywords!''}">
<meta name="description" content="${site.seoDescription!''}">
<meta name="copyright" content="${site.copyright!''}" />
<link href="/client/css/base.css" rel="stylesheet" type="text/css" />
<link href="/client/css/main.css" rel="stylesheet" type="text/css" />
</head>

<body>
<!--header-->
<#include "/client/common_header.ftl" />
<!--header_end -->

<!--banner-->
<div class="banner">
    <img src="/client/img/20150207064229425.jpg" />
    <ul class="banner_btn">
        <li><a></a></li>
        <li><a></a></li>
        <li><a></a></li>
        <li><a></a></li>
    </ul>
    
</div>

<!--content-->
<div class="content">
<!--best-->
    <div class="best">
        <div class="best_title"><a>金牌课程</a><p>更多&gt;&gt;</p></div>
        <#if course_page0??>
            <#list course_page1.content as item>
                <#if item_index lt 4>
			        <dl class="best_box">
			            <dt><img src="${item.imgUrl!''}" /></dt>
			            <dd>
			                <a class="best_text">
			                    <span>${item.title!''}</span>
			                    <p>${item.brief!''}</p>
			                </a>
			                <input type="button" value="课程详情" />
			            </dd>
			        </dl>
			    </#if>    
		    </#list>
		</#if>        
    </div>
<!--crouse-->
    <div class="course_box">
    <dl class="course_01">
        <dt><a>热门课程</a><p>更多&gt;&gt;</p></dt>
        <#if course_page0??>
	        <#list course_page0.content as item>
	            <#if item_index lt 10>
	                <dd><a>${item.title!''}</a></dd>
	            </#if>    
	        </#list>
        </#if>     
    </dl>
    <dl class="course_02">
        <dt><a>开展课程</a><p>更多&gt;&gt;</p></dt>        
            <#if course_category_list??>
                <#list course_category_list as list>
                    <#if list_index = 0>
	                    <dd>
	                        <span>${list.title!''}</span>
	                        <#if course_category_page0??>
	                            <#list course_category_page0.content as item>
	                                <#if item_index lt 3 >
		                                <p>${item.title!''}</p>
							            <a>详情</a>
							            <input type="button" value="立即报名" />
							        </#if>
							    </#list>
							</#if>            
	                    </dd>  
                    </#if>  
                    <#if list_index = 1>
                        <dd>
                            <span>${list.title!''}</span>
                            <#if course_category_page1??>
                                <#list course_category_page1.content as item>
                                    <#if item_index lt 3 >
                                        <p>${item.title!''}</p>
                                        <a>详情</a>
                                        <input type="button" value="立即报名" />
                                    </#if>
                                </#list>
                            </#if>            
                        </dd>  
                    </#if>  
                </#list>
            </#if>        
    </dl>
    <dl class="course_03">
        <dt><a>最新课程</a><p><a style=" background:none; font-size:12px; color:#999;" href="/info/list/12">更多&gt;&gt;</a></p></dt>
        <#if course_list??>
            <#list course_list as list>
                <#if list_index lt 10>
                    <dd><a>${list.title!''}</a></dd>
                </#if>
            </#list>
        </#if>        
    </dl>
    </div>

<!--school-->
<div class="school">
     <dl class="school_01">
         <#if about_page??>
             <#list about_page.content as item>
                 <#if item_index = 0>
			        <dt><a>${item.title!''}</a><p><a style=" background:none; font-size:12px; color:#999;" href="/info/entry/content/${item.id!''}?mid=10">更多&gt;&gt;</a></p></dt>
			        <dd>
			             <img src="${item.imgUrl!''}" />
                         <#if item.content?length lt 400>
                            ${item.content!'' }
                         <#else>
                            ${item.content[0..394]}...   
                         </#if> 
			        </dd> 
                </#if>
            </#list>
        </#if> 			          
    </dl>
    
    <dl class="school_02">
         <#if about_page??>
             <#list about_page.content as item>
                 <#if item_index = 1>
                     <dt><a>${item.title!''}</a><p><a style=" background:none; font-size:12px; color:#999;" href="/info/entry/content/${item.id!''}?mid=10">更多&gt;&gt;</a></p></dt>
                     <dd><a>
                         <#if item.content?length lt 95>
			                <p>${item.content!'' }</p>
			             <#else>
			                <p>${item.content[0..94]}...</p>     
			             </#if> 
			             </a>
			         </dd>
                </#if>
            </#list>
        </#if> 			   		               
    </dl>
</div>

<!--index_news-->
<div class="index_news">
    <div class="news_01">
        <dl class="news_box">            
            <dt><a href="/info/list/8" style=" background-image:url(/client/images/index_news.png);color:white;">新闻中心</a><a href="/info/list/8">新闻中心</a></dt>
            <#if news_list??>
                <#list news_list as item>
                    <#if item_index lt 7>
                        <dd><a href="/info/list/content/${item.id!''}?mid=${item.menuId!''}" title="${item.brief!''}">${item.title!''}</a><p>${item.updateTime?string("yyyy-MM-dd")}</p></dd>
                    </#if>
                </#list>
            </#if>           
        </dl>
        <div class="news_more"><span></span><a href="/info/list/8">更多&gt;&gt;</a><span></span></div>
    </div>
    <div class="news_02">
        <dl class="news_box">
            <dt><a style=" background-image:url(/client/images/index_news.png);color:white;">招聘信息</a><a >交通指南</a></dt>
            <#if join_list??>
                <#list join_list as item>
                    <#if item_index lt 7>
                        <dd><a title="${item.brief!''}">${item.title!''}</a><p>${item.updateTime?string("yyyy-MM-dd") }</p></dd>
                    </#if>
                </#list>
            </#if>   
        </dl>
        <div class="news_more"><span></span><a href="/info/list/13">更多&gt;&gt;</a><span></span></div>
    </div>
</div>

<!--active-->
<div class="active">
    <div class="active_title"><a>学校风采</a><p>更多&gt;&gt;</p></div>
     <#if about_page??>
         <#list about_page.content as item>
             <#if item_index = 2>
                  <#if item.showPictures??>
                  <#list item.showPictures?split(",") as uri>
                      <#if ""!=uri && uri_index < 4>
                          <dl class="active_box">
                             <dt><img src="${uri!'' }" /></dt> 
                          </dl> 
                      </#if>
                  </#list>
                 </#if>  
             </#if>
         </#list>
    </#if>       
</div>

<!--index_tea-->
<div class="index_teacher">
    <div class="teacher">
        <div class="teacher_title"><a>教师简介</a><p>更多&gt;&gt;</p></div>
        <#if teacher_page??>
            <#list teacher_page.content as item>
                <#if item_index lt 4>
			        <dl class="teacher_box">
			            <dt><img src="${item.imgUrl!''}" /></dt> 
			            <dd><a>${item.title!''}</a></dd>
			        </dl> 
                    </#if>
                </#list>
            </#if> 		
    </div>
    
     <dl class="school_03">
        <dt><a>报考指南</a><p>更多&gt;&gt;</p></dt>
        <dd><a>国家职业资格证</a></dd>
        <dd><a>国家职业资格证</a></dd>
        <dd><a>国家职业资格证</a></dd>
        <dd><a>国家职业资格证</a></dd>
        <dd><a>国家职业资格证</a></dd>
        <dd><a>国家职业资格证</a></dd>
        <dd><a>国家职业资格证</a></dd>
        <dd><a>国家职业资格证</a></dd>
        <dd><a>国家职业资格证</a></dd>
        <dd><a>国家职业资格证</a></dd>
    </dl>
</div>

<!--last-->
<div class="last">
    <ul class="last_nav">
        <li style="background-color:#008e45;"><a  href="#" title="">首页</a></li>
        <li><a  href="#" title="">课程标题</a></li>
        <li><a  href="#" title="">课程标题</a></li>
        <li><a  href="#" title="">课程标题</a></li>  
        <li><a  href="#" title="">课程标题</a></li>
        <li><a  href="#" title="">课程标题</a></li>
        <li><a  href="#" title="">课程标题</a></li>
        <li><a  href="#" title="">课程标题</a></li>
    </ul>

     <dl class="school_04">
        <dt><a>招生简章</a><p>更多&gt;&gt;</p></dt>
        <dd><a>国家职业资格证</a></dd>
        <dd><a>国家职业资格证</a></dd>
        <dd><a>国家职业资格证</a></dd>
        <dd><a>国家职业资格证</a></dd>
        <dd><a>国家职业资格证</a></dd>
        <dd><a>国家职业资格证</a></dd>
        <dd><a>国家职业资格证</a></dd>
        <dd><a>国家职业资格证</a></dd>
        <dd><a>国家职业资格证</a></dd>
        <dd><a>国家职业资格证</a></dd>
    </dl>
     <dl class="school_04">
        <dt><a>招生简章</a><p>更多&gt;&gt;</p></dt>
        <dd><a>国家职业资格证</a></dd>
        <dd><a>国家职业资格证</a></dd>
        <dd><a>国家职业资格证</a></dd>
        <dd><a>国家职业资格证</a></dd>
        <dd><a>国家职业资格证</a></dd>
        <dd><a>国家职业资格证</a></dd>
        <dd><a>国家职业资格证</a></dd>
        <dd><a>国家职业资格证</a></dd>
        <dd><a>国家职业资格证</a></dd>
        <dd><a>国家职业资格证</a></dd>
    </dl>

 <dl class="school_04">
        <dt><a>招生简章</a><p>更多&gt;&gt;</p></dt>
        <dd><a>国家职业资格证</a></dd>
        <dd><a>国家职业资格证</a></dd>
        <dd><a>国家职业资格证</a></dd>
        <dd><a>国家职业资格证</a></dd>
        <dd><a>国家职业资格证</a></dd>
        <dd><a>国家职业资格证</a></dd>
        <dd><a>国家职业资格证</a></dd>
        <dd><a>国家职业资格证</a></dd>
        <dd><a>国家职业资格证</a></dd>
        <dd><a>国家职业资格证</a></dd>
    </dl>

 <dl class="school_04">
        <dt><a>资料下载</a><p>更多&gt;&gt;</p></dt>
        <dd><a>国家职业资格证</a></dd>
        <dd><a>国家职业资格证</a></dd>
        <dd><a>国家职业资格证</a></dd>
        <dd><a>国家职业资格证</a></dd>
        <dd><a>国家职业资格证</a></dd>
        <dd><a>国家职业资格证</a></dd>
        <dd><a>国家职业资格证</a></dd>
        <dd><a>国家职业资格证</a></dd>
        <dd><a>国家职业资格证</a></dd>
        <dd><a>国家职业资格证</a></dd>
    </dl>
</div>

</div>

<div class="adv">
    <dl class="adv_01">
        <dt><img src="/client/images/adv_icon.png" /></dt>
        <dd><a>QQ在线咨询</a><img src="/client/images/about_qq_27.png" /></dd>
    </dl>
    <div class="adv_02">
        <a>客服在线咨询</a>
        <p>123-123-1532</p>
        <input type="button" value="立即报名" />
    </div>
    <dl class="adv_03">
        <dt><a>二维码</a><p>扫码加入</p></dt>
        <dd><img src="/client/images/index_mark.png"/></dd>
    </dl>
</div>

<!--footer-->
<#include "/client/common_footer.ftl" />
<!--footer_end-->

</body>
</html>
