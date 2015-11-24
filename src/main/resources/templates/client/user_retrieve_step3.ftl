<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>找回密码</title>
	<link rel="shortcut icon" href="/client/images/icon.ico" />
    <link rel="stylesheet" href="/client/css/p-base.css" type="text/css" />
    <link rel="stylesheet" href="/client/css/p-main.css" type="text/css" />
    <script src="/client/js/jquery-1.9.1.min.js"></script>
    <script src="/client/js/Validform_v5.3.2_min.js"></script>
    <script src="/client/js/main.js"></script>

<script>
$(document).ready(function(){
   //初始化表单验证
    $("#form1").Validform({
        tiptype: 3
    });
});
</script>
</head>
<body>
<div class="lookpsw">
    <img src="/client/img/lookpswbg.png">
    <div class="lookpsw-badge">
        <img src="/client/img/badge.png">
        <p>科技小巨人&nbsp&nbsp&nbsp开创新未来</p>
    </div>
    <h4 class="zhaohuimima1">找回密码</h4>
    <div class="sanjiaoxing1"><img src="/client/img/triangle.png" alt="" class="triangle"></div>
</div>

<div class="lookpswbodythree">
<div class="lookpswbodytitlebg"></div>
	<div class="lookpswbodytitle lookpswbodytitle-one-normal">确认账号</div>
	<div class="lookpswbodytitle lookpswbodytitle-two">安全验证</div>
	<div class="lookpswbodytitle lookpswbodytitle-three-active">重置密码</div>
	<div class="lookpswbodytitle lookpswbodytitle-four">修改成功</div>
</div>
<p class="tishixinxi">验证成功！请重新设置密码。</p>
<form action="/login/retrieve_step3" class="lookpsw-form" id="form1" method="post">
    <input type="hidden" value="${mobile!''}">
	<div>
	<input placeholder="请输入新密码" name="password" type="password" datatype="s6-20">
	<span class="Validform_checktip Validform_wrong"></span>
	 </div>
	 <div>
	<input placeholder="再次输入密码"  type="password" datatype="*" recheck="password">
	<span class="Validform_checktip Validform_wrong"></span>
	</div>
	<input type="submit" class="lookpsw-submit">
	<h5>*为了您的账号安全，强烈建议您不要使用旧密码  <a href="/">返回首页</a></h5>
</form>
<div class="modification3">
	<div class="modification2">
		<p>您验证的账号信息为：</p>
		<h5>用户名：${user.username!''}</h5>
		<h5>邮&nbsp&nbsp&nbsp箱：${user.email!''}</h5>
		<h5>手机号：${user.mobile!''}</h5>
	</div>
</div>

</body>
</html>