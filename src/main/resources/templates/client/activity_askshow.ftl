<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>申请展示</title>
<link rel="shortcut icon" href="/client/images/icon.ico" />

<link href="/client/css/base.css" rel="stylesheet" type="text/css" />
<link href="/client/css/team.css" rel="stylesheet" type="text/css" />


</head>

<script src="/client/js/jquery-1.9.1.min.js"></script>
<script src="/client/js/main.js"></script>

<script type="text/javascript" src="/mag/js/WdatePicker.js"></script>
<script type="text/javascript" src="/mag/js/swfupload.js"></script>
<script type="text/javascript" src="/mag/js/swfupload.queue.js"></script>
<script type="text/javascript" src="/mag/js/swfupload.handlers.js"></script>
<script type="text/javascript" charset="utf-8" src="/mag/js/kindeditor-min.js"></script>
<script type="text/javascript" charset="utf-8" src="/mag/js/zh_CN.js"></script>
<script type="text/javascript" src="/mag/js/layout.js"></script>

<script type="text/javascript">
    $(function () {
        //初始化表单验证
        $("#form1").initValidform();

        //初始化编辑器
        var editor = KindEditor.create('.editor', {
            width: '98%',
            height: '350px',
            resizeType: 1,
            uploadJson: '/Verwalter/editor/upload?action=EditorFile',
            fileManagerJson: '/Verwalter/editor/upload?action=EditorFile',
            allowFileManager: true
        });
        
   });
function subActivity(){
    
}    
</script>

<body>
<!--main-->
<div class="main">
<!--header-->
    <#include "/client/user_common_header.ftl" />
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
    <form action="/enterprise/article" method="post">
    <div class="right_content">
        <div class="right_box">
        	<dl class="crumb">
            	<dt><a href="#"></a></dt>
                <dd>
                	<p>当前所在位置:</p>
                    <a href="">申请展示</a>
                </dd>
            </dl>
            <dl class="team_title01">
                <dt></dt>
                <dd>
                    <#if category_list??>
                    <#list category_list as ca>
                        <input type="radio" name="categoryId" value="${ca.id?c}" /><span>${ca.title!''}</span>
                    </#list>
                    </#if> 
                </dd>
            </dl>
            <dl class="team_title02">
                <dt>展示标题 :</dt>
                <dd>
                    <input type="text" value="" id="title" name="title"/>
                </dd>
            </dl>   
            <dl style="float:left;">
                <dt style="font-size:14px;float:left">内容描述：</dt>
                <dd>
                    <textarea name="content" class="editor" id="content" style="visibility:hidden;"></textarea>
                </dd>
            </dl>    
        </div>    
        <input style="width:80px; height: 30px; border:none; background: #e67817; font-size: 14px; color: white; border-radius: 6px;margin-left: 30px; margin-top: 50px;" type="submit" value="提交"/>
    </div>
    </form>
</div><!--content_end-->
</div><!--main-->
</body>
</html>
