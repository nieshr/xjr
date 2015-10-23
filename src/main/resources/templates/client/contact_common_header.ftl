<!-- 导航 -->
	<div class="wrapper">
		<ul class="wrapper-nav" id="wrapper-nav">
			<a href="/"><li>首页</li></a>
			<a href="#"><li>专项行动</li></a>
			<a href="#"><li>培育活动</li></a>
			<a href="/info/index"><li>新闻动态</li></a>
			<a href="/info/projectshow"><li>企业项目</li></a>
			<a href="/info/resource"><li>专家资源</li></a>
			<a href="#"><li>合作机构</li></a>
			<a href="/info/contact"><li class="active">联系方式</li></a>
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
  <!--右侧按钮-->
    <div class="swiper-pagination"></div>
    <!--右侧浮动导航开始-->
    <script>
function gotop()
{
    $('html,body').animate({scrollTop:0},400);
}
</script>
    <div class="floatbox">
      <a id="BizQQWPA" href="http://wpa.qq.com/msgrd?v=3&uin=${site.qq1!''}&site=qq&menu=yes" title="申报入口">
        <img src="/client/l_images/float_ico02.png" width="42" height="42" alt="申报入口">
      </a>
      <a href="http://weibo.com/ynsite" title="搜索" target="_blank" rel="nofollow">
        <img src="/client/l_images/float_ico01.png" width="42" height="42" alt="搜索">
      </a>
      <a id="BizQQWPA" href="http://wpa.qq.com/msgrd?v=3&uin=1981148933&site=qq&menu=yes" target="_blank" title="在线客服">
        <img src="/client/l_images/float_ico06.png" width="42" height="42" alt="在线客服">
      </a>
      <a href="javascript:loginWinOpen('weixin_win','myselfbox',200);" title="官方微信">
        <img src="/client/l_images/float_ico03.png" width="42" height="42" alt="官方微信">
        <span class="qr"><img src="/client/l_images/QR_code.png"></span>
      </a>
      
      <a title="客服电话">
        <img src="/client/l_images/float_ico04.png" width="42" height="42" alt="客服电话">
        <span class="phone_num">023-8888 8888</span>
      </a>
      <a href="javascript:gotop();" title="到顶部">
        <img src="/client/l_images/float_ico05.png" width="42" height="42" alt="到顶部">
      </a>
    </div>
    <!--右侧浮动导航结束-->
<!-- head end -->
