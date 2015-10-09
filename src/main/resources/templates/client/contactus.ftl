<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>联系我们</title>
	<link rel="stylesheet" href="/client/css/news_base.css">
	<link rel="stylesheet" href="/client/css/news_main.css">
	<script src="/client/js/l_main.js"></script>
	<script src="/client/js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="/client/js/Validform_v5.3.2_min.js"></script>		
<script>
$(document).ready(function(){
	$("#form1").Validform({
	    	  tiptype:4,
			  ajaxPost:true,
	          callback: function (data) { 
	   		  if (data.code == 0) {
		          alert("提交成功");
	              window.location.reload();
              }
             else {
                alert(data.msg);
             }
        }
	});
});

   document.onkeydown = function(event){
    if((event.keyCode || event.which) == 13){
        $("#btn_submit").click();
    }
   }
</script>		
<script>
function show1()
{
	$(".contactussmall2").addClass("hide");
	$(".contactussmall").removeClass("hide");
}

function show2()
{
	$(".contactussmall").addClass("hide");
	$(".contactussmall2").removeClass("hide");
}
</script>
</head>
<body>
<!-- head  -->
<#include "/client/news_common_header.ftl" />
<!-- head end -->
<!-- contend -->
<div class="titlelist">
	<div id="titlelist">
	<div>
		<a href="javascript:show1();" class="active">联系我们</a>
		<a href="javascript:show2();">在线留言</a>
</div>
	</div>
<div class="contactustitle">
		<span>联系我们</span>
		<hr style="border-top:1px solid #DDDDDD;" />
</div>
	<div class="contactussmall">
		<div class="contactus">
			<#if site??>
				<h1>${site.title!''}</h1>
				<h3 class="contactustel">电话：${site.telephone!''}</h3>
				<h3 class="contactusaddress">地址：${site.address!''}</h3>
				<h3 class="contactusqq">QQ：${site.qq1!''}</h3>
				<h3 class="contactusemail">邮箱：${site.adminEmail!''}</h3>
				<h3 class="contactusleft">线路：${site.busRoute!''}</h3>
				<h3 class="contactusleft2">${site.addressDetail!''}</h3>
				<h3 class="contactuswechat">扫描添加微信：</h3>
				<img src="${site.wxQrCode!''}" alt="">
			</#if>	
		</div>
		<div class="map">
			<iframe src="map.html" frameborder="0"></iframe>
		</div>
	</div>
	<div class="contactussmall2 hide">
		<div class="contactustitle">
			<span>在线留言</span>
			<hr style="border-top:1px solid #DDDDDD;" />
		</div>
		<form id="form1" action="/suggestion/submit">
		<div class="massage">
			<span>标题：</span>
			<input type="text" name="title" class="search">
			<br>
			<span>内容:</span>
			<textarea name="content" id="" cols="30" rows="10"></textarea>
			<br>
			<input type="submit" id="btn_submit" class="button" value="提交" />
			<div class="map" style="margin-top:-210px;">
				<iframe src="map.html" frameborder="0"></iframe>
			</div>
		</div>
		
		</form>
	</div>
</div>
<!-- contendend -->


<!-- 底部 -->
<#include "/client/news_common_footer.ftl" />
<!-- 底部end -->

</body>
</html>