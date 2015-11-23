<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<style>
    body{min-width:1200px;}
</style>
<!--网页左上角小图标-->
<link rel="shortcut icon" href="/client/images/icon.ico" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>科技小巨人-注册</title>
<!--css-->
<link href="/client/css/index_base.css" rel="stylesheet" type="text/css" />
<link href="/client/css/index_main.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="/client/css/ios6alert.css">

<script src="/client/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/client/js/Validform_v5.3.2_min.js"></script>
 <script src="/client/js/ios6alert.js"></script>
<script>
$(document).ready(function(){
	$("#reg").Validform({
		tiptype:4
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
   	 
   	 $("#isCheck").change(function(){
   	    var check = document.getElementById("isCheck");
   	    if(check.checked){
   	        $("#btn_reg").removeAttr("disabled");
   	        $("#btn_reg").css("background","#e77917");
   	    }else{
   	        $("#btn_reg").attr("disabled","true");
            $("#btn_reg").css("background","#999999");
   	    }
   	 });

//手机验证码
    $("#smsCodeBtn").bind("click", function() {  
        
        var mob = $('#txt_regMobile').val();
        
        var re = /^1\d{10}$/;
        
        if (!re.test(mob)) {
            $("body").ios6alert({
                content : "请输入正确的手机号"
            });
            return;
        }
        
        $("#smsCodeBtn").attr("disabled","disabled"); 
        $("#smsCodeBtn").css("background-color","#999999");
        
        <#--
        window.location="http://www.ht3g.com/htWS/BatchSend.aspx?CorpID=CQDL00059&Pwd=644705&Mobile=18580494867&Content=尊敬的用户，您的验证码为1234【科技小巨人】";
        $.get("http://www.ht3g.com/htWS/BatchSend.aspx",{
            'CorpID':'CQDL00059',
            'Pwd':'644705',
            'Mobile':mob,
            'Content':'尊敬的用户，您的验证码为1234【科技小巨人】',
        },function(status){
            alert(status);
        })
        -->
        $.ajax({  
            url : "/reg/smscode",  
            async : true,  
            type : 'GET',  
            data : {"mobile": mob},  
            success : function(res) {  
                if(1==res.message||0==res.message){
                    $("body").ios6alert({
                        content : "验证码已发送，请耐心等待！"
                    });
                }else{
                    $("body").ios6alert({
                        content : "验证码发送失败，请再次尝试！"
                    });
                    $("#smsCodeBtn").removeAttr("disabled");
                }
            },  
            error : function(XMLHttpRequest, textStatus,  
                    errorThrown) {  
                $("body").ios6alert({
                    content : "error！"
                });
                $("#smsCodeBtn").removeAttr("disabled");
            }  
        });
    }); 
});

   document.onkeydown = function(event){
    if((event.keyCode || event.which) == 13){
        $("#btn_reg").click();
    }
   }
   
 <#if error??>
function warnmsg()
{
    $("body").ios6alert({
        content : "请填写完整资料！"
    });
}

window.onload=warnmsg;
</#if>
<#--
function inputPwd()
{
	var isIE = /msie/i.test(navigator.userAgent) && !window.opera;
	if (isIE)
	{

		$(.input_pwd).css("display","none");
		$(.input_pwd2).css("display","block");
	} else {
      this.type='password';
        if(this.value=='密码')
        {
            this.value='';
        }
	}

		
		
}
-->
</script>
</head>

<body>

<!--头部BEIGEN-->
<div class="head">
	<div class="logo">
    	<!--LOGO 尺寸：118*163-->
    	<a href="/"><img src="/client/images/index_logo.png" alt="科技小巨人LOGO" width="118px"; height="163px"; /></a>
        <p>科技小巨人&nbsp;&nbsp;&nbsp;&nbsp;开创新未来</p>
    </div>
    <ul>
    	<li class="whitebutton"><a href="/login">登&nbsp;&nbsp;录</a><i></i></li>
        <li class="sel whitebutton" id="present"><a href="/reg">注&nbsp;&nbsp;册</a><i></i></li>
        <li class="whitebutton"><a href="/">浏&nbsp;&nbsp;览</a><i></i></li>
    </ul>
</div>
<!--头部END-->

<!--main  BEIGEN-->
<div class="main">
	<!--register-->
	<div class="register">
    <form action="/reg" method="post" id="reg">
    	<div>
        	<i class="i1"></i>
        	<input type="text" name="username" id="txt_regId" value="用户名" ajaxUrl="/reg/check/username" datatype="*6-25" onfocus="if(this.value=='用户名'){this.value='';}" onblur="if(this.value==''){this.value='用户名'}"/>
            <span class="Validform_checktip">*用户名</span>
        </div>
        <div>
        	<i class="i2"></i>
        	<input class="input_pwd" type="password" name="password" id="txt_regPwd" value="" datatype="*6-25" <#--onfocus="javascript:inputPwd();" onblur="if(this.value==''){this.type='text';this.value='密码'}"-->/>
        	<span class="Validform_checktip">*密码</span>
        </div>
        <div>
        	<i class="i3"></i>
        	<input class="input_pwd" type="password" id="txt_regPwdCfm" recheck="password" value="" datatype="*6-25" <#-- onfocus="this.type='password';if(this.value=='确认密码'){this.value='';}" onblur="if(this.value==''){this.type='text';this.value='确认密码'}"--> msg="两次密码不一致"/>
            <span class="Validform_checktip">*确认密码</span>
        </div>
        <div>
        	<i class="i4"></i>
        	<input type="text" name="mobile" id="txt_regMobile" value="手机" ajaxUrl="/reg/check/mobile" datatype="m|/^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/" onfocus="if(this.value=='手机'){this.value='';}" onblur="if(this.value==''){this.value='手机'}"/>
            <span class="Validform_checktip">*注册手机号</span>
        </div>
        <div>
        	<i class="i5"></i>
        	<input class="inpt5" type="text" name="smsCode" ajaxUrl="/reg/check/smsCode" datatype="*" value="验证码（30分钟有效）" onfocus="if(this.value=='验证码（30分钟有效）'){this.value='';}" onblur="if(this.value==''){this.value='验证码（30分钟有效）'}"/>
            <input class="inpt5_1"id="smsCodeBtn" style="background-color:#e77917;" onclick="javascript:;" readOnly="true"  type="submit" value="发送验证码" />
            <span class="Validform_checktip Validform_wrong" style=""></span>
            
            <#--<span class="span5"><b></b><font style="float:left;">重庆市科技小巨人企业培育专项行动网上平台验证码【科技小巨人】</font></span>-->
        </div>
        <div style="margin-top:-10px;margin-bottom:-20px;">
          
          
        </div>
        
        <div  style="margin-top:50px;">
        	<i class="i6"></i>
        	<input type="text" name="email" datatype="e" value="email" onfocus="this.type='text';if(this.value=='email'){this.value='';}" onblur="if(this.value==''){this.type='text';this.value='email'}"/>
            <!--
            <span><b></b>该用户名已被注册</span>
            -->
            <span>*邮箱</span>
        </div>
        <div class="sure">
        	<!--<input style="margin-top:7px" class="check" id="isCheck" type="checkbox" />-->
        	<input type="checkbox" class="check" id="isCheck"/>
            <span class="span6"> 我已阅读并接受<a href="#"> 版权声明 </a>和<a href="#"> 隐私保护 </a>条款</span>
        </div>
        <div>
        	<input class="ipt8" id="btn_reg" type="submit" disabled="disabled" style="background-color:#999999" value="加入小巨人" />
        </div>
    </form>
    </div>
</div>
<!--main  END-->

</body>
</html>
