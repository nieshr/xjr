<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>联系我们</title>
	<link rel="stylesheet" href="/client/css/base.css">
	<link rel="stylesheet" href="/client/css/main.css">
	<script src="/client/js/main.js"></script>
</head>
<body>
<!-- head  -->
<#include "/client/news_common_header.ftl" />
<!-- head end -->
<!-- contend -->
<div class="titlelist">
	<div id="titlelist">
	<div>
		<a href="javascript:show1();" class="active">联系我们</a>
		<a href="javascript:show2();">在线留言</a>
</div>
	</div>
<div class="contactustitle">
		<span>联系我们</span>
		<hr style="border-top:1px solid #DDDDDD;" />
</div>
	<div class="contactussmall">
		<div class="contactus">
		  <#if site??>
			<h1>${site.title!''}</h1>
			<h3 class="contactustel">电话：${site.telephone!''}</h3>
			<h3 class="contactusaddress">地址：${site.address!''}</h3>
			<h3 class="contactusqq">QQ：987654321</h3>
			<h3 class="contactusemail">邮箱：987654321@qq.com</h3>
			<h3 class="contactusleft">线路：公交124路、998路、452路、124路</h3>
			<h3 class="contactusleft2">轻轨3号线在黄泥磅轻轨站下。</h3>
			<h3 class="contactuswechat">扫描添加微信：</h3>
			<img src="img/qrcode2.png" alt="">
		</div>
		<div class="map">
			<iframe src="map.html" frameborder="0"></iframe>
		</div>
	</div>
	<div class="contactustitle">
		<span>在线留言</span>
		<hr style="border-top:1px solid #DDDDDD;" />
	</div>
	<form action="">
	<div class="massage">
		<span>标题：</span>
		<input type="text" class="search">
		<br>
		<span>内容:</span>
		<textarea name="" id="" cols="30" rows="10"></textarea>
		<br>
		<input type="submit" class="button" value="提交" />
	</div>
	</form>
	
</div>
<!-- contendend -->

<!-- 底部 -->
<div class="footlist">
<div class="footlist-title">
	<ul>
		<li class="footlist-title-title">友情链接：</li>
		<li><a href="#">市科委</a><span>|</span></li>
		<li><a href="#">市财政局</a><span>|</span></li>
		<li><a href="#">市经信委</a><span>|</span></li>
		<li><a href="#">市人社局</a><span>|</span></li>
		<li><a href="#">市工商局</a><span>|</span></li>
		<li><a href="#">市知识产权局</a><span>|</span></li>
		<li><a href="#">团市委</a><span>|</span></li>
		<li><a href="#">市妇联</a><span>|</span></li>
		<li><a href="#">市工商联</a></li>
	</ul>
</div>
<div class="footlist-body">
	<h5>联系电话：023-67727040</h5>
	<h5>传真：023-67874803</h5>
	<h5>地址：重庆市渝北区新溉大道2号生产力大厦2楼</h5>
	<h5>Copyright 2014-2015 Peking University Science Park All rights reserved design by cqtiandu.com 渝ICP备15009454号   </h5> 
	<div class="footercode"> 
	<img src="img/qrcode.png">
	<p>微信公众号</p>
	</div>
</div>         
</div>
<!-- 底部end -->

</body>
</html>