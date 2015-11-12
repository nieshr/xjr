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
			var editor;
			KindEditor.ready(function(K) {
				editor = K.create('textarea[name="content"]', {
					resizeType : 1,
					allowPreviewEmoticons : false,
					allowImageUpload : true,
					items : [
						'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
						'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
						'insertunorderedlist', '|', 'emoticons', 'image', 'link']
				});
			});
   });
   			KindEditor.ready(function(K) {
				var editor = K.editor({
					allowFileManager : true
				});
      				//上传图片
   				K('#image3').click(function() {
				editor.loadPlugin('image', function() {
					editor.plugin.imageDialog({
						showRemote : false,
						imageUrl : K('#url3').val(),
						clickFn : function(url, title, width, height, border, align) {
							K('#url3').val(url);
							editor.hideDialog();
						}
					});
				});
			});
		});		
function subActivity(){
    
}    

function done(msg)
{
    alert(msg);	
}

<#if msg?? >
window.onload=done(${msg});
</#if>

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
            <dd><a href="/enterprise/check">网上报名</a></dd>
            <dd><a href="/enterprise/data">项目资料</a></dd>
            <dd><a href="/enterprise/activity/list">活动列表</a></dd>
            <dd><a href="/enterprise/project">申请展示</a></dd>
		</dl>
	</div>
<!--right-->
<#if enterprise??&&enterprise.formType??&&enterprise.statusId??>
    <form  action="/enterprise/article" method="post">
    <input type="hidden" name="categoryId" value="<#if enterprise.formType??&&enterprise.formType==0>16<#else>17</#if>" />
    <#if article??>
    <input type="hidden" name="id" value="${article.id?c!''}" />
    </#if>
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
                <#--
                    <#if category_list??>
                    <#list category_list as ca>
                        <input type="radio" name="categoryId" value="${ca.id?c}" /><span>${ca.title!''}</span>
                    </#list>
                    </#if>
                    -->
                    <#if article??&&article.statusId??>
                     审核状态：<#if article.statusId==0>已通过<#elseif article.statusId==1>审核中<#elseif article.statusId==2>未通过</#if>
                     </#if>
                </dd>
            </dl>
            <dl class="team_title02">
                <dt>展示标题 :</dt>
                <dd>
                    <input type="text" value="<#if article??&&article.title??>${article.title!''}</#if>" <#if article??&&article.imgUrl??&&(article.statusId==0 || article.statusId==1)>disabled=""</#if> datatype="*" id="title" name="title"/>
                </dd>
            </dl>   
             <dl class="team_title02" style="float:left;">
                <dt style="font-size:14px;float:left">封面图片：</dt>
                <dd>
                    <input type="text" datatype="*" id="url3" value="<#if article??&&article.imgUrl??>${article.imgUrl!''}</#if>"  /> <input <#if article??&&article.imgUrl??&&(article.statusId==0 || article.statusId==1)>disabled=""</#if> type="button" id="image3" value="选择图片" />
                </dd>
            </dl>               
            <dl style="float:left;">
                <dt style="font-size:14px;float:left">内容描述：</dt>
                <dd>
                    <textarea  name="content" class="editor" id="content" <#if article??&&article.imgUrl??&&(article.statusId==0 || article.statusId==1)>disabled=""</#if> datetype="*" style="visibility:hidden;"><#if article??&&article.content??>${article.content!''}</#if></textarea>
                </dd>
            </dl>    
        </div>    
        <input <#if article??&&article.imgUrl??&&(article.statusId==0 || article.statusId==1)>disabled=""</#if> style="cursor:pointer; width:80px; height: 30px; border:none; background:  <#if article??&&article.imgUrl??&&(article.statusId==0 || article.statusId==1)>#666<#else>>#e67817</#if>; font-size: 14px; color: white; border-radius: 6px;margin-left: 30px; margin-top: 50px;" type="submit" value="提交"/>
    </div>
    </form>
 <#else>
     <div class="right_content">
        <div class="right_box">
             <dl class="team_title01">
                <dt></dt>
                <dd>
				 	<h2>请先完善报名资料并通过审核后再申请展示。您的文章将在显示“企业项目”页面中。</h2>
				 </dd>
				 </dl>
 </#if>
 
</div><!--content_end-->
</div><!--main-->
</body>
</html>
