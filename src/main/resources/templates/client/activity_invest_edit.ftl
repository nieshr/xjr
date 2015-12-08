<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>活动管理员-辅导投资</title>
<link rel="shortcut icon" href="/client/images/icon.ico" />

<link href="/client/css/base.css" rel="stylesheet" type="text/css" />
<link href="/client/css/team.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="/client/css/showBo.css">

</head>

<script src="/client/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/mag/js/Validform_v5.3.2_min.js"></script>
<script src="/client/js/main.js"></script>
<script src="/client/js/showBo.js"></script>

<script type="text/javascript" src="/mag/js/WdatePicker.js"></script>
<script type="text/javascript" src="/mag/js/swfupload.js"></script>
<script type="text/javascript" src="/mag/js/swfupload.queue.js"></script>
<script type="text/javascript" src="/mag/js/swfupload.handlers.js"></script>
<script type="text/javascript" charset="utf-8" src="/mag/js/kindeditor.js"></script>
<script type="text/javascript" charset="utf-8" src="/mag/js/zh_CN.js"></script>
<script type="text/javascript" src="/mag/js/layout.js"></script>


<script type="text/javascript">
    var img;
    
$(document).ready(function(){
        var txtPic = $("#url3").val();
        if (txtPic == "" || txtPic == null) 
        {
            $(".ImgView").hide();
        }
        else 
        {
            $("#ImgView").attr("src" , $("#url3").val())
            $(".ImgView").show();
        }

        $("#url3").change(function()
        {
          $("#ImgView").attr("src" , $("#url3").val())
        });
});

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
            <dd><a href="/activity/create">创建活动</a></dd>
            <dd><a href="/activity/list">活动列表</a></dd>
		</dl>
	</div>
<!--right-->

    <form id = "form1"  action="/activity/invest/submit" method="post">
    <input type="hidden" name="enterpriseId" value="${enterprise.id?c}" />
    <input type="hidden" name="expertId" value="${expert.id?c}" />
    <input type="hidden" name="activityId" value="${activityId?c}" />
    <div class="right_content">
        <div class="right_box">
        	<dl class="crumb">
            	<dt><a href="javascript:void(0)"></a></dt>
                <dd>
                	<p>当前所在位置:</p>
                    <a href="javascript:void(0)">分配辅导投资</a>
                </dd>
            </dl>
            <dl class="team_title02">
                <dt>辅导企业：</dt>
                <dd style="line-height: 30px; font-size: 14px;">
					<span>${enterprise.title!''}</span>                   
                </dd>
            </dl>
            <dl class="team_title02">
                <dt>投资机构：</dt>
                <dd style="line-height: 30px; font-size: 14px;">
                    ${expert.invest!''}
                </dd>
            </dl>   
             <dl class="team_title02">
                <dt>辅导专家：</dt>
                <dd style="line-height: 30px; font-size: 14px;">
                    ${expert.name!''}
                </dd>
            </dl> 
             <dl class="team_title03">
                <dt style="font-size:14px;float:left">投融资价值：</dt>
                <dd style="line-height: 30px; font-size: 14px;">
                    <input  type="text" name="amount" style="width:64px" value="<#if invest??>${invest.amount!''}</#if>" />万元
                </dd>
            </dl>  
                        

            <dl class="team_title03" style="float:left;margin-top:20px;">
                <dt style="font-size:14px;float:left;">详情：&nbsp;&nbsp;</dt>
                <dd>
                    <textarea  name="datail" class="editor" style="width:400px;height:200px;"><#if invest??&&invest.datail??>${invest.datail!''}</#if></textarea>
                </dd>
            </dl>    
        </div>    
        <input  style="cursor:pointer; width:80px; height: 30px; border:none; background: #e67817; font-size: 14px; color: white; border-radius: 6px;margin-left: 30px; margin-top: 50px;" type="submit" value="提交"/>
    </div>
    </form>

 
</div><!--content_end-->
</div><!--main-->
</body>
</html>
