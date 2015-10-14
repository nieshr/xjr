<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>活动资料</title>
    <link rel="shortcut icon" href="/images/icon.ico" />
    <link href="/client/css/base.css" rel="stylesheet" type="text/css" />
    <link href="/client/css/area.css" rel="stylesheet" type="text/css" />
    <script src="/client/js/jquery-1.9.1.min.js"></script>
    <script src="/client/js/main.js"></script>
    <script type="text/javascript">
        function download(name){
            $.get("/expert/download",{"name":name},function(){
            });
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
                            <p>&gt;</p>
                            <a href="#">活动资料</a>
                        </dd>
                        <dt class="crumb_back"><a  href="javascript:history.go(-1);">返回上一页</a></dt>
                    </dl>
                </div>  
                <dl class="active_text">
                <dd>
                    <div><span>活动名称：</span><input type="text" value="${activity.title!''}" disabled="" /></div>
                    <div>
                        <span>活动类型：</span>
                        <select disabled="">
                            <#if all_type??>
                                <#list all_type as item>
                                    <option <#if item.title==activity.activityType>selected=""</#if>>${item.title}</option>
                                </#list>
                            </#if>
                        </select>
                    </div>
                    <div><span>地区 ：</span><input type="text" value="${activity.region!''}" disabled=""/></div>
                    <div><span>日期：</span><input type="text" value="<#if activity.date??>${activity.date?string("yyyy-MM-dd")}</#if>" disabled=""/></div>
                    <div><span>地址：</span><input type="text" value="${activity.address!''}" disabled=""/></div>
                    <div><span>主题：</span><input type="text" value="${activity.theme!''}" disabled=""/></div>
                    <div><span>简介：</span><textarea disabled="">${activity.introduction!''}</textarea></div>
                    <div>
                        <span>筹备开始时刻：</span><input type="text" value="<#if activity.prepareOn??>${activity.prepareOn?string("yyyy-MM-dd")}</#if>" disabled=""/>
                        <span>筹备结束时刻：</span><input type="text" value="<#if activity.prepareOff??>${activity.prepareOff?string("yyyy-MM-dd")}</#if>" disabled=""/>
                    </div>
                    <div><span>活动结束时刻：</span><input type="text" value="<#if activity.eventEnd??>${activity.eventEnd?string("yyyy-MM-dd")}</#if>" disabled=""/></div>
                    <div>
                        <span style="margin-top: 6px;">相关下载：</span>
                        <#if resources??>
                            <ul class="active_add_text">
                                <#list resources?split(",") as item>
                                    <li>
                                        <img src="/images/active_file.png" />
                                        <p class="p01">${item}</p>
                                        <a href="/expert/download?name=${item}">下载</a>
                                    </li>
                                </#list>
                            </ul>
                        </#if>
                    </div>
    
                    <div>
                        <span style="margin-top: 6px;">项目列表：</span>
                        <#if enterprise_resources??>
                            <form action="/expert/files/download" method="post">
                                <ul class="active_project_text">
                                    <#list enterprise_resources as item>
                                        <li>
                                            <p class="p01">${item_index+1}.${item.download!''}</p>
                                            <a style="display:block;  width:100px;"></a>
                                            <a href="#">档案详情</a>
                                            <input style="width: 14px; height: 14px; margin-top: 10px;" type="checkbox" name="name" value="${item.id?c}" />
                                        </li>
                                    </#list>
                                    <li style="margin-top:10px ;"><input  type="submit" name="" id="" value="下载PPT" /></li>
                                </ul>
                            </form>
                        </#if>
                    </div>
    
                    <div>
                        <span style="margin-top: 6px;">评委专家：</span>
                        <#if experts??>
                            <ul class="active_project_text">
                                <#list experts as item>
                                    <li>
                                        <p class="p01">${item_index+1}.${item.name!''}</p>
                                        <a style="display:block;  width:100px;"></a>
                                        <a href="#">评分情况</a>
                                        <a style="display:block;  width:60px;"></a>
                                    </li>
                                </#list>
                            </ul>
                        </#if>
                    </div>
    
                    <div>
                        <span style="margin-top: 6px;">路演结果：</span>
                        <ul class="active_project_text">
                            <li>
                                <a href="#" class="p01">查看排名</a>
                                <a style="display:block;  width:60px;"></a>
                            </li>
                        </ul>
                    </div>
                </dl>
            </div>
        </div><!--content_end-->
    </div><!--main-->
</body>
</html>
