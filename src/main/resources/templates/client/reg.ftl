<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<!--网页左上角小图标-->
<link rel="shortcut icon" href="/client/images/index/icon.ico" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>科技小巨人-注册</title>
<!--css-->
<link href="/client/css/index_base.css" rel="stylesheet" type="text/css" />
<link href="/client/css/index_main.css" rel="stylesheet" type="text/css" />

<script src="/client/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/client/js/Validform_v5.3.2_min.js"></script>
<script>
$(document).ready(function(){
	$("#reg").Validform({
		tiptype:4,
		  ajaxPost:true,
		                callback: function (data) { 
                <!-- 修改 -->
                    if (data.role == 2){
                        window.location.href="/user/diysite/order/list/0";
                    }    
                    else if (data.code == 0) {
                    	alert("注册成功")
                        var url = document.referrer;          
                        if(undefined==url || ""==url){
                            window.location.href="/";
                        }else{
                            window.location.href = url; 
                        }
                    } else {
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
//手机验证码
    $("#smsCodeBtn").bind("click", function() {  
        
        var mob = $('#txt_regMobile').val();
        
        var re = /^1\d{10}$/;
        
        if (!re.test(mob)) {
            alert("请输入正确的手机号");
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
                    alert("验证码已发送，请耐心等待！");
                }else{
                    alert("验证码发送失败，请再次尝试！");
                    $("#smsCodeBtn").removeAttr("disabled");
                }
            },  
            error : function(XMLHttpRequest, textStatus,  
                    errorThrown) {  
                alert("error");
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
   
  
</script>
</head>

<body>

<!--头部BEIGEN-->
<div class="head">
	<div class="logo">
    	<!--LOGO 尺寸：173*240-->
    	<a href="#"><img src="/client/images/index_logo.png" alt="科技小巨人LOGO" /></a>
        <p>科技小巨人&nbsp;&nbsp;&nbsp;&nbsp;开创新未来</p>
    </div>
    <ul>
    	<li class="whitebutton"><a href="/login">登&nbsp;&nbsp;录</a><i></i></li>
        <li class="sel whitebutton" id="present"><a href="/reg">注&nbsp;&nbsp;册</a><i></i></li>
        <li class="whitebutton"><a href="#">浏&nbsp;&nbsp;览</a><i></i></li>
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
        </div>
        <div>
        	<i class="i2"></i>
        	<input type="text" name="password" id="txt_regPwd" value="密码" datatype="*6-25" onfocus="this.type='password';if(this.value=='密码'){this.value='';}" onblur="if(this.value==''){this.type='text';this.value='密码'}"/>
        </div>
        <div>
        	<i class="i3"></i>
        	<input type="text" id="txt_regPwdCfm" recheck="password" value="确认密码" datatype="*6-25" onfocus="this.type='password';if(this.value=='确认密码'){this.value='';}" onblur="if(this.value==''){this.type='text';this.value='确认密码'}" msg="两次密码不一致"/>
        </div>
        <div>
        	<i class="i4"></i>
        	<input type="text" name="mobile" id="txt_regMobile" value="手机" ajaxUrl="/reg/check/mobile" datatype="m" onfocus="if(this.value=='手机'){this.value='';}" onblur="if(this.value==''){this.value='手机'}"/>
        </div>
        <div>
        	<i class="i5"></i>
        	<input class="inpt5" type="text" name="smsCode" ajaxUrl="/reg/check/smsCode" datatype="*" value="验证码（30分钟有效）" onfocus="this.type='password';if(this.value=='验证码（30分钟有效）'){this.value='';}" onblur="if(this.value==''){this.type='text';this.value='验证码（30分钟有效）'}"/>
            <input class="inpt5_1"id="smsCodeBtn" style="background-color:#e77917;" onclick="javascript:;" readOnly="true"  type="submit" value="发送验证码" />
            <span class="span5"><b></b>重庆市科技小巨人企业培育专项行动网上平台验证码【科技小巨人】</span>
        </div>
        <div>
        	<i class="i6"></i>
        	<input type="text" value="email" name="email" datatype="e" value="email" onfocus="this.type='password';if(this.value=='email'){this.value='';}" onblur="if(this.value==''){this.type='text';this.value='email'}"/>
            <!--
            <span><b></b>该用户名已被注册</span>
            -->
        </div>
        <div class="sure">
        	<input class="check" type="checkbox" />
            <span class="span6"> 我已阅读并接受<a href="#"> 版权声明 </a>和<a href="#"> 隐私保护 </a>条款</span>
        </div>
        <div>
        	<input class="ipt8" id="btn_reg" type="submit" value="加入小巨人" />
        </div>
    </form>
    </div>
</div>
<!--main  END-->

</body>
</html>
