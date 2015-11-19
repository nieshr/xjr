<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
        <title>专家评委-活动列表</title>
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
                                <dt>时间</dt>
                                <#if activities??>
                                    <#list activities as item>
                                        <#if item.date??>
                                            <dd><a>${item.date?string("yyyy年MM月dd日   HH:mm")}</a></dd>
                                        </#if>
                                    </#list>
                                </#if>
                            </dl>
                            <dl class="list_active7">
                                <dt>操作</dt>
                                <#if activities??>
                                    <#list activities as item>
                                        <dd>
                                        <#--
                                        	<#if item.gradetimeId??&&item.gradetimeId==0>
                                        		<a title="活动未开始" href="javascript:void(0)" style="color:#666;">评分</a>
                                        	<#elseif item.gradetimeId??&&item.gradetimeId==1>	
	                                        	<a target="_blank" href="/expert/grade?activityId=${item.id?c}">评分</a>
	                                        <#elseif item.gradetimeId??&&item.gradetimeId==2>
	                                        	<a title="活动已结束" href="javascript:void(0)" style="color:#666;">评分</a>	
	                                        </#if>
	                                    -->
	                                    	<a target="_blank" title="<#if item.gradetimeId??&&item.gradetimeId==0>
	                                    												活动未开始
	                                    												<#elseif item.gradetimeId??&&item.gradetimeId==1>
	                                    												开始评分
	                                    												 <#elseif item.gradetimeId??&&item.gradetimeId==2>
	                                    												 活动已结束
	                                    												 </#if>	" href="/expert/grade?activityId=${item.id?c}">评分</a>    
	                                        &nbsp;|&nbsp;
	                                        <a href="/expert/detail/${item.id?c}">详情</a>
	                                        &nbsp;|&nbsp;
	                                        <#if item.statusId??&&(item.statusId==1||item.statusId==2)>
	                                        	<a href="/expert/export/grade?activityId=${item.id?c!''}&expertId=${expert.id?c!''}&isModule=1" title="下载并打印模板">评分表模板</a>
	                                        <#else>
	                                        	<a href="javascript:void(0)" title="活动还在筹备中" style="color:#666;">评分表模板</a>
	                                        </#if>
	                                    </dd>
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