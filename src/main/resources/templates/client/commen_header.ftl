<script type="text/javascript">           
$(document).ready(function(){

     $(".suspend").mouseover(function() {
        $(this).stop();
        $(this).animate({width: 130}, 400);
    })
    
    $(".suspend").mouseout(function() {
        $(this).stop();
        $(this).animate({width: 40}, 400);
    });
    
});
function move()
{
    $('html,body').animate({scrollTop:0},400);
}
</script>
<!--QQ在线咨询-->
<div class="suspend">
    <dl>
        <dt class="IE6PNG"></dt>
        <dd class="suspendQQ"><a href="http://wpa.qq.com/msgrd?v=3&uin=${site.qq1!'' }&site=qq&menu=yes"></a></dd>
        <dd class="suspendTel"><a href="javascript:move();"></a></dd>
    </dl>
</div>
<!--头部 -->
<div class="wrapper header">
    <div class="header_1"><img src="/client/images/logo.png"/></div>
    <div class="header_2"><img src="/client/images/icon_1.png" /><span>${site.telephone!'' }</span></div>
</div>
<!--导航-->
<div class="navbox">
    <div class="nav">
        <li class="drop-menu-effect">
            <#if navi_item_list??>
                <#list navi_item_list as bar>
                    <#if bar_index = 0>
                    <a href="${bar.linkUri}">
                        <span>
                            ${bar.title}                                                                           
                        </span>
                    </a>
                    </#if>
                </#list>
            </#if> 
        </li>
        <li class="drop-menu-effect"> 
            <#if navi_item_list??>
                <#list navi_item_list as bar>
                    <#if bar_index = 1>
                    <a href="${bar.linkUri}">
                        <span>
                            ${bar.title}                                                                           
                        </span>
                    </a>
			        <div class="submenu">
			            <div class="mj_menu_news_bg">
			                <div class="mj_menu_news_main">
			                    <div class="mj_menu_news_li">
			                        <div class="mj_menu_li_txt">
			                        <#if profile_page??>
		                                <#list profile_page.content as item>
			                                <a href="/info/service/content/${item.id}?mid=10">${item.title!'' }</a><br />
			                            </#list>
			                        </#if>    	               
			                        </div>
			                    </div>
			                    <div class="mj_menu_news_img">
			                        <img src="${bar.iconUri!'' }" /><br />
			                        <font> ${bar.title} </font>
			                    </div>
			                    <div style="clear:both; height:0px; overflow:hidden;"></div>
			                </div>
			            </div>
			        </div>
	                </#if>
                </#list>
            </#if> 
        </li>
      
        <li class="drop-menu-effect">
            <#if navi_item_list??>
                <#list navi_item_list as bar>
                    <#if bar_index = 2>
                    <a href="${bar.linkUri}">
                        <span>
                            ${bar.title}                                                                           
                        </span>
                    </a>
		            <div class="submenu">
		                <div class="mj_menu_news_bg">
		                    <div class="mj_menu_news_main">
		                        <div class="mj_menu_news_li">
		                            <div class="mj_menu_li_txt">
		                            <#if service_page??>
		                                <#list service_page.content as item>
		                                    <a href="/info/service/content/${item.id}?mid=10">${item.title!'' }</a><br />
		                                </#list>
		                            </#if>                     
		                            </div>
		                        </div>
		                        <div class="mj_menu_news_img">
		                            <img src="${bar.iconUri!'' }" /><br />
		                            <font> ${bar.title} </font>
		                        </div>
		                        <div style="clear:both; height:0px; overflow:hidden;"></div>
		                    </div>
		                </div>
		            </div>
                    </#if>
                </#list>
            </#if>             
        </li>
      
        <li class="drop-menu-effect"> 
            <#if navi_item_list??>
                <#list navi_item_list as bar>
                    <#if bar_index = 3>
                    <a href="${bar.linkUri}">
                        <span>
                            ${bar.title}                                                                           
                        </span>
                    </a>
		            <div class="submenu">
		                <div class="mj_menu_news_bg">
		                    <div class="mj_menu_news_main">
		                        <div class="mj_menu_news_li">
		                            <div class="mj_menu_li_txt">
		                            <#if news_list??>
		                                <#list news_list as item>
		                                    <a href="/info/list/8?catId=${item.id!''}">${item.title!'' }</a><br />
		                                </#list>
		                            </#if>                     
		                            </div>
		                        </div>
		                        <div class="mj_menu_news_img">
		                            <img src="${bar.iconUri!'' }" /><br />
		                            <font> ${bar.title} </font>
		                        </div>
		                        <div style="clear:both; height:0px; overflow:hidden;"></div>
		                    </div>
		                </div>
		            </div>
                    </#if>
                </#list>
            </#if> 		            
        </li>
      
        <li class="drop-menu-effect"> 
            <#if navi_item_list??>
                <#list navi_item_list as bar>
                    <#if bar_index = 4>
                    <a href="${bar.linkUri}">
                        <span>
                            ${bar.title}                                                                           
                        </span>
                    </a>
		            <div class="submenu" style="left:-347px;">
		                <div class="mj_menu_news_bg">
		                    <div class="mj_menu_news_main">
		                        <div class="mj_menu_news_li2">
		                            <div class="mj_menu_li_txt">
		                            <#if konwledge_list??>
		                                <#list konwledge_list as item>
		                                    <a href="/info/list/11?catId=${item.id!''}">${item.title!'' }</a><br />
		                                </#list>
		                            </#if>                     
		                            </div>
		                        </div>
		                        <div class="mj_menu_news_img2">
		                            <img src="${bar.iconUri!'' }" /><br />
		                            <font> ${bar.title} </font>
		                        </div>
		                        <div style="clear:both; height:0px; overflow:hidden;"></div>
		                    </div>
		                </div>
		            </div>
                    </#if>
                </#list>
            </#if> 		            
        </li>
      
        <li class="drop-menu-effect"> 
            <#if navi_item_list??>
                <#list navi_item_list as bar>
                    <#if bar_index = 5>
                    <a href="${bar.linkUri}">
                        <span>
                            ${bar.title}                                                                           
                        </span>
                    </a>> 
			        <div class="submenu" style="left:-347px;">
			            <div class="mj_menu_news_bg">
			                <div class="mj_menu_news_main">
			                    <div class="mj_menu_news_li2" style="width:99px;">
			                        <div class="mj_menu_li_txt">
			                        <#if site_link_list??>
			                            <#list site_link_list as link>
			                                <#if link_index lt 6>                    
			                                    <a href="${link.linkUri}" target="_blank" title="">${link.title}</a><br />
			                                </#if>
			                            </#list>
			                        </#if>
			                        </div>
			                    </div>
				                <div class="mj_menu_news_li2" style="width:99px;">
				                    <div class="mj_menu_li_txt">
					                <#if site_link_list??>
                                        <#list site_link_list as link>
                                            <#if link_index gt 5 && link_index lt 12>                    
                                                <a href="${link.linkUri}" target="_blank" title="">${link.title}</a><br />
                                            </#if>
                                        </#list>
                                    </#if>
                                    </div>
				                </div>
				                <div class="mj_menu_news_li2" style="width:99px;">
				                     <div class="mj_menu_li_txt">
                                     <#if site_link_list??>
                                        <#list site_link_list as link>
                                            <#if link_index gt 11 && link_index lt 18>                    
                                                <a href="${link.linkUri}" target="_blank" title="">${link.title}</a><br />
                                            </#if>
                                        </#list>
                                     </#if>
                                     </div>
                                </div>
			                    <div style="clear:both; height:0px; overflow:hidden;"></div>
			                </div>
			            </div>
			        </div>
                    </#if>
                </#list>
            </#if>			        
        </li>    
    </div>
</div>
<script>
$(function(){
    lanrenzhijia(".drop-menu-effect");
});
function lanrenzhijia(_this){
    $(_this).each(function(){
        var $this = $(this);
        var theMenu = $this.find(".submenu");
        var tarHeight = theMenu.height();
        theMenu.css({height:0});
        $this.hover(
            function(){
                $(this).addClass("mj_hover_menu");
                theMenu.stop().show().animate({height:tarHeight},400);
            },
            function(){
                $(this).removeClass("mj_hover_menu");
                theMenu.stop().animate({height:0},400,function(){
                    $(this).css({display:"none"});
                });
            }
        );
    });
}
</script>
<!--banner-->
<div class="wrapper banner"><img src="/client/images/2457331_120545314178_2.jpg" /></div>