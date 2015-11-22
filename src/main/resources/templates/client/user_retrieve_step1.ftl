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
	<style>
	
	</style>

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

<div class="lookpswbody">
<div class="lookpswbodytitlebg"></div>
	<div class="lookpswbodytitle lookpswbodytitle-one">确认账号</div>
	<div class="lookpswbodytitle lookpswbodytitle-two">安全验证</div>
	<div class="lookpswbodytitle lookpswbodytitle-three">重置密码</div>
	<div class="lookpswbodytitle lookpswbodytitle-four">修改成功</div>
</div>
<form action="/login/retrieve_step" class="lookpsw-form" method="post" id="form1">
	<input type="text" id="mobile" value="请输入手机号"  name="mobile" ajaxurl="/login/check/mobile" onfocus="if(this.value=='请输入手机号'){this.value='';}" onblur="if(this.value==''){this.value='请输入手机号'}" datatype="m|/^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/"><span class="Validform_checktip" style="color:red;line-height:14px; font-size:16px;"></span>
	<div class="verification-body">
	   <input type="text" datatype="*" id="code" class="verification" ajaxurl="/login/check/code">
	   <!--<span  style="display:block;"class="Validform_checktip"></span>-->
	   <div class="verificationimg">
	   <img src="/verify" width="122px" height=46px alt="验证码" onclick="this.src = '/verify?date='+Math.random();" id="yzm" />
	   </div>
	   <span style="color:red; position:absolute; right:-114px;top:14px;line-height:14px; font-size:16px;"  class="Validform_checktip" ></span>
	 </div>
	<input type="submit" class="lookpsw-submit" >
</form>
	


</body>
</html>