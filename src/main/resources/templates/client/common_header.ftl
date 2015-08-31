<div class="header">
    <img class="logo" src="${site.logoUri!'' }"/>
    <div class="search">
        <form action="/search" method="get">
	        <input id="search-text" type="text" class="search_text" name="keywords" value="${keywords!keywords_list[0].title}"  onfocus="this.value='';" onblur="if(this.value==''){this.value='${keywords!keywords_list[0].title}'}"/>
	        <input type="submit"  style="width:51px; height:41px; background-color:#008e45; color:white; font-size:14px;" value="搜索"
	               />
        </form>
    </div>
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
                <a  href="${bar.linkUri}" title=""><li>${bar.title!''}</li></a>
            </#list>
        </#if>
    </ul>
    </div>
</div>
<div class="adv">
    <dl class="adv_01">
        <dt><a href="http://wpa.qq.com/msgrd?v=3&uin=${site.qq1!'' }&site=qq&menu=yes" title="${site.qq1!'' }"><img src="/client/images/adv_icon.png" /></a></dt>
        <dd><a href="http://wpa.qq.com/msgrd?v=3&uin=${site.qq1!'' }&site=qq&menu=yes" title="${site.qq1!'' }">QQ在线咨询</a><img src="/client/images/about_qq_27.png" /></dd>
    </dl>
    <div class="adv_02">
        <p>客服在线咨询</p>
        <p>${site.telephone!''}</p>
        <a href="/info/list/12" style="width: 66px;
									  height: 26px;
									  text-align: center;
									  line-height: 26px;
									  background: url(/client/images/adv_line.png);
									  font-size: 14px;
									  color: white;
									  margin-top: 20px;
									  margin-left: 68px;
									  border: none;">立即报名</a>
    </div>
    <dl class="adv_03">
        <dt><p>二维码</p><p>扫码加入</p></dt>
        <dd><img src="${site.wxQrCode!''}"/></dd>
    </dl>
</div>
