<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<title>填写资料</title>
<link rel="shortcut icon" href="/client/images/icon.ico" />
<link href="/client/css/base.css" rel="stylesheet" type="text/css" />
<link href="/client/css/team.css" rel="stylesheet" type="text/css" />

<script src="/client/js/jquery-1.9.1.min.js"></script>
<script src="/client/js/main.js"></script>
<script type="text/javascript" src="/client/js/Validform_v5.3.2_min.js"></script>
<script>

function done()
{
    alert("上传资料成功！");	
	location.href="/enterprise/check";
}
<#if done?? &&done == 1>
window.onload=done;
</#if>

function submitCheck()
{
	var filedata = $("#file").val();
	if (filedata == "")
	{
		alert("请添加文件！")
	}
<#--
	var filedata = $("#file").val();

	var target = document.getElementById("file");	
	var isIE = /msie/i.test(navigator.userAgent) && !window.opera;
	var fileSize = 0;
	if (isIE && !target.files)
	{
		var filePath = target.value;
		var fileSystem = new ActiveXObject("Scripting.FileSystemObject");
		var file = fileSystem.GetFile (filePath);
		fileSize = file.Size;
	} else {
		fileSize = target.files[0].size;	
	}


		if (fileSize >= 1024*1024)
		{
			alert("上传文件不能大于1M！");
		}else{
		$("#upload").submit();
		}
	-->

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
            <dd><a href="/enterprise/info">基本资料</a></dd>
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
                <a href="#">基本资料</a>
                <p>&gt;</p>
                <a href="#">填写资料</a>
            </dd>
            <dt class="crumb_back"><a  href="javascript:history.go(-1);">返回上一页</a></dt>
        </dl>
        <dl class="team_apply">
        	<dt><a class="a1" href="/enterprise/info">第一步<br/>资料填写</a></dt>
        	<dd></dd>
        	<dt><a class="a2" href="/enterprise/print">第二步<br />生成打印</a></dt>
        	<dd></dd>
        	<dt><a class="a3" href="/enterprise/upload">第三步<br />上传报名表</a></dt>
        </dl>
        <div style="float:left;	">
        <form id="upload" enctype="multipart/form-data" action="/client/enterprise/upload" method="post">
        <dl class="apply_step2" >
            <input type="hidden" id="id" name="id" value="<#if id??>${id?c!''}</#if>"></input>
				<dt>*点击选择文件，上传已经打印并盖章的报名表扫描文件</dt>
				<dd ><input id="file" style="margin-top: 20px ; background : #fff;color:#333;" name="Filedata" type="file" value="" /></dd>
		</dl>	
		<dl class="apply_step2" style="margin-top:20px ; ">
				<dt>*多个文件请放置在同一个文件夹内，并添加为压缩文件上传</dt>
				<dd><input  style="background:#529c15;"  class="area_save_btn" type="button" value="上传报名表" onclick="javascript:submitCheck();"/></dd>
		</dl>		
        </form>
        </div>
    </div>  
    </div>
</div><!--content_end-->
</div><!--main-->
</body>
</html>
