<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/mag/style/idialog.css" rel="stylesheet" id="lhgdialoglink">
<title>编辑活动地区</title>
<script type="text/javascript" src="/mag/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="/mag/js/Validform_v5.3.2_min.js"></script>
<script type="text/javascript" src="/mag/js/lhgdialog.js"></script>
<script type="text/javascript" src="/mag/js/layout.js"></script>
<script type="text/javascript" src="/mag/js/swfupload.js"></script>
<script type="text/javascript" src="/mag/js/swfupload.queue.js"></script>
<script type="text/javascript" src="/mag/js/swfupload.handlers.js"></script>
<link href="/mag/style/style.css" rel="stylesheet" type="text/css">
<script type="text/javascript">
    $(function () {
        //初始化表单验证
        $("#form1").initValidform();




        //（缩略图）
        var txtPic = $("#txtImgUrl").val();
        if (txtPic == "" || txtPic == null) {
            $(".thumb_ImgUrl_show").hide();
        }
        else {
            $(".thumb_ImgUrl_show").html("<ul><li><div class='img-box1'><img src='" + txtPic + "' bigsrc='" + txtPic + "' /></div></li></ul>");
            $(".thumb_ImgUrl_show").show();
        }

        $("#txtImgUrl").blur(function () {
            var txtPic = $("#txtImgUrl").val();
            if (txtPic == "" || txtPic == null) {
                $(".thumb_ImgUrl_show").hide();
            }
            else {
                $(".thumb_ImgUrl_show").html("<ul><li><div class='img-box1'><img src='" + txtPic + "' bigsrc='" + txtPic + "' /></div></li></ul>");
                $(".thumb_ImgUrl_show").show();
            }
        });
        
        //设置封面图片的样式
        $(".photo-list ul li .img-box img").each(function () {
            if ($(this).attr("src") == $("#hidFocusPhoto").val()) {
                $(this).parent().addClass("selected");
            }
        });
    });
</script>
</head>

<body class="mainbody">
<form name="form1" method="post" action="/Verwalter/setting/activityType/save" id="form1">
<div>
<input type="hidden" name="__VIEWSTATE" id="__VIEWSTATE" value="${__VIEWSTATE!""}">
<input name="serviceItemId" type="text" value='<#if activityType??>${activityType.id}</#if>' style="display:none">
<input type="hidden" name="id"  value="<#if activityType??>${activityType.id}</#if>">
</div>

<!--导航栏-->
<div class="location">
  <a href="/Verwalter/setting/activityType/list" class="back"><i></i><span>返回列表页</span></a>
  <a href="/Verwalter/center" class="home"><i></i><span>首页</span></a>
  <i class="arrow"></i>
  <a href="/Verwalter/setting/activityType/list"><span>活动地区</span></a>
  <i class="arrow"></i>
  <span>编辑活动地区</span>
</div>
<div class="line10"></div>
<!--/导航栏-->

<!--内容-->
<div class="content-tab-wrap">
  <div id="floatHead" class="content-tab">
    <div class="content-tab-ul-wrap">
      <ul>
        <li><a href="javascript:;" onclick="tabs(this);" class="selected">编辑信息</a></li>
      </ul>
    </div>
  </div>
</div>

<div class="tab-content">
  <dl>
    <dt>地区名称</dt>
    <dd>
        <input name="title" type="text" value="<#if activityType??>${activityType.title!""}</#if>" class="input normal" datatype="*1-255" sucmsg=" "> 
        <span class="Validform_checktip">*名称</span>
    </dd>
  </dl>
  <dl>
    <dt>是否启用</dt>
    <dd>
      <div class="rule-multi-radio multi-radio">
        <span id="rblStatus" style="display: none;">
            <input type="radio" name="isEnable" value="1" <#if !activityType?? || activityType.isEnable?? && activityType.isEnable>checked="checked"</#if>>
            <label>是</label>
            <input type="radio" name="isEnable" value="0" <#if activityType?? && (!activityType.isEnable?? || !activityType.isEnable)>checked="checked"</#if>>
            <label>否</label>
      </div>
      <span class="Validform_checktip">*不启用则不显示</span>
    </dd>
  </dl>
  <dl>
    <dt>排序数字</dt>
    <dd>
      <input name="sortId" type="text" value="<#if activityType??>${activityType.sortId!""}<#else>99</#if>" class="input small" datatype="n" sucmsg=" ">
      <span class="Validform_checktip">*数字，越小越向前</span>
    </dd>
  </dl>
   <dl>
       <dt>URL链接</dt>
       <dd>
           <input name="linkUrl" type="text" value="<#if article??>${article.linkUrl!""}</#if>" maxlength="255" id="txtLinkUrl" class="input normal">
           <span class="Validform_checktip">填写后直接跳转到该网址</span>
       </dd>
   </dl>
   <dl>
       <dt>封面图片</dt>
       <dd>
           <input name="imgUrl" type="text" id="txtImgUrl" value="<#if article??>${article.imgUrl!""}</#if>" class="input normal upload-path">
           <div class="upload-box upload-img"></div>
           <div class="photo-list thumb_ImgUrl_show" style="display: none;">
               <ul>
                   <li>
                       <div class="img-box1"></div>
                   </li>
               </ul>
           </div>
       </dd>
   </dl>  
   <dl>
       <dt>描述内容</dt>
       <dd>
           <textarea name="content" rows="2" cols="20" id="txtZhaiyao" class="input" datatype="*0-255" sucmsg=" "><#if activityType??>${activityType.content!""}</#if></textarea>
       </dd>
   </dl>  
  
  <#--
 	<dl>
	    <dt>颜色</dt>
	    <dd>
	        <div class="rule-single-select">
	            <select name="color" id="ddlCategoryId" datatype="*" sucmsg=" " nullmsg="请选择！" class="Validform_error" style="display: none;">
	            	<#if activityType??>
	            	<#else>
	            	<option value="">请选择类别...</option>
	            	</#if>
	                <option value="#e67817" <#if activityType?? && activityType.color=="#e67817">selected="selected"</#if>><b style="color:#e67817;">橙色</b></option>
					<option value="#529c15" <#if activityType?? && activityType.color=="#529c15">selected="selected"</#if>><b style="color:#529c15;">绿色</b></option>
				    <option value="#FF4454" <#if activityType?? && activityType.color=="#FF4454">selected="selected"</#if>><b style="color:#FF4454;">红色</b></option>
				    <option value="#111111"<#if activityType?? && activityType.color=="#111111">selected="selected"</#if>><b style="color:#111111;">黑色</b></option>
				    <option value="#0080FF"<#if activityType?? && activityType.color=="#0080FF">selected="selected"</#if>><b style="color:#0080FF;">蓝色</b></option>
				    <option value="#FFFF00 "<#if activityType?? && activityType.color=="#FFFF00">selected="selected"</#if>><b style="color:#FFFF00 ;">黄色</b></option>
	            </select>
	        </div>
	        <#if activityType??>
	            <span class="Validform_checktip Validform_right"></span>
	        <#else>
	            <span class="Validform_checktip Validform_wrong">请选择！</span>
	        </#if>
	    </dd>
	</dl>   
	-->
</div>
<!--/内容-->


<!--工具栏-->
<div class="page-footer">
  <div class="btn-list">
    <input type="submit" name="btnSubmit" value="提交保存" id="btnSubmit" class="btn">
    <input name="btnReturn" type="button" value="返回上一页" class="btn yellow" onclick="javascript:history.back(-1);">
  </div>
  <div class="clear"></div>
</div>
<!--/工具栏-->
</form>


</body></html>