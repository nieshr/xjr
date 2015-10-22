<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<title>站内信息</title>
<link rel="shortcut icon" href="/client/images/icon.ico" />
<link href="/client/css/base.css" rel="stylesheet" type="text/css" />
<link href="/client/css/team.css" rel="stylesheet" type="text/css" />

<script src="/client/js/jquery-1.9.1.min.js"></script>
<script src="/client/js/main.js"></script>
<script type="text/javascript" src="/client/js/Validform_v5.3.2_min.js"></script>
        <style type="text/css">
        .page{ width: 600px; float: right; margin-top: 30px;}
        .page *{ float: left;}
        .page span{ color: #333333; line-height: 20px; display: block;}
        .page a{  width: 20px; height: 20px;}
        .page .page_next{ width: 60px;}
        .page .page_last{width: 40px;}
        .page p{  margin-left: 10px;}
        .hide{display:none;}
    </style>
    
<script>
$(document).ready(function(){

    $("#form1").Validform({
            tiptype:4
    });
});

function showmore()
{
  //	$(".messagelist").css("display","block");
	$(".messagelist").slideDown("slow");
	}
</script>
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
            <dd><a href="/enterprise/check">基本资料</a></dd>
            <dd><a href="/enterprise/activity/list">活动列表</a></dd>
            <dd><a href="/enterprise/project">申请展示</a></dd>

        </dl>
    </div>
<!--right-->
    <div class="right_content">
    <div class="right_box">
        <dl class="crumb">
            <dt><a href="#"></a></dt>
            <dd>
                <p>当前所在位置:</p>
                <a href="#">企业/团队</a>
                <p>&gt;</p>
                <a href="#">站内信息</a>

            </dd>
            <dt class="crumb_back"><a  href="javascript:history.go(-1);">返回上一页</a></dt>
        </dl>
                    <#if message_list?size gt 10>
                        <a style=" color: #66660F;align:center;margin:10px 0 10px 45%;display:block;" href="javascript:showmore();">显示更多</a>
                    </#if>
            <#if message_list??>
                <#list message_list as item>
                    <div class="messagelist" style="float:left;width:100%;<#if (message_list?size-item_index) gt 10>display:none;<#else>display:block;</#if>">
                    <#if item.speaker?? && item.speaker == 1>
                        <span style="display:block;float:left;">#${item_index!''}&nbsp;${item.region!''}管理员：</span>
                        <span style="display:block;float:left;background:#DDDDD1;margin-bottom:12px;border-radius:20px; ">
                            <h3 style="text-align:center;margin-top:5px;">${item.title!''}</h3>
                            <h4 style="text-align:right;margin-bottom:10px;right:10px;">${item.time!''}</h4>
                            <textarea style="background:#DDDDD1;border:none;margin-bottom:18px;" rows="3" disabled="" cols="80">${item.content!''}</textarea>
                        </span>
                    <#else>
                        <span style="display:block;float:right;">：${item.name!''}&nbsp;#${item_index!''}</span>
                        <span style="display:block;float:right;background:#DDDDD1;margin-bottom:12px;border-radius:20px;">
                            <h3 style="text-align:center;margin-top:5px;">${item.title!''}</h3>
                            <h4 style="text-align:right;margin-bottom:10px;right:10px;">${item.time!''}</h4>
                            <textarea style="background:#DDDDD1;border:none;margin-bottom:18px;" rows="3" cols="80" disabled="">${item.content!''}</textarea>
                        </span>
                    </#if>    
                    </div>
                </#list>
            </#if>    
            
            <dl class="team_mes_list">
                <form action="/enterprise/message/reply" id="form1">
                    <input type="hidden" name="regionAdminId" value="${message.regionAdminId?c!''}"></input>
                    <input type="hidden" name="statusE" value="1"></input>
                    <input type="hidden" name="speaker" value="0"></input>
                    <input type="hidden" name="region" value="${message.region!''}"></input>
	                <dd style=" margin-top: 20px;">
	                    <a>标题：</a><input name="title" type="text" value=""/>
	                </dd>
	                <dd style=" margin-top: 20px;">
	                    <a>内容：</a><textarea name="content" ></textarea>
	                </dd>
	                <dd>
	                    <input datatype="*0-120" style=" margin:20px 0 20px 0; width:60px;border: none; border-radius: 6px; background: #e67817;height: 30px; line-height: 30px; color: white; margin-left: 40px;" type="submit" value="发表" />
	                </dd>
                </form>
            </dl>
    </div> 

    </div>
</div><!--content_end-->
</div><!--main-->
</body>
</html>