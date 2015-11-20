<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>科技小巨人-联系我们</title>
	<link rel="shortcut icon" href="/client/images/icon.ico" />
	<link rel="stylesheet" href="/client/css/news_base.css">
	<link rel="stylesheet" href="/client/css/news_main.css">
	<link rel="stylesheet" href="/client/css/ios6alert.css">
	<script src="/client/js/l_main.js"></script>
	<script src="/client/js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="/client/js/Validform_v5.3.2_min.js"></script>	
	<script src="/client/js/ios6alert.js"></script>
	<style type="text/css">
		#allmap {width: 100%;height: 100%;overflow: hidden;margin:0;font-family:"微软雅黑";}
	</style>
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=2CRkBB0Hh3ux5pookBULG1Px"></script>
<script>
$(document).ready(function(){
	$("#form1").Validform({
	    	  tiptype:4,
			  ajaxPost:true,
	          callback: function (data) { 
	   		  if (data.code == 0) {
					$("body").ios6alert({
						title : "科技小巨人-留言",
						content : "提交成功！"
					});
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
<script type="text/javascript">
$(document).ready(function(){
        loadMap(${site.longitude!'102.718072'}, ${site.latitude!'25.048034'});
});

function loadMap(x, y)
{
    // 百度地图API功能
    /*
    var map = new BMap.Map("allmap");    // 创建Map实例
    map.centerAndZoom(new BMap.Point(x, y), 16);  // 初始化地图,设置中心点坐标和地图级别
    
    map.setCurrentCity("重庆");          // 设置地图显示的城市 此项是必须设置的
    
    map.addOverlay(new BMap.Marker(new BMap.Point(x, y)); // 创建点
    */
    
    var map = new BMap.Map("allmap");
    var point = new BMap.Point(x, y);
    map.centerAndZoom(point, 16);
    map.enableScrollWheelZoom(true);     //开启鼠标滚轮缩放
    map.addControl(new BMap.MapTypeControl());   //添加地图类型控件
    var marker = new BMap.Marker(new BMap.Point(x, y)); // 创建点
    map.addOverlay(marker);
}
</script>
</head>
<body>
<!-- head  -->
<#include "/client/contact_common_header.ftl" />
 
<!-- contend -->
<div class="titlelist">
	<div id="titlelist">
	<div>
		<a href="javascript:void(0);" class="active">联系我们</a>
		<a href="javascript:void(0);">在线留言</a>
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
				<h3 class="contactustel">
				电话：
				<#list site.telephone?split(",") as item>
					<#if item !="">
						${item!''} &nbsp;
					</#if>
				</#list>	
				</h3>
				<h3 class="contactusemail">传真：
				<#list site.fax?split(",") as item>
					<#if item !="">
						${item!''} &nbsp;
					</#if>
				</#list>	
				</h3>				
				<h3 class="contactusqq">Q&nbsp;Q：${site.qq1!''}</h3>
				<h3 class="contactusemail">邮箱：${site.adminEmail!''}</h3>
				<h3 class="contactusaddress">地址：${site.address!''}</h3>				
				<h3 class="contactusleft">线路：${site.busRoute!''}</h3>
				<h3 class="contactusleft2">${site.addressDetail!''}</h3>
				<h3 class="contactuswechat">扫描添加微信：</h3>
				<img src="${site.wxQrCode!''}" alt="">
			</#if>	
		</div>
		<div class="map">
		<#--	<iframe src="map.html" frameborder="0"></iframe> -->
			<div id="allmap"></div>

		</div>
	</div>
	<div class="contactussmall2">
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
			<textarea name="content" id="" cols="30" rows="10" datatype="*2-255"></textarea>
			<br>
			<input style="cursor:pointer;" type="submit" id="btn_submit" class="button" value="提交" />

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