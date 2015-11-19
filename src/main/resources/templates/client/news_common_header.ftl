<!-- 导航 -->
	<div class="wrapper">
		<ul class="wrapper-nav" id="wrapper-nav">
			<li><a href="/">首页</a></li>
			<li <#if active??&&active==1>class="active"</#if>><a href="/info/aIn">专项行动</a></li>
			<li <#if active??&&active==2>class="active"</#if>><a href="/info/activity/list">培育活动</a></li>
			<li <#if active??&&active==3>class="active"</#if>><a href="/info/index">新闻动态</a></li>
			<li <#if active??&&active==4>class="active"</#if>><a href="/info/projectshow">企业项目</a></li>
			<li <#if active??&&active==5>class="active"</#if>><a href="/info/resource">专家资源</a></li>
			<li <#if active??&&active==6>class="active"</#if>><a href="/info/host">合作机构</a></li>
			<li <#if active??&&active==7>class="active"</#if>><a href="/info/contact">联系方式</a></li>
		</ul>
                <#if username??>
                    <div class="logoin"><a href="/user" style="color:#ff4040;">${username}</a><span>|</span><a href="/logout">退出</a></div>
                <#else>
                    <div class="logoin"><a href="/login">登录</a><span>|</span><a href="/reg">注册</a></div>
                </#if>
	</div>
<!-- 导航end -->
<!-- banner -->
<div class="listbanner">
	<img src="/client/news_img/bglist.png"/>
	<div><span>关注成长，创领未来</span></div>
</div>
<!-- bannerend -->
    <!--右侧浮动导航开始-->
    <script>
function gotop()
{
    $('html,body').animate({scrollTop:0},400);
}
</script>
    <div class="floatbox">
      <a  href="/user" target="_blank" title="申报入口">
        <img src="/client/l_images/float_ico02.png" width="42" height="42" alt="申报入口">
      </a>
      <a href="/info/search" title="搜索" target="_blank" rel="nofollow">
        <img src="/client/l_images/float_ico01.png" width="42" height="42" alt="搜索">
      </a>
      <a id="BizQQWPA" href="http://wpa.qq.com/msgrd?v=3&uin=<#if site??>${site.qq1}</#if>&site=qq&menu=yes" target="_blank" title="在线客服">
        <img src="/client/l_images/float_ico06.png" width="42" height="42" alt="在线客服">
      </a>
      <a href="javascript:loginWinOpen('weixin_win','myselfbox',200);" title="官方微信">
        <img src="/client/l_images/float_ico03.png" width="42" height="42" alt="官方微信">
        <span class="qr"><img src="<#if site??>${site.wxQrCode!''}</#if>"></span>
      </a>
      
      <a title="客服电话">
        <img src="/client/l_images/float_ico04.png" width="42" height="42" alt="客服电话">
        <span class="phone_num" ><#if site??>${site.telephone!''}</#if></span>
      </a>
      <a href="javascript:gotop();" title="到顶部">
        <img src="/client/l_images/float_ico05.png" width="42" height="42" alt="到顶部">
      </a>
    </div>
    <!--右侧浮动导航结束-->
<!-- head end -->