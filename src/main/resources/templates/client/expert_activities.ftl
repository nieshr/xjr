<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
        <title>活动列表</title>
        <link rel="shortcut icon" href="/images/icon.ico" />
        <link href="/client/css/base.css" rel="stylesheet" type="text/css" />
        <link href="/client/css/expert.css" rel="stylesheet" type="text/css" />
        <script src="/client/js/jquery-1.9.1.min.js"></script>
        <script src="/client/js/main.js"></script>
    </head>
    <body>
        <!--main-->
        <div class="main">
            <!--header-->
            <div class="header">
                <div class="logo"></div>
                <form>
                    <div class="search">
                        <input type="text" value=" &nbsp;请输入搜索内容" />
                        <input style="width:40px; background:url(/client/images/search.png) no-repeat center;" type="submit" value="" />
                    </div>
                </form>
                <dl class="porfile">
                    <dt><a>帮助</a></dt>
                    <dt><a>站内信息</a></dt>
                    <dd>
                        <div><img src="images/porfile_img.png" /></div>
                        <span>学友</span>
                        <p>管理员</p>
                        <span id="nav_guide"></span>
                    </dd>
                    <ul id="porfile_subnav">
                        <li><a href="#">帐号资料</a></li>
                        <li><a href="#">头像设置</a></li>
                        <li><a href="#">修改密码</a></li>
                        <li><a href="#">退出</a></li>  
                    </ul>
                </dl>
            </div><!--header_end-->
            <!--content-->
            <div class="content">
                <!--left-->
                <div class="leftbar">
                    <dl class="nav">
                        <dd><a href="#">辅导企业</a></dd>
                        <dd><a href="#">活动列表</a></dd>
                        <dd><a href="#">路演辅导</a></dd>
                    </dl>
                </div>
                <!--right-->
                <div class="right_content">
                    <div class="right_box">
                        <dl class="crumb">
                            <dt><a href="#"></a></dt>
                            <dd>
                                <p>当前所在位置:</p>
                                <a href="#">活动列表</a>
                            </dd>
                        </dl>
                        <div class="list_base7">
                            <dl>
                                <dt>活动类型</dt>
                                <#if activities??>
                                    <#list activities as item>
                                        <dd class="list_color_yellow"><a>${item.activityType!''}</a></dd>
                                    </#list>
                                </#if>
                            </dl>
                            <dl>
                                <dt>活动名称</dt>
                                <#if activities??>
                                    <#list activities as item>
                                        <dd><a>${item.title!''}</a></dd>
                                    </#list>
                                </#if>
                            </dl>
                            <dl>
                                <dt>日期</dt>
                                <#if activities??>
                                    <#list activities as item>
                                        <#if item.date??>
                                            <dd><a>${item.date?string("yyyy-MM-dd")}</a></dd>
                                        </#if>
                                    </#list>
                                </#if>
                            </dl>
                            <dl class="list_active7">
                                <dt>操作</dt>
                                <#if activities??>
                                    <#list activities as item>
                                        <dd><a href="#">评分</a>&nbsp;|&nbsp;<a href="/expert/detail/${item.id?c}">详情</a></dd>
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