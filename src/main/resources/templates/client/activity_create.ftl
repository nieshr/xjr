<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>活动信息</title>
<link rel="shortcut icon" href="/client/images/icon.ico" />
<link href="/client/css/base.css" rel="stylesheet" type="text/css" />
<link href="/client/css/active.css" rel="stylesheet" type="text/css" />
<link href="/mag/style/WdatePicker.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="/client/css/showBo.css">
<link rel="stylesheet" href="/client/css/ueditor.css">

<script src="/client/js/jquery-1.9.1.min.js"></script>
<script src="/client/js/main.js"></script>
<script type="text/javascript" src="/mag/js/WdatePicker.js"></script>
<script type="text/javascript" src="/mag/js/swfupload.js"></script>
<script type="text/javascript" src="/mag/js/swfupload.queue.js"></script>
<script type="text/javascript" src="/mag/js/swfupload.handlers.js"></script>
<script type="text/javascript" charset="utf-8" src="/mag/js/kindeditor-min.js"></script>
<script type="text/javascript" charset="utf-8" src="/mag/js/zh_CN.js"></script>
<script type="text/javascript" src="/client/js/Validform_v5.3.2_min.js"></script>
<script type="text/javascript" src="/mag/js/layout.js"></script>
<script src="/client/js/showBo.js"></script>

<script>
$(document).ready(function(){

    $("#form1").Validform({ 
            tiptype:4,
            ajaxPost:true,
            callback: function (data) { 
                if (data.code == 0)
                {
                    location.href="/activity/list";
                }
                else 
                {
                     Showbo.Msg.alert(data.msg);
                }
             }
    });
    
    <#if upload??&&upload == 1>
        var height = $(document.body).height();
       $('html,body').animate({scrollTop:height+'px'},100);
    </#if>    
});

$(function(){
    $('#selectEnterprise').click(function(){
    var activitydate = $("#date").val()
    var  prepareOn = $("#prepareOn").val()
    var  prepareOff = $("#prepareOff").val()
    var  eventEnd  = $("#eventEnd").val()
    if (activitydate == ""||prepareOn == ""||prepareOff == "" || eventEnd == "")
    {
    	 Showbo.Msg.alert("请填写完整资料");
    }
    else{    
         $.ajax({
             type: "GET",
             url: "/activity/bufferEn",
             contentType: "application/json; charset=utf-8",
             data: {id:$("#id").val(), 
            	   title:$("#title").val(), 
            	   activityType:$("#activityType").val(),
            	   region:$("#region").val(),
            	   date:$("#date").val(),
            	   address:$("#address").val(),
            	   theme:$("#theme").val(),
            	   introduction:$("#introduction").val(),
            	   prepareOn:$("#prepareOn").val(),
            	   prepareOff:$("#prepareOff").val(),
            	   eventEnd:$("#eventEnd").val()},
             dataType: "json",
             success: function(data){
		                 if (data.code == 0)
		                 {
		                     location.href="/activity/selectEnterprise?activityId="+data.activityId+"&area="+$("#region").val();
		                 }
		                 else 
		                 {
		                      Showbo.Msg.alert(data.msg);
		                 }
                      }
         });
     }    
    });
});

$(function(){
    $('#selectExpert').click(function(){
    var activitydate = $("#date").val()
    var  prepareOn = $("#prepareOn").val()
    var  prepareOff = $("#prepareOff").val()
    var  eventEnd  = $("#eventEnd").val()
    if (activitydate == ""||prepareOn == ""||prepareOff == "" || eventEnd == "")
    {
         Showbo.Msg.alert("请填写完整资料");
    }
    else{
         $.ajax({
             type: "GET",
             url: "/activity/bufferEn",
             contentType: "application/json; charset=utf-8",
             data: {id:$("#id").val(), 
                   title:$("#title").val(), 
                   activityType:$("#activityType").val(),
                   region:$("#region").val(),
                   date:$("#date").val(),
                   address:$("#address").val(),
                   theme:$("#theme").val(),
                   introduction:$("#introduction").val(),
                   prepareOn:$("#prepareOn").val(),
                   prepareOff:$("#prepareOff").val(),
                   eventEnd:$("#eventEnd").val()},
             dataType: "json",
             success: function(data){
                         if (data.code == 0)
                         {
                             location.href="/activity/selectExpert?activityId="+data.activityId;
                         }
                         else 
                         {
                              Showbo.Msg.alert(data.msg);
                         }
                      }
         });
    } 
    });
});

function activityPass(activityId)
{
         $.ajax({
             type: "GET",
             url: "/activity/pass",
             contentType: "application/json; charset=utf-8",
             data: {"activityId" : activityId},
             dataType: "json",
             success: function(data){
                         if (data.code == 0)
                         {
                              Showbo.Msg.alert("审核成功");
                         }
                         else 
                         {
                              Showbo.Msg.alert(data.msg);
                         }
                      }
         });
}

function sendSms(id,activityId,roleId)
{
         $.ajax({
             type: "GET",
             url: "/activity/sendSms",
             contentType: "application/json; charset=utf-8",
             data: {"id":id,"activityId" : activityId,"roleId":roleId},
             dataType: "json",
             success: function(data){
                         if (data.code == 0)
                         {
                              Showbo.Msg.alert("发送成功！");
                         }
                         else 
                         {
                              Showbo.Msg.alert(data.msg);
                         }
                      }
         });
}

function passCheck(activityId) {
    if (confirm("点击将更新列表到评分表（需等待3秒）。确认吗？")) {
         $.ajax({
             type: "GET",
             url: "/activity/pass",
             contentType: "application/json; charset=utf-8",
             data: {"activityId" : activityId},
             dataType: "json",
             success: function(data){
                         if (data.code == 0)
                         {
                         	alert("更新成功！");
                             location.reload();
                         }
                         else 
                         {
                             alert(data.msg);
                         }
                      }
         });
    }
}

function cancelCheck(activityId) {
    if (confirm("取消审核后将可以修改活动信息，确认吗？")) {
         $.ajax({
             type: "GET",
             url: "/activity/cancel",
             contentType: "application/json; charset=utf-8",
             data: {"activityId" : activityId},
             dataType: "json",
             success: function(data){
                         if (data.code == 0)
                         {
                         	alert("取消成功！");
                             location.reload();
                         }
                         else 
                         {
                             alert(data.msg);
                         }
                      }
         });
    }
}


function sortUp(id , activityId)
{
         $.ajax({
             type: "GET",
             url: "/activity/sortUp",
             contentType: "application/json; charset=utf-8",
             data: {"id":id ,"activityId" : activityId},
             dataType: "json",
             success: function(data){
                         if (data.code == 0)
                         {
                             location.reload();
                         }
                         else 
                         {
                             alert(data.msg);
                         }
                      }
         });
}

function sortDown(id , activityId)
{
         $.ajax({
             type: "GET",
             url: "/activity/sortDown",
             contentType: "application/json; charset=utf-8",
             data: {"id":id ,"activityId" : activityId},
             dataType: "json",
             success: function(data){
                         if (data.code == 0)
                         {
                             location.reload();
                         }
                         else 
                         {
                             alert(data.msg);
                         }
                      }
         });
}


function submitCheck()
{
    var filedata = $("#file").val();

    if (filedata == "")
    {
         Showbo.Msg.alert("请添加文件！");
    }
    else{
        $("#upload").submit();
    }   
}

function pptSubmitCheck()
{
    var filedata = $("#pptfile").val();

    if (filedata == "")
    {
         Showbo.Msg.alert("请添加文件！");
    }
    else{
        $("#pptupload").submit();
    }   
}


function enterpptSubmitCheck()
{
    var filedata = $("#enterpptfile").val();

    if (filedata == "")
    {
        Showbo.Msg.alert("请添加文件！");
    }
    else{
        $("#enterpptupload").submit();
    }   
}

function done()
{
    Showbo.Msg.alert("上传成功！");
}
function done2()
{
    Showbo.Msg.alert("请添加类型为*.PPT文件！");
}
<#if done?? &&done == 1>
window.onload=done;
<#elseif done?? &&done == 2>
window.onload=done2;
</#if>

</script>
<style>
.Validform_wrong {  background-position: 20px center;}
.Validform_right {  background-position: 7px center;}
</style>
</head>
<body>

<#-- 百度编辑器 begin -->
    <!-- 加载编辑器的容器 -->

    <!-- 配置文件 -->
    <script type="text/javascript" src="/client/js/ueditor.config.js"></script>
    <!-- 编辑器源码文件 -->
    <script type="text/javascript" src="/client/js/ueditor.all.js"></script>
    <!-- 实例化编辑器 -->
    <script type="text/javascript">
		var ue = UE.getEditor('container', {
			toolbars: 
			[
			    [
			        'anchor', //锚点
			        'undo', //撤销
			        'redo', //重做
			        'bold', //加粗
			        'indent', //首行缩进
			        'snapscreen', //截图
			        'italic', //斜体
			        'underline', //下划线
			        'strikethrough', //删除线
			        'subscript', //下标
			        'fontborder', //字符边框
			        'superscript', //上标
			        'formatmatch', //格式刷
			        'source', //源代码
			        'blockquote', //引用
			        'pasteplain', //纯文本粘贴模式
			        'selectall', //全选
			        'print', //打印
			        'preview', //预览
			        'horizontal', //分隔线
			        'removeformat', //清除格式
			        'time', //时间
			        'date', //日期
			        'unlink', //取消链接
			        'insertrow', //前插入行
			        'insertcol', //前插入列
			        'mergeright', //右合并单元格
			        'mergedown', //下合并单元格
			        'deleterow', //删除行
			        'deletecol', //删除列
			        'splittorows', //拆分成行
			        'splittocols', //拆分成列
			        'splittocells', //完全拆分单元格
			        'deletecaption', //删除表格标题
			        'inserttitle', //插入标题
			        'mergecells', //合并多个单元格
			        'deletetable', //删除表格
			        'cleardoc', //清空文档
			        'insertparagraphbeforetable', //"表格前插入行"
			        'insertcode', //代码语言
			        'fontfamily', //字体
			        'fontsize', //字号
			        'paragraph', //段落格式
			        'simpleupload', //单图上传
			        'insertimage', //多图上传
			        'edittable', //表格属性
			        'edittd', //单元格属性
			        'link', //超链接
			        'emotion', //表情
			        'spechars', //特殊字符
			        'searchreplace', //查询替换
			        'map', //Baidu地图
			        'gmap', //Google地图
			        'insertvideo', //视频
			        'help', //帮助
			        'justifyleft', //居左对齐
			        'justifyright', //居右对齐
			        'justifycenter', //居中对齐
			        'justifyjustify', //两端对齐
			        'forecolor', //字体颜色
			        'backcolor', //背景色
			        'insertorderedlist', //有序列表
			        'insertunorderedlist', //无序列表
			        <#--'fullscreen', //全屏-->
			        'directionalityltr', //从左向右输入
			        'directionalityrtl', //从右向左输入
			        'rowspacingtop', //段前距
			        'rowspacingbottom', //段后距
			        'pagebreak', //分页
			        'insertframe', //插入Iframe
			        'imagenone', //默认
			        'imageleft', //左浮动
			        'imageright', //右浮动
			        'attachment', //附件
			        'imagecenter', //居中
			        'wordimage', //图片转存
			        'lineheight', //行间距
			        'edittip ', //编辑提示
			        'customstyle', //自定义标题
			        'autotypeset', //自动排版
			        'webapp', //百度应用
			        'touppercase', //字母大写
			        'tolowercase', //字母小写
			        'background', //背景
			        'template', //模板
			        'scrawl', //涂鸦
			        'music', //音乐
			        'inserttable', //插入表格
			        'drafts', // 从草稿箱加载
			        'charts', // 图表
			    ]
			],
		    autoHeightEnabled: true,
		    autoFloatEnabled: true,
		    initialFrameWidth: 750, 
			initialFrameHeight: 300,
			
			//检验后端
		    "imageUrl": "/Verwalter/baidu/upload?action=uploadimage",
		    "imagePath": "src/main/resources/static/images/",
		    "imageFieldName": "imgFile",
		    "imageMaxSize": 2048,
		    "imageAllowFiles": [".png", ".jpg", ".jpeg", ".gif", ".bmp"]
		    
		});
    </script>

<#-- 百度编辑器 end-->


<!--main-->
<div class="main">
<!--header-->
<#include "/client/user_common_header.ftl" />
<!--header_end-->
<!--content-->
<div class="content">
<!--left-->
	<div class="leftbar">
	   <#if mark?? && mark == "region">
		   <dl class="nav">
	            <dd><a href="/region/enterprise/list">企业列表</a></dd>
	            <dd><a href="/region/activity/list">活动列表</a></dd>
	             <#--<dd><a href="#">档案跟踪</a></dd>-->
	        </dl>
	   <#elseif mark?? && mark == "expert">
			<dl class="nav">
            <dd><a href="/expert/enterprise/list">活动列表</a></dd>
            <dd><a href="/expert/enterprises">辅导企业</a></dd>
            <dd><a href="/expert/lyfd">路演辅导</a></dd>
			</dl>	   
        <#elseif mark?? && mark == "enterprise">
          <dl class="nav">
        <dd><a href="/enterprise/info">网上报名</a></dd>
        <dd><a href="/enterprise/data">项目资料</a></dd>
        <dd><a href="/enterprise/activity/list">活动列表</a></dd>
        <dd><a href="/enterprise/project">申请展示</a></dd>
          </dl>   	     
	   <#else>
			<dl class="nav">
	            <dd><a href="/activity/create">创建活动</a></dd>
	            <dd><a href="/activity/list">活动列表</a></dd>
			</dl>
		</#if>
	</div>
<!--right-->
    <div class="right_content">

    <div class="right_box">
    	<dl class="crumb">
        	<dt><a href="#"></a></dt>
            <dd>
            	<p>当前所在位置:</p>
            	<#if mark?? && (mark == "region"||mark=="enterprise")>
            	   <a href="#">查看活动</a>
            	<#else>
                <a href="#">创建活动</a>
                <p>&gt;</p>
                <a href="#">资料填写</a>
                </#if>
            </dd>
            <dt class="crumb_back"><a  href="javascript:history.go(-1);">返回上一页</a></dt>
        </dl>
        
    </div>  
    <form action="/activity/submit" id="form1">
    <dl class="active_content">
    	<dd>
            <#if activity??>
                <input type="hidden" name="id" id="id" value="${activity.id?c!''}"/>
                <input type="hidden" name="statusEn" id="statusEn" value="${activity.statusEn!''}"/>
                <input type="hidden" name="statusEx" id="statusEx" value="${activity.statusEx!''}"/>
                <input type="hidden" name="fileUrl" id="fileUrl" value="<#if activity.fileUrl??>${activity.fileUrl!''}</#if>"/>
            
                <#if activity.statusId??>
                    <#if activity.statusId==1>
                        <div class=" new_new"><h3 style="margin:0 0 10px 50px;">活动状态：已审核</h3></div>
                    <#elseif activity.statusId==2>
                        <div class=" new_new"><h3 style="margin:0 0 10px 50px;">活动状态：已结束</h3></div>
                    <#elseif activity.statusId==0>
                        <div class=" new_new"><h3 style="margin:0 0 10px 50px;">活动状态：筹备中</h3></div>
                    </#if>       
                </#if>    
            </#if>         
    			<div class=" new_new"><span>活动名称：</span><input <#if pagetype??&& pagetype == "check">disabled=""</#if> type="text" name="title" id="title" datatype="*"value="<#if activity??>${activity.title!''}</#if>" /></div>
    			<div class=" new_new">
    				<span>活动类型：</span>
    				<select name="activityType" id="activityType" <#if pagetype??&& pagetype == "check">disabled=""</#if>>
    				    <#if activityType_list??>
    				        <#list activityType_list as item>
    					        <option value="${item.title!''}" <#if activity??&&activity.activityType?? &&activity.activityType == item.title>selected="selected"</#if> <#if pagetype??&& pagetype == "check">disabled=""</#if>>${item.title!''}</option>
    					    </#list>
    					</#if>        
    				</select>
    			</div>
    			<div class=" new_new">
    			    <span>地区 ：</span>
                    <select name="region" id="region" <#if pagetype??&& pagetype == "check">disabled=""</#if>>
                        <#if region_list??>
                            <#list region_list as item>
                                <option value="${item.title!''}" <#if activity?? &&activity.region == item.title>selected="selected"</#if>  <#if pagetype??&& pagetype == "check">disabled=""</#if>>${item.title!''}</option>
                            </#list>
                        </#if>        
                    </select>
    			</div>
    			<div class=" new_new"><span>时间：</span>
	                    <input <#if pagetype??&& pagetype == "check" >disabled=""</#if> name="date" type="text" id="date" value="<#if activity??>${activity.date!""}</#if>" class="input date" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',lang:'zh-cn'})" datatype="/^\s*$|^\d{4}\-\d{1,2}\-\d{1,2}\s{1}(\d{1,2}:){2}\d{1,2}$/" errormsg="填写正确格式" sucmsg=" " >
	            </div>    
    			
    			<div class=" new_new"><span>地址：</span><input <#if pagetype??&& pagetype == "check" >disabled=""</#if> type="text" name="address" id="address" datatype="*" value="<#if activity??>${activity.address!''}</#if>" /></div>
    			<div class=" new_new"><span>主题：</span><textarea style="height: 56px;" cols="3" rows="3" <#if pagetype??&& pagetype == "check" >disabled=""</#if> type="text" name="theme" id="theme" datatype="*1-255" errormsg="最多255字！"><#if activity??>${activity.theme!''}</#if></textarea></div>
				<div class="editer">
					
					<#if mark??&&mark=="activity">
					<span style="margin-left:78px;margin-top:20px;display:block;float:left;  font-size: 14px;">简介：</span>
					<#--
					<textarea  <#if pagetype??&& pagetype == "check" >disabled=""</#if> name="introduction" id="introduction" datatype="*" ><#if activity??>${activity.introduction!''}</#if></textarea>
					-->
    					    <script id="container" name="introduction" type="text/plain">
				        这里写你的初始化内容
				    </script>
					<#else>
					<span style="line-height:24px;">简介：</span>
					<div style="float:left; display: 
										block; width: 750px; 
										margin-top: 0;  
										font-size: 14px;
										color: #666666;
										text-indent: 32px;
										line-height: 24px;">
						${activity.introduction!''}
					</div>
					</#if>
				</div>    		
				
    			<div class=" new_new">
    				<span>筹备开始时刻：</span>
                        <input <#if pagetype??&& pagetype == "check">disabled=""</#if> name="prepareOn" id="prepareOn" type="text"  value="<#if activity??>${activity.prepareOn!""}</#if>" class="input date" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',lang:'zh-cn'})" datatype="/^\s*$|^\d{4}\-\d{1,2}\-\d{1,2}\s{1}(\d{1,2}:){2}\d{1,2}$/" errormsg="填写正确格式" sucmsg=" ">
    				<span>筹备结束时刻：</span>
                        <input <#if pagetype??&& pagetype == "check" >disabled=""</#if> name="prepareOff" id="prepareOff" type="text" value="<#if activity??>${activity.prepareOff!""}</#if>" class="input date" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',lang:'zh-cn'})" datatype="/^\s*$|^\d{4}\-\d{1,2}\-\d{1,2}\s{1}(\d{1,2}:){2}\d{1,2}$/" errormsg="填写正确格式" sucmsg=" ">
    			</div>
    			<div class=" new_new">
    			    <span>活动结束时刻：</span>
                    <input <#if pagetype??&& pagetype == "check" >disabled=""</#if> name="eventEnd" id="eventEnd" type="text" value="<#if activity??>${activity.eventEnd!""}</#if>" class="input date" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',lang:'zh-cn'})" datatype="/^\s*$|^\d{4}\-\d{1,2}\-\d{1,2}\s{1}(\d{1,2}:){2}\d{1,2}$/" errormsg="填写正确格式" sucmsg=" ">
    			</div>
    			<#--
                <#if activityType_list??>
                    <#list activityType_list as type>
                        <#if (type_index == 0||type_index ==1)&&type.title == activity.activityType>    -->			
		    			<div class=" new_new">
		    				<span style="margin-top: 6px;">初选项目：</span>
		    				<ul class="active_project_text">
		    				    <#if selected_enterprise_list??>
		    				        <#list selected_enterprise_list as item>
				    					<li>
				    						<p class="p01"  style="width: 470px; float: left;text-align:left;"><a style="color:#000;" <#if mark??&&(mark=="activity"||mark="region"||mark=="expert")>target=_blank</#if>  title="查看项目详情" href="<#if mark??&&(mark=="activity"||mark="region"||mark=="expert")>/activity/enterprise/check/${item.enterpriseId?c!''}<#else>javascript:void(0)</#if>">${item_index+1}.${item.enterpriseTitle!''}</a></p>
				    						<#if mark??&&(mark=="activity"||mark="region"||mark=="expert")>
					    						<a href="/activity/enterprise/check/${item.enterpriseId?c!''}" target=_blank>查看</a>
					    						<#if item.pptUrl??&&item.pptUrl?length gt 0>
		                                            <a>丨</a>
		                                            <a href="/download/data?name=${item.pptUrl!''} " title="点击下载文件">PPT</a>
		                                        <#else>
		                                            <a>丨</a>
		                                            <a href="javascript:void(0) " style="color:#666;" title="无资料">PPT</a>
		                                        </#if>                                       
		                                        <#if item.enterpriseFileUrl??&&item.enterpriseFileUrl?length gt 0>
		                                            <a>丨</a>
		                                            <a href="/download/data?name=${item.enterpriseFileUrl!''} " title="点击下载文件">扫描件</a>
		                                        <#else>
		                                            <a>丨</a>
		                                            <a href="javascript:void(0) " style="color:#666;" title="无资料">扫描件</a>
		                                        </#if>
		                                        <#if item.dataBusiness??&&item.dataBusiness?length gt 0>
		                                            <a>丨</a>
		                                            <a href="/download/data?name=${item.dataBusiness!''}" title="点击下载文件">商业计划书</a>
		                                        <#else>
		                                            <a>丨</a>
		                                            <a href="javascript:void(0) " style="color:#666;"  title="无资料">商业计划书</a>    
		                                        </#if>
		                                        <#if item.dataPossible??&&item.dataPossible?length gt 0>
		                                            <a>丨</a>
		                                            <a href="/download/data?name=${item.dataPossible!''}" title="点击下载文件">可行性报告</a>
		                                        <#else>
		                                            <a>丨</a>
		                                            <a href="javascript:void(0) " style="color:#666;" title="无资料">可行性报告</a> 
		                                        </#if>
		                                        <#if item.dataOther??&&item.dataOther?length gt 0>
		                                            <a>丨</a>
		                                            <a href="/download/data?name=${item.dataOther!''}" title="点击下载文件">其他资料</a>
		                                        <#else>
		                                            <a>丨</a>
		                                            <a href="javascript:void(0)" style="color:#666;" title="无资料">其他资料</a>   
		                                        </#if>
					    					</#if>	
				    						<#--
				                            <a>丨</a>
				                            <a href="/enterprise/grade?activityId=${item.activityId?c!''}&enterpriseId=${item.enterpriseId?c!''}">得分</a>
				                            <a>丨</a>
				                            <a href="/activity/getCoach?enterpriseId=${item.enterpriseId?c!''}&activityId=${item.activityId?c!''}">分配路演辅导</a>
				                            <a>丨</a>
				                            <a href="#">下载</a>
				                            <a style="display:block;  width:80px;"></a>
				                            <p class="p02">辅导专家，李专家</p>
				    						-->
				    					</li>
		    					    </#list>
		    					</#if>    
		                        <#if pagetype??&& pagetype == "check">
		                        <#else>
		    					   <input id="selectEnterprise" style="width:100px; height:30px;cursor:pointer; line-height: 30px; border: none;background:white url(images/active_add_project.png) no-repeat 10px; padding-left: 13px;" type="button" <#if pagetype??&& pagetype == "check" ||activity??&&activity.statusId??&&(activity.statusId==1||activity.statusId==2)>disabled=""</#if> value="添加项目" />
		    				    </#if>
		    				</ul>
		    			</div>
             <#-->           </#if>
                    </#list>
                </#if>     -->		
 				<#if recommend_list??&&recommend_list?size gt 0>
    			<div class=" new_new">
    				<span style="margin-top: 6px;">推荐项目：</span>
    				<ul class="active_project_text">
    				    <#if recommend_list??>
    				        <#list recommend_list as item>
		    					<li>
		    					    <p class="p01" style="width: 435px; float: left;text-align:left;"><b style="float:left;">${item_index+1}.${item.enterpriseTitle!''}</b><#if item.win??&&item.win==1><img src="/client/images/n0.png" style="width:12px; height:12px;margin-left:3px;" title="胜出项目" alt="胜出" /> </#if></p> 
						            <#if mark??&&mark=="enterprise"&&activity.statusId??&&activity.statusId==2>
		                           		<a  href="/enterprise/grade/?activityId=${item.activityId?c!''}&enterpriseId=${item.enterpriseId?c!''}" title="查看该项目的得分"    target="_blank">得分</a>
									</#if>
		                            
		    						<#if mark??&&(mark=="activity"||mark="region"||mark=="expert")>
		    							<a>丨</a>
			    						<a href="/activity/enterprise/check/${item.enterpriseId?c!''}" target=_blank>查看</a>
			    						<#if item.pptUrl??&&item.pptUrl?length gt 0>
                                            <a>丨</a>
                                            <a href="/download/data?name=${item.pptUrl!''} " title="点击下载文件">PPT</a>
                                        <#else>
                                            <a>丨</a>
                                            <a href="javascript:void(0) " style="color:#666;" title="无资料">PPT</a>
                                        </#if>                                       
                                        <#if item.enterpriseFileUrl??&&item.enterpriseFileUrl?length gt 0>
                                            <a>丨</a>
                                            <a href="/download/data?name=${item.enterpriseFileUrl!''} " title="点击下载文件">扫描件</a>
                                        <#else>
                                            <a>丨</a>
                                            <a href="javascript:void(0) " style="color:#666;" title="无资料">扫描件</a>
                                        </#if>
                                        <#if item.dataBusiness??&&item.dataBusiness?length gt 0>
                                            <a>丨</a>
                                            <a href="/download/data?name=${item.dataBusiness!''}" title="点击下载文件">商业计划书</a>
                                        <#else>
                                            <a>丨</a>
                                            <a href="javascript:void(0) " style="color:#666;"  title="无资料">商业计划书</a>    
                                        </#if>
                                        <#if item.dataPossible??&&item.dataPossible?length gt 0>
                                            <a>丨</a>
                                            <a href="/download/data?name=${item.dataPossible!''}" title="点击下载文件">可行性报告</a>
                                        <#else>
                                            <a>丨</a>
                                            <a href="javascript:void(0) " style="color:#666;" title="无资料">可行性报告</a> 
                                        </#if>
                                        <#if item.dataOther??&&item.dataOther?length gt 0>
                                            <a>丨</a>
                                            <a href="/download/data?name=${item.dataOther!''}" title="点击下载文件">其他资料</a>
                                        <#else>
                                            <a>丨</a>
                                            <a href="javascript:void(0)" style="color:#666;" title="无资料">其他资料</a>   
                                        </#if>
			    					</#if>	
		    					</li>
    					    </#list>
    					</#if>    
                        <#if pagetype??&& pagetype == "check">
	                        <#if mark??&&(mark=="activity"||mark="region"||mark=="expert")>
	                        	<#if activity??&&activity.fileUrl??>
		                        	<input  style="width:100px; height:30px;cursor:pointer; line-height: 30px; border: none;background-color:#e67817;color:#fff;margin-top:10px;" type="button" onclick="location.href='/download/data?name=${activity.fileUrl!''}'" value="推荐表下载" />
	                        	<#else>
	                        		<input  style="width:100px; height:30px;cursor:pointer; line-height: 30px; border: none;background-color:#666;color:#fff;margin-top:10px;" type="button"  value="推荐表下载" />
	                        	</#if>	
	                        </#if>	
                        <#else>
    					    	<input id="selectEnterprise" style="width:100px; height:30px;cursor:pointer; line-height: 30px; border: none;background:white url(images/active_add_project.png) no-repeat 10px; padding-left: 13px;" type="button" value="添加项目" />
    				    </#if>
    				</ul>
    			</div> 
    			</#if>
    			<#if mark??&&(mark=="activity"||mark="region"||mark=="expert")||mark??&&mark=="enterprise"&&activity.statusId??&&activity.statusId==2>
	    			<div style="margin-top:50px;" class=" new_new">
	    				<span style="margin-top: 6px;">评委专家：</span>
	    				<ul class="active_project_text">
	    		    	    <#if selected_expert_list??>
	    				        <#list selected_expert_list as item>
			    					<li>
			    						<p class="p01" style="  width: 250px;float: left; text-align: left;">${item_index+1}.${item.name!''}</p>
			    						<a style="display:block;  width:100px;"></a>
					        			<#if activityType_list??>
				    				        <#list activityType_list as type>
				    				        	<#if (type_index == 0||type_index ==2)&& activity?? && type.title == activity.activityType>						
				                                    <#if activity.statusId??&&activity.statusId==0 || !activity.statusId??>
				                                   	    <a href="javascript:void(0)" title="评分尚未开始" style="color:#666;" target="_blank">评分情况</a>
				                                    <#else>
				                                    	<a href="/expert/search/grade?activityId=${activity.id?c!''}&expertId=${item.expertId?c!''}" title="查看该评委的评分详情" target="_blank">评分情况</a>
				                                    </#if>
												</#if>
				    					    </#list>
			    						</#if>   		                                    
			    					</li>
	    					    </#list>
	    					</#if>    
	                        <#if pagetype??&& pagetype == "check">
	                        <#else>
	    					   <input id="selectExpert" style="cursor:pointer;width:100px; height:30px; line-height: 30px; border: none;background:white url(images/active_add_project.png) no-repeat 10px; padding-left: 13px;" type="button" <#if pagetype??&& pagetype == "check" ||activity??&&activity.statusId??&&(activity.statusId==1||activity.statusId==2)>disabled=""</#if> value="添加评委" />
	    				    </#if>
	    				</ul>
	    			</div>
	    		</#if>	
    			<#-- 路演辅导列表 -->
               <#if roadshow_list??>
               <div style="margin-top:50px;" class=" new_new">
                    <span style="margin-top: 6px;">路演辅导：</span>
                    <ul class="active_project_text">
                            <#list roadshow_list as item>
                                <li>
                                    <p class="p01" style="  width: 250px;float: left; text-align: left;">${item_index+1}.${item.expertName!''}</p>
                                    <a style="display:block;  width:100px;"></a>
                                </li>
                            </#list>
                    </ul>
                </div>
                </#if>     	
                <#-- 路演辅导列表  end -->		
            <!-- 评分汇总 -->
            	<#if activity??&&activity.statusId??&&activity.statusId==2>
                    <div class=" new_new">
                        <span style="margin-top: 6px;">路演结果：</span>
                        <ul class="active_project_text">
                            <li>
                                <a href="/activity/getGrade?activityId=${activity.id?c!''}" target=_blank class="p01">查看排名</a>
                                <a style="display:block;  width:60px;"></a>
                            </li>
                        </ul>
                    </div>
                </#if>    
    			<!-- 评分汇总 end-->
     <#if pagetype?? && pagetype == "check">
    <#-- <#elseif activity??&&activity.statusId??&&activity.statusId==0 || activity?? && !activity.statusId?? || !activity??> -->
     <#else>	
    	<dt style=" margin-top: 40px;" class="dt05">
    	   <input type="submit" value="保存" style="cursor:pointer;"/>
    	</dt>
     </#if>
    </dl>
    </form>
    <#if activity??>
     <h2 style="margin:20px 0 20px 20px;">附件：</h2>
    <dl class="active_content">
    	<dd>
    	 <#if pagetype?? && pagetype == "check">
    	 <#else>
		<form id="upload" enctype="multipart/form-data" action="/client/activity/upload" method="post">
		<input type="hidden" id="id" name="id" <#if activity??>value="${activity.id?c}"</#if>></input>
		<div class=" new_new">
			<span>添加文件：</span>
			<input name="Filedata" type="file" id="file" value="" />
			<input  style="margin-left:20px;    border-radius: 8px;" class="area_save_btn" type="button" onclick="javascript:submitCheck();" value="上传" />
		</div>
		</form>
		</#if>
		<#if activity?? && activity.download??>
			<div class=" new_new">
				<span style="margin-top: 10px;">相关下载：</span>
				<ul class="active_add_file">
					<li>
						<img src="/client/images/active_file.png" />
						<p class="p01">${activity.download!''}</p>
						<a href="/download/data?name=${activity.download!''}">下载</a>
					</li>
				</ul>
			</div>
	    </#if>	
		</dl>
		
		<#if pagetype??&&pagetype == "check">
			<#if activity.pptUrl??&&activity.pptUrl != "">
			    <dl class="active_content">
		        <dd>
		        <input type="button" value="PPT模板下载" onclick="location.href='/download/data?name=${activity.pptUrl!''}'"  style="margin:10px 0 30px 48px;    border-radius: 8px;
		                                                float: left;
		                                                height: 26px;
		                                                line-height: 26px;
		                                                width: 170px;"></input>
		        </dd>
		        </dl>
	        </#if>
		<#else>
		    <dl class="active_content" style="margin-top:50px;">
        <dd>
		<form id="pptupload" enctype="multipart/form-data" action="/client/activity/pptupload" method="post">
        <input type="hidden" id="id" name="id" <#if activity??>value="${activity.id?c}"</#if>></input>
        <div class=" new_new">
            <span>添加PPT模板：</span>
            <input name="Filedata" type="file" id="pptfile" value="" />
            <input type="button" value="PPT模板上传" onclick="javascript:pptSubmitCheck();"  style="margin:-5px 0 30px 48px;
                                                             border-radius: 8px;
			                                                float: left;
			                                                height: 26px;
			                                                line-height: 26px;
			                                                width: 170px;"></input>
        
        </div>
        </form>
        <#if activity??&&activity.pptUrl??>
             <div>
                <span style="margin-top: 10px;">已上传PPT模板：</span>
                <ul class="active_add_file">
                    <li>
                        <img src="/client/images/active_file.png" />
                        <p class="p01">${activity.pptUrl!''}</p>
                        <a href="/download/data?name=${activity.pptUrl!''}">下载</a>
                    </li>
                </ul>
            </div>
        </#if>

		</#if>
		<#--
		<#if mark??&&mark == "enterprise">
		<dl class="active_content" style="margin-top:50px;">
        <dd>
		<form id="enterpptupload" enctype="multipart/form-data" action="/client/enterprise/pptupload" method="post">
        <input type="hidden" name="id" <#if enterprise??>value="${enterprise.id?c}"</#if>></input>
         <input type="hidden"  name="activityId" <#if activity??>value="${activity.id?c}"</#if>></input>
        <div>
            <h2 style="margin: 10px 0 20px 10px;">上传PPT：</h2>
            <input name="Filedata" type="file" id="enterpptfile" value="" />
            <input type="button" value="PPT上传" onclick="javascript:enterpptSubmitCheck();"  style="margin:-5px 0 30px 48px;
                                                             border-radius: 8px;
			                                                float: left;
			                                                height: 26px;
			                                                line-height: 26px;
			                                                width: 170px;"></input>
        
        </div>
        </form>
        	<#if enterprise.pptUrl??>
             <div>
                <span style="margin-top: 10px;">已上传PPT：</span>
                <ul class="active_add_file">
                    <li>
                        <img src="/client/images/active_file.png" />
                        <p class="p01">${enterprise.pptUrl!''}</p>
                        <a href="/download/data?name=${enterprise.pptUrl!''}">下载</a>
                    </li>
                </ul>
            </div>
            </#if>
        </#if>-->
	</#if>
		
    </div>
</div><!--content_end-->
</div><!--main-->
</body>
</html>
