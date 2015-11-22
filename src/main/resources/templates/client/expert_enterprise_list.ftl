<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
    <title>专家评委-辅导企业</title>
    <link rel="shortcut icon" href="/client/images/icon.ico" />
    <link href="/client/css/base.css" rel="stylesheet" type="text/css" />
    <link href="/client/css/expert.css" rel="stylesheet" type="text/css" />
    <script src="/client/js/jquery-1.9.1.min.js"></script>
    <script src="/client/js/main.js"></script>
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
                            <a href="/expert/enterprises">辅导企业</a>
                        </dd>
                    </dl>
                    <div class="list_base6">
                        <dl>
                            <dt>备选企业列表</dt>
                            <#if enterprise_list??>
                                <#list enterprise_list as item>
                                    <dd><a>${item.enterpriseName!''}</a></dd>
                                </#list>
                            </#if>
                        </dl>
                        <dl class="list_color_blue">
                            <dt>地址</dt>
                            <#if enterprise_list??>
                                <#list enterprise_list as item>
                                    <dd><a title="${item.addr!''}">${item.addr!''}</a></dd>
                                </#list>
                            </#if>
                        </dl>
                        <dl class="list_color_yellow">
                            <dt>电话</dt>
                            <#if enterprise_list??>
                                <#list enterprise_list as item>
                                    <dd><a>${item.pantent!''}</a></dd>
                                </#list>
                            </#if>
                        </dl>
                        <dl class="list_active6">
                            <dt>操作</dt>
                            <#if enterprise_list??>
                                <#list enterprise_list as item>
                                    <dd><a href="/expert/coach/${item.enterpriseId?c}">跟踪档案</a></dd>
                                </#list>
                            </#if>
                        </dl>
                    </div>
                </div> 
            </div>
        </div><!--content_end-->
    </div><!--main-->
</body>
</html>