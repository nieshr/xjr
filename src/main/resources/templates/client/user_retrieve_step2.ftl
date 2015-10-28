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

<div class="lookpswbodytwo">
<div class="lookpswbodytitlebg"></div>
	<div class="lookpswbodytitle lookpswbodytitle-one-normal">确认账号</div>
	<div class="lookpswbodytitle lookpswbodytitle-two-active">安全验证</div>
	<div class="lookpswbodytitle lookpswbodytitle-three">重置密码</div>
	<div class="lookpswbodytitle lookpswbodytitle-four">修改成功</div>
</div>
<p class="tishixinxi">已发送验证码到该手机，收到之前请勿关闭此页面</p>
<form action="/login/retrieve_step2" class="lookpsw-form" id="form1" method="post">
	<input type="hidden" value="${mobile!''}" name="mobile" id="mobile"/>
	<input type="text" placeholder="请输入您收到的验证码" name="smsCode" datatype="*">
	<span class="Validform_checktip Validform_wrong">${msg!''}</span>
	<input type="submit" class="lookpsw-submit" >
	<h5>如果在5分钟后仍未收到短信，可以试试<a href="/reg/smscode?mobile=${mobile!''}">重新发送一遍</a>返回首页</h5>
</form>
	


</body>
</html>