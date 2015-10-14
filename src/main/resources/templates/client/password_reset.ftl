<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>密码修改</title>
    <link rel="shortcut icon" href="images/icon.ico" />
    <link href="/client/css/base.css" rel="stylesheet" type="text/css" />
    <link href="/client/css/center.css" rel="stylesheet" type="text/css" />
</head>
    <script src="/client/js/jquery-1.9.1.min.js"></script>
    <script src="/client/js/main.js"></script>
<script type="text/javascript" src="/client/js/Validform_v5.3.2_min.js"></script>
<script>
    $(document).ready(function(){
        $("#reset").Validform({
            tiptype:4,
        });
    });
</script>
<body>
    <!--main-->
    <div class="main">
        <!--header-->
        <#include "/client/user_common_header.ftl">
        <!--header_end-->
        <!--content-->
        <div class="content">
            <!--left-->
            <div class="leftbar">
                <dl class="nav">
                    <dt><a href="#">企业/团队</a></dt>
                    <dd><a href="#">基本资料</a></dd>
                    <dd><a href="#">活动列表</a></dd>
                    <dd><a href="#">申请展示</a></dd>
                    <dt><a href="#">企业/团队</a></dt>
                    <dd><a href="#">基本资料</a></dd>
                    <dd><a href="#">活动列表</a></dd>
                    <dd><a href="#">申请展示</a></dd>
                </dl>
            </div>
            <!--right-->
            <div class="right_content">
                <div class="right_box">
                        <dl class="crumb">
                            <dt><a href="#"></a></dt>
                            <dd>
                                <a href="#">密码修改</a>
                            </dd>
                        </dl>
                        <dl class="password">
                    <form id="reset" action="/user/password/save" method="post">
                            <dd><p>当前密码 :</p><input datatype="*" ajaxurl="/user/check/oldpassword" type="password" value="" /></dd>
                            <dd><p>新密码 : </p><input name="newPassword" id="newPassword" datatype="*6-25" type="password" value="" /></dd>
                            <dd><p>确认密码 :</p><input name="rePassword" id="rePassword" recheck="newPassword" datatype="*6-25" type="password" value="" /></dd>
                            <dt><input type="submit" value="确定" /></dt>
                    </form>
                        </dl>
                </div>    
            </div>
        </div><!--content_end-->
    </div><!--main-->
</body>
</html>
