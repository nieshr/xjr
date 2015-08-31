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
<script src="/client/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/client/js/pageSwitch.js"></script>

</head>

<body>
<!--header-->
<#include "/client/common_header.ftl" />
<!--header_end -->

<!--banner-->
<!--效果html开始-->
<div id="wrap">
    <div id="imgs" style="width:${adtype.width}px;height:${adtype.height}px;">
       <#if big_scroll_ad_list??>
            <#list big_scroll_ad_list as item>
                <#if item_index lt 9>
                    <div><a href="${item.linkUri!''}" <#if item.typeIsNewWindow?? && item.typeIsNewWindow>target="_blank"</#if>><img src="${item.fileUri!''}" /></a></div>
                </#if>
            </#list>
        </#if>     
    </div>
    <#--<div id="navs">
    <a href="javascript:;" class="active"></a>
    <a href="javascript:;"></a>
    <a href="javascript:;"></a>
    <a href="javascript:;"></a>
    <a href="javascript:;"></a>
    <a href="javascript:;"></a>
    <a href="javascript:;"></a>
    <a href="javascript:;"></a>
    <a href="javascript:;"></a>
    </div>-->
    <input type="hidden" id="tssel" value="${adtype.mark!''}"/>
   
</div>
<script type="text/javascript" src="/client/js/pageSwitch.js"></script>
<script>
var reg=location.search.match(/ts=([^&]*)/),
    ts=reg&&reg[1]||'${adtype.effect!''}',
    a=new pageSwitch('imgs',{
    duration:1000,
    start:0,
    direction:1,
    loop:true,
    ease:/flip(?!Paper)/.test(ts)?'bounce':'ease',
    transition:ts,
    mouse:true,
    mousewheel:false,
    autoplay:true,         //bool 是否自动播放幻灯 新增
    interval:3000,    
    arrowkey:true
}),
navs=document.getElementById('navs').getElementsByTagName('a');

a.on('before',function(m,n){
    navs[m].className='';
    navs[n].className='active';
});

if(/^(?:scroll3d|flip)/.test(ts)){
    document.getElementById('imgs').className='visible';
}

document.getElementById('tssel').onchange=function(){
    location.href='?ts='+this.value;
}


var options=document.getElementById('tssel').options,
    i=0,op;
while(op=options[i++]){
    if(op.value===ts){
        op.selected=true;
        break;
    }
}

i=0;
for(;i<navs.length;i++){
    !function(i){
        navs[i].onclick=function(){
            a.slide(i);
        }
    }(i);
}
</script>
<!--效果html结束-->
    <#-- 原版
    <img src="/client/img/20150207064229425.jpg" />
    <ul class="banner_btn">
        <li><a></a></li>
        <li><a></a></li>
        <li><a></a></li>
        <li><a></a></li>
    </ul>
    -->
</div>

<!--content-->
<div class="content">
<!--best-->
    <div class="best">
        <div class="best_title"><a>金牌课程</a><p><a style=" background:none; font-size:12px; color:#999;" href="/info/list/12">更多&gt;&gt;</a></p></div>
        <#if course_page1??>
            <#list course_page1.content as item>
                <#if item_index lt 4>
			        <dl class="best_box">
			            <dt><img src="${item.imgUrl!''}" /></dt>
			            <dd>
			                <span class="best_text">
			                    <span>${item.title!''}</span>
			                    <p>${item.brief!''}</p>
			                </span>
                              <a href="/info/coursechoose/content/${item.id}?mid=12" style="  float: right;
                                                                            height: 30px;
                                                                            width: 64px;
                                                                            background: url(/client/images/index_btn_line.png);
                                                                            border: none;
                                                                            line-height: 30px;
                                                                            text-align:center;
                                                                            margin-right:7px;
                                                                            color: white;"
                                                                            title="${item.title!''}">立即报名</a>
			            </dd>
			        </dl>
			    </#if>    
		    </#list>
		</#if>        
    </div>
<!--crouse-->
    <div class="course_box">
    <dl class="course_01">
        <dt><a>热门课程</a><p><a style=" background:none; font-size:12px; color:#999;" href="/info/list/12">更多&gt;&gt;</a></p></dt>
        <#if course_page2??>
	        <#list course_page2.content as item>
	            <#if item_index lt 10>
	                <dd><a href="/info/coursechoose/content/${item.id}?mid=12">${item.title!''}</a></dd>
	            </#if>    
	        </#list>
        </#if>     
    </dl>
    <dl class="course_02">
        <dt><a>开展课程</a><p><a style=" background:none; font-size:12px; color:#999;" href="/info/list/12">更多&gt;&gt;</a></p></dt>        
            <#if course_category_list??>
                <#list course_category_list as list>
                    <#if list_index = 0>
	                    <dd>
	                        <span>${list.title!''}</span>
	                        <#if course_category_page0??>
	                            <#list course_category_page0.content as item>
	                                <#if item_index lt 3 >
		                                <p>${item.title!''}</p>
							            <a href="/info/coursechoose/content/${item.id}?mid=12">详情</a>
							            <a href="/info/coursechoose/content/${item.id}?mid=12" style="  float: right;
																									  height: 30px;
																									  width: 64px;
																									  background: url(/client/images/index_btn_line.png);
																									  border: none;
																									  line-height: 30px;
																									  text-align:center;
																									  color: white;">立即报名</a>
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
                                        <a href="/info/coursechoose/content/${item.id}?mid=12">详情</a>
                                                                                <a href="/info/coursechoose/content/${item.id}?mid=12" style="  float: right;
                                                                                                      height: 30px;
                                                                                                      width: 64px;
                                                                                                      background: url(/client/images/index_btn_line.png);
                                                                                                      border: none;
                                                                                                      line-height: 30px;
                                                                                                      text-align:center;
                                                                                                      color: white;">立即报名</a>
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
                    <dd><a href="/info/coursechoose/content/${list.id}?mid=12">${list.title!''}</a></dd>
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
                     <dd>
                         <#if item.content?length lt 195>
			                <p>${item.content!'' }</p>
			             <#else>
			                <p>${item.content[0..194]}...</p>     
			             </#if> 
			            
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
            <dt><a style=" background-image:url(/client/images/index_news.png);color:white;">招聘信息</a><a href="/info/list/13?catId=49">交通指南</a></dt>
            <#if join_list??>
                <#list join_list as item>
                    <#if item_index lt 7>
                        <dd><a href="/info/list/13?catId=48" title="${item.brief!''}">${item.title!''}</a><p>${item.updateTime?string("yyyy-MM-dd") }</p></dd>
                    </#if>
                </#list>
            </#if>   
        </dl>
        <div class="news_more"><span></span><a href="/info/list/13">更多&gt;&gt;</a><span></span></div>
    </div>
</div>

<!--active-->
<div class="active">
      <#if about_page??>
         <#list about_page.content as item>
             <#if item_index = 2>
             <div class="active_title"><a>学校风采</a><p><a style=" background:none; font-size:12px; color:#999;" href="/info/entry/content/${item.id!''}?mid=10">更多&gt;&gt;</a></p></div>
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
        <div class="teacher_title"><a>教师简介</a><p><a style=" background:none; font-size:12px; color:#999;" href="/info/list/11">更多&gt;&gt;</a></p></div>
        <#if teacher_page??>
            <#list teacher_page.content as item>
                <#if item_index lt 4>
			        <dl class="teacher_box">
			            <dt><a href="/info/list/content/${item.id}?mid=11"><img src="${item.imgUrl!''}" /></a></dt> 
			            <dd><a href="/info/list/content/${item.id}?mid=11">${item.title!''}</a></dd>
			        </dl> 
                    </#if>
                </#list>
            </#if> 		
    </div>
    
     <dl class="school_03">
        <dt><a>资格培训</a><p><a style=" background:none; font-size:12px; color:#999;" href="/info/list/12">更多&gt;&gt;</a></p></dt>
           <#if course_category_page2??>
                <#list course_category_page2.content as item>
                    <#if item_index lt 9 >
                        <dd><a href="/info/coursechoose/content/${item.id}?mid=12">${item.title!''}</a></dd>
                    </#if>
                </#list>
            </#if>   
    </dl>
</div>

<!--last-->
<div class="last">
    <ul class="last_nav">
        <li style="background-color:#008e45;"><a  href="#" title="">首页</a></li>
            <#if course_category_list??>
                <#list course_category_list as item>
                    <#if item_index lt 7 >        
                        <li><a  href="/info/list/12?catId=${item.id!''}" title="">${item.title!''}</a></li>
                    </#if>
                </#list>
            </#if>       
    </ul>

     <dl class="school_04">
        <dt><#if course_category_list??>
                 <#list course_category_list as item>
                     <#if item_index = 0>
                         <a>${item.title!''}</a>
                     </#if>
                 </#list>
             </#if>             
            <p><a style=" background:none; font-size:12px; color:#999;" href="/info/list/12">更多&gt;&gt;</a></p></dt>
            <#if course_category_page0??>
                <#list course_category_page0.content as item>
                    <#if item_index lt 9 >
                        <dd><a href="/info/coursechoose/content/${item.id}?mid=12">${item.title!''}</a></dd>
                    </#if>
                </#list>
            </#if>           
    </dl>
     <dl class="school_04">
        <dt><#if course_category_list??>
                 <#list course_category_list as item>
                     <#if item_index = 1>
                         <a>${item.title!''}</a>
                     </#if>
                 </#list>
             </#if>     
            <p><a style=" background:none; font-size:12px; color:#999;" href="/info/list/12">更多&gt;&gt;</a></p></dt>
            <#if course_category_page1??>
                <#list course_category_page1.content as item>
                    <#if item_index lt 9 >
                        <dd><a href="/info/coursechoose/content/${item.id}?mid=12">${item.title!''}</a></dd>
                    </#if>
                </#list>
            </#if>       
    </dl>

 <dl class="school_04">
        <dt><#if course_category_list??>
                 <#list course_category_list as item>
                     <#if item_index = 2>
                         <a>${item.title!''}</a>
                     </#if>
                 </#list>
             </#if>
            <p><a style=" background:none; font-size:12px; color:#999;" href="/info/list/12">更多&gt;&gt;</a></p></dt>
            <#if course_category_page2??>
                <#list course_category_page2.content as item>
                    <#if item_index lt 9 >
                        <dd><a href="/info/coursechoose/content/${item.id}?mid=12">${item.title!''}</a></dd>
                    </#if>
                </#list>
            </#if>       
    </dl>

 <dl class="school_04">
        <dt><a>资料下载</a><p>更多&gt;&gt;</p></dt>
        <#if download_list??>
            <#list download_list as item>
                <#if item_index lt 9>        
                    <dd><a href="/download/data?name=${item.imgUrl!''}" title="${brief!''}">${item.title!''}</a>
                        <a style="  float: right;
									  height: 30px;
									  width: 64px;
									  background: url(/client/images/index_btn_line.png);
									  border: none;
									  line-height: 30px;
									  color: white;">立即下载</a>
                    </dd>
                </#if>
            </#list>
        </#if>       
    </dl>
</div>

</div>
<!--footer-->
<#include "/client/common_footer.ftl" />
<!--footer_end-->

</body>
</html>
