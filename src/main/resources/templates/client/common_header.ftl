<div class="header">
    <img class="logo" src="${site.logoUri!'' }"/>
    <form>
    <div class="search">
        <form action="/search" method="get">
	        <input type="text" class="search_text" name="keywords" value="${keywords!keywords_list[0].title}" />
	        <input type="submit"  style="width:51px; height:41px; background-color:#008e45; color:white; font-size:14px;" value="搜索" />
        </form>
    </div>
    </form>
    <div class="share">
        <div class="share_icon">
			<!-- JiaThis Button BEGIN -->
			<div class="jiathis_style"><span class="jiathis_txt"></span>
			<a class="jiathis_button_qzone"></a>
			<a class="jiathis_button_tsina"></a>
			<a class="jiathis_button_tqq"></a>
			<a class="jiathis_button_renren"></a>
			<a class="jiathis_button_kaixin001"></a>
			<a>&nbsp;</a>
			<a href="http://www.jiathis.com/share" class="jiathis jiathis_txt jiathis_separator jtico jtico_jiathis" target="_blank"></a>
			<a class="jiathis_counter_style"></a>
			</div>
			<script type="text/javascript" >
			var jiathis_config={
			    url:"http://www.cqlxjy.cn",
			    summary:"重庆教育培训高素质学校",
			    title:"好教育，到立学！立学教育培训学校 ##",
			    shortUrl:false,
			    hideMore:false
			}
			</script>
			<script type="text/javascript" src="http://v3.jiathis.com/code/jia.js" charset="utf-8"></script>
			<!-- JiaThis Button END -->	
        </div>
        <p>好教育，到立学，一键分享！</p>
        <p>联系电话：${site.telephone!'' }</p>
    </div>
    <div class="mark">
        <img src="${site.wxQrCode!''}" />
        <p>二维码，扫一扫</p>
    </div>
</div>
<!--nav-->
<div class="main_nav">
    <div class="right_nav">
    <ul class="nav">
        <#if navi_item_list??>
            <#list navi_item_list as bar>
                <li><a  href="${bar.linkUri}" title="">${bar.title!''}</a></li>
            </#list>
        </#if>
    </ul>
    </div>
</div>
