<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/mag/style/idialog.css" rel="stylesheet" id="lhgdialoglink">
<title>编辑物流配送</title>
<script type="text/javascript" src="/mag/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="/mag/js/Validform_v5.3.2_min.js"></script>
<script type="text/javascript" src="/mag/js/lhgdialog.js"></script>
<script type="text/javascript" src="/mag/js/swfupload.js"></script>
<script type="text/javascript" src="/mag/js/swfupload.queue.js"></script>
<script type="text/javascript" src="/mag/js/swfupload.handlers.js"></script>
<script type="text/javascript" src="/mag/js/layout.js"></script>
<link href="/mag/style/style.css" rel="stylesheet" type="text/css">
<script type="text/javascript">
$(function () {
    //初始化表单验证
    $("#form1").initValidform();
    
    //初始化上传控件
    $(".upload-img").each(function () {
        $(this).InitSWFUpload({ 
            sendurl: "/Verwalter/upload", 
            flashurl: "/mag/js/swfupload.swf"
        });
    });
    
    //（缩略图）
    var txtPic = $("#txtImgUrl").val();
    if (txtPic == "" || txtPic == null) {
        $(".thumb_ImgUrl_show").hide();
    }
    else {
        $(".thumb_ImgUrl_show").html("<ul><li><div class='img-box1'><img src='" + txtPic + "' bigsrc='" + txtPic + "' /></div></li></ul>");
        $(".thumb_ImgUrl_show").show();
    }
    
    $(".upload-show360").each(function () {
        $(this).InitSWFUpload_show360({ 
            btntext: "批量上传", 
            btnwidth: 66, 
            single: false, 
            water: true, 
            thumbnail: true, 
            filesize: "5120", 
            sendurl: "/Verwalter/upload", 
            flashurl: "/mag/js/swfupload.swf", 
            filetypes: "*.jpg;*.jpge;*.png;*.gif;" 
        });
    });
});


     $(function(){  
        $("#roleId").change(function(){      
        var select_role = $(this).children('option:selected').val();  
        if(select_role == '2'){  
        	$(".region").css("display","block");
         }else{  
         	$(".region").css("display","none");
         }  
        
     });  
   });  
 

</script>
</head>

<body class="mainbody">
<form name="form1" method="post" action="/Verwalter/order/setting/diysite/save" id="form1">
<div>
<input type="hidden" name="__VIEWSTATE" id="__VIEWSTATE" value="${__VIEWSTATE!""}">
<input name="diySiteId" type="text" value='<#if diy_site??>${diy_site.id}</#if>' style="display:none">
</div>

<!--导航栏-->
<div class="location">
  <a href="/Verwalter/order/setting/diysite/list" class="back"><i></i><span>返回列表页</span></a>
  <a href="/Verwalter/center" class="home"><i></i><span>首页</span></a>
  <i class="arrow"></i>
  <a href="/Verwalter/order/setting/diysite/list"><span>角色</span></a>
  <i class="arrow"></i>
  <span>编辑角色</span>
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
  <#--><dl>
    <dt>角色名称</dt>
    <dd>
        <input name="title" type="text" value="<#if diy_site??>${diy_site.title!""}</#if>" class="input normal" datatype="*2-100" sucmsg=" "> 
        <span class="Validform_checktip">*同盟店名称</span>
    </dd>
  </dl>-->
  <dl>
      <dt>角色类别</dt>
      <dd>
           <div class="rule-single-select">
                <select id="roleId" name="roleId" datatype="*" sucmsg=" ">
                     <option value="" <#if !diy_site?? || !diy_site.roleId??>selected="selected"</#if>>请选择...</option>
                     <option value="2" <#if diy_site?? && diy_site.roleId?? && diy_site.roleId==2>selected="selected"</#if>>区县管理</option> 
                     <option value="3" <#if diy_site?? && diy_site.roleId?? && diy_site.roleId==3>selected="selected"</#if>>专家</option>
                     <option value="4" <#if diy_site?? && diy_site.roleId?? && diy_site.roleId==4>selected="selected"</#if>>活动管理员</option>                   
                </select>
           </div>
       </dd>
  </dl>
    <dl  class="region" <#if diy_site?? && !diy_site.region?? || !diy_site?? || diy_site?? &&diy_site.region="">style= "display:none;"</#if>>
      <dt>区县</dt>
      <dd>
           <div class="rule-single-select">
                <select id="region" name="region"  sucmsg=" "  >
               
                     <option value="" <#if !diy_site?? || !diy_site.region??>selected="selected"</#if>>请选择...</option>

                     <#if region_list??>
                     	<#list region_list as item>
                     		<option value="${item.title!''}" <#if diy_site?? && diy_site.region?? && diy_site.region==item.title>selected="selected"</#if>>${item.title!''}</option> 
          				</#list>
          			</#if>            
                </select>
           </div>
       </dd>
  </dl>
  <dl>
    <dt>登录名</dt>
    <dd>
        <input name="username" type="text" value="<#if diy_site??>${diy_site.username!""}</#if>" class="input normal" <#if !diy_site??>ajaxurl="/Verwalter/order/setting/diysite/check<#if diy_site??>?id=${diy_site.id}</#if>"</#if> datatype="*6-100" sucmsg=" "> 
        <span class="Validform_checktip">*登录账号</span>
    </dd>
  </dl>
  <dl>
    <dt>登录密码</dt>
    <dd>
        <input name="password" type="password" value="<#if diy_site??>${diy_site.password!""}</#if>" class="input normal" datatype="*6-20" sucmsg=" "> 
        <span class="Validform_checktip">*</span>
    </dd>
  </dl>
  <dl>
    <dt>重复密码</dt>
    <dd>
        <input type="password" value="<#if diy_site??>${diy_site.password!""}</#if>" class="input normal" datatype="*" recheck="password" sucmsg=" "> 
        <span class="Validform_checktip">*</span>
    </dd>
  </dl>
  <dl>
    <dt>手机号</dt>
    <dd>
        <input name="mobile" type="text" value="<#if diy_site??>${diy_site.mobile!""}</#if>" class="input normal" datatype="m" sucmsg=" "> 
        <span class="Validform_checktip">*用于接收通知短信</span>
    </dd>
  </dl>
  <dl>
    <dt>邮箱</dt>
    <dd>
        <input name="email" type="text" value="<#if diy_site??>${diy_site.email!""}</#if>" class="input normal" datatype="*" sucmsg=" "> 
        <span class="Validform_checktip">*用于接收通知邮件</span>
    </dd>
  </dl>
  
    <dl>
    <dt>负责人</dt>
    <dd>
        <input name="inCharge" type="text" value="<#if diy_site??>${diy_site.inCharge!""}</#if>" class="input normal" datatype="*" sucmsg=" "> 
    </dd>
  </dl>
  
  <dl>
    <dt>是否启用</dt>
    <dd>
      <div class="rule-multi-radio multi-radio">
        <span id="rblStatus" style="display: none;">
            <input type="radio" name="statusId" value="1" <#if !diy_site?? || diy_site?? && diy_site.statusId?? && diy_site.statusId == 1>checked="checked"</#if>>
            <label>是</label>

            <input type="radio" name="statusId" value="0" <#if diy_site?? && diy_site.statusId?? && diy_site.statusId == 0>checked="checked"</#if>>
            <label>否</label>
        </span>
      </div>
      <span class="Validform_checktip">*不启用则不该角色不可用</span>
    </dd>
  </dl>
  <dl>
    <dt>排序数字</dt>
    <dd>
      <input name="sortId" type="text" value="<#if diy_site??>${diy_site.sortId!""}<#else>99</#if>" class="input small" datatype="n" sucmsg=" ">
      <span class="Validform_checktip">*数字，越小越向前</span>
    </dd>
  </dl>
  
  <dl>
    <dt>描述说明</dt>
    <dd>
      <textarea name="info" rows="2" cols="20" class="input normal"><#if diy_site??>${diy_site.info!""}</#if></textarea>
      <span class="Validform_checktip"></span>
    </dd>
  </dl>
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