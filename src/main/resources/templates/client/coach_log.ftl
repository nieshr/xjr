<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>专家评委-日志记录</title>
    <link rel="shortcut icon" href="/images/icon.ico" />
    <link href="/client/css/base.css" rel="stylesheet" type="text/css" />
    <link href="/client/css/expert.css" rel="stylesheet" type="text/css" />
    <script src="/client/js/jquery-1.9.1.min.js"></script>
    <script src="/client/js/main.js"></script>
    <script>
			function submit(){
			    var content = document.getElementById("content").value;
			    window.location.href = "/expert/coach/save?content="+content+"&activityId=<#if activityId??>${activityId?c}</#if>";
			}    
    </script>
</head>


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
                        <dd><a href="/expert/enterprise/list">活动列表</a></dd>
                        <dd><a href="/expert/enterprises">辅导企业</a></dd>
                        <dd><a href="/expert/lyfd">路演辅导</a></dd>
                    </dl>
                </div>
                <!--right-->
                <div class="right_content">
                    <div class="right_box">
                        <dl class="crumb">
                            <dt><a href="#"></a></dt>
                                <dd>
                                    <p>当前所在位置:</p>
                                    <a href="/expert/lyfd">路演辅导</a>
                                    <p>&gt;</p>
                                    <a href="#">路演辅导记录</a>
                                </dd>
                            <dt class="crumb_back"><a></a></dt>
                        </dl>
                        <dl class="expert_date">
                            <#include "/client/records.ftl">
                            <dt>
                                <span>意见录入:</span>
                                <textarea id="content"></textarea>
                                <input type="button" value="发表" onclick="javascript:submit();" />
                            </dt>
                        </dl>
                    </div>    
                </div>
            </div><!--content_end-->
    </div><!--main-->
</body>
</html>
