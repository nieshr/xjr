<!-- 导航 -->
	<div class="wrapper">
		<ul class="wrapper-nav" id="wrapper-nav">
			<a href="/"><li>首页</li></a>
			<a href="#"><li>专项行动</li></a>
			<a href="#"><li>培育活动</li></a>
			<a href="/info/index"><li>新闻动态</li></a>
			<a href="/info/projectshow"><li>企业项目</li></a>
			<a href="/info/resource"><li  class="active">专家资源</li></a>
			<a href="#"><li>合作机构</li></a>
			<a href="/info/contact"><li>联系方式</li></a>
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
