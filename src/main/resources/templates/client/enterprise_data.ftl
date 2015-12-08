<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<title>个人中心-上传资料</title>
<link rel="shortcut icon" href="/client/images/icon.ico" />
<link href="/client/css/base.css" rel="stylesheet" type="text/css" />
<link href="/client/css/team.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="/client/css/showBo.css">

<style>
.apply_content dd div .Validform_wrong span{ text-align:left;}
</style>

<script src="/client/js/jquery-1.9.1.min.js"></script>
<script src="/client/js/main.js"></script>
<script type="text/javascript" src="/mag/js/WdatePicker.js"></script>
<script type="text/javascript" src="/client/js/Validform_v5.3.2_min.js"></script>
<script src="/client/js/showBo.js"></script>
<script>

function done()
{
    Showbo.Msg.alert("上传资料成功！");
}
function done2()
{
    Showbo.Msg.alert("文件类型错误！");
}
<#if done?? &&done == 1>
window.onload=done;
</#if>
<#if done?? &&done == 2>
window.onload=done2;
</#if>

function submitCheck0()
{
	var filedata = $("#file0").val();
	if (filedata == "")
	{
	    Showbo.Msg.alert("请添加文件！");
		}else{
		$("#upload0").submit();
		}
}

function submitCheck1()
{
	var filedata = $("#file1").val();
	if (filedata == "")
	{
	    Showbo.Msg.alert("请添加文件！");
		}else{
		$("#upload1").submit();
		}
}

function submitCheck2()
{
	var filedata = $("#file2").val();
	if (filedata == "")
	{
	    Showbo.Msg.alert("请添加文件！");
		}else{
		$("#upload2").submit();
		}
}

function submitCheck3()
{
	var filedata = $("#file3").val();
	if (filedata == "")
	{
	    Showbo.Msg.alert("请添加文件！");
		}else{
		$("#upload3").submit();
		}
}
</script>
</head>

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
            <dd><a href="/enterprise/check">网上报名</a></dd>
            <dd><a href="/enterprise/data">项目资料</a></dd>
            <dd><a href="/enterprise/activity/list">活动列表</a></dd>
            <dd><a href="/enterprise/project">申请展示</a></dd>

		</dl>
	</div>
<!--right-->
    <div class="right_content">

    <div class="right_box">
    	<dl class="crumb" style="z-index:1000;">
        	<dt><a href="#"></a></dt>
            <dd>
            	<p>当前所在位置:</p>
                <a href="#">企业/团队</a>
                <p>&gt;</p>
                <a href="#">基本资料</a>
                <p>&gt;</p>
                <a href="#">上传资料</a>
            </dd>
            <dt class="crumb_back" onClick="history.go(-1);"><a>返回上一页</a></dt>
        </dl>


       
    </div>  
    <dl class="apply_content">
			      	<form id="upload0" enctype="multipart/form-data" action="/client/enterprise/pptupload" method="post">
			            <input type="hidden" name="id" value="${enterprise.id?c!''}" />
				    	<dt class="dt01" style="background:#80a2c6;"><span>PPT</span><br/><p>项目展示PPT</p></dt>
				    	<dd>
				    			<div>
				    			     <span><input id="file0" style="margin: 20px 0 0 48px ;background : #fff;color:#333;" name="Filedata" type="file" value="" /></span>
				    			     <input  style="background:#ccc;margin:18px 0 0 110px;
				    			     						  cursor:pointer;
															  font-size: 12px;
															  height: 30px;
															  width: 108px;
															  line-height: 30px;
															  margin-right: 20px;
															  margin-top: 15px;
															  border-radius: 6px;
															  border: none;
															  color: white;
															  background: #e67817; " 
															   class="area_save_btn" type="button" value="上传项目资料" onclick="javascript:submitCheck0();"/>
				    		    </div>
				    		<#if enterprise.pptUrl??&&enterprise.pptUrl != "">
				    		    <div style="margin-top:20px;">
				    		    	<span>已上传资料：</span>
				    		    	<a href="/download/data?name=${enterprise.pptUrl!''}" title="点击下载">${enterprise.pptUrl!''}</a>
				    		    </div>	    		    
			    		    </#if>
				    	</dd>
				    </form>	
    </dl>
    
     	<#if enterprise.dataAble??>
    		<#list enterprise.dataAble?split(",") as data>
    			<#if data == "商业计划书">   
    			<dl class="apply_content">
			      	<form id="upload1" enctype="multipart/form-data" action="/client/dataBusiness/upload" method="post">
			            <input type="hidden" name="id" value="${enterprise.id?c!''}" />
				    	<dt class="dt01" style="background:#80a2c6;"><span>商业计划书</span><br/><p>报名表中勾选此项时上传</p></dt>
				    	<dd>
				    			<div>
				    			     <span><input id="file1" style="margin: 20px 0 0 48px ;background : #fff;color:#333;" name="Filedata" type="file" value="" /></span>
				    			     <input  style="background:#ccc;margin:18px 0 0 110px;
				    			     						  cursor:pointer;
															  font-size: 12px;
															  height: 30px;
															  width: 108px;
															  line-height: 30px;
															  margin-right: 20px;
															  margin-top: 15px;
															  border-radius: 6px;
															  border: none;
															  color: white;
															  background: #e67817; " 
															   class="area_save_btn" type="button" value="上传项目资料" onclick="javascript:submitCheck1();"/>
				    		    </div>
				    		<#if enterprise.dataBusiness??&&enterprise.dataBusiness != "">
				    		    <div style="margin-top:20px;">
				    		    	<span>已上传资料：</span>
				    		    	<a href="/download/data?name=${enterprise.dataBusiness!''}" title="点击下载">${enterprise.dataBusiness!''}</a>
				    		    </div>	    		    
			    		    </#if>
				    	</dd>
				    </form>	
				    </dl>
	    		</#if>
	    	</#list>
	    </#if>
    	<#if enterprise.dataAble??>
    		<#list enterprise.dataAble?split(",") as data>
    			<#if data == "可行性报告">
    			<dl class="apply_content">	    	    
			    	<form id="upload2" enctype="multipart/form-data" action="/client/dataPossible/upload" method="post">
			    		  <input type="hidden" name="id" value="${enterprise.id?c!''}" />
			    	    	<dt class="dt01" style="background:#80a2c6;margin-top:100px;"><span>可行性报告</span><br/><p>报名表中勾选此项时上传</p></dt>
			    	<dd>
			    			<div>
			    			     <span><input id="file2" style="margin: 20px 0 0 48px ; background : #fff;color:#333;" name="Filedata" type="file" value="" /></span>
				    			     <input  style="background:#ccc;margin:18px 0 0 110px;
				    			     						  cursor:pointer;
															  font-size: 12px;
															  height: 30px;
															  width: 108px;
															  line-height: 30px;
															  margin-right: 20px;
															  margin-top: 15px;
															  border-radius: 6px;
															  border: none;
															  color: white;
															  background: #e67817; "  
			    			     class="area_save_btn" type="button" value="上传项目资料" onclick="javascript:submitCheck2();"/>
			    		    </div>
			    		    <#if enterprise.dataPossible??&&enterprise.dataPossible != "">
				    		    <div style="margin-top:20px;">
				    		    	<span>已上传资料：</span>
				    		    	<a href="/download/data?name=${enterprise.dataPossible!''}" title="点击下载">${enterprise.dataPossible!''}</a>
				    		    </div>
			    		    </#if>
			    	</dd>
			    	</form>	
			    	</dl>
	    		</#if>
	    	</#list>
	    </#if>
    	<#if enterprise.dataAble??>
    		<#list enterprise.dataAble?split(",") as data>
    			<#if data == "其他说明资料">	  
    				<dl class="apply_content">      	
			    	<form id="upload3" enctype="multipart/form-data" action="/client/dataOther/upload" method="post">
			    		  <input type="hidden" name="id" value="${enterprise.id?c!''}" />
			    	    	<dt class="dt01" style="background:#80a2c6; margin-top:100px;"><span>其他说明资料</span><br/><p>报名表中勾选此项时上传</p></dt>
			    	<dd>
			    			<div>
			    			     <span><input id="file3" style="margin: 20px 0 0 48px ; background : #fff;color:#333;" name="Filedata" type="file" value="" /></span>
				    			     <input  style="background:#ccc;margin:18px 0 0 110px;
				    			     						  cursor:pointer;
															  font-size: 12px;
															  height: 30px;
															  width: 108px;
															  line-height: 30px;
															  margin-right: 20px;
															  margin-top: 15px;
															  border-radius: 6px;
															  border: none;
															  color: white;
															  background: #e67817; " 
			    			       							class="area_save_btn" type="button" value="上传项目资料" onclick="javascript:submitCheck3();"/>
			    		    </div>
			    		    <#if enterprise.dataOther??&&enterprise.dataOther != "">
				    		    <div style="margin-top:20px;">
				    		    	<span>已上传资料：</span>
				    		    	<a href="/download/data?name=${enterprise.dataOther!''}" title="点击下载">${enterprise.dataOther!''}</a>
				    		    </div>    		    
			    		    </#if>
			    	</dd>
			    	</form>	
			    	</dl>
	    		</#if>
	    	</#list>
	    </#if>
    </div>

</div><!--content_end-->
</div><!--main-->
</body>
</html>
