<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
    <title>路演辅导</title>
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
        <#include "/client/user_common_header.ftl" />
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
                            </dd>
                        </dl>
                        <div class="btwz">当前辅导活动</div>
                        <div class="list_base8">
                            <dl>
                                <dt>序号</dt>
                                <#if grade_false_list??>                                
                                    <#list grade_false_list as item>
                                        <dd>${item_index+1}</dd>
                                    </#list>
                                </#if>
                            </dl>
                            <dl>
                                <dt>活动名称</dt>
                                    <#if grade_false_list??>                                
                                        <#list grade_false_list as item>
                                            <dd><a href="#">${item.enterpriseName!''}</a></dd>
                                        </#list>
                                    </#if>
                            </dl>
                            <dl>
                                <dt>活动类型</dt>
                                <#if grade_false_list??>                                
                                    <#list grade_false_list as item>
                                        <dd class="list_color_blue"><a href="#">${item.activityType!''}</a></dd>
                                    </#list>
                                </#if>
                            </dl>
                            <dl>
                                <dt>行业归属</dt>
                                <#if grade_false_list??>                                
                                    <#list grade_false_list as item>
                                        <dd class="list_color_blue"><a href="#">${item.type!''}</a></dd>
                                    </#list>
                                </#if>
                            </dl>
                            <dl  class="list_active8">
                                <dt>操作</dt>
                                <#if grade_false_list??>                                
                                    <#list grade_false_list as item>
                                        <dd><a href="/expert/coach/${item.enterpriseId?c}">录入辅导信息</a>丨<a href="#">下载</a></dd>
                                    </#list>
                                </#if>
                            </dl>
                        </div>
                        <div class="btwz">往期辅导企业</div>
                        <div class="list_base8">
                            <dl>
                                <dt>序号</dt>
                                <#if grade_true_list??>
                                    <#list grade_true_list as item>
                                        <dd>${item_index+1}</dd>
                                    </#list>
                                </#if>
                            </dl>
                            <dl>
                                <dt>项目名称</dt>
                                <#if grade_true_list??>
                                    <#list grade_true_list as item>
                                        <dd><a href="#">${item.enterpriseName!''}</a></dd>
                                    </#list>
                                </#if>
                            </dl>
                            <dl>
                                <dt>活动类型</dt>
                                <#if grade_true_list??>
                                    <#list grade_true_list as item>
                                        <dd><a href="#">${item.activityType!''}</a></dd>
                                    </#list>
                                </#if>
                            </dl>
                            <dl>
                                <dt>行业归属</dt>
                                <#if grade_true_list??>
                                    <#list grade_true_list as item>
                                        <dd><a href="#">${item.type!''}</a></dd>
                                    </#list>
                                </#if>
                            </dl>
                            <dl  class="list_active8">
                                <dt>操作</dt>
                                <#if grade_true_list??>
                                    <#list grade_true_list as item>
                                        <dd><a href="/expert/coach/log/${item.entenpriseId?c}">查看</a>丨<a href="#">下载</a></dd>
                                    </#list>
                                </#if>
                            </dl>
                        </div>
                    </div>
                </div><!--content_end-->
    </div><!--main-->
</body>
</html>