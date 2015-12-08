<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>个人中心-申请展示</title>
<link rel="shortcut icon" href="/client/images/icon.ico" />

<link href="/client/css/base.css" rel="stylesheet" type="text/css" />
<link href="/client/css/team.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="/client/css/showBo.css">

</head>

<script src="/client/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/mag/js/Validform_v5.3.2_min.js"></script>
<script src="/client/js/main.js"></script>

<script type="text/javascript" src="/mag/js/WdatePicker.js"></script>
<script type="text/javascript" src="/mag/js/swfupload.js"></script>
<script type="text/javascript" src="/mag/js/swfupload.queue.js"></script>
<script type="text/javascript" src="/mag/js/swfupload.handlers.js"></script>
<script type="text/javascript" charset="utf-8" src="/mag/js/kindeditor.js"></script>
<script type="text/javascript" charset="utf-8" src="/mag/js/zh_CN.js"></script>
<script type="text/javascript" src="/mag/js/layout.js"></script>
<script src="/client/js/showBo.js"></script>

<script type="text/javascript">
    var img;
    
$(document).ready(function(){
        var txtPic = $("#url3").val();
        if (txtPic == "" || txtPic == null) 
        {
            $("#ImgView").hide();
        }
        else 
        {
            $("#ImgView").attr("src" , $("#url3").val())
            $("#ImgView").show();
        }

        $("#url3").blur(function()
        {
          $("#ImgView").attr("src" , $("#url3").val());
          $("#ImgView").show();
        });
});


    $(function () {
        //初始化表单验证
        $("#form1").initValidform();
        //初始化编辑器
			var editor;
			KindEditor.ready(function(K) {
				editor = K.create('textarea[name="content"]', {
		            height: '500px',
					resizeType : 1,
					allowPreviewEmoticons : false,
					allowImageUpload : true,
				    uploadJson: '/editor/upload',
            		fileManagerJson: '/Verwalter/editor/upload?action=EditorFile',
					items : [
						'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
						'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
						'insertunorderedlist', '|', 'emoticons', 'image', 'link']
				});
			});
			
        });

        
function showImg(imageUrl)
{
    $(".thumb_ImgUrl_show").show();
    $("#ImgView").attr("src",imageUrl);
}


function subActivity(){
    
}    

function done(msg)
{
     Showbo.Msg.alert(msg);
}

<#if msg?? >
window.onload=done(${msg});
</#if>


</script>
<script>
KindEditor.ready(function(K) {
    var editor = K.editor({
        allowFileManager : true,
        uploadJson: '/editor/upload',
        allowUpload : true,

    });

    K('#image3').click(function() {
        editor.loadPlugin('image', function() {
            editor.plugin.imageDialog({
                showRemote : false,
                imageUrl : K('#url3').val(),                       
                clickFn : function(url, title, width, height, border, align) {
                    K('#url3').val(url);
                    img=url;
                    editor.hideDialog();
                    showImg(url)
                }
            });
        });
    });
});
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
    <form id = "form1"  action="/enterprise/article" method="post">
    <input type="hidden" name="categoryId" value="<#if enterprise.formType??&&enterprise.formType==0>16<#else>17</#if>" />
    <#if article??>
    <input type="hidden" name="id" value="${article.id?c!''}" />
    </#if>
    <div class="right_content">
        <div class="right_box">
        	<dl class="crumb">
            	<dt><a href="javascript:void(0)"></a></dt>
                <dd>
                	<p>当前所在位置:</p>
                    <a href="javascript:void(0)">申请展示</a>
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
                <dt>展示标题：</dt>
                <dd>
                    <input type="text" <#if article??&&article.statusId==1>disabled=""</#if> value="<#if article??&&article.title??>${article.title!''}</#if>"  datatype="*" id="title" name="title"/>
                </dd>
            </dl>   
             <dl class="team_title03">
                <dt style="font-size:14px;float:left">封面图片：</dt>
                <dd>
                    <input name="imgUrl" type="text" end  id="url3"<#if article??&&article.statusId==1>disabled=""</#if> value="<#if article??&&article.imgUrl??>${article.imgUrl!''}</#if>"  />
                    <input <#if article??&&article.statusId==1>disabled=""</#if> type="button" id="image3" value="选择图片" />
                    <div class="img1_lcy">
                        <img id="ImgView" style="width:332px; height:144px;"/>
                    </div>
                </dd>
                
            </dl>  
                        

            <dl class="team_title03" style="float:left;margin-top:20px;">
                <dt style="font-size:14px;float:left;">内容描述：&nbsp;&nbsp;</dt>
                <dd>
                    <textarea  name="content" class="editor" id="content" <#if article??&&article.statusId==1>disabled=""</#if> datetype="*" style="visibility:hidden;"><#if article??&&article.content??>${article.content!''}</#if></textarea>
                </dd>
            </dl>    
        </div>    
        <input <#if article??&&article.statusId==1>disabled="" style="cursor:pointer; width:80px; height: 30px; border:none; background: #666; font-size: 14px; color: white; border-radius: 6px;margin-left: 30px; margin-top: 50px;"</#if> style="cursor:pointer; width:80px; height: 30px; border:none; background: #e67817; font-size: 14px; color: white; border-radius: 6px;margin-left: 30px; margin-top: 50px;" type="submit" value="提交"/>
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
