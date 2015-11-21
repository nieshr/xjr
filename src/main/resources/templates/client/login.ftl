<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<!--网页左上角小图标-->
<link rel="shortcut icon" href="/client/images/icon.ico" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>科技小巨人-登录</title>
<!--css-->
<link href="/client/css/index_base.css" rel="stylesheet" type="text/css" />
<link href="/client/css/index_main.css" rel="stylesheet" type="text/css" />

<script src="/client/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/client/js/Validform_v5.3.2_min.js"></script>
<script type="text/javascript" src="/client/js/jquery.cookie.js"></script>

<script>
$(document).ready(function(){

	$("#login").Validform({
			tiptype:4,
	  	    ajaxPost:true,
            callback: function (data) { 
            if (data.role == 1){
                if (data.statusId == 1||data.statusId == 0 || data.statusId == 2 ||data.statusId ==3)
                {
                    window.location.href="/enterprise/check";
                }
                else
                {
                    window.location.href="/enterprise/info";
                }
            }    
            else if (data.role == 2){
                window.location.href="/region/enterprise/list";
            }
            else if (data.role == 3){
                window.location.href="/expert/enterprise/list";
            }
            else if (data.role == 4){
                window.location.href="/activity/list";
            }
            else 
            {
                alert(data.msg);
            }
        }
	});
	
     $(".whitebutton").mouseover(function(){   
        var index = $(this).index();
        $(".whitebutton").eq(index).addClass("sel");
        $(".whitebutton").eq(index).siblings().removeClass("sel");
     });
     $(".whitebutton").mouseout(function(){   
        $(".whitebutton").siblings().removeClass("sel");
        $("#present").addClass("sel");
   	 });
    
	//记住密码
	if ($.cookie("rmbUser") == "true") { 
        $("#rmbUser").attr("checked", true); 
        $("#txt_loginId").val($.cookie("userName")); 
        $("#txt_loginPwd").attr("type","password");
        $("#txt_loginPwd").val($.cookie("passWord")); 
    } 
    $(function(){
    	$("#btn_login").click(function(){
    	    saveUserInfo();    	   
    	});	
    });    
});

   document.onkeydown = function(event){
	    if((event.keyCode || event.which) == 13){
	    	$("#txt_loginId").blur();
	    	$("#txt_loginPwd").blur();
	    	$("#txt_loginYzm").blur();
	        $("#btn_login").click();
	    }
   }
   
 //保存用户信息 
function saveUserInfo() { 
    if (document.getElementById("rmbUser").checked==true) { 
        var userName = $("#txt_loginId").val(); 
        var passWord = $("#txt_loginPwd").val(); 
        $.cookie("rmbUser", "true", { expires: 14 }); // 存储一个带7天期限的 cookie 
        $.cookie("userName", userName, { expires: 14 }); // 存储一个带7天期限的 cookie 
        $.cookie("passWord", passWord, { expires: 14 }); // 存储一个带7天期限的 cookie 
    } 
    else { 
        $.cookie("rmbUser", "false", { expires: -1 }); 
        $.cookie("userName", '', { expires: -1 }); 
        $.cookie("passWord", '', { expires: -1 }); 
    } 
}        
  
</script>
</head>

<body>

<!--头部BEIGEN-->
<div class="head">
	<div class="logo">
    	<!--LOGO 尺寸：173*240-->
    	<a href="/"><img src="/client/images/index_logo.png" alt="科技小巨人LOGO" width="118px"; height="163px";  /></a>
        <p>科技小巨人&nbsp;&nbsp;&nbsp;&nbsp;开创新未来</p>
    </div>
    <ul>
    	<li class="sel whitebutton" id="present"><a href="/login">登&nbsp;&nbsp;录</a><i></i></li>
        <li class="whitebutton"><a href="/reg">注&nbsp;&nbsp;册</a><i></i></li>
        <li class="whitebutton"><a href="/">浏&nbsp;&nbsp;览</a><i></i></li>
    </ul>
</div>
<!--头部END-->

<!--main  BEIGEN-->
<div class="main">
	<!--login-->
	<div class="register">
    <form id="login" action="/login" method="post" name="login">
    	<div>
        	<i class="i1"></i>
        	<input type="text" name="username" id="txt_loginId" tip="用户名/手机号" datatype="*6-25" />
            
        </div>
        <div>
        	<i class="i3"></i>
        	<input type="password" name="password" id="txt_loginPwd"  datatype="*6-25" nullmsg="请输入密码"/>
           
        </div>
        <div class="yzm">
        	<i class="i5"></i>
        	<input class="inpt5" name="code" id="txt_loginYzm" type="text" value="验证码" datatype="*4-4" ajaxUrl="/login/check/code" onfocus="this.value='';" onblur="if(this.value==''){this.value='验证码'}"/>
            <a><img src="/verify" height=46.7px alt="验证码" onclick="this.src = '/verify?date='+Math.random();" id="yzm"/></a>
            
        </div>
        <div class="sure">
            <span class="span6 span7">  <input id="rmbUser" type="checkbox" style="width:20px;height:20px;"/>&nbsp;记住密码</span>
        	<span class="span6 span7"><a href="/login/password_retrieve">忘记密码？</a></span>
            <span class="span6 "><a href="/reg">注册账号></a></span>
        </div>
        <div>
        	<input class="ipt8" type="submit" id="btn_login" value="立即登录" />
        </div>
    </form>
    </div>
</div>
<!--main  END-->

</body>
</html>
